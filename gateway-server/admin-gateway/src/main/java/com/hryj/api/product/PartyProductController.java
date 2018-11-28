package com.hryj.api.product;

import com.alibaba.fastjson.JSON;
import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.product.partyprod.request.*;
import com.hryj.entity.vo.product.partyprod.response.PartyProductDataResponseVO;
import com.hryj.entity.vo.product.partyprod.response.PartyProductStatisticsItem;
import com.hryj.feign.PartyProductFeignClient;
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
 * @className: PartyProductController
 * @description: 仓库门店商品管理
 * @create 2018/6/27 16:38
 **/
@Api(value = "/product/partyProductMgr", tags = "商品 - 门店仓库商品管理")
@Slf4j
@RestController
@RequestMapping("/product/partyProductMgr")
public class PartyProductController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PartyProductFeignClient partyProductFeignClient;

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.product.partyprod.response.PartyProductDataResponseVO>
     * @author 王光银
     * @methodName: searchPartyProduct
     * @methodDesc: 返回指定门店或仓库的商品数据和基础信息
     * @description:
     * @param: [partyProductDataRequestVO]
     * @create 2018-06-27 20:23
     **/
    @ApiOperation(value = "返回指定门店或仓库的商品数据和基础信息 - V1.2", notes = "返回指定门店或仓库的商品数据和基础信息")
    @PostMapping("/searchPartyProduct")
    public Result<PartyProductDataResponseVO> searchPartyProduct(@RequestBody PartyProductDataRequestVO partyProductDataRequestVO) {
        WebUtil.getRequestVO(request, partyProductDataRequestVO);
        log.info("返回指定门店或仓库的商品数据和基础信息：partyProductDataRequestVO=====:" + JSON.toJSONString(partyProductDataRequestVO));
        Result result = partyProductFeignClient.searchPartyProduct(partyProductDataRequestVO);
        log.info("返回指定门店或仓库的商品数据和基础信息：result=====" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result
     * @author 王光银
     * @methodName: appendProductToPartySaleablePool
     * @methodDesc: 将提交的商品添加到指定的门店或仓库的可销售商品池中
     * @description:
     * @param: [partySaleableProdPoolAppendRequestVO]
     * @create 2018-06-27 20:22
     **/
    @ApiOperation(value = "将提交的商品添加到指定的门店或仓库的可销售商品池中", notes = "将提交的商品添加到指定的门店或仓库的可销售商品池中")
    @PostMapping("/appendProductToPartySaleablePool")
    public Result appendProductToPartySaleablePool(@RequestBody PartySaleableProdPoolAppendRequestVO partySaleableProdPoolAppendRequestVO) {
        WebUtil.getRequestVO(request, partySaleableProdPoolAppendRequestVO);
        log.info("将提交的商品添加到指定的门店或仓库的可销售商品池中：partySaleableProdPoolAppendRequestVO=====" + JSON.toJSONString(partySaleableProdPoolAppendRequestVO));
        Result result = partyProductFeignClient.appendProductToPartySaleablePool(partySaleableProdPoolAppendRequestVO);
        log.info("将提交的商品添加到指定的门店或仓库的可销售商品池中：result=====" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result
     * @author 王光银
     * @methodName: updateManyPartyProduct
     * @methodDesc: 批量更新维护门店或仓库的商品销售数据（销售价格和库存数量）
     * @description: 门店不能维护销售价格，即使传参数也不会处理
     * @param: [partyUpdatePriceInventoryQuantityRequestVO]
     * @create 2018-06-27 20:22
     **/
    @ApiOperation(value = "批量更新维护门店或仓库的商品销售数据（销售价格和库存数量）", notes = "门店不能维护销售价格，即使传参数也不会处理")
    @PostMapping("/updateManyPartyProduct")
    public Result updateManyPartyProduct(@RequestBody PartyUpdatePriceInventoryQuantityRequestVO partyUpdatePriceInventoryQuantityRequestVO) {
        WebUtil.getRequestVO(request, partyUpdatePriceInventoryQuantityRequestVO);
        log.info("批量更新维护门店或仓库的商品销售数据（销售价格和库存数量）：partyUpdatePriceInventoryQuantityRequestVO=====" + JSON.toJSONString(partyUpdatePriceInventoryQuantityRequestVO));
        Result result = partyProductFeignClient.updateManyPartyProduct(partyUpdatePriceInventoryQuantityRequestVO);
        log.info("批量更新维护门店或仓库的商品销售数据（销售价格和库存数量）：result=====" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result
     * @author 王光银
     * @methodName: updateOnePartyProduct
     * @methodDesc: 更新维护一个门店或仓库的销售商品（销售价格和库存量）
     * @description: 门店没有设置销售价格的权限，即使传递也不会进行处理
     * @param: [partyUpdatePriceInventoryQuantityItemRequestVO]
     * @create 2018-06-27 20:21
     **/
    @ApiOperation(value = "更新维护一个门店或仓库的销售商品（销售价格和库存量）", notes = "门店没有设置销售价格的权限，即使传递也不会进行处理")
    @PostMapping("/updateOnePartyProduct")
    public Result updateOnePartyProduct(@RequestBody PartyUpdatePriceInventoryQuantityItemRequestVO partyUpdatePriceInventoryQuantityItemRequestVO) {
        WebUtil.getRequestVO(request, partyUpdatePriceInventoryQuantityItemRequestVO);
        log.info("更新维护一个门店或仓库的销售商品（销售价格和库存量）：partyUpdatePriceInventoryQuantityItemRequestVO=====" + JSON.toJSONString(partyUpdatePriceInventoryQuantityItemRequestVO));
        Result result = partyProductFeignClient.updateOnePartyProduct(partyUpdatePriceInventoryQuantityItemRequestVO);
        log.info("更新维护一个门店或仓库的销售商品（销售价格和库存量）：result=====" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result
     * @author 王光银
     * @methodName: upPartyProduct
     * @methodDesc: 上架指定的仓库或门店商品
     * @description:
     * @param: [idRequestVO]
     * @create 2018-06-27 20:21
     **/
    @ApiOperation(value = "上架指定的仓库或门店商品", notes = "上架指定的仓库或门店商品")
    @PostMapping("/upPartyProduct")
    public Result upPartyProduct(@RequestBody IdRequestVO idRequestVO) {
        WebUtil.getRequestVO(request, idRequestVO);
        log.info("上架指定的仓库或门店商品：idRequestVO=====" + JSON.toJSONString(idRequestVO));
        Result result = partyProductFeignClient.upPartyProduct(idRequestVO);
        log.info("上架指定的仓库或门店商品：result=====" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result
     * @author 王光银
     * @methodName: downPartyProduct
     * @methodDesc: 下架指定仓库或门店商品
     * @description:
     * @param: [party_product_id]
     * @create 2018-06-27 20:20
     **/
    @ApiOperation(value = "下架指定仓库或门店商品", notes = "下架指定仓库或门店商品")
    @PostMapping("/downPartyProduct")
    public Result downPartyProduct(@RequestBody IdRequestVO idRequestVO) {
        WebUtil.getRequestVO(request, idRequestVO);
        log.info("下架指定仓库或门店商品：idRequestVO=====" + JSON.toJSONString(idRequestVO));
        Result result = partyProductFeignClient.downPartyProduct(idRequestVO);
        log.info("下架指定仓库或门店商品：result=====" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.ListResponseVO   <   com.hryj.entity.vo.product.partyprod.response.PartyProductStatisticsItem>>
     * @author 王光银
     * @methodName: partyProdStatistics
     * @methodDesc: 门店或仓库商品简单统计接口
     * @description:
     * @param: [partyProductStatisticsRequestVO]
     * @create 2018-07-05 21:13
     **/
    @ApiOperation(value = "门店或仓库商品简单统计接口", notes = "门店或仓库商品简单统计接口")
    @PostMapping("/partyProdSimpleStatistics")
    public Result<ListResponseVO<PartyProductStatisticsItem>> partyProdSimpleStatistics(@RequestBody PartyProductStatisticsRequestVO partyProductStatisticsRequestVO) {
        WebUtil.getRequestVO(request, partyProductStatisticsRequestVO);
        log.info("门店或仓库商品简单统计接口：partyProductStatisticsRequestVO=====" + JSON.toJSONString(partyProductStatisticsRequestVO));
        Result<ListResponseVO<PartyProductStatisticsItem>> result = partyProductFeignClient.partyProdSimpleStatistics(partyProductStatisticsRequestVO);
        log.info("门店或仓库商品简单统计接口：result=====" + JSON.toJSONString(result));
        return result;
    }
}
