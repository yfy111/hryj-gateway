package com.hryj.feign;

import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.RequestVO;
import com.hryj.entity.vo.staff.team.response.AppTeamDataResponseVO;
import com.hryj.entity.vo.staff.user.StaffUserVO;
import com.hryj.entity.vo.staff.user.request.StaffAccountRequestVO;
import com.hryj.entity.vo.staff.user.request.StaffLoginRequestVO;
import com.hryj.entity.vo.staff.user.request.StaffModifyPwdRequestVO;
import com.hryj.entity.vo.staff.user.request.StaffSmsModifyPwdRequestVO;
import com.hryj.entity.vo.staff.user.response.AppStaffLoginResponseVO;
import com.hryj.entity.vo.staff.user.response.StaffUserInfoVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 李道云
 * @className: StaffFeignClient
 * @description: 员工服务feign接口
 * @create 2018/6/26 17:25
 **/
@FeignClient(name = "staff-server")
public interface StaffFeignClient {

    /**
     * @author 李道云
     * @methodName: loginByPwd
     * @methodDesc: 员工登录
     * @description:
     * @param: [staffLoginRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.staff.user.response.AppStaffLoginResponseVO>
     * @create 2018-07-02 22:37
     **/
    @RequestMapping(value = "/staffApp/loginByPwd", method = RequestMethod.POST)
    Result<AppStaffLoginResponseVO> loginByPwd(StaffLoginRequestVO staffLoginRequestVO);

    /**
     * @author 李道云
     * @methodName: updateStaffLoginPwd
     * @methodDesc: 修改密码
     * @description:
     * @param: [staffModifyPwdRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.staff.user.response.AppStaffLoginResponseVO>
     * @create 2018-07-02 22:37
     **/
    @RequestMapping(value = "/staffApp/updateStaffLoginPwd", method = RequestMethod.POST)
    Result<AppStaffLoginResponseVO> updateStaffLoginPwd(StaffModifyPwdRequestVO staffModifyPwdRequestVO);

    /**
     * @author 李道云
     * @methodName: findStaffUserInfoVO
     * @methodDesc: 查询员工基本信息
     * @description:
     * @param: [requestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.staff.user.response.StaffUserInfoVO>
     * @create 2018-07-16 16:49
     **/
    @RequestMapping(value = "/staffApp/findStaffUserInfoVO", method = RequestMethod.POST)
    Result<StaffUserInfoVO> findStaffUserInfoVO(@RequestBody RequestVO requestVO);

    /**
     * @author 李道云
     * @methodName: sendCodeForForgetLoginPwd
     * @methodDesc: 发送验证码(忘记密码)
     * @description:
     * @param: [staffAccountRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-05 11:38
     **/
    @RequestMapping(value = "/staff/sendCodeForForgetLoginPwd", method = RequestMethod.POST)
    Result sendCodeForForgetLoginPwd(@RequestBody StaffAccountRequestVO staffAccountRequestVO);

    /**
     * @author 李道云
     * @methodName: updateStaffLoginPwdBySms
     * @methodDesc: 短信验证修改登录密码
     * @description:
     * @param: [staffAccountRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-05 11:38
     **/
    @RequestMapping(value = "/staff/updateStaffLoginPwdBySms", method = RequestMethod.POST)
    Result updateStaffLoginPwdBySms(@RequestBody StaffSmsModifyPwdRequestVO staffSmsModifyPwdRequestVO);

    /**
     * @author 代廷波
     * @methodName: findStoreStaffList
     * @methodDesc: 查询门店的员工列表
     * @description:
     * @param: []
     * @return com.hryj.common.Result
     * @create 2018-07-05 12:36
     **/
    @RequestMapping(value = "/staffApp/findStoreStaffList", method = RequestMethod.POST)
    Result<ListResponseVO<StaffUserVO>> findStoreStaffList(@RequestBody RequestVO requestVO);

    /**
     * @author 代廷波
     * @methodName: getTeamData
     * @methodDesc: 获取部门组织数据
     * @description:
     * @param: [requestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.staff.team.response.AppTeamDataResponseVO>
     * @create 2018-07-21 17:44
     **/
    @PostMapping("/app/dept/getTeamData")
    Result<AppTeamDataResponseVO> getTeamData(@RequestBody RequestVO requestVO);

}
