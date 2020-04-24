package com.zhancheng.applet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.entity.CardHistory;

import java.util.Map;

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
     * 返回首页点赞浏览信息
     *
     * @param cid cardId
     * @return
     */
    Map<String, Object> getCardHistoryByHome(Long cid);

}
