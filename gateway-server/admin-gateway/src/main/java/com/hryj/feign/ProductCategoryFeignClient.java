package com.hryj.feign;

import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.product.category.request.*;
import com.hryj.entity.vo.product.category.response.ProdCateAttrResponseVO;
import com.hryj.entity.vo.product.category.response.ProdCateTreeResponseItemVO;
import com.hryj.entity.vo.product.category.response.ProductCategoryResponseVO;
import com.hryj.entity.vo.product.partyprod.request.IdRequestVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 李道云
 * @className: OrderFeignClient
 * @description: 订单服务feign接口
 * @create 2018/6/26 17:19
 **/
@FeignClient(name = "product-server")
public interface ProductCategoryFeignClient {

    /**
     * @author 王光银
     * @methodName: getOneProductCategory
     * @methodDesc: 根据分类ID返回一个商品分类数据，包含属性数据
     * @description: 返回对象中的 sub_list 节点属性永远不会有值
     * @param: [prodCateIdRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.product.category.response.ProductCategoryResponseVO>
     * @create 2018-07-12 10:12
     **/
    @PostMapping("/productCategoryMgr/getOneProductCategory")
    Result<ProductCategoryResponseVO> getOneProductCategory(@RequestBody ProdCateIdRequestVO prodCateIdRequestVO);

    /**
     * @author 王光银
     * @methodName: findProdCateTree
     * @methodDesc: 返回商品分类树结构
     * @description: category_name可以为空，不为空时作前后模糊匹配, include_attr 参数不必传,传了也没用
     * @param: [productCategoryFindRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.ListResponseVO<com.hryj.entity.vo.product.category.response.ProdCateTreeResponseItemVO>>
     * @create 2018-07-11 9:19
     **/
    @RequestMapping(value = "/productCategoryMgr/findProdCateTree", method = RequestMethod.POST)
    Result<ListResponseVO<ProdCateTreeResponseItemVO>> findProdCateTree(@RequestBody ProductCategoryFindRequestVO productCategoryFindRequestVO);

    /**
     * @author 王光银
     * @methodName: findProductCategory
     * @methodDesc: 返回商品分类的树形结构数据
     * @description: category_name可以为空，不为空时作前后模糊匹配, include_attr是否包含商品分类的属性数据，Y包含，其他任意值不包含， 缺省为不包含
     * @param: [category_name, include_attr]
     * @return com.hryj.common.Result<java.util.List<com.hryj.entity.vo.product.category.response.ProductCategoryResponseVO>>
     * @create 2018-06-27 20:32
     **/
    @RequestMapping(value = "/productCategoryMgr/findProductCategory", method = RequestMethod.POST)
    Result<ListResponseVO<ProductCategoryResponseVO>> findProductCategory(@RequestBody ProductCategoryFindRequestVO productCategoryFindRequestVO);

    /**
     * @author 王光银
     * @methodName: findProdCategoryAttr
     * @methodDesc: 加载指定商品分类的属性
     * @description:
     * @param: [product_category_id, attr_type]
     * @return com.hryj.common.Result<com.hryj.entity.vo.product.category.response.ProdCateAttrResponseVO>
     * @create 2018-06-27 20:32
     **/
    @RequestMapping(value = "/productCategoryMgr/findProdCategoryAttr", method = RequestMethod.POST)
    Result<ListResponseVO<ProdCateAttrResponseVO>> findProdCategoryAttr(@RequestBody ProductCategoryAttrFindRequestVO productCategoryAttrFindRequestVO);


    /**
     * @author 王光银
     * @methodName: saveProductCategory
     * @methodDesc: 保存创建商品分类
     * @description: 接口支持一次处理商品分类的所有数据：分类基本信息数据和分类属性数据，同时另外单独提供了数据分开处理的接口，并且强烈建议使用数据分开处理的方式
     * @param: [product_category]
     * @return com.hryj.common.Result
     * @create 2018-06-27 20:31
     **/
    @RequestMapping(value = "/productCategoryMgr/saveCreateProductCategory", method = RequestMethod.POST)
    Result saveCreateProductCategory(@RequestBody ProductCategoryRequestVO product_category);

