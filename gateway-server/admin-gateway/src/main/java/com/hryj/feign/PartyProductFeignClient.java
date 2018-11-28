package com.hryj.feign;

import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.PageResponseVO;
import com.hryj.entity.vo.product.partyprod.request.*;
import com.hryj.entity.vo.product.partyprod.response.PartyIntersectionProductItem;
import com.hryj.entity.vo.product.partyprod.response.PartyProductDataResponseVO;
import com.hryj.entity.vo.product.partyprod.response.PartyProductListItemResponseVO;
import com.hryj.entity.vo.product.partyprod.response.PartyProductStatisticsItem;
import com.hryj.entity.vo.promotion.recommend.request.CopyRecommendProdToOtherPartyRequestVO;
import com.hryj.entity.vo.promotion.recommend.request.PartyRecommendProdAppendRequestVO;
import com.hryj.entity.vo.promotion.recommend.response.PartyRecommendProductItemResponseVO;
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
public interface PartyProductFeignClient {

    /**
     * @author 王光银
     * @methodName: searchManyPartyPolymerizationProduct
     * @methodDesc: 分页查询多个门店或仓库的聚合商品
     * @description: 支持并集查询和交集查询两种方式
     * @param: [partyPolymerizationProductSearchRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.PageResponseVO<com.hryj.entity.vo.product.partyprod.response.PartyProductItemResponseVO>>
     * @create 2018-06-30 9:19
     **/
    @RequestMapping(value = "/partyProductMgr/searchManyPartyPolymerizationProduct", method = RequestMethod.POST)
    Result<PageResponseVO<PartyIntersectionProductItem>> searchManyPartyPolymerizationProduct(
            @RequestBody SearchPartyPolymerizationProductRequestVO partyPolymerizationProductSearchRequestVO);

    /**
     * @author 王光银
     * @methodName: searchPartyProduct
     * @methodDesc: 返回指定门店或仓库的商品数据和基础信息
     * @description:
     * @param: [partyProductDataRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.product.partyprod.response.PartyProductDataResponseVO>
     * @create 2018-06-27 20:23
     **/
    @RequestMapping(value = "/partyProductMgr/searchPartyProduct", method = RequestMethod.POST)
    Result<PartyProductDataResponseVO> searchPartyProduct(@RequestBody PartyProductDataRequestVO partyProductDataRequestVO);

    /**
     * @author 王光银
     * @methodName: searchPartySelectableProduct
     * @methodDesc: 加载指定门店或仓库的可添加销售的商品列表数据
     * @description:
     * @param: [partySelectableProdsPoolRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.PageResponseVO<com.hryj.entity.vo.product.partyprod.response.PartySelectableProdItemResponseVO>>
     * @create 2018-06-30 9:18
     **/
    @RequestMapping(value = "/partyProductMgr/searchPartySelectableProduct", method = RequestMethod.POST)
    Result<PageResponseVO<PartyProductListItemResponseVO>> searchPartySelectableProduct(@RequestBody PartySelectableProdsPoolRequestVO partySelectableProdsPoolRequestVO);

    /**
     * @author 王光银
     * @methodName: appendProductToPartySaleablePool
     * @methodDesc: 将提交的商品添加到指定的门店或仓库的可销售商品池中
     * @description:
     * @param: [partySaleableProdPoolAppendRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-27 20:22
     **/
    @RequestMapping(value = "/partyProductMgr/appendProductToPartySaleablePool", method = RequestMethod.POST)
    Result appendProductToPartySaleablePool(@RequestBody PartySaleableProdPoolAppendRequestVO partySaleableProdPoolAppendRequestVO);

    /**
     * @author 王光银
     * @methodName: updateManyPartyProduct
     * @methodDesc: 批量更新维护门店或仓库的商品销售数据（销售价格和库存数量）
     * @description: 门店不能维护销售价格，即使传参数也不会处理
     * @param: [partyUpdatePriceInventoryQuantityRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-27 20:22
     **/
    @RequestMapping(value = "/partyProductMgr/updateManyPartyProduct", method = RequestMethod.POST)
    Result updateManyPartyProduct(@RequestBody PartyUpdatePriceInventoryQuantityRequestVO partyUpdatePriceInventoryQuantityRequestVO);

    /**
     * @author 王光银
     * @methodName: updateOnePartyProduct
     * @methodDesc: 更新维护一个门店或仓库的销售商品（销售价格和库存量）
     * @description: 门店没有设置销售价格的权限，即使传递也不会进行处理
     * @param: [partyUpdatePriceInventoryQuantityItemRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-27 20:21
     **/
    @RequestMapping(value = "/partyProductMgr/updateOnePartyProduct", method = RequestMethod.POST)
    Result updateOnePartyProduct(@RequestBody PartyUpdatePriceInventoryQuantityItemRequestVO partyUpdatePriceInventoryQuantityItemRequestVO);

