package com.inspur.beans;

/**   
*    
* 项目名称：StormCrawl   
* 类名称：HtmlContentBean   
* 类描述：网页内容实体   
* 创建人：Diablo   
* 创建时间：2015年2月25日 下午1:41:45   
* 修改人：Diablo   
* 修改时间：2015年2月25日 下午1:41:45   
* 修改备注：   
* @version    
*    0.1
*/
public class HtmlContentBean {

	private String content;
	private String url;
	private String last_crawl_time;
	
	public HtmlContentBean(String content, String url, String last_crawl_time) {
		super();
		this.content = content;
		this.url = url;
		this.last_crawl_time = last_crawl_time;
	}
	
	public HtmlContentBean(){
		super();
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getLast_crawl_time() {
		return last_crawl_time;
	}
	public void setLast_crawl_time(String last_crawl_time) {
		this.last_crawl_time = last_crawl_time;
	}
}
