package com.hryj.api;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.hryj.cache.CacheGroup;
import com.hryj.cache.CodeCache;
import com.hryj.cache.RedisService;
import com.hryj.common.CodeEnum;
import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.sys.request.CityAreaListRequestVO;
import com.hryj.entity.vo.sys.request.CodeListRequestVO;
import com.hryj.entity.vo.sys.response.CityAreaVO;
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
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 李道云
 * @className: SysContoller
 * @description: 系统服务
 * @create 2018-06-12 16:30
 **/
@Api(value="/sys", tags = "系统模块")
@Slf4j
@RestController
@RequestMapping("/sys")
public class SysContoller {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private AliYunOSS aliYunOSS;

    @Autowired
    private RedisService redisService;

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
     * @methodName: getCityListByPid
     * @methodDesc: 根据父级城市id获取城市列表
     * @description:
     * @param: [cityAreaListRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-28 15:50
     **/
    @ApiOperation(value="根据父级城市id获取城市列表", notes = "获取一级所有省份，pid传100000")
    @PostMapping("/getCityListByPid")
    public Result<ListResponseVO<CityAreaVO>> getCityListByPid(@RequestBody CityAreaListRequestVO cityAreaListRequestVO){
        if(cityAreaListRequestVO ==null){
            cityAreaListRequestVO = new CityAreaListRequestVO();
            cityAreaListRequestVO.setPid(100000L);
        }
        WebUtil.getRequestVO(request,cityAreaListRequestVO);
        log.info("根据父级城市id获取城市列表：cityAreaListRequestVO======" + JSON.toJSONString(cityAreaListRequestVO));
        if(cityAreaListRequestVO.getPid() ==null){
            return new Result(CodeEnum.FAIL_PARAMCHECK, "父级id不能为空");
        }
        String value = redisService.get(CacheGroup.CITY_CHAILD.getValue(),"S"+cityAreaListRequestVO.getPid());
        List<CityAreaVO> cityList = null;
        if(StrUtil.isEmpty(value)){
            cityList = new ArrayList<>();
        }else{
            cityList = JSON.parseArray(value, CityAreaVO.class);
        }
        Result<ListResponseVO<CityAreaVO>> result = new Result(CodeEnum.SUCCESS, new ListResponseVO<>(cityList));
        log.info("根据父级城市id获取城市列表：result======" + JSON.toJSONString(result));
        return result;
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

    /**
     * @author 李道云
     * @methodName: uploadBatchFile
     * @methodDesc: 上传批量文件
     * @description:
     * @param: [file]
     * @return com.hryj.common.Result
     * @create 2018-07-04 15:49
     **/
    @ApiOperation(value="上传批量文件", notes = "上传批量文件, 返回URL列表")
    @ApiImplicitParam(name="file",value="文件",required = true, paramType = "query", dataType = "File")
    @PostMapping("/uploadBatchFile")
    public Result uploadBatchFile(@RequestParam(name = "file") MultipartFile[] file) throws IOException {
        if(file ==null || file.length ==0){
            return new Result(CodeEnum.FAIL_PARAMCHECK,"上传的文件不能为空");
        }
        List<String> fileList = new ArrayList<>();
        for (int i=0; i<file.length; i++){
            MultipartFile f = file[i];
            String file_path = aliYunOSS.uploadFile(f.getOriginalFilename(),f.getInputStream());
            fileList.add(file_path);
        }
        return new Result(CodeEnum.SUCCESS, fileList);
    }

    /**
     * @author 李道云
     * @description: 刷新code表的缓存数据
     * @param: []
     * @return com.hryj.common.Result
     * @create 2018-06-23 15:17
     **/
    @ApiIgnore
    @PostMapping("/flushCodeCache")
    public Result flushCodeCache(){
        return sysFeignClient.flushCodeCache();
    }

    /**
     * @author 李道云
     * @description: 刷新城市的缓存数据
     * @param: []
     * @return com.hryj.common.Result
     * @create 2018-06-23 15:17
     **/
    @ApiIgnore
    @PostMapping("/flushCityCache")
    public Result flushCityCache(){
        return sysFeignClient.flushCityCache();
    }


    /**
     * @author 李道云
     * @description: 查看缓存数据
     * @param: [group_name, key_name]
     * @return com.hryj.common.Result
     * @create 2018-06-23 11:10
     **/
    @ApiIgnore
    @PostMapping("/getCache")
    public Result getCache(String group_name, String key_name){
        return sysFeignClient.getCache(group_name,key_name);
    }

    /**
     * @author 李道云
     * @description: 设置缓存数据
     * @param: [group_name, key_name, value, exT]
     * @return com.hryj.common.Result
     * @create 2018-06-23 11:14
     **/
    @ApiIgnore
    @PostMapping("/setCache")
    public Result setCache(String group_name, String key_name, String value, Integer exT){
        return sysFeignClient.setCache(group_name,key_name,value,exT);
    }

    /**
     * @author 王光银
     * @methodName: refreshProductBrandCache
     * @methodDesc: 刷新商品品牌缓存
     * @description:
     * @param: []
     * @return com.hryj.common.Result
     * @create 2018-10-08 10:19
     **/
    @ApiIgnore
    @PostMapping("/refreshProductBrandCache")
    public Result refreshProductBrandCache(){
        return sysFeignClient.refreshProductBrandCache();
    }

    /**
     * @author 王光银
     * @methodName: refreshProductGeoCache
     * @methodDesc: 刷新商品产地缓存
     * @description:
     * @param: []
     * @return com.hryj.common.Result
     * @create 2018-10-08 10:20
     **/
    @ApiIgnore
    @PostMapping("/refreshProductGeoCache")
    public Result refreshProductGeoCache(){
        return sysFeignClient.refreshProductGeoCache();
    }

}
