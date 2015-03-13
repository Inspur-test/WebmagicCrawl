package com.inspur.beans;

public class HtmlUpdateUrlCrawlBean {
	public HtmlUpdateUrlCrawlBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HtmlUpdateUrlCrawlBean(String url, String crawled,
			String last_crawl_time) {
		super();
		this.url = url;
		this.crawled = crawled;
		this.last_crawl_time = last_crawl_time;
	}
	private String url;
	private String crawled;
	private String last_crawl_time;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCrawled() {
		return crawled;
	}
	public void setCrawled(String crawled) {
		this.crawled = crawled;
	}
	public String getLast_crawl_time() {
		return last_crawl_time;
	}
	public void setLast_crawl_time(String last_crawl_time) {
		this.last_crawl_time = last_crawl_time;
	}
}
