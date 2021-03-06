/**
 * @(#)com.linker.resmaster.base.dao.BaseDao.java 厦门中兴软创软件有限公司
 * @date 2014-10-28
 */
package com.pwz.back.dao;


import com.pwz.back.po.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @version 1.0
 * @since 2014-10-28 下午04:48:49
 */

public interface BaseDao<T extends BaseEntity, PK extends Serializable> {

    void add(T entity);

    void update(T entity);

    void delete(PK pk);

    T getEntityById(PK pk);

    /**
     * 通过查询条件得到结果列表
     * @param params
     * @return
     */
    List<T> findList(Map<String, Object> params);

}
