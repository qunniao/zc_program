package com.zhancheng.core.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.zhancheng.core.entity.ProductGuigeName;
import com.zhancheng.core.entity.ProductGuigeSku;
import com.zhancheng.core.entity.ProductGuigeValue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author tangchao
 */
public class AddSkuUtil {

    public static void addName(List<ProductGuigeName> productGuigeNameList, Map<String, Object> map, Integer level, Long pid){

        if (CollectionUtil.isEmpty(map)){
            return;
        }
        ProductGuigeName productGuigeName = new ProductGuigeName();
        String levelKey = map.get("key").toString();
        productGuigeName.setGName(levelKey).setLevel(level).setPid(pid);
        productGuigeNameList.add(productGuigeName);
    }

    public static void addValue(List<ProductGuigeValue> productGuigeValueList, String[] valueArrauy, Integer level,
                                List<ProductGuigeName> productGuigeNameList){

        if (ArrayUtil.isEmpty(valueArrauy)){
            return;
        }

        ProductGuigeValue productGuigeValue;

        for (ProductGuigeName productGuigeName : productGuigeNameList) {

            if (level.equals(productGuigeName.getLevel())){
                Integer nid = productGuigeName.getNid();

                for (String value : valueArrauy) {

                    productGuigeValue = new ProductGuigeValue();
                    productGuigeValue.setNid(nid);
                    productGuigeValue.setGValue(value);
                    System.out.println(productGuigeValue);
                    productGuigeValueList.add(productGuigeValue);
                }
            }
        }
    }

    public static String[] valueArray(Map<String, Object> map){

        if (CollectionUtil.isEmpty(map)){
            return null;
        }

        Object value = map.get("value");
        if (ObjectUtil.isNull(value)){
            return null;
        }

        return value.toString().split(",");
    }

    public static void addSku(List<Map<String, Object>> list, List<ProductGuigeSku> productGuigeSkuList) {

        System.out.println(productGuigeSkuList);
        if (CollectionUtil.isEmpty(list)){
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            BigDecimal price = new BigDecimal(map.get("price").toString());
            Integer num = Integer.parseInt(map.get("num").toString());
            productGuigeSkuList.get(i).setPrice(price).setNum(num);
        }
    }



}