    /**
     * @author 王光银
     * @methodName: saveProductCategoryForBase
     * @methodDesc: 保存创建商品分类
     * @description: 接口只处理商品分类基础信息数据,不会处理属性数据，即使传递了属性参数
     * @param: [product_category]
     * @return com.hryj.common.Result
     * @create 2018-06-27 20:30
     **/
    @RequestMapping(value = "/productCategoryMgr/saveProductCategoryForBase", method = RequestMethod.POST)
    Result saveProductCategoryForBase(@RequestBody ProductCategoryRequestVO product_category);

    /**
     * @author 王光银
     * @methodName: saveOneAttr
     * @methodDesc: 保存新增一个商品分类属性
     * @description:
     * @param: [one_attr]
     * @return com.hryj.common.Result
     * @create 2018-07-05 11:55
     **/
    @RequestMapping(value = "/productCategoryMgr/saveOneAttr", method = RequestMethod.POST)
    Result saveOneAttr(@RequestBody ProdCateAttrHandleResquestVO one_attr);

    /**
     * @author 王光银
     * @methodName: saveManyAttr
     * @methodDesc: 保存一个产品分类的一组属性
     * @description:
     * @param: [prodCateAttrHandleResquestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-05 16:45
     **/
    @RequestMapping(value = "/productCategoryMgr/saveManyAttr", method = RequestMethod.POST)
    Result saveManyAttr(@RequestBody ProdCateAttrHandleResquestVO prodCateAttrHandleResquestVO);

    /**
     * @author 王光银
     * @methodName: updateProductCategory
     * @methodDesc: 一次更新商品分类的所有数据
     * @description: 属性数据部分先删除后新增,但不建议使用该接口，强烈建议使用分别数据更新接口
     * @param: [product_category]
     * @return com.hryj.common.Result
     * @create 2018-06-27 20:30
     **/
    @RequestMapping(value = "/productCategoryMgr/updateProductCategory", method = RequestMethod.POST)
    Result updateProductCategory(@RequestBody ProductCategoryRequestVO product_category);

    /**
     * @author 王光银
     * @methodName: updateProductCategoryForBase
     * @methodDesc: 修改保存商品分类的基础信息数据
     * @description:
     * @param: [product_category]
     * @return com.hryj.common.Result
     * @create 2018-06-27 20:29
     **/
    @RequestMapping(value = "/productCategoryMgr/updateProductCategoryForBase", method = RequestMethod.POST)
    Result updateProductCategoryForBase(@RequestBody ProductCategoryRequestVO product_category);

    /**
     * @author 王光银
     * @methodName: updateManyAttr
     * @methodDesc: 保存修改一个商品分类的一组属性数据
     * @description:
     * @param: [prodCateAttrHandleResquestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-05 16:46
     **/
    @RequestMapping(value = "/productCategoryMgr/updateManyAttr", method = RequestMethod.POST)
    Result updateManyAttr(@RequestBody ProdCateAttrHandleResquestVO prodCateAttrHandleResquestVO);

    /**
     * @author 王光银
     * @methodName: updateOneAttr
     * @methodDesc: 保存修改一个产品分类的一个属性
     * @description:
     * @param: [category_attr]
     * @return com.hryj.common.Result
     * @create 2018-06-27 20:28
     **/
    @RequestMapping(value = "/productCategoryMgr/updateOneAttr", method = RequestMethod.POST)
    Result updateOneAttr(@RequestBody ProdCateAttrRequestVO category_attr);

    /**
     * @author 王光银
     * @methodName: updateOneEnumAttrItem
     * @methodDesc: 保存修改一个商品分类的一个枚举属性的一个枚举条目
     * @description:
     * @param: [prodCateAttrEnumItemRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-27 20:28
     **/
    @RequestMapping(value = "/productCategoryMgr/updateOneEnumItem", method = RequestMethod.POST)
    Result updateOneEnumItem(@RequestBody ProdCateAttrEnumItemRequestVO prodCateAttrEnumItemRequestVO);

    /**
     * @author 王光银
     * @methodName: updateManyEnumItem
     * @methodDesc: 保存修改一个商品分类的一个枚举属性的一组枚举条目
     * @description:
     * @param: [prodCateEnumAttrRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-05 16:51
     **/
    @RequestMapping(value = "/productCategoryMgr/updateManyEnumItem", method = RequestMethod.POST)
    Result updateManyEnumItem(@RequestBody ProdCateEnumAttrRequestVO prodCateEnumAttrRequestVO);

