package com.hryj.api;

import com.alibaba.fastjson.JSON;
import com.hryj.common.Result;
import com.hryj.entity.vo.PageResponseVO;
import com.hryj.entity.vo.user.UserInfoVO;
import com.hryj.entity.vo.user.request.UserListRequestVO;
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
     * @methodName: searchUserList
     * @methodDesc: 分页查询用户列表
     * @description:
     * @param: [userListRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.user.response.UserListResponseVO>
     * @create 2018-06-27 22:17
     **/
    @ApiOperation(value="分页查询用户列表")
    @PostMapping("/searchUserList")
    public Result<PageResponseVO<UserInfoVO>> searchUserList(@RequestBody UserListRequestVO userListRequestVO){
        if(userListRequestVO ==null){
            userListRequestVO = new UserListRequestVO();
        }
        WebUtil.getRequestVO(request, userListRequestVO);
        log.info("分页查询用户列表：userListRequestVO======" + JSON.toJSONString(userListRequestVO));
        Result<PageResponseVO<UserInfoVO>> result = userFeignClient.searchUserList(userListRequestVO);
        log.info("分页查询用户列表：result======" + JSON.toJSONString(result));
        return result;
    }
}
