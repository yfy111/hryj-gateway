package com.hryj.feign;

import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.PageResponseVO;
import com.hryj.entity.vo.RequestVO;
import com.hryj.entity.vo.amap.request.AmapAroundRequestVO;
import com.hryj.entity.vo.amap.request.AmapDistrictRequestVO;
import com.hryj.entity.vo.staff.dept.request.*;
import com.hryj.entity.vo.staff.dept.response.*;
import com.hryj.entity.vo.staff.role.request.*;
import com.hryj.entity.vo.staff.role.response.ResourceTreeResponseVO;
import com.hryj.entity.vo.staff.role.response.RoleListParamResponseVO;
import com.hryj.entity.vo.staff.role.response.RoleNameListResponseVO;
import com.hryj.entity.vo.staff.role.response.RolePermissionTreeResponseVO;
import com.hryj.entity.vo.staff.store.request.StoreIdRequestVO;
import com.hryj.entity.vo.staff.store.request.StoreInfoRequestVO;
import com.hryj.entity.vo.staff.store.request.StoreListParamRequestVO;
import com.hryj.entity.vo.staff.store.request.StoreUpdateStatusRequestVO;
import com.hryj.entity.vo.staff.store.response.StoreInfoResponseVO;
import com.hryj.entity.vo.staff.store.response.StoreListResponseVO;
import com.hryj.entity.vo.staff.user.StaffUserVO;
import com.hryj.entity.vo.staff.user.request.*;
import com.hryj.entity.vo.staff.user.response.*;
import com.hryj.entity.vo.staff.warehouse.request.*;
import com.hryj.entity.vo.staff.warehouse.response.WarehouseCityAreaResponseVO;
import com.hryj.entity.vo.staff.warehouse.response.WarehouseInfoResponseVO;
import com.hryj.entity.vo.staff.warehouse.response.WarehouseListResponseVO;
import com.hryj.exception.BizException;
import com.hryj.exception.GlobalException;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 李道云
 * @className: StaffFeignClient
 * @description: 员工服务feign接口
 * @create 2018/6/26 17:25
 **/
@FeignClient(name = "staff-server")
public interface StaffFeignClient {

//***************部门开始***************************************************************************************************

    /**
     * @return
     * @author 代廷波
     * @description: 获取部门树型结构
     * @param: null
     * @create 2018/06/27 17:11
     **/
    @PostMapping("/dept/getDeptTree")
    Result<ListResponseVO<DeptGroupTreeResponseVO>> getDeptTree(DeptTreeRequestVO deptTreeRequestVO) throws GlobalException;

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.staff.dept.response.DeptGroupTreeResponseVO>
     * @author 代廷波
     * @description: 根据当前根节点的dept_pid查询所有父节点
     * @param: 当前节点的dept_pid
     * @create 2018/06/27 17:11
     **/
    //@PostMapping("/dept/getDeptIdUpDeptTree")
    //Result<ListResponseVO<DeptGroupListResponseVO>> getDeptIdUpDeptTree(@RequestBody DeptPidRequestVO deptPidRequestVO) throws GlobalException;

    /**
     * @return com.hryj.common.Result
     * @author 代廷波
     * @description: 添加部门
     * @param: vo
     * @create 2018/06/27 18:44
     **/

    @PostMapping("/dept/saveDept")
    Result saveDept(@RequestBody DeptRequestVO deptRequestVO) throws GlobalException;


    /**
     * @return com.hryj.common.Result
     * @author 代廷波
     * @description: 修改部门名称
     * @param: vo
     * @create 2018/06/27 18:44
     **/

    @PostMapping("/dept/updateDeptName")
    @ApiOperation(value = "更新部门名称")
    Result updateDeptName(@RequestBody DeptUpdataNameRequestVO deptUpdataNameRequestVO) throws GlobalException;

    /**
     * @return com.hryj.common.Result
     * @author 代廷波
     * @description: 根据部门id查询员工列表
     * @param: dept_id 部门id
     * @create 2018/06/27 19:24
     **/
    @PostMapping("/dept/getDeptIdByStaffList")
    Result<ListResponseVO<DeptStaffListResponseVO>> getDeptIdByStaffList(@RequestBody DeptIdRequestVO deptIdRequestVO) throws GlobalException;

