package com.inspur.webmagic.crawl;

import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
@Component("HtmlUrlCrawl")
public class HtmlUrlCrawl  implements PageProcessor{
	private Site site=Site.me().setRetryTimes(10).setTimeOut(10000);
	public String regex="";
	public HtmlUrlCrawl(){
		super();
		this.regex=".*";
	}
	public HtmlUrlCrawl(String regex){
		this.regex=regex;
	}
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}

	public void process(Page page) {
		// TODO Auto-generated method stub
		page.addTargetRequests(page.getHtml().links().regex(regex).all());
		page.putField("urls", page.getHtml().links().regex(regex).all());
	}

}
