package com.hryj.api.staff;

import com.alibaba.fastjson.JSON;
import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.PageResponseVO;
import com.hryj.entity.vo.staff.role.request.*;
import com.hryj.entity.vo.staff.role.response.ResourceTreeResponseVO;
import com.hryj.entity.vo.staff.role.response.RoleListParamResponseVO;
import com.hryj.entity.vo.staff.role.response.RoleNameListResponseVO;
import com.hryj.entity.vo.staff.role.response.RolePermissionTreeResponseVO;
import com.hryj.exception.BizException;
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
 * @className: RoleController
 * @description: 角色模块
 * @create 2018/6/26 17:34
 **/
@Api(value="/staff/role", tags = "角色模块 - v1.2")
@Slf4j
@RestController
@RequestMapping("/staff/role")
public class RoleController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private StaffFeignClient staffFeignClient;

    /**
     * @author 代廷波
     * @description:
     * @param: role_name 角色名
     * @return com.hryj.common.Result
     * @create 2018/06/23 14:02
     **/
    @ApiOperation(value="添加角色")
    @PostMapping("/saveRole")
    public Result saveRole(@RequestBody RoleAddRequestVO vo) throws GlobalException {
        log.info("添加角色：saveRole======" + JSON.toJSONString(vo));
        WebUtil.getRequestVO(request,vo);
        Result result =  staffFeignClient.saveRole(vo);
        log.info("添加角色：result======" + JSON.toJSONString(result));
        return result;
    }
    /**
     * @author 代廷波
     * @description: 修改角色名
     * @param: vo
     * @return com.hryj.common.Result
     * @create 2018/07/23 20:00
     **/
    @ApiOperation(value="修改角色名")
    @PostMapping("/updateRole")
    public Result updateRole(@RequestBody RoleAddRequestVO vo) throws GlobalException {
        log.info("修改角色名：updateRole======" + JSON.toJSONString(vo));
        WebUtil.getRequestVO(request,vo);
        Result result =  staffFeignClient.updateRole(vo);
        log.info("修改角色名：result======" + JSON.toJSONString(result));
        return result;

    }
    /**
     * @author 代廷波
     * @description: 修改角名状态
     * @param: role_id 角色id
     * @param: status_value 角色状态:1-正常,0-停用
     * @return com.hryj.common.Result
     * @create 2018/06/23 14:06
     **/
    @ApiOperation(value="修改角名状态")
    @PostMapping("/updateRoleStatus")
    public Result updateRoleStatus(@RequestBody RoleUpdateStatusRequestVO vo)throws GlobalException {
        log.info("修改角名状态：updateRoleStatus======" + JSON.toJSONString(vo));
        WebUtil.getRequestVO(request,vo);
        Result result =  staffFeignClient.updateRoleStatus(vo);
        log.info("修改角名状态：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 代廷波
     * @description: 添加角色资源
     * @param: role_id 角色id
     * @param: resource_ids 资源集合 数组
     * @param: login_token 用户 token
     * @return com.hryj.common.Result
     * @create 2018/06/27 21:23
     **/
    @ApiOperation(value="添加角色资源 - v1.2")
    @PostMapping("/saveRoleResource")
    public Result saveRoleResource(@RequestBody RoleAddResourceRequestVO vo) throws GlobalException {
        log.info("添加角色资源：saveRoleResource======" + JSON.toJSONString(vo));
        WebUtil.getRequestVO(request,vo);
        Result result =  staffFeignClient.saveRoleResource(vo);
        log.info("添加角色资源：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 代廷波
     * @description: 角色列表查询
     * @param: staffRoleDto
     * @return com.hryj.common.PageResult
     * @create 2018/06/23 14:31
     **/
    @ApiOperation(value="角色列表查询")
    @PostMapping("/getRoleList")
    public Result<PageResponseVO<RoleListParamResponseVO>> getRoleList(@RequestBody RoleListParamRequestVO staffRoleDto)throws GlobalException {
        log.info("角色列表查询：getRoleList======" + JSON.toJSONString(staffRoleDto));
        Result<PageResponseVO<RoleListParamResponseVO>> result =  staffFeignClient.getRoleList(staffRoleDto);
        log.info("角色列表查询：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 代廷波
     * @description: 获取资源树
     * @param:
     * @return com.hryj.common.Result<com.hryj.entity.vo.staff.role.response.ResourceTreeResponseVO>
     * @create 2018/06/28 20:46
     **/
    @ApiOperation(value="获取资源树")
    @PostMapping("/getResourceTree")
    public Result<ListResponseVO<ResourceTreeResponseVO>> getResourceTree()throws GlobalException {
        Result<ListResponseVO<ResourceTreeResponseVO>> result =  staffFeignClient.getResourceTree();
        log.info("获取资源树：result======" + JSON.toJSONString(result));
        return result;
    }
    /**
     * @author 代廷波
     * @description: 角色名列表
     * @param: role_name 角色名
     * @return com.hryj.common.Result<com.hryj.entity.vo.staff.role.response.RoleNameListResponseVO>
     * @create 2018/06/28 20:57
     **/
    @ApiOperation(value="根据角色名查询列表")
    @PostMapping("/getRoleNameList")
    public Result<ListResponseVO<RoleNameListResponseVO>> getRoleNameList(@RequestBody RoleNameRequestVO vo)throws GlobalException {
        log.info("根据角色名查询列表：getRoleNameList======" + JSON.toJSONString(vo));
        Result<ListResponseVO<RoleNameListResponseVO>> result =  staffFeignClient.getRoleNameList(vo);
        log.info("根据角色名查询列表：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 代廷波
     * @description: 根据角色查询对应的资源
     * @param: role_id 角色id
     * @return com.hryj.common.Result<com.hryj.entity.vo.staff.role.response.RoleNameListResponseVO>
     * @create 2018/06/29 21:08
     **/
    @ApiOperation(value="根据角色id查询对应的资源")
    @PostMapping("/getRoleIdByResource")
    public Result<ListResponseVO<ResourceTreeResponseVO>> getRoleIdByResource(@RequestBody RoleIdRequestVO vo)throws GlobalException {
        log.info("根据角色查询对应的资源：getRoleIdByResource======" + JSON.toJSONString(vo));
        Result<ListResponseVO<ResourceTreeResponseVO>>  result = staffFeignClient.getRoleIdByResource(vo);
        log.info("根据角色查询对应的资源：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 王光银
     * @methodName: getRolePermissionTree
     * @methodDesc: 加载角色权限树
     * @description:
     * @param: [vo]
     * @return com.hryj.common.Result<com.hryj.entity.vo.staff.role.response.RolePermissionTreeResponseVO>
     * @create 2018-08-04 15:05
     **/
    @ApiOperation(value="获取角色权限树（基于所有权限的权限树）")
    @PostMapping("/getRolePermissionTree")
    public Result<RolePermissionTreeResponseVO> getRolePermissionTree(@RequestBody RoleIdRequestVO vo) throws BizException {
        log.info("加载角色权限树：getRolePermissionTree======" + JSON.toJSONString(vo));
        Result<RolePermissionTreeResponseVO>  result = staffFeignClient.getRolePermissionTree(vo);
        log.info("加载角色权限树：getRolePermissionTree======" + JSON.toJSONString(result));
        return result;
    }
}
