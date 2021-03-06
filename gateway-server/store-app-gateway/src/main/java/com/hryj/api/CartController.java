package com.hryj.api;

import com.alibaba.fastjson.JSON;
import com.hryj.common.Result;
import com.hryj.entity.vo.cart.request.CartOperationRequestVO;
import com.hryj.entity.vo.cart.request.ShoppingCartForStoreRequestVO;
import com.hryj.entity.vo.cart.request.ShoppingCartRequestVO;
import com.hryj.entity.vo.cart.response.ShoppingCartResponseVO;
import com.hryj.feign.CartFeignClient;
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
 * @author 罗秋涵
 * @className: CartController
 * @description: 购物车模块
 * @create 2018/6/30 14:48
 **/
@Api(value="/cart", tags = "购物车模块")
@Slf4j
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private CartFeignClient cartFeignClient;

    /**
     * @author 罗秋涵
     * @methodName: addShoppingCart
     * @methodDesc: 加入用户购物车
     * @description:
     * @param: [shoppingCartRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-29 18:46
     **/
    @ApiOperation(value="加入用户购物车",notes = "加入用户购物车")
    @PostMapping("/addShoppingCart")
    public Result addShoppingCart(@RequestBody ShoppingCartRequestVO shoppingCartRequestVO){
        WebUtil.getRequestVO(request,shoppingCartRequestVO);
        log.info("加入用户购物车：shoppingCartRequestVO======" + JSON.toJSONString(shoppingCartRequestVO));
        Result result = cartFeignClient.storeAddShoppingCart(shoppingCartRequestVO);
        log.info("加入用户购物车：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 罗秋涵
     * @description:
     * @param: [shoppingCartRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-17 16:31
     **/
    @ApiOperation(value = "修改购物车商品数量", notes = "修改购物车商品数量")
    @PostMapping(value = "/updateShoppingCart")
    public Result updateShoppingCart(@RequestBody ShoppingCartRequestVO shoppingCartRequestVO){
        WebUtil.getRequestVO(request,shoppingCartRequestVO);
        log.info("加入用户购物车：shoppingCartRequestVO======" + JSON.toJSONString(shoppingCartRequestVO));
        Result result = cartFeignClient.updateShoppingCart(shoppingCartRequestVO);
        log.info("加入用户购物车：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 罗秋涵
     * @methodName: findShoppingCartList
     * @methodDesc: 查询用户购物车列表
     * @description:
     * @param: [requestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.cart.response.ShoppingCartResponseVO>
     * @create 2018-06-29 19:06
     **/
    @ApiOperation(value="查询用户购物车列表 ,v1.2 修改",notes = "查询用户购物车列表")
    @PostMapping("/findShoppingCartList")
    public Result<ShoppingCartResponseVO> findShoppingCartList(@RequestBody ShoppingCartForStoreRequestVO shoppingCartForStoreRequestVO){
        WebUtil.getRequestVO(request,shoppingCartForStoreRequestVO);
        log.info("查询用户购物车列表：shoppingCartForStoreRequestVO======" + JSON.toJSONString(shoppingCartForStoreRequestVO));
        Result<ShoppingCartResponseVO> result = cartFeignClient.storeFindShoppingCartList(shoppingCartForStoreRequestVO);
        log.info("查询用户购物车列表：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 罗秋涵
     * @methodName: deleteShoppingCartItem
     * @methodDesc: 批量删除用户购物车商品
     * @description: 购物车条目id用逗号拼接,并返回购物车列表信息
     * @param: [cartItemIds]
     * @return com.hryj.common.Result
     * @create 2018-06-29 19:16
     **/
    @ApiOperation(value="批量删除用户购物车商品",notes = "购物车条目id用逗号拼接,并返回购物车列表信息")
    @PostMapping("/deleteShoppingCartItem")
    public Result<ShoppingCartResponseVO> deleteShoppingCartItem(@RequestBody CartOperationRequestVO cartoPerationRequestVO){
        WebUtil.getRequestVO(request,cartoPerationRequestVO);
        log.info("批量删除用户购物车商品：cartoPerationRequestVO======" + JSON.toJSONString(cartoPerationRequestVO));
        Result<ShoppingCartResponseVO> result = cartFeignClient.storeDeleteShoppingCartItem(cartoPerationRequestVO);
        log.info("批量删除用户购物车商品：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 罗秋涵
     * @description: 清除无效商品
     * @param: [cartoPerationRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.cart.response.ShoppingCartResponseVO>
     * @create 2018-07-16 11:18
     **/
    @ApiOperation(value = "清除无效商品", notes = "清除无效商品")
    @PostMapping("/clearInvalidProduct")
    public Result<ShoppingCartResponseVO> clearInvalidProduct(@RequestBody  CartOperationRequestVO cartoPerationRequestVO) {
        WebUtil.getRequestVO(request,cartoPerationRequestVO);
        log.info("清除无效商品：cartoPerationRequestVO======" + JSON.toJSONString(cartoPerationRequestVO));
        Result<ShoppingCartResponseVO> result = cartFeignClient.storeClearInvalidProduct(cartoPerationRequestVO);
        log.info("清除无效商品：result======" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @author 罗秋涵
     * @description: 查询购物车商品数量
     * @param: [shoppingCartForStoreRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.cart.response.ShoppingCartResponseVO>
     * @create 2018-07-17 11:21
     **/
    @PostMapping("/getCartProductNum")
    @ApiOperation(value = "查询购物车商品数量", notes = "查询购物车商品数量")
    public Result<Integer> getCartProductNum(@RequestBody(required = false) ShoppingCartForStoreRequestVO shoppingCartForStoreRequestVO) {
        if(shoppingCartForStoreRequestVO == null){
            shoppingCartForStoreRequestVO = new ShoppingCartForStoreRequestVO();
        }
        WebUtil.getRequestVO(request,shoppingCartForStoreRequestVO);
        log.info("查询购物车商品数量：shoppingCartForStoreRequestVO======" + JSON.toJSONString(shoppingCartForStoreRequestVO));
        Result<Integer> result = cartFeignClient.getCartProductNum(shoppingCartForStoreRequestVO);
        log.info("查询购物车商品数量：result======" + JSON.toJSONString(result));
        return  result;
    }
}
