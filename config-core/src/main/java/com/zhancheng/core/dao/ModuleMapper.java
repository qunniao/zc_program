package com.zhancheng.core.dao;

import com.zhancheng.core.dto.ModuleCategoryDto;
import com.zhancheng.core.entity.Module;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 模块表 Mapper 接口
 * </p>
 *
 * @author tangchao
 * @since 2019-08-12
 */
@Repository
public interface ModuleMapper extends BaseMapper<Module> {


    @Select("SELECT * FROM zc_module zc WHERE zc.cid in (${cid})")
    List<Module> getAllbyCid(@Param("cid") String cid);

    /**
     * 查询 报价模块信息
     * @param id
     * @return
     */
    ModuleCategoryDto queryInfo(Long id);

    /**
     * 查询 报价模块列表
     * @param cid
     * @return
     */
    List<Module> queryModuleList(@Param("cid") Long cid);

}