package com.hryj.api.order;

import com.alibaba.fastjson.JSON;
import com.hryj.common.Result;
import com.hryj.entity.vo.PageResponseVO;
import com.hryj.entity.vo.RequestVO;
import com.hryj.entity.vo.order.DistributionInfoVO;
import com.hryj.entity.vo.order.request.DistributionConfirmRequestVo;
import com.hryj.entity.vo.order.request.DistributionDetailRequestVO;
import com.hryj.entity.vo.order.request.DistributionRequestVO;
import com.hryj.entity.vo.order.response.DistributionDetailResponseVO;
import com.hryj.entity.vo.order.response.DistributionListReponseVO;
import com.hryj.entity.vo.order.response.WaitHandelOrderCountResponseVO;
import com.hryj.feign.OrderFeignClient;
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
 * @author 罗秋涵
 * @className: OrderForStoreDistributionController
 * @description: 门店端配送取货（店员）
 * @create 2018/7/6 0006 9:18
 **/
@Api(value="/storeOrderDistribution", tags = "配送取货管理（店员）")
@Slf4j
@RestController
@RequestMapping(value = "/storeOrderDistribution")
public class OrderDistributionController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OrderFeignClient orderFeignClient;

    /**
     * @author 罗秋涵
     * @methodName: getWaitHandelOrderCount
     * @methodDesc: 获取待处理订单数量
     * @description:
     * @param:
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.WaitHandelOrderCountResponseVO>
     * @create 2018-06-30 10:36
     **/
    @ApiOperation(value="获取待处理订单数量",notes = "获取待处理订单数量")
    @PostMapping("/getWaitHandelOrderCount")
    public Result<WaitHandelOrderCountResponseVO> getWaitHandelOrderCount(){
        RequestVO requestVO = WebUtil.getRequestVO(request,null);
        log.info("获取待处理订单数量据：requestVO======" + JSON.toJSONString(requestVO));
        Result<WaitHandelOrderCountResponseVO> result = orderFeignClient.getWaitHandelOrderCount(requestVO);
        log.info("获取待处理订单数量据：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 罗秋涵
     * @methodName: findDistributionList
     * @methodDesc: 查询配送单列表
     * @description: distribution_type：配送类别:01-送货,02-取货；distribution_status：配送单状态:01-待分配,02-待配送,03-配送完成,04-配送超时
     * @param: [requestVO, distribution_status, start_date, end_date]
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.DistributionListReponseVO>
     * @create 2018-06-30 11:21
     **/
    @ApiOperation(value="查询配送单列表",notes = "distribution_type：配送类别:01-送货,02-取货；distribution_status：配送单状态:01-待分配,02-待配送,03-配送完成,04-配送超时")
    @PostMapping("/findDistributionList")
    public Result<DistributionListReponseVO> findDistributionList(@RequestBody DistributionRequestVO distributionRequestVO){
        WebUtil.getRequestVO(request,distributionRequestVO);
        log.info("查询配送单列表：distributionRequestVO======" + JSON.toJSONString(distributionRequestVO));
        Result<DistributionListReponseVO> result = orderFeignClient.findDistributionList(distributionRequestVO);
        log.info("查询配送单列表：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 罗秋涵
     * @description: 查询配送单列表（已配送，已超时，待取货，已取货）
     * @param: [distributionRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.DistributionResponseVO>
     * @create 2018-07-17 21:50
     **/
    @ApiOperation(value="查询配送单列表（已配送，已超时，待取货，已取货）",notes = "查询配送单列表（已配送，已超时，待取货，已取货）")
    @PostMapping("/findDistributionForStaff")
    public Result<PageResponseVO<DistributionInfoVO>> findDistributionForStaff(@RequestBody DistributionRequestVO distributionRequestVO){
        WebUtil.getRequestVO(request,distributionRequestVO);
        log.info("查询配送单列表：distributionRequestVO======" + JSON.toJSONString(distributionRequestVO));
        Result<PageResponseVO<DistributionInfoVO>> result = orderFeignClient.findDistributionForStaff(distributionRequestVO);
        log.info("查询配送单列表：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 罗秋涵
     * @methodName: findDistributionDetail
     * @methodDesc: 查询配送单详情
     * @description:
     * @param: [distribution_id]
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.DistributionDetailResponseVO>
     * @create 2018-06-30 14:04
     **/
    @ApiOperation(value="查询配送单详情",notes = "查询配送单详情")
    @PostMapping("/findDistributionDetail")
    public Result<DistributionDetailResponseVO> findDistributionDetail(@RequestBody DistributionDetailRequestVO distributionDetailRequestVO){
        WebUtil.getRequestVO(request,distributionDetailRequestVO);
        log.info("查询配送单详情：distributionDetailRequestVO======" + JSON.toJSONString(distributionDetailRequestVO));
        Result<DistributionDetailResponseVO> result = orderFeignClient.findDistributionDetail(distributionDetailRequestVO);
        log.info("查询配送单详情：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 罗秋涵
     * @methodName: confirmDistributionDetail
     * @methodDesc: 确认配送完成
     * @description:
     * @param: [distribution_id]
     * @return com.hryj.common.Result
     * @create 2018-06-30 14:06
     **/
    @ApiOperation(value="确认配送完成",notes = "确认配送完成")
    @PostMapping("/confirmDistributionDetail")
    public Result confirmDistributionDetail(@RequestBody DistributionConfirmRequestVo requestVO){
        WebUtil.getRequestVO(request,requestVO);
        log.info("确认配送完成：distributionDetailRequestVO======" + JSON.toJSONString(requestVO));
        Result result = orderFeignClient.confirmDistributionDetail(requestVO);
        log.info("确认配送完成：result======" + JSON.toJSONString(result));
        return result;
    }

}
