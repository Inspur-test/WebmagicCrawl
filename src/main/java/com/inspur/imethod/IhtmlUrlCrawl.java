package com.inspur.imethod;

import com.inspur.beans.HtmlUrlCrawlBean;

/**   
*    
* 项目名称：StormCrawl   
* 类名称：IhtmlUrlCrawl   
* 类描述：   把url地址存放到hbase的方法接口
* 创建人：Diablo   
* 创建时间：2015年2月25日 下午1:42:53   
* 修改人：Diablo   
* 修改时间：2015年2月25日 下午1:42:53   
* 修改备注：   
* @version    
*    
*/
public interface IhtmlUrlCrawl {
	public void insertHtmlUrl(HtmlUrlCrawlBean htmlurlcrawlbean);
}
