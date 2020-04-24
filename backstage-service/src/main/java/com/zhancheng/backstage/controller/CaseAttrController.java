package com.zhancheng.backstage.controller;


import com.zhancheng.backstage.service.CaseAttrService;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.entity.CaseAttr;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 案例属性名称和值 前端控制器
 * </p>
 *
 * @author tangchao
 * @since 2019-08-15
 */
@Api(tags = "案例属性名称和值")
@RestController
@RequestMapping("{version}/caseAttr")
public class CaseAttrController {

    @Autowired
    private CaseAttrService caseAttrService;

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "添加案例属性")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "案例id"),
            @ApiImplicitParam(name = "attrName", value = "属性名"),
            @ApiImplicitParam(name = "attrValue", value = "属性值"),
    })
    @PostMapping
    public String save(CaseAttr caseAttr) {
        caseAttrService.save(caseAttr);

        return R.ok();
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "删除案例属性")
    @ApiImplicitParam(name = "aid", value = "主键id")
    @DeleteMapping("/{aid}")
    public String delete(@PathVariable("aid") Integer aid) {

        return R.ok(caseAttrService.removeById(aid));
    }
}