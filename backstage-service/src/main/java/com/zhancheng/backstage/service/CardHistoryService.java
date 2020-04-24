package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.dto.CardHistoryDto;
import com.zhancheng.core.dto.PageDto;
import com.zhancheng.core.entity.CardHistory;
import com.zhancheng.core.vo.CardHistoryVo;

/**
 * <p>
 * 名片浏览,点赞记录表 服务类
 * </p>
 *
 * @author tangchao
 * @since 2019-08-08
 */
public interface CardHistoryService extends IService<CardHistory> {

    /**
     * 查询访客列表
     *
     * @param cardHistoryDto 访客记录信息
     * @param pageDto 分页信息
     * @return page
     */
    IPage<CardHistoryVo> queryList(CardHistoryDto cardHistoryDto, PageDto pageDto);
}
