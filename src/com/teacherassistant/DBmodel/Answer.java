package com.teacherassistant.DBmodel;

import cn.bmob.v3.BmobObject;

public class Answer extends BmobObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String qId;
	private String aAnswerer;
	private String aState;
	private String aContext;
	
	public String getqId() {
		return qId;
	}
	public void setqId(String qId) {
		this.qId = qId;
	}
	public String getaAnswerer() {
		return aAnswerer;
	}
	public void setaAnswerer(String aAnswerer) {
		this.aAnswerer = aAnswerer;
	}
	public String getaState() {
		return aState;
	}
	public void setaState(String aState) {
		this.aState = aState;
	}
	public String getaContext() {
		return aContext;
	}
	public void setaContext(String aContext) {
		this.aContext = aContext;
	}
	
	
	
}
