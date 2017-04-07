package com.teacherassistant.DBmodel;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

public class Question extends BmobObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String qName = "";
	private String qDescription = "";// 问题描述
	private String qProposer = "";// 问题提出者
	private int qAnswerNumber = 0;
	private String state;
	
	public String getqName() {
		return qName;
	}
	public void setqName(String qName) {
		this.qName = qName;
	}
	public String getqDescription() {
		return qDescription;
	}
	public void setqDescription(String qDescription) {
		this.qDescription = qDescription;
	}
	public String getqProposer() {
		return qProposer;
	}
	public void setqProposer(String qProposer) {
		this.qProposer = qProposer;
	}
	public int getqAnswerNumber() {
		return qAnswerNumber;
	}
	public void setqAnswerNumber(int qAnswerNumber) {
		this.qAnswerNumber = qAnswerNumber;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	

}
