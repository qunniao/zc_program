package com.zhancheng.applet.controller;


import com.zhancheng.applet.service.CaseLabelService;
import com.zhancheng.core.commom.MapFactory;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.entity.CaseLabel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

/**
 * <p>
 * 案例标签 前端控制器
 * </p>
 *
 * @author tangchao
 * @since 2019-08-15
 */
@Api(tags = "案例标签相关")
@RestController
@RequestMapping("{version}/caseLabel")
public class CaseLabelController {

    @Autowired
    private CaseLabelService caseLabelService;

    @Verify
    @GetMapping
    @ApiOperation(value = "获取案例标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "分类标签的父Id,如果没有则是一级分类")
    })
    public String getCaseLabel(String pid) {
        Map<String, Object> hashMap = MapFactory.getHashMap();
        if (StringUtils.isBlank(pid)) {
            hashMap.put("pid", "0");
        } else {
            hashMap.put("pid", pid);
        }
        Collection<CaseLabel> caseLabels = caseLabelService.listByMap(hashMap);
        return R.ok(caseLabels);
    }

}

