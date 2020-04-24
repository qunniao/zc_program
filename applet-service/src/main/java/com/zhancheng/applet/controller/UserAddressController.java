package com.zhancheng.applet.controller;


import cn.hutool.core.util.ObjectUtil;
import com.zhancheng.applet.service.UserAddressService;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dto.PageDto;
import com.zhancheng.core.entity.UserAddress;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户地址 前端控制器
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Api(tags = "用户地址")
@RestController
@RequestMapping("{version}/userAddress")
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;

    @ApiOperation(value = "分页查询用户地址")
    @GetMapping("/list")
    public String list(PageDto<UserAddress> page, UserAddress userAddress){

        return R.ok(userAddressService.queryPage(page, userAddress));
    }

    @ApiOperation(value = "查询详情")
    @GetMapping("/info/{id}")
    public String info(@PathVariable("id") Long id){

        return R.ok(userAddressService.getById(id));
    }

    @ApiOperation(value = "添加用户地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户 id"),
            @ApiImplicitParam(name = "contactName", value = "联系人姓名"),
            @ApiImplicitParam(name = "contactPhone", value = "联系人电话"),
            @ApiImplicitParam(name = "province", value = "省"),
            @ApiImplicitParam(name = "city", value = "市"),
            @ApiImplicitParam(name = "county", value = "区"),
            @ApiImplicitParam(name = "contactAddress", value = "地址"),
            @ApiImplicitParam(name = "longitude", value = "经度"),
            @ApiImplicitParam(name = "latitude", value = "纬度"),
            @ApiImplicitParam(name = "isDefaultAddress", value = "是否为默认地址,0为不是，1为是")
    })
    @PostMapping("/save")
    public String save(UserAddress userAddress){

        System.err.println(userAddress);

        if (userAddress.getIsDefaultAddress()){
            UserAddress userAddress1 = userAddressService.queryDefault(userAddress.getUid());
            if (ObjectUtil.isNotNull(userAddress1)){
                userAddress1.setIsDefaultAddress(Boolean.FALSE);
                userAddress1.updateById();
            }
        }

        return R.ok(userAddressService.save(userAddress));
    }

    @ApiOperation(value = "修改用户地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id"),
            @ApiImplicitParam(name = "uid", value = "用户 uid"),
            @ApiImplicitParam(name = "contactName", value = "联系人姓名"),
            @ApiImplicitParam(name = "contactPhone", value = "联系人电话"),
            @ApiImplicitParam(name = "province", value = "省"),
            @ApiImplicitParam(name = "city", value = "市"),
            @ApiImplicitParam(name = "county", value = "区"),
            @ApiImplicitParam(name = "contactAddress", value = "地址"),
            @ApiImplicitParam(name = "longitude", value = "经度"),
            @ApiImplicitParam(name = "latitude", value = "纬度"),
            @ApiImplicitParam(name = "isDefaultAddress", value = "是否为默认地址,0为不是，1为是")
    })
    @PostMapping("/update")
    public String update(UserAddress userAddress){

        System.err.println(userAddress);
        if (userAddress.getIsDefaultAddress()){
            UserAddress userAddress1 = userAddressService.queryDefault(userAddress.getUid());
            if (ObjectUtil.isNotNull(userAddress1)){
                userAddress1.setIsDefaultAddress(Boolean.FALSE);
                userAddress1.updateById();
            }
        }

        return R.ok(userAddressService.updateById(userAddress));
    }

    @ApiOperation(value = "查询默认收货地址")
    @ApiImplicitParam(name = "uid", value = "用户id")
    @GetMapping("/queryDefault")
    public String queryDefault(Long uid){

        return R.ok(userAddressService.queryDefault(uid));
    }

    @ApiOperation(value = "删除地址")
    @ApiImplicitParam(name = "id", value = "主键id")
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){

        return R.ok(userAddressService.removeById(id));
    }

}

