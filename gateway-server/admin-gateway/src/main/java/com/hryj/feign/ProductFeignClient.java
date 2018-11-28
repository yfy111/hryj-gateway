package com.hryj.feign;

import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.PageResponseVO;
import com.hryj.entity.vo.product.audit.request.ProductBackupRequestVO;
import com.hryj.entity.vo.product.audit.request.ProductHandledResultSubmitRequestVO;
import com.hryj.entity.vo.product.audit.request.SearchProductAuditRequestVO;
import com.hryj.entity.vo.product.audit.response.ProductAuditPageItemResponseVO;
import com.hryj.entity.vo.product.audit.response.ProductBackupResponseVO;
import com.hryj.entity.vo.product.common.request.*;
import com.hryj.entity.vo.product.common.response.ProductBrandResponseVO;
import com.hryj.entity.vo.product.common.response.ProductGeoResponseVO;
import com.hryj.entity.vo.product.common.response.ProductTaxRateResponseVO;
import com.hryj.entity.vo.product.request.*;
import com.hryj.entity.vo.product.response.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 李道云
 * @className: ProductFeignClient
 * @description: 商品服务feign接口
 * @create 2018/6/26 17:19
 **/
@FeignClient(name = "product-server")
public interface ProductFeignClient {

    /**
     * @author 王光银
     * @methodName: searchProductByPage
     * @methodDesc: 分页查询商品数据
     * @description:
     * @param: [productSearchRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.PageResponseVO<com.hryj.entity.vo.product.response.ProductPageListItemResponseVO>>
     * @create 2018-06-30 9:09
     **/
    @RequestMapping(value = "/productMgr/findProductByPage", method = RequestMethod.POST)
    Result<PageResponseVO<ProdListItemResponseVO>> searchProductByPage(@RequestBody SearchProductRequestVO productSearchRequestVO);


    /**
     * @author 王光银
     * @methodName: getProduct
     * @methodDesc: 查询一个商品的完整信息,
     * @description: 该接口会跟随业务中商品数据的增加而增加返回数据
     * @param: [productIdRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.product.response.ProductDetailResponseVO>
     * @create 2018-06-27 20:02
     **/
    @RequestMapping(value = "/productMgr/getProduct", method = RequestMethod.POST)
    Result<ProductDetailResponseVO> getProduct(@RequestBody ProductIdRequestVO productIdRequestVO);

    /**
     * @author 王光银
     * @methodName: saveCreateProduct
     * @methodDesc: 保存新增一个商品
     * @description:
     * @param: [product]
     * @return com.hryj.common.Result
     * @create 2018-06-27 20:00
     **/
    @RequestMapping(value = "/productMgr/saveCreateProduct", method = RequestMethod.POST)
    Result saveCreateProduct(@RequestBody ProductRequestVO product);

    /**
     * @author 王光银
     * @methodName: updateProduct
     * @methodDesc: 保存修改商品信息
     * @description: 调用该接口修改商品信息会导致商品下架
     * @param: [product]
     * @return com.hryj.common.Result
     * @create 2018-06-27 20:00
     **/
    @RequestMapping(value = "/productMgr/updateProduct", method = RequestMethod.POST)
    Result updateProduct(@RequestBody ProductRequestVO product);


    /**
     * @author 王光银
     * @methodName: searchProductAuditByPage
     * @methodDesc: 分页查询商品审核管理数据
     * @description:
     * @param: [productAuditSearchRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.PageResponseVO<com.hryj.entity.vo.product.audit.response.ProductAuditPageItemResponseVO>>
     * @create 2018-06-30 9:16
     **/
    @RequestMapping(value = "/productAuditMgr/findProductAuditByPage", method = RequestMethod.POST)
    Result<PageResponseVO<ProductAuditPageItemResponseVO>> searchProductAuditByPage(@RequestBody SearchProductAuditRequestVO productAuditSearchRequestVO);

    /**
     * @author 王光银
     * @methodName: getOneProductBackupData
     * @methodDesc: 查询返回一个商品的快照数据
     * @description:
     * @param: [productBackupRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.product.audit.response.ProductBackupResponseVO>
     * @create 2018-06-27 20:25
     **/
    @RequestMapping(value = "/productAuditMgr/getOneProductAuditData", method = RequestMethod.POST)
    Result<ProductBackupResponseVO> getOneProductBackupData(@RequestBody ProductBackupRequestVO productBackupRequestVO);

    /**
     * @author 王光银
     * @methodName: submitProductAuditResult
     * @methodDesc: 提交商品审核处理结果
     * @description: 审核通过时，修改后的商品数据备份会覆盖到商品库中
     * @param: [productHandledResultSubmitRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-27 20:25
     **/
    @RequestMapping(value = "/productAuditMgr/submitProductAuditResult", method = RequestMethod.POST)
    Result submitProductAuditResult(@RequestBody ProductHandledResultSubmitRequestVO productHandledResultSubmitRequestVO);

