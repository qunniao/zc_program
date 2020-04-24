package com.zhancheng.backstage.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.ModuleService;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.dto.PageDto;
import com.zhancheng.core.entity.Module;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 模块表 前端控制器
 * </p>
 *
 * @author tangchao
 * @since 2019-08-12
 */
@Api(tags = "报价模块")
@RestController
@RequestMapping("{version}/module")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation("报价模块添加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "自主报价分类Id"),
            @ApiImplicitParam(name = "moduleName", value = "模块名称", required = true),
            @ApiImplicitParam(name = "price", value = "价格",required = true),
            @ApiImplicitParam(name = "labor", value = "工时")
    })
    @PostMapping
    public String save(Module module){

        return R.ok(moduleService.save(module));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation("报价模块修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id"),
            @ApiImplicitParam(name = "cid", value = "自主报价分类Id"),
            @ApiImplicitParam(name = "moduleName", value = "模块名称"),
            @ApiImplicitParam(name = "price", value = "价格"),
            @ApiImplicitParam(name = "labor", value = "工时")
    })
    @PutMapping
    public String update(Module module){

        return R.ok(moduleService.updateById(module));
    }

    @Verify(role = UserIdentity.ADMIN)
    @GetMapping("/info/{id}")
    @ApiOperation(value = "查询报价详情")
    @ApiImplicitParam(name = "id", value = "主键id", required = true)
    public String info(@PathVariable Long id){

        return R.ok(moduleService.queryInfo(id));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "报价模块分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "categoryId"),
            @ApiImplicitParam(name = "moduleName", value = "模块名称")
    })
    @GetMapping("/list")
    public String list(PageDto<Module> pageDto, Long cid, String moduleName){

        IPage<Module> page = moduleService.page(pageDto.getPage(), new QueryWrapper<Module>()
                .eq(ObjectUtil.isNotNull(cid), "cid", cid)
                .like(ObjectUtil.isNotNull(moduleName), "module_name", moduleName)
                .eq("is_deleted", 0));

        return R.ok(page);
    }

    @Verify(role = UserIdentity.ADMIN)
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除报价")
    @ApiImplicitParam(name = "ids", value = "多个或一个主键id", required = true)
    public String info(@RequestParam("ids") List<Long> ids){
        return R.ok(moduleService.removeByIds(ids));
    }
}

