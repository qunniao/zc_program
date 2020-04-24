package com.zhancheng.applet.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zhancheng.applet.service.CaseLikeService;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.entity.CaseLike;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 案例浏览点赞收藏 前端控制器
 * </p>
 *
 * @author tangchao
 * @since 2019-08-15
 */
@RestController
@Api(tags = "案例收藏")
@RequestMapping("{version}/caseLike")
public class CaseLikeController {

    @Autowired
    private CaseLikeService caseLikeService;

    @ApiOperation(value = "添加和取消案例收藏")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "案例id", required = true),
            @ApiImplicitParam(name = "uid", value = "用户id",required = true),
            @ApiImplicitParam(name = "isCollection", value = "是否收藏 1:收藏, 0:取消收藏")
    })
    @PostMapping("/saveOrUpdate")
    public String save(CaseLike caseLike){
        Boolean success;
        CaseLike caseLikeOne = caseLikeService.getOne(new QueryWrapper<CaseLike>()
                .eq("cid", caseLike.getCid())
                .eq("uid", caseLike.getUid())
                .eq("is_deleted", 0));

        if (ObjectUtil.isNull(caseLikeOne)){
            success = caseLikeService.save(caseLike);
        }else {
            success = caseLikeService.update(new UpdateWrapper<CaseLike>()
                    .eq("cid", caseLike.getCid())
                    .eq("uid", caseLike.getUid())
                    .set("is_collection", caseLike.getIsCollection())
            );
        }

        System.out.println(caseLike);
        return R.ok(success);
    }

    @ApiOperation(value = "添加和取消案例点赞")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "案例id", required = true),
            @ApiImplicitParam(name = "uid", value = "用户id",required = true),
            @ApiImplicitParam(name = "isLike", value = "是否点赞 1:点赞, 0:取消点赞")
    })
    @PostMapping("/like")
    public String like(CaseLike caseLike){
        Boolean success;
        CaseLike caseLikePo = caseLikeService.getOne(new QueryWrapper<CaseLike>()
                .eq("cid", caseLike.getCid())
                .eq("uid", caseLike.getUid())
                .eq("is_deleted", 0));

        if (ObjectUtil.isNull(caseLikePo)){
            success = caseLikeService.save(caseLike);
        }else {
            success = caseLikeService.update(new UpdateWrapper<CaseLike>()
                    .eq("cid", caseLike.getCid())
                    .eq("uid", caseLike.getUid())
                    .set("is_like", caseLike.getIsLike())
            );
        }

        return R.ok(success);
    }

    @ApiOperation(value = "查询案例收藏列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户id",required = true)
    })
    @GetMapping("list/{uid}")
    public String getList(@PathVariable Long uid){
        return R.ok(caseLikeService.queryList(uid));
    }

}

