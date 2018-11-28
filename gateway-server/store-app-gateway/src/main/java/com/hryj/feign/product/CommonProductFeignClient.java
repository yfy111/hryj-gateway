package com.hryj.feign.product;

import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.product.common.request.TopSaleRequestVO;
import com.hryj.entity.vo.product.response.ProductTopSalesItemResponseVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 王光银
 * @className: ProductFeignClient
 * @description: 商品公共服务feign-client, 这些服务没有版本区别
 * @create 2018/6/26 17:19
 **/
@FeignClient(name = "product-server")
public interface CommonProductFeignClient {

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.ListResponseVO<com.hryj.entity.vo.product.response.ProductTopSalesItemResponseVO>>
     * @author 王光银
     * @methodName: findTopSalesProduct
     * @methodDesc: APP门店查询TOP销量商品（不包含已下架商品）
     * @description: 该接口的统计范围是在统计时刻仍然在销售的商品，已经下架的商品不管销售多高不会返回，同时参数party_id有值返回指定门店或仓库的TOP销售商品，否则返回全国范围内的TOP销量商品，目前返回的商品数量为10个
     * @param: [partyIdRequestVO]
     * @create 2018-07-03 21:45
     **/
    @RequestMapping(value = "/productCommon/findTopSalesProduct", method = RequestMethod.POST)
    Result<ListResponseVO<ProductTopSalesItemResponseVO>> findTopSalesProduct(@RequestBody TopSaleRequestVO topSaleRequestVO);

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.ListResponseVO<com.hryj.entity.vo.product.response.ProductTopSalesItemResponseVO>>
     * @author 王光银
     * @methodName: findTopSalesProduct
     * @methodDesc: APP门店查询TOP销量商品(包含已下架商品)
     * @description: 该接口的统计范围是只按照销量统计，不管商品是否下架，返回集合按照销量排序， 参数 party_id有值返回指定门店或仓库的TOP销售商品，否则返回全国范围内的TOP销量商品，目前返回的商品数量为10个
     * @param: [partyIdRequestVO]
     * @create 2018-07-03 21:45
     **/
    @RequestMapping(value = "/productCommon/findTopSalesAndHisProduct", method = RequestMethod.POST)
    Result<ListResponseVO<ProductTopSalesItemResponseVO>> findTopSalesAndHisProduct(@RequestBody TopSaleRequestVO topSaleRequestVO);
}
