package com.back.po;
import java.io.Serializable;


/**
 * 
 * 
 * 
 **/

/**
 * @author 潘文卓  
 * 时间:Mon Nov 13 17:10:59 CST 2017
 * 表 note
 **/public class Note extends BaseEntity  implements Serializable {

	/****/
	private String id;
	/****/
	private String classifyId;
	/****/
	private String content;
	/****/
	private String title;


	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setClassifyId(String classifyId){
		this.classifyId = classifyId;
	}

	public String getClassifyId(){
		return this.classifyId;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return this.content;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return this.title;
	}

}
