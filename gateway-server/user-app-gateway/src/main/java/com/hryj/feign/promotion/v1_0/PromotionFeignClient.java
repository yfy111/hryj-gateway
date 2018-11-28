package com.hryj.feign.promotion.v1_0;

import com.hryj.common.Result;
import com.hryj.entity.vo.PageResponseVO;
import com.hryj.entity.vo.RequestVO;
import com.hryj.entity.vo.promotion.activity.request.OnlyActivityIdRequestVO;
import com.hryj.entity.vo.promotion.activity.response.AppPromotionActivityDataResponseVO;
import com.hryj.entity.vo.promotion.activity.response.AppPromotionActivityResponseVO;
import com.hryj.entity.vo.promotion.advertisingposition.response.AppAdvertisingPositionResponseVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 汪豪
 * @className: PromotionFeignClient
 * @description: 促销服务feign接口
 * @create 2018/6/26 17:19
 **/
@FeignClient(name = "promotion-server")
public interface PromotionFeignClient {

    /**
     * @author 汪豪
     * @methodName: findPromotionActivity
     * @methodDesc: APP端加载促销活动
     * @description: 不分页
     * @param: [requestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.promotion.activity.response.AppPromotionActivityResponseVO>
     * @create 2018-06-28 19:44
     **/
    @RequestMapping("/v1-0/activityForApp/findPromotionActivity")
    Result<PageResponseVO<AppPromotionActivityResponseVO>> findPromotionActivity(@RequestBody RequestVO requestVO);

    /**
     * @author 汪豪
     * @methodName: getPromotionActivityData
     * @methodDesc: APP端加载活动的详细信息
     * @description:
     * @param: [onlyActivityIdRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.promotion.activity.response.AppPromotionActivityDataResponseVO>
     * @create 2018-06-29 11:52
     **/
    @RequestMapping("/v1-0/activityForApp/getPromotionActivityData")
    Result<AppPromotionActivityDataResponseVO> getPromotionActivityData(@RequestBody OnlyActivityIdRequestVO onlyActivityIdRequestVO);

    /**
     * @author 汪豪
     * @methodName: findAdvertisingPosition
     * @methodDesc: APP端加载广告位
     * @description:
     * @param: [requestVO]
     * @return com.hryj.common.Result<java.util.List<com.hryj.entity.vo.promotion.advertisingposition.response.AppAdvertisingPositionResponseVO>>
     * @create 2018-06-29 11:13
     **/
    @RequestMapping("/v1-0/advertisingForApp/findAdvertisingPosition")
    Result<PageResponseVO<AppAdvertisingPositionResponseVO>> findAdvertisingPosition(@RequestBody RequestVO requestVO);
}
