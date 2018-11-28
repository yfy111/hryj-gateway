package com.hryj.api;

import com.alibaba.fastjson.JSON;
import com.hryj.common.CodeEnum;
import com.hryj.common.Result;
import com.hryj.entity.vo.user.request.UserLoginRequestVO;
import com.hryj.entity.vo.user.request.UserRegisterRequestVO;
import com.hryj.entity.vo.user.request.UserRegisterVerifyRequestVO;
import com.hryj.entity.vo.user.response.UserLoginResponseVO;
import com.hryj.feign.UserFeignClient;
import com.hryj.utils.WebUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 李道云
 * @className: LoginController
 * @description: 登录模块
 * @create 2018/7/2 21:04
 **/
@Api(value="/login", tags = "登录模块", consumes = "application/json; charset=utf8")
@Slf4j
@RestController
@RequestMapping(value = "/login", consumes = "application/json; charset=utf8")
public class LoginController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserFeignClient userFeignClient;

    /**
     * @author 李道云
     * @description: 用户登录
     * @param: [userLoginRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.user.response.UserLoginResponseVO>
     * @create 2018-06-26 15:11
     **/
    @ApiOperation(value="用户登录", notes = "用户登录")
    @PostMapping(value = "/loginBySmsCode", consumes = "application/json; charset=utf8")
    public Result<UserLoginResponseVO> loginBySmsCode(@RequestBody UserLoginRequestVO userLoginRequestVO){
        if(userLoginRequestVO ==null){
            userLoginRequestVO = new UserLoginRequestVO();
        }
        WebUtil.getRequestVO(request,userLoginRequestVO);
        log.info("用户登录：userLoginRequestVO======" + JSON.toJSONString(userLoginRequestVO));
        if("temporary".equals(userLoginRequestVO.getApp_channel())){
            return new Result(CodeEnum.FAIL_FORBIDDEN,"培训包已下线，请重新安装最新版本");
        }
        Result<UserLoginResponseVO> result = userFeignClient.loginBySmsCode(userLoginRequestVO);
        log.info("用户登录：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 李道云
     * @methodName: verifyRegisterUser
     * @methodDesc: 验证注册用户
     * @description:
     * @param: [userRegisterVerifyRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-08-16 11:56
     **/
    @ApiOperation(value="验证注册用户", notes = "验证注册用户")
    @PostMapping("/verifyRegisterUser")
    public Result verifyRegisterUser(@RequestBody UserRegisterVerifyRequestVO userRegisterVerifyRequestVO){
        if(userRegisterVerifyRequestVO ==null){
            userRegisterVerifyRequestVO = new UserRegisterVerifyRequestVO();
        }
        WebUtil.getRequestVO(request,userRegisterVerifyRequestVO);
        log.info("验证注册用户：userRegisterVerifyRequestVO======" + JSON.toJSONString(userRegisterVerifyRequestVO));
        Result<UserLoginResponseVO> result = userFeignClient.verifyRegisterUser(userRegisterVerifyRequestVO);
        log.info("验证注册用户：result======" + JSON.toJSONString(result));
        return result;
    }
    /**
     * @author 李道云
     * @description:
     * @param: [userRegisterRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.user.response.UserLoginResponseVO>
     * @create 2018-06-26 15:11
     **/
    @ApiOperation(value="用户注册", notes = "用户注册")
    @PostMapping("/register")
    public Result<UserLoginResponseVO> register(@RequestBody UserRegisterRequestVO userRegisterRequestVO) {
        if(userRegisterRequestVO ==null){
            userRegisterRequestVO = new UserRegisterRequestVO();
        }
        WebUtil.getRequestVO(request,userRegisterRequestVO);
        log.info("用户注册：userRegisterRequestVO======" + JSON.toJSONString(userRegisterRequestVO));
        Result<UserLoginResponseVO> result = userFeignClient.register(userRegisterRequestVO);
        log.info("用户注册：result======" + JSON.toJSONString(userRegisterRequestVO));
        return result;
    }

}
