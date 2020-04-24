package com.zhancheng.core.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author tangchao
 */
@Data
public class ProductDto {
    private Integer pid;
    private Integer tid;
    private Integer superiorTid;
    private Integer mobileDid;
    private Integer pcdid;
    private String priceUnit;
    private BigDecimal marketPrice;
    private BigDecimal price;
    private String productName;
    private String typeName;
    private String superiorTypeName;
    private String carousel;
    private String pcContent;
    private String mobileContent;
    private String productIntro;
    private List<ProductParamDto> productParam;
    private SkuDto SkuDto;
}
