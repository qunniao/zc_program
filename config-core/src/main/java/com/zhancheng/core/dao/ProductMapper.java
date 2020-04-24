package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.core.dto.ProductDto;
import com.zhancheng.core.dto.ProductParamDto;
import com.zhancheng.core.dto.SkuDto;
import com.zhancheng.core.entity.Product;
import com.zhancheng.core.vo.ProductListVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 产品spu表 Mapper 接口
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Repository
public interface ProductMapper extends BaseMapper<Product> {

    @Select("SELECT pr.pid,pr.pt_id,pr.sid,pr.product_name,pr.product_intro,pr.price,pr.market_price,pr.price_unit,image.url from zc_product pr LEFT JOIN zc_product_image image on pr.pid = image.pid WHERE pr.pt_id in (${ptId}) and pr.is_deleted =0 and image.is_cover=1 ORDER BY price ${end}")
    List<Map<String, Object>> getProductIntro(@Param("ptId") String ptId, @Param("end") String end);

    @Select("SELECT pr.pid,pr.pt_id,pr.sid,pr.product_name,pr.product_intro,pr.price,pr.market_price,pr.price_unit,image.url from zc_product pr LEFT JOIN zc_product_image image on pr.pid = image.pid WHERE pr.product_name like '%${word}%' and pr.is_deleted =0 and image.is_cover=1 ORDER BY price ${end}")
    List<Map<String, Object>> searchProduct(@Param("word") String word, @Param("end") String end);


    /**
     * 按推荐排序
     *
     * @param ptId
     * @param end
     * @return
     */
    @Select("SELECT pr.pid,pr.pt_id,pr.sid,pr.product_name,pr.product_intro,pr.price,pr.market_price,pr.price_unit,image.url from zc_product pr LEFT JOIN zc_product_image image on pr.pid = image.pid WHERE pr.pt_id in (${ptId}) and pr.is_deleted =0 and image.is_cover=1 ORDER BY pr.is_recommend  ${end}")
    List<Map<String, Object>> getProductIntroByRecommend(@Param("ptId") String ptId, @Param("end") String end);


    /**
     * 获取产品数量
     *
     * @return
     */
    @Select(" SELECT count(*) count FROM zc_product")
    Integer getProductCount();

    /**
     * 后台管理产品列表
     *
     * @return
     */
    IPage<Map<String, Object>> getProduct(Page page);

    @Select("SELECT tb1.*,IFNULL(tb2.count,0) count from (SELECT zp.*,zpt.type_name type_name,zpi.url from zc_product zp LEFT JOIN zc_product_type zpt on zp.pt_id = zpt.tid LEFT JOIN zc_product_image zpi on zpi.pid = zp.pid and zpi.is_cover=1 where zp.is_deleted =0 AND zp.pid = #{pid}) tb1 LEFT JOIN (SELECT count(*) count, zop.spu_id FROM zc_order_product zop GROUP  BY zop.spu_id) tb2 on tb1.pid =tb2.spu_id")
    Map<String, Object> queryInfo(Long pid);

    ProductDto queryProductInfo(Long pid);

    /**
     * 查询手机端详情图 和 客户端详情
     * @param pid
     * @return
     */
    Product queryDetails(Long pid);

    List<ProductParamDto> queryProductParam(@Param("pid") Long pid,@Param("sKuId") Integer sKuId);

    SkuDto querySkuParam(@Param("pid") Long pid);

    IPage<ProductListVo> queryList(Page page, @Param("tid") Long tid, @Param("sort") String sort);

}
