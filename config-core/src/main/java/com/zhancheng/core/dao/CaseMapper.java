package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.core.entity.Case;
import com.zhancheng.core.vo.CaseListVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 案例表 Mapper 接口
 * </p>
 *
 * @author tangchao
 * @since 2019-08-15
 */
@Repository
public interface CaseMapper extends BaseMapper<Case> {

    /**
     * 案例总数
     *
     * @return
     */
    @Select(" SELECT count(*) count FROM zc_case")
    Integer getCaseCount();

    IPage<Case> queryList(Page page);

    /**
     * 查询案例列表
     * @param page 分页数据
     * @param tid 案例类型
     * @param keyword 关键字
     * @return 案例列表
     */
    IPage<CaseListVo> getList(Page page, @Param("tid") Long tid, @Param("keyword") String keyword);

    Case queryInfo(@Param("cid") Long cid);
}
