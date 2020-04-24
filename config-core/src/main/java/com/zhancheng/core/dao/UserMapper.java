package com.zhancheng.core.dao;

import com.zhancheng.core.dto.Test;
import com.zhancheng.core.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Repository
public interface UserMapper extends BaseMapper<User> {


    /**
     * 获得用户总人数
     *
     * @return
     */
    @Select("SELECT count(*) count FROM zc_user zu where zu.`status`=1")
    Integer selectCount();

    /**
     * 获取当前一个月用户的注册人数
     *
     * @return
     */
    @Select("SELECT DATE_FORMAT(zu.gmt_create, '%Y-%m-%d') AS date, COUNT(*) AS count FROM zc_user zu WHERE DATEDIFF(zu.gmt_create, NOW()) <= 0 AND DATEDIFF(zu.gmt_create, NOW()) > -30 GROUP BY DATE_FORMAT(zu.gmt_create, '%Y-%m-%d');")
    List< HashMap<String, Integer>> selectCountByMonth();


    Map userTest(Long uid);

}
