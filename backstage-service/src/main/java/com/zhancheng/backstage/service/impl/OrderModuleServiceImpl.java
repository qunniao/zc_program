package com.zhancheng.backstage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.OrderModuleService;
import com.zhancheng.core.dao.OrderModuleMapper;
import com.zhancheng.core.entity.OrderModule;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 自主报价订单 服务实现类
 * </p>
 *
 * @author tangchao
 * @since 2019-08-12
 */
@Service
public class OrderModuleServiceImpl extends ServiceImpl<OrderModuleMapper, OrderModule> implements OrderModuleService {

    @Override
    public IPage<Map> getOrderList(Page page, Long uid) {

        return baseMapper.getOrderList(page, uid);
    }

    @Override
    public Map queryInfo(Long id) {
        return baseMapper.getOrderInfo(id);
    }
}
