package com.zhancheng.backstage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.ModuleService;
import com.zhancheng.core.dao.ModuleMapper;
import com.zhancheng.core.dto.ModuleCategoryDto;
import com.zhancheng.core.entity.Module;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 模块表 服务实现类
 * </p>
 *
 * @author tangchao
 * @since 2019-08-12
 */
@Service
public class ModuleServiceImpl extends ServiceImpl<ModuleMapper, Module> implements ModuleService {

    @Override
    public ModuleCategoryDto queryInfo(Long id) {

        return baseMapper.queryInfo(id);
    }
}
