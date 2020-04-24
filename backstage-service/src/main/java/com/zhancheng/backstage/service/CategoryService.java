package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.dto.CategoryDto;
import com.zhancheng.core.dto.PageDto;
import com.zhancheng.core.entity.Category;

/**
 * <p>
 * 自主报价分类 服务类
 * </p>
 *
 * @author tangchao
 * @since 2019-08-12
 */
public interface CategoryService extends IService<Category> {

    /**
     * 案例分类zc_category 查询接口：查询一级分类(level=1),关联查询二级分类(cid)
     * @return
     */
    IPage<CategoryDto> queryCategoryByLevel(PageDto pageDto);

    CategoryDto queryInfo(Long id);
}
