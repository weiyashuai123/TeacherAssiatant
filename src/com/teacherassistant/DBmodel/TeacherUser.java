package com.teacherassistant.DBmodel;

import cn.bmob.v3.BmobObject;

public class TeacherUser extends BmobObject {

	/**
	 * JavaBean对象教师用户
	 * 
	 * @author 魏亚帅
	 * @date 2016年11月26日
	 */
	private static final long serialVersionUID = 1L;

	private String username = "";
	private String password = "";
	private String phoneNumber = "";
	private String realname = "";
	private String school = "";

	public TeacherUser(){
		
	}
	public TeacherUser(String username, String password, String phoneNumber) {
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.username = username;
	}

	public TeacherUser(String username, String password, String phoneNumber, String realname, String school) {
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.username = username;
		this.school = school;
		this.realname = realname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

}
