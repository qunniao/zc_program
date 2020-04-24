package com.zhancheng.core.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author tangchao
 */
@Data
public class SkuInfo implements Serializable {

    private KeyAndValue level1;
    private KeyAndValue level2;
    private KeyAndValue level3;
    private List<Sku> sku;
}
