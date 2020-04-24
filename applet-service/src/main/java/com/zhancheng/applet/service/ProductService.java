package com.zhancheng.applet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.dto.PageDto;
import com.zhancheng.core.entity.Product;
import com.zhancheng.core.entity.ProductParam;
import com.zhancheng.core.vo.ProductListVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 产品spu表 服务类
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
public interface ProductService extends IService<Product> {

    /**
     * 获取首页商品简介显示
     *
     * @param pid  传入一级分类Id
     * @param star 从第几条数据开始(0)
     * @param end  到第几条数据结束(2)
     * @return
     */
    List<Map<String, Object>> getProduct(Long pid, String star, String end);



    /**
     * 获取商品简介显示
     *
     * @param desc 价格升序()还是降序(desc)
     * @param pid  传入一级分类Id
     * @param star 从第几条数据开始(0)
     * @param end  到第几条数据结束(2)
     * @return
     */
    List<Map<String, Object>> getProduct(Long pid, String desc, String star, String end);

    /**
     * 传入自定义参数获取商品简介
     *
     * @param desc 价格升序()还是降序(desc)
     * @param star 从第几条数据开始(0)
     * @param end  到第几条数据结束(2)
     * @param ptId 二级分类Id
     * @return
     */
    List<Map<String, Object>> getProduct(String desc, String star, String end, String ptId);

    /**
     * 获取商品的详细信息
     *
     * @param pid  商品Id
     * @param type
     * @return
     */
    String getProductInfo(Long pid, Integer type);


    /**
     * 模糊查询商品
     *
     * @param word 关键词
     * @param star 从第几条数据开始(0)
     * @param end  到第几条数据结束(10)
     * @param desc 是否排序
     * @return
     */
    String searchProduct(String word, String star, String end, String desc);

    /**
     *  获取产品参数
     * @param pid 产品id
     * @return
     */
    List<ProductParam> getParam(Long pid);

    /**
     *  获取产品参数
     * @param pid 产品id
     * @return
     */
    Map<String, Object> getSku(Long pid);

    /**
     *  获取产品参数
     * @param tid 分类id
     * @return
     */
    IPage<ProductListVo> getList(PageDto pageDto, Long tid, Integer sortType);


}
