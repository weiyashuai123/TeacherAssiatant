package com.teacherassistant.DBmodel;

import cn.bmob.v3.BmobObject;

public class Job extends BmobObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String courseName;
	private String jobContent;
	private String teacher;
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getJobContent() {
		return jobContent;
	}
	public void setJobContent(String jobContent) {
		this.jobContent = jobContent;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	

}
