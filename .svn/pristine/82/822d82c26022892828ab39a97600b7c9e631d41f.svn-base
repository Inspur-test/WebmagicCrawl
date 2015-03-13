package com.inspur.kafka;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import com.inspur.beans.HtmlUrlCrawlBean;
import com.inspur.mybatis.HtmlContentMapper;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class ProducerUrls {
	public  ProducerUrls(String topic){
		InputStream is=this.getClass().getResourceAsStream("producer.properties");
		Properties properties=new Properties();
		try {
			properties.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ProducerConfig config=new ProducerConfig(properties);
		Producer<String, String> producer = new Producer<String, String>(config);
		 HtmlContentMapper HtmlContentMapper=new HtmlContentMapper();
		 List<HtmlUrlCrawlBean> HtmlUrlCrawlBean=HtmlContentMapper.getURLInfo();
		 for(HtmlUrlCrawlBean h:HtmlUrlCrawlBean){
				String key = h.getLast_crawl_time();
	            KeyedMessage<String, String> data = new KeyedMessage<String, String>(
	            		topic, key, h.getChild_urls());
	            producer.send(data);
			}
			HtmlUrlCrawlBean.clear();
			producer.close();
	}
}
