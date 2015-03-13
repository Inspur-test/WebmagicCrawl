package com.inspur.chrome.tieba;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class SimpleCrawlUrl  implements PageProcessor{
	private Site site=Site.me().setRetryTimes(10).setTimeOut(10000);
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		page.putField("content", page.getHtml());
		 if (page.getResultItems().get("content")==null){
	            //skip this page
	            page.setSkip(true);
	        }
		 page.addTargetRequests(page.getHtml().links().regex("http://tieba\\.baidu\\.com/f\\?kw=.*").all());
		 page.addTargetRequests(page.getHtml().links().regex("http://tieba\\.baidu\\.com/p.*").all());
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}
	public static void main(String[] args){
		Spider.create(new SimpleCrawlUrl())
		.addUrl("http://tieba.baidu.com")
		.setDownloader(new TiebaDownloader("/Users/Diablo/Downloads/chromedriver","/Users/Diablo/Downloads/tiebacookie"))
		.addPipeline(new FilePipeline("/Users/Diablo/Downloads"))
		.thread(1)
		.run();
	}
}
