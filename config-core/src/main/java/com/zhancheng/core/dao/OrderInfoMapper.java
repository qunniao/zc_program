package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.core.entity.OrderInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Repository
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {


    /**
     * 查看每个用户自助报价订单数
     *
     * @return
     */
    @Select("SELECT count(*) count,uid FROM zc_order_info zc WHERE zc.omid !=0 GROUP BY uid ")
    List<Map<String, Object>> seletNumByUid();


    /**
     * 分页查询待处理订单
     *
     * @return
     */
    IPage<Map<String, Object>> getOrderInfo(Page page);


    /**
     * 分页查询订单
     *
     * @param page       分页信息
     * @param orderState 订单状态
     * @param uid        用户uid
     * @return
     */
    IPage<Map<String, Object>> getOrderByState(Page page, @Param("orderState") Integer orderState, @Param("uid") Long uid);
}

