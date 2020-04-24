package com.zhancheng.core.dto;

import lombok.Data;

import java.util.Set;

/**
 * @author tangchao
 */
@Data
public class Test {

    private Long uid;
    private Set<String> moduleJson;
    private Set<String> duration;
}
