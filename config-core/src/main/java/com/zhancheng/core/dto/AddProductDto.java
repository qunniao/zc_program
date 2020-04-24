package com.zhancheng.core.dto;

import com.zhancheng.core.entity.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tangchao
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AddProductDto extends Product {

    private String gName;
}
