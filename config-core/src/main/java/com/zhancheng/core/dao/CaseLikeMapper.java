package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.entity.CaseLike;
import com.zhancheng.core.vo.CaseLikeVo;

import java.util.List;

/**
 * <p>
 * 案例浏览点赞收藏 Mapper 接口
 * </p>
 *
 * @author tangchao
 * @since 2019-08-15
 */
public interface CaseLikeMapper extends BaseMapper<CaseLike> {

    /**
     * 查询案例收藏列表
     * @param uid
     * @return
     */
    List<CaseLikeVo> queryList(Long uid);

    /**
     * 查询案例浏览量
     * @param cid 案例id
     * @return 浏览量
     */
    Integer queryWatchNum(Long cid);

}
