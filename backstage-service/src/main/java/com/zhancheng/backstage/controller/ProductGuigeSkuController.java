package com.zhancheng.backstage.controller;

import com.zhancheng.backstage.service.ProductGuigeSkuService;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tangchao
 */
@Api(tags = "产品spu表相关")
@RestController
@RequestMapping("{version}/sku")
public class ProductGuigeSkuController {

    @Autowired
    private ProductGuigeSkuService skuService;

    @Verify(role = UserIdentity.ADMIN)
    @GetMapping("/info/{pid}")
    @ApiOperation(value = "查询sku")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "产品id", required = true)
    })
    public String querySkuInfo(@PathVariable Long pid) {

        return R.ok(skuService.queryList(pid));
    }



}
