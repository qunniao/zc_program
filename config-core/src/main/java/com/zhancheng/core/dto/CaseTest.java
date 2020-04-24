package com.zhancheng.core.dto;


import lombok.Data;

import java.math.BigDecimal;

/**
 * @author tangchao
 */
@Data
public class CaseTest {

    private Long cid;
    private Long sid;
    private Long tid;
    private String name;
    private String cover;
    private BigDecimal price;
    private String mobileDetail;
    private String content;
    private Boolean recommend;
    private Integer aid;
    private String attrName;
    private String attrValue;
    private Long bid;
    private Long pid;

}
