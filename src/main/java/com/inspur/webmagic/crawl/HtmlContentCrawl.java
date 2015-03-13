package com.inspur.webmagic.crawl;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class HtmlContentCrawl implements PageProcessor{
	private Site site=Site.me().setRetryTimes(10).setTimeOut(10000);
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		page.putField("content", page.getHtml());
		 if (page.getResultItems().get("content")==null){
	            //skip this page
	            page.setSkip(true);
	        }
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}

}
