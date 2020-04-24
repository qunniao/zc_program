package com.zhancheng.backstage.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.UserService;
import com.zhancheng.core.commom.RedisTemplate;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dao.*;
import com.zhancheng.core.dto.PageDto;
import com.zhancheng.core.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CardHistoryMapper cardHistoryMapper;

    @Autowired
    private OrderPayHistoryMapper orderPayHistoryMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CaseMapper caseMapper;

    @Override
    public Long getUserId() {
        String token = request.getHeader("token");
        return redisTemplate.getUid(token);
    }

    @Override
    public String firstPart() {

        HashMap<String, Object> result = new HashMap<>();
        //用户总数
        result.put("userCount", userMapper.selectCount());

        //访问人数
        result.put("visitorCount", cardHistoryMapper.getAllSum());

        //线上交易额
        result.put("gmv", orderPayHistoryMapper.getGMV());

        //案例总数
        result.put("caseCount", caseMapper.getCaseCount());
        //产品数量
        result.put("productCount", productMapper.getProductCount());

        //用户增长的图表
        result.put("chart", userMapper.selectCountByMonth());
        return R.ok(result);
    }

    @Override
    public IPage findList(PageDto<User> page, String phone, Boolean sortType) {

        IPage iPage = baseMapper.selectPage(page.getPage(), new QueryWrapper<User>()
                .likeRight(StrUtil.isNotBlank(phone),"phone", phone)
                .orderByDesc(sortType, "gmt_create"));

        return iPage;
    }
}
