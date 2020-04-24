package com.zhancheng.core.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author BianShuHeng
 * @decription
 * @project ProductListVo
 * @date 2019/9/27 13:57
 */
@Data
public class ProductListVo {
    private Long pid;
    private Long ptId;
    private String productName;
    private String typeName;
    private String cover;
    private String priceUnit;
    private BigDecimal price;
    private BigDecimal marketPrice;
    private Integer salesNum;
    private Integer status;
}
