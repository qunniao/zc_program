package com.zhancheng.core.dao;

import com.zhancheng.core.entity.OrderProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单商品表 Mapper 接口
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Repository
public interface OrderProductMapper extends BaseMapper<OrderProduct> {

    /**
     * 查询商品销售数量
     *
     * @param pid 商品Id
     * @return
     */
    @Select("SELECT IFNULL(SUM(pro.product_num),0) sum FROM zc_order_product pro where pro.spu_id =#{pid}")
    int getSalesNum(Long pid);

    /**
     * 根据orderNum查询所有订单商品
     *
     * @param orderNum
     * @return
     */
    @Select("SELECT tab1.*,zpi.url FROM ( SELECT zod.product_num,zod.product_price,zp.product_name,zod.product_name sku_name,zp.pid FROM zc_order_product zod LEFT JOIN zc_product zp on zp.pid =zod.spu_id where zod.order_number = #{orderNum} ) tab1 LEFT JOIN zc_product_image zpi on zpi.pid =tab1.pid where zpi.is_cover =1")
    List<Map<String, Object>> getOrderProductByOrderNum(@Param("orderNum") String orderNum);


}
