package com.hryj.api;

import com.alibaba.fastjson.JSON;
import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.PageResponseVO;
import com.hryj.entity.vo.order.AdminOrderInfoVO;
import com.hryj.entity.vo.order.request.*;
import com.hryj.entity.vo.order.response.AdminOrderDetailResponseVO;
import com.hryj.feign.OrderFeignClient;
import com.hryj.permission.Permission;
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
 * @author 叶方宇
 * @className: OrderController
 * @description: 订单服务API
 * @create 2018/6/26 17:08
 **/
@Api(value="/adminOrder", tags = "订单模块")
@Slf4j
@RestController
@RequestMapping("/adminOrder")
public class OrderController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OrderFeignClient orderFeignClient;

    /**
     * @author 叶方宇
     * @methodName: searchOrderList
     * @methodDesc: 分页查询订单列表
     * @description:
     * @param: [adminOrderListRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.AdminOrderInfoVO>
     * @create 2018-06-30 16:41
     **/
    @ApiOperation(value="分页查询订单列表",notes = "分页查询订单列表")
    @PostMapping("/searchOrderList")
    public Result<PageResponseVO<AdminOrderInfoVO>> searchOrderList(@RequestBody AdminOrderListRequestVO adminOrderListRequestVO){
        WebUtil.getRequestVO(request, adminOrderListRequestVO);
        log.info("分页查询订单列表：adminOrderListRequestVO======" + JSON.toJSONString(adminOrderListRequestVO));
        Result<PageResponseVO<AdminOrderInfoVO>> result = orderFeignClient.searchOrderList(adminOrderListRequestVO);
        log.info("分页查询订单列表：result======" + JSON.toJSONString(result));
        return result;

    }

    /**
     * @author 叶方宇
     * @methodName: deliveryForExpress
     * @methodDesc: 发货录入快递信息
     * @description:
     * @param: [order_id, express_agency, express_code]
     * @return com.hryj.common.Result
     * @create 2018-06-30 16:46
     **/
    @ApiOperation(value="发货录入快递信息",notes = "发货录入快递信息")
    @PostMapping("/deliveryForExpress")
    public Result deliveryForExpress(@RequestBody OrderExpressVO orderExpressVO){
        WebUtil.getRequestVO(request, orderExpressVO);
        log.info("发货录入快递信息：orderExpressVO======" + JSON.toJSONString(orderExpressVO));
        Result result = orderFeignClient.deliveryForExpress(orderExpressVO);
        log.info("发货录入快递信息：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 叶方宇
     * @methodName: deliveryForDistribution
     * @methodDesc: 发货分派配送员，调度（后台管理）
     * @description:
     * @param: [order_id, staff_id]
     * @return com.hryj.common.Result
     * @create 2018-06-30 16:50
     **/
    @ApiOperation(value="发货分派配送员",notes = "发货分派配送员")
    @PostMapping("/deliveryForDistribution")
    public Result deliveryForDistribution(@RequestBody DeliveryForDistributionVO deliveryForDistributionVO){
        WebUtil.getRequestVO(request, deliveryForDistributionVO);
        log.info("发货分派配送员：deliveryForDistributionVO======" + JSON.toJSONString(deliveryForDistributionVO));
        Result result = orderFeignClient.deliveryForDistribution(deliveryForDistributionVO);
        log.info("发货分派配送员：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 叶方宇
     * @description: 退货单分配处理人（后台管理）
     * @param: [deliveryForDistributionVO]
     * @return com.hryj.common.Result
     * @create 2018-07-10 16:56z`
     **/
    @ApiOperation(value="退货分派取货员",notes = "取货分派取货员")
    @PostMapping("/returnForDistribution")
    public Result  returnForDistribution(@RequestBody DeliveryForDistributionVO deliveryForDistributionVO){
        WebUtil.getRequestVO(request, deliveryForDistributionVO);
        log.info("退货分派取货员：deliveryForDistributionVO======" + JSON.toJSONString(deliveryForDistributionVO));
        Result result = orderFeignClient.returnForDistribution(deliveryForDistributionVO);
        log.info("退货分派取货员：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 叶方宇
     * @methodName: getOrderDetail
     * @methodDesc: 查询订单详情
     * @description:
     * @param: [order_id]
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.AdminOrderDetailResponseVO>
     * @create 2018-06-30 17:16
     **/
    @ApiOperation(value="查询订单详情 ，v1.2修改",notes = "查询订单详情")
    @PostMapping("/getOrderDetail")
    public Result<AdminOrderDetailResponseVO> getOrderDetail(@RequestBody OrderIdVO orderIdVO){
        WebUtil.getRequestVO(request, orderIdVO);
        log.info("查询订单详情：orderIdVO======" + JSON.toJSONString(orderIdVO));
        Result result = orderFeignClient.getOrderDetail(orderIdVO);
        log.info("查询订单详情：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 叶方宇
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

    @ApiOperation(value="获取订单类型 v1.2",notes = "获取订单类型")
    @PostMapping("/loadPermissionPool")
    public Result<ListResponseVO<Permission>> loadPermissionPool() {

        Result result = orderFeignClient.loadPermissionPool();
        log.info("查询订单详情：result======" + JSON.toJSONString(result));
        return result;
    }

}
