package com.zhancheng.applet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.applet.service.CardHistoryService;
import com.zhancheng.core.dao.CardHistoryMapper;
import com.zhancheng.core.entity.CardHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 名片浏览,点赞记录表 服务实现类
 * </p>
 *
 * @author tangchao
 * @since 2019-08-08
 */
@Service
public class CardHistoryServiceImpl extends ServiceImpl<CardHistoryMapper, CardHistory> implements CardHistoryService {

    @Autowired
    private CardHistoryMapper cardHistoryMapper;

    @Override
    public Map<String, Object> getCardHistoryByHome(Long cid) {

        HashMap<String, Object> hashMap = new HashMap<>();
        //获取点赞人数
        Integer likeNum = cardHistoryMapper.getCount(0, cid);
        //获取浏览人数
        Integer browseNum = cardHistoryMapper.getCount(1, cid);
        //获取浏览人信息
        List<Map<String, Object>> cardHistory = cardHistoryMapper.getCardHistory(1, cid.toString());
        hashMap.put("likeNum", likeNum);
        hashMap.put("browseInfo", cardHistory);
        hashMap.put("browseNum", browseNum);
        return hashMap;
    }
}
