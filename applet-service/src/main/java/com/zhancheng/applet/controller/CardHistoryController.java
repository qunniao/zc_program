package com.zhancheng.applet.controller;


import com.zhancheng.applet.service.CardHistoryService;
import com.zhancheng.applet.service.UserCardService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Map;

/**
 * <p>
 * 名片浏览,点赞记录表 前端控制器
 * </p>
 *
 * @author tangchao
 * @since 2019-08-08
 */
@Api(tags = "名片浏览相关")
@RestController
@RequestMapping("{version}/cardHistory")
public class CardHistoryController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private CardHistoryService cardHistoryService;

    @Autowired
    private UserCardService userCardService;

    @PostMapping
    @Verify(role = UserIdentity.ORDINARY)
    @ApiOperation(value = "点赞", notes = "给名片点赞")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cardId", value = "名片id", required = true)
    })
    public String addCardHistory(Long cardId) {
        String token = request.getHeader("token");
        Long uid = redisTemplate.getUid(token);
        if (cardId == null) {
            throw new MyException(CodeMsg.PARAMETER_NULL);
        }
        Map<String, Object> hashMap = MapFactory.getHashMap();
        hashMap.put("uid", uid);
        hashMap.put("user_card_id", cardId);
        hashMap.put("type", "0");
        Collection<CardHistory> cardHistories = cardHistoryService.listByMap(hashMap);
        if (cardHistories.size() > 0) {
            throw new MyException(CodeMsg.ALREADY_EXISTED);
        }
//        判断名片是否存在
        Map<String, Object> map = MapFactory.getHashMap();
        map.put("cid", cardId);
        Collection<UserCard> userCards = userCardService.listByMap(map);
        if (userCards.size() < 1) {
            throw new MyException(CodeMsg.QUERY_EMPTY);
        }
        CardHistory cardHistory = new CardHistory();
        cardHistory.setUid(uid);
        cardHistory.setType(0);
        cardHistory.setUserCardId(cardId);
        cardHistoryService.save(cardHistory);
        return R.ok();
    }




}

