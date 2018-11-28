package com.hryj.feign.product.v1_1;

import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.RequestVO;
import com.hryj.entity.vo.product.category.request.app.AppProdCateSearchRequestVO;
import com.hryj.entity.vo.product.category.response.ProductCategoryItemResponseVO;
import com.hryj.entity.vo.product.common.request.ProductValidateRequestVO;
import com.hryj.entity.vo.product.common.request.ShareProductRequestVO;
import com.hryj.entity.vo.product.common.response.ShareProductResponseVO;
import com.hryj.entity.vo.product.request.app.AppSearchProductRequestVO;
import com.hryj.entity.vo.product.response.app.AppProdListItemResponseVO;
import com.hryj.entity.vo.product.response.app.AppProductInfoResponseVO;
import com.hryj.entity.vo.userparty.request.UserPartyRequestVO;
import com.hryj.entity.vo.userparty.response.UserPartyResponseItem;
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
    
    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.ListResponseVO<com.hryj.entity.vo.product.category.response.ProductCategoryItemResponseVO>>
     * @author 王光银
     * @methodName: findProdCate
     * @methodDesc: APP端查询商品分类
     * @description: 如果category_id参数有值则返回该分类下的直接子分类，如果没有值则返回所有一级分类
     * @param: [category_id]
     * @create 2018-07-02 20:16
     **/
    @RequestMapping(value = "/v1-1/productCategoryForApp/findProdCate", method = RequestMethod.POST)
    Result<ListResponseVO<ProductCategoryItemResponseVO>> findProdCate(
            @RequestBody AppProdCateSearchRequestVO appProdCateSearchRequestVO);

    /* --------------------------------------------------------------------------------------------------------------------------------------------------- */
    /* ------------------------------------------------分割线上是与分类相关的接口或者是分类下的商品搜索接口---------------------------------------------------- */
    /* --------------------------------------------------------------------------------------------------------------------------------------------------- */

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.ListResponseVO<com.hryj.entity.vo.product.response.AppProductListItemResponseVO>>
     * @author 王光银
     * @methodName: findRecommendProductList
     * @methodDesc: App端查询推荐位商品列表
     * @description: 推荐商品返回列表不分页，商品返回数量的多少，排序都在接口内控制
     * @param: [requestVO]
     * @create 2018-07-02 20:16
     **/
    @RequestMapping(value = "/v1-1/partyProductForApp/findRecommendProductList", method = RequestMethod.POST)
    Result<ListResponseVO<AppProdListItemResponseVO>> findRecommendProductList(@RequestBody RequestVO requestVO);

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.PageResponseVO<com.hryj.entity.vo.product.response.AppProductListItemResponseVO>>
     * @author 王光银
     * @methodName: searchProductList
     * @methodDesc: App端商品搜素
     * @description:
     * @param: [appSearchProductRequestVO]
     * @create 2018-06-30 9:33
     **/
    @RequestMapping(value = "/v1-1/partyProductForApp/searchProductList", method = RequestMethod.POST)
    Result<ListResponseVO<AppProdListItemResponseVO>> searchProductList(@RequestBody AppSearchProductRequestVO appSearchProductRequestVO);

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.product.response.AppProductInfoResponseVO>
     * @author 王光银
     * @methodName: getProductInfo
     * @methodDesc: APP端获取商品的详细信息
     * @description: 商品下架返回 biz_code=11000,  活动结束返回 biz_code=11010，库存数量不足返回 biz_code=11020，这三个业务上的非正常情况编码不会影响其他数据的正常返回
     * @param: [productValidateRequestVO]
     * @create 2018-06-30 18:30
     **/
    @RequestMapping(value = "/v1-1/partyProductForApp/getProductInfo", method = RequestMethod.POST)
    Result<AppProductInfoResponseVO> getProductInfo(@RequestBody ProductValidateRequestVO productValidateRequestVO);

    /**
     * @author 王光银
     * @methodName: findAroundPartyList
     * @methodDesc: 查询返回覆盖用户的门店和仓库列表
     * @description:
     * @param: [userPartyRequestVO]
     * @return [Result<ListResponseVO<UserPartyResponseItem>>]
     * @create 2018-08-16 9:23
     **/
    @RequestMapping(value = "/userParty/findAroundPartyList", method = RequestMethod.POST)
    Result<ListResponseVO<UserPartyResponseItem>> findAroundPartyList(@RequestBody UserPartyRequestVO userPartyRequestVO);

    /**
     * @author 王光银
     * @methodName: getProductShareContent
     * @methodDesc: 获取商品分享内容
     * @description:
     * @param: [requestVO]
     * @return [Result<ShareProductResponseVO]
     * @create 2018-08-23 15:47
     **/
    @RequestMapping(value = "/productCommon/getProductShareContent", method = RequestMethod.POST)
    Result<ShareProductResponseVO> getProductShareContent(@RequestBody ShareProductRequestVO requestVO);

}