    /**
     * @author 王光银
     * @methodName: partyProductInventoryAdjust
     * @methodDesc: 调整当事组织(门店或仓库)的商品库存数量
     * @description:
     * @param: [partyProductInventoryAdjustRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-09 16:40
     **/
    @RequestMapping(value = "/productCommon/partyProductInventoryAdjust", method = RequestMethod.POST)
    Result partyProductInventoryAdjust(@RequestBody PartyProductInventoryAdjustRequestVO partyProductInventoryAdjustRequestVO);

    /**
     * @author 王光银
     * @methodName: findTopSalesProduct
     * @methodDesc: 查询TOP销量商品
     * @description: 该接口的统计范围是在统计时刻仍然在销售的商品，已经下架的商品不管销售多高不会返回，同时参数party_id有值返回指定门店或仓库的TOP销售商品，否则返回全国范围内的TOP销量商品，目前返回的商品数量为10个
     * @param: [partyIdRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.ListResponseVO<com.hryj.entity.vo.product.response.ProductTopSalesItemResponseVO>>
     * @create 2018-07-03 21:45
     **/
    @RequestMapping(value = "/productCommon/findTopSalesProduct", method = RequestMethod.POST)
    Result<ListResponseVO<ProductTopSalesItemResponseVO>> findTopSalesProduct(@RequestBody TopSaleRequestVO topSaleRequestVO);

    /**
     * @author 王光银
     * @methodName: findTopSalesProduct
     * @methodDesc: 查询TOP销量商品
     * @description: 该接口的统计范围是只按照销量统计，不管商品是否下架，返回集合按照销量排序， 参数 party_id有值返回指定门店或仓库的TOP销售商品，否则返回全国范围内的TOP销量商品，目前返回的商品数量为10个
     * @param: [partyIdRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.ListResponseVO<com.hryj.entity.vo.product.response.ProductTopSalesItemResponseVO>>
     * @create 2018-07-03 21:45
     **/
    @RequestMapping(value = "/productCommon/findTopSalesAndHisProduct", method = RequestMethod.POST)
    Result<ListResponseVO<ProductTopSalesItemResponseVO>> findTopSalesAndHisProduct(@RequestBody TopSaleRequestVO topSaleRequestVO);


    /**
     * @author 王光银
     * @methodName: downProduct
     * @methodDesc: 上架商品（一个或多个）
     * @description:
     * @param: [idsRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-13 9:31
     **/
    @RequestMapping(value = "/productMgr/upProducts", method = RequestMethod.POST)
    Result upProducts(@RequestBody ProductIdsRequestVO idsRequestVO);

    /**
     * @author 王光银
     * @methodName: downProduct
     * @methodDesc: 下架商品（一个或多个）
     * @description:
     * @param: [idsRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-13 9:31
     **/
    @RequestMapping(value = "/productMgr/downProducts", method = RequestMethod.POST)
    Result downProducts(@RequestBody ProductIdsRequestVO idsRequestVO);

    /**
     * @author 王光银
     * @methodName: adjustmentProductInventory
     * @methodDesc: 调整商品中心- 商品库存
     * @description:
     * @param: [requestVO]
     * @return com.hryj.common.Result
     * @create 2018-09-11 9:42
     **/
    @RequestMapping(value = "/product/inventory/crossBorderProductInventoryAdjustment", method = RequestMethod.POST)
    Result adjustmentProductInventory(@RequestBody ProductInventoryAdjustmentRequestVO requestVO);


    /**
     * @author 汪豪
     * @methodName: searchProductBrand
     * @methodDesc:
     * @description: 多条品牌数据只返回前十条数据--v1.2新增接口
     * @param: [searchProductBrandRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.ListResponseVO<com.hryj.entity.vo.product.common.response.ProductBrandResponseVO>>
     * @create 2018-09-11 9:09
     **/
    @RequestMapping(value = "/productCommon/searchProductBrand", method = RequestMethod.POST)
    Result<ListResponseVO<ProductBrandResponseVO>> searchProductBrand();

    /**
     * @author 汪豪
     * @methodName: searchProductGeo
     * @methodDesc:
     * @description: 多条商品产地数据只返回前十条数据--v1.2新增接口
     * @param: [searchProductGeoRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.ListResponseVO<com.hryj.entity.vo.product.common.response.ProductGeoResponseVO>>
     * @create 2018-09-11 9:13
     **/
    @RequestMapping(value = "/productCommon/searchProductGeo", method = RequestMethod.POST)
    Result<ListResponseVO<ProductGeoResponseVO>> searchProductGeo();

    /**
     * @author 汪豪
     * @methodName: searchHSCode
     * @methodDesc:
     * @description: 多条商品HSCode数据只返回前十条数据--v1.2新增接口
     * @param: [searchHSCodeRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.ListResponseVO<com.hryj.entity.vo.product.common.response.ProductTaxRateResponseVO>>
     * @create 2018-09-11 9:16
     **/
    @RequestMapping(value = "/productCommon/searchHSCode", method = RequestMethod.POST)
    Result<ListResponseVO<ProductTaxRateResponseVO>> searchHSCode(@RequestBody SearchHSCodeRequestVO searchHSCodeRequestVO);
}
