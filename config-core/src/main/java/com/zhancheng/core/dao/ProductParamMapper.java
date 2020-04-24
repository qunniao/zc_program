package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.entity.ProductParam;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 产品参数表
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-09-17 16:15:33
 */
@Repository
public interface ProductParamMapper extends BaseMapper<ProductParam> {

    /**
     * 获取产品参数
     *
     * @param pid 产品id
     * @return list
     */
    List<ProductParam> getInfo(Long pid);
	
}
