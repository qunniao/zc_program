package com.zhancheng.core.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author tangchao
 */
@Data
public class CaseLikeVo {

    @ApiModelProperty(value = "收藏表id", name = "id")
    private Long id;

    @ApiModelProperty(value = "案例id", name = "cid")
    private Long cid;

    @ApiModelProperty(value = "用户id", name = "uid")
    private Long uid;

    @ApiModelProperty(value = "案例名称", name = "name")
    private String name;

    @ApiModelProperty(value = "案例图片", name = "cover")
    private String cover;

    @ApiModelProperty(value = "案例价格", name = "price")
    private BigDecimal price;

    @ApiModelProperty(value = "是否收藏", name = "isCollection")
    private Boolean isCollection;
}
