package com.inspur.test;

import java.util.ArrayList;
import java.util.List;
import us.codecraft.webmagic.Spider;

import com.inspur.beans.HtmlUrlCrawlBean;
import com.inspur.mybatis.HtmlContentMapper;
import com.inspur.webmagic.crawl.HtmlContentCrawl;
import com.inspur.webmagic.pipeline.SaveHtmlContentPipeline;

public class TestCrawlContent {
	public static void main(String[] args){
		long start=System.currentTimeMillis();
		HtmlContentMapper HtmlContentMapper=new HtmlContentMapper();
		List<HtmlUrlCrawlBean> HtmlUrlCrawlBean=HtmlContentMapper.getURLInfo();
		List<String> urls=new ArrayList<String>();
		for(HtmlUrlCrawlBean h:HtmlUrlCrawlBean){
			urls.add(h.getChild_urls().trim());
			if(urls.size()>=1000){
				break;
			}
		}
		String[] urls_arr=new String[urls.size()];
	    urls_arr=urls.toArray(urls_arr);
		urls.clear();
		Spider.create(new HtmlContentCrawl())
		.addUrl(urls_arr)
		.addPipeline(new SaveHtmlContentPipeline())
		.thread(1)
		.run();
		long end=System.currentTimeMillis();
		System.out.println("time:   "+(end-start));
	}
}
