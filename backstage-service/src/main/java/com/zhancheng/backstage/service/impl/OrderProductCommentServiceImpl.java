package com.zhancheng.backstage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.OrderProductCommentService;
import com.zhancheng.core.dao.OrderProductCommentMapper;
import com.zhancheng.core.entity.OrderProductComment;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单评论表 服务实现类
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Service
public class OrderProductCommentServiceImpl extends ServiceImpl<OrderProductCommentMapper, OrderProductComment> implements OrderProductCommentService {

}
