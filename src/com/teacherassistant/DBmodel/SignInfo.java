package com.teacherassistant.DBmodel;

import cn.bmob.v3.BmobObject;

public class SignInfo extends BmobObject{

	private static final long serialVersionUID = 1L;
	
	private String stuName;
	private String state;
	private double latitude;
	private double longititude;
	private String stuid;
	private String classname;
	private String teacher;
	private String stuRealname;
	
	
	public String getStuRealname() {
		return stuRealname;
	}
	public void setStuRealname(String stuRealname) {
		this.stuRealname = stuRealname;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongititude() {
		return longititude;
	}
	public void setLongititude(double longititude) {
		this.longititude = longititude;
	}
	public String getStuid() {
		return stuid;
	}
	public void setStuid(String stuid) {
		this.stuid = stuid;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	
}
