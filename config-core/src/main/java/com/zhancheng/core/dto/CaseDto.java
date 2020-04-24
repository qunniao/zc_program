package com.zhancheng.core.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author tangchao
 */
@Data
public class CaseDto {

    private Long cid;
    private Long sid;
    private Long tid;
    private String name;
    private String cover;
    private BigDecimal price;
    private String mobileDetail;
    private String content;
    private Boolean recommend;
    private Boolean isDeleted;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;

}
