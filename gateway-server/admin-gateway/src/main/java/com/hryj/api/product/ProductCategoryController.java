package com.hryj.api.product;

import com.alibaba.fastjson.JSON;
import com.hryj.common.Result;
import com.hryj.entity.vo.ListResponseVO;
import com.hryj.entity.vo.product.category.request.*;
import com.hryj.entity.vo.product.category.response.ProdCateAttrResponseVO;
import com.hryj.entity.vo.product.category.response.ProductCategoryResponseVO;
import com.hryj.entity.vo.product.partyprod.request.IdRequestVO;
import com.hryj.exception.ServerException;
import com.hryj.feign.ProductCategoryFeignClient;
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
 * @author 王光银
 * @className: ProductCategoryController
 * @description: 商品分类
 * @create 2018/6/25 0025 20:07
 **/
@Api(value = "/product/productCategoryMgr", tags = "商品 - 商品分类")
@Slf4j
@RestController
@RequestMapping("/product/productCategoryMgr")
public class ProductCategoryController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ProductCategoryFeignClient productCategoryFeignClient;

    /**
     * @author 王光银
     * @methodName: getOneProductCategory
     * @methodDesc: 根据分类ID返回一个商品分类数据，包含属性数据
     * @description: 返回对象中的 sub_list 节点属性永远不会有值
     * @param: [prodCateIdRequestVO]
     * @return com.hryj.common.Result<com.hryj.entity.vo.product.category.response.ProductCategoryResponseVO>
     * @create 2018-07-12 10:12
     **/
    @ApiOperation(value = "根据分类ID返回一个商品分类数据，包含属性数据", notes = "根据分类ID返回一个商品分类数据，包含属性数据")
    @PostMapping("/getOneProductCategory")
    public Result<ProductCategoryResponseVO> getOneProductCategory(@RequestBody ProdCateIdRequestVO prodCateIdRequestVO) throws ServerException {
        WebUtil.getRequestVO(request, prodCateIdRequestVO);
        log.info("返回商品分类树结构（简洁版）- -- request:" + JSON.toJSONString(prodCateIdRequestVO));
        Result result = productCategoryFeignClient.getOneProductCategory(prodCateIdRequestVO);
        log.info("返回商品分类树结构（简洁版）- -- response:" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.ListResponseVO<com.hryj.entity.vo.product.category.response.ProductCategoryResponseVO>>
     * @author 王光银
     * @methodName: findProductCategory
     * @methodDesc: 返回商品分类的树形结构数据
     * @description: category_name可以为空，不为空时作前后模糊匹配, include_attr是否包含商品分类的属性数据，Y包含，其他任意值不包含， 缺省为不包含，目前为Y时也没有返回属性数据
     * @param: [productCategoryFindRequestVO]
     * @create 2018-07-05 17:12
     **/
    @ApiOperation(value = "返回商品分类的树形结构数据", notes = "category_name可以为空，不为空时作前后模糊匹配, include_attr是否包含商品分类的属性数据，Y包含，其他任意值不包含， 缺省为不包含,目前为Y时也没有返回属性数据")
    @PostMapping("/findProductCategory")
    public Result<ListResponseVO<ProductCategoryResponseVO>> findProductCategory(@RequestBody ProductCategoryFindRequestVO productCategoryFindRequestVO) {
        WebUtil.getRequestVO(request, productCategoryFindRequestVO);
        log.info("返回商品分类的树形结构数据- -- request:" + JSON.toJSONString(productCategoryFindRequestVO));
        Result result = productCategoryFeignClient.findProductCategory(productCategoryFindRequestVO);
        log.info("返回商品分类的树形结构数据- -- response:" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.ListResponseVO<com.hryj.entity.vo.product.category.response.ProdCateAttrResponseVO>>
     * @author 王光银
     * @methodName: findProdCategoryAttr
     * @methodDesc: 加载指定分类的属性集合
     * @description:
     * @param: [productCategoryAttrFindRequestVO]
     * @create 2018-07-05 17:13
     **/
    @ApiOperation(value = "加载指定分类的属性集合", notes = "加载指定分类的属性集合")
    @PostMapping("/findProdCategoryAttr")
    public Result<ListResponseVO<ProdCateAttrResponseVO>> findProdCategoryAttr(@RequestBody ProductCategoryAttrFindRequestVO productCategoryAttrFindRequestVO) {
        WebUtil.getRequestVO(request, productCategoryAttrFindRequestVO);
        log.info("加载指定分类的属性集合 -- request:" + JSON.toJSONString(productCategoryAttrFindRequestVO));
        Result result = productCategoryFeignClient.findProdCategoryAttr(productCategoryAttrFindRequestVO);
        log.info("加载指定分类的属性集合 -- response:" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result
     * @author 王光银
     * @methodName: saveCreateProductCategory
     * @methodDesc: 保存创建商品分类(全数据)
     * @description:
     * @param: [product_category]
     * @create 2018-06-27 20:31
     **/
    @ApiOperation(value = "保存创建商品分类", notes = "接口支持一次处理商品分类的所有数据：分类基本信息数据和分类属性数据，同时另外单独提供了数据分开处理的接口，并且强烈建议使用数据分开处理的方式")
    @PostMapping("/saveProductCategory")
    public Result saveCreateProductCategory(@RequestBody ProductCategoryRequestVO product_category) {
        WebUtil.getRequestVO(request, product_category);
        log.info("保存创建商品分类(全数据) -- request:" + JSON.toJSONString(product_category));
        Result result = productCategoryFeignClient.saveCreateProductCategory(product_category);
        log.info("保存创建商品分类(全数据) -- response:" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result
     * @author 王光银
     * @methodName: saveProductCategoryForBase
     * @methodDesc: 保存创建商品分类基(基础数据)
     * @description: 接口只处理商品分类基础信息数据, 不会处理属性数据，即使传递了属性参数
     * @param: [product_category]
     * @create 2018-06-27 20:30
     **/
    @ApiOperation(value = "保存创建商品分类", notes = "接口只处理商品分类基础信息数据,不会处理属性数据，即使传递了属性参数")
    @PostMapping("/saveProductCategoryForBase")
    public Result saveProductCategoryForBase(@RequestBody ProductCategoryRequestVO product_category) {
        WebUtil.getRequestVO(request, product_category);
        log.info("保存创建商品分类(基础数据) -- request:" + JSON.toJSONString(product_category));
        Result result = productCategoryFeignClient.saveProductCategoryForBase(product_category);
        log.info("保存创建商品分类(基础数据) -- response:" + JSON.toJSONString(result));
        return result;
    }


    /**
     * @return com.hryj.common.Result
     * @author 王光银
     * @methodName: updateProductCategory
     * @methodDesc: 更新商品分类的所有数据
     * @description:
     * @param: [product_category]
     * @create 2018-07-05 17:15
     **/
    @ApiOperation(value = "一次更新商品分类的所有数据", notes = "属性数据部分先删除后新增,但不建议使用该接口，强烈建议使用分别数据更新接口")
    @PostMapping("/updateProductCategory")
    public Result updateProductCategory(@RequestBody ProductCategoryRequestVO product_category) {
        WebUtil.getRequestVO(request, product_category);
        log.info("更新商品分类的所有数据 -- request:" + JSON.toJSONString(product_category));
        Result result = productCategoryFeignClient.updateProductCategory(product_category);
        log.info("更新商品分类的所有数据 -- response:" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result
     * @author 王光银
     * @methodName: updateProductCategoryForBase
     * @methodDesc: 修改保存商品分类的基础信息数据
     * @description:
     * @param: [product_category]
     * @create 2018-07-05 17:16
     **/
    @ApiOperation(value = "修改保存商品分类的基础信息数据", notes = "修改保存商品分类的基础信息数据")
    @PostMapping("/updateProductCategoryForBase")
    public Result updateProductCategoryForBase(@RequestBody ProductCategoryRequestVO product_category) {
        WebUtil.getRequestVO(request, product_category);
        log.info("修改保存商品分类的基础信息数据 -- request:" + JSON.toJSONString(product_category));
        Result result = productCategoryFeignClient.updateProductCategoryForBase(product_category);
        log.info("修改保存商品分类的基础信息数据 -- response:" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result
     * @author 王光银
     * @methodName: updateOneAttr
     * @methodDesc: 保存修改一个商品分类的一个属性
     * @description:
     * @param: [category_attr]
     * @create 2018-07-05 17:16
     **/
    @ApiOperation(value = "保存修改一个任意类型属性", notes = "属性类型如果是枚举，那么会将枚举条目先删除后新增（如果修改前存在枚举条目）")
    @PostMapping("/updateOneAttr")
    public Result updateOneAttr(@RequestBody ProdCateAttrRequestVO category_attr) {
        WebUtil.getRequestVO(request, category_attr);
        log.info("保存修改一个商品分类的一个属性 -- request:" + JSON.toJSONString(category_attr));
        Result result = productCategoryFeignClient.updateOneAttr(category_attr);
        log.info("保存修改一个商品分类的一个属性 -- response:" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result
     * @author 王光银
     * @methodName: 删除一个商品分类
     * @methodDesc: 如果商品分类下有子级分类或者分类下已关联商品将返回失败
     * @description:
     * @param: [prodCateDeleteRequestVO]
     * @create 2018-07-05 17:18
     **/
    @ApiOperation(value = "删除一个商品分类", notes = "如果商品分类下有子级分类或者分类下已关联商品将返回失败")
    @PostMapping("/deleteProductCategory")
    public Result deleteProductCategory(@RequestBody ProdCateIdRequestVO prodCateDeleteRequestVO) {
        WebUtil.getRequestVO(request, prodCateDeleteRequestVO);
        log.info("删除一个商品分类 -- request:" + JSON.toJSONString(prodCateDeleteRequestVO));
        Result result = productCategoryFeignClient.deleteProductCategory(prodCateDeleteRequestVO);
        log.info("删除一个商品分类 -- response:" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result
     * @author 王光银
     * @methodName: deleteOneAttr
     * @methodDesc: 删除一个商品分类的一个属性
     * @description:
     * @param: [deleteOneAttrRequestVO]
     * @create 2018-07-05 17:18
     **/
    @ApiOperation(value = "删除商品分类的一个属性", notes = "删除商品分类的一个属性")
    @PostMapping("/deleteOneAttr")
    public Result deleteOneAttr(@RequestBody DeleteOneRequestVO deleteOneAttrRequestVO) {
        WebUtil.getRequestVO(request, deleteOneAttrRequestVO);
        log.info("删除一个商品分类的一个属性 -- request:" + JSON.toJSONString(deleteOneAttrRequestVO));
        Result result = productCategoryFeignClient.deleteOneAttr(deleteOneAttrRequestVO);
        log.info("删除一个商品分类的一个属性 -- response:" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result
     * @author 王光银
     * @methodName: deleteManyAttr
     * @methodDesc: 删除一个商品分类的一组属性
     * @description:
     * @param: [deleteManyAttrRequestVO]
     * @create 2018-07-05 17:19
     **/
    @ApiOperation(value = "删除商品分类的一组属性", notes = "删除商品分类的一组属性")
    @PostMapping("/deleteManyAttr")
    public Result deleteManyAttr(@RequestBody DeleteManyRequestVO deleteManyAttrRequestVO) {
        WebUtil.getRequestVO(request, deleteManyAttrRequestVO);
        log.info("删除一个商品分类的一组属性 -- request:" + JSON.toJSONString(deleteManyAttrRequestVO));
        Result result = productCategoryFeignClient.deleteManyAttr(deleteManyAttrRequestVO);
        log.info("删除一个商品分类的一组属性 -- response:" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result
     * @author 王光银
     * @methodName: deleteOneEnumItem
     * @methodDesc: 删除一个商品分类的一个枚举属性的一个枚举条目
     * @description:
     * @param: [deleteOneRequestVO]
     * @create 2018-07-05 17:23
     **/
    @ApiOperation(value = "删除一个商品分类的一个枚举属性的一个枚举条目", notes = "删除一个商品分类的一个枚举属性的一个枚举条目")
    @PostMapping("/deleteOneEnumItem")
    public Result deleteOneEnumItem(@RequestBody DeleteOneRequestVO deleteOneRequestVO) {
        WebUtil.getRequestVO(request, deleteOneRequestVO);
        log.info("删除一个商品分类的一个枚举属性的一个枚举条目 -- request:" + JSON.toJSONString(deleteOneRequestVO));
        Result result = productCategoryFeignClient.deleteOneEnumItem(deleteOneRequestVO);
        log.info("删除一个商品分类的一个枚举属性的一个枚举条目 -- response:" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result
     * @author 王光银
     * @methodName: deleteManyEnumItem
     * @methodDesc: 删除一个商品分类的一个枚举属性的一组枚举条目
     * @description:
     * @param: [deleteManyRequestVO]
     * @create 2018-07-05 17:24
     **/
    @ApiOperation(value = "删除一个商品分类的一个枚举属性的一组枚举条目", notes = "删除一个商品分类的一个枚举属性的一组枚举条目")
    @PostMapping("/deleteManyEnumItem")
    public Result deleteManyEnumItem(@RequestBody DeleteManyRequestVO deleteManyRequestVO) {
        WebUtil.getRequestVO(request, deleteManyRequestVO);
        log.info("删除一个商品分类的一个枚举属性的一组枚举条目 -- request:" + JSON.toJSONString(deleteManyRequestVO));
        Result result = productCategoryFeignClient.deleteManyEnumItem(deleteManyRequestVO);
        log.info("删除一个商品分类的一个枚举属性的一组枚举条目 -- response:" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result
     * @author 王光银
     * @methodName: saveOneAttr
     * @methodDesc: 保存新增一个商品分类属性
     * @description:
     * @param: [one_attr]
     * @create 2018-07-05 11:55
     **/
    @ApiOperation(value = "保存新增一个商品分类属性", notes = "保存新增一个商品分类属性")
    @PostMapping("/saveOneAttr")
    public Result saveOneAttr(@RequestBody ProdCateAttrHandleResquestVO one_attr) {
        WebUtil.getRequestVO(request, one_attr);
        log.info("保存新增一个商品分类属性 -- request:" + JSON.toJSONString(one_attr));
        Result result = productCategoryFeignClient.saveOneAttr(one_attr);
        log.info("保存新增一个商品分类属性 -- response:" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result
     * @author 王光银
     * @methodName: saveOneEnumItem
     * @methodDesc: 保存新增一个商品分类的一个枚举属性的一个枚举条目
     * @description:
     * @param: [prodCateEnumAttrRequestVO]
     * @create 2018-07-05 16:57
     **/
    @ApiOperation(value = "保存新增一个商品分类的一个枚举属性的一个枚举条目", notes = "保存新增一个商品分类的一个枚举属性的一个枚举条目")
    @PostMapping("/saveOneEnumItem")
    public Result saveOneEnumItem(@RequestBody ProdCateEnumAttrRequestVO prodCateEnumAttrRequestVO) {
        WebUtil.getRequestVO(request, prodCateEnumAttrRequestVO);
        log.info("保存新增一个商品分类的一个枚举属性的一个枚举条目 -- request:" + JSON.toJSONString(prodCateEnumAttrRequestVO));
        Result result = productCategoryFeignClient.saveOneEnumItem(prodCateEnumAttrRequestVO);
        log.info("保存新增一个商品分类的一个枚举属性的一个枚举条目 -- response:" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result
     * @author 王光银
     * @methodName: saveManyAttr
     * @methodDesc: 保存一个产品分类的一组属性
     * @description:
     * @param: [prodCateAttrHandleResquestVO]
     * @create 2018-07-05 16:45
     **/
    @ApiOperation(value = "保存一个产品分类的一组属性", notes = "保存一个产品分类的一组属性")
    @PostMapping("/saveManyAttr")
    public Result saveManyAttr(@RequestBody ProdCateAttrHandleResquestVO prodCateAttrHandleResquestVO) {
        WebUtil.getRequestVO(request, prodCateAttrHandleResquestVO);
        log.info("保存一个产品分类的一组属性 -- request:" + JSON.toJSONString(prodCateAttrHandleResquestVO));
        Result result = productCategoryFeignClient.saveManyAttr(prodCateAttrHandleResquestVO);
        log.info("保存一个产品分类的一组属性 -- response:" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result
     * @author 王光银
     * @methodName: saveManyEnumItem
     * @methodDesc: 保存新增一个商品分类的一个枚举属性的一组枚举条目
     * @description:
     * @param: [prodCateEnumAttrRequestVO]
     * @create 2018-07-05 16:57
     **/
    @ApiOperation(value = "保存新增一个商品分类的一个枚举属性的一组枚举条目", notes = "保存新增一个商品分类的一个枚举属性的一组枚举条目")
    @PostMapping("/saveManyEnumItem")
    public Result saveManyEnumItem(@RequestBody ProdCateEnumAttrRequestVO prodCateEnumAttrRequestVO) {
        WebUtil.getRequestVO(request, prodCateEnumAttrRequestVO);
        log.info("保存新增一个商品分类的一个枚举属性的一组枚举条目 -- request:" + JSON.toJSONString(prodCateEnumAttrRequestVO));
        Result result = productCategoryFeignClient.saveManyEnumItem(prodCateEnumAttrRequestVO);
        log.info("保存新增一个商品分类的一个枚举属性的一组枚举条目 -- response:" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result
     * @author 王光银
     * @methodName: updateOneEnumAttrItem
     * @methodDesc: 保存修改一个商品分类的一个枚举属性的一个枚举条目
     * @description:
     * @param: [prodCateAttrEnumItemRequestVO]
     * @create 2018-06-27 20:28
     **/
    @ApiOperation(value = "保存修改一个商品分类的一个枚举属性的一个枚举条目", notes = "保存修改一个商品分类的一个枚举属性的一个枚举条目")
    @PostMapping("/updateOneEnumItem")
    public Result updateOneEnumItem(@RequestBody ProdCateAttrEnumItemRequestVO prodCateAttrEnumItemRequestVO) {
        WebUtil.getRequestVO(request, prodCateAttrEnumItemRequestVO);
        log.info("保存修改一个商品分类的一个枚举属性的一个枚举条目 -- request:" + JSON.toJSONString(prodCateAttrEnumItemRequestVO));
        Result result = productCategoryFeignClient.updateOneEnumItem(prodCateAttrEnumItemRequestVO);
        log.info("保存修改一个商品分类的一个枚举属性的一个枚举条目 -- response:" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result
     * @author 王光银
     * @methodName: updateManyAttr
     * @methodDesc: 保存修改一个商品分类的一组属性数据
     * @description:
     * @param: [prodCateAttrHandleResquestVO]
     * @create 2018-07-05 16:46
     **/
    @ApiOperation(value = "保存修改一个商品分类的一组属性数据", notes = "保存修改一个商品分类的一组属性数据")
    @PostMapping("/updateManyAttr")
    public Result updateManyAttr(@RequestBody ProdCateAttrHandleResquestVO prodCateAttrHandleResquestVO) {
        WebUtil.getRequestVO(request, prodCateAttrHandleResquestVO);
        log.info("保存修改一个商品分类的一组属性数据 -- request:" + JSON.toJSONString(prodCateAttrHandleResquestVO));
        Result result = productCategoryFeignClient.updateManyAttr(prodCateAttrHandleResquestVO);
        log.info("保存修改一个商品分类的一组属性数据 -- response:" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result
     * @author 王光银
     * @methodName: updateManyEnumItem
     * @methodDesc: 保存修改一个商品分类的一个枚举属性的一组枚举条目
     * @description:
     * @param: [prodCateEnumAttrRequestVO]
     * @create 2018-07-05 16:51
     **/
    @ApiOperation(value = "保存修改一个商品分类的一个枚举属性的一组枚举条目", notes = "保存修改一个商品分类的一个枚举属性的一组枚举条目")
    @PostMapping("/updateManyEnumItem")
    public Result updateManyEnumItem(@RequestBody ProdCateEnumAttrRequestVO prodCateEnumAttrRequestVO) {
        WebUtil.getRequestVO(request, prodCateEnumAttrRequestVO);
        log.info("保存修改一个商品分类的一个枚举属性的一组枚举条目 -- request:" + JSON.toJSONString(prodCateEnumAttrRequestVO));
        Result result = productCategoryFeignClient.updateManyEnumItem(prodCateEnumAttrRequestVO);
        log.info("保存修改一个商品分类的一个枚举属性的一组枚举条目 -- response:" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result
     * @author 王光银
     * @methodName: upProdCateSort
     * @methodDesc: 上移一个商品分类的展示位置
     * @description:
     * @param: [idRequestVO]
     * @create 2018-07-11 15:17
     **/
    @ApiOperation(value = "上移一个商品分类的展示位置", notes = "上移一个商品分类的展示位置")
    @PostMapping("/upProdCateSort")
    public Result upProdCateSort(@RequestBody IdRequestVO idRequestVO) {
        WebUtil.getRequestVO(request, idRequestVO);
        log.info("上移一个商品分类的展示位置- -- request:" + JSON.toJSONString(idRequestVO));
        Result result = productCategoryFeignClient.upProdCateSort(idRequestVO);
        log.info("上移一个商品分类的展示位置- -- response:" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result
     * @author 王光银
     * @methodName: downProdCateSort
     * @methodDesc: 下移一个商品分类的展示位置
     * @description:
     * @param: [idRequestVO]
     * @create 2018-07-11 15:17
     **/
    @ApiOperation(value = "下移一个商品分类的展示位置", notes = "下移一个商品分类的展示位置")
    @PostMapping("/downProdCateSort")
    public Result downProdCateSort(@RequestBody IdRequestVO idRequestVO) {
        WebUtil.getRequestVO(request, idRequestVO);
        log.info("下移一个商品分类的展示位置- -- request:" + JSON.toJSONString(idRequestVO));
        Result result = productCategoryFeignClient.downProdCateSort(idRequestVO);
        log.info("下移一个商品分类的展示位置- -- response:" + JSON.toJSONString(result));
        return result;
    }
}
