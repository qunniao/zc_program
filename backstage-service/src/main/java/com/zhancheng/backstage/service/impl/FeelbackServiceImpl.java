package com.zhancheng.backstage.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.FeelbackService;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dao.FeelbackMapper;
import com.zhancheng.core.dto.PageDto;
import com.zhancheng.core.entity.Feelback;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 定制功能反馈表 服务实现类
 * </p>
 *
 * @author tangchao
 * @since 2019-08-13
 */
@Service
public class FeelbackServiceImpl extends ServiceImpl<FeelbackMapper, Feelback> implements FeelbackService {

    @Override
    public String getFeelback(PageDto<Feelback> pageDto, Integer state, Long uid) {
        IPage<Feelback> feelBack = baseMapper.selectPage(pageDto.getPage(), new QueryWrapper<Feelback>()
                .eq(ObjectUtil.isNotNull(state), "state", state)
                .eq(ObjectUtil.isNotNull(uid), "uid", uid)
                .orderByDesc("gmt_create")
                .eq("is_deleted", 0));

        return R.ok(feelBack);
    }
}
