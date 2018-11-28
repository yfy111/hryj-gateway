package com.hryj.feign;

import com.hryj.common.Result;
import com.hryj.entity.vo.RequestVO;
import com.hryj.entity.vo.sys.response.AppVersionResponseVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 李道云
 * @className: SysFeignClient
 * @description: 系统服务feign接口
 * @create 2018/6/23 15:11
 **/
@FeignClient(name = "sys-server")
public interface SysFeignClient {

    /**
     * @author 李道云
     * @methodName: getAppVersionInfo
     * @methodDesc: 获取应用版本信息
     * @description:
     * @param: [requestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.sys.response.AppVersionResponseVO>
     * @create 2018-07-02 12:15
     **/
    @RequestMapping(value = "/sys/getAppVersionInfo", method = RequestMethod.POST)
    Result<AppVersionResponseVO> getAppVersionInfo(@RequestBody RequestVO requestVO);
}
