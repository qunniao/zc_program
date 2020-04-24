package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.entity.CaseLabel;

import java.util.List;

/**
 * <p>
 * 案例标签 服务类
 * </p>
 *
 * @author tangchao
 * @since 2019-08-15
 */
public interface CaseLabelService extends IService<CaseLabel> {

    List<CaseLabel> queryList();

    CaseLabel queryInfo(Integer bid);

    List<CaseLabel> secondList(Integer pid);

}
