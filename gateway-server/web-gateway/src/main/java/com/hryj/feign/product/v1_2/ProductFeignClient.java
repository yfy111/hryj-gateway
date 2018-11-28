package com.hryj.feign.product.v1_2;

import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.product.category.request.app.AppProdCateSearchRequestVO;
import com.hryj.entity.vo.product.category.response.ProductCategoryItemResponseVO;
import com.hryj.entity.vo.product.request.app.AppSearchProductRequestVO;
import com.hryj.entity.vo.product.response.app.AppProdListItemResponseVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 王光银
 * @className: ProductFeignClient
 * @description: 商品服务feign接口
 * @create 2018/6/26 17:19
 **/
@FeignClient(name = "product-server")
public interface ProductFeignClient {
    
    /*******************************************************v1-1版本*********************************************************************************************/

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.ListResponseVO<com.hryj.entity.vo.product.category.response.ProductCategoryItemResponseVO>>
     * @author 王光银
     * @methodName: findProdCate
     * @methodDesc: APP端查询商品分类
     * @description: 如果category_id参数有值则返回该分类下的直接子分类，如果没有值则返回所有一级分类
     * @param: [category_id]
     * @create 2018-07-02 20:16
     **/
    @RequestMapping(value = "/v1-2/productCategoryForApp/findProdCate", method = RequestMethod.POST)
    Result<ListResponseVO<ProductCategoryItemResponseVO>> findProdCate(
            @RequestBody AppProdCateSearchRequestVO appProdCateSearchRequestVO);

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.PageResponseVO<com.hryj.entity.vo.product.response.AppProductListItemResponseVO>>
     * @author 王光银
     * @methodName: searchProductList
     * @methodDesc: App端商品搜素
     * @description:
     * @param: [appSearchProductRequestVO]
     * @create 2018-06-30 9:33
     **/
    @RequestMapping(value = "/v1-2/partyProductForApp/searchProductList", method = RequestMethod.POST)
    Result<ListResponseVO<AppProdListItemResponseVO>> searchProductList(@RequestBody AppSearchProductRequestVO appSearchProductRequestVO);

}
