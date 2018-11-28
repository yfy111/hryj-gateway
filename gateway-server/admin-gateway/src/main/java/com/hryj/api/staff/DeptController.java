package com.hryj.api.staff;

import com.alibaba.fastjson.JSON;
import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.staff.dept.request.*;
import com.hryj.entity.vo.staff.dept.response.*;
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
 * @className: DeptController
 * @description: 部门组织
 * @create 2018/6/28 14:17
 **/
@Api(value="/staff/dept", tags = "部门组织")
@Slf4j
@RestController
@RequestMapping("/staff/dept")
public class DeptController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private StaffFeignClient staffFeignClient;
    /**
     * @author 代廷波
     * @description: 获取部门树型结构
     * @param: null
     * @return
     * @create 2018/06/27 17:11
     **/
    @ApiOperation(value="获取部门树型结构")
    @PostMapping("/getDeptTree")
    public Result<ListResponseVO<DeptGroupTreeResponseVO>> getDeptTree(@RequestBody (required = false) DeptTreeRequestVO deptTreeRequestVO) throws GlobalException {
        if(deptTreeRequestVO==null){
            deptTreeRequestVO = new DeptTreeRequestVO();
        }
        WebUtil.getRequestVO(request,deptTreeRequestVO);
        log.info("获取部门树型结构：getDeptTree======" + JSON.toJSONString(deptTreeRequestVO));
        Result<ListResponseVO<DeptGroupTreeResponseVO>> result =staffFeignClient.getDeptTree(deptTreeRequestVO);
        log.info("获取部门树型结构：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 代廷波
     * @description: 添加部门
     * @param: vo
     * @return com.hryj.common.Result
     * @create 2018/06/27 18:44
     **/
    @ApiOperation(value="添加部门")
    @PostMapping("/saveDept")
    public Result saveDept(@RequestBody DeptRequestVO deptRequestVO) throws GlobalException {
        log.info("添加部门：saveDept======" + JSON.toJSONString(deptRequestVO));
        WebUtil.getRequestVO(request,deptRequestVO);
        Result result=staffFeignClient.saveDept(deptRequestVO);
        log.info("添加部门：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 代廷波
     * @description: 修改部门名称
     * @param: vo
     * @return com.hryj.common.Result
     * @create 2018/06/27 18:44
     **/
    @ApiOperation(value="修改部门名称")
    @PostMapping("/updateDeptName")
    public Result updateDeptName(@RequestBody DeptUpdataNameRequestVO vo) throws GlobalException {
        log.info("修改部门名称：updateDeptName======" + JSON.toJSONString(vo));
        WebUtil.getRequestVO(request,vo);
        Result result= staffFeignClient.updateDeptName(vo);
        log.info("修改部门名称：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 代廷波
     * @description: 根据部门id查询员工列表
     * @param: dept_id 部门id
     * @return com.hryj.common.Result
     * @create 2018/06/27 19:24
     **/
    @ApiOperation(value="根据部门id查询员工列表")
    @PostMapping("/getDeptIdByStaffList")
    public Result<ListResponseVO<DeptStaffListResponseVO>> getDeptIdByStaffList(@RequestBody DeptIdRequestVO vo) throws GlobalException {
        log.info("根据部门id查询员工列表：getDeptIdByStaffList======" + JSON.toJSONString(vo));
        Result result= staffFeignClient.getDeptIdByStaffList(vo);
        log.info("根据部门id查询员工列表：result======" + JSON.toJSONString(result));
        return result;
    }
    /**
     * @author 代廷波
     * @description:  保存部门员工列表
     * @param: vo
     * @return com.hryj.common.Result
     * @create 2018/06/28 18:54
     **/
    @ApiOperation(value="保存部门员工列表")
    @PostMapping("/saveDeptStaff")
    public Result saveDeptStaff(@RequestBody DeptStaffRequestVO vo) throws GlobalException {
        log.info("保存部门员工列表：saveDeptStaff======" + JSON.toJSONString(vo));
        WebUtil.getRequestVO(request,vo);
        Result result= staffFeignClient.saveDeptStaff(vo);
        log.info("保存部门员工列表：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 代廷波
     * @description: 根据部门id查询分润列表
     * @param: dept_id 部门id
     * @return com.hryj.common.Result
     * @create 2018/06/27 19:24
     **/
    @ApiOperation(value="根据部门id查询分润列表")
    @PostMapping("/getDeptIdByShareList")
    public Result<DeptStaffShareResPonseVO> getDeptIdByShareList(@RequestBody DeptShareRequestVO vo) throws GlobalException {
        log.info("根据部门id查询分润列表：getDeptIdByShareList======" + JSON.toJSONString(vo));
        Result<DeptStaffShareResPonseVO> result= staffFeignClient.getDeptIdByShareList(vo);
        log.info("根据部门id查询分润列表：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 代廷波
     * @description: 根据部门id查询向上的部门列表
     * @param: dept_id 部门id
     * @return com.hryj.common.Result
     * @create 2018/06/27 19:24
     **/
    @ApiOperation(value="根据部门id查询向上的部门列表")
    @PostMapping("/getDeptIdByUpDeptList")
    public Result<ListResponseVO<DeptGroupListResponseVO>> getDeptIdByUpDeptList(@RequestBody DeptIdRequestVO vo) throws GlobalException {
        log.info("根据部门id查询向上的部门列表：getDeptIdByUpDeptList======" + JSON.toJSONString(vo));
        Result<ListResponseVO<DeptGroupListResponseVO>> result= staffFeignClient.getDeptIdByUpDeptList(vo);
        log.info("根据部门id查询向上的部门列表：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 代廷波
     * @description: 批量设置部门分润
     * @param: vo
     * @return com.hryj.common.Result
     * @create 2018/07/07 17:49
     **/
    @ApiOperation(value="批量设置部门分润")
    @PostMapping("/savaBatchDeptShare")
    public Result savaBatchDeptShare(@RequestBody DeptShareBatchReqestVO vo) throws GlobalException {
        log.info("批量设置部门分润：savaBatchDeptShare======" + JSON.toJSONString(vo));
        WebUtil.getRequestVO(request,vo);
        Result result= staffFeignClient.savaBatchDeptShare(vo);
        log.info("批量设置部门分润：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 代廷波
     * @description: 根据当前组织id,判断是否可以创建区域公司
     * @param: vo
     * @return com.hryj.common.Result
     * @create 2018/07/09 16:13
     **/
    @ApiOperation(value="根据当前组织id,判断是否可以创建区域公司")
    @PostMapping("/getDeptCreateCompanyFlag")
    public Result<DeptCreateCompanyFlag> getDeptCreateCompanyFlag(@RequestBody DeptIdRequestVO vo) throws GlobalException {
        log.info("根据当前组织id,判断是否可以创建区域公司：getDeptCreateCompanyFlag======" + JSON.toJSONString(vo));
        Result<DeptCreateCompanyFlag> result=staffFeignClient.getDeptCreateCompanyFlag(vo);
        log.info("根据当前组织id,判断是否可以创建区域公司：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 代廷波
     * @description: 根据当前登陆人查询所有门店或者仓库列表
     * @param: vo
     * @return com.hryj.common.Result<com.hryj.entity.vo.ListResponseVO<com.hryj.entity.vo.staff.dept.response.DeptStoreOrWarehouseResponseVO>>
     * @create 2018/07/14 14:34
     **/
    @ApiOperation(value="根据根据当前登陆人查询所有门店或者仓库列表")
    @PostMapping("/getDeptStoreOrWarehouseList")
    public Result<ListResponseVO<DeptStoreOrWarehouseResponseVO>> getDeptStoreOrWarehouseList(@RequestBody DeptStoreOrWarehouseRequestVO vo) throws GlobalException {
        log.info("根据根据当前登陆人查询所有门店或者仓库列表：getDeptStoreOrWarehouseList======" + JSON.toJSONString(vo));
        Result<ListResponseVO<DeptStoreOrWarehouseResponseVO>> result= staffFeignClient.getDeptStoreOrWarehouseList(vo);
        log.info("根据根据当前登陆人查询所有门店或者仓库列表：result======" + JSON.toJSONString(result));
        return result;
    }
    /**
     * @author 代廷波
     * @description: 根据部门id或者员工或者登录token查询下面所有组织
     * @param: vo
     * @return com.hryj.common.Result<com.hryj.entity.vo.ListResponseVO<com.hryj.entity.vo.staff.dept.response.DeptGroupTreeResponseVO>>
     * @create 2018/07/23 14:37
     **/
    @ApiOperation(value="根据部门id或者员工或者登录token查询下面所有组织")
    @PostMapping("/common/getDeptIdByDownDeptTree")
    public Result<ListResponseVO<DeptGroupTreeResponseVO>> getDeptIdByDownDeptTree(@RequestBody DeptDownTreeRequestVO vo) throws GlobalException {
        log.info("根据部门id或者员工或者登录token查询下面所有组织：getDeptIdByDownDeptTree======" + JSON.toJSONString(vo));
        Result<ListResponseVO<DeptGroupTreeResponseVO>> result= staffFeignClient.getDeptIdByDownDeptTree(vo);
        log.info("根据部门id或者员工或者登录token查询下面所有组织：result======" + JSON.toJSONString(result));
        return result;
    }
    /**
     * @author 代廷波
     * @description: 根据当前部门id查询下一级部门
     * @param: vo
     * @return com.hryj.common.Result<com.hryj.entity.vo.ListResponseVO<com.hryj.entity.vo.staff.dept.response.DeptGroupResponseVO>>
     * @create 2018/09/28 10:52
     **/
    @ApiOperation(value="根据当前部门id查询下一级部门 - v1.2")
    @PostMapping("/getDeptPidList")
    public Result<ListResponseVO<DeptGroupPidResponseVO>> getDeptPidList(@RequestBody DeptIdRequestVO vo) throws GlobalException {
        log.info("根据当前部门id查询下一级部门：DeptIdRequestVO======" + JSON.toJSONString(vo));
        Result<ListResponseVO<DeptGroupPidResponseVO>> result= staffFeignClient.getDeptPidList(vo);
        log.info("根据当前部门id查询下一级部门：result======" + JSON.toJSONString(result));
        return result;
    }

}
