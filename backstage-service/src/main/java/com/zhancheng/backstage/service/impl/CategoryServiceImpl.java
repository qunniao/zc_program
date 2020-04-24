package com.zhancheng.backstage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.CategoryService;
import com.zhancheng.core.dao.CategoryMapper;
import com.zhancheng.core.dto.CategoryDto;
import com.zhancheng.core.dto.PageDto;
import com.zhancheng.core.entity.Category;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 自主报价分类 服务实现类
 * </p>
 *
 * @author tangchao
 * @since 2019-08-12
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public IPage<CategoryDto> queryCategoryByLevel(PageDto pageDto) {
        Page<Object> page = new Page<>(pageDto.getCurrent(), pageDto.getSize());
        return baseMapper.findByLevel(page);
    }

    @Override
    public CategoryDto queryInfo(Long id) {

        return baseMapper.queryInfo(id);
    }
}
