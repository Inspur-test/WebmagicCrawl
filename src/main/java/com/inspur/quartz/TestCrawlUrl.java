package com.inspur.quartz;

import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Spider;

import com.inspur.webmagic.crawl.HtmlUrlCrawl;
import com.inspur.webmagic.pipeline.SaveHtmlUrlPipeline;
@Component("TestCrawlUrl")
public class TestCrawlUrl {
	public  void run(){
			HtmlUrlCrawl htmlurlcrawl=new HtmlUrlCrawl();
			Spider.create(htmlurlcrawl)
			.addUrl("http://bbs.tianya.cn")
			.thread(1)
//			.addPipeline(new SaveHtmlUrlPipeline())
			.run();
	}
}
