package com.back.dao;

import com.back.po.TableNum;

import java.util.List;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018-4-9 13:11
 **/
public interface TableNumDao extends BaseDao<TableNum, String> {
    List<String> findAllTables();
}
