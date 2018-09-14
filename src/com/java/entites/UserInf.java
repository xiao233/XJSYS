package com.java.entites;

import com.java.constants.CommonConstants;

public class UserInf {
	private String userSeq;
	private String userName;
	private String userPaw;
	private String userPawNew;
	private String sysid;
	private String backUrl;
	private String confirmFlag;
	private String codeImage;
	private String isLogin;
	
	
	
	public String getIsLogin() {
		if(isLogin==null || isLogin == "") {
			isLogin = CommonConstants.IS_LOGIN;
		}
		return isLogin;
	}
	public void setIsLogin(String isLogin) {
		this.isLogin = isLogin;
	}
	public String getCodeImage() {
		return codeImage;
	}
	public void setCodeImage(String codeImage) {
		this.codeImage = codeImage;
	}
	public String getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(String userSeq) {
		this.userSeq = userSeq;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPaw() {
		return userPaw;
	}
	public void setUserPaw(String userPaw) {
		this.userPaw = userPaw;
	}
	public String getUserPawNew() {
		return userPawNew;
	}
	public void setUserPawNew(String userPawNew) {
		this.userPawNew = userPawNew;
	}
	public String getSysid() {
		return sysid;
	}
	public void setSysid(String sysid) {
		this.sysid = sysid;
	}
	public String getBackUrl() {
		return backUrl;
	}
	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}
	public String getConfirmFlag() {
		return confirmFlag;
	}
	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}
	
	
}
