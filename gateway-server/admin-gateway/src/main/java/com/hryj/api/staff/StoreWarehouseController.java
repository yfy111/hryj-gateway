package com.hryj.api.staff;

import com.alibaba.fastjson.JSON;
import com.hryj.common.Result;
import com.hryj.entity.vo.PageResponseVO;
import com.hryj.entity.vo.staff.storewarehouse.request.SearchPartyRequestVO;
import com.hryj.entity.vo.staff.storewarehouse.response.PartySearchItemResponseVO;
import com.hryj.exception.BizException;
import com.hryj.feign.StoreWarehouseFeignClient;
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
 * @author 王光银
 * @className: StoreWarehouseController
 * @description: 门店仓库综合公共接口
 * @create 2018/7/4 15:52
 **/
@Api(value="/staff/storeWarehouse", tags = "门店仓库综合公共接口")
@Slf4j
@RestController
@RequestMapping("/staff/storeWarehouse")
public class StoreWarehouseController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private StoreWarehouseFeignClient storeWarehouseFeignClient;

    /**
     * @author 王光银
     * @methodName: searchPartyPage
     * @methodDesc: 返回当前请求用户能够看到的所有仓库或门店数据
     * @description: 数据权限处理依据当前请求用户在组织结构上的节点位置
     * @param: [partySearchRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.PageResponseVO<com.hryj.entity.vo.product.partyprod.response.PartySearchItemResponseVO>>
     * @create 2018-06-30 9:20
     **/
    @ApiOperation(value="可见门店或仓库查询")
    @PostMapping("/searchVisiblePage")
    public Result<PageResponseVO<PartySearchItemResponseVO>> searchVisiblePartyPage(@RequestBody SearchPartyRequestVO partySearchRequestVO) throws BizException {
        WebUtil.getRequestVO(request, partySearchRequestVO);
        log.info("返回当前请求用户能够看到的所有仓库或门店数据-- request:" + JSON.toJSONString(partySearchRequestVO));
        Result<PageResponseVO<PartySearchItemResponseVO>> result = storeWarehouseFeignClient.searchVisiblePartyPage(partySearchRequestVO);
        log.info("返回当前请求用户能够看到的所有仓库或门店数据-- response:" + JSON.toJSONString(result));
        return result;
    }
}
