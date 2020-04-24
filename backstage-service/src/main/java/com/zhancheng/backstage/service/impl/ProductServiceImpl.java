package com.zhancheng.backstage.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.ProductGuigeSkuService;
import com.zhancheng.backstage.service.ProductService;
import com.zhancheng.core.dao.*;
import com.zhancheng.core.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 产品spu表 服务实现类
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductImageMapper productImageMapper;

    @Autowired
    private ProductGuigeSkuService productGuigeSkuService;

    @Autowired
    private ProductGuigeNameMapper productGuigeNameMapper;

    @Autowired
    private ProductGuigeValueMapper productGuigeValueMapper;

    @Autowired
    private ProductGuigeSkuMapper productGuigeSkuMapper;

    @Autowired
    private ProductParamMapper productParamMapper;

    @Override
    public IPage<Map<String, Object>> getProduct(long pageNum, long size) {
        IPage<Map<String, Object>> product = productMapper.getProduct(new Page(pageNum, size));
        return product;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateProduct(Map<String, Object> map) {

        System.err.println(map);

        String jsonStr = JSONUtil.toJsonStr(map.get("product"));

        // 产品信息
        Product product = JSONUtil.toBean(jsonStr, Product.class);
        // 电脑端
        String detailMobile = JSONUtil.toJsonStr(map.get("productDetailMobile"));
        // 图片
        JSONArray image = JSONUtil.parseArray(map.get("productImage"));
        // 手机端
        String detailPc = JSONUtil.toJsonStr(map.get("productDetailPc"));

        JSONArray param = JSONUtil.parseArray(map.get("param"));

        System.err.println(detailMobile);
        System.err.println(image);
        System.err.println(detailPc);
        System.err.println(param);

        int i = baseMapper.updateById(product);

        Long pid = product.getPid();

        String skuInfoStr = JSONUtil.toJsonStr(map.get("skuInfo"));

        if (ObjectUtil.isNotEmpty(image)) {
            productImageMapper.delete(new QueryWrapper<ProductImage>().eq("pid", pid));
            insertProductImage(image, pid);
        }

        if (ObjectUtil.isNotEmpty(param)) {
            productParamMapper.delete(new QueryWrapper<ProductParam>().eq("pid", pid));
            insertProductParam(param, pid);
        }

        if (StrUtil.isNotBlank(detailMobile)) {
            ProductDetailMobile productDetailMobile = JSONUtil.toBean(detailMobile, ProductDetailMobile.class);
            productDetailMobile.updateById();
        }
        if (StrUtil.isNotBlank(detailPc)) {
            ProductDetailPc productDetailPc = JSONUtil.toBean(detailPc, ProductDetailPc.class);
            productDetailPc.updateById();
        }

        // 添加sku信息
        if (StrUtil.isNotBlank(skuInfoStr)) {

            List<Integer> nidList = productGuigeNameMapper.getNid(product.getPid());

            if (CollectionUtil.isNotEmpty(nidList)) {
                productGuigeNameMapper.deleteBatchIds(nidList);

                productGuigeValueMapper.deleteByNid(nidList);

                productGuigeSkuService.remove(new QueryWrapper<ProductGuigeSku>().eq("pid", product.getPid()));
            }

            productGuigeSkuService.addSku(skuInfoStr, product.getPid());
        }

        return i > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insertProduct(Map<String, Object> map) {

        String jsonStr = JSONUtil.toJsonStr(map.get("product"));

        // 产品信息
        Product product = JSONUtil.toBean(jsonStr, Product.class);
        // 图片 数组
        JSONArray image = JSONUtil.parseArray(map.get("productImage"));
        // 电脑端
        String detailMobile = JSONUtil.toJsonStr(map.get("productDetailMobile"));
        // 手机端
        String detailPc = JSONUtil.toJsonStr(map.get("productDetailPc"));

        // 产品参数数组
        JSONArray param = JSONUtil.parseArray(map.get("param"));

        System.err.println(detailMobile);
        System.err.println(image);
        System.err.println(detailPc);
        System.err.println(param);

        // 保存产品
        baseMapper.insert(product);
        Long pid = product.getPid();

        // 添加图片和产品参数
        insertProductImage(image, pid);
        insertProductParam(param, pid);

        // 保存移动端详情
        if (StrUtil.isNotBlank(detailMobile)) {
            ProductDetailMobile productDetailMobile = JSONUtil.toBean(detailMobile, ProductDetailMobile.class);
            productDetailMobile.setPid(pid);
            productDetailMobile.insert();
        }
        // 保存电脑端详情
        if (StrUtil.isNotBlank(detailPc)) {
            ProductDetailPc productDetailPc = JSONUtil.toBean(detailPc, ProductDetailPc.class);
            productDetailPc.setPid(pid);
            productDetailPc.insert();
        }

        String skuInfoStr = JSONUtil.toJsonStr(map.get("skuInfo"));

        // 添加sku信息
        if (StrUtil.isNotBlank(skuInfoStr)) {
            productGuigeSkuService.addSku(skuInfoStr, product.getPid());
        }

        return true;
    }

    @Override
    public Map<String, Object> queryDetails(Long pid) {

        Map<String, Object> map = new HashMap<>(2);

        Product product = baseMapper.queryDetails(pid);

        List<ProductGuigeName> productGuigeNameList = productGuigeNameMapper.queryList(pid);

        // sku 信息
        List<Map<String, Object>> sku = productGuigeSkuMapper.queryList(pid);

        map.put("product", product);
        map.put("productGuigeNameList", productGuigeNameList);
        map.put("sku", sku);
        return map;
    }

    /**
     * 保存 图片
     *
     * @param image 图片数组
     * @param pid   产品id
     */
    private void insertProductImage(JSONArray image, Long pid) {


        for (int i = 0; i < image.size(); i++) {
            JSONObject jsonObject = image.getJSONObject(i);
            ProductImage productImage = JSONUtil.toBean(jsonObject, ProductImage.class);
            productImage.setPid(pid);
            productImage.insert();
        }
    }

    /**
     * 添加产品参数
     *
     * @param param 参数
     * @param pid   产品id
     */
    private void insertProductParam(JSONArray param, Long pid) {

        // 添加产品参数
        for (int i = 0; i < param.size(); i++) {
            JSONObject jsonObject = param.getJSONObject(i);
            ProductParam productParam = JSONUtil.toBean(jsonObject, ProductParam.class);
            productParam.setPid(pid);
            productParam.insert();
        }
    }


}
