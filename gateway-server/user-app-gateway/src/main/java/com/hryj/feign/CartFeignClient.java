package com.hryj.feign;

import com.hryj.common.Result;
import com.hryj.entity.vo.cart.request.CartOperationRequestVO;
import com.hryj.entity.vo.cart.request.ShoppingCartForStoreRequestVO;
import com.hryj.entity.vo.cart.request.ShoppingCartRequestVO;
import com.hryj.entity.vo.cart.response.ShoppingCartResponseVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 罗秋涵
 * @className: CartFeignClient
 * @description: 购物车服务feign接口
 * @create 2018/6/26 17:19
 **/
@FeignClient(name = "order-server")
public interface CartFeignClient {

    /**
     * @author 罗秋涵
     * @description: 加入购物车
     * @param: [shoppingCartRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-16 10:18
     **/
    @RequestMapping(value = "/userCart/addShoppingCart", method = RequestMethod.POST)
    Result addShoppingCart(@RequestBody ShoppingCartRequestVO shoppingCartRequestVO);

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.cart.response.ShoppingCartResponseVO>
     * @author 罗秋涵
     * @methodName: findShoppingCartList
     * @methodDesc: 查询购物车列表
     * @description:
     * @param: [requestVO]
     * @create 2018-06-29 19:06
     **/
    @RequestMapping(value = "/userCart/findShoppingCartList", method = RequestMethod.POST)
    Result<ShoppingCartResponseVO> findShoppingCartList(@RequestBody ShoppingCartForStoreRequestVO shoppingCartForStoreRequestVO);

    /**
     * @return com.hryj.common.Result
     * @author 罗秋涵
     * @methodName: deleteShoppingCartItem
     * @methodDesc: 批量删除购物车商品
     * @description: 购物车条目id用逗号拼接, 并返回购物车列表信息
     * @param: [cartItemIds]
     * @create 2018-06-29 19:16
     **/
    @RequestMapping(value = "/userCart/deleteShoppingCartItem", method = RequestMethod.POST)
    Result<ShoppingCartResponseVO> deleteShoppingCartItem(@RequestBody CartOperationRequestVO cartoPerationRequestVO);

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.cart.response.ShoppingCartResponseVO>
     * @author 罗秋涵
     * @description: 用户在购物车里清除无效商品
     * @param: [requestVO]
     * @create 2018-07-02 22:21
     **/
    @RequestMapping(value = "/userCart/clearInvalidProduct", method = RequestMethod.POST)
    Result<ShoppingCartResponseVO> clearInvalidProduct(@RequestBody  CartOperationRequestVO cartoPerationRequestVO);

    /**
     * @author 罗秋涵
     * @description: 获取购物车商品数量
     * @param: [shoppingCartForStoreRequestVO]
     * @return com.hryj.common.Result<java.lang.Integer>
     * @create 2018-07-17 13:44
     **/
    @RequestMapping(value = "/userCart/getCartProductNum", method = RequestMethod.POST)
    Result<Integer> getCartProductNum(@RequestBody ShoppingCartForStoreRequestVO shoppingCartForStoreRequestVO);

    /**
     * @author 罗秋涵
     * @description: 修改购物车
     * @param: [shoppingCartRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-17 16:47
     **/
    @RequestMapping(value = "/userCart/updateShoppingCart", method = RequestMethod.POST)
    Result updateShoppingCart(@RequestBody ShoppingCartRequestVO shoppingCartRequestVO);
}
