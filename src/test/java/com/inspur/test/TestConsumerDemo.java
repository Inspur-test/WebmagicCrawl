package com.inspur.test;

import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import com.inspur.kafka.ConsumerDemo;
import com.inspur.webmagic.crawl.HtmlContentCrawl;
import com.inspur.webmagic.pipeline.SaveHtmlContentPipeline;

public class TestConsumerDemo {
	 public static void main(String[] arg) {
	        String[] args = { "Html_urls", "10" };
	        String topic = args[0];
	        int threads = Integer.parseInt(args[1]);
	        PageProcessor crawlName=new HtmlContentCrawl();
	       Pipeline Pipeline=new SaveHtmlContentPipeline();
	        ConsumerDemo demo = new ConsumerDemo(topic,crawlName,Pipeline);
	        demo.run(threads);
	        try {
	            Thread.sleep(10000);
	        } catch (InterruptedException ie) {
	 
	        }
	        demo.shutdown();
	    }
}
