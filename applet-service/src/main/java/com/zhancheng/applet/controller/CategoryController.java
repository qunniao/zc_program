package com.zhancheng.applet.controller;


import com.zhancheng.applet.service.CategoryService;
import com.zhancheng.core.commom.MapFactory;
import com.zhancheng.core.commom.RedisTemplate;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.entity.Category;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Map;

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

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private CategoryService categoryService;

    @Verify
    @GetMapping
    @ApiOperation(value = "获取自主报价分类", notes = "可以获取一级分类,二级分类,三级分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "父类Id,如果为空,则是一级分类Id")
    })
    public String getCategory(String cid) {
        if (StringUtils.isBlank(cid)) {
            cid = "0";
        }
        Map<String, Object> hashMap = MapFactory.getHashMap();
        hashMap.put("cid", cid);
        Collection<Category> categories = categoryService.listByMap(hashMap);
        return R.ok(categories);
    }

}

