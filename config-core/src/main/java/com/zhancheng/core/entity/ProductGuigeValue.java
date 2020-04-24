package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 产品规格值
 * zc_product_guige_value 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-09-11 10:14:26
 */
@Data
@Accessors(chain = true)
@TableName("zc_product_guige_value")
public class ProductGuigeValue extends Model<ProductGuigeValue> {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "vid", type = IdType.AUTO)
	private Integer vid;
	/**
	 * 
	 */
	private Integer nid;
	/**
	 * 规格值
	 */
	@TableField("g_value")
	private String gValue;

	@Override
	protected Serializable pkVal() {
		return this.vid;
}
}
