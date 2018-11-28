package com.hryj.api;

import com.alibaba.fastjson.JSON;
import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.RequestVO;
import com.hryj.entity.vo.staff.user.StaffUserVO;
import com.hryj.entity.vo.staff.user.request.StaffModifyPwdRequestVO;
import com.hryj.entity.vo.staff.user.response.AppStaffLoginResponseVO;
import com.hryj.entity.vo.staff.user.response.StaffUserInfoVO;
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
 * @className: StaffController
 * @description: 门店服务API
 * @create 2018/6/26 17:08
 **/
@Api(value="/staff", tags = "员工模块")
@Slf4j
@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private StaffFeignClient staffFeignClient;

    /**
     * @author 李道云
     * @methodName: updateStaffLoginPwd
     * @methodDesc: 修改登录密码
     * @description:
     * @param: [staffModifyPwdRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.staff.user.response.AppStaffLoginResponseVO>
     * @create 2018-07-02 22:42
     **/
    @ApiOperation(value = "修改登录密码", notes = "修改登录密码")
    @PostMapping("/updateStaffLoginPwd")
    public Result<AppStaffLoginResponseVO> updateStaffLoginPwd(@RequestBody StaffModifyPwdRequestVO staffModifyPwdRequestVO){
        if(staffModifyPwdRequestVO ==null){
            staffModifyPwdRequestVO = new StaffModifyPwdRequestVO();
        }
        WebUtil.getRequestVO(request,staffModifyPwdRequestVO);
        log.info("修改登录密码：appStaffModifyPwdRequestVO======" + JSON.toJSONString(staffModifyPwdRequestVO));
        Result<AppStaffLoginResponseVO> result = staffFeignClient.updateStaffLoginPwd(staffModifyPwdRequestVO);
        log.info("修改登录密码：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 李道云
     * @methodName: findStaffUserInfoVO
     * @methodDesc: 查询员工基本信息
     * @description:
     * @param: []
     * @return com.hryj.common.Result<com.hryj.entity.vo.staff.user.response.StaffUserInfoVO>
     * @create 2018-07-16 16:50
     **/
    @ApiOperation(value = "查询员工基本信息", notes = "查询员工基本信息")
    @PostMapping("/findStaffUserInfoVO")
    public Result<StaffUserInfoVO> findStaffUserInfoVO(){
        RequestVO requestVO = WebUtil.getRequestVO(request,null);
        log.info("查询员工基本信息：requestVO======" + JSON.toJSONString(requestVO));
        Result<StaffUserInfoVO> result = staffFeignClient.findStaffUserInfoVO(requestVO);
        log.info("查询员工基本信息：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 李道云
     * @methodName: findStoreStaffList
     * @methodDesc: 查询门店的员工列表
     * @description:
     * @param: []
     * @return com.hryj.common.Result
     * @create 2018-07-05 12:36
     **/
    @ApiOperation(value = "查询门店的员工列表", notes = "查询门店的员工列表")
    @PostMapping("/findStoreStaffList")
    public Result<ListResponseVO<StaffUserVO>> findStoreStaffList(){
        RequestVO requestVO = WebUtil.getRequestVO(request,null);
        log.info("查询门店的员工列表：requestVO======" + JSON.toJSONString(requestVO));
        Result<ListResponseVO<StaffUserVO>> result = staffFeignClient.findStoreStaffList(requestVO);
        log.info("查询门店的员工列表：result======" + JSON.toJSONString(result));
        return result;
    }
}
