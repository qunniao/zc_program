package com.zhancheng.applet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.vo.CaseVo;
import com.zhancheng.core.entity.Case;

/**
 * <p>
 * 案例表 服务类
 * </p>
 *
 * @author tangchao
 * @since 2019-08-15
 */
public interface CaseService extends IService<Case> {

    /**
     * 返回案例页面的案例信息
     *
     * @param bid
     * @param page
     * @return
     */
    String getCase(String bid, Integer page,String word);

    /**
     * 查询案例详情
     *
     * @param cid 案例id
     * @param cid 案例id
     * @return case
     */
    CaseVo queryInfo(Long cid, Long uid);


}
