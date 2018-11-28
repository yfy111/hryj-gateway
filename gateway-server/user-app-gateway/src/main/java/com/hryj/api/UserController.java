package com.hryj.api;

import com.alibaba.fastjson.JSON;
import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.RequestVO;
import com.hryj.entity.vo.user.UserAddressVO;
import com.hryj.entity.vo.user.request.DefaultPartyRequestVO;
import com.hryj.entity.vo.user.request.UserReferralCodeRequestVO;
import com.hryj.entity.vo.user.response.UserInfoResponseVO;
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
 * @className: UserController
 * @description: 用户模块
 * @create 2018-06-12 16:30
 **/
@Api(value="/user", tags = "用户模块")
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserFeignClient userFeignClient;

    /**
     * @author 李道云
     * @methodName: findUserInfoForApp
     * @methodDesc: 查询用户基本信息
     * @description:
     * @param: [requestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.user.response.UserInfoResponseVO>
     * @create 2018-08-29 9:53
     **/
    @ApiOperation(value="查询用户基本信息", notes = "查询用户基本信息")
    @PostMapping("/findUserInfo")
    public Result<UserInfoResponseVO> findUserInfo(){
        RequestVO requestVO = WebUtil.getRequestVO(request,null);
        log.info("查询用户基本信息：requestVO======" + JSON.toJSONString(requestVO));
        Result<UserInfoResponseVO> result = userFeignClient.findUserInfo(requestVO);
        log.info("查询用户基本信息：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 李道云
     * @methodName: findUserAddressList
     * @methodDesc:  查询用户地址列表
     * @description:
     * @param: []
     * @return com.hryj.common.Result<com.hryj.entity.vo.ListResponseVO<com.hryj.entity.vo.user.UserAddressVO>>
     * @create 2018-07-05 10:57
     **/
    @ApiOperation(value="查询用户地址列表", notes = "目前只会有一条地址，以后可能会多个地址")
    @PostMapping("/findUserAddressList")
    public Result<ListResponseVO<UserAddressVO>> findUserAddressList(){
        RequestVO requestVO = WebUtil.getRequestVO(request,null);
        log.info("查询用户地址信息：requestVO======" + JSON.toJSONString(requestVO));
        Result<ListResponseVO<UserAddressVO>> result = userFeignClient.findUserAddressList(requestVO);
        log.info("查询用户地址信息：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 李道云
     * @methodName: updateUserAddress
     * @methodDesc: 更新用户地址
     * @description:
     * @param: [userAddressVO]
     * @return com.hryj.common.Result
     * @create 2018-06-29 9:23
     **/
    @ApiOperation(value="更新用户地址", notes = "更新用户地址")
    @PostMapping("/updateUserAddress")
    public Result updateUserAddress(@RequestBody UserAddressVO userAddressVO){
        if(userAddressVO ==null){
            userAddressVO = new UserAddressVO();
        }
        WebUtil.getRequestVO(request,userAddressVO);
        log.info("更新用户地址:userAddressVO======" + JSON.toJSONString(userAddressVO));
        Result result = userFeignClient.updateUserAddress(userAddressVO);;
        log.info("更新用户地址:result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 李道云
     * @methodName: setDefaultParty
     * @methodDesc: 设置默认门店或仓库
     * @description:
     * @param: [defaultPartyRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-08-15 15:47
     **/
    @ApiOperation(value="设置默认门店或仓库", notes = "设置默认门店或仓库")
    @PostMapping("/setDefaultParty")
    public Result setDefaultParty(@RequestBody DefaultPartyRequestVO defaultPartyRequestVO){
        if(defaultPartyRequestVO ==null){
            defaultPartyRequestVO = new DefaultPartyRequestVO();
        }
        WebUtil.getRequestVO(request,defaultPartyRequestVO);
        log.info("设置默认门店或仓库:defaultPartyRequestVO======" + JSON.toJSONString(defaultPartyRequestVO));
        Result result = userFeignClient.setDefaultParty(defaultPartyRequestVO);;
        log.info("设置默认门店或仓库:result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 李道云
     * @methodName: setReferralCode
     * @methodDesc: 设置推荐码
     * @description:
     * @param: [userReferralCodeRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-08-29 9:55
     **/
    @ApiOperation(value="设置推荐码", notes = "设置推荐码")
    @PostMapping("/setReferralCode")
    public Result setReferralCode(@RequestBody UserReferralCodeRequestVO userReferralCodeRequestVO){
        if(userReferralCodeRequestVO ==null){
            userReferralCodeRequestVO = new UserReferralCodeRequestVO();
        }
        WebUtil.getRequestVO(request,userReferralCodeRequestVO);
        log.info("设置推荐码:userReferralCodeRequestVO======" + JSON.toJSONString(userReferralCodeRequestVO));
        Result result = userFeignClient.setReferralCode(userReferralCodeRequestVO);;
        log.info("设置推荐码:result======" + JSON.toJSONString(result));
        return result;
    }
}
