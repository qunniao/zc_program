package com.zhancheng.backstage.controller;


import com.zhancheng.backstage.service.CardHistoryService;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.dto.CardHistoryDto;
import com.zhancheng.core.dto.PageDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 名片浏览,点赞记录表 前端控制器
 * </p>
 *
 * @author tangchao
 * @since 2019-08-08
 */
@Api(tags = "名片浏览")
@RestController
@RequestMapping("{version}/cardHistory")
public class CardHistoryController {

    @Autowired
    private CardHistoryService cardHistoryService;

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "名片访客记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户id")
    })
    @GetMapping("/list")
    public String queryList(CardHistoryDto cardHistoryDto, PageDto pageDto) {

        System.err.println(pageDto);

        return R.ok(cardHistoryService.queryList(cardHistoryDto, pageDto));
    }

}

