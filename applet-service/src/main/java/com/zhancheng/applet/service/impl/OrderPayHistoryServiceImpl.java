package com.zhancheng.applet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.applet.service.OrderPayHistoryService;
import com.zhancheng.core.dao.OrderPayHistoryMapper;
import com.zhancheng.core.entity.OrderPayHistory;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付记录表 服务实现类
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Service
public class OrderPayHistoryServiceImpl extends ServiceImpl<OrderPayHistoryMapper, OrderPayHistory> implements OrderPayHistoryService {

}