    /**
     * @return com.hryj.common.Result
     * @author 代廷波
     * @description: 存部门员工列表
     * @param: vo
     * @create 2018/06/28 18:54
     **/
    @PostMapping("/dept/saveDeptStaff")
    Result saveDeptStaff(@RequestBody DeptStaffRequestVO deptStaffRequestVO) throws GlobalException;

    /**
     * @return com.hryj.common.Result
     * @author 代廷波
     * @description: 根据部门id查询分润列表
     * @param: dept_id 部门id
     * @create 2018/06/27 19:24
     **/
    @PostMapping("/dept/getDeptIdByShareList")
    Result<DeptStaffShareResPonseVO> getDeptIdByShareList(@RequestBody DeptShareRequestVO deptIdRequestVO) throws GlobalException;

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.staff.dept.response.DeptStaffShareListResPonseVO>
     * @author 代廷波
     * @description: 通过部门添加分润
     * @param: vo
     * @create 2018/06/28 18:51
     **/
    // @PostMapping("/dept/saveDeptShare")
    // Result<DeptStaffShareListResPonseVO> saveDeptShare(@RequestBody DeptShareConfigReqestVO deptShareConfigReqestVO) throws GlobalException;

    /**
     * @return com.hryj.common.Result
     * @author 代廷波
     * @description: 根据部门id查询向上的部门列表
     * @param: dept_id 部门id
     * @create 2018/06/27 19:24
     **/
    @ApiOperation(value = "根据部门id查询向上的部门列表")
    @PostMapping("/dept/getDeptIdByUpDeptList")
    Result<ListResponseVO<DeptGroupListResponseVO>> getDeptIdByUpDeptList(@RequestBody DeptIdRequestVO deptIdRequestVO) throws GlobalException;

    /**
     * @return com.hryj.common.Result
     * @author 代廷波
     * @description: 批量设置部门分润
     * @param: vo
     * @create 2018/07/07 17:49
     **/
    @ApiOperation(value = "批量设置部门分润")
    @PostMapping("/dept/savaBatchDeptShare")
    Result savaBatchDeptShare(@RequestBody DeptShareBatchReqestVO deptShareBatchReqestVO) throws GlobalException;

    /**
     * @return com.hryj.common.Result
     * @author 代廷波
     * @description: 根据当前组织id, 判断是否可以创建区域公司
     * @param: vo
     * @create 2018/07/09 16:13
     **/
    @PostMapping("/dept/getDeptCreateCompanyFlag")
    Result<DeptCreateCompanyFlag> getDeptCreateCompanyFlag(@RequestBody DeptIdRequestVO vo) throws GlobalException;

    @PostMapping("/dept/getDeptStoreOrWarehouseList")
    Result<ListResponseVO<DeptStoreOrWarehouseResponseVO>> getDeptStoreOrWarehouseList(@RequestBody DeptStoreOrWarehouseRequestVO vo) throws GlobalException;

    /**
     * @author 代廷波
     * @description: 根据部门id或者员工或者登录token查询下面所有组织
     * @param: vo
     * @return com.hryj.common.Result<com.hryj.entity.vo.ListResponseVO<com.hryj.entity.vo.staff.dept.response.DeptGroupTreeResponseVO>>
     * @create 2018/07/23 14:37
     **/
    @PostMapping("/dept/common/getDeptIdByDownDeptTree")
    Result<ListResponseVO<DeptGroupTreeResponseVO>> getDeptIdByDownDeptTree(@RequestBody DeptDownTreeRequestVO vo) throws GlobalException;

    @PostMapping("/dept/getDeptPidList")
    Result<ListResponseVO<DeptGroupPidResponseVO>> getDeptPidList(DeptIdRequestVO vo);

    //***************部门结束***************************************************************************************************


    //***************角色开始***************************************************************************************************

