package com.hryj.api;

import com.alibaba.fastjson.JSON;
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
import com.hryj.feign.OrderFeignClient;
import com.hryj.utils.WebUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * @author 罗秋涵
 * @className: OrderController
 * @description: 订单模块
 * @create 2018/6/27 0027 10:03
 **/
@Api(value="/order", tags = "订单模块")
@Slf4j
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OrderFeignClient orderFeignClient;


    /**
     * @author 罗秋涵
     * @methodName: immediateBuy
     * @methodDesc: 立即购买
     * @description:
     * @param: [shoppingCartRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.OrderSettlementResponseVO>
     * @create 2018-06-29 21:43
     **/
    @ApiOperation(value = "立即购买-v1.2", notes = "立即购买 状态值biz_code: 11000商品下架，11010活动已结束，11020商品库存不足，11030商品不存在，16100组织不存在，11040 商品价格变动，11144 跨境订单金额超限")
    @PostMapping("/immediateBuy")
    public Result<OrderSettlementResponseVO> immediateBuy(@RequestBody ShoppingCartRequestVO shoppingCartRequestVO){
        WebUtil.getRequestVO(request,shoppingCartRequestVO);
        log.info("立即购买：shoppingCartRequestVO======" + JSON.toJSONString(shoppingCartRequestVO));
        Result<OrderSettlementResponseVO> result=orderFeignClient.immediateBuy(shoppingCartRequestVO);
        log.info("立即购买：ImmediateBuyResponseVO======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 白飞
     * @methodName: confirmInfo
     * @methodDesc: 立即购买
     * @description:
     * @param: [productRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.OrderConfirmInfoResponseVO>
     * @create 2018-08-15 11:43
     **/
    @ApiOperation(value = "订单确认信息-立即购买、购物车去结算", notes = "立即购买 状态值biz_code: 11000商品下架，11010活动已结束，11020商品库存不足，11030商品不存在，16100组织不存在，11040 商品价格变动")
    @PostMapping("/confirmInfo")
    public Result<OrderConfirmInfoResponseVO> confirmInfo(@RequestBody ProductBuyRequestVO productRequestVO){
        WebUtil.getRequestVO(request, productRequestVO);
        log.info("商祥页立即购买或购物车去结算：cartRequestVO======" + JSON.toJSONString(productRequestVO));
        Result<OrderConfirmInfoResponseVO> result = orderFeignClient.confirmInfo(productRequestVO);
        log.info("商祥页立即购买或购物车去结算：cartRequestVO======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 白飞
     * @methodName: create
     * @methodDesc: 订单确认去支付
     * @description:
     * @param: [orderCreateRequest]
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.OrderToPayResponseVO>
     * @create 2018-08-22 15:22
     **/
    @ApiOperation(value = "订单创建-去支付")
    @PostMapping("/create")
    public Result<OrderPaymentResponseVO> create(@RequestBody OrderCreateRequestVO orderCreateRequest){
        WebUtil.getRequestVO(request, orderCreateRequest);
        log.info("确认订单去支付：orderCreateRequest======" + JSON.toJSONString(orderCreateRequest));
        Result<OrderPaymentResponseVO> result = orderFeignClient.create(orderCreateRequest);
        log.info("确认订单去支付：orderCreateRequest======" + JSON.toJSONString(result));
        return result;
    }



    /**
     * @author 罗秋涵
     * @methodName: settlementOrder
     * @methodDesc: 去结算
     * @description: 购物车去结算到订单确认页面
     * @param: [cartItemIds]
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.OrderSettlementResponseVO>
     * @create 2018-06-29 20:12
     **/
    @ApiOperation(value = "去结算v1.2", notes = "购物车去结算到订单确认页面,配送方式:01-自提,02-送货上门,03-快递" +
            "状态值biz_code: 11000商品下架，11010活动已结束，11020商品库存不足，11030商品不存在，16100组织不存在，11040 商品价格变动，11144 跨境订单金额超限")
    @PostMapping("/settlementOrder")
    public Result<OrderSettlementResponseVO> settlementOrder(@RequestBody CartOperationRequestVO cartoPerationRequestVO) {
        WebUtil.getRequestVO(request,cartoPerationRequestVO);
        log.info("去结算：cartoPerationRequestVO======" + JSON.toJSONString(cartoPerationRequestVO));
        Result<OrderSettlementResponseVO> result = orderFeignClient.settlementOrder(cartoPerationRequestVO);
        log.info("去结算：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 罗秋涵
     * @methodName: saveOrderForPay
     * @methodDesc: 去支付
     * @description: 订单确认页去支付保存订单
     * @param: [orderSaveRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.OrderToPayResponseVO>
     * @create 2018-06-29 20:51
     **/
    @ApiOperation(value = "去支付v1.2", notes = "订单确认页去支付保存订单 状态值biz_code: 11000商品下架，11010活动已结束，11020商品库存不足，11030商品不存在，16100组织不存在，11040 商品价格变动，11144 跨境订单金额超限")
    @PostMapping("/saveOrderForPay")
    public Result<OrderToPayResponseVO> saveOrderForPay(@RequestBody OrderSaveRequestVO orderSaveRequestVO){
        WebUtil.getRequestVO(request,orderSaveRequestVO);
        log.info("去支付：orderSaveRequestVO======" + JSON.toJSONString(orderSaveRequestVO));
        Result<OrderToPayResponseVO> result = orderFeignClient.saveOrderForPay(orderSaveRequestVO);
        log.info("去支付：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 罗秋涵
     * @methodName: selectOrderForPaysaveOrderForPay
     * @methodDesc: 选择多个订单进行支付
     * @description: 多个订单id逗号分隔
     * @param: [orderNumStr]
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.OrderToPayResponseVO>
     * @create 2018-06-29 21:53
     **/
    @ApiOperation(value = "选择多个订单进行支付", notes = "多个订单id逗号分隔")
    @PostMapping("/selectOrderForPay")
    public Result<OrderToPayResponseVO> selectOrderForPay(@RequestBody  OrderForPayRequestVO orderForPayRequestVO){
        WebUtil.getRequestVO(request,orderForPayRequestVO);
        log.info("选择多个订单进行支付：orderForPayRequestVO======" + JSON.toJSONString(orderForPayRequestVO));
        Result<OrderToPayResponseVO> result = orderFeignClient.selectOrderForPay(orderForPayRequestVO);
        log.info("选择多个订单进行支付：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 罗秋涵
     * @methodName: confirmPayOrder
     * @methodDesc: 确认支付订单
     * @description: 多个订单编号逗号分隔
     * @param: [orderNumStr, pay_method]
     * @return com.hryj.common.Result
     * @create 2018-06-29 21:34
     **/
    @ApiOperation(value = "确认支付订单", notes = "多个订单编号逗号分隔，返回支付所需签名信息")
    @PostMapping("/confirmPayOrder")
    public Result<Map> confirmPayOrder(@RequestBody ConfirmPayOrderRequestVO confirmPayOrderRequestVO){
        WebUtil.getRequestVO(request,confirmPayOrderRequestVO);
        log.info("确认支付订单：confirmPayOrderRequestVO======" + JSON.toJSONString(confirmPayOrderRequestVO));
        Result<Map> result = orderFeignClient.confirmPayOrder(confirmPayOrderRequestVO);
        log.info("确认支付订单：Result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 罗秋涵
     * @description: 订单支付后查询订单支付结果
     * @param: [orderNumStr]
     * @return com.hryj.common.Result
     * @create 2018-07-03 16:19
     **/
    @ApiOperation(value = "查询订单支付结果", notes = "根据交易流水")
    @RequestMapping(value = "/getOrderPayResult",method = RequestMethod.POST)
    public Result<PaymentGroupResponseVO> getOrderPayResult(@RequestBody SingleParamVO singleParamVO){
        WebUtil.getRequestVO(request,singleParamVO);
        log.info("确认支付订单：singleParamVO======" + JSON.toJSONString(singleParamVO));
        Result<PaymentGroupResponseVO> result = orderFeignClient.getOrderPayResult(singleParamVO);
        log.info("确认支付订单：result======" + JSON.toJSONString(result));
        return  result;
    }


    /**
     * @author 罗秋涵
     * @methodName: findWaitPayOrderList
     * @methodDesc: 查询待支付的订单列表
     * @description:
     * @param: [userOrderListRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.OrderListResponseVO>
     * @create 2018-06-29 22:14
     **/
    @ApiOperation(value = "查询待支付的订单列表,v1.2修改", notes = "查询待支付的订单列表")
    @PostMapping("/findWaitPayOrderList")
    public Result<ListResponseVO<OrderListInfoVO>> findWaitPayOrderList(@RequestBody OrderListRequestVO userOrderListRequestVO){
        WebUtil.getRequestVO(request,userOrderListRequestVO);
        Result<ListResponseVO<OrderListInfoVO>> result =orderFeignClient.findWaitPayOrderList(userOrderListRequestVO);
        log.info("查询待支付的订单列表：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 罗秋涵
     * @methodName: findOrderListByOrderStatus
     * @methodDesc: 根据订单状态查询订单列表
     * @description:
     * @param: [orderListRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.PageResponseVO<com.hryj.entity.vo.order.response.OrderListInfoVO>>
     * @create 2018-06-29 22:16
     **/
    @ApiOperation(value = "根据订单状态查询订单列表 ，v1.2 修改", notes = "根据订单状态查询订单列表")
    @PostMapping("/findOrderListByOrderStatus")
    public Result<PageResponseVO<OrderListInfoVO>> findOrderListByOrderStatus(@RequestBody OrderListRequestVO orderListRequestVO){
        WebUtil.getRequestVO(request,orderListRequestVO);
        log.info("根据订单状态查询订单列表：orderListRequestVO======" + JSON.toJSONString(orderListRequestVO));
        Result<PageResponseVO<OrderListInfoVO>> result = orderFeignClient.findOrderListByOrderStatus(orderListRequestVO);
        log.info("根据订单状态查询订单列表：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 罗秋涵
     * @methodName: findOrderDetail
     * @methodDesc: 查询订单详情
     * @description:
     * @param: [orderIdVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.OrderDetailResponseVO>
     * @create 2018-06-30 9:52
     **/
    @ApiOperation(value = "查询订单详情 v1.2 修改", notes = "查询订单详情")
    @PostMapping("/findOrderDetail")
    public Result<OrderDetailResponseVO> findOrderDetail(@RequestBody OrderIdVO orderIdVO){
        WebUtil.getRequestVO(request,orderIdVO);
        log.info("查询订单详情：orderIdVO======" + JSON.toJSONString(orderIdVO));
        Result<OrderDetailResponseVO> result = orderFeignClient.findOrderDetail(orderIdVO);
        log.info("查询订单详情：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 罗秋涵
     * @methodName: cancelOrder
     * @methodDesc: 取消订单
     * @description:
     * @param: [order_id]
     * @return com.hryj.common.Result
     * @create 2018-06-30 10:08
     **/
    @ApiOperation(value = "取消订单", notes = "取消订单")
    @PostMapping("/cancelOrder")
    public Result cancelOrder(@RequestBody  OrderIdVO orderIdVO) {
        WebUtil.getRequestVO(request, orderIdVO);
        log.info("取消订单：orderIdVO======" + JSON.toJSONString(orderIdVO));
        Result result = orderFeignClient.cancelOrder(orderIdVO);
        log.info("取消订单：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 罗秋涵
     * @description: 获取退货原因列表
     * @param: []
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.ReturnReasonResponseVO>
     * @create 2018-07-03 16:41
     **/
    @ApiOperation(value = "获取退货原因列表", notes = "获取退货原因列表")
    @PostMapping("/getReturnReason")
    public Result<ReturnReasonResponseVO> getReturnReason(){
        Result<ReturnReasonResponseVO> result = orderFeignClient.getReturnReason();
        log.info("获取退货原因列表：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 罗秋涵
     * @methodName: returnOrder
     * @methodDesc: 退货申请
     * @description:
     * @param: [order_id]
     * @return com.hryj.common.Result
     * @create 2018-06-30 10:08
     **/
    @ApiOperation(value = "退货申请", notes = "退货申请")
    @PostMapping("/returnOrder")
    public Result returnOrder(@RequestBody ReturnOrderRequestVO returnOrderRequestVO){
        WebUtil.getRequestVO(request, returnOrderRequestVO);
        log.info("退货申请：returnOrderRequestVO======" + JSON.toJSONString(returnOrderRequestVO));
        Result result = orderFeignClient.returnOrder(returnOrderRequestVO);
        log.info("退货申请：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 罗秋涵
     * @methodName: confirmReceive
     * @methodDesc: 确认收货
     * @description:
     * @param: [orderIdVO]
     * @return com.hryj.common.Result
     * @create 2018-06-30 10:11
     **/
    @ApiOperation(value = "确认收货", notes = "确认收货")
    @PostMapping("/confirmReceive")
    public Result confirmReceive(@RequestBody OrderIdVO orderIdVO){
        WebUtil.getRequestVO(request, orderIdVO);
        log.info("确认收货：orderIdVO======" + JSON.toJSONString(orderIdVO));
        Result result = orderFeignClient.confirmReceive(orderIdVO);
        log.info("确认收货：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 罗秋涵
     * @description: 查询用户各状态订单数量
     * @param: [requestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.OrderDifferentStateNumResponseVO>
     * @create 2018-07-03 16:55
     **/
    @ApiOperation(value = "查询用户各状态订单数量", notes = "查询用户各状态订单数量")
    @PostMapping("/getOrderDifferentStateNum")
    public Result<OrderDifferentStateNumResponseVO> getOrderDifferentStateNum(){
        RequestVO requestVO= WebUtil.getRequestVO(request, null);
        log.info("查询用户各状态订单数量：requestVO======" + JSON.toJSONString(requestVO));
        Result<OrderDifferentStateNumResponseVO> result = orderFeignClient.getOrderDifferentStateNum(requestVO);
        log.info("查询用户各状态订单数量：result======" + JSON.toJSONString(result));
        return result;
    }

}