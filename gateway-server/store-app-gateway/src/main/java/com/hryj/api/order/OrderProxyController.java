package com.hryj.api.order;

import com.alibaba.fastjson.JSON;
import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.PageResponseVO;
import com.hryj.entity.vo.RequestVO;
import com.hryj.entity.vo.cart.request.CartOperationRequestVO;
import com.hryj.entity.vo.cart.request.ProductBuyRequestVO;
import com.hryj.entity.vo.cart.request.ShoppingCartRequestVO;
import com.hryj.entity.vo.order.SingleParamVO;
import com.hryj.entity.vo.order.request.*;
import com.hryj.entity.vo.order.response.*;
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
 * @className: OrderForStoreProxyController
 * @description: 门店端代下单管理
 * @create 2018/7/6 0006 9:29
 **/
@Api(value="/order", tags = "下单管理")
@Slf4j
@RestController
@RequestMapping(value = "/order")
public class OrderProxyController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OrderFeignClient orderFeignClient;


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
        log.info("代下单-商祥页立即购买或购物车去结算：cartRequestVO======" + JSON.toJSONString(productRequestVO));
        Result<OrderConfirmInfoResponseVO> result = orderFeignClient.confirmInfo(productRequestVO);
        log.info("代下单-商祥页立即购买或购物车去结算：cartRequestVO======" + JSON.toJSONString(result));
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
        log.info("代下单-确认订单去支付：orderCreateRequest======" + JSON.toJSONString(orderCreateRequest));
        Result<OrderPaymentResponseVO> result = orderFeignClient.create(orderCreateRequest);
        log.info("代下单-确认订单去支付：orderCreateRequest======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 罗秋涵
     * @methodName: immediateBuy
     * @methodDesc: 立即购买
     * @description:
     * @param: [shoppingCartRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.OrderSettlementResponseVO>
     * @create 2018-06-29 21:43
     **/
    @ApiOperation(value = "立即购买.v1.2", notes = "立即购买 状态值biz_code: 11000商品下架，11010活动已结束，11020商品库存不足，11030商品不存在，16100组织不存在，11040 商品价格变动，11144 跨境订单金额超限")
    @PostMapping("/immediateBuy")
    public Result<OrderSettlementResponseVO> immediateBuy(@RequestBody ShoppingCartRequestVO shoppingCartRequestVO){
        WebUtil.getRequestVO(request,shoppingCartRequestVO);
        log.info("立即购买：shoppingCartRequestVO======" + JSON.toJSONString(shoppingCartRequestVO));
        Result<OrderSettlementResponseVO> result = orderFeignClient.storeImmediateBuy(shoppingCartRequestVO);
        log.info("立即购买：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 罗秋涵
     * @methodName: settlementOrderForHelp
     * @methodDesc: 代下单去结算
     * @description: 购物车去结算到订单确认页面
     * @param: [user_id, cartItemIds]
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.OrderSettlementResponseVO>
     * @create 2018-06-30 14:52
     **/
    @ApiOperation(value="代下单去结算.v1.2",notes = "购物车去结算到订单确认页面 状态值biz_code: 11000商品下架，11010活动已结束，11020商品库存不足，11030商品不存在，16100组织不存在，11040 商品价格变动，11144 跨境订单金额超限")
    @PostMapping("/settlementOrder")
    public Result<OrderSettlementResponseVO> settlementOrder(@RequestBody CartOperationRequestVO cartoPerationRequestVO) {
        WebUtil.getRequestVO(request,cartoPerationRequestVO);
        log.info("代下单去结算：cartoPerationRequestVO======" + JSON.toJSONString(cartoPerationRequestVO));
        Result<OrderSettlementResponseVO> result = orderFeignClient.storeSettlementOrder(cartoPerationRequestVO);
        log.info("代下单去结算：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 罗秋涵
     * @methodName: saveHelpOrderInfo
     * @methodDesc: 代下单保存订单信息
     * @description: 代下单提交页面
     * @param: [orderSaveRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.OrderToPayResponseVO>
     * @create 2018-06-29 20:51
     **/
    @ApiOperation(value="代下单保存订单信息.v1.2",notes = "代下单提交页面 状态值biz_code: 11000商品下架，11010活动已结束，11020商品库存不足，11030商品不存在，16100组织不存在，11040 商品价格变动")
    @PostMapping("/saveOrderForPay")
    public Result<OrderToPayResponseVO> saveOrderForPay(@RequestBody OrderSaveRequestVO orderSaveRequestVO){
        WebUtil.getRequestVO(request,orderSaveRequestVO);
        log.info("代下单保存订单信息：orderSaveRequestVO======" + JSON.toJSONString(orderSaveRequestVO));
        Result<OrderToPayResponseVO> result = orderFeignClient.storeSaveOrderForPay(orderSaveRequestVO);
        log.info("代下单保存订单信息：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 罗秋涵
     * @methodName: confirmPayOrder
     * @methodDesc: 确认支付订单
     * @description: 多个订单编号逗号分隔，返回支付签名，由客户端发起支付
     * @param: [orderNumStr, pay_method]
     * @return com.hryj.common.Result
     * @create 2018-06-29 21:34
     **/
    @ApiOperation(value="确认支付订单",notes = "多个订单编号逗号分隔，返回支付签名，由客户端发起支付")
    @PostMapping("/confirmPayOrder")
    public Result confirmPayOrder(@RequestBody ConfirmPayOrderRequestVO confirmPayOrderRequestVO){
        WebUtil.getRequestVO(request,confirmPayOrderRequestVO);
        log.info("确认支付订单：confirmPayOrderRequestVO======" + JSON.toJSONString(confirmPayOrderRequestVO));
        Result result = orderFeignClient.confirmPayOrder(confirmPayOrderRequestVO);
        log.info("确认支付订单：Result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 罗秋涵
     * @methodName: findWaitPayOrderList
     * @methodDesc: 查询待支付的订单列表
     * @description:
     * @param: [orderListRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.OrderListResponseVO>
     * @create 2018-06-29 22:14
     **/
    @ApiOperation(value="查询待支付的订单列表 ,v1.2修改",notes = "查询待支付的订单列表")
    @PostMapping("/findWaitPayOrderList")
    public Result<ListResponseVO<HistoricalOrderListResponseVO>> findWaitPayOrderList(@RequestBody OrderListRequestVO orderListRequestVO){
        WebUtil.getRequestVO(request,orderListRequestVO);
        log.info("查询待支付的订单列表：orderListRequestVO======" + JSON.toJSONString(orderListRequestVO));
        Result<ListResponseVO<HistoricalOrderListResponseVO>> result = orderFeignClient.storeFindWaitPayOrderList(orderListRequestVO);
        log.info("查询待支付的订单列表：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 罗秋涵
     * @methodName: selectOrderForPay
     * @methodDesc: 选择多个订单进行支付
     * @description: 多个订单id逗号分隔
     * @param: [orderNumStr]
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.OrderToPayResponseVO>
     * @create 2018-06-29 21:53
     **/
    @ApiOperation(value="选择多个订单进行支付",notes = "多个订单id逗号分隔")
    @PostMapping("/selectOrderForPay")
    public Result<OrderToPayResponseVO> selectOrderForPay(@RequestBody OrderForPayRequestVO orderForPayRequestVO){
        WebUtil.getRequestVO(request,orderForPayRequestVO);
        log.info("选择多个订单进行支付：orderForPayRequestVO======" + JSON.toJSONString(orderForPayRequestVO));
        Result<OrderToPayResponseVO> result = orderFeignClient.storeSelectOrderForPay(orderForPayRequestVO);
        log.info("选择多个订单进行支付：result======" + JSON.toJSONString(result));
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
    @PostMapping("/getOrderPayResult")
    public Result getOrderPayResult(@RequestBody SingleParamVO singleParamVO){
        WebUtil.getRequestVO(request,singleParamVO);
        log.info("确认支付订单：singleParamVO======" + JSON.toJSONString(singleParamVO));
        Result result = orderFeignClient.getOrderPayResult(singleParamVO);
        log.info("确认支付订单：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 罗秋涵
     * @methodName: findOrderListByOrderStatus
     * @methodDesc: 根据订单状态查询订单列表
     * @description:
     * @param: [userOrderListRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.PageResponseVO<com.hryj.entity.vo.order.OrderListInfoVO>>
     * @create 2018-06-29 22:16
     **/
    @ApiOperation(value="根据订单状态查询订单列表,v1.2修改",notes = "根据订单状态查询订单列表")
    @PostMapping("/findOrderListByOrderStatus")
    public Result<PageResponseVO<HistoricalOrderListResponseVO>> findOrderListByOrderStatus(@RequestBody OrderListRequestVO orderListRequestVO){
        WebUtil.getRequestVO(request,orderListRequestVO);
        log.info("根据订单状态查询订单列表：orderListRequestVO======" + JSON.toJSONString(orderListRequestVO));
        Result<PageResponseVO<HistoricalOrderListResponseVO>> result = orderFeignClient.storeFindOrderListByOrderStatus(orderListRequestVO);
        log.info("根据订单状态查询订单列表：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 罗秋涵
     * @methodName: findOrderDetail
     * @methodDesc: 查询订单详情
     * @description:
     * @param: [order_id]
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.OrderDetailResponseVO>
     * @create 2018-06-30 9:52
     **/
    @ApiOperation(value="查询订单详情 v1.2 修改",notes = "查询订单详情")
    @PostMapping("/findOrderDetail")
    public Result<OrderDetailResponseVO> findOrderDetail(@RequestBody OrderIdVO orDerIdVO){
        WebUtil.getRequestVO(request,orDerIdVO);
        log.info("查询订单详情：orDerIdVO======" + JSON.toJSONString(orDerIdVO));
        Result<OrderDetailResponseVO> result = orderFeignClient.storeFindOrderDetail(orDerIdVO);
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
    @ApiOperation(value="取消订单",notes = "取消订单")
    @PostMapping("/cancelOrder")
    public Result cancelOrder(@RequestBody OrderIdVO orDerIdVO){
        WebUtil.getRequestVO(request,orDerIdVO);
        log.info("取消订单：orDerIdVO======" + JSON.toJSONString(orDerIdVO));
        Result result = orderFeignClient.storeCancelOrder(orDerIdVO);
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
        Result<ReturnReasonResponseVO> result = orderFeignClient.storeGetReturnReason();
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
    @ApiOperation(value="退货申请",notes = "退货申请")
    @PostMapping("/returnOrder")
    public Result returnOrder(@RequestBody ReturnOrderRequestVO returnOrderRequestVO){
        WebUtil.getRequestVO(request,returnOrderRequestVO);
        log.info("退货申请：returnOrderRequestVO======" + JSON.toJSONString(returnOrderRequestVO));
        Result result = orderFeignClient.storeReturnOrder(returnOrderRequestVO);
        log.info("退货申请：result======" + JSON.toJSONString(result));
        return result;
    }
    /**
     * @author 李道云
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
     * @description: 判断员工是否有代下单访问权限
     * @param: [requestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-03 19:43
     **/
    @PostMapping("/getHelpOrderAccessRight")
    @ApiOperation(value="判断员工是否有代下单访问权限",notes = "返回:help_flag: true|false")
    public Result getHelpOrderAccessRight() {
        RequestVO requestVO = WebUtil.getRequestVO(request,null);
        log.info("判断员工是否有代下单访问权限：requestVO======" + JSON.toJSONString(requestVO));
        Result result = orderFeignClient.getHelpOrderAccessRight(requestVO);
        log.info("判断员工是否有代下单访问权限：result======" + JSON.toJSONString(result));
        return result;
    }

}
