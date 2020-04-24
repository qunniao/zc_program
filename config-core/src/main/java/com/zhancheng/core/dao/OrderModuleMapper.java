package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.core.dto.OrderModuleDto;
import com.zhancheng.core.entity.OrderModule;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 自主报价订单 Mapper 接口
 * </p>
 *
 * @author tangchao
 * @since 2019-08-12
 */
@Repository
public interface OrderModuleMapper extends BaseMapper<OrderModule> {

//    @Select("SELECT tab1.*, zu.nickname FROM ( SELECT zoi.total_price totalPrice, zoi.gmt_create gmtCareate, zoi.uid, " +
//            "zom.id, zp.product_name productName FROM zc_order_module zom LEFT JOIN zc_order_info zoi " +
//            "ON zom.id = zoi.omid  LEFT JOIN zc_product zp ON zp.pid = zom.pid AND zp.is_deleted = 0 " +
//            "ORDER BY zoi.gmt_create DESC LIMIT 10 ) tab1 LEFT JOIN zc_user zu ON tab1.uid = zu.uid")


    List<OrderModuleDto> getOrderModule(Page page);

    Integer countHeadcount();

    /**
     * 查询订单列表
     * @param page
     * @param uid
     * @return
     */
    IPage<Map> getOrderList(Page page,@Param("uid") Long uid);

    Map getOrderInfo(@Param("id") Long id);

    Boolean deleteByUid(List<Long> ids, Long uid);

}
