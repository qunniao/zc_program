package com.zhancheng.applet.controller;


import com.zhancheng.applet.service.FeelbackService;
import com.zhancheng.applet.service.UserService;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.entity.Feelback;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 定制功能反馈表 前端控制器
 * </p>
 *
 * @author tangchao
 * @since 2019-08-13
 */
@Api(tags = "定制功能反馈相关")
@RestController
@RequestMapping("{version}/feelback")
public class FeelbackController {

    @Autowired
    private UserService userService;

    @Autowired
    private FeelbackService feelbackService;

    @PostMapping
    @Verify(role = UserIdentity.ORDINARY)
    @ApiOperation(value = "提交需求")
    public String getFeelback(@Valid Feelback feelback) {
        Long userId = userService.getUserId();
        feelback.setUid(userId);
        feelbackService.save(feelback);
        return R.ok();
    }


}

