package com.hryj.feign;

import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.RequestVO;
import com.hryj.entity.vo.promotion.indexNavigation.request.IndexNavigationListRequestVO;
import com.hryj.entity.vo.promotion.indexNavigation.response.IndexNavigationResponseVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 汪豪
 * @className: IndexNavigationFeignClient
 * @description:
 * @create 2018/8/24 0024 8:47
 **/
@FeignClient(name = "promotion-server")
public interface IndexNavigationFeignClient {

    /**
     * @author 汪豪
     * @methodName: createOrUpdateIndexNavigation
     * @methodDesc:
     * @description:
     * @param: [indexNavigationListRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-08-23 14:15
     **/
    @RequestMapping("/indexNavigationMgr/createOrUpdateIndexNavigation")
    Result createOrUpdateIndexNavigation(@RequestBody IndexNavigationListRequestVO indexNavigationListRequestVO);

    /**
     * @author 汪豪
     * @methodName: showIndexNavigation
     * @methodDesc:
     * @description:
     * @param: []
     * @return com.hryj.common.Result<com.hryj.entity.vo.ListResponseVO<com.hryj.entity.vo.promotion.indexNavigation.response.IndexNavigationResponseVO>>
     * @create 2018-08-23 14:43
     **/
    @RequestMapping("/indexNavigationMgr/showIndexNavigation")
    Result<ListResponseVO<IndexNavigationResponseVO>> showIndexNavigation(@RequestBody RequestVO requestVO);
}
