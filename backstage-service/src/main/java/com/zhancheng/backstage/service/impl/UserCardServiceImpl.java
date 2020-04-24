package com.zhancheng.backstage.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.UserCardService;
import com.zhancheng.core.dao.CardHistoryMapper;
import com.zhancheng.core.dao.UserCardMapper;
import com.zhancheng.core.dto.UserCardDto;
import com.zhancheng.core.entity.CardHistory;
import com.zhancheng.core.entity.UserCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户名片 服务实现类
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Service
public class UserCardServiceImpl extends ServiceImpl<UserCardMapper, UserCard> implements UserCardService {

    @Autowired
    private CardHistoryMapper cardHistoryMapper;


    @Override
    public UserCardDto queryInfo(Long uid) {

        UserCardDto userCardDto = baseMapper.queryCardInfo(uid);

        if (ObjectUtil.isNotNull(userCardDto)){

            Integer viewNum = cardHistoryMapper.selectCount(new QueryWrapper<CardHistory>()
                    .eq("uid", uid).eq("type", 1).eq("is_deleted",0));
            Integer likeNum = cardHistoryMapper.selectCount(new QueryWrapper<CardHistory>()
                    .eq("uid", uid).eq("type", 0).eq("is_deleted",0));

            userCardDto.setViewNum(viewNum);
            userCardDto.setLikeNum(likeNum);
        }

        return userCardDto;
    }
}
