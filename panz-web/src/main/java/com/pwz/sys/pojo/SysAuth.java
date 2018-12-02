package com.pwz.sys.pojo;

import com.pwz.back.po.BaseEntity;

import java.io.Serializable;

public class SysAuth  extends BaseEntity implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_auth.auth_id
     *
     * @mbggenerated
     */
    private String authId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_auth.auth_name
     *
     * @mbggenerated
     */
    private String authName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_auth.parent_auth_id
     *
     * @mbggenerated
     */
    private String parentAuthId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_auth.create_name
     *
     * @mbggenerated
     */
    private String createName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_auth.create_time
     *
     * @mbggenerated
     */
    private Integer createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_auth.modify_name
     *
     * @mbggenerated
     */
    private String modifyName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_auth.modify_time
     *
     * @mbggenerated
     */
    private Integer modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_auth.is_deleted
     *
     * @mbggenerated
     */
    private Boolean isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_auth.beizu
     *
     * @mbggenerated
     */
    private String beizu;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_auth
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_auth.auth_id
     *
     * @return the value of sys_auth.auth_id
     *
     * @mbggenerated
     */
    public String getAuthId() {
        return authId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_auth.auth_id
     *
     * @param authId the value for sys_auth.auth_id
     *
     * @mbggenerated
     */
    public void setAuthId(String authId) {
        this.authId = authId == null ? null : authId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_auth.auth_name
     *
     * @return the value of sys_auth.auth_name
     *
     * @mbggenerated
     */
    public String getAuthName() {
        return authName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_auth.auth_name
     *
     * @param authName the value for sys_auth.auth_name
     *
     * @mbggenerated
     */
    public void setAuthName(String authName) {
        this.authName = authName == null ? null : authName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_auth.parent_auth_id
     *
     * @return the value of sys_auth.parent_auth_id
     *
     * @mbggenerated
     */
    public String getParentAuthId() {
        return parentAuthId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_auth.parent_auth_id
     *
     * @param parentAuthId the value for sys_auth.parent_auth_id
     *
     * @mbggenerated
     */
    public void setParentAuthId(String parentAuthId) {
        this.parentAuthId = parentAuthId == null ? null : parentAuthId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_auth.create_name
     *
     * @return the value of sys_auth.create_name
     *
     * @mbggenerated
     */
    public String getCreateName() {
        return createName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_auth.create_name
     *
     * @param createName the value for sys_auth.create_name
     *
     * @mbggenerated
     */
    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_auth.create_time
     *
     * @return the value of sys_auth.create_time
     *
     * @mbggenerated
     */
    public Integer getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_auth.create_time
     *
     * @param createTime the value for sys_auth.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_auth.modify_name
     *
     * @return the value of sys_auth.modify_name
     *
     * @mbggenerated
     */
    public String getModifyName() {
        return modifyName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_auth.modify_name
     *
     * @param modifyName the value for sys_auth.modify_name
     *
     * @mbggenerated
     */
    public void setModifyName(String modifyName) {
        this.modifyName = modifyName == null ? null : modifyName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_auth.modify_time
     *
     * @return the value of sys_auth.modify_time
     *
     * @mbggenerated
     */
    public Integer getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_auth.modify_time
     *
     * @param modifyTime the value for sys_auth.modify_time
     *
     * @mbggenerated
     */
    public void setModifyTime(Integer modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_auth.is_deleted
     *
     * @return the value of sys_auth.is_deleted
     *
     * @mbggenerated
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_auth.is_deleted
     *
     * @param isDeleted the value for sys_auth.is_deleted
     *
     * @mbggenerated
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_auth.beizu
     *
     * @return the value of sys_auth.beizu
     *
     * @mbggenerated
     */
    public String getBeizu() {
        return beizu;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_auth.beizu
     *
     * @param beizu the value for sys_auth.beizu
     *
     * @mbggenerated
     */
    public void setBeizu(String beizu) {
        this.beizu = beizu == null ? null : beizu.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_auth
     *
     * @mbggenerated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysAuth other = (SysAuth) that;
        return (this.getAuthId() == null ? other.getAuthId() == null : this.getAuthId().equals(other.getAuthId()))
            && (this.getAuthName() == null ? other.getAuthName() == null : this.getAuthName().equals(other.getAuthName()))
            && (this.getParentAuthId() == null ? other.getParentAuthId() == null : this.getParentAuthId().equals(other.getParentAuthId()))
            && (this.getCreateName() == null ? other.getCreateName() == null : this.getCreateName().equals(other.getCreateName()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getModifyName() == null ? other.getModifyName() == null : this.getModifyName().equals(other.getModifyName()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()))
            && (this.getBeizu() == null ? other.getBeizu() == null : this.getBeizu().equals(other.getBeizu()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_auth
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAuthId() == null) ? 0 : getAuthId().hashCode());
        result = prime * result + ((getAuthName() == null) ? 0 : getAuthName().hashCode());
        result = prime * result + ((getParentAuthId() == null) ? 0 : getParentAuthId().hashCode());
        result = prime * result + ((getCreateName() == null) ? 0 : getCreateName().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getModifyName() == null) ? 0 : getModifyName().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        result = prime * result + ((getBeizu() == null) ? 0 : getBeizu().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_auth
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", authId=").append(authId);
        sb.append(", authName=").append(authName);
        sb.append(", parentAuthId=").append(parentAuthId);
        sb.append(", createName=").append(createName);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyName=").append(modifyName);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", beizu=").append(beizu);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}