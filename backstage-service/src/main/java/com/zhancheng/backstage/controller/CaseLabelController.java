package com.zhancheng.backstage.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.ObjectUtil;
import com.zhancheng.backstage.service.CaseLabelService;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.entity.CaseLabel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 案例标签 前端控制器
 * </p>
 *
 * @author tangchao
 * @since 2019-08-15
 */
@Api(tags = "案例分类")
@RestController
@RequestMapping("{version}/caseLabel")
public class CaseLabelController {
    @Autowired
    private CaseLabelService caseLabelService;

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "添加案例分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "案例id",required = true),
            @ApiImplicitParam(name = "labelName", value = "分类名称", required = true)
    })
    @PostMapping("/save")
    public String save(CaseLabel caseLabel) {

        return R.ok(caseLabelService.save(caseLabel));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "编辑案例分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bid", value = "主键id",required = true),
            @ApiImplicitParam(name = "pid", value = "案例id"),
            @ApiImplicitParam(name = "labelName", value = "分类名称")
    })
    @PostMapping("/update")
    public String update(CaseLabel caseLabel) {

        CaseLabel caseLabelQuery = caseLabelService.getById(caseLabel.getBid());

        if (ObjectUtil.isNull(caseLabelQuery)){
            throw new MyException(CodeMsg.QUERY_EMPTY);
        }
        CopyOptions copyOptions = new CopyOptions().setIgnoreNullValue(true);
        BeanUtil.copyProperties(caseLabel, caseLabelQuery, copyOptions);

        return R.ok(caseLabelService.updateById(caseLabelQuery));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "查询案例分类列表")
    @GetMapping("/list")
    public String list() {

        return R.ok(caseLabelService.queryList());
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "查询二级案例分类列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "案例id",required = true)
    })
    @GetMapping("/list/{pid}")
    public String secondList(@PathVariable Integer pid) {

        return R.ok(caseLabelService.secondList(pid));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "查询案例分类信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bid", value = "主键id",required = true)
    })
    @GetMapping("/info/{bid}")
    public String info(@PathVariable Integer bid) {
        return R.ok(caseLabelService.queryInfo(bid));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "删除案例标签")
    @ApiImplicitParam(name = "bIds", value = "多个或一个主键id",required = true)
    @DeleteMapping("/delete")
    public String delete(@RequestParam(value ="bIds") List<Long> bIds) {

        System.out.println(bIds);
        return R.ok(caseLabelService.removeByIds(bIds));
    }
}

