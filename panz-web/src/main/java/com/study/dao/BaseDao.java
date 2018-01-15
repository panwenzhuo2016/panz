/**
 * @(#)com.linker.resmaster.base.dao.BaseDao.java
 * 厦门中兴软创软件有限公司
 * @date 2014-10-28
 */
package com.study.dao;


import com.study.po.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
 * @version 1.0
 * @since 2014-10-28 下午04:48:49
 */

public interface BaseDao<T extends BaseEntity, PK extends Serializable> {

	public void add(T entity);

	public void update(T entity);

	public void delete(PK pk);

	public T getEntityById(PK pk);

	/**
	 * 通过查询条件得到结果列表
	 * @param params
	 * @return
	 */
	public List<T> findList(Map<String, Object> params);

}
