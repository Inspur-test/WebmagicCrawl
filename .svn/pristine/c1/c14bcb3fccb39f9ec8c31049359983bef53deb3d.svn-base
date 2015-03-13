package com.inspur.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.inspur.beans.HtmlContentBean;
import com.inspur.beans.HtmlUpdateUrlCrawlBean;
import com.inspur.beans.HtmlUrlCrawlBean;
import com.inspur.imethod.IhtmlContentCrawl;

/**   
*    
* 项目名称：StormCrawl   
* 类名称：HtmlContentMapper   
* 类描述：  把网页内容写入到数据库的方法实现 
* 创建人：Diablo   
* 创建时间：2015年2月25日 下午1:50:36   
* 修改人：Diablo   
* 修改时间：2015年2月25日 下午1:50:36   
* 修改备注：   
* @version    
*    
*/
public class HtmlContentMapper {
	//创建hbase连接
	static SqlSessionFactory sqlSessionFactory = null;   
    static {   
       sqlSessionFactory = PhoenixUtil.getSqlSessionFactory();   
    } 
    //得到未被爬取过的url地址
    public List<HtmlUrlCrawlBean> getURLInfo(){
    	SqlSession sqlSession = sqlSessionFactory.openSession(); 
    	try {   
    		IhtmlContentCrawl HtmlContentMapper = sqlSession.getMapper(IhtmlContentCrawl.class);
    		List<HtmlUrlCrawlBean> HtmlUrlCrawlBeans=HtmlContentMapper.getURLInfo();
	        return HtmlUrlCrawlBeans;
	       }catch(Exception e){
	    	   e.printStackTrace();
	       }
    	finally {   
	           sqlSession.close();   
	       } 
    	return null;
    }
    //插入网页信息到hbase数据库
    public void insertContent(String url,String content,String last_crawl_time){
    	SqlSession sqlSession = sqlSessionFactory.openSession(); 
    	try{
    		IhtmlContentCrawl HtmlContentMapper = sqlSession.getMapper(IhtmlContentCrawl.class);
    		HtmlContentBean htmlcontentbean=new HtmlContentBean(content, url, last_crawl_time);
    		HtmlContentMapper.insertContent(htmlcontentbean);
    		sqlSession.commit();
    	}finally{
    		sqlSession.close();
    	}
    }
    //更新爬取数据库的url
    public void updateHtmlUrl(String url,String crawled,String last_crawl_time){
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {   
			IhtmlContentCrawl IhtmlContentCrawl = sqlSession.getMapper(IhtmlContentCrawl.class);
    		HtmlUpdateUrlCrawlBean HtmlUpdateUrlCrawlBean=new HtmlUpdateUrlCrawlBean(url,crawled,last_crawl_time);
    		IhtmlContentCrawl.updateHtmlUrl(HtmlUpdateUrlCrawlBean);
	        sqlSession.commit();
	       }catch(Exception e){
	    	   e.printStackTrace();
	       }
		finally {   
	           sqlSession.close();   
	       }  
		
	}
}
