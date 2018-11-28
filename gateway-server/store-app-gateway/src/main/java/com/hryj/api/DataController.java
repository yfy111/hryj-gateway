package com.hryj.api;

import com.alibaba.fastjson.JSON;
import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.RequestVO;
import com.hryj.entity.vo.order.UserTradeVO;
import com.hryj.entity.vo.product.common.request.TopSaleRequestVO;
import com.hryj.entity.vo.product.response.ProductTopSalesItemResponseVO;
import com.hryj.entity.vo.profit.request.DataQueryRequestVO;
import com.hryj.entity.vo.profit.response.DataQueryResponseVO;
import com.hryj.entity.vo.staff.team.response.AppTeamDataResponseVO;
import com.hryj.entity.vo.user.UserInfoVO;
import com.hryj.feign.*;
import com.hryj.feign.product.CommonProductFeignClient;
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
 * @className: DataController
 * @description: 数据模块
 * @create 2018/7/7 13:47
 **/
@Api(value="/data", tags = "数据模块")
@Slf4j
@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ProfitFeignClient profitFeignClient;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private OrderFeignClient orderFeignClient;

    @Autowired
    private StaffFeignClient staffFeignClient;

    @Autowired
    private CommonProductFeignClient commonProductFeignClient;

    /**
     * @author 李道云
     * @methodName: findPersonalOrTeamData
     * @methodDesc: 查询个人或团队的数据
     * @description: 进入团队数据页面，传当前员工的所在部门id
     * @param: [dataQueryRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.profit.response.DataQueryResponseVO>
     * @create 2018-07-07 13:45
     **/
    @ApiOperation(value="查询个人或团队的数据",notes = "staff_id、store_id、dept_id、wh_id都为空时查询当前登录员工的数据,否则就是查询单个员工、门店、部门的数据")
    @PostMapping("/findPersonalOrTeamData")
    public Result<DataQueryResponseVO> findPersonalOrTeamData(@RequestBody DataQueryRequestVO dataQueryRequestVO){
        if(dataQueryRequestVO ==null){
            dataQueryRequestVO = new DataQueryRequestVO();
        }
        WebUtil.getRequestVO(request,dataQueryRequestVO);
        log.info("查询个人或团队的数据：dataQueryRequestVO======" + JSON.toJSONString(dataQueryRequestVO));
        Result<DataQueryResponseVO> result = profitFeignClient.findPersonalOrTeamData(dataQueryRequestVO);
        log.info("查询个人或团队的数据：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 李道云
     * @methodName: findReferralRegisterUserList
     * @methodDesc: 查询推荐注册用户列表
     * @description:
     * @param: [dataQueryRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.ListResponseVO<com.hryj.entity.vo.user.UserInfoVO>>
     * @create 2018-07-07 17:32
     **/
    @ApiOperation(value="查询推荐注册用户列表",notes = "staff_id、store_id、dept_id、wh_id都为空时查询当前登录员工的数据,否则就是查询单个员工、门店、部门的数据")
    @PostMapping("/findReferralRegisterUserList")
    public Result<ListResponseVO<UserInfoVO>> findReferralRegisterUserList(@RequestBody DataQueryRequestVO dataQueryRequestVO){
        if(dataQueryRequestVO ==null){
            dataQueryRequestVO = new DataQueryRequestVO();
        }
        WebUtil.getRequestVO(request,dataQueryRequestVO);
        log.info("查询推荐注册用户列表：dataQueryRequestVO======" + JSON.toJSONString(dataQueryRequestVO));
        Result<ListResponseVO<UserInfoVO>> result = userFeignClient.findReferralRegisterUserList(dataQueryRequestVO);
        log.info("查询推荐注册用户列表：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 李道云
     * @methodName: 查询交易用户列表
     * @methodDesc:
     * @description:
     * @param: [dataQueryRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-09 10:19
     **/
    @ApiOperation(value="查询交易用户列表",notes = "查询交易用户列表")
    @PostMapping("/findTradeUserList")
    public Result<ListResponseVO<UserTradeVO>> findTradeUserList(@RequestBody DataQueryRequestVO dataQueryRequestVO){
        if(dataQueryRequestVO ==null){
            dataQueryRequestVO = new DataQueryRequestVO();
        }
        WebUtil.getRequestVO(request,dataQueryRequestVO);
        log.info("查询交易用户列表：dataQueryRequestVO======" + JSON.toJSONString(dataQueryRequestVO));
        Result<ListResponseVO<UserTradeVO>> result = orderFeignClient.findTradeUserList(dataQueryRequestVO);
        log.info("查询交易用户列表：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 李道云
     * @methodName: 查询新增交易用户列表
     * @methodDesc:
     * @description:
     * @param: [dataQueryRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-09 10:19
     **/
    @ApiOperation(value="查询新增交易用户列表",notes = "查询新增交易用户列表")
    @PostMapping("/findNewTradeUserList")
    public Result<ListResponseVO<UserTradeVO>> findNewTradeUserList(@RequestBody DataQueryRequestVO dataQueryRequestVO){
        if(dataQueryRequestVO ==null){
            dataQueryRequestVO = new DataQueryRequestVO();
        }
        WebUtil.getRequestVO(request,dataQueryRequestVO);
        log.info("查询新增交易用户列表：dataQueryRequestVO======" + JSON.toJSONString(dataQueryRequestVO));
        Result<ListResponseVO<UserTradeVO>> result = orderFeignClient.findNewTradeUserList(dataQueryRequestVO);
        log.info("查询新增交易用户列表：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 代廷波
     * @description: 获取部门组织数据
     * @param: teamDataRequestVO
     * @return com.hryj.common.Result<com.hryj.entity.vo.staff.team.response.AppTeamDataResponseVO>
     * @create 2018/07/17 19:05
     **/
    @ApiOperation(value="获取部门组织数据",notes = "获取部门组织数据：店员列表、门店列表、仓库列表、部门列表")
    @PostMapping("/getTeamData")
    public Result<AppTeamDataResponseVO> getTeamData(){
        RequestVO requestVO = WebUtil.getRequestVO(request,null);
        log.info("获取部门组织数据：requestVO======" + JSON.toJSONString(requestVO));
        Result<AppTeamDataResponseVO> result = staffFeignClient.getTeamData(requestVO);
        log.info("获取部门组织数据：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.ListResponseVO   <   com.hryj.entity.vo.product.response.ProductTopSalesItemResponseVO>>
     * @author 王光银
     * @methodName: findTopSalesProduct
     * @methodDesc: 查询TOP销量商品
     * @description: 该接口的统计范围是在统计时刻仍然在销售的商品，已经下架的商品不管销售多高不会返回，同时参数party_id有值返回指定门店或仓库的TOP销售商品，否则返回全国范围内的TOP销量商品，目前返回的商品数量为10个
     * @param: [partyIdRequestVO]
     * @create 2018-07-03 21:45
     **/
    @ApiOperation(value = "查询TOP销量商品（不包含下架商品）", notes = "按照销量统计，已经下架的商品不管销售多高不会返回，返回集合按照销量排序， 参数party_id有值返回指定门店或仓库的TOP销售商品，否则返回全国范围内的TOP销量商品，目前返回的商品数量为10， 没有销售数据时返回按照创建时间倒序的前10个")
    @PostMapping("/findTopSalesProduct")
    public Result<ListResponseVO<ProductTopSalesItemResponseVO>> findTopSalesProduct(
            @RequestBody TopSaleRequestVO topSaleRequestVO) {
        WebUtil.getRequestVO(request, topSaleRequestVO);
        log.info("查询TOP销量商品（不包含下架商品） -- request:" + JSON.toJSONString(topSaleRequestVO));
        Result result = commonProductFeignClient.findTopSalesProduct(topSaleRequestVO);
        log.info("查询TOP销量商品（不包含下架商品） -- response:" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.ListResponseVO   <   com.hryj.entity.vo.product.response.ProductTopSalesItemResponseVO>>
     * @author 王光银
     * @methodName: findTopSalesAndHisProduct
     * @methodDesc: 查询TOP销量商品
     * @description: 该接口的统计范围是只按照销量统计，不管商品是否下架，返回集合按照销量排序， 参数 party_id有值返回指定门店或仓库的TOP销售商品，否则返回全国范围内的TOP销量商品，目前返回的商品数量为10个
     * @param: [partyIdRequestVO]
     * @create 2018-07-03 21:45
     **/
    @ApiOperation(value = "查询TOP销量商品（包含已下架商品）", notes = "按照销量统计，不管商品是否下架，返回集合按照销量排序， 参数party_id有值返回指定门店或仓库的TOP销售商品，否则返回全国范围内的TOP销量商品，目前返回的商品数量为10， 没有销售数据时返回按照创建时间倒序的前10个")
    @PostMapping("/findTopSalesAndHisProduct")
    public Result<ListResponseVO<ProductTopSalesItemResponseVO>> findTopSalesAndHisProduct(
            @RequestBody TopSaleRequestVO topSaleRequestVO) {
        WebUtil.getRequestVO(request, topSaleRequestVO);
        log.info("查询TOP销量商品（包含已下架商品） -- request:" + JSON.toJSONString(topSaleRequestVO));
        Result result = commonProductFeignClient.findTopSalesAndHisProduct(topSaleRequestVO);
        log.info("查询TOP销量商品（包含已下架商品） -- response:" + JSON.toJSONString(result));
        return result;
    }
}
