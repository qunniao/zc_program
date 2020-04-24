package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.entity.ProductGuigeValue;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 产品规格值
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-09-11 10:14:26
 */
@Repository
public interface ProductGuigeValueMapper extends BaseMapper<ProductGuigeValue> {

    Boolean deleteByNid(@Param("nidList") List<Integer> nidList);
	
}
