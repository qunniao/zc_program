package com.zhancheng.backstage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhancheng.backstage.service.BannerService;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.entity.Banner;
import com.zhancheng.core.util.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 轮播图表 前端控制器
 * </p>
 *
 * @author tangchao
 * @since 2019-08-02
 */
@Api(tags = "轮播图表相关")
@RestController
@RequestMapping("{version}/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @Verify(role = UserIdentity.ADMIN)
    @PostMapping("/addFile")
    @ApiOperation(value = "上传文件")
    @ApiImplicitParam(name = "files", value = "上传文件集合", required = true)
    public String addFile(@RequestParam(value = "files") List<MultipartFile> files){
        Map<String, Object> map = new HashMap<>(2);
        ArrayList<String> urls = FileUtils.addFile(files);
        map.put("urls", urls);
        return R.ok(urls);
    }

    @Verify(role = UserIdentity.ADMIN)
    @GetMapping("/list")
    @ApiOperation(value = "查询轮播图")
    @ApiImplicitParam(name = "type", value = "0是商城首页轮播图,1是报价首页轮播图", required = true)
    public String queryList(Integer type){

        List<Banner> banner = bannerService.list(new QueryWrapper<Banner>()
                .eq("type", type).eq("is_deleted", 0)
                .orderByDesc("sort"));
        return R.ok(banner);
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "添加轮播图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "0是商城首页轮播图,1是报价首页轮播图", required = true),
            @ApiImplicitParam(name = "sort", value = "排序", required = true),
            @ApiImplicitParam(name = "url", value = "图片地址", required = true),
    })
    @PostMapping("/insert")
    public String insertBanner(Banner banner){

        return R.ok(banner.insert());
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "删除轮播图")
    @ApiImplicitParam(name = "ids", value = "轮播图id集合", required = true)
    @DeleteMapping("/delete")
    public String insertBanner(@RequestParam List<Long> ids){

        return R.ok(bannerService.removeByIds(ids));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "轮播图详情")
    @ApiImplicitParam(name = "id", value = "轮播图主键id", required = true)
    @GetMapping("/info/{id}")
    public String insertBanner(@PathVariable Long id){

        return R.ok(bannerService.getById(id));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "修改轮播图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "轮播图主键id", required = true),
            @ApiImplicitParam(name = "type", value = "0是商城首页轮播图,1是报价首页轮播图"),
            @ApiImplicitParam(name = "sort", value = "排序"),
            @ApiImplicitParam(name = "url", value = "图片地址"),
    })
    @PutMapping("/update")
    public String updateBanner(Banner banner){

        return R.ok(banner.updateById());
    }
}

