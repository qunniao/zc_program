package com.zhancheng.core.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author tangchao
 */
@Data
public class Sku {
    private BigDecimal price;
    private Integer num;
}
