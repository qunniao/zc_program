package com.zhancheng.applet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.dto.CategoryDto;
import com.zhancheng.core.dto.ModuleCategoryDto;
import com.zhancheng.core.entity.Module;

import java.util.List;

/**
 * <p>
 * 模块表 服务类
 * </p>
 *
 * @author tangchao
 * @since 2019-08-12
 */
public interface ModuleService extends IService<Module> {

    /**
     * 根据分类id数据获得模块信息
     *
     * @param cid 分类id数组
     * @return 模块信息
     */
    String getModuleService(String cid);


    /**
     * 传入二级分类id获取模块信息
     *
     * @param id 二级分类id
     * @return 三级分类id和模块id
     */

    String getModuleByCategory(String id);

    /**
     * 查询
     * @param id
     * @return
     */
    ModuleCategoryDto queryInfo(Long id);

    /**
     * 查询
     * @param cid
     * @return
     */
    List<CategoryDto> queryModuleList(Long cid);


}