    /**
     * @return com.hryj.common.Result
     * @author 代廷波
     * @description:
     * @param: role_name 角色名
     * @create 2018/06/23 14:02
     **/
    @PostMapping("/role/saveRole")
    Result saveRole(@RequestBody RoleAddRequestVO roleAddRequestVO) throws GlobalException;
    /**
     * @author 代廷波
     * @description: 修改角色名
     * @param: null
     * @return
     * @create 2018/07/23 20:02
     **/
    @PostMapping("/role/updateRole")
     Result updateRole(@RequestBody RoleAddRequestVO vo) throws GlobalException;
    /**
     * @return com.hryj.common.Result
     * @author 代廷波
     * @description: 修改角名状态
     * @param: id 角色id
     * @param: status_value 角色状态:1-正常,0-停用
     * @create 2018/06/23 14:06
     **/
    @PostMapping("/role/updateRoleStatus")
    Result updateRoleStatus(@RequestBody RoleUpdateStatusRequestVO roleUpdateStatusRequestVO) throws GlobalException;

    /**
     * @return com.hryj.common.Result
     * @author 代廷波
     * @description:
     * @param: role_id 角色id
     * @param: resource_ids 资源集合 数组
     * @param: login_token 用户 token
     * @create 2018/06/27 21:23
     **/
    @PostMapping("/role/saveRoleResource")
    Result saveRoleResource(@RequestBody RoleAddResourceRequestVO roleAddResourceRequestVO) throws GlobalException;

    /**
     * @return com.hryj.common.PageResult
     * @author 代廷波
     * @description: 角色列表查询
     * @param: staffRoleDto
     * @create 2018/06/23 14:31
     **/
    @PostMapping("/role/getRoleList")
    Result<PageResponseVO<RoleListParamResponseVO>> getRoleList(@RequestBody RoleListParamRequestVO staffRoleDto) throws GlobalException;

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.staff.role.response.ResourceTreeResponseVO>
     * @author 代廷波
     * @description: 获取资源树
     * @param:
     * @create 2018/06/28 20:46
     **/
    @PostMapping("/role/getResourceTree")
    Result<ListResponseVO<ResourceTreeResponseVO>> getResourceTree() throws GlobalException;

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.staff.role.response.RoleNameListResponseVO>
     * @author 代廷波
     * @description: 角色名列表
     * @param: role_name 角色名
     * @create 2018/06/28 20:57
     **/
    @PostMapping("/role/getRoleNameList")
    Result<ListResponseVO<RoleNameListResponseVO>> getRoleNameList(@RequestBody RoleNameRequestVO roleNameRequestVO) throws GlobalException;

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.staff.role.response.RoleNameListResponseVO>
     * @author 代廷波
     * @description: 根据角色查询对应的资源
     * @param: role_id 角色id
     * @create 2018/06/29 21:08
     **/

    @PostMapping("/role/getRoleIdByResource")
    Result<ListResponseVO<ResourceTreeResponseVO>> getRoleIdByResource(@RequestBody RoleIdRequestVO roleIdRequestVO) throws GlobalException;


    /**
     * @author 王光银
     * @methodName: getRolePermissionTree
     * @methodDesc: 加载角色权限树
     * @description:
     * @param: [vo]
     * @return com.hryj.common.Result<com.hryj.entity.vo.staff.role.response.RolePermissionTreeResponseVO>
     * @create 2018-08-04 15:05
     **/
    @PostMapping("/role/getRolePermissionTree")
    Result<RolePermissionTreeResponseVO> getRolePermissionTree(@RequestBody RoleIdRequestVO vo) throws BizException;

    //***************角色结束***************************************************************************************************


    //***************员工开始***************************************************************************************************

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.staff.user.response.AdminStaffLoginResponseVO>
     * @author 李道云
     * @methodName: loginByPwd
     * @methodDesc: 员工登录
     * @description:
     * @param: [staffLoginRequestVO]
     * @create 2018-07-02 22:37
     **/
    @RequestMapping(value = "/staff/loginByPwd", method = RequestMethod.POST)
    Result<AdminStaffLoginResponseVO> loginByPwd(@RequestBody StaffLoginRequestVO staffLoginRequestVO);

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.staff.user.response.AdminStaffLoginResponseVO>
     * @author 李道云
     * @methodName: updateStaffLoginPwd
     * @methodDesc: 修改登录密码
     * @description:
     * @param: [staffModifyPwdRequestVO]
     * @create 2018-07-02 22:37
     **/
    @RequestMapping(value = "/staff/updateStaffLoginPwd", method = RequestMethod.POST)
    Result<AdminStaffLoginResponseVO> updateStaffLoginPwd(@RequestBody StaffModifyPwdRequestVO staffModifyPwdRequestVO);

