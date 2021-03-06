package com.pwz.sys.dao;


import com.pwz.back.dao.BaseDao;
import com.pwz.sys.pojo.SysUserAuth;

public interface SysUserAuthMapper  extends BaseDao<SysUserAuth, String> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_auth
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String userAuthId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_auth
     *
     * @mbggenerated
     */
    int insert(SysUserAuth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_auth
     *
     * @mbggenerated
     */
    int insertSelective(SysUserAuth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_auth
     *
     * @mbggenerated
     */
    SysUserAuth selectByPrimaryKey(String userAuthId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_auth
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SysUserAuth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_auth
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysUserAuth record);
}