package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.dto.PageDto;
import com.zhancheng.core.entity.User;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
public interface UserService extends IService<User> {

    Long getUserId();

    String firstPart();

    IPage findList(PageDto<User>  pageDto, String phone, Boolean sortType);

}
