package com.hryj.api.order;

import com.alibaba.fastjson.JSON;
import com.hryj.common.Result;
import com.hryj.entity.vo.PageResponseVO;
import com.hryj.entity.vo.order.ReturnOrderDetailsRequestVO;
import com.hryj.entity.vo.order.ReturnVO;
import com.hryj.entity.vo.order.request.DistributionOrderIdVO;
import com.hryj.entity.vo.order.request.ReturnOrderListRequestVO;
import com.hryj.entity.vo.order.response.ReturnOrderDetailsResponseVO;
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
 * @className: OrderForStoreReturnController
 * @description: 门店端退货管理
 * @create 2018/7/6 0006 9:31
 **/
@Api(value="/storeOrderReturn", tags = "退货管理（分配处理人后）")
@Slf4j
@RestController
@RequestMapping(value = "/storeOrderReturn")
public class OrderReturnController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OrderFeignClient orderFeignClient;

    /**
     * @author 罗秋涵
     * @methodName: findReturnList
     * @methodDesc: 查询退货单列表
     * @description:
     * @param: [requestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.ReturnResponseVO>
     * @create 2018-06-30 15:59
     **/
    @ApiOperation(value="查询退货单列表",notes = "查询退货单列表")
    @PostMapping("/findReturnList")
    public Result<PageResponseVO<ReturnVO>> findReturnList(@RequestBody ReturnOrderListRequestVO returnOrderListRequestVO){
        WebUtil.getRequestVO(request,returnOrderListRequestVO);
        log.info("查询退货单列表：returnOrderListRequestVO======" + JSON.toJSONString(returnOrderListRequestVO));
        Result<PageResponseVO<ReturnVO>> result = orderFeignClient.findReturnList(returnOrderListRequestVO);
        log.info("查询退货单列表：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.ReturnResponseVO>
     * @author 罗秋涵
     * @methodName: findReturnList
     * @methodDesc: 获取退货详情（处理退货时用）
     * @description:
     * @param: [requestVO]
     * @create 2018-06-30 15:59
     **/
    @PostMapping("/getReturnOrderDetails")
    @ApiOperation(value="获取退货详情（处理退货时用）",notes = "获取退货详情（处理退货时用）")
    public Result<ReturnOrderDetailsResponseVO> getReturnOrderDetails(@RequestBody ReturnOrderDetailsRequestVO returnOrderDetailsRequestVO) {
        WebUtil.getRequestVO(request,returnOrderDetailsRequestVO);
        log.info("获取退货详情（处理退货时用）：returnOrderDetailsRequestVO======" + JSON.toJSONString(returnOrderDetailsRequestVO));
        Result<ReturnOrderDetailsResponseVO> result = orderFeignClient.getReturnOrderDetails(returnOrderDetailsRequestVO);
        log.info("获取退货详情（处理退货时用）：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 罗秋涵
     * @methodName: handelReturnOrder
     * @methodDesc: 处理退货单
     * @description:
     * @param: [requestVO, return_id, return_status, return_handel_remark]
     * @return com.hryj.common.Result
     * @create 2018-06-30 16:01
     **/
    @ApiOperation(value="处理退货单",notes = "处理退货单")
    @PostMapping("/handelReturnOrder")
    public Result handelReturnOrder(@RequestBody DistributionOrderIdVO distributionOrderIdVO){
        WebUtil.getRequestVO(request,distributionOrderIdVO);
        log.info("处理退货单：distributionOrderIdVO======" + JSON.toJSONString(distributionOrderIdVO));
        Result result = orderFeignClient.handelReturnOrder(distributionOrderIdVO);
        log.info("处理退货单：result======" + JSON.toJSONString(result));
        return result;
    }



}
