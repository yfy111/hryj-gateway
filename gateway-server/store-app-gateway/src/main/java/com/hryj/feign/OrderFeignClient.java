package com.hryj.feign;

import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.PageResponseVO;
import com.hryj.entity.vo.RequestVO;
import com.hryj.entity.vo.cart.request.CartOperationRequestVO;
import com.hryj.entity.vo.cart.request.ProductBuyRequestVO;
import com.hryj.entity.vo.cart.request.ShoppingCartRequestVO;
import com.hryj.entity.vo.order.*;
import com.hryj.entity.vo.order.request.*;
import com.hryj.entity.vo.order.response.*;
import com.hryj.entity.vo.payment.PaymentGroupResponseVO;
import com.hryj.entity.vo.profit.request.DataQueryRequestVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @author 罗秋涵
 * @className: OrderFeignClient
 * @description: 订单服务feign接口
 * @create 2018/6/26 17:19
 **/
@FeignClient(name = "order-server")
public interface OrderFeignClient {

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.OrderConfirmInfoResponseVO>
     * @author 白飞
     * @methodName: confirmInfo
     * @methodDesc: 订单确认
     * @description:
     * @param: [productRequestVO]
     * @create 2018-06-29 21:43
     **/
    @PostMapping("/userOrder/confirmInfo")
    Result<OrderConfirmInfoResponseVO> confirmInfo(@RequestBody ProductBuyRequestVO productRequestVO);


    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.request.OrderCreateRequestVO>
     * @author 白飞
     * @methodName: create
     * @methodDesc: 订单创建
     * @description:
     * @param: [orderCreateRequest]
     * @create 2018-08-22 15:17
     **/
    @PostMapping("/userOrder/create")
    Result<OrderPaymentResponseVO> create(@RequestBody OrderCreateRequestVO orderCreateRequest);

    /**
     * @author 罗秋涵
     * @methodName: findTradeUserList
     * @methodDesc: 查询交易用户列表
     * @description:
     * @param: [dataQueryRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.ListResponseVO<com.hryj.entity.vo.order.UserTradeVO>>
     * @create 2018-07-09 12:53
     **/
    @RequestMapping(value = "dataOrder/findTradeUserList", method = RequestMethod.POST)
    Result<ListResponseVO<UserTradeVO>> findTradeUserList(@RequestBody DataQueryRequestVO dataQueryRequestVO);

    /**
     * @author 罗秋涵
     * @methodName: findNewTradeUserList
     * @methodDesc: 查询新增交易用户列表
     * @description:
     * @param: [dataQueryRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.ListResponseVO<com.hryj.entity.vo.order.UserTradeVO>>
     * @create 2018-07-09 12:54
     **/
    @RequestMapping(value = "dataOrder/findNewTradeUserList", method = RequestMethod.POST)
    Result<ListResponseVO<UserTradeVO>> findNewTradeUserList(@RequestBody DataQueryRequestVO dataQueryRequestVO);

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.WaitHandelOrderCountResponseVO>
     * @author 罗秋涵
     * @methodName: getWaitHandelOrderCount
     * @methodDesc: 获取待处理订单数量
     * @description:
     * @param: [requestVO]
     * @create 2018-06-30 10:36
     **/
    @PostMapping("/storeOrderDistribution/getWaitHandelOrderCount")
    Result<WaitHandelOrderCountResponseVO> getWaitHandelOrderCount(@RequestBody RequestVO requestVO);


    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.DistributionListReponseVO>
     * @author 罗秋涵
     * @methodName: findDistributionList
     * @methodDesc: 查询配送单列表（门店端，配送订单，已超时）
     * @description: distribution_status：配送状态:01-待配送,02-配送中,03-已配送
     * @param: [requestVO, distribution_status, start_date, end_date]
     * @create 2018-06-30 11:21
     **/
    @PostMapping("/storeOrderDistribution/findDistributionList")
    Result<DistributionListReponseVO> findDistributionList(@RequestBody DistributionRequestVO distributionRequestVO);

