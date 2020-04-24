package com.zhancheng.backstage.controller;


import cn.hutool.core.util.ObjectUtil;
import com.zhancheng.backstage.service.ProductGuigeSkuService;
import com.zhancheng.backstage.service.ProductService;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.entity.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 产品spu表 前端控制器
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Api(tags = "产品spu表相关")
@RestController
@RequestMapping("{version}/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductGuigeSkuService productGuigeSkuService;
//    @Verify(role = UserIdentity.ADMIN)
    @GetMapping
    @ApiOperation(value = "产品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "", required = true),
            @ApiImplicitParam(name = "size", value = "", required = true)
    })
    public String getProduct(long pageNum, long size) {
        return R.ok(productService.getProduct(pageNum, size));
    }

    @Verify(role = UserIdentity.ADMIN)
    @GetMapping("/info/{pid}")
    @ApiOperation(value = "产品详情")
    @ApiImplicitParam(name = "pid", value = "产品id", required = true)
    public String getProduct(@PathVariable Long pid) {

        return R.ok(productService.queryDetails(pid));
    }

    @Verify(role = UserIdentity.ADMIN)
    @DeleteMapping
    @ApiOperation(value = "删除产品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pids", value = "", required = true)
    })
    public String delete(Long[] pids) {
        for (Long pid : pids) {
            Product product = new Product();
            product.setPid(pid);
            product.setIsDeleted(true);
            productService.updateById(product);
        }
        return R.ok();
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation("修改产品")
    @PutMapping("/update")
    public String update(@RequestBody Map<String, Object> map){

       return R.ok(productService.updateProduct(map));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "添加产品")
    @PostMapping("/insert")
    @Transactional(rollbackFor = Exception.class)
    public String save(@RequestBody Map<String, Object> map){

        return R.ok(productService.insertProduct(map));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "上架和下架产品接口")
    @PostMapping("/putAway")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "产品id", required = true),
            @ApiImplicitParam(name = "status", value = "产品状态：0上架中，-1已下架", required = true)
    })
    public String putAway(Long pid, Integer status){

        Product product = productService.getById(pid);

        if (ObjectUtil.isNull(product)){
            throw new MyException(CodeMsg.PRODUCT_NOT_EXISTED);
        }
        product.setStatus(status);
        return R.ok(product.updateById());
    }
}

