package com.my.member;

public class MemberVo {
	String id;
	String mName;
	String pwd;
	String email;
	String phone;
	
	String address;
	int    zipcode;
	String gender;
	
	public MemberVo() {}
	public MemberVo(String id, String mName, String pwd, String email, String phone) {
		this.id = id;
		this.mName = mName;
		this.pwd = pwd;
		this.email = email;
		this.phone = phone;
	}
	
	
	//getter & setter
	
	
	public String getId() {
		return id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	
}
