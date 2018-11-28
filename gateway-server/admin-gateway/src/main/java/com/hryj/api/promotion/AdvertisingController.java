package com.hryj.api.promotion;

import com.alibaba.fastjson.JSON;
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
import com.hryj.feign.AdvertisingFeignClient;
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
 * @className: AdvertisingPositionController
 * @description: 广告位管理
 * @create 2018/6/27 0027 21:43
 **/
@Api(value="/promontion/advertisingMgr", tags = "广告位管理")
@Slf4j
@RestController
@RequestMapping("/promontion/advertisingMgr")
public class AdvertisingController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private AdvertisingFeignClient advertisingFeignClient;

    /**
     * @author 汪豪
     * @methodName: searchAdvertisingPositionPage
     * @methodDesc: 分页查询广告位
     * @description:
     * @param: [searchAdvertisingPositionRequestVO]
     * @return com.hryj.common.PageResult<com.hryj.entity.vo.promotion.advertisingposition.response.AdvertisingPositionItemResponseVO>
     * @create 2018-06-28 15:29
     **/
    @ApiOperation(value = "分页查询广告位", notes = "分页查询广告位")
    @PostMapping("/searchAdvertisingPositionPage")
    public Result<PageResponseVO<AdvertisingPositionItemResponseVO>> searchAdvertisingPositionPage(@RequestBody SearchAdvertisingPositionRequestVO searchAdvertisingPositionRequestVO) {
        WebUtil.getRequestVO(request,searchAdvertisingPositionRequestVO);
        log.info("分页查询广告位:searchAdvertisingPositionRequestVO====="+JSON.toJSONString(searchAdvertisingPositionRequestVO));
        Result<PageResponseVO<AdvertisingPositionItemResponseVO>> result = advertisingFeignClient.searchAdvertisingPositionPage(searchAdvertisingPositionRequestVO);
        log.info("分页查询广告位:result====="+JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 汪豪
     * @methodName: getAdvertisingPosition
     * @methodDesc:  根据广告ID返回广告详细信息
     * @description: include_party是否返回参与门店或仓库，true返回，false不返回， include_jump_config是否返回跳转配置信息：true返回，false不返回
     * @param: [advertisingPositionDetailRequestVO]
     * @return Result<AdvertisingPositionDataResponseVO>
     * @create 2018-07-05 21:23
     **/
    @ApiOperation(value = "查询广告位详情", notes = "分页查询广告位详情")
    @PostMapping("/getAdvertisingPosition")
    public Result<AdvertisingPositionDataResponseVO> getAdvertisingPosition(@RequestBody AdvertisingPositionDetailRequestVO advertisingPositionDetailRequestVO) {
        log.info("查询广告位详情:advertisingPositionDetailRequestVO====="+JSON.toJSONString(advertisingPositionDetailRequestVO));
        Result<AdvertisingPositionDataResponseVO> result = advertisingFeignClient.getAdvertisingPosition(advertisingPositionDetailRequestVO);
        log.info("查询广告位详情:result====="+JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 汪豪
     * @methodName: searchAdvertisingJoinPartyData
     * @methodDesc: 分页查询广告位参与门店或仓库信息
     * @description:
     * @param: [searchAdvertisingPartyDataRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.promotion.advertisingposition.response.AdvertisingPositionJoinPartyDataResponseVO>
     * @create 2018-07-17 10:11
     **/
    @ApiOperation(value = "分页查询广告位参与门店或仓库信息", notes = "分页查询广告位参与门店或仓库信息")
    @PostMapping("/searchAdvertisingJoinPartyData")
    Result<AdvertisingPositionJoinPartyDataResponseVO> searchAdvertisingJoinPartyData(@RequestBody SearchAdvertisingPartyDataRequestVO searchAdvertisingPartyDataRequestVO){
        log.info("分页查询广告位参与门店或仓库信息:searchAdvertisingPartyDataRequestVO====="+JSON.toJSONString(searchAdvertisingPartyDataRequestVO));
        Result<AdvertisingPositionJoinPartyDataResponseVO> result = advertisingFeignClient.searchAdvertisingJoinPartyData(searchAdvertisingPartyDataRequestVO);
        log.info("分页查询广告位参与门店或仓库信息:result====="+JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 汪豪
     * @methodName: saveCreateAdvertisingPosition
     * @methodDesc: 保存创建一个新的广告位
     * @description: 新增的广告位状态为停用， 必须审核通过后才启用
     * @param: [advertisingPositionRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-27 22:56
     **/
    @ApiOperation(value = "保存广告位信息", notes = "新增的广告位状态为停用， 必须审核通过后才启用")
    @PostMapping("/saveCreateAdvertisingPosition")
    public Result saveCreateAdvertisingPosition(@RequestBody AdvertisingPositionRequestVO advertisingPositionRequestVO) {
        WebUtil.getRequestVO(request,advertisingPositionRequestVO);
        log.info("保存广告位信息:advertisingPositionRequestVO====="+JSON.toJSONString(advertisingPositionRequestVO));
        Result result = advertisingFeignClient.saveCreateAdvertisingPosition(advertisingPositionRequestVO);
        log.info("保存广告位信息:result====="+JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 汪豪
     * @methodName: updateAdvertisingPosition
     * @methodDesc: 保存修改一个广告位
     * @description: 广告位的应用范围（门店与仓库）会先删除后新增
     * @param: [advertisingPositionRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-27 23:01
     **/
    @ApiOperation(value = "修改广告位信息", notes = "广告位的应用范围（门店与仓库）会先删除后新增")
    @PostMapping("/updateAdvertisingPosition")
    public Result updateAdvertisingPosition(@RequestBody AdvertisingPositionRequestVO advertisingPositionRequestVO) {
        log.info("修改广告位信息:advertisingPositionRequestVO====="+JSON.toJSONString(advertisingPositionRequestVO));
        Result result = advertisingFeignClient.updateAdvertisingPosition(advertisingPositionRequestVO);
        log.info("修改广告位信息:result====="+JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 汪豪
     * @methodName: enableAdvertisingPosition
     * @methodDesc: 启用一个广告位
     * @description:
     * @param: [advertisingPositionIdRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-27 23:00
     **/
    @ApiOperation(value = "启用一个广告位", notes = "启用一个广告位")
    @PostMapping("/enableAdvertisingPosition")
    public Result enableAdvertisingPosition(@RequestBody AdvertisingPositionIdRequestVO advertisingPositionIdRequestVO) {
        log.info("启用一个广告位:advertisingPositionIdRequestVO====="+JSON.toJSONString(advertisingPositionIdRequestVO));
        Result result = advertisingFeignClient.enableAdvertisingPosition(advertisingPositionIdRequestVO);
        log.info("启用一个广告位:result====="+JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 汪豪
     * @methodName: disableAdvertisingPosition
     * @methodDesc: 停用一个广告位
     * @description:
     * @param: [advertisingPositionIdRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-27 23:00
     **/
    @ApiOperation(value = "停用一个广告位", notes = "停用一个广告位")
    @PostMapping("/disableAdvertisingPosition")
    public Result disableAdvertisingPosition(@RequestBody AdvertisingPositionIdRequestVO advertisingPositionIdRequestVO) {
        log.info("停用一个广告位:advertisingPositionIdRequestVO====="+JSON.toJSONString(advertisingPositionIdRequestVO));
        Result result = advertisingFeignClient.disableAdvertisingPosition(advertisingPositionIdRequestVO);
        log.info("停用一个广告位:result====="+JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 汪豪
     * @methodName: deleteOnePartyFromAdvertisingPositionScope
     * @methodDesc: 从广告位的应用范围中删除一个门店或仓库
     * @description:
     * @param: [advertisingPositionScopeIdRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-27 23:05
     **/
    @ApiOperation(value = "从广告位的应用范围中删除一个门店或仓库", notes = "从广告位的应用范围中删除一个门店或仓库")
    @PostMapping("/deleteOnePartyFromAdvertisingPositionScope")
    public Result deleteOnePartyFromAdvertisingPositionScope(@RequestBody AdvertisingPositionScopeIdRequestVO advertisingPositionScopeIdRequestVO) {
        log.info("从广告位的应用范围中删除一个门店或仓库:advertisingPositionScopeIdRequestVO====="+JSON.toJSONString(advertisingPositionScopeIdRequestVO));
        Result result = advertisingFeignClient.deleteOnePartyFromAdvertisingPositionScope(advertisingPositionScopeIdRequestVO);
        log.info("从广告位的应用范围中删除一个门店或仓库:result====="+JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 汪豪
     * @methodName: topOneAdvertisingPositionScopeItem
     * @methodDesc: 置顶一个广告位的应用范围条目
     * @description: 就是将某个门店或仓库的这个广告位置顶
     * @param: [advertisingPositionScopeIdRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-28 9:15
     **/
    @ApiOperation(value = "置顶一个广告位的应用范围条目", notes = "就是将某个门店或仓库的这个广告位置顶")
    @PostMapping("/topOneAdvertisingPositionScopeItem")
    public Result topOneAdvertisingPositionScopeItem(@RequestBody AdvertisingPositionScopeIdRequestVO advertisingPositionScopeIdRequestVO) {
        log.info("置顶一个广告位的应用范围条目:advertisingPositionScopeIdRequestVO====="+JSON.toJSONString(advertisingPositionScopeIdRequestVO));
        Result result = advertisingFeignClient.topOneAdvertisingPositionScopeItem(advertisingPositionScopeIdRequestVO);
        log.info("置顶一个广告位的应用范围条目:result====="+JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 汪豪
     * @methodName: untopOneAdvertisingPositionScopeItem
     * @methodDesc: 取消置顶一个广告位的应用范围条目
     * @description: 就是将某个门店或仓库的这个广告位取消置顶
     * @param: [advertisingPositionScopeIdRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-28 9:16
     **/
    @ApiOperation(value = "取消置顶一个广告位的应用范围条目", notes = "就是将某个门店或仓库的这个广告位取消置顶")
    @PostMapping("/untopOneAdvertisingPositionScopeItem")
    public Result untopOneAdvertisingPositionScopeItem(@RequestBody AdvertisingPositionScopeIdRequestVO advertisingPositionScopeIdRequestVO) {
        log.info("取消置顶一个广告位的应用范围条目:advertisingPositionScopeIdRequestVO====="+JSON.toJSONString(advertisingPositionScopeIdRequestVO));
        Result result = advertisingFeignClient.untopOneAdvertisingPositionScopeItem(advertisingPositionScopeIdRequestVO);
        log.info("取消置顶一个广告位的应用范围条目:result====="+JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 汪豪
     * @methodName: topManyAdvertisingPositionScopeItem
     * @methodDesc: 批量置顶广告位的应用范围条目
     * @description:
     * @param: [advertisingPositionScopeIdRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-28 9:16
     **/
    @ApiOperation(value = "批量置顶广告位的应用范围条目", notes = "批量置顶广告位的应用范围条目")
    @PostMapping("/topManyAdvertisingPositionScopeItem")
    public Result topManyAdvertisingPositionScopeItem(@RequestBody AdvertisingPositionScopeIdListRequestVO advertisingPositionScopeIdListRequestVO){
        log.info("批量置顶广告位的应用范围条目:advertisingPositionScopeIdListRequestVO====="+JSON.toJSONString(advertisingPositionScopeIdListRequestVO));
        Result result = advertisingFeignClient.topManyAdvertisingPositionScopeItem(advertisingPositionScopeIdListRequestVO);
        log.info("批量置顶广告位的应用范围条目:result====="+JSON.toJSONString(result));
        return result;
    }
}
