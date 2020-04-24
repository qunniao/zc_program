package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.entity.ProductGuigeSku;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 产品sku表
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-09-11 10:14:26
 */
@Repository
public interface ProductGuigeSkuMapper extends BaseMapper<ProductGuigeSku> {

    List<Map<String, Object>> queryList(Long pid);
	
}
