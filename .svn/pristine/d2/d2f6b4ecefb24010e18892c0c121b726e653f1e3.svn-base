package com.inspur.chrome.weibo;


import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class WeiboCrawl  implements PageProcessor{
	private Site site=Site.me().setRetryTimes(10).setTimeOut(10000);
	public static void main(String[] args){
		
		WeiboDownloader WeiboDownloader=new WeiboDownloader("/Users/Diablo/Downloads/chromedriver","/Users/Diablo/Downloads/weibocookie");
		WeiboDownloader.setLogin_url("http://weibo.com/login.php");
		Spider.create(new WeiboCrawl())
		.thread(1)
		.setDownloader(WeiboDownloader)
		.addPipeline(new FilePipeline("/Users/Diablo/Downloads"))
		.addUrl("http://d.weibo.com/")
		.run();
	}

	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		page.addTargetRequests(page.getHtml().links().regex("http://d\\.weibo\\.com.*").all());
		page.putField("content", page.getHtml());
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}

}
