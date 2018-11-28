package com.hryj.api.order;

import com.alibaba.fastjson.JSON;
import com.hryj.common.Result;
import com.hryj.entity.vo.PageResponseVO;
import com.hryj.entity.vo.order.DistributionInfoVO;
import com.hryj.entity.vo.order.ReturnVO;
import com.hryj.entity.vo.order.request.DistributionForStoreRequestVO;
import com.hryj.entity.vo.order.request.ReturnRequestVO;
import com.hryj.entity.vo.order.request.SendOrdersRequestVO;
import com.hryj.entity.vo.order.response.DistributionListReponseVO;
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
 * @className: OrderShipmentsController
 * @description: 门店端发货管理（店长）
 * @create 2018/7/6 0006 9:24
 **/

@Api(value="/storeOrderShipments", tags = "发货管理（店长）")
@Slf4j
@RestController
@RequestMapping(value = "/storeOrderShipments")
public class OrderShipmentsController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OrderFeignClient orderFeignClient;

    /**
     * @author 罗秋涵
     * @methodName: findDistributionListForStore
     * @methodDesc: 查询待分配配送单列表(店长)
     * @description: distribution_type：配送类别:01-送货,02-取货；distribution_status：配送单状态:01-待分配,02-待配送,03-配送完成,04-配送超时
     * @param: [distribution_type, distribution_status]
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.DistributionListReponseVO>
     * @create 2018-06-30 14:18
     **/
    @ApiOperation(value="查询待分配配送单列表(店长)，,v1.2修改",notes = "distribution_type：配送类别:01-送货,02-取货；distribution_status：配送单状态:01-待分配,02-待配送,03-配送完成,04-配送超时")
    @PostMapping("/findDistributionListForStore")
    public Result<DistributionListReponseVO> findDistributionListForStore(@RequestBody DistributionForStoreRequestVO distributionForStoreRequestVO){
        WebUtil.getRequestVO(request,distributionForStoreRequestVO);
        log.info("查询待分配配送单列表(店长)：distributionForStoreRequestVO======" + JSON.toJSONString(distributionForStoreRequestVO));
        Result<DistributionListReponseVO> result = orderFeignClient.findDistributionListForStore(distributionForStoreRequestVO);
        log.info("查询待分配配送单列表(店长)：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 罗秋涵
     * @description: 查询已分配配送单列表(店长)
     * @param: [distributionForStoreRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.DistributionResponseVO>
     * @create 2018-07-18 16:38
     **/
    @ApiOperation(value="查询已分配配送单列表(店长)",notes = "查询已分配配送单列表(店长)")
    @PostMapping("/findAssignedDistributionList")
    public Result<PageResponseVO<DistributionInfoVO>>findAssignedDistributionList(@RequestBody DistributionForStoreRequestVO distributionForStoreRequestVO){
        WebUtil.getRequestVO(request,distributionForStoreRequestVO);
        log.info("查询已分配配送单列表(店长)：distributionForStoreRequestVO======" + JSON.toJSONString(distributionForStoreRequestVO));
        Result<PageResponseVO<DistributionInfoVO>> result = orderFeignClient.findAssignedDistributionList(distributionForStoreRequestVO);
        log.info("查询已分配配送单列表(店长)：result======" + JSON.toJSONString(result));
        return  result;
    }

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.order.response.DistributionListReponseVO>
     * @author 罗秋涵
     * @methodName: findDistributionListForStore
     * @methodDesc: 查询待分配退货单列表(店长)
     * @param: [requestVO, distribution_type, distribution_status]
     * @create 2018-06-30 14:18
     **/
    @ApiOperation(value="查询待分配退货单列表(店长)",notes = "查询待分配退货单列表(店长)")
    @PostMapping("/findReturnListForStore")
    public Result<PageResponseVO<ReturnVO>> findReturnListForStore(@RequestBody ReturnRequestVO returnRequestVO) {
        WebUtil.getRequestVO(request,returnRequestVO);
        log.info("查询待分配退货单列表(店长)：returnOrderListRequestVO======" + JSON.toJSONString(returnRequestVO));
        Result<PageResponseVO<ReturnVO>> result = orderFeignClient.findReturnListForStore(returnRequestVO);
        log.info("查询待分配退货单列表(店长)：result======" + JSON.toJSONString(result));
        return  result;
    }

    /**
     * @author 罗秋涵
     * @methodName: assignDistribution
     * @methodDesc: 分派配送单
     * @description: 多个配送单id逗号分隔
     * @param: [requestVO, distribution_id, staff_id]
     * @return com.hryj.common.Result
     * @create 2018-06-30 14:23
     **/
    @ApiOperation(value="分派配送单",notes = "分派配送单")
    @PostMapping("/assignDistribution")
    public Result assignDistribution(@RequestBody SendOrdersRequestVO sendOrdersRequestVO){
        WebUtil.getRequestVO(request,sendOrdersRequestVO);
        log.info("分派配送单：sendOrdersRequestVO======" + JSON.toJSONString(sendOrdersRequestVO));
        Result result = orderFeignClient.assignDistribution(sendOrdersRequestVO);
        log.info("分派配送单：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 罗秋涵
     * @methodName: assignDistribution
     * @methodDesc: 分派退货单处理人
     * @description: 多个配送单id逗号分隔
     * @param: [requestVO, distribution_id, staff_id]
     * @return com.hryj.common.Result
     * @create 2018-06-30 14:23
     **/
    @ApiOperation(value="分派退货单处理人",notes = "取货单")
    @PostMapping("/returnDistribution")
    public Result returnDistribution(@RequestBody SendOrdersRequestVO sendOrdersRequestVO){
        WebUtil.getRequestVO(request,sendOrdersRequestVO);
        log.info("分派退货单处理人：sendOrdersRequestVO======" + JSON.toJSONString(sendOrdersRequestVO));
        Result result = orderFeignClient.returnDistribution(sendOrdersRequestVO);
        log.info("分派退货单处理人：result======" + JSON.toJSONString(result));
        return result;
    }

}
