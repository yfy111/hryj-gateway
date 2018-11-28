package com.hryj.feign;

import com.hryj.common.Result;
import com.hryj.entity.vo.promotion.activity.request.OnlyActivityIdRequestVO;
import com.hryj.entity.vo.promotion.activity.response.AppPromotionActivityDataResponseVO;
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
     * @methodName: getPromotionActivityData
     * @methodDesc: APP端加载活动的详细信息
     * @description:
     * @param: [onlyActivityIdRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.promotion.activity.response.AppPromotionActivityDataResponseVO>
     * @create 2018-06-29 11:52
     **/
    @RequestMapping("/v1-1/activityForApp/getPromotionActivityData")
    Result<AppPromotionActivityDataResponseVO> getPromotionActivityData(@RequestBody OnlyActivityIdRequestVO onlyActivityIdRequestVO);
}
