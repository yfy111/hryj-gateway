package com.hryj.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hryj.common.CodeEnum;
import com.hryj.common.Result;
import com.hryj.constant.HeaderConstants;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLDecoder;
import java.util.*;

/**
 * @author 李道云
 * @className: SignUtil
 * @description: 签名工具类
 * @create 2018-06-12 16:30
 **/
@Slf4j
public class SignUtil {

    private static final String contentEncoding = "UTF-8";

    private static final String PUBLIC_KEY = "E92AA1213A79E44C2111CF369C8DB82F";

    /**
     * 接口签名校验
     * @param request
     * @return
     */
    public static Result valiSign(HttpServletRequest request) throws IOException {
        //1、校验请求头里的参数
        String app_sign = request.getHeader(HeaderConstants.APP_SIGN);
        //“EF1DE874346D850670ACEA7948E77BB0”为万能签名，用于开发调试接口，不可泄露
        if("EF1DE874346D850670ACEA7948E77BB0".equals(app_sign)){
            return new Result(CodeEnum.SUCCESS);
        }
        if(StrUtil.isEmpty(app_sign)){
            return new Result(CodeEnum.FAIL_PARAMCHECK,"app_sign不能为空");
        }
        Result result = checkRequestHeader(request);
        if(result.isFailed()){
            return result;
        }
        String app_key = request.getHeader(HeaderConstants.APP_KEY);
        String time_stamp = request.getHeader(HeaderConstants.TIME_STAMP);
        //2、校验请求是否已过期
        Date device_date = DateUtil.date(Long.parseLong(time_stamp));
        Date local_date = DateUtil.date();
        //long time = DateUtil.between(device_date, local_date, DateUnit.MINUTE);
        log.info("device_date=" + device_date+"，local_date=" + local_date);
        //时间相差5分钟为过期
        //if(time > 5){
        //    log.info("客户端时间为：{},服务器时间为{}", device_date, local_date);
        //    return new Result(CodeEnum.FAIL_PARAMCHECK,"签名信息已过期");
        //}
        //3、校验签名信息是否正确： Base64(md5(PUBLIC_KEY+app_key+time_stamp))
        String pre_msd5 = PUBLIC_KEY+app_key+time_stamp;
        //log.info("md5前pre_msd5：{}", pre_msd5);
        String after_md5 = SecureUtil.md5(pre_msd5);
        //log.info("md5后after_md5：{}", after_md5);
        String local_sign = Base64.encode(after_md5);
        log.info("后端签名信息local_sign：{}", local_sign);
        log.info("app签名信息app_sign：{}", app_sign);
        //忽略大小写
        if(!app_sign.equalsIgnoreCase(local_sign)){
            return new Result(CodeEnum.FAIL_UNAUTHORIZED,"签名校验失败");
        }
        return new Result(CodeEnum.SUCCESS);
    }

    /**
     * 校验请求头里的参数
     * @param request
     * @return
     */
    private static Result checkRequestHeader(HttpServletRequest request){
        String app_key = request.getHeader(HeaderConstants.APP_KEY);
        String app_version = request.getHeader(HeaderConstants.APP_VERSION);
        String call_source = request.getHeader(HeaderConstants.CALL_SOURCE);
        String app_channel = request.getHeader(HeaderConstants.APP_CHANNEL);
        String device_id = request.getHeader(HeaderConstants.DEVICE_ID);
        String device_model = request.getHeader(HeaderConstants.DEVICE_MODEL);
        String os_version = request.getHeader(HeaderConstants.OS_VERSION);
        String device_ip = request.getHeader(HeaderConstants.DEVICE_IP);
        String time_stamp = request.getHeader(HeaderConstants.TIME_STAMP);
        if(StrUtil.isEmpty(app_key)){
            return new Result(CodeEnum.FAIL_PARAMCHECK,"app_key不能为空");
        }
        if(StrUtil.isEmpty(app_version)){
            return new Result(CodeEnum.FAIL_PARAMCHECK,"app_version不能为空");
        }
        if(StrUtil.isEmpty(call_source)){
            return new Result(CodeEnum.FAIL_PARAMCHECK,"call_source不能为空");
        }
        if(StrUtil.isEmpty(app_channel)){
            return new Result(CodeEnum.FAIL_PARAMCHECK,"app_channel不能为空");
        }
        if(StrUtil.isEmpty(device_id)){
            return new Result(CodeEnum.FAIL_PARAMCHECK,"device_id不能为空");
        }
        if(StrUtil.isEmpty(device_model)){
            return new Result(CodeEnum.FAIL_PARAMCHECK,"device_model不能为空");
        }
        if(StrUtil.isEmpty(os_version)){
            return new Result(CodeEnum.FAIL_PARAMCHECK,"os_version不能为空");
        }
        if(StrUtil.isEmpty(device_ip)){
            return new Result(CodeEnum.FAIL_PARAMCHECK,"device_ip不能为空");
        }
        if(StrUtil.isEmpty(time_stamp)){
            return new Result(CodeEnum.FAIL_PARAMCHECK,"time_stamp不能为空");
        }
        return new Result(CodeEnum.SUCCESS);
    }

