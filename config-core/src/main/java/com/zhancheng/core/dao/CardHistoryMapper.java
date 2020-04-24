package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.core.dto.CardHistoryDto;
import com.zhancheng.core.entity.CardHistory;
import com.zhancheng.core.vo.CardHistoryVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 名片浏览,点赞记录表 Mapper 接口
 * </p>
 *
 * @author tangchao
 * @since 2019-08-08
 */
@Repository
public interface CardHistoryMapper extends BaseMapper<CardHistory> {

    /**
     * 获取数量
     *
     * @param type   0是点赞 1是浏览
     * @param cardId 名片Id
     * @return
     */
    @Select("SELECT sum(num) FROM zc_card_history where type= #{type} AND user_card_id = #{cardId}")
    Integer getCount(Integer type, Long cardId);


    /**
     * 获取浏览用户信息
     *
     * @param type   0是点赞 1是浏览
     * @param cardId 名片Id
     * @return
     */
    @Select("SELECT zu.cover, zu.nickname, zch.id, zch.uid FROM zc_card_history zch LEFT JOIN zc_user zu ON zch.uid = zu.uid WHERE type = ${type} AND zch.user_card_id =${cardId} ORDER BY zch.gmt_modified DESC limit 0,5")
    List<Map<String, Object>> getCardHistory(Integer type, String cardId);


    /**
     * 返回当前名片的访问人数
     *
     * @param cardId
     * @return
     */
    @Select("SELECT sum(num) sum FROM zc_card_history zch where zch.user_card_id=#{cardId} ")
    Integer getSum(String cardId);


    /**
     * 返回所有名片的访问人数
     *
     * @return
     */
    @Select("SELECT SUM(num) sum FROM zc_card_history ")
    Integer getAllSum();


    /**
     * 查询访客列表
     *
     * @param page 分页信息
     * @param cardHistoryDto
     * @return list
     */
    IPage<CardHistoryVo> queryList(Page page,  @Param("ch") CardHistoryDto cardHistoryDto);
}
