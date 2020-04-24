package com.zhancheng.backstage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.ProductTypeService;
import com.zhancheng.core.dao.ProductTypeMapper;
import com.zhancheng.core.entity.ProductType;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品类目 服务实现类
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Service
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements ProductTypeService {

    @Override
    public ProductType queryInfo(Long tId) {
        return baseMapper.queryInfo(tId);
    }
}
