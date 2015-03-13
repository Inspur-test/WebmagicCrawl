package com.inspur.mybatis;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.openqa.selenium.Cookie;

import com.inspur.beans.HtmlCookieBean;
import com.inspur.imethod.IhtmlCookie;
import com.inspur.util.CookieUtil;

public class HtmlCookieMapper {
	static SqlSessionFactory sqlSessionFactory = null;   
    static {   
       sqlSessionFactory = MysqlUtil.getSqlSessionFactory();   
    }
    
    public void UpdateCookie(HtmlCookieBean HtmlCookieBean){
    	SqlSession sqlSession = sqlSessionFactory.openSession(); 
    	try{
    		IhtmlCookie IhtmlCookie=sqlSession.getMapper(IhtmlCookie.class);
    		IhtmlCookie.UpdateCookie(HtmlCookieBean);
    		sqlSession.commit();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	finally{
    		sqlSession.close();  
    	}
    }
    public List<HtmlCookieBean> GetCountInfo(){
    	SqlSession sqlSession = sqlSessionFactory.openSession(); 
    	try{
    		IhtmlCookie IhtmlCookie=sqlSession.getMapper(IhtmlCookie.class);
    		List<HtmlCookieBean> HtmlCookieBean=IhtmlCookie.GetCountInfo();
    		return HtmlCookieBean;
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	finally{
    		sqlSession.close();
    	}
    	return null;
    }
    @SuppressWarnings("static-access")
	public static void main(String[] args){
    	HtmlCookieMapper HtmlCookieMapper=new HtmlCookieMapper();
    	List<HtmlCookieBean> HtmlCookieBean=HtmlCookieMapper.GetCountInfo();
    	CookieUtil CookieUtil=new CookieUtil();
    	Set<Cookie> cookie=CookieUtil.getCookieFromFile("/Users/Diablo/Downloads/weibocookie");
    	byte[] bytes=CookieUtil.serializeObject(cookie);
    	HtmlCookieBean.get(0).setCookie(bytes);
    	HtmlCookieMapper.UpdateCookie(HtmlCookieBean.get(0));
    	try {
			Set<Cookie> cookie_=CookieUtil.deserializeObject(HtmlCookieBean.get(0).getCookie());
			for(Cookie c:cookie){
				System.out.println(c.toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
}
