package com.hryj.interceptor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.hryj.cache.LoginCache;
import com.hryj.common.CodeEnum;
import com.hryj.common.Result;
import com.hryj.constant.HeaderConstants;
import com.hryj.entity.vo.staff.user.StaffAppLoginVO;
import com.hryj.entity.vo.user.UserLoginVO;
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
 * @className: WebInterceptor
 * @description: web页面请求拦截器
 * @create 2018/7/12 20:27
 **/
@Slf4j
public class WebInterceptor implements HandlerInterceptor {

    /**
     * 请求拦截
     *
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
        long start_time = System.currentTimeMillis();
        request.setAttribute("start_time", start_time);
        //打印请求日志
        Map<String, Object> logMap = WebUtil.getRequestLog(request);
        log.info("web端，请求request信息:" + JSON.toJSONString(logMap));
        String referer = request.getHeader("referer");
        //直接放过的请求
        if (request_uri.contains("swagger") || (StrUtil.isNotEmpty(referer) && referer.contains("swagger"))) {
            return true;
        }
        //不需要登录权限的uri资源
        if (request_uri.startsWith("/login") || request_uri.startsWith("/sys")) {
            return true;
        }
        //登录权限判断
        String login_token = request.getHeader(HeaderConstants.LOGIN_TOKEN);
        if (StrUtil.isNotEmpty(login_token)) {
            UserLoginVO userLoginVO = LoginCache.getUserLoginVO(login_token);
            if (userLoginVO != null) {
                //刷新token过期时间
                LoginCache.expireUserLoginTime(login_token);
            } else {
                StaffAppLoginVO staffAdminLoginVO = LoginCache.getStaffAppLoginVO(login_token);
                if (staffAdminLoginVO == null) {
                    WebUtil.outPrint(response, JSON.toJSONString(new Result(CodeEnum.FAIL_TOKEN_INVALID)));
                    return false;
                }
            }
        } else {
            WebUtil.outPrint(response, JSON.toJSONString(new Result(CodeEnum.FAIL_UNAUTHORIZED)));
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        long start_time = (Long) request.getAttribute("start_time");
        long end_time = System.currentTimeMillis();
        long consume_time = end_time - start_time;
        log.info("postHandle-response.getStatus()：" + response.getStatus());
        log.info("postHandle访问目标:" + method.getDeclaringClass().getName() + "." + method.getName() + "，消耗时间：" + consume_time + " ms");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        log.info("afterCompletion-response.getStatus()：" + response.getStatus());
        log.info("afterCompletion-getShortLogMessage：" + handlerMethod.getShortLogMessage());
        log.info("afterCompletion-访问目标：" + method.getDeclaringClass().getName() + "." + method.getName() + " 结束");
    }
}
