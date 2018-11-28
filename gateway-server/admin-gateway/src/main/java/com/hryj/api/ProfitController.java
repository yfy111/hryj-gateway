package com.hryj.api;

import com.alibaba.fastjson.JSON;
import com.hryj.common.Result;
import com.hryj.entity.vo.PageResponseVO;
import com.hryj.entity.vo.profit.request.*;
import com.hryj.entity.vo.profit.response.*;
import com.hryj.feign.ProfitFeignClient;
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
 * @className: ProfitController
 * @description: 分润模块
 * @create 2018/7/10 8:43
 **/
@Api(value="/profit", tags = "分润模块")
@Slf4j
@RestController
@RequestMapping("/profit")
public class ProfitController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ProfitFeignClient profitFeignClient;

    /**
     * @author 李道云
     * @methodName: searchRegionProfitList
     * @methodDesc: 分页查询区域分润列表
     * @description:
     * @param: [regionProfitRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.PageResponseVO<com.hryj.entity.vo.profit.response.RegionBalanceVO>>
     * @create 2018-07-09 20:31
     **/
    @ApiOperation(value="分页查询区域分润列表",notes = "分页查询区域分润列表")
    @PostMapping("/searchRegionProfitList")
    public Result<PageResponseVO<RegionBalanceVO>> searchRegionProfitList(@RequestBody RegionProfitRequestVO regionProfitRequestVO){
        if(regionProfitRequestVO ==null){
            regionProfitRequestVO = new RegionProfitRequestVO();
        }
        WebUtil.getRequestVO(request,regionProfitRequestVO);
        log.info("分页查询区域分润列表：regionProfitRequestVO======" + JSON.toJSONString(regionProfitRequestVO));
        Result<PageResponseVO<RegionBalanceVO>> result = profitFeignClient.searchRegionProfitList(regionProfitRequestVO);
        log.info("分页查询区域分润列表：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 李道云
     * @methodName: searchRegionProfitDetailList
     * @methodDesc: 分页查询区域分润明细列表
     * @description:
     * @param: [regionProfitDetailRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.PageResponseVO<com.hryj.entity.vo.profit.response.RegionBalanceDetailVO>>
     * @create 2018-07-09 20:52
     **/
    @ApiOperation(value="分页查询区域分润明细列表",notes = "分页查询区域分润明细列表")
    @PostMapping("/searchRegionProfitDetailList")
    public Result<PageResponseVO<RegionBalanceDetailVO>> searchRegionProfitDetailList(@RequestBody RegionProfitDetailRequestVO regionProfitDetailRequestVO){
        if(regionProfitDetailRequestVO ==null){
            regionProfitDetailRequestVO = new RegionProfitDetailRequestVO();
        }
        WebUtil.getRequestVO(request,regionProfitDetailRequestVO);
        log.info("分页查询区域分润明细列表：regionProfitDetailRequestVO======" + JSON.toJSONString(regionProfitDetailRequestVO));
        Result<PageResponseVO<RegionBalanceDetailVO>> result = profitFeignClient.searchRegionProfitDetailList(regionProfitDetailRequestVO);
        log.info("分页查询区域分润明细列表：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 李道云
     * @methodName: updateRegionNonFixedCost
     * @methodDesc: 更新区域公司非固定成本
     * @description:
     * @param: [nonFixedCostSetRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-09 21:05
     **/
    @ApiOperation(value="更新区域公司非固定成本",notes = "更新区域公司非固定成本")
    @PostMapping("/updateRegionNonFixedCost")
    public Result updateRegionNonFixedCost(@RequestBody NonFixedCostSetRequestVO nonFixedCostSetRequestVO){
        if(nonFixedCostSetRequestVO ==null){
            nonFixedCostSetRequestVO = new NonFixedCostSetRequestVO();
        }
        WebUtil.getRequestVO(request,nonFixedCostSetRequestVO);
        log.info("更新区域公司非固定成本：nonFixedCostSetRequestVO======" + JSON.toJSONString(nonFixedCostSetRequestVO));
        Result result = profitFeignClient.updateRegionNonFixedCost(nonFixedCostSetRequestVO);
        log.info("更新区域公司非固定成本：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 李道云
     * @methodName: searchStoreProfitList
     * @methodDesc: 分页查询门店分润列表
     * @description:
     * @param: [storeProfitRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.PageResponseVO<com.hryj.entity.vo.profit.response.StoreBalanceVO>>
     * @create 2018-07-09 21:15
     **/
    @ApiOperation(value="分页查询门店分润列表",notes = "分页查询门店分润列表")
    @PostMapping("/searchStoreProfitList")
    public Result<PageResponseVO<StoreBalanceVO>> searchStoreProfitList(@RequestBody StoreProfitRequestVO storeProfitRequestVO){
        if(storeProfitRequestVO ==null){
            storeProfitRequestVO = new StoreProfitRequestVO();
        }
        WebUtil.getRequestVO(request,storeProfitRequestVO);
        log.info("分页查询门店分润列表：storeProfitRequestVO======" + JSON.toJSONString(storeProfitRequestVO));
        Result<PageResponseVO<StoreBalanceVO>> result = profitFeignClient.searchStoreProfitList(storeProfitRequestVO);
        log.info("分页查询门店分润列表：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 李道云
     * @methodName: findStoreProfitDetailList
     * @methodDesc: 查询门店分润明细数据
     * @description:
     * @param: [storeProfitDetailRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.profit.response.StoreProfitDetailResponseVO>
     * @create 2018-07-09 22:43
     **/
    @ApiOperation(value="查询门店分润明细数据",notes = "查询门店分润明细数据")
    @PostMapping("/findStoreProfitDetailList")
    public Result<StoreProfitDetailResponseVO> findStoreProfitDetailList(@RequestBody StoreProfitDetailRequestVO storeProfitDetailRequestVO){
        if(storeProfitDetailRequestVO ==null){
            storeProfitDetailRequestVO = new StoreProfitDetailRequestVO();
        }
        WebUtil.getRequestVO(request,storeProfitDetailRequestVO);
        log.info("查询门店分润明细数据：storeProfitDetailRequestVO======" + JSON.toJSONString(storeProfitDetailRequestVO));
        Result<StoreProfitDetailResponseVO> result = profitFeignClient.findStoreProfitDetailList(storeProfitDetailRequestVO);
        log.info("查询门店分润明细数据：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 李道云
     * @methodName: updateStoreNonFixedCost
     * @methodDesc: 更新门店非固定成本
     * @description:
     * @param: [nonFixedCostSetRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-09 21:17
     **/
    @ApiOperation(value="更新门店非固定成本",notes = "更新门店非固定成本")
    @PostMapping("/updateStoreNonFixedCost")
    public Result updateStoreNonFixedCost(@RequestBody NonFixedCostSetRequestVO nonFixedCostSetRequestVO){
        if(nonFixedCostSetRequestVO ==null){
            nonFixedCostSetRequestVO = new NonFixedCostSetRequestVO();
        }
        WebUtil.getRequestVO(request,nonFixedCostSetRequestVO);
        log.info("更新门店非固定成本：nonFixedCostSetRequestVO======" + JSON.toJSONString(nonFixedCostSetRequestVO));
        Result result = profitFeignClient.updateStoreNonFixedCost(nonFixedCostSetRequestVO);
        log.info("更新门店非固定成本：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 李道云
     * @methodName: searchStoreManagerProfitList
     * @methodDesc: 分页查询店长分润列表
     * @description:
     * @param: [storeManagerProfitRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.PageResponseVO<com.hryj.entity.vo.profit.response.StoreManagerProfitVO>>
     * @create 2018-07-09 22:46
     **/
    @ApiOperation(value="分页查询店长分润列表",notes = "分页查询店长分润列表")
    @PostMapping("/searchStoreManagerProfitList")
    public Result<PageResponseVO<StoreManagerProfitVO>> searchStoreManagerProfitList(@RequestBody StoreManagerProfitRequestVO storeManagerProfitRequestVO){
        if(storeManagerProfitRequestVO ==null){
            storeManagerProfitRequestVO = new StoreManagerProfitRequestVO();
        }
        WebUtil.getRequestVO(request,storeManagerProfitRequestVO);
        log.info("分页查询店长分润列表：storeManagerProfitRequestVO======" + JSON.toJSONString(storeManagerProfitRequestVO));
        Result<PageResponseVO<StoreManagerProfitVO>> result = profitFeignClient.searchStoreManagerProfitList(storeManagerProfitRequestVO);
        log.info("分页查询店长分润列表：result======" + JSON.toJSONString(result));
        return result;
    }
}
