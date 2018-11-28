package com.hryj.feign;

import com.hryj.common.Result;
import com.hryj.entity.vo.PageRequestVO;
import com.hryj.entity.vo.PageResponseVO;
import com.hryj.entity.vo.RequestVO;
import com.hryj.entity.vo.profit.request.DataQueryRequestVO;
import com.hryj.entity.vo.profit.response.DataQueryResponseVO;
import com.hryj.entity.vo.profit.response.DeptGrossProfitResponseVO;
import com.hryj.entity.vo.profit.response.ProfitDataDetailVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 李道云
 * @className: ProfitFeignClient
 * @description: 分润服务feign接口
 * @create 2018/6/26 17:19
 **/
@FeignClient(name = "profit-server")
public interface ProfitFeignClient {

    /**
     * @author 李道云
     * @methodName: findPersonalOrTeamData
     * @methodDesc: 查询个人或团队的数据
     * @description:
     * @param: [dataQueryRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.profit.response.DataQueryResponseVO>
     * @create 2018-07-07 13:52
     **/
    @RequestMapping(value = "/profitApp/findPersonalOrTeamData", method = RequestMethod.POST)
    Result<DataQueryResponseVO> findPersonalOrTeamData(@RequestBody DataQueryRequestVO dataQueryRequestVO);

    /**
     * @author 李道云
     * @methodName: findProfitDataDetail
     * @methodDesc: 查询分润数据明细
     * @description:
     * @param: [requestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.profit.response.ProfitDataDetailVO>
     * @create 2018-07-09 19:32
     **/
    @RequestMapping(value = "/profitApp/findProfitDataDetail", method = RequestMethod.POST)
    Result<ProfitDataDetailVO> findProfitDataDetail(@RequestBody RequestVO requestVO);

    /**
     * @author 李道云
     * @methodName: searchProfitDataDetail
     * @methodDesc: 分页查询分润数据明细
     * @description:
     * @param: [pageRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.PageResponseVO<com.hryj.entity.vo.profit.response.ProfitDataDetailVO>>
     * @create 2018-07-09 19:33
     **/
    @RequestMapping(value = "/profitApp/searchProfitDataDetail", method = RequestMethod.POST)
    Result<PageResponseVO<ProfitDataDetailVO>> searchProfitDataDetail(@RequestBody PageRequestVO pageRequestVO);

    /**
     * @author 李道云
     * @methodName: searcheDeptGrossProfit
     * @methodDesc: 分页查询部门毛利分润
     * @description:
     * @param: [pageRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.PageResponseVO<com.hryj.entity.vo.profit.response.DeptGrossProfitResponseVO>>
     * @create 2018-08-16 11:32
     **/
    @RequestMapping(value = "/profitApp/searcheDeptGrossProfit", method = RequestMethod.POST)
    Result<PageResponseVO<DeptGrossProfitResponseVO>> searcheDeptGrossProfit(@RequestBody PageRequestVO pageRequestVO);

}
