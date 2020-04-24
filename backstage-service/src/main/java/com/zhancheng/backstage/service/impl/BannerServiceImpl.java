package com.zhancheng.backstage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.BannerService;
import com.zhancheng.core.dao.BannerMapper;
import com.zhancheng.core.entity.Banner;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 轮播图表 服务实现类
 * </p>
 *
 * @author tangchao
 * @since 2019-08-02
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {

}