    /**
     * @author 罗秋涵
     * @methodName: findDistributionForStaff
     * @methodDesc: 查询配送单（员工）
     * @description:
     * @param: [distributionRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.DistributionResponseVO>
     * @create 2018-07-21 17:38
     **/
    @PostMapping("/storeOrderDistribution/findDistributionForStaff")
    Result<PageResponseVO<DistributionInfoVO>> findDistributionForStaff(@RequestBody DistributionRequestVO distributionRequestVO);

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.DistributionDetailResponseVO>
     * @author 罗秋涵
     * @methodName: findDistributionDetail
     * @methodDesc: 查询配送单详情
     * @description:
     * @param: [distribution_id]
     * @create 2018-06-30 14:04
     **/
    @PostMapping("/storeOrderDistribution/findDistributionDetail")
    Result<DistributionDetailResponseVO> findDistributionDetail(@RequestBody DistributionDetailRequestVO distributionDetailRequestVO);

    /**
     * @return com.hryj.common.Result
     * @author 罗秋涵
     * @methodName: confirmDistributionDetail
     * @methodDesc: 确认配送完成/取货完成
     * @description:
     * @param: [distribution_id]
     * @create 2018-06-30 14:06
     **/
    @PostMapping("/storeOrderDistribution/confirmDistributionDetail")
    Result confirmDistributionDetail(@RequestBody  DistributionConfirmRequestVo requestVO);

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.OrderSettlementResponseVO>
     * @author 罗秋涵
     * @methodName: immediateBuy
     * @methodDesc: 立即购买（订单确认界面）
     * @description:
     * @param: [shoppingCartRequestVO]
     * @create 2018-06-29 21:43
     **/
    @PostMapping("/storeOrderProxy/immediateBuy")
    Result<OrderSettlementResponseVO> storeImmediateBuy(@RequestBody ShoppingCartRequestVO shoppingCartRequestVO);

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.OrderSettlementResponseVO>
     * @author 罗秋涵
     * @methodName: settlementOrderForHelp
     * @methodDesc: 代下单去结算
     * @description: 购物车去结算到订单确认页面
     * @param: [user_id, cartItemIds]
     * @create 2018-06-30 14:52
     **/
    @PostMapping("/storeOrderProxy/settlementOrder")
    Result<OrderSettlementResponseVO> storeSettlementOrder(@RequestBody CartOperationRequestVO cartoPerationRequestVO);

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.OrderToPayResponseVO>
     * @author 罗秋涵
     * @methodName: saveHelpOrderInfo
     * @methodDesc: 代下单保存订单信息
     * @description: 代下单提交页面
     * @param: [orderSaveRequestVO]
     * @create 2018-06-29 20:51
     **/
    @PostMapping("/storeOrderProxy/saveOrderForPay")
    Result<OrderToPayResponseVO> storeSaveOrderForPay(@RequestBody OrderSaveRequestVO orderSaveRequestVO);

    /**
     * @return com.hryj.common.Result
     * @author 罗秋涵
     * @methodName: confirmPayOrder
     * @methodDesc: 确认支付订单
     * @description: 多个订单编号逗号分隔
     * @param: [orderNumStr, pay_method]
     * @create 2018-06-29 21:34
     **/
    @PostMapping("/orderPay/confirmPayOrder")
    Result confirmPayOrder(@RequestBody ConfirmPayOrderRequestVO confirmPayOrderRequestVO);

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.OrderListResponseVO>
     * @author 罗秋涵
     * @methodName: findWaitPayOrderList
     * @methodDesc: 查询待支付的订单列表
     * @description:
     * @param: [orderListRequestVO]
     * @create 2018-06-29 22:14
     **/
    @PostMapping("/storeOrderProxy/findWaitPayOrderList")
    Result<ListResponseVO<HistoricalOrderListResponseVO>> storeFindWaitPayOrderList(@RequestBody OrderListRequestVO orderListRequestVO);

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.OrderToPayResponseVO>
     * @author 罗秋涵
     * @methodName: selectOrderForPay
     * @methodDesc: 选择多个订单进行支付
     * @description: 多个订单id逗号分隔
     * @param: [orderNumStr]
     * @create 2018-06-29 21:53
     **/
    @PostMapping("/storeOrderProxy/selectOrderForPay")
    Result<OrderToPayResponseVO> storeSelectOrderForPay(@RequestBody OrderForPayRequestVO orderForPayRequestVO);

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.PageResponseVO                               <                               com.hryj.entity.vo.order.OrderListInfoVO>>
     * @author 罗秋涵
     * @methodName: findOrderListByOrderStatus
     * @methodDesc: 根据订单状态查询订单列表
     * @description:
     * @param: [userOrderListRequestVO]
     * @create 2018-06-29 22:16
     **/
    @PostMapping("/storeOrderProxy/findOrderListByOrderStatus")
    Result<PageResponseVO<HistoricalOrderListResponseVO>> storeFindOrderListByOrderStatus(@RequestBody OrderListRequestVO orderListRequestVO);

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.OrderDetailResponseVO>
     * @author 罗秋涵
     * @methodName: findOrderDetail
     * @methodDesc: 查询订单详情
     * @description:
     * @param: [order_id]
     * @create 2018-06-30 9:52
     **/
    @PostMapping("/storeOrderProxy/findOrderDetail")
    Result<OrderDetailResponseVO> storeFindOrderDetail(@RequestBody OrderIdVO orderIdVO);

