package com.hryj.feign.product.v1_2;

import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.RequestVO;
import com.hryj.entity.vo.product.common.request.ProductValidateRequestVO;
import com.hryj.entity.vo.product.request.app.AppSearchProductRequestVO;
import com.hryj.entity.vo.product.response.app.AppProdListItemResponseVO;
import com.hryj.entity.vo.product.response.app.AppProductInfoResponseVO;
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
    @RequestMapping(value = "/v1-2/partyProductForApp/findRecommendProductList", method = RequestMethod.POST)
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
    @RequestMapping(value = "/v1-2/partyProductForApp/searchProductList", method = RequestMethod.POST)
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
    @RequestMapping(value = "/v1-2/partyProductForApp/getProductInfo", method = RequestMethod.POST)
    Result<AppProductInfoResponseVO> getProductInfo(@RequestBody ProductValidateRequestVO productValidateRequestVO);


}
