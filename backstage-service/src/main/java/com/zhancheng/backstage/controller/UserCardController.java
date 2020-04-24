package com.zhancheng.backstage.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.UserCardService;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.dto.PageDto;
import com.zhancheng.core.entity.UserCard;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户名片 前端控制器
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Api(tags = "用户名片")
@RestController
@RequestMapping("{version}/userCard")
public class UserCardController {

    @Autowired
    private UserCardService userCardService;

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "查询用户名片")
    @ApiImplicitParam(value = "用户id", name = "uid", required = true)
    @GetMapping("/info/{uid}")
    public String info(@PathVariable Long uid){

        return R.ok(userCardService.queryInfo(uid));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "分页查询名片列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户id", name = "uid"),
            @ApiImplicitParam(value = "姓名", name = "name"),
            @ApiImplicitParam(value = "手机号", name = "phone")
    })
    @GetMapping("/list")
    public String info(UserCard userCard, PageDto<UserCard> pageDto){

        Long uid = userCard.getUid();
        String name = userCard.getName();
        String phone = userCard.getPhone();

        IPage<UserCard> page = userCardService.page(pageDto.getPage(), new QueryWrapper<UserCard>()
                .eq(ObjectUtil.isNotNull(uid), "uid", uid)
                .likeRight(ObjectUtil.isNotNull(name), "name", name)
                .likeRight(ObjectUtil.isNotNull(phone), "phone", phone)
                .eq("is_deleted", 0));

        return R.ok(page);
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "设置名片是否推荐")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "cid", name = "主键id", required = true),
            @ApiImplicitParam(value = "recommend", name = "是否推荐0 不推荐 1推荐，", required = true),
    })
    @DeleteMapping("update/recommend")
    public String deleteById(Long cid, Boolean recommend) {

        UserCard userCard = userCardService.getById(cid);
        if (ObjectUtil.isNull(userCard)){
            throw new MyException(CodeMsg.USER_CARD_NOT_EXISTS);
        }

        userCard.setIsRecommend(recommend);
        return R.ok(userCard.updateById());
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "删除用户名片")
    @ApiImplicitParam(value = "id", name = "主键id", required = true)
    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id){
        return R.ok(userCardService.removeById(id));
    }
}

