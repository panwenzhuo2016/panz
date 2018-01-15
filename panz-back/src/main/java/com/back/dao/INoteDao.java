package com.back.dao;


import com.back.po.Note;

import java.util.List;
import java.util.Map;

/**
 * @author 潘文卓
 *         Mon Nov 13 17:11:00 CST 2017
 */
public interface INoteDao extends BaseDao<Note, String> {

    /**
     * 查询数据列表
     *
     * @param params 查询条件
     * @return 数据列表
     */
    List<Note> findDataGrid(Map<String, Object> params);

    /**
     * 查询数据列表 数量
     *
     * @param params 查询条件
     * @return 数量
     */
    int countDataGrid(Map<String, Object> params);
}
