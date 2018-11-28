package com.hryj.api.promotion.v1_1;

import com.alibaba.fastjson.JSON;
import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.PageResponseVO;
import com.hryj.entity.vo.RequestVO;
import com.hryj.entity.vo.promotion.activity.request.OnlyActivityIdRequestVO;
import com.hryj.entity.vo.promotion.activity.response.AppPromotionActivityDataResponseVO;
import com.hryj.entity.vo.promotion.activity.response.AppPromotionActivityResponseVO;
import com.hryj.entity.vo.promotion.advertisingposition.response.AppAdvertisingPositionResponseVO;
import com.hryj.entity.vo.promotion.indexNavigation.response.IndexNavigationResponseVO;
import com.hryj.feign.promotion.v1_1.PromotionFeignClient;
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
import java.util.List;

/**
 * @author 李道云
 * @className: PromotionController
 * @description: 营销模块
 * @create 2018/6/28 21:15
 **/
@Api(value="/v1-1/promotion", tags = "营销 - 用户端 - V1.1")
@Slf4j
@RestController("v1.1-PromotionController")
@RequestMapping("/v1-1/promotion")
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

    /**
     * @author 汪豪
     * @methodName: findIndexNavigation
     * @methodDesc: APP端加载首页导航
     * @description:
     * @param: [requestVO]
     * @return com.hryj.common.Result<java.util.List<com.hryj.entity.vo.promotion.indexNavigation.response.IndexNavigationResponseVO>>
     * @create 2018-08-23 16:42
     **/
    @ApiOperation(value = "APP端加载首页导航位", notes = "APP端加载首页导航位")
    @PostMapping("/findIndexNavigation")
    public Result<ListResponseVO<IndexNavigationResponseVO>> findIndexNavigation(){
        RequestVO requestVO = WebUtil.getRequestVO(request, null);
        Result<ListResponseVO<IndexNavigationResponseVO>> result = promotionFeignClient.findIndexNavigation(requestVO);
        log.info("APP端加载首页导航位：result======" + JSON.toJSONString(result));
        return result;
    }
}
