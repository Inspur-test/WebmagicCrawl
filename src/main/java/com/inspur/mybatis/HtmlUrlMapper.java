package com.inspur.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.inspur.beans.HtmlUrlCrawlBean;
import com.inspur.imethod.IhtmlUrlCrawl;

/**   
*    
* 项目名称：StormCrawl   
* 类名称：HtmlUrlMapper   
* 类描述： 把url的信息写到hbase的方法实现
* 创建人：Diablo   
* 创建时间：2015年2月25日 下午1:51:14   
* 修改人：Diablo   
* 修改时间：2015年2月25日 下午1:51:14   
* 修改备注：   
* @version    
*    
*/
public class HtmlUrlMapper {
	static SqlSessionFactory sqlSessionFactory = null;   
    static {   
       sqlSessionFactory = PhoenixUtil.getSqlSessionFactory();   
    }   
    //把url的信息插入到hbase中
	public void insertHtmlUrl(String child_urls,String url,String crawled,String last_crawl_time){
		SqlSession sqlSession = sqlSessionFactory.openSession(); 
    	try {   
    		IhtmlUrlCrawl HtmlUrlMapper = sqlSession.getMapper(IhtmlUrlCrawl.class);
    		HtmlUrlCrawlBean htmlurlcrawlbean=new HtmlUrlCrawlBean(child_urls,url,crawled,last_crawl_time);
	        HtmlUrlMapper.insertHtmlUrl(htmlurlcrawlbean);
	        sqlSession.commit();
	       }catch(Exception e){
	    	   e.printStackTrace();
	       }
    	finally {   
	         sqlSession.close();   
	       }  
	}
	
}
