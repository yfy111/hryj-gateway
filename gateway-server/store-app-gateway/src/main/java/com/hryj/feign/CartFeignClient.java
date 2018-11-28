package com.hryj.feign;

import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.cart.request.CartOperationRequestVO;
import com.hryj.entity.vo.cart.request.ShoppingCartForStoreRequestVO;
import com.hryj.entity.vo.cart.request.ShoppingCartRequestVO;
import com.hryj.entity.vo.cart.response.ShoppingCartResponseVO;
import com.hryj.entity.vo.staff.store.request.StoreStaffRequestVO;
import com.hryj.entity.vo.staff.store.response.StoreStaffResponseVO;
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
     * @return com.hryj.common.Result
     * @author 罗秋涵
     * @methodName: addShoppingCart
     * @methodDesc: 加入用户购物车
     * @description: 购物车商品数量编辑也用此接口
     * @param: [shoppingCartRequestVO, user_id]
     * @create 2018-06-29 18:46
     **/
    @RequestMapping(value = "/storeCart/addShoppingCart", method = RequestMethod.POST)
    Result storeAddShoppingCart(@RequestBody ShoppingCartRequestVO shoppingCartRequestVO);

    /**
     * @author 罗秋涵
     * @description: 修改购物车
     * @param: [shoppingCartRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-17 16:47
     **/
    @RequestMapping(value = "/storeCart/updateShoppingCart", method = RequestMethod.POST)
    Result updateShoppingCart(@RequestBody ShoppingCartRequestVO shoppingCartRequestVO);


    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.cart.response.ShoppingCartResponseVO>
     * @author 李道云
     * @methodName: findShoppingCartList
     * @methodDesc: 查询购物车列表
     * @description:
     * @param: [requestVO]
     * @create 2018-06-29 19:06
     **/
    @RequestMapping(value = "/storeCart/findShoppingCartList", method = RequestMethod.POST)
    Result<ShoppingCartResponseVO> storeFindShoppingCartList(@RequestBody ShoppingCartForStoreRequestVO shoppingCartForStoreRequestVO);


    /**
     * @return com.hryj.common.Result
     * @author 李道云
     * @methodName: deleteShoppingCartItem
     * @methodDesc: 批量删除购物车商品
     * @description: 购物车条目id用逗号拼接, 并返回购物车列表信息
     * @param: [cartItemIds]
     * @create 2018-06-29 19:16
     **/
    @RequestMapping(value = "/storeCart/deleteShoppingCartItem", method = RequestMethod.POST)
    Result<ShoppingCartResponseVO> storeDeleteShoppingCartItem(@RequestBody CartOperationRequestVO cartoPerationRequestVO);


    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.cart.response.ShoppingCartResponseVO>
     * @author 罗秋涵
     * @description: 门店员工在购物车里清除无效商品
     * @param: [user_id]
     * @create 2018-07-03 9:40
     **/
    @RequestMapping(value = "/storeCart/clearInvalidProduct", method = RequestMethod.POST)
    Result<ShoppingCartResponseVO> storeClearInvalidProduct(@RequestBody CartOperationRequestVO cartoPerationRequestVO);


    /**
     * @author 罗秋涵
     * @description: 获取购物车商品数量
     * @param: [shoppingCartForStoreRequestVO]
     * @return com.hryj.common.Result<java.lang.Integer>
     * @create 2018-07-17 13:44
     **/
    @RequestMapping(value = "/storeCart/getCartProductNum", method = RequestMethod.POST)
    Result<Integer> getCartProductNum(@RequestBody ShoppingCartForStoreRequestVO shoppingCartForStoreRequestVO);

    /**
     * 获取员工列表
     * @param staffRequestVO
     * @return
     */
    @RequestMapping(value = "/userOrder/getStoreStaffList", method = RequestMethod.POST)
    Result<ListResponseVO<StoreStaffResponseVO>> getStoreStaffList(@RequestBody StoreStaffRequestVO staffRequestVO);

}
