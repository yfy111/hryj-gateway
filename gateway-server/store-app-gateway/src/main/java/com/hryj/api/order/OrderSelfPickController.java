package com.hryj.api.order;

import com.alibaba.fastjson.JSON;
import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.order.request.ConfirmSelfPickRequestVO;
import com.hryj.entity.vo.order.request.SelfPickRequestVO;
import com.hryj.entity.vo.order.response.SelfPickResponseVO;
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
 * @className: OrderForStoreSelfPickController
 * @description: 门店端自提管理
 * @create 2018/7/6 0006 9:27
 **/
@Api(value="/storeOrderSelfPick", tags = "自提管理")
@Slf4j
@RestController
@RequestMapping(value = "/storeOrderSelfPick")
public class OrderSelfPickController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OrderFeignClient orderFeignClient;

    /**
     * @author 罗秋涵
     * @methodName: findOrderSelfPick
     * @methodDesc: 根据自提码查询自提单信息
     * @description:
     * @param: [self_pick_code, phone_num]
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.SelfPickResponseVO>
     * @create 2018-06-30 15:31
     **/
    @ApiOperation(value="根据自提码查询自提单信息",notes = "根据自提码查询自提单信息")
    @PostMapping("/findOrderSelfPick")
    public Result<SelfPickResponseVO> findOrderSelfPick(@RequestBody SelfPickRequestVO selfPickRequestVO){
        WebUtil.getRequestVO(request,selfPickRequestVO);
        log.info("根据自提码查询自提单信息：selfPickRequestVO======" + JSON.toJSONString(selfPickRequestVO));
        Result<SelfPickResponseVO> result = orderFeignClient.findOrderSelfPick(selfPickRequestVO);
        log.info("根据自提码查询自提单信息：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 罗秋涵
     * @description: 根据用户电话查询自提信息列表
     * @param: [phone_num]
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.OrderSelfPickListResponseVO>
     * @create 2018-07-03 17:22
     **/
    @ApiOperation(value="根据用户电话查询自提信息列表",notes = "根据用户电话查询自提信息列表")
    @PostMapping("/findOrderSelfPickListByPhoneNum")
    public Result<ListResponseVO<SelfPickResponseVO>> findOrderSelfPickListByPhoneNum(@RequestBody SelfPickRequestVO selfPickRequestVO){
        WebUtil.getRequestVO(request,selfPickRequestVO);
        log.info("根据用户电话查询自提信息列表：selfPickRequestVO======" + JSON.toJSONString(selfPickRequestVO));
        Result<ListResponseVO<SelfPickResponseVO>> result = orderFeignClient.findOrderSelfPickListByPhoneNum(selfPickRequestVO);
        log.info("根据用户电话查询自提信息列表：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 罗秋涵
     * @methodName: confirmSelfPick
     * @methodDesc: 确认自提处理
     * @description:
     * @param: [self_pick_id]
     * @return com.hryj.common.Result
     * @create 2018-06-30 15:25
     **/
    @ApiOperation(value="确认自提处理",notes = "确认自提处理")
    @PostMapping("/confirmSelfPick")
    public Result confirmSelfPick(@RequestBody ConfirmSelfPickRequestVO confirmSelfPickRequestVO){
        WebUtil.getRequestVO(request,confirmSelfPickRequestVO);
        log.info("确认自提处理：confirmSelfPickRequestVO======" + JSON.toJSONString(confirmSelfPickRequestVO));
        Result result = orderFeignClient.confirmSelfPick(confirmSelfPickRequestVO);
        log.info("确认自提处理：result======" + JSON.toJSONString(result));
        return result;
    }


}
