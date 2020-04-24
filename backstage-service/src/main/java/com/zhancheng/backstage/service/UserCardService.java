package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.dto.UserCardDto;
import com.zhancheng.core.entity.UserCard;

/**
 * <p>
 * 用户名片 服务类
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
public interface UserCardService extends IService<UserCard> {

   UserCardDto queryInfo(Long uid);

}
