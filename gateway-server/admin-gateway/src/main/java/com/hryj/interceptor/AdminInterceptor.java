package com.hryj.interceptor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.hryj.cache.LoginCache;
import com.hryj.common.CodeEnum;
import com.hryj.common.Result;
import com.hryj.constant.HeaderConstants;
import com.hryj.entity.vo.staff.user.StaffAdminLoginVO;
import com.hryj.utils.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author 李道云
 * @className: AdminInterceptor
 * @description: 运营后台请求拦截器
 * @create 2018/7/12 20:27
 **/
@Slf4j
public class AdminInterceptor implements HandlerInterceptor {

    /**
     * 请求拦截
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //解决跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        String request_uri = request.getRequestURI();
        log.info("请求RequestURI=" + request_uri);
        long start_time = System.currentTimeMillis();
        request.setAttribute("start_time", start_time);
        //打印请求日志
        Map<String,Object> logMap = WebUtil.getRequestLog(request);
        log.info("运营后台，请求request信息:" + JSON.toJSONString(logMap));
        String referer = request.getHeader("referer");
        //直接放过的请求
        if(request_uri.contains("swagger") || (StrUtil.isNotEmpty(referer) && referer.contains("swagger"))){
            return true;
        }
        //不需要登录权限的uri资源
        if(request_uri.startsWith("/login") || request_uri.startsWith("/sys")||request_uri.contains("/upload/excel")){
            return true;
        }
        //登录权限判断
        String login_token = request.getHeader(HeaderConstants.LOGIN_TOKEN);
        if(StrUtil.isNotEmpty(login_token)){
            StaffAdminLoginVO staffAdminLoginVO = LoginCache.getStaffAdminLoginVO(login_token);
            if(staffAdminLoginVO !=null){
                //刷新token过期时间
                LoginCache.expireStaffAdminLoginTime(login_token);
                if(request_uri.contains("common") || request_uri.contains("Common") || request_uri.equals("/staff/logout") || request_uri.equals("/staff/updateStaffLoginPwd")){
                    return true;
                }
                //判断用户是否有该请求权限
                if(StrUtil.isEmpty(staffAdminLoginVO.getPermUrlList()) || !staffAdminLoginVO.getPermUrlList().contains(request_uri)){
                    WebUtil.outPrint(response, JSON.toJSONString(new Result(CodeEnum.FAIL_UNAUTHORIZED)));
                    return false;
                }
            }else{
                WebUtil.outPrint(response, JSON.toJSONString(new Result(CodeEnum.FAIL_TOKEN_INVALID)));
                return false;
            }
        }else{
            WebUtil.outPrint(response, JSON.toJSONString(new Result(CodeEnum.FAIL_UNAUTHORIZED)));
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        long start_time = (Long) request.getAttribute("start_time");
        long end_time = System.currentTimeMillis();
        long consume_time = end_time - start_time;
        log.info("postHandle-response.getStatus()：" + response.getStatus());
        log.info("postHandle访问目标:" + method.getDeclaringClass().getName() + "." + method.getName() + "，消耗时间：" + consume_time + " ms");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception{
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        log.info("afterCompletion-response.getStatus()：" + response.getStatus());
        log.info("afterCompletion-getShortLogMessage：" + handlerMethod.getShortLogMessage());
        log.info("afterCompletion-访问目标：" + method.getDeclaringClass().getName() + "." + method.getName() + " 结束");
    }
}
