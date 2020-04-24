package com.zhancheng.applet.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.applet.service.OrderModuleService;
import com.zhancheng.core.dao.OrderModuleMapper;
import com.zhancheng.core.dto.OrderModuleDto;
import com.zhancheng.core.entity.OrderModule;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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
    public Map<String, Object> getOrderModule(Page page) {

        List<OrderModuleDto> orderModule = baseMapper.getOrderModule(page);
        Integer integer = baseMapper.countHeadcount();

        Map<String, Object> map = new HashMap<>(2);

        map.put("headcount", integer);
        map.put("info", orderModule);
        return map;
    }

    @Override
    public Boolean deleteByUid(List<Long> ids, Long uid) {

        return baseMapper.deleteByUid(ids, uid);
    }

}