    /**
     * 获取请求Body里面的参数json字符串
     * @param request
     * @return
     */
    private static String getBodyParamStr(HttpServletRequest request) {
        String reqBody = "";
        try {
            InputStream inputStream = request.getInputStream();
            if(inputStream !=null){
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                StringBuilder sb = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
                if (sb.length() < 1) {
                    return "";
                }
                String valueStr = sb.toString();
                //valueStr = valueStr.replaceAll("%(?![0-9a-fA-F]{2})", "%25").replaceAll("\\+", "%2B");
                reqBody = URLDecoder.decode(valueStr, contentEncoding);
                if(reqBody.indexOf("{") != -1){
                    reqBody = reqBody.substring(reqBody.indexOf("{"));
                }
            }
        } catch (IOException e) {
            log.error("获取请求Body里面的参数json字符串异常：{}", JSON.toJSON(e));
        }
        return reqBody;
    }

    /**
     * 获取请求body内的参数
     * @param request
     * @return
     */
    private static Map<String,Object> getBodyParams(HttpServletRequest request) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            if(request.getInputStream() !=null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
                String line = null;
                StringBuilder sb = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
                if (sb.length() < 1) {
                    return paramsMap;
                }
                String valueStr = sb.toString();
                //valueStr = valueStr.replaceAll("%(?![0-9a-fA-F]{2})", "%25").replaceAll("\\+", "%2B");
                String reqBody = URLDecoder.decode(valueStr, contentEncoding);
                if(reqBody.indexOf("{") != -1){
                    reqBody = reqBody.substring(reqBody.indexOf("{"));
                }
                if (StrUtil.isNotBlank(reqBody)) {
                    return JSONObject.parseObject(reqBody, Map.class);
                }
            }
        } catch (IOException e) {
            log.error("获取请求Body里面的参数json字符串异常：{}", JSON.toJSON(e));
        }
        return paramsMap;
    }


    /**
     * 获取request请求里的参数（解码后的）
     * @param request
     * @return
     */
    private static Map<String,Object> getRequestParams(HttpServletRequest request){
        Map<String, Object> urlParams = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        if(requestParams !=null){
            Set<String> keySet = requestParams.keySet();
            Iterator it = keySet.iterator();
            while (it.hasNext()) {
                String name = (String) it.next();
                String[] values = requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == (values.length - 1)) ? valueStr + values[i] : valueStr + values[i] + ",";
                }
                try {
                    valueStr = valueStr.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
                    valueStr = valueStr.replaceAll("\\+", "%2B");
                    valueStr = URLDecoder.decode(valueStr,contentEncoding);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                urlParams.put(name, valueStr);
            }
        }
        return urlParams;
    }

    /**
     * 按参数key排序后，获取参数字符串
     * @param request
     * @return
     */
    private static String getOrderParamString(HttpServletRequest request){
        Map<String, Object> params = getBodyParams(request);
        if(params ==null || params.size()==0){
            return "";
        }
        StringBuffer sb = new StringBuffer();
        try {
            ArrayList<String> keyList = new ArrayList<>(params.keySet());
            Collections.sort(keyList);
            for (String key : keyList) {
                Object obj = params.get(key);
                String value = null;
                if(obj !=null){
                    //value = URLEncoder.encode(obj.toString(),contentEncoding);
                    value = obj.toString();
                }
                sb.append(key).append("=").append(value).append("&");
            }
            if (sb.length() > 1) {
                sb.deleteCharAt(sb.length()-1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sb.toString();
    }
}
