package com.zhancheng.backstage.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.entity.ProductGuigeSku;

import java.util.List;
import java.util.Map;

/**
 * 产品sku表
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-09-11 10:14:26
 */
public interface ProductGuigeSkuService extends IService<ProductGuigeSku> {

   Boolean addSku(String skuInfo, Long pid);

   List<Map<String, Object>> queryList(Long pid);

}

