package com.hryj.api;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.hryj.cache.CodeCache;
import com.hryj.common.CodeEnum;
import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.RequestVO;
import com.hryj.entity.vo.sys.request.CodeListRequestVO;
import com.hryj.entity.vo.sys.response.AppVersionResponseVO;
import com.hryj.entity.vo.sys.response.CodeInfoVO;
import com.hryj.feign.SysFeignClient;
import com.hryj.file.AliYunOSS;
import com.hryj.utils.WebUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 李道云
 * @className: SysContoller
 * @description: 系统模块
 * @create 2018-06-12 16:30
 **/
@Api(value="/sys", tags = "系统模块")
@Slf4j
@RestController
@RequestMapping(value = "/sys")
public class SysContoller {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private AliYunOSS aliYunOSS;

    @Autowired
    private SysFeignClient sysFeignClient;

    /**
     * @author 李道云
     * @methodName: getCodeList
     * @methodDesc: 获取代码列表
     * @description:
     * @param: [codeListRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-28 10:31
     **/
    @ApiOperation(value="获取代码列表", notes="代码类别见字典表,或者直接咨询相关开发人员")
    @PostMapping("/getCodeList")
    public Result<ListResponseVO<CodeInfoVO>> getCodeList(@RequestBody CodeListRequestVO codeListRequestVO){
        if(codeListRequestVO ==null || StrUtil.isEmpty(codeListRequestVO.getCode_type())){
            return new Result(CodeEnum.FAIL_PARAMCHECK, "字典类别不能为空");
        }
        WebUtil.getRequestVO(request,codeListRequestVO);
        log.info("获取代码列表：codeListRequestVO======" + JSON.toJSONString(codeListRequestVO));
        List<CodeInfoVO> codeList =  CodeCache.getCodeList(codeListRequestVO.getCode_type());
        Result<ListResponseVO<CodeInfoVO>> result = new Result(CodeEnum.SUCCESS, new ListResponseVO<>(codeList));
        log.info("获取代码列表：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 李道云
     * @methodName: getAppVersionInfo
     * @methodDesc: 获取应用版本信息
     * @description:
     * @param: []
     * @return com.hryj.common.Result<com.hryj.entity.vo.sys.response.AppVersionResponseVO>
     * @create 2018-06-27 20:43
     **/
    @ApiOperation(value="获取应用版本信息",notes = "获取应用版本信息")
    @PostMapping("/getAppVersionInfo")
    public Result<AppVersionResponseVO> getAppVersionInfo(){
        RequestVO requestVO = WebUtil.getRequestVO(request,null);
        log.info("获取应用版本信息：requestVO======" + JSON.toJSONString(requestVO));
        return sysFeignClient.getAppVersionInfo(requestVO);
    }

    /**
     * @author 李道云
     * @methodName: uploadFile
     * @methodDesc: 上传文件
     * @description:
     * @param: [file]
     * @return com.hryj.common.Result
     * @create 2018-07-04 15:49
     **/
    @ApiOperation(value="上传文件", notes = "上传文件, url地址从data里取,file_path")
    @ApiImplicitParam(name="file",value="文件",required = true, paramType = "query", dataType = "File")
    @PostMapping("/uploadFile")
    public Result uploadFile(@RequestParam(name = "file") MultipartFile file) throws IOException {
        if(file ==null || file.getSize()==0){
            return new Result(CodeEnum.FAIL_PARAMCHECK,"上传的文件不能为空");
        }
        String file_path = aliYunOSS.uploadFile(file.getOriginalFilename(),file.getInputStream());
        Map map = new HashMap();
        map.put("file_path", file_path);
        return new Result(CodeEnum.SUCCESS, map);
    }
}
