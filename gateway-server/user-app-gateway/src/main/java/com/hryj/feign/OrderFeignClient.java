package com.hryj.feign;

import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.PageResponseVO;
import com.hryj.entity.vo.RequestVO;
import com.hryj.entity.vo.cart.request.CartOperationRequestVO;
import com.hryj.entity.vo.cart.request.ProductBuyRequestVO;
import com.hryj.entity.vo.cart.request.ShoppingCartRequestVO;
import com.hryj.entity.vo.order.OrderListInfoVO;
import com.hryj.entity.vo.order.SingleParamVO;
import com.hryj.entity.vo.order.request.*;
import com.hryj.entity.vo.order.response.*;
import com.hryj.entity.vo.payment.PaymentGroupResponseVO;
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
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.OrderSettlementResponseVO>
     * @author 罗秋涵
     * @methodName: immediateBuy
     * @methodDesc: 立即购买（订单确认界面）
     * @description:
     * @param: [shoppingCartRequestVO]
     * @create 2018-06-29 21:43
     **/
    @PostMapping("/userOrder/immediateBuy")
    Result<OrderSettlementResponseVO> immediateBuy(@RequestBody ShoppingCartRequestVO shoppingCartRequestVO);

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
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.OrderSettlementResponseVO>
     * @author 罗秋涵
     * @methodName: settlementOrder
     * @methodDesc: 去结算
     * @description: 购物车去结算到订单确认页面
     * @param: [cartItemIds]
     * @create 2018-06-29 20:12
     **/
    @PostMapping("/userOrder/settlementOrder")
    Result<OrderSettlementResponseVO> settlementOrder(@RequestBody CartOperationRequestVO cartoPerationRequestVO);

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.OrderToPayResponseVO>
     * @author 罗秋涵
     * @methodName: saveOrderForPay
     * @methodDesc: 去支付(立即购买)
     * @description: 订单确认页去支付保存订单
     * @param: [orderSaveRequestVO]
     * @create 2018-06-29 20:51
     **/
    @PostMapping("/storeOrderProxy/saveOrderForPay")
    Result<OrderToPayResponseVO> saveOrderForPay(@RequestBody OrderSaveRequestVO orderSaveRequestVO);

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.OrderListResponseVO>
     * @author 罗秋涵
     * @methodName: findWaitPayOrderList
     * @methodDesc: 查询待支付的订单列表
     * @description:
     * @param: [orderListRequestVO]
     * @create 2018-06-29 22:14
     **/
    @PostMapping("/userOrder/findWaitPayOrderList")
    Result<ListResponseVO<OrderListInfoVO>> findWaitPayOrderList(@RequestBody OrderListRequestVO orderListRequestVO);

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.OrderToPayResponseVO>
     * @author 罗秋涵
     * @methodName: selectOrderForPay
     * @methodDesc: 选择多个订单进行支付
     * @description: 多个订单id逗号分隔
     * @param: [orderNumStr]
     * @create 2018-06-29 21:53
     **/
    @PostMapping("/userOrder/selectOrderForPay")
    Result<OrderToPayResponseVO> selectOrderForPay(@RequestBody OrderForPayRequestVO orderForPayRequestVO);


    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.PageResponseVO       <       com.hryj.entity.vo.order.OrderListInfoVO>>
     * @author 罗秋涵
     * @methodName: findOrderListByOrderStatus
     * @methodDesc: 根据订单状态查询订单列表
     * @description:
     * @param: [userOrderListRequestVO]
     * @create 2018-06-29 22:16
     **/
    @PostMapping("/userOrder/findOrderListByOrderStatus")
    Result<PageResponseVO<OrderListInfoVO>> findOrderListByOrderStatus(@RequestBody OrderListRequestVO orderListRequestVO);

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.OrderDetailResponseVO>
     * @author 罗秋涵
     * @methodName: findOrderDetail
     * @methodDesc: 查询订单详情
     * @description:
     * @param: [order_id]
     * @create 2018-06-30 9:52
     **/
    @PostMapping("/userOrder/findOrderDetail")
    Result<OrderDetailResponseVO> findOrderDetail(@RequestBody OrderIdVO orderIdVO);

    /**
     * @return com.hryj.common.Result
     * @author 罗秋涵
     * @methodName: cancelOrder
     * @methodDesc: 取消订单
     * @description:
     * @param: [order_id]
     * @create 2018-06-30 10:08
     **/
    @PostMapping("/userOrder/cancelOrder")
    Result cancelOrder(OrderIdVO orderIdVO);

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.ReturnReasonResponseVO>
     * @author 罗秋涵
     * @description: 获取退货原因列表
     * @param: []
     * @create 2018-07-03 16:41
     **/
    @PostMapping("/userOrder/getReturnReason")
    Result<ReturnReasonResponseVO> getReturnReason();

    /**
     * @return com.hryj.common.Result
     * @author 罗秋涵
     * @methodName: returnOrder
     * @methodDesc: 退货申请
     * @description:
     * @param: [order_id]
     * @create 2018-06-30 10:08
     **/
    @PostMapping("/userOrder/returnOrder")
    Result returnOrder(@RequestBody ReturnOrderRequestVO returnOrderRequestVO);

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
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.OrderDifferentStateNumResponseVO>
     * @author 罗秋涵
     * @description: 查询用户各状态订单数量
     * @param: [requestVO]
     * @create 2018-07-03 16:55
     **/
    @PostMapping("/userOrder/getOrderDifferentStateNum")
    Result<OrderDifferentStateNumResponseVO> getOrderDifferentStateNum(@RequestBody RequestVO requestVO);

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
     * @return com.hryj.common.Result
     * @author 罗秋涵
     * @description: 订单支付后查询订单支付结果
     * @param: [orderNumStr]
     * @create 2018-07-03 16:19
     **/
    @RequestMapping(value = "/orderPay/getOrderPayResult",method = RequestMethod.POST)
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
