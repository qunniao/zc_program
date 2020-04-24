package com.zhancheng.backstage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.StoreService;
import com.zhancheng.core.dao.StoreMapper;
import com.zhancheng.core.entity.Store;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 店铺 服务实现类
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService {

}
