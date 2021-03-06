package com.hryj.api.product.v1_2;

import com.alibaba.fastjson.JSON;
import com.hryj.common.CodeEnum;
import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.RequestVO;
import com.hryj.entity.vo.product.common.request.ProductValidateRequestVO;
import com.hryj.entity.vo.product.request.app.AppSearchProductRequestVO;
import com.hryj.entity.vo.product.response.app.AppProdListItemResponseVO;
import com.hryj.entity.vo.product.response.app.AppProductInfoResponseVO;
import com.hryj.feign.product.v1_2.ProductFeignClient;
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
 * @author 汪豪
 * @className: ProductController
 * @description:
 * @create 2018/9/11 0011 10:26
 **/
@Api(value="/v1-2/product", tags = "商品 - APP用户端 - V1.2")
@Slf4j
@RestController("v1.2-ProductController")
@RequestMapping("/v1-2/product")
public class ProductController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ProductFeignClient productFeignClient;


    /**
     * @author 王光银
     * @methodName: findRecommendProductList
     * @methodDesc:  App用户端查询推荐位商品列表
     * @description: 推荐商品返回列表不分页，商品返回数量的多少，排序都在接口内控制
     * @param: []
     * @return com.hryj.common.Result<com.hryj.entity.vo.ListResponseVO<com.hryj.entity.vo.product.response.AppProductListItemResponseVO>>
     * @create 2018-07-02 20:16
     **/
    @ApiOperation(value = "App用户端查询推荐位商品列表", notes = "推荐商品返回列表不分页，商品返回数量的多少，排序都在接口内控制, 当没有覆盖用户门店和仓库时返回 业务代码:15000")
    @PostMapping("/findRecommendProductList")
    public Result<ListResponseVO<AppProdListItemResponseVO>> findRecommendProductList(@RequestBody(required = false) RequestVO requestVO) {
        if (requestVO == null) {
            requestVO = new RequestVO();
        }
        StringBuilder json_str = new StringBuilder();
        try {
            WebUtil.getRequestVO(request, requestVO);
            json_str.append("v1.2 - App用户端查询推荐位商品列表-request: ");
            json_str.append(JSON.toJSONString(requestVO));
            json_str.append(System.lineSeparator());
            Result result = productFeignClient.findRecommendProductList(requestVO);
            json_str.append("v1.2 - App用户端查询推荐位商品列表-response: ");
            json_str.append(JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            log.error("v1.2 - App用户端查询推荐位商品列表接口异常", e);
            return new Result<>(CodeEnum.FAIL_SERVER, "系统繁忙请稍后再试");
        } finally {
            log.info(json_str.toString());
        }
    }

    /**
     * @author 王光银
     * @methodName: searchProductList
     * @methodDesc: App用户端商品搜素
     * @description:
     * @param: [appSearchProductRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.PageResponseVO<com.hryj.entity.vo.product.response.AppProductListItemResponseVO>>
     * @create 2018-06-30 9:33
     **/
    @ApiOperation(value = "App用户端商品搜素", notes = "当没有覆盖用户门店和仓库时返回 业务代码:15000")
    @PostMapping("/searchProductList")
    public Result<ListResponseVO<AppProdListItemResponseVO>> searchProductList(@RequestBody(required = false) AppSearchProductRequestVO appSearchProductRequestVO) {
        if (appSearchProductRequestVO == null) {
            appSearchProductRequestVO = new AppSearchProductRequestVO();
        }
        StringBuilder json_str = new StringBuilder();
        try {
            WebUtil.getRequestVO(request, appSearchProductRequestVO);
            json_str.append("v1.2 - App端商品搜素-request: ");
            json_str.append(JSON.toJSONString(appSearchProductRequestVO));
            json_str.append(System.lineSeparator());
            Result result = productFeignClient.searchProductList(appSearchProductRequestVO);
            json_str.append("v1.2 - App端商品搜素-response: ");
            json_str.append(JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            log.error("v1.2 - App用户端商品搜素接口异常", e);
            return new Result<>(CodeEnum.FAIL_SERVER, "系统繁忙请稍后再试");
        } finally {
            log.info(json_str.toString());
        }
    }

    /**
     * @author 王光银
     * @methodName: getProductInfo
     * @methodDesc: APP用户端获取商品的详细信息
     * @description: 商品下架返回 biz_code=11000,  活动结束返回 biz_code=11010，库存数量不足返回 biz_code=11020，这三个业务上的非正常情况编码不会影响其他数据的正常返回
     * @param: [productValidateRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.product.response.AppProductInfoResponseVO>
     * @create 2018-06-30 18:30
     **/
    @ApiOperation(value = "APP用户端获取商品的详细信息", notes = "获取商品的详细信息，商品下架返回 biz_code=11000,  活动结束返回 biz_code=11010，库存数量不足返回 biz_code=11020，这三个业务上的非正常情况编码不会影响其他数据的正常返回")
    @PostMapping("/getProductInfo")
    public Result<AppProductInfoResponseVO> getProductInfo(@RequestBody(required = false) ProductValidateRequestVO productValidateRequestVO) {
        if (productValidateRequestVO == null) {
            productValidateRequestVO = new ProductValidateRequestVO();
        }
        StringBuilder json_str = new StringBuilder();
        try {
            WebUtil.getRequestVO(request, productValidateRequestVO);
            json_str.append("v1.2 - APP用户端获取商品的详细信息-request: ");
            json_str.append(JSON.toJSONString(productValidateRequestVO));
            json_str.append(System.lineSeparator());
            Result result = productFeignClient.getProductInfo(productValidateRequestVO);
            json_str.append("v1.2 - APP用户端获取商品的详细信息-response: ");
            json_str.append(JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            log.error("v1.2 - APP用户端获取商品的详细信息接口异常", e);
            return new Result<>(CodeEnum.FAIL_SERVER, "系统繁忙请稍后再试");
        } finally {
            log.info(json_str.toString());
        }
    }


}
