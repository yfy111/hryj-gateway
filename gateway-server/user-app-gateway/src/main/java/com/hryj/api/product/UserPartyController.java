package com.hryj.api.product;

import com.alibaba.fastjson.JSON;
import com.hryj.common.CodeEnum;
import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.userparty.request.UserPartyRequestVO;
import com.hryj.entity.vo.userparty.response.UserPartyResponseItem;
import com.hryj.exception.ServerException;
import com.hryj.feign.product.v1_1.ProductFeignClient;
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
 * @author 王光银
 * @className: UserPartyController
 * @description: 用户与门店关系接口
 * @create 2018-08-16 9:25
 **/
@Api(value = "/user/party", tags = "APP用户端 - 用户与门店 (V1.1新增)")
@Slf4j
@RestController
@RequestMapping("/user/party")
public class UserPartyController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ProductFeignClient productFeignClient;

    @ApiOperation(value = "APP门店端查询覆盖用户的门店与仓库列表", notes = "请使用返回数据集合中的第一个作为默认选中门店")
    @PostMapping("/findAroundPartyList")
    public Result<ListResponseVO<UserPartyResponseItem>> findAroundPartyList(@RequestBody(required = false) UserPartyRequestVO userPartyRequestVO) throws ServerException {
        if (userPartyRequestVO == null) {
            userPartyRequestVO = new UserPartyRequestVO();
        }
        StringBuilder json_str = new StringBuilder();
        try {
            WebUtil.getRequestVO(request, userPartyRequestVO);
            json_str.append("v1.1 - APP用户端查询覆盖用户的门店与仓库列表 - request:");
            json_str.append(JSON.toJSONString(userPartyRequestVO));
            json_str.append(System.lineSeparator());
            Result result = productFeignClient.findAroundPartyList(userPartyRequestVO);
            json_str.append("v1.1 - APP用户端查询覆盖用户的门店与仓库列表 - response:");
            json_str.append(JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            log.error("v1.1 - APP用户端查询覆盖用户的门店与仓库列表", e);
            return new Result<>(CodeEnum.FAIL_SERVER, "系统繁忙请稍后再试");
        } finally {
            log.info(json_str.toString());
        }
    }

}
