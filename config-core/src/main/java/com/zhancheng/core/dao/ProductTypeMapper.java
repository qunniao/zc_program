package com.zhancheng.core.dao;

import com.zhancheng.core.entity.ProductType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 产品类目 Mapper 接口
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Repository
public interface ProductTypeMapper extends BaseMapper<ProductType> {

    /**
     * 查询商品类目信息
     *
     * @param tId 商品类目id
     * @return product type
     */
    @Select("SELECT tab1.*, tab2.type_name  superiorTypeName FROM `zc_product_type` tab1\n" +
            "LEFT JOIN zc_product_type tab2 \n" +
            "ON tab1.pid = tab2.tid AND tab2.is_deleted = 0\n" +
            "WHERE tab1.is_deleted = 0 AND tab1.tid = #{tId}")
    ProductType queryInfo(@Param("tId") Long tId);
}
