package com.hryj.feign;

import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.profit.request.DataQueryRequestVO;
import com.hryj.entity.vo.user.UserInfoVO;
import com.hryj.entity.vo.user.request.UserPhoneRequestVO;
import com.hryj.entity.vo.user.response.UserSearchResponseVO;
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
     * @methodName: searchUserInfo
     * @methodDesc: 搜索用户信息
     * @description:
     * @param: [userPhoneRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.user.response.UserSearchResponseVO>
     * @create 2018-07-05 10:35
     **/
    @RequestMapping(value = "/userStore/searchUserInfo", method = RequestMethod.POST)
    Result<UserSearchResponseVO> searchUserInfo(@RequestBody UserPhoneRequestVO userPhoneRequestVO);

    /**
     * @author 李道云
     * @methodName: findReferralRegisterUserList
     * @methodDesc: 查询推荐注册用户列表
     * @description:
     * @param: [dataQueryRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.ListResponseVO<com.hryj.entity.vo.user.UserInfoVO>>
     * @create 2018-07-07 17:34
     **/
    @RequestMapping(value = "/userStore/findReferralRegisterUserList", method = RequestMethod.POST)
    Result<ListResponseVO<UserInfoVO>> findReferralRegisterUserList(@RequestBody DataQueryRequestVO dataQueryRequestVO);

}
