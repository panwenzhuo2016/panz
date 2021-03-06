package com.pwz.sys.dao;


import com.pwz.back.dao.BaseDao;
import com.pwz.sys.pojo.SysGroupRole;

public interface SysGroupRoleMapper  extends BaseDao<SysGroupRole, String> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_group_role
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String groupRoleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_group_role
     *
     * @mbggenerated
     */
    int insert(SysGroupRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_group_role
     *
     * @mbggenerated
     */
    int insertSelective(SysGroupRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_group_role
     *
     * @mbggenerated
     */
    SysGroupRole selectByPrimaryKey(String groupRoleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_group_role
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SysGroupRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_group_role
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysGroupRole record);
}