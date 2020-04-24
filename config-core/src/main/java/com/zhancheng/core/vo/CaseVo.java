package com.zhancheng.core.vo;

import com.zhancheng.core.entity.Case;
import com.zhancheng.core.entity.CaseAttr;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author tangchao
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CaseVo extends Case {

    @ApiModelProperty(value = "属性信息", name = "caseAttr")
    private List<CaseAttr> caseAttr;

    @ApiModelProperty(value = "点赞数量", name = "likeNum")
    private Integer likeNum;

    @ApiModelProperty(value = "浏览数量", name = "watchNum")
    private Integer watchNum;

    @ApiModelProperty(value = "是否收藏", name = "favorite")
    private Boolean isCollection;

    private Long caseLikeId;
}
