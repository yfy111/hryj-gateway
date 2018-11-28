package com.hryj.api;

import com.alibaba.fastjson.JSON;
import com.hryj.common.Result;
import com.hryj.entity.vo.PageRequestVO;
import com.hryj.entity.vo.PageResponseVO;
import com.hryj.entity.vo.RequestVO;
import com.hryj.entity.vo.profit.response.DeptGrossProfitResponseVO;
import com.hryj.entity.vo.profit.response.ProfitDataDetailVO;
import com.hryj.feign.ProfitFeignClient;
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
 * @author 李道云
 * @className: ProfitController
 * @description: 分润模块
 * @create 2018/7/7 13:47
 **/
@Api(value="/profit", tags = "分润模块")
@Slf4j
@RestController
@RequestMapping("/profit")
public class ProfitController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ProfitFeignClient profitFeignClient;

    /**
     * @author 李道云
     * @methodName: findProfitDataDetail
     * @methodDesc: 查询分润数据明细
     * @description:
     * @param: []
     * @return com.hryj.common.Result<com.hryj.entity.vo.profit.response.ProfitDataDetailVO>
     * @create 2018-07-09 17:13
     **/
    @ApiOperation(value="查询分润数据明细",notes = "查询分润数据明细")
    @PostMapping("/findProfitDataDetail")
    public Result<ProfitDataDetailVO> findProfitDataDetail(){
        RequestVO requestVO = WebUtil.getRequestVO(request,null);
        log.info("查询分润数据明细：requestVO======" + JSON.toJSONString(requestVO));
        Result<ProfitDataDetailVO> result = profitFeignClient.findProfitDataDetail(requestVO);
        log.info("查询分润数据明细：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 李道云
     * @methodName: searchProfitDataDetail
     * @methodDesc: 分页查询分润数据明细
     * @description:
     * @param: [pageRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.PageResponseVO<com.hryj.entity.vo.profit.response.ProfitDataDetailVO>>
     * @create 2018-07-09 17:13
     **/
    @ApiOperation(value="分页查询分润数据明细",notes = "分页查询分润数据明细")
    @PostMapping("/searchProfitDataDetail")
    public Result<PageResponseVO<ProfitDataDetailVO>> searchProfitDataDetail(@RequestBody PageRequestVO pageRequestVO){
        if (pageRequestVO == null) {
            pageRequestVO = new PageRequestVO();
        }
        WebUtil.getRequestVO(request,pageRequestVO);
        log.info("分页查询分润数据明细：pageRequestVO======" + JSON.toJSONString(pageRequestVO));
        Result<PageResponseVO<ProfitDataDetailVO>> result = profitFeignClient.searchProfitDataDetail(pageRequestVO);
        log.info("分页查询分润数据明细：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 李道云
     * @methodName: searcheDeptGrossProfit
     * @methodDesc: 分页查询部门毛利分润
     * @description:
     * @param: [pageRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.PageResponseVO<com.hryj.entity.vo.profit.response.DeptGrossProfitResponseVO>>
     * @create 2018-08-16 11:32
     **/
    @ApiOperation(value="分页查询部门毛利分润",notes = "分页查询部门毛利分润")
    @PostMapping("/searcheDeptGrossProfit")
    public Result<PageResponseVO<DeptGrossProfitResponseVO>> searcheDeptGrossProfit(@RequestBody PageRequestVO pageRequestVO){
        if (pageRequestVO == null) {
            pageRequestVO = new PageRequestVO();
        }
        WebUtil.getRequestVO(request,pageRequestVO);
        log.info("分页查询部门毛利分润：pageRequestVO======" + JSON.toJSONString(pageRequestVO));
        Result<PageResponseVO<DeptGrossProfitResponseVO>> result = profitFeignClient.searcheDeptGrossProfit(pageRequestVO);
        log.info("分页查询部门毛利分润：result======" + JSON.toJSONString(result));
        return result;
    }

}
