package com.zhancheng.backstage.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhancheng.backstage.service.CategoryService;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.dto.CategoryDto;
import com.zhancheng.core.dto.PageDto;
import com.zhancheng.core.entity.Category;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 自主报价分类 前端控制器
 * </p>
 *
 * @author tangchao
 * @since 2019-08-12
 */
@Api(tags = "自主报价分类")
@RestController
@RequestMapping("{version}/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation("查询自主报价分类列表")
    @GetMapping("/list")
    public String list(PageDto pageDto) {

        return R.ok(categoryService.queryCategoryByLevel(pageDto));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation("查询二级自主报价分类列表")
    @GetMapping("/list/{cId}")
    public String list(@PathVariable Long cId) {

        List<Category> category = categoryService.list(new QueryWrapper<Category>()
                .eq("cid", cId).orderByDesc("gmt_create"));

        return R.ok(category);
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation("查询自主报价分类详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id")
    })
    @GetMapping("/info/{id}")
    public String info(@PathVariable("id") Long id) {

        CategoryDto categoryDto = categoryService.queryInfo(id);

        return R.ok(categoryDto);
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation("添加自主报价分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id"),
            @ApiImplicitParam(name = "cid", value = "父类id没有则为0"),
            @ApiImplicitParam(name = "typeName", value = "类目名称"),
            @ApiImplicitParam(name = "cover", value = "类目图标地址"),
            @ApiImplicitParam(name = "level", value = "一级类目，二级类目，三级类目"),
            @ApiImplicitParam(name = "sort", value = "排序")
    })
    @PostMapping("/save")
    public String save(Category category) {

        return R.ok(categoryService.save(category));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation("修改自主报价分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id"),
            @ApiImplicitParam(name = "cid", value = "父类id没有则为0"),
            @ApiImplicitParam(name = "typeName", value = "类目名称"),
            @ApiImplicitParam(name = "cover", value = "类目图标地址"),
            @ApiImplicitParam(name = "level", value = "一级类目，二级类目，三级类目"),
            @ApiImplicitParam(name = "sort", value = "排序")
    })
    @PutMapping("/update")
    public String update(Category category) {

        return R.ok(categoryService.updateById(category));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation("删除自主报价分类")
    @ApiImplicitParam(name = "ids", value = "多个或一个id", required = true)
    @DeleteMapping("/delete")
    public String delete(@RequestParam List<Long> ids) {

        return R.ok(categoryService.removeByIds(ids));
    }
}

