package com.zhancheng.applet.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.applet.service.CaseService;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dao.CaseMapper;
import com.zhancheng.core.dto.PageDto;
import com.zhancheng.core.entity.Case;
import com.zhancheng.core.vo.CaseListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 案例表 前端控制器
 * </p>
 *
 * @author tangchao
 * @since 2019-08-15
 */
@Api(tags = "案例相关")
@RestController
@RequestMapping("{version}/case")
public class CaseController {

    @Autowired
    private CaseService caseService;

    @Autowired
    private CaseMapper caseMapper;


    @Verify
    @GetMapping
    @ApiOperation(value = "获取案例")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bid", value = "二级分类Id,如果为空则查询所有案例"),
            @ApiImplicitParam(name = "word", value = "搜索关键词,不填搜索所有"),
            @ApiImplicitParam(name = "page", value = "页数", required = true)

    })
    public String getCase(String bid, Integer page, String word) {
        if (page == null) {
            return R.fail(CodeMsg.PARAMETER_NULL);
        }
        word = StringUtils.trimToEmpty(word);
        return caseService.getCase(bid, page, word);
    }

    @ApiOperation(value = "获取案例详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "案例id"),
            @ApiImplicitParam(name = "uid", value = "用户id")
    })
    @GetMapping("/info")
    public String getInfo(Long cid, Long uid){

        return R.ok(caseService.queryInfo(cid, uid));
    }
    @ApiOperation(value = "查询案例列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tid", value = "分类Id,如果为空则查询所有案例"),
            @ApiImplicitParam(name = "word", value = "搜索关键词,不填搜索所有")
    })
    @GetMapping("/list")
    public String getList(PageDto<Case> pageDto, Long tid, String keyword){

        System.out.println(tid);
        System.out.println(keyword);
        IPage<CaseListVo> list = caseMapper.getList(pageDto.getPage(), tid, keyword);
        list.getRecords();
        System.out.println(list.getRecords());

        return R.ok(caseMapper.getList(pageDto.getPage(), tid, keyword));

    }
}

