package com.zhancheng.core.vo;

import com.zhancheng.core.entity.News;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author BianShuHeng
 * @decription
 * @project NewsListVo
 * @date 2019/9/29 10:00
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class NewsListVo extends News {
    private String typeName;
}
