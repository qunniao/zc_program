package com.zhancheng.applet.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.applet.service.CaseService;
import com.zhancheng.core.vo.CaseVo;
import com.zhancheng.core.commom.MapFactory;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dao.CaseAttrMapper;
import com.zhancheng.core.dao.CaseLabelMapper;
import com.zhancheng.core.dao.CaseLikeMapper;
import com.zhancheng.core.dao.CaseMapper;
import com.zhancheng.core.entity.Case;
import com.zhancheng.core.entity.CaseAttr;
import com.zhancheng.core.entity.CaseLabel;
import com.zhancheng.core.entity.CaseLike;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
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
    private CaseAttrMapper caseAttrMapper;

    @Autowired
    private CaseLabelMapper caseLabelMapper;

    @Resource
    private CaseLikeMapper caseLikeMapper;

    @Override
    public String getCase(String bid, Integer page, String word) {
        ArrayList<Map<String, Object>> array = new ArrayList();
        QueryWrapper<Case> queryWrapper = new QueryWrapper();
        if (StringUtils.isNotBlank(bid)) {
            queryWrapper.lambda().eq(Case::getTid, bid);
        }
        queryWrapper.lambda().eq(Case::getIsDeleted, false).orderByDesc(Case::getGmtCreate).like(Case::getName, word);
        //先分页查询案例
        IPage<Case> cases = page(new Page<>(page, 10), queryWrapper);
        List<Case> records = cases.getRecords();
        //再查询案例的属性
        records.stream().forEach(x -> {
            Map<String, Object> hashMap = MapFactory.getHashMap();
            hashMap.put("cid", x.getCid());
            List<CaseAttr> caseAttrs = caseAttrMapper.selectByMap(hashMap);
            HashMap<String, Object> temp = new HashMap<>(2);
            temp.put("case", x);
            temp.put("caseAttrs", caseAttrs);
            array.add(temp);
            Map<String, Object> map = MapFactory.getHashMap();
            map.put("bid", x.getTid());
            List<CaseLabel> caseLabels = caseLabelMapper.selectByMap(map);
            if (caseLabels != null) {
                temp.put("typeName", caseLabels.get(0).getLabelName());
            }
        });
        return R.ok(array);
    }

    @Override
    public CaseVo queryInfo(Long cid, Long uid) {

        Case aCase = baseMapper.queryInfo(cid);

        if(ObjectUtil.isNull(aCase)){
            return null;
        }

        CaseVo caseVo = new CaseVo();
        BeanUtil.copyProperties(aCase, caseVo,"attrs", "caseLabels");

        // 点赞数量
        Integer likeNum = caseLikeMapper.selectCount(new QueryWrapper<CaseLike>().eq("cid", cid)
                .eq("is_like", 1).eq("is_deleted", 0));

        // 浏览量
        Integer watchNum = caseLikeMapper.queryWatchNum(cid);

        CaseLike caseLike = caseLikeMapper.selectOne(new QueryWrapper<CaseLike>().eq("cid", cid)
                .eq("is_deleted", 0).eq("uid", uid));

        // 非空 并且 为 收藏为true
        if (ObjectUtil.isNotNull(caseLike)){
            caseVo.setCaseLikeId(caseLike.getId());
            caseLike.setCount(caseLike.getCount() + 1);
            caseVo.setIsCollection(caseLike.getIsCollection());
        }else {
            CaseLike caseLikeInfo = new CaseLike();
            caseLikeInfo.setCid(cid).setIsWatch(Boolean.TRUE).setCount(1);
            caseLikeInfo.insert();
        }

        caseVo.setLikeNum(likeNum);
        caseVo.setWatchNum(watchNum);

        return caseVo;
    }
}
