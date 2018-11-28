package com.hryj.api.product;

import com.alibaba.fastjson.JSON;
import com.hryj.common.Result;
import com.hryj.entity.vo.PageResponseVO;
import com.hryj.entity.vo.product.audit.request.ProductBackupRequestVO;
import com.hryj.entity.vo.product.audit.request.ProductHandledResultSubmitRequestVO;
import com.hryj.entity.vo.product.audit.request.SearchProductAuditRequestVO;
import com.hryj.entity.vo.product.audit.response.ProductAuditPageItemResponseVO;
import com.hryj.entity.vo.product.audit.response.ProductBackupResponseVO;
import com.hryj.feign.ProductFeignClient;
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
 * @className: ProductAuditController
 * @description: 商品审核
 * @create 2018/6/26 17:18
 **/
@Api(value = "/product/productAuditMgr", tags = "商品 - 商品审核")
@Slf4j
@RestController
@RequestMapping("/product/productAuditMgr")
public class ProductAuditController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ProductFeignClient productFeignClient;

    /**
     * @return com.hryj.common.PageResult<com.hryj.entity.vo.product.audit.response.ProductAuditPageItemResponseVO>
     * @author 王光银
     * @methodName: searchProductAuditByPage
     * @methodDesc: 分页查询商品审核管理数据
     * @description:
     * @param: [productAuditSearchRequestVO]
     * @create 2018-06-28 15:51
     **/
    @ApiOperation(value = "分页查询商品审核管理数据 - V1.2", notes = "分页查询商品审核管理数据")
    @PostMapping("/findProductAuditByPage")
    public Result<PageResponseVO<ProductAuditPageItemResponseVO>> searchProductAuditByPage(@RequestBody SearchProductAuditRequestVO productAuditSearchRequestVO) {
        WebUtil.getRequestVO(request, productAuditSearchRequestVO);
        log.info("分页查询商品审核管理数据 -- request:" + JSON.toJSONString(productAuditSearchRequestVO));
        Result result = productFeignClient.searchProductAuditByPage(productAuditSearchRequestVO);
        log.info("分页查询商品审核管理数据 -- response:" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result<com.hryj.entity.vo.product.audit.response.ProductBackupResponseVO>
     * @author 王光银
     * @methodName: getOneProductBackupData
     * @methodDesc: 查询返回一个商品的快照数据
     * @description:
     * @param: [productBackupRequestVO]
     * @create 2018-06-27 20:25
     **/
    @ApiOperation(value = "查询返回一个商品的快照数据 - V1.2", notes = "查询返回一个商品的快照数据")
    @PostMapping("/getOneProductAuditData")
    public Result<ProductBackupResponseVO> getOneProductBackupData(@RequestBody ProductBackupRequestVO productBackupRequestVO) {
        WebUtil.getRequestVO(request, productBackupRequestVO);
        log.info("查询返回一个商品的快照数据 -- request:" + JSON.toJSONString(productBackupRequestVO));
        Result result = productFeignClient.getOneProductBackupData(productBackupRequestVO);
        log.info("查询返回一个商品的快照数据 -- response:" + JSON.toJSONString(result));
        return result;
    }

    /**
     * @return com.hryj.common.Result
     * @author 王光银
     * @methodName: submitProductAuditResult
     * @methodDesc: 提交商品审核处理结果
     * @description: 审核通过时，修改后的商品数据备份会覆盖到商品库中
     * @param: [productHandledResultSubmitRequestVO]
     * @create 2018-06-27 20:25
     **/
    @ApiOperation(value = "提交商品审核处理结果", notes = "提交商品审核处理结果")
    @PostMapping("/submitProductAuditResult")
    public Result submitProductAuditResult(@RequestBody ProductHandledResultSubmitRequestVO productHandledResultSubmitRequestVO) {
        WebUtil.getRequestVO(request, productHandledResultSubmitRequestVO);
        log.info("提交商品审核处理结果 -- request:" + JSON.toJSONString(productHandledResultSubmitRequestVO));
        Result result = productFeignClient.submitProductAuditResult(productHandledResultSubmitRequestVO);
        log.info("提交商品审核处理结果 -- response:" + JSON.toJSONString(result));
        return result;
    }
}
