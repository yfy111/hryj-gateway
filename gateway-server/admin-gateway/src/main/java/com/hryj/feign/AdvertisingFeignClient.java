package com.hryj.feign;

import com.hryj.common.Result;
import com.hryj.entity.vo.PageResponseVO;
import com.hryj.entity.vo.promotion.activity.request.AdvertisingPositionIdRequestVO;
import com.hryj.entity.vo.promotion.activity.request.AdvertisingPositionScopeIdListRequestVO;
import com.hryj.entity.vo.promotion.activity.request.AdvertisingPositionScopeIdRequestVO;
import com.hryj.entity.vo.promotion.advertisingposition.request.AdvertisingPositionDetailRequestVO;
import com.hryj.entity.vo.promotion.advertisingposition.request.AdvertisingPositionRequestVO;
import com.hryj.entity.vo.promotion.advertisingposition.request.SearchAdvertisingPartyDataRequestVO;
import com.hryj.entity.vo.promotion.advertisingposition.request.SearchAdvertisingPositionRequestVO;
import com.hryj.entity.vo.promotion.advertisingposition.response.AdvertisingPositionDataResponseVO;
import com.hryj.entity.vo.promotion.advertisingposition.response.AdvertisingPositionItemResponseVO;
import com.hryj.entity.vo.promotion.advertisingposition.response.AdvertisingPositionJoinPartyDataResponseVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 汪豪
 * @className: AdvertisingFeignClient
 * @description:
 * @create 2018/7/11 0011 10:44
 **/
@FeignClient(name = "promotion-server")
public interface AdvertisingFeignClient {

    /**
     * @author 汪豪
     * @methodName: searchAdvertisingPositionPage
     * @methodDesc: 分页查询广告位
     * @description:
     * @param: [searchAdvertisingPositionRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.PageResponseVO<com.hryj.entity.vo.promotion.advertisingposition.response.AdvertisingPositionItemResponseVO>>
     * @create 2018-06-30 9:23
     **/
    @RequestMapping("/advertisingMgr/searchAdvertisingPositionPage")
    Result<PageResponseVO<AdvertisingPositionItemResponseVO>> searchAdvertisingPositionPage(@RequestBody SearchAdvertisingPositionRequestVO searchAdvertisingPositionRequestVO);

    /**
     * @author 汪豪
     * @methodName: getAdvertisingPosition
     * @methodDesc:  根据广告ID返回广告详细信息
     * @description: include_party是否返回参与门店或仓库，true返回，false不返回， include_jump_config是否返回跳转配置信息：true返回，false不返回
     * @param: [advertisingPositionDetailRequestVO]
     * @return Result<AdvertisingPositionDataResponseVO>
     * @create 2018-07-05 21:23
     **/
    @RequestMapping("/advertisingMgr/getAdvertisingPosition")
    Result<AdvertisingPositionDataResponseVO> getAdvertisingPosition(@RequestBody AdvertisingPositionDetailRequestVO advertisingPositionDetailRequestVO);

    /**
     * @author 汪豪
     * @methodName: searchAdvertisingJoinPartyData
     * @methodDesc: 查询广告位参与门店或仓库信息
     * @description:
     * @param: [searchAdvertisingPartyDataRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.promotion.advertisingposition.response.AdvertisingPositionJoinPartyDataResponseVO>
     * @create 2018-07-17 10:11
     **/
    @RequestMapping("/advertisingMgr/searchAdvertisingJoinPartyData")
    Result<AdvertisingPositionJoinPartyDataResponseVO> searchAdvertisingJoinPartyData(@RequestBody SearchAdvertisingPartyDataRequestVO searchAdvertisingPartyDataRequestVO);

    /**
     * @author 汪豪
     * @methodName: saveCreateAdvertisingPosition
     * @methodDesc: 保存创建一个新的广告位
     * @description: 新增的广告位状态为停用， 必须审核通过后才启用
     * @param: [advertisingPositionRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-27 22:56
     **/
    @RequestMapping("/advertisingMgr/saveCreateAdvertisingPosition")
    Result saveCreateAdvertisingPosition(@RequestBody AdvertisingPositionRequestVO advertisingPositionRequestVO);

    /**
     * @author 汪豪
     * @methodName: updateAdvertisingPosition
     * @methodDesc: 保存修改一个广告位
     * @description: 广告位的应用范围（门店与仓库）会先删除后新增
     * @param: [advertisingPositionRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-27 23:01
     **/
    @RequestMapping("/advertisingMgr/updateAdvertisingPosition")
    Result updateAdvertisingPosition(@RequestBody AdvertisingPositionRequestVO advertisingPositionRequestVO);

    /**
     * @author 汪豪
     * @methodName: enableAdvertisingPosition
     * @methodDesc: 启用一个广告位
     * @description:
     * @param: [advertisingPositionIdRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-27 23:00
     **/
    @RequestMapping("/advertisingMgr/enableAdvertisingPosition")
    Result enableAdvertisingPosition(@RequestBody AdvertisingPositionIdRequestVO advertisingPositionIdRequestVO);

    /**
     * @author 汪豪
     * @methodName: disableAdvertisingPosition
     * @methodDesc: 停用一个广告位
     * @description:
     * @param: [advertisingPositionIdRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-27 23:00
     **/
    @RequestMapping("/advertisingMgr/disableAdvertisingPosition")
    Result disableAdvertisingPosition(@RequestBody AdvertisingPositionIdRequestVO advertisingPositionIdRequestVO);

    /**
     * @author 汪豪
     * @methodName: deleteOnePartyFromAdvertisingPositionScope
     * @methodDesc: 从广告位的应用范围中删除一个门店或仓库
     * @description:
     * @param: [advertisingPositionScopeIdRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-27 23:05
     **/
    @RequestMapping("/advertisingMgr/deleteOnePartyFromAdvertisingPositionScope")
    Result deleteOnePartyFromAdvertisingPositionScope(@RequestBody AdvertisingPositionScopeIdRequestVO advertisingPositionScopeIdRequestVO);

    /**
     * @author 汪豪
     * @methodName: topOneAdvertisingPositionScopeItem
     * @methodDesc: 置顶一个广告位的应用范围条目
     * @description: 就是将某个门店或仓库的这个广告位置顶
     * @param: [advertisingPositionScopeIdRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-28 9:15
     **/
    @RequestMapping("/advertisingMgr/topOneAdvertisingPositionScopeItem")
    Result topOneAdvertisingPositionScopeItem(@RequestBody AdvertisingPositionScopeIdRequestVO advertisingPositionScopeIdRequestVO);

    /**
     * @author 汪豪
     * @methodName: untopOneAdvertisingPositionScopeItem
     * @methodDesc: 取消置顶一个广告位的应用范围条目
     * @description: 就是将某个门店或仓库的这个广告位取消置顶
     * @param: [advertisingPositionScopeIdRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-28 9:16
     **/
    @RequestMapping("/advertisingMgr/untopOneAdvertisingPositionScopeItem")
    Result untopOneAdvertisingPositionScopeItem(@RequestBody AdvertisingPositionScopeIdRequestVO advertisingPositionScopeIdRequestVO);

    @PostMapping("/advertisingMgr/topManyAdvertisingPositionScopeItem")
    public Result topManyAdvertisingPositionScopeItem(@RequestBody AdvertisingPositionScopeIdListRequestVO advertisingPositionScopeIdListRequestVO);
}
