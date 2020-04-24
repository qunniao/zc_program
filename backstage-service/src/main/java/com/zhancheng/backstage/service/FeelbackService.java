package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.dto.PageDto;
import com.zhancheng.core.entity.Feelback;

/**
 * <p>
 * 定制功能反馈表 服务类
 * </p>
 *
 * @author tangchao
 * @since 2019-08-13
 */
public interface FeelbackService extends IService<Feelback> {

    /**
     * 分页查询反馈
     *
     * @param pageDto 分页dto
     * @param state  处理状态
     * @param uid  用户id
     * @return
     */
    String getFeelback(PageDto<Feelback> pageDto, Integer state, Long uid);
}
