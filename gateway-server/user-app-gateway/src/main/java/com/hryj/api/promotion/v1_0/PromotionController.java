package com.hryj.api.promotion.v1_0;

import com.alibaba.fastjson.JSON;
import com.hryj.common.Result;
import com.hryj.entity.vo.PageResponseVO;
import com.hryj.entity.vo.RequestVO;
import com.hryj.entity.vo.promotion.activity.request.OnlyActivityIdRequestVO;
import com.hryj.entity.vo.promotion.activity.response.AppPromotionActivityDataResponseVO;
import com.hryj.entity.vo.promotion.activity.response.AppPromotionActivityResponseVO;
import com.hryj.entity.vo.promotion.advertisingposition.response.AppAdvertisingPositionResponseVO;
import com.hryj.feign.promotion.v1_0.PromotionFeignClient;
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
 * @className: PromotionController
 * @description: 营销模块
 * @create 2018/6/28 21:15
 **/
@Api(value="/promotion", tags = "营销 - 用户端 - V1.0")
@Slf4j
@RestController("v1.0-PromotionController")
@RequestMapping("/promotion")
public class PromotionController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PromotionFeignClient promotionFeignClient;

    /**
     * @author 王光银
     * @methodName: findPromotionActivity
     * @methodDesc: APP端加载促销活动
     * @description: 不分页
     * @param: [requestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.promotion.activity.response.AppPromotionActivityResponseVO>
     * @create 2018-06-28 19:44
     **/
    @ApiOperation(value = "APP端加载促销活动", notes = "APP端加载促销活动,不分页")
    @PostMapping("/findPromotionActivity")
    public Result<PageResponseVO<AppPromotionActivityResponseVO>> findPromotionActivity() {
        RequestVO requestVO = WebUtil.getRequestVO(request,null);
        Result<PageResponseVO<AppPromotionActivityResponseVO>> result = promotionFeignClient.findPromotionActivity(requestVO);
        log.info("APP端加载促销活动：result======" + JSON.toJSONString(result));
        return result;
    }

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

    /**
     * @author 王光银
     * @methodName: findAdvertisingPosition
     * @methodDesc: APP端加载广告位
     * @description:
     * @param: []
     * @return com.hryj.common.Result<java.util.List<com.hryj.entity.vo.promotion.advertisingposition.response.AppAdvertisingPositionResponseVO>>
     * @create 2018-06-29 11:13
     **/
    @ApiOperation(value = "APP端加载广告位", notes = "APP端加载广告位")
    @PostMapping("/findAdvertisingPosition")
    public Result<PageResponseVO<AppAdvertisingPositionResponseVO>> findAdvertisingPosition() {
        RequestVO requestVO = WebUtil.getRequestVO(request, null);
        Result<PageResponseVO<AppAdvertisingPositionResponseVO>> result = promotionFeignClient.findAdvertisingPosition(requestVO);
        log.info("APP端加载广告位：result======" + JSON.toJSONString(result));
        return result;
    }
}
