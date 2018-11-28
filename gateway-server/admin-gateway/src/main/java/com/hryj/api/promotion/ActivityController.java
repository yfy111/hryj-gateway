package com.hryj.api.promotion;

import com.alibaba.fastjson.JSON;
import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.PageResponseVO;
import com.hryj.entity.vo.promotion.activity.request.*;
import com.hryj.entity.vo.promotion.activity.response.*;
import com.hryj.feign.ActivityFeignClient;
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
 * @className: PromotionActivityController
 * @description: 促销活动管理接口，该类型接口只适用于后台管理
 * @create 2018/6/27 0027 21:43
 **/
@Api(value="/promontion/activityMgr", tags = "活动管理")
@Slf4j
@RestController
@RequestMapping("/promontion/activityMgr")
public class ActivityController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ActivityFeignClient activityFeignClient;

    /**
     * @author 汪豪
     * @methodName: searchPromotionActivityPage
     * @methodDesc: 分页查询促销活动
     * @description: 该接口同时适用于活动管理与活动审核管理使用
     * @param: [searchPromotionActivityRequestVO]
     * @return com.hryj.common.PageResult<com.hryj.entity.vo.promotion.activity.response.SearchPromotionActivityItemResponseVO>
     * @create 2018-06-28 15:53
     **/
    @ApiOperation(value = "分页查询促销活动", notes = "该接口同时适用于活动管理与活动审核管理使用")
    @PostMapping("/searchPromotionActivityPage")
    public Result<PageResponseVO<SearchPromotionActivityItemResponseVO>> searchPromotionActivityPage(@RequestBody SearchPromotionActivityRequestVO searchPromotionActivityRequestVO) {
        WebUtil.getRequestVO(request,searchPromotionActivityRequestVO);
        log.info("分页查询促销活动:searchPromotionActivityRequestVO====="+JSON.toJSONString(searchPromotionActivityRequestVO));
        Result<PageResponseVO<SearchPromotionActivityItemResponseVO>> result = activityFeignClient.searchPromotionActivityPage(searchPromotionActivityRequestVO);
        log.info("分页查询促销活动:result====="+JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 汪豪
     * @methodName: getPromotionActivity
     * @methodDesc: 根据活动ID返回活动详细信息
     * @description: include_party是否返回参与门店或仓库，true返回，false不返回， include_product是否返回参与产品信息，true返回，false不返回,include_audit_record是否返回审核记录，true返回，false不返回, 全部默认false
     * @param: [romotionActivityDetailRequestVo]
     * @return com.hryj.common.Result<com.hryj.entity.vo.promotion.activity.response.PromotionActivityDataResponseVO>
     * @create 2018-06-28 17:05
     **/
    @ApiOperation(value = "根据活动ID返回活动详细信息", notes = "include_party是否返回参与门店或仓库，true返回，false不返回， include_product是否返回参与产品信息，true返回，false不返回,include_audit_record是否返回审核记录，true返回，false不返回")
    @PostMapping("/getPromotionActivity")
    public Result<PromotionActivityDataResponseVO> getPromotionActivity(@RequestBody PromotionActivityDetailRequestVO promotionActivityDetailRequestVO) {
        log.info("根据活动ID返回活动详细信息:promotionActivityDetailRequestVO====="+JSON.toJSONString(promotionActivityDetailRequestVO));
        Result<PromotionActivityDataResponseVO> result = activityFeignClient.getPromotionActivity(promotionActivityDetailRequestVO);
        log.info("根据活动ID返回活动详细信息:resutl====="+JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 汪豪
     * @methodName: searchPromotionActivityJoinPartyData
     * @methodDesc: 查询活动参与门店或仓库信息
     * @description: 参与门店可能比较多，是以分页查询方式处理
     * @param: [searchActivityJoinPartyRequestVO]
     * @return com.hryj.common.PageResult<com.hryj.entity.vo.promotion.activity.response.PromotionActivityJoinPartyDataResponseVO>
     * @create 2018-06-28 15:53
     **/
    @ApiOperation(value = "查询活动参与门店或仓库信息", notes = "参与门店可能比较多，是以分页查询方式处理")
    @PostMapping("/searchPromotionActivityJoinPartyData")
    public Result<PageResponseVO<PromotionActivityJoinPartyDataResponseVO>> searchPromotionActivityJoinPartyData(@RequestBody SearchActivityJoinPartyRequestVO searchActivityJoinPartyRequestVO) {
        log.info("查询活动参与门店或仓库信息:searchActivityJoinPartyRequestVO====="+JSON.toJSONString(searchActivityJoinPartyRequestVO));
        Result<PageResponseVO<PromotionActivityJoinPartyDataResponseVO>> result = activityFeignClient.searchPromotionActivityJoinPartyData(searchActivityJoinPartyRequestVO);
        log.info("查询活动参与门店或仓库信息:resutl====="+JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 汪豪
     * @methodName: searchPromotionActivityJoinProductData
     * @methodDesc: 查询活动参与产品信息
     * @description: 参与产品可能比较多，是以分页查询方式处理
     * @param: [searchActivityJoinProductRequestVO]
     * @return com.hryj.common.PageResult<com.hryj.entity.vo.promotion.activity.response.JoinProductItemResponseVO>
     * @create 2018-06-28 15:54
     **/
    @ApiOperation(value = "查询活动参与产品信息", notes = "参与产品可能比较多，是以分页查询方式处理")
    @PostMapping("/searchPromotionActivityJoinProductData")
    public Result<PageResponseVO<JoinProductItemResponseVO>> searchPromotionActivityJoinProductData(@RequestBody SearchActivityJoinProductRequestVO searchActivityJoinProductRequestVO) {
        log.info("查询活动参与产品信息:searchActivityJoinProductRequestVO====="+JSON.toJSONString(searchActivityJoinProductRequestVO));
        Result<PageResponseVO<JoinProductItemResponseVO>> result = activityFeignClient.searchPromotionActivityJoinProductData(searchActivityJoinProductRequestVO);
        log.info("查询活动参与产品信息:resutl====="+JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 汪豪
     * @methodName: findPromotionActivityAuditRecord
     * @methodDesc: 查询活动的审核记录信息
     * @description:
     * @param: [onlyActivityIdRequestVo]
     * @return com.hryj.common.Result<com.hryj.entity.vo.promotion.activity.response.PromotionActivityAuditRecordResponseVO>
     * @create 2018-06-28 17:08
     **/
    @ApiOperation(value = "查询活动的审核记录信息", notes = "查询活动的审核记录信息")
    @PostMapping("/findPromotionActivityAuditRecord")
    public Result<ListResponseVO<PromotionActivityAuditRecordResponseVO>> findPromotionActivityAuditRecord(@RequestBody OnlyActivityIdRequestVO onlyActivityIdRequestVo) {
        log.info("查询活动的审核记录信息:onlyActivityIdRequestVo====="+JSON.toJSONString(onlyActivityIdRequestVo));
        Result<ListResponseVO<PromotionActivityAuditRecordResponseVO>> result = activityFeignClient.findPromotionActivityAuditRecord(onlyActivityIdRequestVo);
        log.info("查询活动的审核记录信息:resutl====="+JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 汪豪
     * @methodName: saveCreatePromotionActivity
     * @methodDesc: 保存创建一个新的促销活动
     * @description:
     * @param: [promotionActivityRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-28 11:36
     **/
    @ApiOperation(value = "保存促销活动", notes = "保存创建一个新的促销活动")
    @PostMapping("/saveCreatePromotionActivity")
    public Result saveCreatePromotionActivity(@RequestBody PromotionActivityRequestVO promotionActivityRequestVO) {
        WebUtil.getRequestVO(request,promotionActivityRequestVO);
        log.info("保存促销活动:promotionActivityRequestVO====="+JSON.toJSONString(promotionActivityRequestVO));
        Result result = activityFeignClient.saveCreatePromotionActivity(promotionActivityRequestVO);
        log.info("保存促销活动:resutl====="+JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 汪豪
     * @methodName: updatePromotionActivity
     * @methodDesc: 保存修改一个促销活动
     * @description: 活动审核通过后不能再进行修改，活动基本信息采用更新方式，参与门店或仓库，参与商品以先删除后新增的方式处理
     * @param: [promotionActivityRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-28 11:37
     **/
    @ApiOperation(value = "保存修改一个促销活动", notes = "修改活动使用的VO与新增一致，修改处理时除活动本身的基本信息做修改外，活动的门店仓库活动范围和活动商品都是先删除再新增的处理方式")
    @PostMapping("/updatePromotionActivity")
    public Result updatePromotionActivity(@RequestBody PromotionActivityRequestVO promotionActivityRequestVO) {
        WebUtil.getRequestVO(request,promotionActivityRequestVO);
        log.info("保存修改一个促销活动:promotionActivityRequestVO====="+JSON.toJSONString(promotionActivityRequestVO));
        Result result = activityFeignClient.updatePromotionActivity(promotionActivityRequestVO);
        log.info("保存修改一个促销活动:result====="+JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 汪豪
     * @methodName: updatePromotionActivityBase
     * @methodDesc: 修改活动的基本信息
     * @description: 活动审核通过后不能再进行修改
     * @param: [promotionActivityBaseRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-28 11:44
     **/
    @ApiOperation(value = "修改活动的基本信息", notes = "基本信息的范围是不影响活动的应用范围，不影响活动的参与商品")
    @PostMapping("/updatePromotionActivityBase")
    public Result updatePromotionActivityBase(@RequestBody PromotionActivityBaseRequestVO promotionActivityBaseRequestVO) {
        WebUtil.getRequestVO(request,promotionActivityBaseRequestVO);
        log.info("修改活动的基本信息:promotionActivityBaseRequestVO====="+JSON.toJSONString(promotionActivityBaseRequestVO));
        Result result = activityFeignClient.updatePromotionActivityBase(promotionActivityBaseRequestVO);
        log.info("修改活动的基本信息:result====="+JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 汪豪
     * @methodName: deleteOnePartyFormScope
     * @methodDesc: 删除一个指定的活动应用范围（门店或仓库）
     * @description: 活动审核通过后不能删除
     * @param: [activityScopeItemIdRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-28 11:47
     **/
    @ApiOperation(value = "删除一个指定的活动应用范围（门店或仓库）", notes = "删除活动门店或仓库不会影响商品，商品是参与门店或仓库的商品交集")
    @PostMapping("/deleteOnePartyFormScope")
    public Result deleteOnePartyFormScope(@RequestBody ActivityScopeItemIdRequestVO activityScopeItemIdRequestVO) {
        log.info("删除一个指定的活动应用范围（门店或仓库）:activityScopeItemIdRequestVO====="+JSON.toJSONString(activityScopeItemIdRequestVO));
        Result result = activityFeignClient.deleteOnePartyFormScope(activityScopeItemIdRequestVO);
        log.info("删除一个指定的活动应用范围（门店或仓库）:result====="+JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 汪豪
     * @methodName: deleteOneProductFormScope
     * @methodDesc: 删除一个指定的活动参与商品
     * @description: 活动审核通过后不能删除
     * @param: [promotionActivityAppendProdRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-28 11:52
     **/
    @ApiOperation(value = "删除一个指定的活动参与商品", notes = "删除活动门店或仓库不会影响商品，商品是参与门店或仓库的商品交集")
    @PostMapping("/deleteOneProductFormScope")
    public Result deleteOneProductFormScope(@RequestBody PromotionActivityAppendProdRequestVO promotionActivityAppendProdRequestVO) {
        log.info("删除一个指定的活动参与商品:promotionActivityAppendProdRequestVO====="+JSON.toJSONString(promotionActivityAppendProdRequestVO));
        Result result = activityFeignClient.deleteOneProductFormScope(promotionActivityAppendProdRequestVO);
        log.info("删除一个指定的活动参与商品:result====="+JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 汪豪
     * @methodName: appendManyProductToActivity
     * @methodDesc: 向活动追加商品
     * @description: 活动审核通过后不能追加商品
     * @param: [promotionActivityAppendProdRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-28 11:56
     **/
    @ApiOperation(value = "向活动追加商品", notes = "向活动追加商品")
    @PostMapping("/appendManyProductToActivity")
    public Result appendManyProductToActivity(@RequestBody PromotionActivityAppendProdRequestVO promotionActivityAppendProdRequestVO) {
        log.info("向活动追加商品:promotionActivityAppendProdRequestVO====="+JSON.toJSONString(promotionActivityAppendProdRequestVO));
        Result result = activityFeignClient.appendManyProductToActivity(promotionActivityAppendProdRequestVO);
        log.info("向活动追加商品:result====="+JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 汪豪
     * @methodName: upMoveOneJoinProduct
     * @methodDesc: 上移一个产品在活动中的排序位置
     * @description:
     * @param: [activityProductItemIdRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-28 14:43
     **/
    @ApiOperation(value = "上移一个产品在活动中的排序位置", notes = "上移一个产品在活动中的排序位置")
    @PostMapping("/upMoveOneJoinProduct")
    public Result upMoveOneJoinProduct(@RequestBody ActivityProductItemIdRequestVO activityProductItemIdRequestVO) {
        log.info("上移一个产品在活动中的排序位置:activityProductItemIdRequestVO====="+JSON.toJSONString(activityProductItemIdRequestVO));
        Result result = activityFeignClient.upMoveOneJoinProduct(activityProductItemIdRequestVO);
        log.info("上移一个产品在活动中的排序位置:result====="+JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 汪豪
     * @methodName: downMoveJoinProduct
     * @methodDesc: 下移一个产品在活动中的排序位置
     * @description:
     * @param: [activityProductItemIdRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-28 14:44
     **/
    @ApiOperation(value = "下移一个产品在活动中的排序位置", notes = "下移一个产品在活动中的排序位置")
    @PostMapping("/downMoveJoinProduct")
    public Result downMoveJoinProduct(@RequestBody ActivityProductItemIdRequestVO activityProductItemIdRequestVO) {
        log.info("下移一个产品在活动中的排序位置:activityProductItemIdRequestVO====="+JSON.toJSONString(activityProductItemIdRequestVO));
        Result result = activityFeignClient.downMoveJoinProduct(activityProductItemIdRequestVO);
        log.info("下移一个产品在活动中的排序位置:result====="+JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 汪豪
     * @methodName: enablePomotionActivity
     * @methodDesc: 启用一个促销活动
     * @description:
     * @param: [onlyActivityIdRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-10 13:53
     **/
    @ApiOperation(value = "启用一个促销活动", notes = "启用一个促销活动")
    @PostMapping("/enablePomotionActivity")
    public Result enablePomotionActivity(@RequestBody OnlyActivityIdRequestVO onlyActivityIdRequestVO) {
        return activityFeignClient.enablePomotionActivity(onlyActivityIdRequestVO);
    }

    /**
     * @author 汪豪
     * @methodName: disablePomotionActivity
     * @methodDesc: 停用一个促销活动
     * @description:
     * @param: [onlyActivityIdRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-10 13:53
     **/
    @ApiOperation(value = "停用一个促销活动", notes = "停用一个促销活动")
    @PostMapping("/disablePomotionActivity")
    public Result disablePomotionActivity(@RequestBody OnlyActivityIdRequestVO onlyActivityIdRequestVO) {
        return activityFeignClient.disablePomotionActivity(onlyActivityIdRequestVO);
    }

}
