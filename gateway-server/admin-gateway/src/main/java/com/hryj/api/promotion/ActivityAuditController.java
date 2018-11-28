package com.hryj.api.promotion;

import com.alibaba.fastjson.JSON;
import com.hryj.common.Result;
import com.hryj.entity.vo.PageResponseVO;
import com.hryj.entity.vo.promotion.activity.request.SearchPromotionActivityRequestVO;
import com.hryj.entity.vo.promotion.activity.request.SubmitActivityRequestVO;
import com.hryj.entity.vo.promotion.activity.response.PromotionActivityAuditRecordResponseVO;
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
 * @className: ActivityAuditController
 * @description: 促销活动审核管理接口，该类接口只适用于后台管理
 * @create 2018/6/28 0028 16:39
 **/
@Api(value="/promontion/activityAuditMgr", tags = "活动审核管理")
@Slf4j
@RestController
@RequestMapping("/promontion/activityAuditMgr")
public class ActivityAuditController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ActivityFeignClient activityFeignClient;

    /**
     * @author 汪豪
     * @methodName: searchActivityAuditRecord
     * @methodDesc: 查询促销活动的所有审核记录
     * @description:
     * @param: [searchPromotionActivityRequestVO]
     * @return com.hryj.common.PageResult<com.hryj.entity.vo.promotion.activity.response.PromotionActivityAuditRecordResponseVO>
     * @create 2018-06-28 16:57
     **/
    @ApiOperation(value = "查询促销活动的所有审核记录", notes = "查询促销活动的所有审核记录")
    @PostMapping("/searchActivityAuditRecord")
    public Result<PageResponseVO<PromotionActivityAuditRecordResponseVO>> searchActivityAuditRecord(@RequestBody SearchPromotionActivityRequestVO searchPromotionActivityRequestVO) {
        WebUtil.getRequestVO(request,searchPromotionActivityRequestVO);
        log.info("查询促销活动的所有审核记录:searchPromotionActivityRequestVO====="+JSON.toJSONString(searchPromotionActivityRequestVO));
        Result<PageResponseVO<PromotionActivityAuditRecordResponseVO>> result = activityFeignClient.searchPromotionActivityAuditRecord(searchPromotionActivityRequestVO);
        log.info("查询促销活动的所有审核记录:result====="+JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 汪豪
     * @methodName: submitActivityHandleResult
     * @methodDesc: 提交促销活动审核处理结果
     * @description: handle_result 处理结果:1-通过,0-不通过
     * @param: [submitActivityRequestVo]
     * @return com.hryj.common.Result
     * @create 2018-06-28 17:03
     **/
    @ApiOperation(value = "提交促销活动审核处理结果", notes = "handle_result 处理结果:1-通过,0-不通过")
    @PostMapping("/submitActivityHandleResult")
    public Result submitActivityHandleResult(@RequestBody SubmitActivityRequestVO submitActivityRequestVo) {
        WebUtil.getRequestVO(request,submitActivityRequestVo);
        log.info("提交促销活动审核处理结果:submitActivityRequestVo====="+JSON.toJSONString(submitActivityRequestVo));
        Result result = activityFeignClient.submitActivityHandleResult(submitActivityRequestVo);
        log.info("提交促销活动审核处理结果:result====="+JSON.toJSONString(result));
        return result;
    }
}
