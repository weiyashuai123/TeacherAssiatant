package com.teacherassistant.DBmodel;

import cn.bmob.v3.BmobObject;

public class SignRecord extends BmobObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String teacher;
	private String classname;
	private String namelist;
	private String simple;
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getNamelist() {
		return namelist;
	}
	public void setNamelist(String namelist) {
		this.namelist = namelist;
	}
	public String getSimple() {
		return simple;
	}
	public void setSimple(String simple) {
		this.simple = simple;
	}
	
	
	

}
