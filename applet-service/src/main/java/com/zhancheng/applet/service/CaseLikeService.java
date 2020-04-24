package com.zhancheng.applet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.entity.CaseLike;
import com.zhancheng.core.vo.CaseLikeVo;

import java.util.List;

/**
 * <p>
 * 案例浏览点赞收藏 服务类
 * </p>
 *
 * @author tangchao
 * @since 2019-08-15
 */
public interface CaseLikeService extends IService<CaseLike> {


    /**
     * 查询案例收藏列表
     *
     * @param uid
     * @return case like vo
     */
    List<CaseLikeVo> queryList(Long uid);
}
