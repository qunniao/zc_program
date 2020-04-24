package com.zhancheng.backstage.controller;

import com.zhancheng.backstage.service.NewsTypeService;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.dto.PageDto;
import com.zhancheng.core.entity.NewsType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 新闻类目
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-09-20 16:35:52
 */
@Api(tags = "新闻类目")
@RestController
@RequestMapping("/{version}/newsTypes")
public class NewsTypeController {

    @Autowired
    private NewsTypeService newsTypeService;

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "分页查询新闻类目列表")
    @GetMapping("/list")
    public String list(PageDto<NewsType> pageDto, NewsType newsType){

        return R.ok(newsTypeService.queryPage(pageDto, newsType));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "查询新闻类目详情")
    @ApiImplicitParam(name = "id", value = "主键id")
    @GetMapping("/info/{id}")
    public String info(@PathVariable("id") Integer id){

        return R.ok(newsTypeService.getById(id));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "添加新闻类目")
    @ApiImplicitParams({
                        @ApiImplicitParam(name = "id", value = "主键id"),
                        @ApiImplicitParam(name = "name", value = "类目名称"),
                        @ApiImplicitParam(name = "sort", value = "排序"),
            })
    @PostMapping("/save")
    public String save(NewsType newsType){

        return R.ok(newsTypeService.save(newsType));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "修改新闻类目")
    @ApiImplicitParams({
                        @ApiImplicitParam(name = "id", value = "主键id"),
                        @ApiImplicitParam(name = "name", value = "类目名称"),
                        @ApiImplicitParam(name = "sort", value = "排序")
            })
    @PostMapping("/update")
    public String update(NewsType newsType){

        return R.ok(newsTypeService.updateById(newsType));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "删除新闻类目")
    @ApiImplicitParam(name = "ids", value = "主键id")
    @DeleteMapping("/delete")
    public String delete(@RequestParam List<Integer> ids){

        return R.ok(newsTypeService.removeByIds(ids));
    }

}
