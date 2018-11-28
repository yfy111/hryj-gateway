package com.hryj.feign;

import com.hryj.common.Result;
import com.hryj.entity.vo.PageResponseVO;
import com.hryj.entity.vo.staff.storewarehouse.request.SearchPartyRequestVO;
import com.hryj.entity.vo.staff.storewarehouse.response.PartySearchItemResponseVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 李道云
 * @className: StaffFeignClient
 * @description: 员工服务feign接口
 * @create 2018/6/26 17:25
 **/
@FeignClient(name = "staff-server")
public interface StoreWarehouseFeignClient {
    /**
     * @author 王光银
     * @methodName: searchPartyPage
     * @methodDesc: 返回当前请求用户能够看到的所有仓库或门店数据
     * @description: 数据权限处理依据当前请求用户在组织结构上的节点位置
     * @param: [partySearchRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.PageResponseVO<com.hryj.entity.vo.product.partyprod.response.PartySearchItemResponseVO>>
     * @create 2018-06-30 9:20
     **/
    @RequestMapping(value = "/storeWarehouse/searchVisiblePage", method = RequestMethod.POST)
    Result<PageResponseVO<PartySearchItemResponseVO>> searchVisiblePartyPage(
            @RequestBody SearchPartyRequestVO partySearchRequestVO);
}
