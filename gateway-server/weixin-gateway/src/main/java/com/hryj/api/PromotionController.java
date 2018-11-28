package com.hryj.api;

import com.alibaba.fastjson.JSON;
import com.hryj.common.Result;
import com.hryj.entity.vo.promotion.activity.request.OnlyActivityIdRequestVO;
import com.hryj.entity.vo.promotion.activity.response.AppPromotionActivityDataResponseVO;
import com.hryj.feign.PromotionFeignClient;
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
 * @author 汪豪
 * @className: PromotionController
 * @description:
 * @create 2018/9/12 0012 10:48
 **/
@Api(value="/h5/promotion", tags = "营销")
@Slf4j
@RestController("PromotionController")
@RequestMapping("/h5/promotion")
public class PromotionController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PromotionFeignClient promotionFeignClient;

    /**
     * @author 王光银
     * @methodName: getPromotionActivityData
     * @methodDesc: APP端加载活动的详细信息和商品
     * @description:
     * @param: [onlyActivityIdRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.promotion.activity.response.AppRromotionActivityDataResponseVO>
     * @create 2018-06-28 20:04
     **/
    @ApiOperation(value = "查询活动的详细信息和商品", notes = "查询活动的详细信息和商品")
    @PostMapping("/getPromotionActivityData")
    public Result<AppPromotionActivityDataResponseVO> getPromotionActivityData(@RequestBody OnlyActivityIdRequestVO onlyActivityIdRequestVO) {
        WebUtil.getRequestVO(request,onlyActivityIdRequestVO);
        log.info("查询活动的详细信息和商品：onlyActivityIdRequestVO======" + JSON.toJSONString(onlyActivityIdRequestVO));
        Result<AppPromotionActivityDataResponseVO> result = promotionFeignClient.getPromotionActivityData(onlyActivityIdRequestVO);
        log.info("查询活动的详细信息和商品：result======" + JSON.toJSONString(result));
        return result;
    }
}
