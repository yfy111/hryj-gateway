package com.hryj.api;

import com.alibaba.fastjson.JSON;
import com.hryj.common.Result;
import com.hryj.entity.vo.staff.user.request.StaffAccountRequestVO;
import com.hryj.entity.vo.staff.user.request.StaffLoginRequestVO;
import com.hryj.entity.vo.staff.user.request.StaffSmsModifyPwdRequestVO;
import com.hryj.entity.vo.staff.user.response.AdminStaffLoginResponseVO;
import com.hryj.feign.StaffFeignClient;
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
 * @create 2018/6/26 17:34
 **/
@Api(value="/login", tags = "登录模块")
@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private StaffFeignClient staffFeignClient;

    /**
     * @author 李道云
     * @methodName: loginByPwd
     * @methodDesc: 员工登录
     * @description:
     * @param: [staffLoginRequestVO]
     * @return com.hryj.common.Result<AdminStaffLoginResponseVO>
     * @create 2018-07-05 11:59
     **/
    @ApiOperation(value = "员工登录", notes = "员工登录")
    @PostMapping("/loginByPwd")
    public Result<AdminStaffLoginResponseVO> loginByPwd(@RequestBody StaffLoginRequestVO staffLoginRequestVO){
        if(staffLoginRequestVO ==null){
            staffLoginRequestVO = new StaffLoginRequestVO();
        }
        WebUtil.getRequestVO(request, staffLoginRequestVO);
        log.info("员工登录：staffLoginRequestVO======" + JSON.toJSONString(staffLoginRequestVO));
        Result<AdminStaffLoginResponseVO> result = staffFeignClient.loginByPwd(staffLoginRequestVO);
        log.info("员工登录：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 李道云
     * @methodName: sendCodeForForgetLoginPwd
     * @methodDesc: 发送验证码(忘记密码)
     * @description:
     * @param: [staffAccountRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-27 22:59
     **/
    @ApiOperation(value = "发送验证码(忘记密码)", notes = "发送验证码(忘记密码)")
    @PostMapping("/sendCodeForForgetLoginPwd")
    public Result sendCodeForForgetLoginPwd(@RequestBody StaffAccountRequestVO staffAccountRequestVO){
        if(staffAccountRequestVO ==null){
            staffAccountRequestVO = new StaffAccountRequestVO();
        }
        WebUtil.getRequestVO(request,staffAccountRequestVO);
        log.info("发送验证码(忘记密码)：staffAccountRequestVO======" + JSON.toJSONString(staffAccountRequestVO));
        Result result = staffFeignClient.sendCodeForForgetLoginPwd(staffAccountRequestVO);
        log.info("发送验证码(忘记密码)：：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 李道云
     * @methodName: updateStaffLoginPwdBySms
     * @methodDesc: 短信验证修改登录密码
     * @description:
     * @param: [staffSmsModifyPwdRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-27 23:01
     **/
    @ApiOperation(value = "短信验证修改登录密码", notes = "短信验证修改登录密码")
    @PostMapping("/updateStaffLoginPwdBySms")
    public Result updateStaffLoginPwdBySms(@RequestBody StaffSmsModifyPwdRequestVO staffSmsModifyPwdRequestVO){
        if(staffSmsModifyPwdRequestVO ==null){
            staffSmsModifyPwdRequestVO = new StaffSmsModifyPwdRequestVO();
        }
        WebUtil.getRequestVO(request,staffSmsModifyPwdRequestVO);
        log.info("短信验证修改登录密码：staffSmsModifyPwdRequestVO======" + JSON.toJSONString(staffSmsModifyPwdRequestVO));
        Result result = staffFeignClient.updateStaffLoginPwdBySms(staffSmsModifyPwdRequestVO);
        log.info("短信验证修改登录密码：：result======" + JSON.toJSONString(result));
        return result;
    }


}
