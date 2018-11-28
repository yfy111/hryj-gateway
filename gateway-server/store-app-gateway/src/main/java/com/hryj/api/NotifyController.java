package com.hryj.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egzosn.pay.ali.api.AliPayService;
import com.egzosn.pay.wx.api.WxPayService;
import com.hryj.cache.CodeCache;
import com.hryj.common.Result;
import com.hryj.feign.OrderFeignClient;
import com.hryj.pay.AliPay;
import com.hryj.pay.WxPay;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * @author 李道云
 * @className: NotifyController
 * @description: 支付回调
 * @create 2018/7/17 21:49
 **/
@ApiIgnore
@Slf4j
@Controller
@RequestMapping(value = "/notify")
public class NotifyController {

    private static final String party_id ="100000";//红瑞集团
    private static final String cross_party_id ="100001";//光彩国际
    private static final String app_key ="HRYJ-STORE";
    private static final String wx_payment_method ="01";//01-微信
    private static final String ali_payment_method ="02";//02-支付宝

    @Autowired
    private OrderFeignClient orderFeignClient;

    /**
     * 微信支付回调
     * @param request
     * @return
     */
    @RequestMapping(value = "/webchat")
    public String webchat(HttpServletRequest request) throws IOException {
        log.info("微信支付回调,request.getParameterMap()===" + JSON.toJSONString(request.getParameterMap()));
        String paymentConfig = CodeCache.getValueByKey("PaymentConfig", "S" + app_key + party_id + wx_payment_method);
        JSONObject payment_config = JSON.parseObject(paymentConfig);
        WxPayService service = WxPay.getWxPayService(payment_config);
        Map<String, Object> param_map = service.getParameter2Map(request.getParameterMap(), request.getInputStream());
        if (null == param_map) {
            return service.getPayOutMessage("FAIL", "支付回调参数为空").toMessage();
        }
        log.info("微信支付回调参数,param_map=====" + JSON.toJSONString(param_map));
        //支付回调签名校验
        boolean flag = service.verify(param_map);
        log.info("微信支付回调签名校验,flag=====" + flag);
        if(!flag){
            return service.getPayOutMessage("FAIL", "支付回调签名校验失败").toMessage();
        }
        Result result = orderFeignClient.notifyForWxPay(param_map);
        log.info("微信支付回调处理,result=====" + JSON.toJSONString(result));
        if(result.isSuccess()){
            return service.getPayOutMessage("SUCCESS", "支付回调处理成功").toMessage();
        }else{
            return service.getPayOutMessage("FAIL", "支付回调处理失败").toMessage();
        }
    }

    /**
     * 支付宝支付回调
     * @param request
     * @return
     */
    @RequestMapping(value = "/alipay")
    public String alipay(HttpServletRequest request) throws IOException {
        log.info("支付宝支付回调,request.getParameterMap()===" + JSON.toJSONString(request.getParameterMap()));
        String paymentConfig = CodeCache.getValueByKey("PaymentConfig", "S" + app_key + party_id + ali_payment_method);
        JSONObject payment_config = JSON.parseObject(paymentConfig);
        AliPayService service = AliPay.getAliPayService(payment_config);
        Map<String, Object> param_map = service.getParameter2Map(request.getParameterMap(), request.getInputStream());
        if (null == param_map) {
            return service.getPayOutMessage("fail", "支付回调参数为空").toMessage();
        }
        log.info("支付宝支付回调餐,param_map=====" + JSON.toJSONString(param_map));
        //支付回调签名校验
        boolean flag = service.verify(param_map);
        log.info("支付回调签名校验,flag=====" + flag);
        if(!flag){
            return service.getPayOutMessage("fail", "支付回调签名校验失败").toMessage();
        }
        Result result = orderFeignClient.notifyForAliPay(param_map);
        log.info("支付宝支付回调处理,result=====" + JSON.toJSONString(result));
        if(result.isSuccess()){
            return service.getPayOutMessage("success", "支付回调处理成功").toMessage();
        }else{
            return service.getPayOutMessage("failure", "支付回调处理失败").toMessage();
        }
    }

    /**
     * 跨境电商微信支付回调
     * @param request
     * @return
     */
    @RequestMapping(value = "/webchatForCoross")
    public String webchatForCoross(HttpServletRequest request) throws IOException {
        log.info("跨境电商微信支付回调,request.getParameterMap()===" + JSON.toJSONString(request.getParameterMap()));
        String paymentConfig = CodeCache.getValueByKey("PaymentConfig", "S" + app_key + cross_party_id + wx_payment_method);
        JSONObject payment_config = JSON.parseObject(paymentConfig);
        WxPayService service = WxPay.getWxPayService(payment_config);
        Map<String, Object> param_map = service.getParameter2Map(request.getParameterMap(), request.getInputStream());
        if (null == param_map) {
            return service.getPayOutMessage("FAIL", "支付回调参数为空").toMessage();
        }
        log.info("跨境电商微信支付回调参数,param_map=====" + JSON.toJSONString(param_map));
        //支付回调签名校验
        boolean flag = service.verify(param_map);
        log.info("跨境电商微信支付回调签名校验,flag=====" + flag);
        if(!flag){
            return service.getPayOutMessage("FAIL", "支付回调签名校验失败").toMessage();
        }
        Result result = orderFeignClient.notifyForWxPay(param_map);
        log.info("跨境电商微信支付回调处理,result=====" + JSON.toJSONString(result));
        if(result.isSuccess()){
            return service.getPayOutMessage("SUCCESS", "支付回调处理成功").toMessage();
        }else{
            return service.getPayOutMessage("FAIL", "支付回调处理失败").toMessage();
        }
    }

    /**
     * 跨境电商支付宝支付回调
     * @param request
     * @return
     */
    @RequestMapping(value = "/alipayForCoross")
    public String alipayForCoross(HttpServletRequest request) throws IOException {
        log.info("跨境电商支付宝支付回调,request.getParameterMap()===" + JSON.toJSONString(request.getParameterMap()));
        String paymentConfig = CodeCache.getValueByKey("PaymentConfig", "S" + app_key + cross_party_id + ali_payment_method);
        JSONObject payment_config = JSON.parseObject(paymentConfig);
        AliPayService service = AliPay.getAliPayService(payment_config);
        Map<String, Object> param_map = service.getParameter2Map(request.getParameterMap(), request.getInputStream());
        if (null == param_map) {
            return service.getPayOutMessage("fail", "支付回调参数为空").toMessage();
        }
        log.info("跨境电商支付宝支付回调餐,param_map=====" + JSON.toJSONString(param_map));
        //支付回调签名校验
        boolean flag = service.verify(param_map);
        log.info("跨境电商支付回调签名校验,flag=====" + flag);
        if(!flag){
            return service.getPayOutMessage("fail", "支付回调签名校验失败").toMessage();
        }
        Result result = orderFeignClient.notifyForAliPay(param_map);
        log.info("跨境电商支付宝支付回调处理,result=====" + JSON.toJSONString(result));
        if(result.isSuccess()){
            return service.getPayOutMessage("success", "支付回调处理成功").toMessage();
        }else{
            return service.getPayOutMessage("failure", "支付回调处理失败").toMessage();
        }
    }

}
