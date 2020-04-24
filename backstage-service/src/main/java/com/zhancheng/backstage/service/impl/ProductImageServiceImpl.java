package com.zhancheng.backstage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.ProductImageService;
import com.zhancheng.core.dao.ProductImageMapper;
import com.zhancheng.core.entity.ProductImage;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品主图和轮播图 服务实现类
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Service
public class ProductImageServiceImpl extends ServiceImpl<ProductImageMapper, ProductImage> implements ProductImageService {

}
