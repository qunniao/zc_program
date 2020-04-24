package com.zhancheng.applet.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.applet.service.ModuleService;
import com.zhancheng.core.commom.MapFactory;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dao.CategoryMapper;
import com.zhancheng.core.dao.ModuleMapper;
import com.zhancheng.core.dto.CategoryDto;
import com.zhancheng.core.dto.ModuleCategoryDto;
import com.zhancheng.core.entity.Category;
import com.zhancheng.core.entity.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private ModuleMapper moduleMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public String getModuleService(String cid) {
        List<Module> allbyCid = moduleMapper.getAllbyCid(cid);
        return R.ok(allbyCid);
    }

    @Override
    public String getModuleByCategory(String id) {
        List<HashMap<String, Object>> result = new ArrayList<>();
        Map<String, Object> hashMap = MapFactory.getHashMap();
        hashMap.put("cid", id);
        List<Category> categories = categoryMapper.selectByMap(hashMap);
        for (Category category : categories) {
            HashMap<String, Object> temp = new HashMap<>();
            temp.put("category", category);
            Long cid = category.getId();
            hashMap.put("cid", cid);
            List<Module> modules = moduleMapper.selectByMap(hashMap);
            temp.put("modules", modules);
            result.add(temp);
        }
        return R.ok(result);
    }

    @Override
    public ModuleCategoryDto queryInfo(Long id) {

        return baseMapper.queryInfo(id);
    }

    @Override
    public List<CategoryDto> queryModuleList(Long cid) {

        List<CategoryDto> categoryDtos = categoryMapper.queryList(cid);

        if(ObjectUtil.isEmpty(categoryDtos)){
            return null;
        }

        //根据一级id获取二级列表
        for (CategoryDto categoryDto : categoryDtos) {
            // 三级列表
            List<Category> category = categoryDto.getCategory();

            if(ObjectUtil.isNotEmpty(category)){
                for (Category category1 : category) {
                    Long id = category1.getId();
                    System.err.println(id);
                    List<Module> modules = moduleMapper.queryModuleList(id);
                    System.err.println(modules);
                    category1.setModuleList(modules);
                }
            }
        }

        return categoryDtos;
    }
}
