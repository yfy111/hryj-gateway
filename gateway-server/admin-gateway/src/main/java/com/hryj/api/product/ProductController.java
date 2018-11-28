package com.hryj.api.product;

import com.alibaba.fastjson.JSON;
import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.PageResponseVO;
import com.hryj.entity.vo.product.common.request.SearchHSCodeRequestVO;
import com.hryj.entity.vo.product.common.request.SearchProductBrandRequestVO;
import com.hryj.entity.vo.product.common.request.SearchProductGeoRequestVO;
import com.hryj.entity.vo.product.common.response.ProductBrandResponseVO;
import com.hryj.entity.vo.product.common.response.ProductGeoResponseVO;
import com.hryj.entity.vo.product.common.response.ProductTaxRateResponseVO;
import com.hryj.entity.vo.product.request.*;
import com.hryj.entity.vo.product.response.*;
import com.hryj.exception.BizException;
import com.hryj.exception.ServerException;
import com.hryj.feign.ProductFeignClient;
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
 * @className: ProductController
 * @description: 商品管理
 * @create 2018/6/26 0026 16:06
 **/
@Api(value = "/product/productMgr", tags = "商品 - 商品管理")
@Slf4j
@RestController
@RequestMapping("/product/productMgr")
public class ProductController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ProductFeignClient productFeignClient;

    /**
     * @return com.hryj.common.PageResult<com.hryj.entity.vo.product.response.ProductPageListItemResponseVO>
     * @author 王光银
     * @methodName: searchProductByPage
     * @methodDesc: 分页查询商品数据
     * @description:
     * @param: [productSearchRequestVO]
     * @create 2018-06-28 15:14
     **/
    @ApiOperation(value = "分页查询商品数据 - V1.2", notes = "分页查询商品数据")
    @PostMapping("/findProductByPage")
    public Result<PageResponseVO<ProdListItemResponseVO>> searchProductByPage(@RequestBody SearchProductRequestVO productSearchRequestVO) {
        WebUtil.getRequestVO(request, productSearchRequestVO);
        log.info("分页查询商品数据 -- request:" + JSON.toJSONString(productSearchRequestVO));
        Result result = productFeignClient.searchProductByPage(productSearchRequestVO);
        log.info("分页查询商品数据 -- response:" + JSON.toJSONString(result));
        return result;
    }


    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.product.response.ProductDetailResponseVO>
     * @author 王光银
     * @methodName: getProduct
     * @methodDesc: 查询一个商品的完整信息
     * @description: 该接口会跟随业务中商品数据的增加而增加返回数据
     * @param: [productIdRequestVO]
     * @create 2018-06-27 20:02
     **/
    @ApiOperation(value = "查询商品详情 - V1.2", notes = "该接口会跟随业务中商品数据的增加而增加返回数据")
    @PostMapping("/getProduct")
    public Result<ProductDetailResponseVO> getProduct(@RequestBody ProductIdRequestVO productIdRequestVO) {
        WebUtil.getRequestVO(request, productIdRequestVO);
        log.info("查询一个商品的完整信息 -- request:" + JSON.toJSONString(productIdRequestVO));
        Result result = productFeignClient.getProduct(productIdRequestVO);
        log.info("查询一个商品的完整信息 -- response:" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result
     * @author 王光银
     * @methodName: saveCreateProduct
     * @methodDesc: 保存新增一个商品
     * @description: 商品新增后状态为未审核，需通过审核后变更为已审核
     * @param: [product]
     * @create 2018-06-27 20:00
     **/
    @ApiOperation(value = "保存商品信息 - V1.2", notes = "保存商品信息")
    @PostMapping("/saveCreateProduct")
    public Result saveCreateProduct(@RequestBody ProductRequestVO product) {
        WebUtil.getRequestVO(request, product);
        log.info("保存新增一个商品 -- request:" + JSON.toJSONString(product));
        Result result = productFeignClient.saveCreateProduct(product);
        log.info("保存新增一个商品 -- response:" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result
     * @author 王光银
     * @methodName: updateProduct
     * @methodDesc: 保存修改商品信息
     * @description: 调用该接口会进行审核备份，请求的数据变更操作不会马上生效，必须审核通过过才生效
     * @param: [product]
     * @create 2018-06-27 20:00
     **/
    @ApiOperation(value = "修改商品信息 - V1.2", notes = "修改商品信息")
    @PostMapping("/updateProduct")
    public Result updateProduct(@RequestBody ProductRequestVO product) {
        WebUtil.getRequestVO(request, product);
        log.info("保存修改商品信息 -- request:" + JSON.toJSONString(product));
        Result result = productFeignClient.updateProduct(product);
        log.info("保存修改商品信息 -- response:" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 王光银
     * @methodName: downProduct
     * @methodDesc: 上架商品（一个或多个）
     * @description:
     * @param: [idsRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-13 9:31
     **/
    @ApiOperation(value = "上架商品（一个或多个）", notes = "上架商品（一个或多个）")
    @PostMapping("/upProducts")
    public Result upProducts(@RequestBody ProductIdsRequestVO idsRequestVO) throws ServerException {
        WebUtil.getRequestVO(request, idsRequestVO);
        log.info("上架商品（一个或多个） -- request:" + JSON.toJSONString(idsRequestVO));
        Result result = productFeignClient.upProducts(idsRequestVO);
        log.info("上架商品（一个或多个） -- response:" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 王光银
     * @methodName: downProduct
     * @methodDesc: 下架商品（一个或多个）
     * @description:
     * @param: [idsRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-13 9:31
     **/
    @ApiOperation(value = "下架商品（一个或多个）", notes = "下架商品（一个或多个）")
    @PostMapping("/downProducts")
    public Result downProducts(@RequestBody ProductIdsRequestVO idsRequestVO) throws ServerException {
        WebUtil.getRequestVO(request, idsRequestVO);
        log.info("下架商品（一个或多个） -- request:" + JSON.toJSONString(idsRequestVO));
        Result result = productFeignClient.downProducts(idsRequestVO);
        log.info("下架商品（一个或多个） -- response:" + JSON.toJSONString(result));
        return result;
    }


    /**
     * @author 王光银
     * @methodName: adjustmentCrossBorderProductInventory
     * @methodDesc: 调整商品中心-商品库存
     * @description:
     * @param: [requestVO]
     * @return com.hryj.common.Result
     * @create 2018-09-11 9:38
     **/
    @ApiOperation(value = "调整商品中心-商品库存(跨境商品库存设置) - V1.2", notes = "调整商品中心-商品库存(跨境商品库存设置)")
    @PostMapping("/adjustmentProductInventory")
    public Result adjustmentProductInventory(
            @RequestBody ProductInventoryAdjustmentRequestVO requestVO) throws BizException {
        WebUtil.getRequestVO(request, requestVO);
        log.info("调整商品中心商品库存 -- request:" + JSON.toJSONString(requestVO));
        Result result = productFeignClient.adjustmentProductInventory(requestVO);
        log.info("调整商品中心商品库存 -- response:" + JSON.toJSONString(result));
        return result;
    }


}
