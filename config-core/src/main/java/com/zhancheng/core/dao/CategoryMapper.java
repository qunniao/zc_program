package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.core.dto.CategoryDto;
import com.zhancheng.core.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 自主报价分类 Mapper 接口
 * </p>
 *
 * @author tangchao
 * @since 2019-08-12
 */
@Repository
public interface CategoryMapper extends BaseMapper<Category> {

    IPage<CategoryDto> findByLevel(Page page);

    CategoryDto queryInfo(Long id);

    List<CategoryDto> queryList(Long cid);

}
