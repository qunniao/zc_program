package com.zhancheng.backstage.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zhancheng.backstage.service.FeelbackService;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.dto.PageDto;
import com.zhancheng.core.entity.Feelback;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 定制功能反馈表 前端控制器
 * </p>
 *
 * @author tangchao
 * @since 2019-08-13
 */
@Api(tags="用户反馈相关相关")
@RestController
@RequestMapping("{version}/feelback")
public class FeelbackController {

    @Autowired
    private FeelbackService feelbackService;

    @Verify(role = UserIdentity.ADMIN)
    @GetMapping
    @ApiOperation(value = "获取处理需求列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "state", value = "状态：0：待处理 1：已解决；2：未解决")
    })
    public String getFeelback(PageDto<Feelback> pageDto, Integer state, Long uid) {

        return feelbackService.getFeelback(pageDto, state, uid);
    }

    @Verify(role = UserIdentity.ADMIN)
    @GetMapping("/info/{id}")
    @ApiOperation(value = "获取处理需求详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id")
    })
    public String getFeelbackInfo(@PathVariable Long id) {

        return R.ok(feelbackService.getById(id));
    }

    @Verify(role = UserIdentity.ADMIN)
    @PutMapping("/update")
    @ApiOperation(value = "修改需求详情状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id"),
            @ApiImplicitParam(name = "state", value = "状态: 1：已解决； 2：未解决")
    })
    public String updateFeelbackInfo(Long id, Integer state) {
        boolean update = feelbackService.update(new UpdateWrapper<Feelback>()
                .eq("id", id).set("state", state));

        return R.ok(update);
    }

    @Verify(role = UserIdentity.ADMIN)
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除需求反馈")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id")
    })
    public String updateFeelbackInfo(@RequestParam List<Long> ids) {
        return R.ok(feelbackService.removeByIds(ids));
    }


}

