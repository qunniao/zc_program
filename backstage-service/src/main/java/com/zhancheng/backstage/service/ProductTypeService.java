package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.entity.ProductType;

/**
 * <p>
 * 产品类目 服务类
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
public interface ProductTypeService extends IService<ProductType> {


    /**
     * 查询商品类目信息
     *
     * @param tId 商品类目id
     * @return product type
     */
    ProductType queryInfo(Long tId);
}
