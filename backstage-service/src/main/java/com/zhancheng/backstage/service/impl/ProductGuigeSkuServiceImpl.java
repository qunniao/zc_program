package com.zhancheng.backstage.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.ProductGuigeNameService;
import com.zhancheng.backstage.service.ProductGuigeSkuService;
import com.zhancheng.backstage.service.ProductGuigeValueService;
import com.zhancheng.core.dao.ProductGuigeSkuMapper;
import com.zhancheng.core.entity.ProductGuigeName;
import com.zhancheng.core.entity.ProductGuigeSku;
import com.zhancheng.core.entity.ProductGuigeValue;
import com.zhancheng.core.util.AddSkuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 产品sku表
 *
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-09-11 10:14:26
 */

@Service
public class ProductGuigeSkuServiceImpl extends ServiceImpl<ProductGuigeSkuMapper, ProductGuigeSku> implements ProductGuigeSkuService {

    @Autowired
    private ProductGuigeNameService productGuigeNameService;

    @Autowired
    private ProductGuigeValueService productGuigeValueService;

    @Override
    public Boolean addSku(String skuInfo, Long pid) {
        if (StrUtil.isBlank(skuInfo)) {
            return Boolean.FALSE;
        }
        JSONObject jsonObject = JSONUtil.parseObj(skuInfo);
        Map<String, Object> level1 = (Map<String, Object>) jsonObject.get("level1");
        Map<String, Object> level2 = (Map<String, Object>) jsonObject.get("level2");
        Map<String, Object> level3 = (Map<String, Object>) jsonObject.get("level3");
        List<Map<String, Object>> skuList = (List<Map<String, Object>>) jsonObject.get("sku");

        String[] level1Array = AddSkuUtil.valueArray(level1);
        String[] level2Array = AddSkuUtil.valueArray(level2);
        String[] level3Array = AddSkuUtil.valueArray(level3);

        List<ProductGuigeName> productGuigeNameList = new ArrayList<>();
        List<ProductGuigeValue> productGuigeValueList = new ArrayList<>();

        // 添加 属性名
        AddSkuUtil.addName(productGuigeNameList, level1, 1, pid);
        AddSkuUtil.addName(productGuigeNameList, level2, 2, pid);
        AddSkuUtil.addName(productGuigeNameList, level3, 3, pid);

        productGuigeNameService.saveBatch(productGuigeNameList);

        AddSkuUtil.addValue(productGuigeValueList, level1Array, 1, productGuigeNameList);
        System.out.println(productGuigeValueList);
        AddSkuUtil.addValue(productGuigeValueList, level2Array, 2, productGuigeNameList);
        System.out.println(productGuigeValueList);
        AddSkuUtil.addValue(productGuigeValueList, level3Array, 3, productGuigeNameList);
        System.out.println(productGuigeValueList);
        productGuigeValueService.saveBatch(productGuigeValueList);
        System.out.println(productGuigeValueList);
        // 添加 属性值

        List<ProductGuigeSku> productGuigeSkuList = new ArrayList<>();
        ProductGuigeSku productGuigeSku;

        if (CollectionUtil.isNotEmpty(level3)) {
            for (String sp1 : level1Array) {
                for (String sp2 : level2Array) {
                    for (String sp3 : level3Array) {
                        productGuigeSku = new ProductGuigeSku();
                        productGuigeSku.setSp1(sp1).setSp2(sp2).setSp3(sp3).setPid(pid);
                        productGuigeSkuList.add(productGuigeSku);
                    }
                }
            }
        }
        if (CollectionUtil.isNotEmpty(level2) && CollectionUtil.isEmpty(level3)) {
            for (String sp1 : level1Array) {
                for (String sp2 : level2Array) {
                    productGuigeSku = new ProductGuigeSku();
                    productGuigeSku.setSp1(sp1).setSp2(sp2).setPid(pid);
                    productGuigeSkuList.add(productGuigeSku);
                }
            }
        }

        if (CollectionUtil.isNotEmpty(level1) && CollectionUtil.isEmpty(level2) && CollectionUtil.isEmpty(level3)) {
            for (String sp1 : level1Array) {
                productGuigeSku = new ProductGuigeSku();
                productGuigeSku.setSp1(sp1).setPid(pid);
                productGuigeSkuList.add(productGuigeSku);
            }
        }
        AddSkuUtil.addSku(skuList, productGuigeSkuList);
        this.saveBatch(productGuigeSkuList);

        return Boolean.TRUE;
    }

    @Override
    public List<Map<String, Object>> queryList(Long pid) {

        return baseMapper.queryList(pid);

    }
}
