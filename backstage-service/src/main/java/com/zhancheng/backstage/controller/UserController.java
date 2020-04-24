package com.zhancheng.backstage.controller;


import com.zhancheng.backstage.service.UserService;
import com.zhancheng.core.commom.MapFactory;
import com.zhancheng.core.commom.RedisTemplate;
import com.zhancheng.core.commom.SignUtil;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.dto.PageDto;
import com.zhancheng.core.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Api(tags="用户个人信息相关")
@RestController
@RequestMapping("{version}/user")
public class UserController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/login")
    @ApiOperation(value = "后台用户登录", notes = "用户名登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "pwd", value = "密码", required = true)
    })
    public String login(String username, String pwd) {
        Map<String, Object> map = MapFactory.getHashMap();
        map.put("username", username);
        map.put("pwd", SignUtil.MD5(pwd));
        System.out.println(SignUtil.MD5(pwd));
        map.put("status", "0");
        Collection<User> collection = userService.listByMap(map);
        if (collection != null && collection.size() > 0) {
            Iterator<User> iterator = collection.iterator();
            User user = iterator.next();
            String token = redisTemplate.setUser(user);
            return R.ok(token);
        } else {
            return R.fail(CodeMsg.PASSWORD_ERROR);
        }
    }

    @Verify(role = UserIdentity.ADMIN)
    @GetMapping("/firstPart")
    @ApiOperation(value = "获取首页第一行数据和图表数据")
    public String firstPart() {
        return userService.firstPart();
    }

    @Verify(role = UserIdentity.ADMIN)
    @PostMapping("/updatePassword")
    @ApiOperation(value = "用户修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pwd", value = "", required = true),
            @ApiImplicitParam(name = "newPwd", value = "", required = true)
    })
    public String updatePassword(String pwd, String newPwd) {
        Map<String, Object> map = MapFactory.getHashMap();
        Long userId = userService.getUserId();
        map.put("uid", userId);
        map.put("pwd", SignUtil.MD5(pwd));
        //判断新密码是否为空
        if (StringUtils.isBlank(newPwd)) {
            return R.fail(CodeMsg.PARAMETER_NULL);
        }
        System.out.println(SignUtil.MD5(pwd));
        map.put("status", "0");
        Collection<User> collection = userService.listByMap(map);
        if (collection != null && collection.size() > 0) {
            Iterator<User> iterator = collection.iterator();
            User user = iterator.next();
            User temp = new User();
            temp.setUid(user.getUid());
            //修改密码
            temp.setPwd(SignUtil.MD5(newPwd));
            userService.updateById(temp);
            return R.ok();
        } else {
            return R.fail(CodeMsg.PASSWORD_ERROR);
        }
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "查询用户列表", notes = "查询用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", required = true),
            @ApiImplicitParam(name = "size", value = "页容量", required = true),
            @ApiImplicitParam(name = "phone", value = "手机号"),
            @ApiImplicitParam(name = "sortType", value = "默认为空；1 时间排序")
    })
    @GetMapping("/list")
    public String list(PageDto<User> pageDto, String phone, Boolean sortType){

        return R.ok(userService.findList(pageDto, phone, sortType));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "用户详情")
    @ApiImplicitParam(value = "uid", name = "uid", required = true)
    @GetMapping("/info/{uid}")
    public String deleteByIds(@PathVariable Long uid){

        return R.ok(userService.getById(uid));
    }


    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "删除用户")
    @ApiImplicitParam(value = "uid集合", name = "ids", required = true)
    @DeleteMapping("/delete")
    public String deleteByIds(@RequestParam List<Long> ids){
        return R.ok(userService.removeByIds(ids));
    }
}

