package com.zhancheng.backstage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.CardHistoryService;
import com.zhancheng.core.dao.CardHistoryMapper;
import com.zhancheng.core.dto.CardHistoryDto;
import com.zhancheng.core.dto.PageDto;
import com.zhancheng.core.entity.CardHistory;
import com.zhancheng.core.vo.CardHistoryVo;
import org.springframework.stereotype.Service;

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

    @Override
    public IPage<CardHistoryVo> queryList(CardHistoryDto cardHistoryDto, PageDto pageDto) {

        return baseMapper.queryList(pageDto.getPage(), cardHistoryDto);
    }
}
