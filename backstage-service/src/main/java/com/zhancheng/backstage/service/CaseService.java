package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
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
     * Query list
     *
     * @param page
     * @return page
     */
    IPage<Case> queryList(Page page);

    /**
     * 添加案例表
     * @param casePO 案例数据
     * @return
     */
    Boolean saveInfo(Case casePO);

    /**
     * 更新信息
     *
     * @param casePO 案例信息
     * @return boolean
     */
    Boolean updateInfo(Case casePO);

    /**
     * 查询详情
     *
     * @param cid 案例id
     * @return case
     */
    Case queryInfo(Long cid);

}
