package com.teacherassistant.DBmodel;

import java.util.Date;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;

public class Classes extends BmobObject {

	/**
	 * ∞‡º∂–≈œ¢
	 */
	private static final long serialVersionUID = 1L;

	private String id = "";
	private String name = "";
	private int peoplenumber = 0;
	private String state = "";
	private String teacher = "";
	private BmobDate starttime;
	
	public Classes() {

	}

	public Classes( String name,  String teacher) {
		this.name = name;
		this.teacher = teacher;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPeoplenumber() {
		return peoplenumber;
	}

	public void setPeoplenumber(int peoplenumber) {
		this.peoplenumber = peoplenumber;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public BmobDate getStarttime() {
		return starttime;
	}

	public void setStarttime(BmobDate starttime) {
		this.starttime = starttime;
	}

	
}
