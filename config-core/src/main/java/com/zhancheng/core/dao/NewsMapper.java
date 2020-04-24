package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.core.entity.News;
import com.zhancheng.core.vo.NewsListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 新闻资讯
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-09-20 16:35:52
 */
@Mapper
public interface NewsMapper extends BaseMapper<News> {

    /**
     * 分页查询新闻资讯列表
     * @param page 分页信息
     * @param typeId 分类id
     * @return page
     */
    IPage<NewsListVo> queryList(Page page, @Param("typeId") Integer typeId);
}
