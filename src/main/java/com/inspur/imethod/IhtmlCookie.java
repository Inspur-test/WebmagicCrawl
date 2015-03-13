package com.inspur.imethod;

import java.util.List;

import com.inspur.beans.HtmlCookieBean;

public interface IhtmlCookie {
	
	List<HtmlCookieBean> GetCountInfo();

	void UpdateCookie(HtmlCookieBean HtmlCookieBean);
}
