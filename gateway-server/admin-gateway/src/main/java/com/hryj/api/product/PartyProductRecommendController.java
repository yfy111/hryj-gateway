package com.hryj.api.product;

import com.alibaba.fastjson.JSON;
import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.product.partyprod.request.IdRequestVO;
import com.hryj.entity.vo.product.partyprod.request.PartyIdRequestVO;
import com.hryj.entity.vo.promotion.recommend.request.CopyRecommendProdToOtherPartyRequestVO;
import com.hryj.entity.vo.promotion.recommend.request.PartyRecommendProdAppendRequestVO;
import com.hryj.entity.vo.promotion.recommend.response.PartyRecommendProductItemResponseVO;
import com.hryj.feign.PartyProductFeignClient;
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
 * @className: RecommendController
 * @description: 推荐管理
 * @create 2018/6/27 20:49
 **/
@Api(value = "/product/partyProductRecommendMgr", tags = "商品 - 推荐位商品管理")
@Slf4j
@RestController
@RequestMapping("/product/partyProductRecommendMgr")
public class PartyProductRecommendController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PartyProductFeignClient partyProductFeignClient;

    /**
     * @return com.hryj.common.Result<java.util.List<com.hryj.entity.vo.promotion.recommend.response.PartyRecommendProductItemResponseVO>>
     * @author 王光银
     * @methodName: findPartyRecommendProductList
     * @methodDesc: 查询返回指定门店或仓库的推荐商品
     * @description: 每个门店的推荐商品最多为5个，该查询不需要进行分页
     * @param: [partyIdRequestVO]
     * @create 2018-06-28 16:06
     **/
    @ApiOperation(value = "查询返回指定门店或仓库的推荐商品", notes = "每个门店的推荐商品最多为5个，该查询不需要进行分页")
    @PostMapping("/findPartyRecommendProductList")
    public Result<ListResponseVO<PartyRecommendProductItemResponseVO>> findPartyRecommendProductList(
            @RequestBody PartyIdRequestVO partyIdRequestVO) {
        WebUtil.getRequestVO(request, partyIdRequestVO);
        log.info("查询返回指定门店或仓库的推荐商品 -- request:" + JSON.toJSONString(partyIdRequestVO));
        Result result = partyProductFeignClient.findPartyRecommendProductList(partyIdRequestVO);
        log.info("查询返回指定门店或仓库的推荐商品 -- response:" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result
     * @author 王光银
     * @methodName: saveAppendManyPartyRecommendProduct
     * @methodDesc: 批量保存门店或仓库的推荐位商品
     * @description:
     * @param: [partyRecommendProdAppendRequestVO]
     * @create 2018-06-27 21:19
     **/
    @ApiOperation(value = "批量保存门店或仓库的推荐位商品", notes = "批量保存门店或仓库的推荐位商品")
    @PostMapping("/saveAppendManyPartyRecommendProduct")
    public Result saveAppendManyPartyRecommendProduct(@RequestBody PartyRecommendProdAppendRequestVO partyRecommendProdAppendRequestVO) {
        WebUtil.getRequestVO(request, partyRecommendProdAppendRequestVO);
        log.info("批量保存门店或仓库的推荐位商品 -- request:" + JSON.toJSONString(partyRecommendProdAppendRequestVO));
        Result result = partyProductFeignClient.saveAppendManyPartyRecommendProduct(partyRecommendProdAppendRequestVO);
        log.info("批量保存门店或仓库的推荐位商品 -- response:" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result
     * @author 王光银
     * @methodName: deleteOneFromPartyRecommendProduct
     * @methodDesc: 删除一个门店或仓库的推荐商品
     * @description: 如果指定的推荐商品不存在，直接返回成功
     * @param: [idRequestVO]
     * @create 2018-06-27 21:22
     **/
    @ApiOperation(value = "删除一个门店或仓库的推荐商品", notes = "如果指定的推荐商品不存在，直接返回成功")
    @PostMapping("/deleteOneFromPartyRecommendProduct")
    public Result deleteOneFromPartyRecommendProduct(@RequestBody IdRequestVO idRequestVO) {
        WebUtil.getRequestVO(request, idRequestVO);
        log.info("删除一个门店或仓库的推荐商品 -- request:" + JSON.toJSONString(idRequestVO));
        Result result = partyProductFeignClient.deleteOneFromPartyRecommendProduct(idRequestVO);
        log.info("删除一个门店或仓库的推荐商品 -- response:" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result
     * @author 王光银
     * @methodName: copyToOtherParty
     * @methodDesc: 复制一个门店或仓库的推荐商品到其他的门店或仓库
     * @description:
     * @param: [copyRecommendProdToOtherPartyRequestVO]
     * @create 2018-06-27 21:35
     **/
    @ApiOperation(value = "复制一个门店或仓库的推荐商品到其他的门店或仓库", notes = "复制一个门店或仓库的推荐商品到其他的门店或仓库")
    @PostMapping("/copyToOtherParty")
    public Result copyToOtherParty(@RequestBody CopyRecommendProdToOtherPartyRequestVO copyRecommendProdToOtherPartyRequestVO) {
        WebUtil.getRequestVO(request, copyRecommendProdToOtherPartyRequestVO);
        log.info("复制一个门店或仓库的推荐商品到其他的门店或仓库 -- request:" + JSON.toJSONString(copyRecommendProdToOtherPartyRequestVO));
        Result result = partyProductFeignClient.copyToOtherParty(copyRecommendProdToOtherPartyRequestVO);
        log.info("复制一个门店或仓库的推荐商品到其他的门店或仓库 -- response:" + JSON.toJSONString(result));
        return result;
    }
}
