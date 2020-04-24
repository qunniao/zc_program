package com.zhancheng.core.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author tangchao
 */
@Data
public class ModuleCategoryDto{

    private Long moduleCid;
    private String moduleName;
    private BigDecimal price;
    private Integer labor;
    private Integer categoryId;
    private Integer categoryCid;
    private String typeName;
    private String cover;

}
