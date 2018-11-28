package com.hryj.feign;

import com.hryj.common.Result;
import com.hryj.entity.vo.PageResponseVO;
import com.hryj.entity.vo.order.AdminOrderInfoVO;
import com.hryj.entity.vo.order.request.*;
import com.hryj.entity.vo.order.response.AdminOrderDetailResponseVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 李道云
 * @className: OrderFeignClient
 * @description: 订单服务feign接口
 * @create 2018/6/26 17:19
 **/
@FeignClient(name = "order-server")
public interface OrderFeignClient {

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.AdminOrderInfoVO>
     * @author 李道云
     * @methodName: searchOrderList
     * @methodDesc: 分页查询订单列表
     * @description:
     * @param: [adminOrderListRequestVO]
     * @create 2018-06-30 16:41
     **/
    @PostMapping("/adminOrder/searchOrderList")
    Result<PageResponseVO<AdminOrderInfoVO>> searchOrderList(@RequestBody AdminOrderListRequestVO adminOrderListRequestVO);


    /**
     * @return com.hryj.common.Result
     * @author 李道云
     * @methodName: deliveryForExpress
     * @methodDesc: 发货录入快递信息
     * @description:
     * @param: [order_id, express_agency, express_code]
     * @create 2018-06-30 16:46
     **/
    @PostMapping("/adminOrder/deliveryForExpress")
    Result deliveryForExpress(@RequestBody OrderExpressVO orderExpress);


    /**
     * @return com.hryj.common.Result
     * @author 李道云
     * @methodName: deliveryForDistribution
     * @methodDesc: 发货分派配送员，调度（后台管理）
     * @description:
     * @param: [order_id, staff_id]
     * @create 2018-06-30 16:50
     **/
    @PostMapping("/adminOrder/deliveryForDistribution")
    Result deliveryForDistribution(@RequestBody DeliveryForDistributionVO deliveryForDistributionVO);


    /**
     * @return com.hryj.common.Result
     * @author 罗秋涵
     * @description: 退货单分配处理人（后台管理）
     * @param: [deliveryForDistributionVO]
     * @create 2018-07-10 16:56
     **/
    @PostMapping("/adminOrder/returnForDistribution")
    Result returnForDistribution(@RequestBody DeliveryForDistributionVO deliveryForDistributionVO);

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.AdminOrderDetailResponseVO>
     * @author 李道云
     * @methodName: getOrderDetail
     * @methodDesc: 查询订单详情
     * @description:
     * @param: [order_id]
     * @create 2018-06-30 17:16
     **/
    @PostMapping("/adminOrder/getOrderDetail")
    Result<AdminOrderDetailResponseVO> getOrderDetail(@RequestBody OrderIdVO orderIdVO);


    /**
     * @return com.hryj.common.Result
     * @author 李道云
     * @methodName: handelReturnOrder
     * @methodDesc: 处理退货单
     * @description:
     * @param: [requestVO, return_id, return_status, return_handel_remark]
     * @create 2018-06-30 16:01
     **/
    @PostMapping("/storeOrderReturn/handelReturnOrder")
    Result handelReturnOrder(@RequestBody DistributionOrderIdVO vo);

    /**
     * @return com.hryj.common.Result
     * @author 代廷波
     * @description: 查询订单类型
     * @create 2018/09/19 16:02
     **/
    @PostMapping("/order/orderType/loadPermissionPool")
    Result loadPermissionPool();
}
