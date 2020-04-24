package com.zhancheng.applet.controller;

import com.zhancheng.applet.service.BannerService;
import com.zhancheng.core.commom.MapFactory;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.entity.Banner;
import com.zhancheng.core.util.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * <p>
 * 轮播图表 前端控制器
 * </p>
 *
 * @author tangchao
 * @since 2019-08-02
 */
@Api(tags = "轮播图相关")
@RestController
@RequestMapping("{version}/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @Verify
    @GetMapping
    @ApiOperation(value = "返回轮播图", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "0是商城首页轮播图,1是报价首页轮播图", required = true)
    })
    public String getBanner(String type) {
        Map<String, Object> hashMap = MapFactory.getHashMap();
        hashMap.put("type", type);
        Collection<Banner> banners = bannerService.listByMap(hashMap);
        return R.ok(banners);
    }


    @Verify
    @PostMapping("/addFile")
    @ApiOperation(value = "上传文件")
    @ApiImplicitParam(name = "files", value = "上传文件集合", required = true)
    public String addFile(@RequestParam(value = "files") List<MultipartFile> files) throws Exception {
        Map<String, Object> map = new HashMap<>();
        ArrayList<String> urls = FileUtils.addFile(files);
        System.err.println("上传测试： " + urls);
        map.put("urls", urls);
        return R.ok(urls);
    }
}

