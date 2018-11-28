package com.hryj.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.hryj.constant.HeaderConstants;
import com.hryj.entity.vo.RequestVO;
import eu.bitwalker.useragentutils.UserAgent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 李道云
 * @className: WebUtil
 * @description:
 * @create 2018-06-12 16:58
 **/
public class WebUtil {

    /**
     * 获取上下文URL全路径
     * @param request
     * @return
     */
    public static String getContextPath(HttpServletRequest request) {
        StringBuffer sb = new StringBuffer();
        sb.append(request.getScheme()).append("://");
        sb.append(request.getServerName());
        //80端口无需拼接
        if(request.getServerPort() !=80){
            sb.append(":").append(request.getServerPort());
        }
        sb.append(request.getContextPath());
        String path = sb.toString();
        return path;
    }

    /**
     * 获取上次访问的URL链接(不包含http://www.XXXXX)
     * @param request
     * @return
     */
    public static String getLastURL(HttpServletRequest request) {
        String path = getContextPath(request);
        String referer = request.getHeader("referer");
        if(StrUtil.isNotEmpty(referer)){
            int index = path.length();
            return referer.substring(index);
        }else{
            return null;
        }
    }

    /**
     * 获取IP地址
     * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (StrUtil.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StrUtil.isEmpty(ip) || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StrUtil.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StrUtil.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StrUtil.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    /**
     * 去掉url中的路径,留下请求参数部分
     * @param url
     * @return
     */
    private static String truncateUrlPage(String url) {
        String strAllParam = null;
        //url = url.replace("\"", "").trim();
        String[] arrSplit = url.split("[?]");
        if (url.length() > 1) {
            if (arrSplit.length > 1) {
                if (arrSplit[1] != null) {
                    strAllParam = arrSplit[1];
                }
            }else{
                strAllParam = url;
            }
        }
        return strAllParam;
    }

    /**
     * 解析出url参数中的键值对 如 "index.jsp?Action=del&id=123",解析出Action:del,id:123存入map中
     * @param url url地址
     * @return
     */
    public static Map<String, String> parseURLParam(String url) {
        Map<String, String> mapRequest = new HashMap<String, String>();
        String strUrlParam = truncateUrlPage(url);
        if (StrUtil.isEmpty(strUrlParam)) {
            return mapRequest;
        }
        // 每个键值为一组
        String[] arrSplit = strUrlParam.split("[&]");
        for (String strSplit : arrSplit) {
            int index = strSplit.indexOf("=");
            String key = strSplit.substring(0, index);
            String value = strSplit.substring(index+1);
            if(StrUtil.isNotEmpty(key)){
                mapRequest.put(key, value);
            }
        }
        return mapRequest;
    }

    /**
     * 输出响应信息
     * @param response
     * @param result
     */
    public static void outPrint(HttpServletResponse response, String result){
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        try {
            PrintWriter pw = response.getWriter();
            pw.write(result);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取请求日志信息
     * @param request
     * @return
     */
    public static Map<String,Object> getRequestLog(HttpServletRequest request){
        Map<String,Object> logMap = new HashMap<>();
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        logMap.put("user_agent",JSON.toJSONString(userAgent));
        logMap.put("request_url",request.getRequestURL().toString());
        logMap.put("request_uri",request.getRequestURI());
        logMap.put("request_method",request.getMethod());
        logMap.put("referer",request.getHeader("referer"));
        logMap.put("session_id",request.getSession().getId());
        logMap.put("request_time", DateUtil.date());
        //自定义请求Header
        logMap.put("app_sign",request.getHeader("app_sign"));
        logMap.put("app_key",request.getHeader("app_key"));
        logMap.put("app_version",request.getHeader("app_version"));
        logMap.put("call_source",request.getHeader("call_source"));
        logMap.put("app_channel",request.getHeader("app_channel"));
        logMap.put("device_id",request.getHeader("device_id"));
        logMap.put("device_model",request.getHeader("device_model"));
        logMap.put("os_version",request.getHeader("os_version"));
        logMap.put("device_ip",request.getHeader("device_ip"));
        logMap.put("time_stamp",request.getHeader("time_stamp"));
        logMap.put("login_token",request.getHeader("login_token"));
        return logMap;
    }

    /**
     * @author 李道云
     * @description: 将请求header内的参数信息拷贝到requestVO里面
     * @param: [request, requestVO]
     * @return com.hryj.entity.vo.RequestVO
     * @create 2018-06-26 14:57
     **/
    public static RequestVO getRequestVO(HttpServletRequest request,RequestVO requestVO){
        if(requestVO ==null){
            requestVO = new RequestVO();
        }
        String app_key = request.getHeader(HeaderConstants.APP_KEY);
        String app_version = request.getHeader(HeaderConstants.APP_VERSION);
        String call_source = request.getHeader(HeaderConstants.CALL_SOURCE);
        String app_channel = request.getHeader(HeaderConstants.APP_CHANNEL);
        String device_id = request.getHeader(HeaderConstants.DEVICE_ID);
        String device_model = request.getHeader(HeaderConstants.DEVICE_MODEL);
        String os_version = request.getHeader(HeaderConstants.OS_VERSION);
        String device_ip = request.getHeader(HeaderConstants.DEVICE_IP);
        String time_stamp = request.getHeader(HeaderConstants.TIME_STAMP);
        String login_token = request.getHeader(HeaderConstants.LOGIN_TOKEN);
        requestVO.setApp_key(app_key);
        requestVO.setApp_version(app_version);
        requestVO.setCall_source(call_source);
        requestVO.setApp_channel(app_channel);
        requestVO.setDevice_id(device_id);
        requestVO.setDevice_model(device_model);
        requestVO.setOs_version(os_version);
        requestVO.setTime_stamp(time_stamp);
        requestVO.setDevice_ip(StrUtil.isEmpty(device_ip) ? getIpAddr(request):device_ip);
        requestVO.setLogin_token(login_token);
        return requestVO;
    }
}
