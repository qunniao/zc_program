package com.zhancheng.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 产品规格名称
 * zc_product_guige_name 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-09-11 10:14:26
 */
@Data
@Accessors(chain = true)
@TableName("zc_product_guige_name")
public class ProductGuigeName extends Model<ProductGuigeName> {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "nid", type = IdType.AUTO)
	private Integer nid;
	/**
	 * 产品id
	 */
	private Long pid;
	/**
	 * 属性名
	 */
	@TableField("g_name")
	private String gName;
	/**
	 * 
	 */
	private Integer level;

	@TableField(exist = false)
	private List<ProductGuigeValue> productGuigeValueList;

	@Override
	protected Serializable pkVal() {
		return this.nid;
}
}