    /**
     * @return com.hryj.common.Result
     * @author 罗秋涵
     * @methodName: cancelOrder
     * @methodDesc: 取消订单
     * @description:
     * @param: [order_id]
     * @create 2018-06-30 10:08
     **/
    @PostMapping("/storeOrderProxy/cancelOrder")
    Result storeCancelOrder(@RequestBody OrderIdVO orderIdVO);

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.ReturnReasonResponseVO>
     * @author 罗秋涵
     * @description: 获取退货原因列表
     * @param: []
     * @create 2018-07-03 16:41
     **/
    @PostMapping("/storeOrderProxy/getReturnReason")
    Result<ReturnReasonResponseVO> storeGetReturnReason();

    /**
     * @return com.hryj.common.Result
     * @author 罗秋涵
     * @methodName: returnOrder
     * @methodDesc: 退货申请
     * @description:
     * @param: [order_id]
     * @create 2018-06-30 10:08
     **/
    @PostMapping("/storeOrderProxy/returnOrder")
    Result storeReturnOrder(@RequestBody ReturnOrderRequestVO returnOrderRequestVO);

    /**
     * @return com.hryj.common.Result
     * @author 罗秋涵
     * @methodName: confirmReceive
     * @methodDesc: 确认收货
     * @description:
     * @param: [order_id]
     * @create 2018-06-30 10:11
     **/
    @PostMapping("/userOrder/confirmReceive")
    Result confirmReceive(@RequestBody OrderIdVO orderIdVO);

    /**
     * @return com.hryj.common.Result
     * @author 罗秋涵
     * @description: 判断员工是否有代下单访问权限
     * @param: [requestVO]
     * @create 2018-07-03 19:43
     **/
    @PostMapping("/storeOrderProxy/getHelpOrderAccessRight")
    Result getHelpOrderAccessRight(@RequestBody RequestVO requestVO);

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.ReturnResponseVO>
     * @author 罗秋涵
     * @methodName: findReturnList
     * @methodDesc: 查询退货单列表
     * @description:
     * @param: [requestVO]
     * @create 2018-06-30 15:59
     **/
    @PostMapping("/storeOrderReturn/findReturnList")
    Result<PageResponseVO<ReturnVO>> findReturnList(@RequestBody ReturnOrderListRequestVO returnOrderListRequestVO);

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.ReturnResponseVO>
     * @author 罗秋涵
     * @methodName: findReturnList
     * @methodDesc: 获取退货详情（处理退货时用）
     * @description:
     * @param: [requestVO]
     * @create 2018-06-30 15:59
     **/
    @PostMapping("/storeOrderReturn/getReturnOrderDetails")
    Result<ReturnOrderDetailsResponseVO> getReturnOrderDetails(@RequestBody ReturnOrderDetailsRequestVO returnOrderDetailsRequestVO);

    /**
     * @return com.hryj.common.Result
     * @author 罗秋涵
     * @methodName: handelReturnOrder
     * @methodDesc: 处理退货单
     * @description:
     * @param: [requestVO, return_id, return_status, return_handel_remark]
     * @create 2018-06-30 16:01
     **/
    @PostMapping("/storeOrderReturn/handelReturnOrder")
    Result handelReturnOrder(@RequestBody DistributionOrderIdVO vo);

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.SelfPickResponseVO>
     * @author 罗秋涵
     * @methodName: findOrderSelfPick
     * @methodDesc: 根据自提码查询自提单信息
     * @description:
     * @param: [self_pick_code, phone_num]
     * @create 2018-06-30 15:31
     **/
    @PostMapping("/storeOrderSelfPick/findOrderSelfPick")
    Result<SelfPickResponseVO> findOrderSelfPick(@RequestBody SelfPickRequestVO selfPickRequestVO);

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.OrderSelfPickListResponseVO>
     * @author 罗秋涵
     * @description: 根据用户电话查询自提信息列表
     * @param: [phone_num]
     * @create 2018-07-03 17:22
     **/
    @PostMapping("/storeOrderSelfPick/findOrderSelfPickListByPhoneNum")
    Result<ListResponseVO<SelfPickResponseVO>> findOrderSelfPickListByPhoneNum(@RequestBody SelfPickRequestVO selfPickRequestVO);

