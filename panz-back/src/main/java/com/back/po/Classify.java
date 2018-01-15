package com.back.po;
import java.io.Serializable;


/**
 *
 *
 *
 **/

/**
 * @author 潘文卓
 * 时间:Mon Nov 13 17:00:38 CST 2017
 * 表 classify
 **/public class Classify extends BaseEntity  implements Serializable {

	/****/
	private String id;
	/****/
	private String classifyName;
	/****/
	private String sort;
	/**
	 * 每个分类对应的数量
	 */
	private int num;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setClassifyName(String classifyName){
		this.classifyName = classifyName;
	}

	public String getClassifyName(){
		return this.classifyName;
	}

	public void setSort(String sort){
		this.sort = sort;
	}

	public String getSort(){
		return this.sort;
	}

}
