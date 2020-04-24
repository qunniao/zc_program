package com.zhancheng.applet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.applet.service.CategoryService;
import com.zhancheng.core.dao.CategoryMapper;
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

}
