package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.dto.ModuleCategoryDto;
import com.zhancheng.core.entity.Module;

/**
 * <p>
 * 模块表 服务类
 * </p>
 *
 * @author tangchao
 * @since 2019-08-12
 */
public interface ModuleService extends IService<Module> {

    ModuleCategoryDto queryInfo(Long id);

}
