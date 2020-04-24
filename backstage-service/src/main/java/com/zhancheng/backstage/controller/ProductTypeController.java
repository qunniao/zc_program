package com.zhancheng.backstage.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.backstage.service.ProductTypeService;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.entity.ProductType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 产品类目 前端控制器
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Api(tags = "产品类目")
@RestController
@RequestMapping("{version}/productType")
public class ProductTypeController {

    @Autowired
    ProductTypeService productTypeService;

    @Verify(role = UserIdentity.ADMIN)
    @GetMapping
    @ApiOperation(value = "查询商品类目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "", required = true),
            @ApiImplicitParam(name = "size", value = "", required = true),
            @ApiImplicitParam(name = "pid", value = "类目父id(0表示一级类目)", required = true)})
    public String getProductType(Long pid, long pageNum, long size) {
        QueryWrapper<ProductType> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ProductType::getPid, pid).eq(ProductType::getIsDeleted, false);
        IPage<ProductType> page = productTypeService.page(new Page<ProductType>(pageNum, size), queryWrapper);
        return R.ok(page);
    }

    @Verify(role = UserIdentity.ADMIN)
    @GetMapping("/info/{tid}")
    @ApiOperation(value = "查询商品类目详情")
    @ApiImplicitParam(name = "tid", value = "类目id", required = true)
    public String queryInfo(@PathVariable("tid") Long tid) {

        return R.ok(productTypeService.queryInfo(tid));
    }


    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "添加商品类目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cover", value = "图标地址"),
            @ApiImplicitParam(name = "typeName", value = "类目名称", required = true),
            @ApiImplicitParam(name = "sort", value = "排序"),
            @ApiImplicitParam(name = "pid", value = "类目父id(0表示一级类目)")
    })
    @PostMapping("/save")
    public String save(ProductType productType) {
        return R.ok(productTypeService.save(productType));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "修改商品类目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tid", value = "类目id", required = true),
            @ApiImplicitParam(name = "cover", value = "图标地址"),
            @ApiImplicitParam(name = "typeName", value = "类目名称"),
            @ApiImplicitParam(name = "sort", value = "排序"),
            @ApiImplicitParam(name = "pid", value = "类目父id(0表示一级类目)")
    })
    @PutMapping("/update")
    public String update(ProductType productType) {
        return R.ok(productTypeService.updateById(productType));
    }

    @Verify(role = UserIdentity.ADMIN)
    @DeleteMapping
    @ApiOperation(value = "删除商品类目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tids", value = "类目id", required = true)})
    public String deleteProductType(@RequestParam("tids") List<Long> tids) {

        return R.ok(productTypeService.removeByIds(tids));
    }
}

