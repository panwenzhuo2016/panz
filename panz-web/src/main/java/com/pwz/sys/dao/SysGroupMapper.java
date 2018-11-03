package com.pwz.sys.dao;


import com.pwz.back.dao.BaseDao;
import com.pwz.sys.pojo.SysGroup;

public interface SysGroupMapper  extends BaseDao<SysGroup, String> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_group
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String groupId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_group
     *
     * @mbggenerated
     */
    int insert(SysGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_group
     *
     * @mbggenerated
     */
    int insertSelective(SysGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_group
     *
     * @mbggenerated
     */
    SysGroup selectByPrimaryKey(String groupId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_group
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SysGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_group
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysGroup record);
}