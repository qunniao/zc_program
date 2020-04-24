package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.entity.Product;

import java.util.Map;

/**
 * <p>
 * 产品spu表 服务类
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
public interface ProductService extends IService<Product> {

    IPage<Map<String, Object>> getProduct(long pageNum, long size);

    /**
     * 更新产品
     *
     * @param map 产品信息
     * @return boolean
     */
    Boolean updateProduct(Map<String, Object> map);

    /**
     * 添加产品
     *
     * @param map 产品信息
     * @return boolean
     */
    Boolean insertProduct(Map<String, Object> map);

    /**
     * 查询产品详情信息
     *
     * @param pid 产品id
     * @return map
     */
    Map<String, Object> queryDetails(Long pid);
}
