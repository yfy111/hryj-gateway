package com.hryj.api.staff;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.hryj.cache.LoginCache;
import com.hryj.common.CodeEnum;
import com.hryj.common.Result;
import com.hryj.constant.StaffConstants;
import com.hryj.entity.bo.staff.user.StaffUserInfo;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.PageResponseVO;
import com.hryj.entity.vo.RequestVO;
import com.hryj.entity.vo.staff.user.StaffUserVO;
import com.hryj.entity.vo.staff.user.request.*;
import com.hryj.entity.vo.staff.user.response.*;
import com.hryj.exception.GlobalException;
import com.hryj.feign.StaffFeignClient;
import com.hryj.utils.ExcelUtil;
import com.hryj.utils.WebUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author 李道云
 * @className: StaffController
 * @description: 员工模块
 * @create 2018/6/26 17:34
 **/
@Api(value = "/staff", tags = "员工模块-v1.2")
@Slf4j
@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private HttpServletRequest request;

  /*  @Autowired
    private HttpServletResponse response;*/


    @Autowired
    private StaffFeignClient staffFeignClient;

    /**
     * @return com.hryj.common.Result
     * @author 李道云
     * @methodName: logout
     * @methodDesc:
     * @description:
     * @param: []
     * @create 2018-06-28 22:10
     **/
    @ApiOperation(value = "注销登录")
    @PostMapping("/logout")
    public Result logout() {
        RequestVO requestVO = WebUtil.getRequestVO(request, null);
        log.info("注销登录：requestVO======" + JSON.toJSONString(requestVO));
        String login_token = requestVO.getLogin_token();
        if (LoginCache.getStaffAdminLoginVO(login_token) != null) {
            LoginCache.clearStaffAdminLoginVO(requestVO.getLogin_token());
        }
        return new Result(CodeEnum.SUCCESS);
    }

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.staff.user.response.AppStaffLoginResponseVO>
     * @author 李道云
     * @methodName: updateStaffLoginPwd
     * @methodDesc: 修改登录密码
     * @description:
     * @param: [staffModifyPwdRequestVO]
     * @create 2018-07-02 22:42
     **/
    @ApiOperation(value = "修改登录密码")
    @PostMapping("/updateStaffLoginPwd")
    public Result<AdminStaffLoginResponseVO> updateStaffLoginPwd(@RequestBody StaffModifyPwdRequestVO staffModifyPwdRequestVO) {
        WebUtil.getRequestVO(request, staffModifyPwdRequestVO);
        log.info("修改密码：updateStaffLoginPwd======" + JSON.toJSONString(staffModifyPwdRequestVO));
        return staffFeignClient.updateStaffLoginPwd(staffModifyPwdRequestVO);
    }

    /**
     * @return com.hryj.common.Result
     * @author 代廷波
     * @description: 添加员工
     * @param: vo
     * @create 2018/06/26 9:36
     **/
    @ApiOperation(value = "添加员工- v1.2")
    @PostMapping("/saveStaff")
    public Result<StaffAccountResponseVO> saveStaff(@RequestBody StaffUserInfoRequestVO staffUserInfoRequestVO) throws GlobalException {
        log.info("添加员工：saveStaff======" + JSON.toJSONString(staffUserInfoRequestVO));
        WebUtil.getRequestVO(request, staffUserInfoRequestVO);
        Result<StaffAccountResponseVO> result = staffFeignClient.saveStaff(staffUserInfoRequestVO);
        log.info("添加员工：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result
     * @author 代廷波
     * @description: 修改员工
     * @param: vo
     * @create 2018/06/26 9:36
     **/
    @ApiOperation(value = "修改员工 - v1.2")
    @PostMapping("/updateStaff")
    public Result updateStaff(@RequestBody StaffUserInfoRequestVO staffUserInfoRequestVO) throws GlobalException {
        log.info("修改员工：updateStaff======" + JSON.toJSONString(staffUserInfoRequestVO));
        WebUtil.getRequestVO(request, staffUserInfoRequestVO);
        Result result = staffFeignClient.updateStaff(staffUserInfoRequestVO);
        log.info("修改员工：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result
     * @author 代廷波
     * @description: 根据员工id查询详情
     * @param: staff_id 员工id
     * @create 2018/06/26 9:41
     **/
    @ApiOperation(value = " 根据员工id查询详情")
    @PostMapping("/getStaffByIdDet")
    public Result<StaffResponseVO> getStaffByIdDet(@RequestBody StaffIdRequestVO staffIdRequestVO) throws GlobalException {
        log.info("根据员工id查询详情：getStaffByIdDet======" + JSON.toJSONString(staffIdRequestVO));
        Result<StaffResponseVO> result = staffFeignClient.getStaffByIdDet(staffIdRequestVO);
        log.info("根据员工id查询详情：result======" + JSON.toJSONString(result));
        return result;
    }


    /**
     * @return com.hryj.common.Result
     * @author 代廷波
     * @description: 查询员工列表
     * @param: vo
     * @create 2018/06/26 9:36
     **/
    @ApiOperation(value = "查询员工列表")
    @PostMapping("/getStaffList")
    public Result<PageResponseVO<StaffListResponseVO>> getStaffList(@RequestBody StaffListParamRequestVO vo) throws GlobalException {
        log.info("查询员工列表：getStaffList======" + JSON.toJSONString(vo));
        Result<PageResponseVO<StaffListResponseVO>> result = staffFeignClient.getStaffList(vo);
        log.info("查询员工列表：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result
     * @author 李道云
     * @methodName: findStoreStaffList
     * @methodDesc: 查询门店的员工列表
     * @description:
     * @param: []
     * @create 2018-07-05 12:36
     **/
    @ApiOperation(value = "查询门店的员工列表", notes = "查询门店的员工列表")
    @PostMapping("/findStoreStaffList")
    public Result<ListResponseVO<StaffUserVO>> findStoreStaffList() {
        RequestVO requestVO = WebUtil.getRequestVO(request, null);
        log.info("查询门店的员工列表：requestVO======" + JSON.toJSONString(requestVO));
        Result<ListResponseVO<StaffUserVO>> result = staffFeignClient.findStoreStaffList(requestVO);
        log.info("查询门店的员工列表：result======" + JSON.toJSONString(result));
        return result;
    }

    @PostMapping("/upload/excel")
    @ApiOperation(value = "excel导入员工 v-1.2", notes = "excel导入员工 v-1.2")
    public void importExcel(HttpServletResponse response, @RequestParam("login_token") String login_token, @RequestParam(name = "file") MultipartFile file) throws IOException {
        //判断token
        if (StrUtil.isEmpty(login_token)){
            responseErrerMsg(response, CodeEnum.FAIL_PARAMCHECK.getMsg());

        }else {
            log.info("excel导入员工login_token:{},file名称:{},file大小:{}",login_token,file.getOriginalFilename(),file.getSize());
            //刷新token过期时间
            LoginCache.expireStaffAdminLoginTime(login_token);
            Result<StaffUserUploadResponseVO> result = staffFeignClient.uploadExcel(login_token, file);

            log.info("excel导入员工响应信息：requestVO======" + JSON.toJSONString(result));
            if (CodeEnum.FAIL_PARAMCHECK.getCode() == result.getCode()){
                responseErrerMsg(response, result.getMsg());
            }

            if (null !=result.getData() && CollectionUtil.isNotEmpty(result.getData().getErrer_list())) {
                responseErrerExcel(response,file.getInputStream(), result.getData().getErrer_list(), file.getOriginalFilename());
            }

            if (null !=result.getData() && CollectionUtil.isNotEmpty(result.getData().getSuccess_List())) {
                responseSuccessExcel(response, result.getData().getSuccess_List(), file.getOriginalFilename());
            }
        }
    }
    private void responseErrerMsg(HttpServletResponse response, String msg) throws IOException {

        // 根据版本选择创建Workbook的方式
        Workbook workbook = new XSSFWorkbook();;
        // 获取模板的工作薄
        Sheet sheet = workbook.createSheet();
        short rowHeight = 300;
        short width =19;
        sheet.setColumnWidth(0, 35*256);//第一列宽度
        Row row = null;
        Cell cell = null;

        // 单元格删除样式
        CellStyle errorDelStyle = workbook.createCellStyle();
        errorDelStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);    //设置填充方案
        errorDelStyle.setFillForegroundColor(IndexedColors.RED.getIndex());  //设置填充颜色

        // 设置表头
        row = sheet.createRow(0);
        row.setHeight(rowHeight);
        cell = row.createCell(0);
        cell.setCellValue("失败原因");

        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellStyle(errorDelStyle);
        cell.setCellValue(msg);
        ExcelUtil.excelExport(response, workbook, "失败原因" + ".xlsx");

    }

    private void responseSuccessExcel(HttpServletResponse response, List<StaffUserInfo> success_list, String fileName) throws IOException {


        // 根据版本选择创建Workbook的方式
        Workbook workbook = null;
        // 根据文件名判断文件是2003版本还是2007版本
        if (ExcelUtil.isExcel2007(fileName)) {
            workbook = new XSSFWorkbook();
        }else {
            workbook = new HSSFWorkbook();
        }
        // 获取模板的工作薄
        Sheet sheet = workbook.createSheet();//getSheetAt(0);
        sheet.setColumnWidth(0, 20*256);//第1列宽度
        sheet.setColumnWidth(1, 40*256);//第2列宽度
        sheet.setColumnWidth(2, 30*256);//第3列宽度

        short rowHeight = 300;
        Row row = null;
        Cell cell = null;

        // 设置表头
        row = sheet.createRow(0);
        row.setHeight(rowHeight);
        cell = row.createCell(0);
        cell.setCellValue(StaffConstants.STAFF_EXCEL_EXPORT_TEMPLET[0]);
        cell = row.createCell(1);
        cell.setCellValue(StaffConstants.STAFF_EXCEL_EXPORT_TEMPLET[1]);
        cell = row.createCell(2);
        cell.setCellValue(StaffConstants.STAFF_EXCEL_EXPORT_TEMPLET[2]);

        StaffUserInfo userInfo = null;
        for (int j = 0; j < success_list.size(); j++) {
            userInfo = success_list.get(j);
            row = sheet.createRow(j + 1);
            row.setHeight(rowHeight);
            cell = row.createCell(0);
            cell.setCellValue(userInfo.getStaff_name());
            // 身份证
            cell = row.createCell(1);
            cell.setCellValue(userInfo.getId_card());
            // 账号
            cell = row.createCell(2);
            cell.setCellValue(userInfo.getStaff_account());
        }
        ExcelUtil.excelExport(response, workbook, "导入成功_" + fileName);

    }

    private void responseErrerExcel(HttpServletResponse response, InputStream inputStream, List<StaffUserExcelVO> errer_list, String fileName) throws IOException {

        //根据版本选择创建Workbook的方式
        Workbook workbook = null;
        //根据文件名判断文件是2003版本还是2007版本
        if (ExcelUtil.isExcel2007(fileName)) {
            workbook = new XSSFWorkbook(inputStream);
        } else {
            workbook = new HSSFWorkbook(inputStream);
        }
        //获取模板的工作薄
        Sheet sheetAt = workbook.getSheetAt(0);
        Row row = sheetAt.getRow(0);
        short rowHeight = row.getHeight();
        Row row1 = null;
        Cell cell = null;


        // 单元格样式
        CellStyle errorStyle = workbook.createCellStyle();
        errorStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);    //设置填充方案
        errorStyle.setFillForegroundColor(IndexedColors.RED.getIndex());  //设置填充颜色

        // 单元格删除样式
        CellStyle errorDelStyle = workbook.createCellStyle();
        errorDelStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);    //设置填充方案
        errorDelStyle.setFillForegroundColor(IndexedColors.RED.getIndex());  //设置填充颜色

        // 单元格删除样式
        CellStyle backDelStyle = workbook.createCellStyle();
        backDelStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);    //设置填充方案
        backDelStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());  //设置填充颜色



        Font ztFont = workbook.createFont();
        ztFont.setStrikeout(true);
        errorDelStyle.setFont(ztFont);


        StaffUserExcelVO staff = null;
        for (int j = 0; j < errer_list.size(); j++) {
            staff = errer_list.get(j);

            row1 = sheetAt.createRow(j + 1);
            row1.setHeight(rowHeight);
            cell = row1.createCell(0);
            if (staff.getStaff_name_validation()) {
                cell.setCellStyle(errorStyle);
            }
            cell.setCellValue(staff.getStaff_name());
            // 联系电话
            cell = row1.createCell(1);
            if (staff.getPhone_num_validation()) {
                if (staff.getPhone_num_only()) {
                    cell.setCellStyle(errorDelStyle);
                } else {
                    cell.setCellStyle(errorStyle);
                }

            }
            cell.setCellValue(staff.getPhone_num());

            // 性别
            cell = row1.createCell(2);
            if (staff.getSex_validation()) {
                cell.setCellStyle(errorStyle);
            }
            cell.setCellValue(staff.getSex());

            // 身份证
            cell = row1.createCell(3);
            if (staff.getId_card_validation()) {
                if (staff.getId_card_only()) {
                    cell.setCellStyle(errorDelStyle);
                } else {
                    cell.setCellStyle(errorStyle);
                }
            }
            cell.setCellValue(staff.getId_card());

            // 学历
            cell = row1.createCell(4);
            if (staff.getEducation_validation()) {
                cell.setCellStyle(errorStyle);
            }
            cell.setCellValue(staff.getEducation());

            // 邮箱
            cell = row1.createCell(5);
            if (staff.getEmail_validation()) {
                cell.setCellStyle(errorStyle);
            }
            cell.setCellValue(staff.getEmail());

            // 家庭地址
            cell = row1.createCell(6);
            if (staff.getHome_address_validation()) {
                cell.setCellStyle(errorStyle);
            }
            cell.setCellValue(staff.getHome_address());

            // 紧急联系人
            cell = row1.createCell(7);
            if (staff.getContact_name_validation()) {
                cell.setCellStyle(errorStyle);
            }
            cell.setCellValue(staff.getContact_name());

            // 紧急联系人电话
            cell = row1.createCell(8);
            if (staff.getContact_tel_validation()) {
                cell.setCellStyle(errorStyle);
            }
            cell.setCellValue(staff.getContact_tel());

            // 角色
            cell = row1.createCell(9);
            if (staff.getRole_name_validation()) {
                cell.setCellStyle(errorStyle);
            }else{
                if (staff.getRole_name_db_flag()){
                    cell.setCellStyle(backDelStyle);
                }
            }

            cell.setCellValue(staff.getRole_name());

        }
        ExcelUtil.excelExport(response, workbook, "导入失败_" + fileName);

    }
}
