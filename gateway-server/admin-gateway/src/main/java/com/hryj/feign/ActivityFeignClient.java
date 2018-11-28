package com.hryj.feign;

import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.PageResponseVO;
import com.hryj.entity.vo.promotion.activity.request.*;
import com.hryj.entity.vo.promotion.activity.response.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 汪豪
 * @className: ActivityFeignClient
 * @description: 促销活动feign接口
 * @create 2018/7/6 0006 9:43
 **/
@FeignClient(name = "promotion-server")
public interface ActivityFeignClient {

    /**
     * @author 汪豪
     * @methodName: searchPromotionActivityAuditRecord
     * @methodDesc: 查询促销活动的所有审核记录
     * @description:
     * @param: [searchPromotionActivityRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.PageResponseVO<com.hryj.entity.vo.promotion.activity.response.PromotionActivityAuditRecordResponseVO>>
     * @create 2018-06-30 9:23
     **/
    @RequestMapping("/activityAuditMgr/searchActivityAuditRecord")
    Result<PageResponseVO<PromotionActivityAuditRecordResponseVO>> searchPromotionActivityAuditRecord(@RequestBody SearchPromotionActivityRequestVO searchPromotionActivityRequestVO);

    /**
     * @author 汪豪
     * @methodName: submitActivityHandleResult
     * @methodDesc: 提交促销活动审核处理结果
     * @description:
     * @param: [submitActivityRequestVo]
     * @return com.hryj.common.Result
     * @create 2018-06-28 17:03
     **/
    @RequestMapping("/activityAuditMgr/submitActivityHandleResult")
    Result submitActivityHandleResult(@RequestBody SubmitActivityRequestVO submitActivityRequestVo);

    /**
     * @author 汪豪
     * @methodName: searchPromotionActivityPage
     * @methodDesc: 分页查询促销活动
     * @description: 该接口同时适用于活动管理与活动审核管理使用
     * @param: [searchPromotionActivityRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.PageResponseVO<com.hryj.entity.vo.promotion.activity.response.SearchPromotionActivityItemResponseVO>>
     * @create 2018-06-30 9:20
     **/
    @RequestMapping("/promotionActivityMgr/searchActivityPage")
    Result<PageResponseVO<SearchPromotionActivityItemResponseVO>> searchPromotionActivityPage(@RequestBody SearchPromotionActivityRequestVO searchPromotionActivityRequestVO);

    /**
     * @author 汪豪
     * @methodName: getPromotionActivity
     * @methodDesc:  根据活动ID返回活动详细信息
     * @description: include_party是否返回参与门店或仓库，true返回，false不返回， include_product是否返回参与产品信息，true返回，false不返回,include_audit_record是否返回审核记录，true返回，false不返回
     * @param: [romotionActivityDetailRequestVo]
     * @return com.hryj.common.Result<com.hryj.entity.vo.promotion.activity.response.PromotionActivityDataResponseVO>
     * @create 2018-07-05 21:23
     **/
    @RequestMapping("/promotionActivityMgr/getActivity")
    Result<PromotionActivityDataResponseVO> getPromotionActivity(@RequestBody PromotionActivityDetailRequestVO promotionActivityDetailRequestVO);

    /**
     * @author 汪豪
     * @methodName: searchPromotionActivityJoinPartyData
     * @methodDesc: 查询活动参与门店或仓库信息
     * @description: 参与门店可能比较多，是以分页查询方式处理
     * @param: [searchActivityJoinPartyRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.PageResponseVO<com.hryj.entity.vo.promotion.activity.response.PromotionActivityJoinPartyDataResponseVO>>
     * @create 2018-06-30 9:21
     **/
    @RequestMapping("/promotionActivityMgr/searchActivityJoinPartyData")
    Result<PageResponseVO<PromotionActivityJoinPartyDataResponseVO>> searchPromotionActivityJoinPartyData(@RequestBody SearchActivityJoinPartyRequestVO searchActivityJoinPartyRequestVO);

    /**
     * @author 汪豪
     * @methodName: searchPromotionActivityJoinProductData
     * @methodDesc: 查询活动参与产品信息
     * @description: 参与产品可能比较多，是以分页查询方式处理
     * @param: [searchActivityJoinProductRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.PageResponseVO<com.hryj.entity.vo.promotion.activity.response.JoinProductItemResponseVO>>
     * @create 2018-06-30 9:22
     **/
    @RequestMapping("/promotionActivityMgr/searchActivityJoinProductData")
    Result<PageResponseVO<JoinProductItemResponseVO>> searchPromotionActivityJoinProductData(@RequestBody SearchActivityJoinProductRequestVO searchActivityJoinProductRequestVO);

