package com.zhancheng.core.dto;

import com.zhancheng.core.entity.Category;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 边书恒
 * @Title: CategoryDto
 * @ProjectName zc-program
 * @Description: TODO
 * @date 2019/8/28 20:44
 */
@Data
public class CategoryDto {

    private Long id;

    /**
     * 父类Id没有则为0
     */
    private Long cid;

    /**
     * 类目名称
     */
    private String typeName;

    private String superiorTypeName;

    /**
     * 类目图标地址
     */
    private String cover;

    /**
     * 一级类目,二级类目,三级类目
     */
    private Integer level;

    /**
     * 排序
     */
    private Integer sort;
    private Boolean isDeleted;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;

    private List<Category> category;
}
