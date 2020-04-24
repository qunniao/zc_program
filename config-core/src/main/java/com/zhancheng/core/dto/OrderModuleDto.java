package com.zhancheng.core.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author tangchao
 */
@Data
public class OrderModuleDto {

    private Integer orderModuleId;
    private Integer num;
    private BigDecimal totalPrice;
    private Date gmtCreate;
    private String productName;
    private String nickName;
    private String cover;

}