    /**
     * @author 汪豪
     * @methodName: findActivityAuditRecord
     * @methodDesc: 查询活动的审核记录信息
     * @description:
     * @param: [onlyActivityIdRequestVo]
     * @return com.hryj.common.Result<com.hryj.entity.vo.promotion.activity.response.PromotionActivityAuditRecordResponseVO>
     * @create 2018-06-28 17:08
     **/
    @RequestMapping("/promotionActivityMgr/findActivityAuditRecord")
    Result<ListResponseVO<PromotionActivityAuditRecordResponseVO>> findPromotionActivityAuditRecord(@RequestBody OnlyActivityIdRequestVO onlyActivityIdRequestVo);

    /**
     * @author 王光银
     * @methodName: saveCreateActivity
     * @methodDesc: 保存创建一个新的促销活动
     * @description:
     * @param: [promotionActitivyRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-28 11:36
     **/
    @RequestMapping("/promotionActivityMgr/saveCreateActivity")
    Result saveCreatePromotionActivity(@RequestBody PromotionActivityRequestVO promotionActitivyRequestVO);

    /**
     * @author 王光银
     * @methodName: updateActivity
     * @methodDesc: 保存修改一个促销活动
     * @description: 活动审核通过后不能再进行修改，活动基本信息采用更新方式，参与门店或仓库，参与商品以先删除后新增的方式处理
     * @param: [promotionActitivyRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-28 11:37
     **/
    @RequestMapping("/promotionActivityMgr/updateActivity")
    Result updatePromotionActivity(@RequestBody PromotionActivityRequestVO promotionActitivyRequestVO);

    /**
     * @author 王光银
     * @methodName: updateActivityBase
     * @methodDesc: 修改活动的基本信息
     * @description: 活动审核通过后不能再进行修改
     * @param: [promotionActivityBaseRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-28 11:44
     **/
    @RequestMapping("/promotionActivityMgr/updateActivityBase")
    Result updatePromotionActivityBase(@RequestBody PromotionActivityBaseRequestVO promotionActivityBaseRequestVO);

    /**
     * @author 汪豪
     * @methodName: deleteOnePartyFormScope
     * @methodDesc: 删除一个指定的活动应用范围（门店或仓库）
     * @description: 活动审核通过后不能删除
     * @param: [activityScopeItemIdRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-28 11:47
     **/
    @RequestMapping("/promotionActivityMgr/deleteOnePartyFormScope")
    Result deleteOnePartyFormScope(@RequestBody ActivityScopeItemIdRequestVO activityScopeItemIdRequestVO);

    /**
     * @author 汪豪
     * @methodName: deleteOneProductFormScope
     * @methodDesc: 删除一个指定的活动参与商品
     * @description: 活动审核通过后不能删除
     * @param: [activityProductItemIdRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-28 11:52
     **/
    @RequestMapping("/promotionActivityMgr/deleteOneProductFormScope")
    Result deleteOneProductFormScope(@RequestBody PromotionActivityAppendProdRequestVO promotionActivityAppendProdRequestVO) ;

    /**
     * @author 王光银
     * @methodName: appendManyProductToActivity
     * @methodDesc: 向活动追加商品
     * @description: 活动审核通过后不能追加商品
     * @param: [promotionActivityAppendProdRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-28 11:56
     **/
    @RequestMapping("/promotionActivityMgr/appendManyProductToActivity")
    Result appendManyProductToActivity(@RequestBody PromotionActivityAppendProdRequestVO promotionActivityAppendProdRequestVO) ;

    /**
     * @author 汪豪
     * @methodName: upMoveOneJoinProduct
     * @methodDesc: 上移一个产品在活动中的排序位置
     * @description:
     * @param: [activityProductItemIdRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-05 21:40
     **/
    @RequestMapping("/promotionActivityMgr/upMoveOneJoinProduct")
    Result upMoveOneJoinProduct(@RequestBody ActivityProductItemIdRequestVO activityProductItemIdRequestVO);

    /**
     * @author 汪豪
     * @methodName: downMoveJoinProduct
     * @methodDesc: 下移一个产品在活动中的排序位置
     * @description:
     * @param: [activityProductItemIdRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-05 21:40
     **/
    @RequestMapping("/promotionActivityMgr/downMoveJoinProduct")
    Result downMoveJoinProduct(@RequestBody ActivityProductItemIdRequestVO activityProductItemIdRequestVO);

    /**
     * @author 汪豪
     * @methodName: enablePomotionActivity
     * @methodDesc: 启用一个促销活动
     * @description:
     * @param: [onlyActivityIdRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-10 13:53
     **/
    @PostMapping("/promotionActivityMgr/enablePomotionActivity")
    Result enablePomotionActivity(@RequestBody OnlyActivityIdRequestVO onlyActivityIdRequestVO);

    /**
     * @author 汪豪
     * @methodName: disablePomotionActivity
     * @methodDesc: 停用一个促销活动
     * @description:
     * @param: [onlyActivityIdRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-10 13:53
     **/
    @PostMapping("/promotionActivityMgr/disablePomotionActivity")
    Result disablePomotionActivity(@RequestBody OnlyActivityIdRequestVO onlyActivityIdRequestVO);

}
