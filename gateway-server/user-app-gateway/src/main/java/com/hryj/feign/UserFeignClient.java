package com.hryj.feign;

import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.RequestVO;
import com.hryj.entity.vo.user.UserAddressVO;
import com.hryj.entity.vo.user.request.*;
import com.hryj.entity.vo.user.response.UserInfoResponseVO;
import com.hryj.entity.vo.user.response.UserLoginResponseVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 李道云
 * @className: UserFeignClient
 * @description: 用户服务feign接口
 * @create 2018/6/26 13:48
 **/
@FeignClient(name = "user-server")
public interface UserFeignClient {

    /**
     * @author 李道云
     * @description: 用户登录
     * @param: [userLoginRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.user.response.UserLoginResponseVO>
     * @create 2018-06-26 13:53
     **/
    @RequestMapping(value = "/userApp/loginBySmsCode", method = RequestMethod.POST)
    Result<UserLoginResponseVO> loginBySmsCode(@RequestBody UserLoginRequestVO userLoginRequestVO);

    /**
     * @author 李道云
     * @methodName: verifyRegisterUser
     * @methodDesc: 验证注册用户
     * @description:
     * @param: [userRegisterVerifyRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-08-16 11:56
     **/
    @RequestMapping(value = "/userApp/verifyRegisterUser", method = RequestMethod.POST)
    Result verifyRegisterUser(@RequestBody UserRegisterVerifyRequestVO userRegisterVerifyRequestVO);

    /**
     * @author 李道云
     * @description: 用户注册
     * @param: [userRegisterRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.user.response.UserLoginResponseVO>
     * @create 2018-06-26 13:53
     **/
    @RequestMapping(value = "/userApp/register", method = RequestMethod.POST)
    Result<UserLoginResponseVO> register(@RequestBody UserRegisterRequestVO userRegisterRequestVO);

    /**
     * @author 李道云
     * @methodName: findUserInfo
     * @methodDesc: 查询用户基本信息
     * @description:
     * @param: [requestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.user.response.UserInfoResponseVO>
     * @create 2018-08-29 9:53
     **/
    @RequestMapping(value = "/userApp/findUserInfo", method = RequestMethod.POST)
    Result<UserInfoResponseVO> findUserInfo(@RequestBody RequestVO requestVO);

    /**
     * @author 李道云
     * @methodName: findUserAddressList
     * @methodDesc: 查询用户地址列表
     * @description:
     * @param: [requestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-02 19:45
     **/
    @RequestMapping(value = "/userApp/findUserAddressList", method = RequestMethod.POST)
    Result<ListResponseVO<UserAddressVO>> findUserAddressList(@RequestBody RequestVO requestVO);

    /**
     * @author 李道云
     * @methodName: updateUserAddress
     * @methodDesc: 更新用户地址信息
     * @description:
     * @param: [userAddressVO]
     * @return com.hryj.common.Result
     * @create 2018-07-02 19:48
     **/
    @RequestMapping(value = "/userApp/updateUserAddress", method = RequestMethod.POST)
    Result updateUserAddress(@RequestBody UserAddressVO userAddressVO);

    /**
     * @author 李道云
     * @methodName: setDefaultParty
     * @methodDesc: 设置默认门店或仓库
     * @description:
     * @param: [defaultPartyRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-08-15 15:47
     **/
    @RequestMapping(value = "/userApp/setDefaultParty", method = RequestMethod.POST)
    Result setDefaultParty(@RequestBody DefaultPartyRequestVO defaultPartyRequestVO);

    /**
     * @author 李道云
     * @methodName: setReferralCode
     * @methodDesc: 设置推荐码
     * @description:
     * @param: [userReferralCodeRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-08-29 9:55
     **/
    @RequestMapping(value = "/userApp/setReferralCode", method = RequestMethod.POST)
    Result setReferralCode(@RequestBody UserReferralCodeRequestVO userReferralCodeRequestVO);

}
