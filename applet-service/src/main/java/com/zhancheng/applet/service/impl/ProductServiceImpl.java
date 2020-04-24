package com.zhancheng.applet.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.applet.service.ProductService;
import com.zhancheng.core.commom.MapFactory;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dao.*;
import com.zhancheng.core.dto.PageDto;
import com.zhancheng.core.entity.*;
import com.zhancheng.core.vo.ProductListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private ProductTypeMapper productTypeMapper;

    @Autowired
    private OrderProductMapper orderProductMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductImageMapper productImageMapper;
    @Autowired
    private ProductDetailMobileMapper productDetailMobileMapper;
    @Autowired
    private ProductDetailPcMapper productDetailPcMapper;

    @Autowired
    private ProductGuigeNameMapper productGuigeNameMapper;

    @Autowired
    private ProductGuigeSkuMapper productGuigeSkuMapper;

    @Autowired
    private ProductParamMapper productParamMapper;


    @Override
    public List<Map<String, Object>> getProduct(Long pid, String star, String end) {
        Map<String, Object> hashMap = MapFactory.getHashMap();
        hashMap.put("pid", pid);
        StringBuffer ptId = new StringBuffer("-1");
        List<ProductType> productTypes = productTypeMapper.selectByMap(hashMap);
        for (ProductType productType : productTypes) {
            if ("-1".equals(ptId.toString())) {
                ptId = new StringBuffer(" ");
            } else {
                ptId = ptId.append(",");
            }
            ptId = ptId.append(productType.getTid());
        }
        List<Map<String, Object>> productIntros = productMapper.getProductIntroByRecommend(ptId.toString(), "desc" + " LIMIT " + star + "," + end);
        for (Map<String, Object> map : productIntros) {
            Long temp = Long.parseLong(map.get("pid").toString());
//            添加销售数量
            int salesNum = orderProductMapper.getSalesNum(temp);
            map.put("salesNum", salesNum);
        }
        return productIntros;


    }

    @Override
    public List<Map<String, Object>> getProduct(Long pid, String desc, String star, String end) {
        Map<String, Object> hashMap = MapFactory.getHashMap();
        hashMap.put("pid", pid);
        StringBuffer ptId = new StringBuffer("-1");
        //一级分类查询所有二级分类的Id
        List<ProductType> productTypes = productTypeMapper.selectByMap(hashMap);
        for (ProductType productType : productTypes) {
            if ("-1".equals(ptId.toString())) {
                ptId = new StringBuffer(" ");
            } else {
                ptId = ptId.append(",");
            }
            ptId = ptId.append(productType.getTid());
        }
        return getProduct(desc, star, end, ptId.toString());

    }

    @Override
    public List<Map<String, Object>> getProduct(String desc, String star, String end, String ptId) {
        List<Map<String, Object>> productIntros = productMapper.getProductIntro(ptId, desc + " LIMIT " + star + "," + end);
        for (Map<String, Object> map : productIntros) {
            Long pid = Long.parseLong(map.get("pid").toString());
//            添加销售数量
            int salesNum = orderProductMapper.getSalesNum(pid);
            map.put("salesNum", salesNum);
        }
        return productIntros;
    }

    @Override
    public String getProductInfo(Long pid, Integer type) {
        Map<String, Object> hashMap = MapFactory.getHashMap();
        Map<String, Object> result = new HashMap<>();
        hashMap.put("pid", pid);
        List<Product> products = productMapper.selectByMap(hashMap);
        if (products.size() <= 0) {
            throw new MyException(CodeMsg.QUERY_EMPTY);
        }
        Product product = products.get(0);
        result.put("product", product);
        List<ProductImage> productImages = productImageMapper.selectByMap(hashMap);
        result.put("images", productImages);
        if (type != 0) {
            List<ProductDetailPc> productDetailPcs = productDetailPcMapper.selectByMap(hashMap);
            result.put("detail", productDetailPcs);
        } else {
            List<ProductDetailMobile> productDetailMobiles = productDetailMobileMapper.selectByMap(hashMap);
            result.put("detail", productDetailMobiles);
        }
        return R.ok(result);
    }

    @Override
    public String searchProduct(String word, String star, String end, String desc) {
        List<Map<String, Object>> productIntros = productMapper.searchProduct(word, desc + " LIMIT " + star + "," + end);
        for (Map<String, Object> map : productIntros) {
            Long pid = Long.parseLong(map.get("pid").toString());
//            添加销售数量
            int salesNum = orderProductMapper.getSalesNum(pid);
            map.put("salesNum", salesNum);
        }
        return R.ok(productIntros);
    }

    @Override
    public List<ProductParam> getParam(Long pid) {

        return productParamMapper.getInfo(pid);
    }

    @Override
    public Map<String, Object> getSku(Long pid) {

        Map<String, Object> map = new HashMap<>(2);

        List<ProductGuigeName> productGuigeNameList = productGuigeNameMapper.queryList(pid);
        // sku 信息
        List<ProductGuigeSku> productGuigeSkuList = productGuigeSkuMapper.selectList(new QueryWrapper<ProductGuigeSku>()
                .eq("pid", pid));

        map.put("productGuigeNameList", productGuigeNameList);
        map.put("sku", productGuigeSkuList);
        return map;
    }

    @Override
    public IPage<ProductListVo> getList(PageDto pageDto, Long tid, Integer sortType) {

        String sort = "";
        if (ObjectUtil.isNull(sortType) || "1".equals(sortType.toString())){
            sort = "DESC";
        }else {
            sort = "ASC";
        }
        IPage<ProductListVo> productListVoIPage = baseMapper.queryList(pageDto.getPage(), tid, sort);

        List<ProductListVo> records = productListVoIPage.getRecords();

        // 填充主图
        if (CollectionUtil.isNotEmpty(records)){

            for (ProductListVo productListVo: records) {
                ProductImage productImage = productImageMapper.selectOne(new QueryWrapper<ProductImage>()
                        .eq("pid", productListVo.getPid())
                        .eq("is_deleted", 0)
                        .eq("is_cover", 0));
                if (ObjectUtil.isNotNull(productImage)){
                    productListVo.setCover(productImage.getUrl());
                }
            }
        }

        return productListVoIPage;
    }


}
