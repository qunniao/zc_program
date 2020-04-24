package com.zhancheng.core.dto;

import lombok.Data;

import java.util.List;

/**
 * @author tangchao
 */
@Data
public class SkuDto {
    private String skuId;
    private String price;
    private String num;
    private String skuName;
    private List<ProductParamDto> sKuParam;
}
