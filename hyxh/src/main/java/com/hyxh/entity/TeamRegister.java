package com.hyxh.entity;

public class TeamRegister {
	private String teamId;
	private String state;
	private String registerDate;
	private String PhotosCode;
	private String vipSource;
	private String vipIdentity ;
	private String mobilephone;
	
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getPhotosCode() {
		return PhotosCode;
	}
	public void setPhotosCode(String photosCode) {
		PhotosCode = photosCode;
	}
	public String getVipSource() {
		return vipSource;
	}
	public void setVipSource(String vipSource) {
		this.vipSource = vipSource;
	}
	public String getVipIdentity() {
		return vipIdentity;
	}
	public void setVipIdentity(String vipIdentity) {
		this.vipIdentity = vipIdentity;
	}
	
}
