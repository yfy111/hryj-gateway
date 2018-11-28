package com.hryj.feign;

import com.hryj.common.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
     * @description: 
     * @param: 刷新code表的缓存数据
     * @return 
     * @create 2018-06-23 15:18
     **/
    @RequestMapping(value = "/cache/flushCodeCache", method = RequestMethod.POST)
    Result flushCodeCache();

    /**
     * @author 李道云
     * @description:
     * @param: 刷新城市的缓存数据
     * @return
     * @create 2018-06-23 15:18
     **/
    @RequestMapping(value = "/cache/flushCityCache", method = RequestMethod.POST)
    Result flushCityCache();

    /**
     * @author 李道云
     * @description: 查看缓存数据
     * @param:
     * @return
     * @create 2018-06-23 15:19
     **/
    @RequestMapping(value = "/cache/getCache", method = RequestMethod.POST)
    Result getCache(@RequestParam("group_name")String group_name, @RequestParam("key_name")String key_name);

    /**
     * @author 李道云
     * @description: 设置缓存数据
     * @param:
     * @return
     * @create 2018-06-23 15:20
     **/
    @RequestMapping(value = "/cache/setCache", method = RequestMethod.POST)
    Result setCache(@RequestParam("group_name")String group_name, @RequestParam("key_name")String key_name, @RequestParam("value")String value, @RequestParam("exT")Integer exT);

    /**
     * 刷新商品品牌缓存
     * @return
     */
    @RequestMapping(value = "/cache/refreshProductBrandCache", method = RequestMethod.POST)
    Result refreshProductBrandCache();

    /**
     * 刷新商品产地缓存
     * @return
     */
    @RequestMapping(value = "/cache/refreshProductGeoCache", method = RequestMethod.POST)
    Result refreshProductGeoCache();
}
