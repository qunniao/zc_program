package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.entity.OrderModule;

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

    IPage<Map> getOrderList(Page page, Long uid);

    Map queryInfo(Long id);


}
