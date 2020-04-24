package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 产品sku表
 * zc_product_guige_sku 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-09-11 10:14:26
 */
@Data
@Accessors(chain = true)
@TableName("zc_product_guige_sku")
public class ProductGuigeSku extends Model<ProductGuigeSku> {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "sku_id", type = IdType.AUTO)
	private Long skuId;
	/**
	 * 
	 */
	private Long pid;
	/**
	 * 规格值id
	 */
	private String sp1;
	/**
	 * 规格值id
	 */
	private String sp2;
	/**
	 * 规格值id
	 */
	private String sp3;
	/**
	 * 
	 */
	private Integer num;
	/**
	 * 
	 */
	private BigDecimal price;
	/**
	 * 图片
	 */
	private String pic;

	@Override
	protected Serializable pkVal() {
		return this.skuId;
}
}
