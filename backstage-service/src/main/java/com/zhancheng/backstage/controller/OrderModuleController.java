package com.zhancheng.backstage.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.backstage.service.OrderModuleService;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 自主报价订单 前端控制器
 * </p>
 *
 * @author tangchao
 * @since 2019-08-12
 */
@Api(tags = "自主报价订单")
@RestController
@RequestMapping("{version}/orderModule")
public class OrderModuleController {

    @Autowired
    private OrderModuleService orderModuleService;

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "报价订单分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码",required = true),
            @ApiImplicitParam(name = "size", value = "页容量"),
            @ApiImplicitParam(name = "uid", value = "用户id")
    })
    @GetMapping("/list")
    public String queryList(Page page, Long uid){

        return R.ok(orderModuleService.getOrderList(page, uid));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "报价订单详情查询")
    @ApiImplicitParam(name = "id", value = "主键id",required = true)
    @GetMapping("/info/{id}")
    public String queryInfo(@PathVariable Long id){
        return R.ok(orderModuleService.queryInfo(id));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "删除自主报价订单")
    @ApiImplicitParam(value = "ids", name = "主键id", required = true)
    @DeleteMapping("/delete")
    public String deleteById(@RequestParam List<Long> ids){
        return R.ok(orderModuleService.removeByIds(ids));
    }

}