    /**
     * @return com.hryj.common.Result
     * @author 罗秋涵
     * @methodName: confirmSelfPick
     * @methodDesc: 确认自提处理
     * @description:
     * @param: [self_pick_id]
     * @create 2018-06-30 15:25
     **/
    @PostMapping("/storeOrderSelfPick/confirmSelfPick")
    Result confirmSelfPick(@RequestBody ConfirmSelfPickRequestVO confirmSelfPickRequestVO);

    /**
     * @author 罗秋涵
     * @methodName: findDistributionListForStore
     * @methodDesc: 查询待分配配送单列表(店长)
     * @description: distribution_type：配送类别:01-送货,02-取货；distribution_status：配送单状态:01-待分配,02-待配送,03-配送完成,04-配送超时
     * @param: [distribution_type, distribution_status]
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.DistributionListReponseVO>
     * @create 2018-06-30 14:18
     **/
    @PostMapping("/storeOrderShipments/findDistributionListForStore")
    Result<DistributionListReponseVO> findDistributionListForStore(@RequestBody DistributionForStoreRequestVO distributionForStoreRequestVO);

    /**
     * @author 罗秋涵
     * @description: 查询已分配配送单列表(店长)
     * @param: [distributionForStoreRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.DistributionResponseVO>
     * @create 2018-07-18 16:38
     **/
    @RequestMapping(value = "/storeOrderShipments/findAssignedDistributionList", method = RequestMethod.POST)
    Result<PageResponseVO<DistributionInfoVO>> findAssignedDistributionList(@RequestBody DistributionForStoreRequestVO distributionForStoreRequestVO);

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.DistributionListReponseVO>
     * @author 罗秋涵
     * @methodName: findDistributionListForStore
     * @methodDesc: 查询待分配退货单列表(店长)
     * @param: [requestVO, distribution_type, distribution_status]
     * @create 2018-06-30 14:18
     **/
    @PostMapping("/storeOrderShipments/findReturnListForStore")
    Result<PageResponseVO<ReturnVO>> findReturnListForStore(@RequestBody ReturnRequestVO returnRequestVO);

    /**
     * @return com.hryj.common.Result
     * @author 罗秋涵
     * @methodName: assignDistribution
     * @methodDesc: 分派配送单（门店端）
     * @description:
     * @param: [requestVO, distribution_id, staff_id]
     * @create 2018-06-30 14:23
     **/
    @PostMapping("/storeOrderShipments/assignDistribution")
    Result assignDistribution(@RequestBody SendOrdersRequestVO sendOrdersRequestVO);

    /**
     * @return com.hryj.common.Result
     * @author 罗秋涵
     * @methodName: assignDistribution
     * @methodDesc: 分派退货处理人（门店端）
     * @description:
     * @param: [requestVO, distribution_id, staff_id]
     * @create 2018-06-30 14:23
     **/
    @PostMapping("/storeOrderShipments/returnDistribution")
    Result returnDistribution(@RequestBody SendOrdersRequestVO sendOrdersRequestVO);

    /**
     * @return com.hryj.common.Result
     * @author 罗秋涵
     * @description: 订单支付后查询订单支付结果
     * @param: [orderNumStr]
     * @create 2018-07-03 16:19
     **/
    @PostMapping("/orderPay/getOrderPayResult")
    Result<PaymentGroupResponseVO> getOrderPayResult(@RequestBody SingleParamVO singleParamVO);

    /**
     * @return com.hryj.common.Result
     * @author 罗秋涵
     * @description: 微信支付回调
     * @param: [param_map]
     * @create 2018-07-03 16:19
     **/
    @RequestMapping(value = "/orderPay/notifyForWxPay", method = RequestMethod.POST)
    Result notifyForWxPay(@RequestBody Map<String, Object> param_map);

    /**
     * @return com.hryj.common.Result
     * @author 罗秋涵
     * @description: 支付宝支付回调
     * @param: [param_map]
     * @create 2018-07-03 16:19
     **/
    @RequestMapping(value = "/orderPay/notifyForAliPay", method = RequestMethod.POST)
    Result notifyForAliPay(@RequestBody Map<String, Object> param_map);

}
