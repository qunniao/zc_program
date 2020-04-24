package com.zhancheng.applet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.applet.service.FeelbackService;
import com.zhancheng.core.dao.FeelbackMapper;
import com.zhancheng.core.entity.Feelback;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 定制功能反馈表 服务实现类
 * </p>
 *
 * @author tangchao
 * @since 2019-08-13
 */
@Service
public class FeelbackServiceImpl extends ServiceImpl<FeelbackMapper, Feelback> implements FeelbackService {

}