    /**
     * @author 王光银
     * @methodName: upPartyProduct
     * @methodDesc: 上架指定的仓库或门店商品
     * @description:
     * @param: [idRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-27 20:21
     **/
    @RequestMapping(value = "/partyProductMgr/upPartyProduct", method = RequestMethod.POST)
    Result upPartyProduct(@RequestBody IdRequestVO idRequestVO);

    /**
     * @author 王光银
     * @methodName: downPartyProduct
     * @methodDesc: 下架指定仓库或门店商品
     * @description:
     * @param: [idRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-27 20:20
     **/
    @RequestMapping(value = "/partyProductMgr/downPartyProduct", method = RequestMethod.POST)
    Result downPartyProduct(@RequestBody IdRequestVO idRequestVO);

    /**
     * @author 王光银
     * @methodName: findPartyRecommendProductList
     * @methodDesc: 查询返回指定门店或仓库的推荐商品
     * @description: 每个门店的推荐商品最多为5个，该查询不需要进行分页, 返回数据按是否置顶以及更新时间，创建时间进行排序
     * @param: [partyIdRequestVO]
     * @return com.hryj.common.Result<java.util.List<com.hryj.entity.vo.promotion.recommend.response.PartyRecommendProductItemResponseVO>>
     * @create 2018-06-28 16:06
     **/
    @RequestMapping(value = "/partyProductRecommendMgr/findPartyRecommendProductList", method = RequestMethod.POST)
    Result<ListResponseVO<PartyRecommendProductItemResponseVO>> findPartyRecommendProductList(@RequestBody PartyIdRequestVO partyIdRequestVO);

    /**
     * @author 王光银
     * @methodName: saveAppendManyPartyRecommendProduct
     * @methodDesc: 批量保存门店或仓库的推荐位商品
     * @description:
     * @param: [partyRecommendProdAppendRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-27 21:19
     **/
    @RequestMapping(value = "/partyProductRecommendMgr/saveAppendManyPartyRecommendProduct", method = RequestMethod.POST)
    Result saveAppendManyPartyRecommendProduct(@RequestBody PartyRecommendProdAppendRequestVO partyRecommendProdAppendRequestVO);

    /**
     * @author 王光银
     * @methodName: deleteOneFromPartyRecommendProduct
     * @methodDesc: 删除一个门店或仓库的推荐商品
     * @description: 如果指定的推荐商品不存在，直接返回成功
     * @param: [idRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-27 21:22
     **/
    @RequestMapping(value = "/partyProductRecommendMgr/deleteOneFromPartyRecommendProduct", method = RequestMethod.POST)
    Result deleteOneFromPartyRecommendProduct(@RequestBody IdRequestVO idRequestVO);

    /**
     * @author 王光银
     * @methodName: copyToOtherParty
     * @methodDesc: 复制一个门店或仓库的推荐商品到其他的门店或仓库
     * @description:  复制推荐商品时会排除掉复制目标门店或仓库没有销售的商品
     * @param: [copyRecommendProdToOtherPartyRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-27 21:35
     **/
    @RequestMapping(value = "/partyProductRecommendMgr/copyToOtherParty", method = RequestMethod.POST)
    Result copyToOtherParty(@RequestBody CopyRecommendProdToOtherPartyRequestVO copyRecommendProdToOtherPartyRequestVO);

    /**
     * @author 王光银
     * @methodName: topPartyRecommendProduct
     * @methodDesc: 置顶广告位商品
     * @description: 置顶广告位时更新时间，有多个置顶商品按照更新时间排序，越早的排在前面
     * @param: [idRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-04 9:19
     **/
    @RequestMapping(value = "/partyProductRecommendMgr/topPartyRecommendProduct", method = RequestMethod.POST)
    Result topPartyRecommendProduct(@RequestBody IdRequestVO idRequestVO);

    /**
     * @author 王光银
     * @methodName: topPartyRecommendProduct
     * @methodDesc: 撤消置顶广告位商品
     * @description:
     * @param: [idRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-04 9:19
     **/
    @RequestMapping(value = "/partyProductRecommendMgr/untopPartyRecommendProduct", method = RequestMethod.POST)
    Result untopPartyRecommendProduct(@RequestBody IdRequestVO idRequestVO);

    /**
     * @author 王光银
     * @methodName: partyProdStatistics
     * @methodDesc: 门店或仓库商品简单统计接口
     * @description:
     * @param: [partyProductStatisticsRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.ListResponseVO<com.hryj.entity.vo.product.partyprod.response.PartyProductStatisticsItem>>
     * @create 2018-07-05 21:13
     **/
    @RequestMapping(value = "/partyProductMgr/partyProdSimpleStatistics", method = RequestMethod.POST)
    Result<ListResponseVO<PartyProductStatisticsItem>> partyProdSimpleStatistics(
            @RequestBody PartyProductStatisticsRequestVO partyProductStatisticsRequestVO);

}
