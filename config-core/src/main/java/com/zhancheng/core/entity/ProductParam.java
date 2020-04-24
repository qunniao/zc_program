package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 产品参数表
 * zc_product_param 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-09-17 16:15:33
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zc_product_param")
public class ProductParam extends Model<ProductParam> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	/**
	 * 产品id
	 */
	private Long pid;
	/**
	 * 属性名
	 */
	private String name;
	/**
	 * 属性值 
	 */
	@TableField(value = "value")
	private String values;
	/**
	 * 是否删除 0:未删除; 1:删除 
	 */
	@TableLogic
	private Boolean isDeleted;
	/**
	 * 创建时间
	 */
	private Date gmtCreate;
	/**
	 * 修改时间
	 */
	private Date gmtModified;

	@Override
	protected Serializable pkVal() {
		return this.id;
}
}
