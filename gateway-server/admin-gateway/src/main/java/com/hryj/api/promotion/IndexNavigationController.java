package com.hryj.api.promotion;

import com.alibaba.fastjson.JSON;
import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.RequestVO;
import com.hryj.entity.vo.promotion.indexNavigation.request.IndexNavigationListRequestVO;
import com.hryj.entity.vo.promotion.indexNavigation.response.IndexNavigationResponseVO;
import com.hryj.feign.IndexNavigationFeignClient;
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
 * @author 汪豪
 * @className: IndexNavigationController
 * @description:
 * @create 2018/8/24 0024 8:39
 **/
@Api(value="/promontion/navigationMgr", tags = "首页导航管理")
@Slf4j
@RestController
@RequestMapping("/promontion/navigationMgr")
public class IndexNavigationController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private IndexNavigationFeignClient indexNavigationFeignClient;

    /**
     * @author 汪豪
     * @methodName: createOrUpdateIndexNavigation
     * @methodDesc:
     * @description:
     * @param: [indexNavigationListRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-08-23 14:15
     **/
    @ApiOperation(value = "后台管理修改首页导航位", notes = "后台管理修改首页导航位")
    @PostMapping("/createOrUpdateIndexNavigation")
    public Result createOrUpdateIndexNavigation(@RequestBody IndexNavigationListRequestVO indexNavigationListRequestVO){
        log.info("后台管理修改首页导航位:indexNavigationListRequestVO====="+JSON.toJSONString(indexNavigationListRequestVO));
        return indexNavigationFeignClient.createOrUpdateIndexNavigation(indexNavigationListRequestVO);
    }

    /**
     * @author 汪豪
     * @methodName: showIndexNavigation
     * @methodDesc:
     * @description:
     * @param: []
     * @return com.hryj.common.Result<com.hryj.entity.vo.ListResponseVO<com.hryj.entity.vo.promotion.indexNavigation.response.IndexNavigationResponseVO>>
     * @create 2018-08-23 14:43
     **/
    @ApiOperation(value = "后台管理展示首页导航位", notes = "后台管理展示首页导航位")
    @PostMapping("/showIndexNavigation")
    public Result<ListResponseVO<IndexNavigationResponseVO>> showIndexNavigation(){
        RequestVO requestVO = WebUtil.getRequestVO(request, null);
        Result<ListResponseVO<IndexNavigationResponseVO>> result = indexNavigationFeignClient.showIndexNavigation(requestVO);
        log.info("后台管理展示首页导航位:result====="+JSON.toJSONString(result));
        return result;
    }

}
