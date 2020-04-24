package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.entity.ProductGuigeName;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 产品规格名称
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-09-11 10:14:26
 */
@Repository
public interface ProductGuigeNameMapper extends BaseMapper<ProductGuigeName> {

    /**
     * 查询 nid
     * @param pid
     * @return
     */
    @Select("SELECT nid FROM `zc_product_guige_name` WHERE pid = #{pid}")
    List<Integer> getNid(@Param("pid") Long pid);

    /**
     * 根据商品id查询规格名称
     * @param pid 商品id
     * @return List<ProductGuigeName>
     */
    List<ProductGuigeName> queryList(Long pid);

}
