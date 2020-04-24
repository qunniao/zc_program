package com.zhancheng.applet.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.entity.OrderModule;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 自主报价订单 服务类
 * </p>
 *
 * @author tangchao
 * @since 2019-08-12
 */
public interface OrderModuleService extends IService<OrderModule> {


    /**
     * 返回自主报价
     *
     * @return
     */
    Map<String, Object> getOrderModule(Page page);


    /**
     * 根据 用户id 和 主键id删除
     *
     * @param ids 主键id
     * @param uid 用户id
     * @return boolean
     */
    Boolean deleteByUid(List<Long> ids, Long uid);
}
