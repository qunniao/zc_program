package com.zhancheng.applet.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.applet.service.OrderModuleService;
import com.zhancheng.applet.service.ProductTypeService;
import com.zhancheng.core.commom.MapFactory;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.entity.OrderModule;
import com.zhancheng.core.entity.ProductType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 自主报价订单 前端控制器
 * </p>
 *
 * @author tangchao
 * @since 2019-08-12
 */
@Api(tags = "自主报价订单相关")
@RestController
@RequestMapping("{version}/orderModule")
public class OrderModuleController {

    @Autowired
    private OrderModuleService orderModuleService;

    @Autowired
    private ProductTypeService productTypeService;

    @Verify
    @GetMapping
    @ApiOperation(value = "查询最近报价列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "页码", name = "current", required = true),
            @ApiImplicitParam(value = "页容量", name = "size", required = true)
    })
    public String getOrderModule(Page page) {
        return R.ok(orderModuleService.getOrderModule(page));
    }

    @ApiOperation(value = "删除自主报价订单")
    @ApiImplicitParam(value = "ids", name = "主键id", required = true)
    @DeleteMapping("/delete")
    public String deleteById(@RequestParam @NotEmpty List<Long> ids, @NotNull Long uid){

        return R.ok(orderModuleService.deleteByUid(ids, uid));
    }

    @Verify
    @GetMapping("/info")
    @ApiOperation(value = "查看报价详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "报价id"),
    })
    public String getInfo(Long id) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> hashMap = MapFactory.getHashMap();
        Collection<OrderModule> orderModules = orderModuleService.listByMap(hashMap);
        if (orderModules == null) {
            throw new MyException(CodeMsg.QUERY_EMPTY);
        }
        OrderModule next = orderModules.iterator().next();
        ProductType byId = productTypeService.getById(next.getPid());
        result.put("typeName", byId.getTypeName());
        JSONArray array = JSONArray.parseArray(next.getModuleJson());
        //价格
        BigDecimal price = new BigDecimal(0);
        //时间
        Double time = 0.0;
        for (int i = 0; i < array.size(); i++) {
            JSONObject jsonObject = array.getJSONObject(i);
            time += jsonObject.getLong("labor");
            price = price.add(jsonObject.getBigDecimal("price"));
        }
        result.put("price", price);
        result.put("time", (int) time.doubleValue());
        result.put("modules", array);
        return R.ok(result);
    }
}

