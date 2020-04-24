package com.zhancheng.backstage.controller;

import cn.hutool.core.util.ObjectUtil;
import com.zhancheng.backstage.service.NewsService;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.dto.PageDto;
import com.zhancheng.core.entity.News;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 新闻资讯
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-09-20 16:35:52
 */
@Api(tags = "新闻资讯")
@RestController
@RequestMapping("/{version}/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "分页查询新闻资讯列表 按照 sort 和 创建时间倒序排列")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "typeId", value = "分类id")
    })
    @GetMapping("/list")
    public String list(PageDto<News> pageDto, Integer typeId){

        return R.ok(newsService.queryPage(pageDto, typeId));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "查询新闻资讯详情")
    @ApiImplicitParam(name = "id", value = "主键id")
    @GetMapping("/info/{id}")
    public String info(@PathVariable("id") Integer id){

        return R.ok(newsService.getById(id));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "添加新闻资讯")
    @ApiImplicitParams({
                        @ApiImplicitParam(name = "typeId", value = "zc_news_type 分类id"),
                        @ApiImplicitParam(name = "title", value = "标题", required = true),
                        @ApiImplicitParam(name = "cover", value = "封面图"),
                        @ApiImplicitParam(name = "content", value = "新闻内容"),
                        @ApiImplicitParam(name = "sort", value = "排序")
            })
    @PostMapping("/save")
    public String save(@RequestBody News news){

        return R.ok(newsService.save(news));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "修改新闻资讯")
    @ApiImplicitParams({
                        @ApiImplicitParam(name = "id", value = "主键id",required = true),
                        @ApiImplicitParam(name = "typeId", value = "zc_news_type 分类id  "),
                        @ApiImplicitParam(name = "title", value = "标题"),
                        @ApiImplicitParam(name = "cover", value = "封面图"),
                        @ApiImplicitParam(name = "content", value = "新闻内容"),
                        @ApiImplicitParam(name = "sort", value = "排序")
            })
    @PutMapping("/update")
    public String update(@RequestBody News news){

        System.err.println(news);

        News newInfo = newsService.getById(news.getId());

        if (ObjectUtil.isNull(newInfo)){
            throw new MyException(CodeMsg.NEWS_NOT_EXISTED);
        }

        return R.ok(newsService.updateById(news));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "删除新闻资讯")
    @ApiImplicitParam(name = "ids", value = "主键id")
    @DeleteMapping("/delete")
    public String delete(@RequestParam List<Integer> ids){

        return R.ok(newsService.removeByIds(ids));
    }

}
