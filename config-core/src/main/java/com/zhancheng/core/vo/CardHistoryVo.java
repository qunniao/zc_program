package com.zhancheng.core.vo;

import com.zhancheng.core.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tangchao
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CardHistoryVo extends User {

    private Long id;
    private Long userCardId;
    private Integer num;
    private Integer type;
}
