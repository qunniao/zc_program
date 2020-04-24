package com.zhancheng.core.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author BianShuHeng
 * @decription
 * @project CaseListVo
 * @date 2019/9/27 16:56
 */
@Data
public class CaseListVo {
    private Long cid;
    private Long tid;
    private String name;
    private String labelName;
    private String cover;
    private BigDecimal price;
    private String attrName;
    private String attrValue;
}
