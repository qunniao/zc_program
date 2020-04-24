package com.zhancheng.core.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author tangchao
 */
@Data
@ApiModel(value = "分页参数实体类")
public class PageDto<T> {

    @ApiModelProperty(name= "current", value = "当前页码", required = true)
    private Integer current;
    @ApiModelProperty(name= "size", value = "页容量", required = true)
    private Integer size = 10;

    public Page<T> getPage(){

        return new Page<T>(current, size);
    }

}
