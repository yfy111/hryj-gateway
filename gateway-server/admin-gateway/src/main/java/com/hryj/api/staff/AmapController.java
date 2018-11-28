package com.hryj.api.staff;

import com.alibaba.fastjson.JSON;
import com.hryj.common.Result;
import com.hryj.entity.vo.amap.request.AmapAroundRequestVO;
import com.hryj.entity.vo.amap.request.AmapDistrictRequestVO;
import com.hryj.exception.GlobalException;
import com.hryj.feign.StaffFeignClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 代廷波
 * @className: AmapController
 * @description: 地图接口
 * @create 2018/7/13 0013-16:05
 **/
@Api(value="/staff/amap", tags = "地图接口")
@RestController
@RequestMapping("/staff/amap")
@Slf4j
public class AmapController {
    @Autowired
    private StaffFeignClient staffFeignClient;

    /**
     * @author 代廷波
     * @description: 周边搜索
     * @param: vo
     * @return com.hryj.common.Result
     * @create 2018/07/19 11:22
     **/
    @ApiOperation(value="获取地图信息")
    @PostMapping("/getAmapAround")
    public Result getAmapAround(@RequestBody AmapAroundRequestVO vo) throws GlobalException {
        log.info("获取地图信息：getDeptStoreOrWarehouseList======" + JSON.toJSONString(vo));
        Result result = staffFeignClient.getAmapAround(vo);
        log.info("获取地图信息：result======" + JSON.toJSONString(result));
        return result;
    }
    /**
     * @author 代廷波
     * @description: 行政搜索
     * @param: vo
     * @return com.hryj.common.Result
     * @create 2018/07/19 11:42
     **/
    @ApiOperation(value="行政搜索")
    @PostMapping("/getAmapAmapDistrict")
    public Result getAmapAmapDistrict(@RequestBody AmapDistrictRequestVO vo) throws GlobalException {
        log.info("行政搜索：getAmapAmapDistrict======" + JSON.toJSONString(vo));
        Result result = staffFeignClient.getAmapAmapDistrict(vo);
        log.info("行政搜索：result======" + JSON.toJSONString(result));
        return result;
    }

}