    /**
     * @return com.hryj.common.Result
     * @author 李道云
     * @methodName: sendCodeForForgetLoginPwd
     * @methodDesc: 发送验证码(忘记密码)
     * @description:
     * @param: [staffAccountRequestVO]
     * @create 2018-07-05 11:38
     **/
    @RequestMapping(value = "/staff/sendCodeForForgetLoginPwd", method = RequestMethod.POST)
    Result sendCodeForForgetLoginPwd(@RequestBody StaffAccountRequestVO staffAccountRequestVO);

    /**
     * @return com.hryj.common.Result
     * @author 李道云
     * @methodName: updateStaffLoginPwdBySms
     * @methodDesc: 短信验证修改登录密码
     * @description:
     * @param: [staffAccountRequestVO]
     * @create 2018-07-05 11:38
     **/
    @RequestMapping(value = "/staff/updateStaffLoginPwdBySms", method = RequestMethod.POST)
    Result updateStaffLoginPwdBySms(@RequestBody StaffSmsModifyPwdRequestVO staffSmsModifyPwdRequestVO);

    /**
     * @return com.hryj.common.Result
     * @author 代廷波
     * @description: 添加员工
     * @param: vo
     * @create 2018/06/26 9:36
     **/
    @PostMapping("/staff/saveStaff")
    Result<StaffAccountResponseVO> saveStaff(@RequestBody StaffUserInfoRequestVO staffUserInfoRequestVO) throws GlobalException;

    /**
     * @return com.hryj.common.Result
     * @author 代廷波
     * @description: 修改员工
     * @param: vo
     * @create 2018/06/26 9:36
     **/
    @PostMapping("/staff/updateStaff")
    Result updateStaff(@RequestBody StaffUserInfoRequestVO staffUserInfoRequestVO) throws GlobalException;

    /**
     * @return com.hryj.common.Result
     * @author 代廷波
     * @description: 根据员工id查询详情
     * @param: staff_id 员工id
     * @create 2018/06/26 9:41
     **/
    @PostMapping("/staff/getStaffByIdDet")
    Result<StaffResponseVO> getStaffByIdDet(@RequestBody StaffIdRequestVO staffIdRequestVO) throws GlobalException;


    /**
     * @return com.hryj.common.Result
     * @author 代廷波
     * @description: 查询员工列表
     * @param: vo
     * @create 2018/06/26 9:36
     **/
    @PostMapping("/staff/getStaffList")
    Result<PageResponseVO<StaffListResponseVO>> getStaffList(@RequestBody StaffListParamRequestVO staffListParamRequestVO) throws GlobalException;

    /**
     * @return com.hryj.common.Result
     * @author 李道云
     * @methodName: findStoreStaffList
     * @methodDesc: 查询门店的员工列表
     * @description:
     * @param: []
     * @create 2018-07-05 12:36
     **/
    @RequestMapping(value = "/staff/findStoreStaffList", method = RequestMethod.POST)
    Result<ListResponseVO<StaffUserVO>> findStoreStaffList(@RequestBody RequestVO requestVO);

