package com.hryj.feign;

import com.hryj.common.Result;
import com.hryj.entity.vo.PageResponseVO;
import com.hryj.entity.vo.profit.request.*;
import com.hryj.entity.vo.profit.response.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 李道云
 * @className: ProfitFeignClient
 * @description: 分润feign接口
 * @create 2018/7/10 8:45
 **/
@FeignClient(name = "profit-server")
public interface ProfitFeignClient {

    /**
     * @author 李道云
     * @methodName: searchRegionProfitList
     * @methodDesc: 分页查询区域分润列表
     * @description:
     * @param: [regionProfitRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.PageResponseVO<com.hryj.entity.vo.profit.response.RegionBalanceVO>>
     * @create 2018-07-10 8:49
     **/
    @RequestMapping(value = "/profitAdmin/searchRegionProfitList", method = RequestMethod.POST)
    Result<PageResponseVO<RegionBalanceVO>> searchRegionProfitList(@RequestBody RegionProfitRequestVO regionProfitRequestVO);

    /**
     * @author 李道云
     * @methodName: searchRegionProfitDetailList
     * @methodDesc: 分页查询区域分润明细列表
     * @description:
     * @param: [regionProfitDetailRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.PageResponseVO<com.hryj.entity.vo.profit.response.RegionBalanceDetailVO>>
     * @create 2018-07-10 8:49
     **/
    @RequestMapping(value = "/profitAdmin/searchRegionProfitDetailList", method = RequestMethod.POST)
    Result<PageResponseVO<RegionBalanceDetailVO>> searchRegionProfitDetailList(@RequestBody RegionProfitDetailRequestVO regionProfitDetailRequestVO);

    /**
     * @author 李道云
     * @methodName: updateRegionNonFixedCost
     * @methodDesc: 更新区域公司非固定成本
     * @description:
     * @param: [nonFixedCostSetRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-10 8:52
     **/
    @RequestMapping(value = "/profitAdmin/updateRegionNonFixedCost", method = RequestMethod.POST)
    Result updateRegionNonFixedCost(@RequestBody NonFixedCostSetRequestVO nonFixedCostSetRequestVO);

    /**
     * @author 李道云
     * @methodName: searchStoreProfitList
     * @methodDesc: 分页查询门店分润列表
     * @description:
     * @param: [storeProfitRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.PageResponseVO<com.hryj.entity.vo.profit.response.StoreBalanceVO>>
     * @create 2018-07-10 8:53
     **/
    @RequestMapping(value = "/profitAdmin/searchStoreProfitList", method = RequestMethod.POST)
    Result<PageResponseVO<StoreBalanceVO>> searchStoreProfitList(@RequestBody StoreProfitRequestVO storeProfitRequestVO);

    /**
     * @author 李道云
     * @methodName: findStoreProfitDetailList
     * @methodDesc: 查询门店分润明细数据
     * @description:
     * @param: [storeProfitDetailRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.profit.response.StoreProfitDetailResponseVO>
     * @create 2018-07-10 8:54
     **/
    @RequestMapping(value = "/profitAdmin/findStoreProfitDetailList", method = RequestMethod.POST)
    Result<StoreProfitDetailResponseVO> findStoreProfitDetailList(@RequestBody StoreProfitDetailRequestVO storeProfitDetailRequestVO);

    /**
     * @author 李道云
     * @methodName: updateStoreNonFixedCost
     * @methodDesc: 更新门店非固定成本
     * @description:
     * @param: [nonFixedCostSetRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-10 8:55
     **/
    @RequestMapping(value = "/profitAdmin/updateStoreNonFixedCost", method = RequestMethod.POST)
    Result updateStoreNonFixedCost(@RequestBody NonFixedCostSetRequestVO nonFixedCostSetRequestVO);

    /**
     * @author 李道云
     * @methodName: searchStoreManagerProfitList
     * @methodDesc: 分页查询店长分润列表
     * @description:
     * @param: [storeManagerProfitRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.PageResponseVO<com.hryj.entity.vo.profit.response.StoreManagerProfitVO>>
     * @create 2018-07-10 8:56
     **/
    @RequestMapping(value = "/profitAdmin/searchStoreManagerProfitList", method = RequestMethod.POST)
    Result<PageResponseVO<StoreManagerProfitVO>> searchStoreManagerProfitList(@RequestBody StoreManagerProfitRequestVO storeManagerProfitRequestVO);

}
