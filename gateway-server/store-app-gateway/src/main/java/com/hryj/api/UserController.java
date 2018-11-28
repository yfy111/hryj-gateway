package com.hryj.api;

import com.alibaba.fastjson.JSON;
import com.hryj.common.Result;
import com.hryj.entity.vo.user.request.UserPhoneRequestVO;
import com.hryj.entity.vo.user.response.UserSearchResponseVO;
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
 * @create 2018/6/30 17:51
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
     * @methodName: searchUserInfo
     * @methodDesc: 搜索用户信息(代下单)
     * @description:
     * @param: [userPhoneRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.user.response.UserSearchResponseVO>
     * @create 2018-07-05 10:35
     **/
    @PostMapping("/searchUserInfo")
    @ApiOperation(value="搜索用户信息",notes = "搜索用户信息(代下单)")
    public Result<UserSearchResponseVO> searchUserInfo(@RequestBody UserPhoneRequestVO userPhoneRequestVO){
        if(userPhoneRequestVO ==null){
            userPhoneRequestVO = new UserPhoneRequestVO();
        }
        WebUtil.getRequestVO(request,userPhoneRequestVO);
        log.info("搜索用户信息(代下单)：userSearchRequestVO======" + JSON.toJSONString(userPhoneRequestVO));
        Result<UserSearchResponseVO> result = userFeignClient.searchUserInfo(userPhoneRequestVO);
        log.info("搜索用户信息(代下单)：UserSearchResponseVO======" + JSON.toJSONString(result));
        return result;
    }
}
