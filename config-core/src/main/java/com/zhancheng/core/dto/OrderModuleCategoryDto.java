package com.zhancheng.core.dto;

import lombok.Data;

/**
 * @author tangchao
 */
@Data
public class OrderModuleCategoryDto {

    private Long id;
    private Integer uid;
    private Long pid;
    private String moduleJson;
    private String duration;
    private String totalPrice;
    private Integer num;
}
