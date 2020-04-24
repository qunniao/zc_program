package com.zhancheng.applet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.applet.service.CaseLikeService;
import com.zhancheng.core.dao.CaseLikeMapper;
import com.zhancheng.core.entity.CaseLike;
import com.zhancheng.core.vo.CaseLikeVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 案例浏览点赞收藏 服务实现类
 * </p>
 *
 * @author tangchao
 * @since 2019-08-15
 */
@Service
public class CaseLikeServiceImpl extends ServiceImpl<CaseLikeMapper, CaseLike> implements CaseLikeService {

    @Override
    public List<CaseLikeVo> queryList(Long uid) {
        return baseMapper.queryList(uid);
    }
}
