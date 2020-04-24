package com.zhancheng.core.vo;

import lombok.Data;

/**
 * @author tangchao
 */
@Data
public class OrderCartVo {
   private Long cid;
   private Long sid;
   private Long uid;
   private Long skuId;
   private Long spuId;
   private String productName;
   private Integer productNum;
   private String productPrice;
   private String productImage;
   private String sp1;
   private String sp2;
   private String sp3;
   private String skuImage;
}
