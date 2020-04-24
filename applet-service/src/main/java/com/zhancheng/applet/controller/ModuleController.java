package com.zhancheng.applet.controller;


import com.alibaba.fastjson.JSONObject;
import com.zhancheng.applet.service.*;
import com.zhancheng.core.commom.SignUtil;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.entity.Category;
import com.zhancheng.core.entity.Module;
import com.zhancheng.core.entity.OrderInfo;
import com.zhancheng.core.entity.OrderModule;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * 模块表 前端控制器
 * </p>
 *
 * @author tangchao
 * @since 2019-08-12
 */
@Api(tags = "模块相关")
@RestController
@RequestMapping("{version}/module")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderModuleService orderModuleService;

    @Autowired
    private OrderInfoService orderInfoService;

    @Verify
    @GetMapping("/modules")
    @ApiOperation(value = "根据一个二级分类Id获取功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "二级分类Id)", required = true)
    })
    public String getModuleByCategory(String id) {
        if (StringUtils.isBlank(id)) {
            throw new MyException(CodeMsg.PARAMETER_NULL);
        }
        return moduleService.getModuleByCategory(id);
    }

    @Verify
    @GetMapping("/category/{cid}")
    @ApiOperation(value = "根据一级id返回 报价分类列表和报价模块信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "一级分类Id)", required = true)
    })
    public String queryModuleList(@PathVariable Long cid) {

        return R.ok(moduleService.queryModuleList(cid));
    }

    @GetMapping("/info/{id}")
    @ApiOperation(value = "查询报价详情")
    @ApiImplicitParam(name = "id", value = "主键id", required = true)
    public String info(@PathVariable Long id){

        return R.ok(moduleService.queryInfo(id));
    }

    @Verify
    @GetMapping
    @ApiOperation(value = "根据多个三级分类Id获取功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "分类id数组)", required = true)
    })
    public String getModule(String cid) {
        if (StringUtils.isBlank(cid)) {
            throw new MyException(CodeMsg.PARAMETER_NULL);
        }
        return moduleService.getModuleService(cid);
    }

    @Verify(role = UserIdentity.ORDINARY)
    @PostMapping("/total")
    @ApiOperation(value = "计算总价")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mid", value = "模块id数组", required = true),
            @ApiImplicitParam(name = "pid", value = "一级分类Id", required = true)
    })
    public String getTotal(String pid, @RequestParam("mid") List<String> mid) {
        HashMap<String, Object> hashMap = new HashMap<>();

        if (StringUtils.isBlank(pid)) {
            throw new MyException(CodeMsg.PARAMETER_NULL);
        }
        Collection<Module> modules = moduleService.listByIds(mid);
        // 模块数量
        int size = modules.size();
        Iterator<Module> iterator = modules.iterator();
        BigDecimal price = new BigDecimal(0);
        Double time = 0.0;
        while (iterator.hasNext()) {
            Module next = iterator.next();
            time += next.getLabor();
            price = price.add(next.getPrice());
        }
        time = Math.ceil(time / 8);
        Category byId = categoryService.getById(pid);
        hashMap.put("category", byId);
        hashMap.put("modules", modules);
        hashMap.put("price", price);
        hashMap.put("time", (int) time.doubleValue());
        //添加订单
        OrderModule orderModule = new OrderModule();
        orderModule.setPid(byId.getId());
        orderModule.setModuleJson(JSONObject.toJSONString(modules));
        orderModule.setNum(size);
        orderModuleService.save(orderModule);
        Long userId = userService.getUserId();
        OrderInfo orderInfo = new OrderInfo();
        orderModule.setUid(userId);
        orderInfo.setOrderNumber(SignUtil.generateNonceStr());
        orderModule.setTotalPrice(price);
        orderInfo.setPayWay(2);
        orderInfoService.save(orderInfo);
        orderModuleService.save(orderModule);
        return R.ok(hashMap);
    }
}

