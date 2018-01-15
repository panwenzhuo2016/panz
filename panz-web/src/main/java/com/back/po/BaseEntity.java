/**
 * @(#)com.linker.resmaster.base.entity.BaseEntity.java
 * 厦门中兴软创软件有限公司
 * @date 2014-11-5
 */
package com.back.po;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @version 1.0
 * @since 2014-11-5 下午03:54:46
 */

public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 271553265351633230L;

	private String creator;//创建人
	
	private String modifier;//修改人
	
	private String createTime;//创建时间

	private String modifyTime;//修改时间
	
	private char deleteState;//删除标识位



	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreateTime() {
		if(StringUtils.isEmpty(createTime)){
			createTime=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		}
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyTime() {
		if(StringUtils.isEmpty(modifyTime)){
			modifyTime=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		}
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public char getDeleteState() {
		return deleteState;
	}

	public void setDeleteState(char deleteState) {
		this.deleteState = deleteState;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

}