    /**
     * @author 王光银
     * @methodName: saveOneEnumItem
     * @methodDesc: 保存新增一个商品分类的一个枚举属性的一个枚举条目
     * @description:
     * @param: [prodCateEnumAttrRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-05 16:57
     **/
    @RequestMapping(value = "/productCategoryMgr/saveOneEnumItem", method = RequestMethod.POST)
    Result saveOneEnumItem(@RequestBody ProdCateEnumAttrRequestVO prodCateEnumAttrRequestVO);

    /**
     * @author 王光银
     * @methodName: saveManyEnumItem
     * @methodDesc: 保存新增一个商品分类的一个枚举属性的一组枚举条目
     * @description:
     * @param: [prodCateEnumAttrRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-05 16:57
     **/
    @RequestMapping(value = "/productCategoryMgr/saveManyEnumItem", method = RequestMethod.POST)
    Result saveManyEnumItem(@RequestBody ProdCateEnumAttrRequestVO prodCateEnumAttrRequestVO);

    /**
     * @author 王光银
     * @methodName: deleteOneEnumItem
     * @methodDesc: 删除一个商品分类的一个枚举属性的一个枚举条目
     * @description:
     * @param: [deleteOneRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-05 16:53
     **/
    @RequestMapping(value = "/productCategoryMgr/deleteOneEnumItem", method = RequestMethod.POST)
    Result deleteOneEnumItem(@RequestBody DeleteOneRequestVO deleteOneRequestVO);

    /**
     * @author 王光银
     * @methodName: deleteManyEnumItem
     * @methodDesc: 删除一个商品分类的一个枚举属性的一组枚举条目
     * @description:
     * @param: [deleteManyRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-05 16:53
     **/
    @RequestMapping(value = "/productCategoryMgr/deleteManyEnumItem", method = RequestMethod.POST)
    Result deleteManyEnumItem(@RequestBody DeleteManyRequestVO deleteManyRequestVO);

    /**
     * @author 王光银
     * @methodName: deleteProductCategory
     * @methodDesc: 删除一个商品分类
     * @description: 如果参数为空直接返回成功， 如果商品分类下有子级分类或者分类下已关联商品将返回失败
     * @param: [prodCateDeleteRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-27 20:28
     **/
    @RequestMapping(value = "/productCategoryMgr/deleteProductCategory", method = RequestMethod.POST)
    Result deleteProductCategory(@RequestBody ProdCateIdRequestVO prodCateDeleteRequestVO);

    /**
     * @author 王光银
     * @methodName: deleteOneAttr
     * @methodDesc: 删除一个商品分类属性
     * @description: 如果参数为空直接返回成功
     * @param: [deleteOneAttrRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-27 20:27
     **/
    @RequestMapping(value = "/productCategoryMgr/deleteOneAttr", method = RequestMethod.POST)
    Result deleteOneAttr(@RequestBody DeleteOneRequestVO deleteOneAttrRequestVO);

    /**
     * @author 王光银
     * @methodName: deleteManyAttr
     * @methodDesc: 删除一组商品分类属性
     * @description: 如果参数为空直接返回成功
     * @param: [deleteManyAttrRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-06-27 20:27
     **/
    @RequestMapping(value = "/productCategoryMgr/deleteManyAttr", method = RequestMethod.POST)
    Result deleteManyAttr(@RequestBody DeleteManyRequestVO deleteManyAttrRequestVO);


    /**
     * @author 王光银
     * @methodName: upProdCateSort
     * @methodDesc: 上移一个商品分类的展示位置
     * @description:
     * @param: [idRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-11 15:17
     **/
    @PostMapping("/productCategoryMgr/upProdCateSort")
    Result upProdCateSort(@RequestBody IdRequestVO idRequestVO);

    /**
     * @author 王光银
     * @methodName: downProdCateSort
     * @methodDesc: 下移一个商品分类的展示位置
     * @description:
     * @param: [idRequestVO]
     * @return com.hryj.common.Result
     * @create 2018-07-11 15:17
     **/
    @PostMapping("/productCategoryMgr/downProdCateSort")
    Result downProdCateSort(@RequestBody IdRequestVO idRequestVO);
}
