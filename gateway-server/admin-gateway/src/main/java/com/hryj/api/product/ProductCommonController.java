package com.hryj.api.product;

import com.alibaba.fastjson.JSON;
import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.PageResponseVO;
import com.hryj.entity.vo.product.category.request.ProductCategoryFindRequestVO;
import com.hryj.entity.vo.product.category.response.ProdCateTreeResponseItemVO;
import com.hryj.entity.vo.product.common.request.SearchHSCodeRequestVO;
import com.hryj.entity.vo.product.common.request.SearchProductBrandRequestVO;
import com.hryj.entity.vo.product.common.request.SearchProductGeoRequestVO;
import com.hryj.entity.vo.product.common.response.ProductBrandResponseVO;
import com.hryj.entity.vo.product.common.response.ProductGeoResponseVO;
import com.hryj.entity.vo.product.common.response.ProductTaxRateResponseVO;
import com.hryj.entity.vo.product.partyprod.request.PartySelectableProdsPoolRequestVO;
import com.hryj.entity.vo.product.partyprod.request.SearchPartyPolymerizationProductRequestVO;
import com.hryj.entity.vo.product.partyprod.response.PartyIntersectionProductItem;
import com.hryj.entity.vo.product.partyprod.response.PartyProductListItemResponseVO;
import com.hryj.exception.BizException;
import com.hryj.exception.ServerException;
import com.hryj.feign.PartyProductFeignClient;
import com.hryj.feign.ProductCategoryFeignClient;
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
 * @description: 商品的公共接口
 * @create 2018/6/26 0026 16:06
 **/
@Api(value = "/product/common", tags = "商品 - 统计")
@Slf4j
@RestController
@RequestMapping("/product/common")
public class ProductCommonController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PartyProductFeignClient partyProductFeignClient;

    @Autowired
    private ProductCategoryFeignClient productCategoryFeignClient;

    @Autowired
    private ProductFeignClient productFeignClient;


    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.ListResponseVO<com.hryj.entity.vo.product.category.response.ProdCateTreeResponseItemVO>>
     * @author 王光银
     * @methodName: findProdCateTree
     * @methodDesc: 返回商品分类树结构（简洁版）
     * @description: category_name可以为空，不为空时作前后模糊匹配, include_attr 参数不必传,传了也没用
     * @param: [productCategoryFindRequestVO]
     * @create 2018-07-11 9:19
     **/
    @ApiOperation(value = "返回商品分类树结构（简洁版），适合用于构造tree", notes = "category_name可以为空，不为空时作前后模糊匹配, include_attr 参数不必传,传了也没用")
    @PostMapping("/findProdCateTree")
    public Result<ListResponseVO<ProdCateTreeResponseItemVO>> findProdCateTree(
            @RequestBody ProductCategoryFindRequestVO productCategoryFindRequestVO) throws BizException {
        WebUtil.getRequestVO(request, productCategoryFindRequestVO);
        log.info("返回商品分类树结构（简洁版）- -- request:" + JSON.toJSONString(productCategoryFindRequestVO));
        Result result = productCategoryFeignClient.findProdCateTree(productCategoryFindRequestVO);
        log.info("返回商品分类树结构（简洁版）- -- response:" + JSON.toJSONString(result));
        return result;
    }


    /**
     * @return com.hryj.common.PageResult<com.hryj.entity.vo.product.partyprod.response.PartyProductItemResponseVO>
     * @author 王光银
     * @methodName: searchManyPartyPolymerizationProduct
     * @methodDesc: 分页查询多个门店或仓库的聚合商品
     * @description: 支持并集查询和交集查询两种方式
     * @param: [partyPolymerizationProductSearchRequestVO]
     * @create 2018-06-28 15:31
     **/
    @ApiOperation(value = "分页查询多个门店或仓库的聚合商品 - V1.2", notes = "支持并集查询和交集查询两种方式")
    @PostMapping("/searchManyPartyPolymerizationProduct")
    public Result<PageResponseVO<PartyIntersectionProductItem>> searchManyPartyPolymerizationProduct(@RequestBody SearchPartyPolymerizationProductRequestVO partyPolymerizationProductSearchRequestVO) {
        WebUtil.getRequestVO(request, partyPolymerizationProductSearchRequestVO);
        log.info("搜索门店或仓库的聚合商品：request =" + JSON.toJSONString(partyPolymerizationProductSearchRequestVO));
        Result result = partyProductFeignClient.searchManyPartyPolymerizationProduct(partyPolymerizationProductSearchRequestVO);
        log.info("搜索门店或仓库的聚合商品：response =" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.PageResult<com.hryj.entity.vo.product.partyprod.response.PartySelectableProdItemResponseVO>
     * @author 王光银
     * @methodName: searchPartySelectableProduct
     * @methodDesc: 加载指定门店或仓库的可添加销售的商品列表数据
     * @description:
     * @param: [partySelectableProdsPoolRequestVO]
     * @create 2018-06-28 15:45
     **/
    @ApiOperation(value = "加载指定门店或仓库的可添加销售的商品列表数据 - V1.2", notes = "加载指定门店或仓库的可添加销售的商品列表数据")
    @PostMapping("/searchPartySelectableProduct")
    public Result<PageResponseVO<PartyProductListItemResponseVO>> searchPartySelectableProduct(@RequestBody PartySelectableProdsPoolRequestVO partySelectableProdsPoolRequestVO) {
        WebUtil.getRequestVO(request, partySelectableProdsPoolRequestVO);
        log.info("加载指定门店或仓库的可添加销售的商品列表数据：partySelectableProdsPoolRequestVO=====:" + JSON.toJSONString(partySelectableProdsPoolRequestVO));
        Result result = partyProductFeignClient.searchPartySelectableProduct(partySelectableProdsPoolRequestVO);
        log.info("加载指定门店或仓库的可添加销售的商品列表数据：result=====" + JSON.toJSONString(result));
        return result;
    }

    @ApiOperation(value = "搜索商品品牌--v1.2新增接口", notes = "搜索商品品牌--v1.2新增接口")
    @PostMapping("/searchProductBrand")
    public Result<ListResponseVO<ProductBrandResponseVO>> searchProductBrand() throws ServerException {
        Result<ListResponseVO<ProductBrandResponseVO>> result = productFeignClient.searchProductBrand();
        log.info("搜索商品品牌 -- response:" + JSON.toJSONString(result));
        return result;
    }

    @ApiOperation(value = "搜索商品产地--v1.2新增接口", notes = "搜索商品产地--v1.2新增接口")
    @PostMapping("/searchProductGeo")
    public Result<ListResponseVO<ProductGeoResponseVO>> searchProductGeo() throws ServerException {
        Result<ListResponseVO<ProductGeoResponseVO>> result = productFeignClient.searchProductGeo();
        log.info("搜索商品产地 -- response:" + JSON.toJSONString(result));
        return result;
    }

    @ApiOperation(value = "搜索HSCode（多条数据只返回十条候选）--v1.2新增接口", notes = "搜索商品产地（多条数据只返回十条候选）--v1.2新增接口")
    @PostMapping("/searchHSCode")
    public Result<ListResponseVO<ProductTaxRateResponseVO>> searchHSCode(@RequestBody SearchHSCodeRequestVO searchHSCodeRequestVO) throws ServerException {
        log.info("搜索HSCode -- request:" + JSON.toJSONString(searchHSCodeRequestVO));
        Result<ListResponseVO<ProductTaxRateResponseVO>> result = productFeignClient.searchHSCode(searchHSCodeRequestVO);
        log.info("搜索HSCode -- response:" + JSON.toJSONString(result));
        return result;
    }
}
