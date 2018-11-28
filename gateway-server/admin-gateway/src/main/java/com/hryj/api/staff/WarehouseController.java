package com.hryj.api.staff;

import com.alibaba.fastjson.JSON;
import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.PageResponseVO;
import com.hryj.entity.vo.staff.warehouse.request.*;
import com.hryj.entity.vo.staff.warehouse.response.WarehouseCityAreaResponseVO;
import com.hryj.entity.vo.staff.warehouse.response.WarehouseInfoResponseVO;
import com.hryj.entity.vo.staff.warehouse.response.WarehouseListResponseVO;
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

/**
 * @author 李道云
 * @className: WarehouseController
 * @description: 仓库模块
 * @create 2018/6/28 15:52
 **/
@Api(value="/staff/warehouse", tags = "仓库模块")
@Slf4j
@RestController
@RequestMapping("/staff/warehouse")
public class WarehouseController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private StaffFeignClient staffFeignClient;

    /**
     * @author 代廷波
     * @description: 添加仓库
     * @param: vo
     * @return com.hryj.common.Result
     * @create 2018/06/27 18:44
     **/
    @ApiOperation(value="添加仓库")
    @PostMapping("/saveWarehose")
    public Result saveWarehose(@RequestBody WarehouseInfoRequestVO vo) throws GlobalException {
        log.info("添加仓库：saveWarehose======" + JSON.toJSONString(vo));
        WebUtil.getRequestVO(request,vo);
        Result result= staffFeignClient.saveWarehose(vo);
        log.info("添加仓库：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 代廷波
     * @description: 修改仓库
     * @param: vo
     * @return com.hryj.common.Result
     * @create 2018/06/27 18:44
     **/
    @ApiOperation(value="修改仓库")
    @PostMapping("/updateWarehose")
    public Result updateWarehose(@RequestBody WarehouseInfoRequestVO vo) throws GlobalException {
        log.info("修改仓库：updateWarehose======" + JSON.toJSONString(vo));
        WebUtil.getRequestVO(request,vo);
        Result  result = staffFeignClient.updateWarehose(vo);
        log.info("修改仓库：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 代廷波
     * @description: 根据仓库id查询详情
     * @param: warehose_id 仓库id
     * @return com.hryj.common.Result
     * @create 2018/06/27 19:24
     **/
    @ApiOperation(value="根据仓库id查询详情")
    @PostMapping("/getWarehoseByIdDet")
    public Result<WarehouseInfoResponseVO> getWarehoseByIdDet(@RequestBody WarehouseIdRequestVO vo) throws GlobalException {
        log.info("根据仓库id查询详情：getWarehoseByIdDet======" + JSON.toJSONString(vo));
        Result<WarehouseInfoResponseVO> result = staffFeignClient.getWarehoseByIdDet(vo);
        log.info("根据仓库id查询详情：result======" + JSON.toJSONString(result));
        return result;
    }


    /**
     * @author 代廷波
     * @description: 修改仓库状态
     * @param: warehose_id 仓库id
     * @param: status_value 仓库状态:1-正常,0-关闭
     * @return com.hryj.common.Result
     * @create 2018/07/04 10:55
     **/
    @ApiOperation(value="修改仓库状态")
    @PostMapping("/updateWarehoseStatus")
    public Result updateWarehoseStatus(@RequestBody WarehouseUpdateStatusRequestVO vo) throws GlobalException {
        log.info("修改仓库状态：updateWarehoseStatus======" + JSON.toJSONString(vo));
        WebUtil.getRequestVO(request,vo);
        Result result = staffFeignClient.updateWarehoseStatus(vo);
        log.info("修改仓库状态：result======" + JSON.toJSONString(result));
        return result;
    }
    /**
     * @author 代廷波
     * @description: 仓库列表查询
     * @param: vo
     * @return
     * @create 2018/06/27 19:39
     **/
    @ApiOperation(value="仓库列表查询")
    @PostMapping("/getWarehoseList")
    public Result<PageResponseVO<WarehouseListResponseVO>> getWarehoseList(@RequestBody WarehuoseParamRequestVO vo)throws GlobalException{
        log.info("仓库列表查询：getWarehoseList======" + JSON.toJSONString(vo));
        Result<PageResponseVO<WarehouseListResponseVO>> result = staffFeignClient.getWarehoseList(vo);
        log.info("仓库列表查询：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 代廷波
     * @description: 根据省ia获取仓库的配送区域
     * @param: vo
     * @return com.hryj.common.Result<com.hryj.entity.vo.ListResponseVO<com.hryj.entity.vo.staff.warehouse.response.WarehouseCityAreaResponseVO>>
     * @create 2018/07/17 11:52
     **/
    @ApiOperation(value="根据省(直辖市)ia获取仓库的配送区域")
    @PostMapping("/getWarehoseCityAreaList")
    public Result<ListResponseVO<WarehouseCityAreaResponseVO>> getWarehoseCityAreaList(@RequestBody WareHouseCityIdsRequestVO vo)throws GlobalException{
        log.info("根据省(直辖市)ia获取仓库的配送区域：getWarehoseCityAreaList======" + JSON.toJSONString(vo));
        Result<ListResponseVO<WarehouseCityAreaResponseVO>> result = staffFeignClient.getWarehoseCityAreaList(vo);
        log.info("根据省(直辖市)ia获取仓库的配送区域：result======" + JSON.toJSONString(result));
        return result;

    }
}
