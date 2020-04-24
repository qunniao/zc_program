package com.zhancheng.backstage.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.CaseAttrService;
import com.zhancheng.backstage.service.CaseService;
import com.zhancheng.core.dao.CaseAttrMapper;
import com.zhancheng.core.dao.CaseMapper;
import com.zhancheng.core.dto.JsonDto;
import com.zhancheng.core.entity.Case;
import com.zhancheng.core.entity.CaseAttr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 案例表 服务实现类
 * </p>
 *
 * @author tangchao
 * @since 2019-08-15
 */
@Service
public class CaseServiceImpl extends ServiceImpl<CaseMapper, Case> implements CaseService {
    
    @Autowired
    private CaseAttrService caseAttrService;

    @Autowired
    private CaseAttrMapper caseAttrMapper;

    @Override
    public IPage<Case> queryList(Page page) {

        return baseMapper.queryList(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveInfo(Case casePO) {
        baseMapper.insert(casePO);

        return addAttr(casePO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateInfo(Case casePO) {
        baseMapper.updateById(casePO);

        List<CaseAttr> caseAttrList = caseAttrMapper.selectList(new QueryWrapper<CaseAttr>().eq("cid", casePO.getCid()));
        if (ObjectUtil.isNotEmpty(caseAttrList)){
            Map<String, Object> map = new HashMap<>(1);
            map.put("cid", casePO.getCid());
            caseAttrMapper.deleteByMap(map);
        }

        return addAttr(casePO);
    }

    @Override
    public Case queryInfo(Long cid) {

        return baseMapper.queryInfo(cid);
    }

    private Boolean addAttr(Case casePO){
        JsonDto[] attrs = casePO.getAttrs();
        if (ArrayUtil.isNotEmpty(attrs)) {
            List<CaseAttr> caseAttrList = new LinkedList<>();
            for (JsonDto jsonDto : attrs) {
                CaseAttr caseAttr = new CaseAttr();
                caseAttr.setAttrName(jsonDto.getName()).setAttrValue(jsonDto.getValue()).setCid(casePO.getCid());
                caseAttrList.add(caseAttr);
            }
            return caseAttrService.saveBatch(caseAttrList);
        }

        return false;
    }
}