    @RequestMapping(value = "/staff/upload/excel", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Result<StaffUserUploadResponseVO> uploadExcel(@RequestParam("login_token") String login_token, @RequestPart("file") MultipartFile file);



    //***************员工结束***************************************************************************************************


    //***************门店开始***************************************************************************************************

    /**
     * @return com.hryj.common.Result
     * @author 代廷波
     * @description: 添加门店
     * @param: vo
     * @create 2018/06/27 18:44
     **/

    @PostMapping("/store/saveStore")
    Result saveStore(@RequestBody StoreInfoRequestVO storeInfoRequestVO) throws GlobalException;

    /**
     * @return com.hryj.common.Result
     * @author 代廷波
     * @description: 修改门店
     * @param: vo
     * @create 2018/06/27 18:44
     **/

    @PostMapping("/store/updateStore")
    Result updateStore(@RequestBody StoreInfoRequestVO storeInfoRequestVO) throws GlobalException;


    /**
     * @return com.hryj.common.Result
     * @author 代廷波
     * @description: 根据门店id查询详情
     * @param: Store_id 门店id
     * @create 2018/06/27 19:24
     **/
    @PostMapping("/store/getStoreIdByDet")
    Result<StoreInfoResponseVO> getStoreIdByDet(@RequestBody StoreIdRequestVO storeIdRequestVO) throws GlobalException;


    /**
     * @return com.hryj.common.Result
     * @author 代廷波
     * @description:修改门店状态
     * @param: vo
     * @create 2018/06/27 19:53
     **/
    @PostMapping("/store/updateStoreStatus")
    Result updateStoreStatus(@RequestBody StoreUpdateStatusRequestVO storeUpdateStatusRequestVO) throws GlobalException;


    /**
     * @return
     * @author 代廷波
     * @description: 门店列表查询
     * @param: vo
     * @create 2018/06/27 19:39
     **/
    @PostMapping("/store/getStoreList")
    Result<PageResponseVO<StoreListResponseVO>> getStoreList(@RequestBody StoreListParamRequestVO storeListParamRequestVO) throws GlobalException;


    //***************门店结束***************************************************************************************************


    //***************仓库开始***************************************************************************************************

    /**
     * @return com.hryj.common.Result
     * @author 代廷波
     * @description: 添加仓库
     * @param: vo
     * @create 2018/06/27 18:44
     **/

    @PostMapping("/warehouse/saveWarehose")
    Result saveWarehose(@RequestBody WarehouseInfoRequestVO warehouseInfoRequestVO) throws GlobalException;

    /**
     * @return com.hryj.common.Result
     * @author 代廷波
     * @description: 修改仓库
     * @param: vo
     * @create 2018/06/27 18:44
     **/

    @PostMapping("/warehouse/updateWarehose")
    Result updateWarehose(@RequestBody WarehouseInfoRequestVO warehouseInfoRequestVO) throws GlobalException;


    /**
     * @return com.hryj.common.Result
     * @author 代廷波
     * @description: 根据仓库id查询详情
     * @param: warehose_id 仓库id
     * @create 2018/06/27 19:24
     **/
    @PostMapping("/warehouse/getWarehoseByIdDet")
    Result<WarehouseInfoResponseVO> getWarehoseByIdDet(@RequestBody WarehouseIdRequestVO warehouseIdRequestVO) throws GlobalException;


    /**
     * @return com.hryj.common.Result
     * @author 代廷波
     * @description: 修改仓库状态
     * @param: warehose_id 仓库id
     * @param: status_value 仓库状态:1-正常,0-关闭
     * @create 2018/07/04 10:55
     **/
    @PostMapping("/warehouse/updateWarehoseStatus")
    Result updateWarehoseStatus(@RequestBody WarehouseUpdateStatusRequestVO warehouseUpdateStatusRequestVO) throws GlobalException;


    /**
     * @return
     * @author 代廷波
     * @description: 仓库列表查询
     * @param: vo
     * @create 2018/06/27 19:39
     **/
    @PostMapping("/warehouse/getWarehoseList")
    Result<PageResponseVO<WarehouseListResponseVO>> getWarehoseList(@RequestBody WarehuoseParamRequestVO warehuoseParamRequestVO) throws GlobalException;

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.ListResponseVO   <   com.hryj.entity.vo.staff.warehouse.response.WarehouseCityAreaResponseVO>>
     * @author 代廷波
     * @description: 根据省ia获取仓库的配送区域
     * @param: vo
     * @create 2018/07/17 11:52
     **/
    @PostMapping("/warehouse/getWarehoseCityAreaList")
    Result<ListResponseVO<WarehouseCityAreaResponseVO>> getWarehoseCityAreaList(@RequestBody WareHouseCityIdsRequestVO vo) throws GlobalException;


    //***************仓库结束***************************************************************************************************


    //***************地图数据开始***************************************************************************************************
    @PostMapping("/amap/getAmapAround")
    /**
     * @author 代廷波
     * @description: 周边搜索
     * @param: vo
     * @return com.hryj.common.Result
     * @create 2018/07/19 11:22
     **/
    Result getAmapAround(AmapAroundRequestVO vo);

    /**
     * @return
     * @author 代廷波
     * @description: 重庆区域收缩
     * @param: null
     * @create 2018/07/19 11:45
     **/
    @PostMapping("/amap/getAmapAmapDistrict")
    Result getAmapAmapDistrict(@RequestBody AmapDistrictRequestVO vo) throws GlobalException;




    //***************地图数据结束***************************************************************************************************
}
