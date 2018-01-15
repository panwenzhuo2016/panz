package com.study.service;


import com.study.po.Classify;

import java.util.List;
import java.util.Map;

/**
 * Created by 潘文卓 on Mon Nov 13 17:00:38 CST 2017
 */
public interface IClassifyService {

    /**
     * 新增
     * @param classify 实体
     */
    void add(Classify classify);

    /**
     * 修改
     * @param classify 实体
     */
    void update(Classify classify);

    /**
     * 删除
     * @param objId
     */
    void delete(String objId);

    /**
     * 通过主键 获取实体
     * @param objId 主键
     * @return 实体
     */
    Classify get(String objId);

    /**
     *  保存 新增修改共用
     * @param classify 实体
     */
    void save(Classify classify);

    List<Classify> findDataGrid(Map<String, Object> params);

}
