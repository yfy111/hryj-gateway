package com.hryj.api.product.v1_0;

import com.alibaba.fastjson.JSON;
import com.hryj.common.CodeEnum;
import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.product.category.request.app.AppProdCateSearchRequestVO;
import com.hryj.entity.vo.product.category.response.ProductCategoryItemResponseVO;
import com.hryj.entity.vo.product.common.request.ProductValidateRequestVO;
import com.hryj.entity.vo.product.request.app.AppSearchProductRequestVO;
import com.hryj.entity.vo.product.response.app.AppProdListItemResponseVO;
import com.hryj.entity.vo.product.response.app.AppProductInfoResponseVO;
import com.hryj.feign.product.v1_0.ProductFeignClient;
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
 * @description: APP门店端接口  v1.0
 * @create 2018/7/17 0017 16:43
 **/
@Api(value = "/product", tags = "商品 - APP门店端 - V1.0")
@Slf4j
@RestController("v1.0-ProductController")
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ProductFeignClient productFeignClient;

    /**
     * @author 王光银
     * @methodName: findProdCate
     * @methodDesc: APP门店端查询商品分类
     * @description: 如果category_id参数有值则返回该分类下的直接子分类，如果没有值则返回所有一级分类
     * @param: [appProdCateSearchRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.ListResponseVO<com.hryj.entity.vo.product.category.response.ProductCategoryItemResponseVO>>
     * @create 2018-07-02 20:16
     **/
    @ApiOperation(value = "APP门店端查询商品分类", notes = "category_id参数有值则返回该分类下的直接子分类，如果没有值则返回所有一级分类")
    @PostMapping("/findProdCate")
    public Result<ListResponseVO<ProductCategoryItemResponseVO>> findProdCate(
            @RequestBody(required = false) AppProdCateSearchRequestVO appProdCateSearchRequestVO) {
        if (appProdCateSearchRequestVO == null) {
            appProdCateSearchRequestVO = new AppProdCateSearchRequestVO();
        }
        StringBuilder json_str = new StringBuilder();
        try {
            WebUtil.getRequestVO(request, appProdCateSearchRequestVO);
            json_str.append("v1.0 - APP门店端查询商品分类 - request:");
            json_str.append(JSON.toJSONString(appProdCateSearchRequestVO));
            json_str.append(System.lineSeparator());
            Result result = productFeignClient.findProdCate(appProdCateSearchRequestVO);
            json_str.append("v1.0 - APP门店端查询商品分类 - response:");
            json_str.append(JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            log.error("v1.0 - APP门店端查询商品分类接口异常", e);
            return new Result<>(CodeEnum.FAIL_SERVER, "系统繁忙请稍后再试");
        } finally {
            log.info(json_str.toString());
        }
    }

    /**
     * @author 王光银
     * @methodName: searchProductList
     * @methodDesc: App门店端商品搜索
     * @description:
     * @param: [appSearchProductRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.PageResponseVO<com.hryj.entity.vo.product.response.AppProductListItemResponseVO>>
     * @create 2018-06-30 9:33
     **/
    @ApiOperation(value = "App门店端商品搜索", notes = "商品搜索, 当没有覆盖用户门店和仓库时返回 业务代码:15000")
    @PostMapping("/searchProductList")
    public Result<ListResponseVO<AppProdListItemResponseVO>> searchProductList(@RequestBody(required = false) AppSearchProductRequestVO appSearchProductRequestVO) {
        if (appSearchProductRequestVO == null) {
            appSearchProductRequestVO = new AppSearchProductRequestVO();
        }
        StringBuilder json_str = new StringBuilder();
        try {
            WebUtil.getRequestVO(request, appSearchProductRequestVO);
            json_str.append("v1.0 - App门店端商品搜索 - request:");
            json_str.append(JSON.toJSONString(appSearchProductRequestVO));
            json_str.append(System.lineSeparator());
            Result result = productFeignClient.searchProductList(appSearchProductRequestVO);
            json_str.append("v1.0 - App门店端商品搜索 - response:");
            json_str.append(JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            log.error("v1.0 - App门店端商品搜索接口异常", e);
            return new Result<>(CodeEnum.FAIL_SERVER, "系统繁忙请稍后再试");
        } finally {
            log.info(json_str.toString());
        }
    }

    /**
     * @author 王光银
     * @methodName: getProductInfo
     * @methodDesc: APP门店端获取商品详细信息
     * @description: 商品下架返回 biz_code=11000,  活动结束返回 biz_code=11010，库存数量不足返回 biz_code=11020，这三个业务上的非正常情况编码不会影响其他数据的正常返回
     * @param: [productValidateRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.product.response.AppProductInfoResponseVO>
     * @create 2018-06-30 18:30
     **/
    @ApiOperation(value = "APP门店端获取商品详细信息", notes = "获取商品的详细信息，商品下架返回 biz_code=11000,  活动结束返回 biz_code=11010，库存数量不足返回 biz_code=11020，这三个业务上的非正常情况编码不会影响其他数据的正常返回")
    @PostMapping("/getProductInfo")
    public Result<AppProductInfoResponseVO> getProductInfo(@RequestBody(required = false) ProductValidateRequestVO productValidateRequestVO) {
        if (productValidateRequestVO == null) {
            productValidateRequestVO = new ProductValidateRequestVO();
        }
        StringBuilder json_str = new StringBuilder();
        try {
            WebUtil.getRequestVO(request, productValidateRequestVO);
            json_str.append("v1.0 - APP门店端获取商品详细信息 - request:");
            json_str.append(JSON.toJSONString(productValidateRequestVO));
            json_str.append(System.lineSeparator());
            Result result = productFeignClient.getProductInfo(productValidateRequestVO);
            json_str.append("v1.0 - APP门店端获取商品详细信息 - response:");
            json_str.append(JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            log.error("v1.0 - APP门店端获取商品详细信息接口异常", e);
            return new Result<>(CodeEnum.FAIL_SERVER, "系统繁忙请稍后再试");
        } finally {
            log.info(json_str.toString());
        }
    }

}
