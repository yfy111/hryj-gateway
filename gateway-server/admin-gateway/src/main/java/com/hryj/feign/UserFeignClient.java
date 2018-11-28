package com.hryj.feign;

import com.hryj.common.Result;
import com.hryj.entity.vo.PageResponseVO;
import com.hryj.entity.vo.user.UserInfoVO;
import com.hryj.entity.vo.user.request.UserListRequestVO;
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
     * @methodName: searchUserList
     * @methodDesc: 分页查询用户列表
     * @description:
     * @param: [userListRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.PageResponseVO<com.hryj.entity.vo.user.UserInfoVO>>
     * @create 2018-07-09 9:19
     **/
    @RequestMapping(value = "/userAdmin/searchUserList", method = RequestMethod.POST)
    Result<PageResponseVO<UserInfoVO>> searchUserList(@RequestBody UserListRequestVO userListRequestVO);

}
