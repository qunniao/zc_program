package com.zhancheng.applet.controller;


import com.zhancheng.applet.service.ProductTypeService;
import com.zhancheng.core.commom.MapFactory;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.entity.ProductType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

/**
 * <p>
 * 产品类目 前端控制器
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Api(tags = "产品类目相关")
@RestController
@RequestMapping("{version}/productType")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @Verify
    @GetMapping(value = "/firstType" )
    @ApiOperation(value = "查询产品一级类目", notes = "返回所有一级类目")
    public String getfirstType() {
        Map<String, Object> hashMap = MapFactory.getHashMap();
        hashMap.put("pid", "0");
        Collection<ProductType> productTypes = productTypeService.listByMap(hashMap);
        return R.ok(productTypes);
    }

    @Verify
    @GetMapping("/secondType")
    @ApiOperation(value = "查询产品二级类目", notes = "返回二级类目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "1", required = true)
    })
    public String getSecondType(int pid) {
        Map<String, Object> hashMap = MapFactory.getHashMap();
        hashMap.put("pid", pid);
        Collection<ProductType> productTypes = productTypeService.listByMap(hashMap);
        return R.ok(productTypes);
    }

    @ApiOperation(value = "产品分类添加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "类目父id(0表示一级类目)"),
            @ApiImplicitParam(name = "typeName", value = "类目名称"),
            @ApiImplicitParam(name = "cover", value = "图标地址"),
            @ApiImplicitParam(name = "sort", value = "排序")
    })
    @PostMapping("/save")
    public String save(ProductType productType){

        return R.ok(productTypeService.save(productType));
    }

    @ApiOperation(value = "产品分类修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tid", value = "类目id",required = true),
            @ApiImplicitParam(name = "pid", value = "类目父亲id(0表示一级类目)"),
            @ApiImplicitParam(name = "typeName", value = "类目名称"),
            @ApiImplicitParam(name = "cover", value = "图标地址"),
            @ApiImplicitParam(name = "sort", value = "排序")
    })
    @PutMapping("/update")
    public String update(ProductType productType){

        return R.ok(productTypeService.updateById(productType));
    }





}

