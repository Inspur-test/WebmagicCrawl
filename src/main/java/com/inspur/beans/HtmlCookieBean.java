package com.inspur.beans;

public class HtmlCookieBean {
public HtmlCookieBean() {
		super();
		// TODO Auto-generated constructor stub
	}
public HtmlCookieBean(String account_id, String password,
			byte[] cookie, String website) {
		super();
		this.account_id = account_id;
		this.password = password;
		this.cookie = cookie;
		this.website = website;
	}
private String account_id;
private String password;
private byte[] cookie;
private String website;

public String getAccount_id() {
	return account_id;
}
public void setAccount_id(String account_id) {
	this.account_id = account_id;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public byte[] getCookie() {
	return cookie;
}
public void setCookie(byte[] cookie) {
	this.cookie = cookie;
}
public String getWebsite() {
	return website;
}
public void setWebsite(String website) {
	this.website = website;
}
}
