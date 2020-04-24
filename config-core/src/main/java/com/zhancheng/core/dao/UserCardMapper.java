package com.zhancheng.core.dao;

import com.zhancheng.core.dto.UserCardDto;
import com.zhancheng.core.entity.UserCard;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户名片 Mapper 接口
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Repository
public interface UserCardMapper extends BaseMapper<UserCard> {

    UserCardDto queryCardInfo(Long uid);
}
