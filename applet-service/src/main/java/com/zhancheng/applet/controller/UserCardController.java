package com.zhancheng.applet.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhancheng.applet.service.CardHistoryService;
import com.zhancheng.applet.service.UserCardService;
import com.zhancheng.applet.service.UserService;
import com.zhancheng.core.commom.MapFactory;
import com.zhancheng.core.commom.RedisTemplate;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.entity.CardHistory;
import com.zhancheng.core.entity.UserCard;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * <p>
 * 用户名片 前端控制器
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Api(tags = "用户名片相关")
@RestController
@RequestMapping("{version}/userCard")
public class UserCardController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private CardHistoryService cardHistoryService;

    @Autowired
    private UserCardService userCardService;

    @Autowired
    private UserService userService;

    @GetMapping
    @ApiOperation(value = "名片页面", notes = "返回用户名片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户id,如果没有则返回推荐用户名片")
    })
    public String getUserCart(String uid) {
        Map<String, Object> hashMap = MapFactory.getHashMap();
        UserCard next;
        if (StringUtils.isBlank(uid)) {
            hashMap.put("is_recommend", "1");
        } else {
            hashMap.put("uid", uid);
        }
        Collection<UserCard> userCards = userCardService.listByMap(hashMap);
        if (userCards.size() < 1) {
            throw new MyException(CodeMsg.QUERY_EMPTY);
        }
        Iterator<UserCard> iterator = userCards.iterator();
        next = iterator.next();
        //获得名片Id
        Long cid = next.getCid();
        Map<String, Object> result = cardHistoryService.getCardHistoryByHome(cid);
        result.put("userInfo", next);
        String token = request.getHeader("token");
        Long userId = redisTemplate.getUid(token);
        Map<String, Object> map = MapFactory.getHashMap();
        map.put("uid", userId);
        map.put("user_card_id", cid);
        map.put("type", "1");
        Collection<CardHistory> cardHistories = cardHistoryService.listByMap(map);
        if (cardHistories.size() < 1) {
            CardHistory cardHistory = new CardHistory();
            cardHistory.setType(1);
            cardHistory.setUserCardId(cid);
            cardHistory.setUid(userId);
            cardHistoryService.save(cardHistory);
        } else {
            Iterator<CardHistory> list = cardHistories.iterator();
            CardHistory temp = list.next();
            CardHistory cardHistory = new CardHistory();
            cardHistory.setId(temp.getId());
            cardHistory.setNum(temp.getNum() + 1);
            cardHistoryService.updateById(cardHistory);
        }
        return R.ok(result);
    }

    @Verify(role = UserIdentity.ORDINARY)
    @PostMapping
    @ApiOperation(value = "保存用户名片")
    public String saveUserCart(@Valid UserCard userCard) {
        Long uid = userService.getUserId();
        UserCard uid1 = userCardService.getOne(new QueryWrapper<UserCard>().eq("uid", uid));
        // 用户名片存
        if (ObjectUtil.isNotNull(uid1)){
            throw new MyException(CodeMsg.USER_CARD_EXISTS);
        }

        userCard.setUid(uid);
        userCardService.save(userCard);
        return R.ok();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改用户名片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "cid", required = true),
            @ApiImplicitParam(name = "name", value = "名称"),
            @ApiImplicitParam(name = "companyName", value = "公司名称"),
            @ApiImplicitParam(name = "companyPosition", value = "公司职位"),
            @ApiImplicitParam(name = "cover", value = "头像"),
            @ApiImplicitParam(name = "phone", value = "手机号"),
            @ApiImplicitParam(name = "wechat", value = "微信号"),
            @ApiImplicitParam(name = "email", value = "电子邮箱"),
            @ApiImplicitParam(name = "address", value = "地址"),
            @ApiImplicitParam(name = "introText", value = "个人简介"),
            @ApiImplicitParam(name = "introYuyin", value = "语音介绍"),
            @ApiImplicitParam(name = "hometown", value = "家乡"),
            @ApiImplicitParam(name = "schoolName", value = "学校名称"),
            @ApiImplicitParam(name = "schoolTime", value = "学校学习时间，比如：2005年-2008年"),
            @ApiImplicitParam(name = "schoolMaster", value = "学校专业"),
            @ApiImplicitParam(name = "schoolXueli", value = "学校的学历，比如：大专"),
            @ApiImplicitParam(name = "isRecommend", value = "是否推荐")
    })
    public String updateUserCart(UserCard userCard) {
        return R.ok(userCardService.updateById(userCard));
    }

}

