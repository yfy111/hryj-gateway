
package com.hryj.api.staff;

/**
 * @author 李道云
 * @className: StoreController
 * @description: 门店模块
 * @create 2018/6/28 15:24
 **/

import com.alibaba.fastjson.JSON;
import com.hryj.common.Result;
import com.hryj.entity.vo.PageResponseVO;
import com.hryj.entity.vo.staff.store.request.StoreIdRequestVO;
import com.hryj.entity.vo.staff.store.request.StoreInfoRequestVO;
import com.hryj.entity.vo.staff.store.request.StoreListParamRequestVO;
import com.hryj.entity.vo.staff.store.request.StoreUpdateStatusRequestVO;
import com.hryj.entity.vo.staff.store.response.StoreInfoResponseVO;
import com.hryj.entity.vo.staff.store.response.StoreListResponseVO;
import com.hryj.exception.GlobalException;
import com.hryj.feign.StaffFeignClient;
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


@Api(value="/staff/store", tags = "门店模块")
@Slf4j
@RestController
@RequestMapping("/staff/store")
public class StoreController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private StaffFeignClient staffFeignClient;

    /**
     * @author 代廷波
     * @description: 添加门店
     * @param: vo
     * @return com.hryj.common.Result
     * @create 2018/06/27 18:44
     **/
    @ApiOperation(value="添加门店")
    @PostMapping("/saveStore")
    public Result saveStore(@RequestBody StoreInfoRequestVO vo) throws GlobalException {
        log.info("添加门店：saveStore======" + JSON.toJSONString(vo));
        WebUtil.getRequestVO(request,vo);
        Result result = staffFeignClient.saveStore(vo);
        log.info("添加门店：result======" + JSON.toJSONString(result));
        return result;

    }

    /**
     * @author 代廷波
     * @description: 修改门店
     * @param: vo
     * @return com.hryj.common.Result
     * @create 2018/06/27 18:44
     **/
    @ApiOperation(value="修改门店")
    @PostMapping("/updateStore")
    public Result updateStore(@RequestBody StoreInfoRequestVO vo) throws GlobalException {
        log.info("修改门店：updateStore======" + JSON.toJSONString(vo));
        WebUtil.getRequestVO(request,vo);
        Result result = staffFeignClient.updateStore(vo);
        log.info("修改门店：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 代廷波
     * @description: 根据门店id查询详情
     * @param: Store_id 门店id
     * @return com.hryj.common.Result
     * @create 2018/06/27 19:24
     **/
    @ApiOperation(value=" 根据门店id查询详情")

    @PostMapping("/getStoreIdByDet")
    public Result<StoreInfoResponseVO> getStoreIdByDet(@RequestBody StoreIdRequestVO vo) throws GlobalException {
        log.info("根据门店id查询详情：getStoreIdByDet======" + JSON.toJSONString(vo));
        Result<StoreInfoResponseVO> result = staffFeignClient.getStoreIdByDet(vo);
        log.info("根据门店id查询详情：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 代廷波
     * @description: 修改门店状态
     * @param: vo
     * @return com.hryj.common.Result
     * @create 2018/06/27 19:53
     **/
    @ApiOperation(value="修改门店状态")
    @PostMapping("/updateStoreStatus")
    public Result updateStoreStatus(@RequestBody StoreUpdateStatusRequestVO vo) throws GlobalException {
        log.info("修改门店状态：updateStoreStatus======" + JSON.toJSONString(vo));
        WebUtil.getRequestVO(request,vo);
        Result result = staffFeignClient.updateStoreStatus(vo);
        log.info("修改门店状态：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 代廷波
     * @description: 门店列表查询
     * @param: vo
     * @return
     * @create 2018/06/27 19:39
     **/
    @ApiOperation(value="门店列表查询")
    @PostMapping("/getStoreList")
    public Result<PageResponseVO<StoreListResponseVO>> getStoreList(@RequestBody StoreListParamRequestVO vo)throws GlobalException{
        log.info("门店列表查询：getStoreList======" + JSON.toJSONString(vo));
        Result<PageResponseVO<StoreListResponseVO>> result = staffFeignClient.getStoreList(vo);
        log.info("门店列表查询：result======" + JSON.toJSONString(result));
        return result;
    }

}

