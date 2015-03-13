package com.inspur.kafka;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

public class ConsumerMsgTask implements Runnable{
	private KafkaStream<byte[], byte[]> m_stream;
    private List<String> list = Collections.synchronizedList(new ArrayList<String>());
    private PageProcessor crawlName;
    private Pipeline Pipeline;
    public ConsumerMsgTask(KafkaStream<byte[], byte[]> stream, int threadNumber,PageProcessor crawlName,Pipeline Pipeline) {
        m_stream = stream;
        this.crawlName=crawlName;
        this.Pipeline=Pipeline;
    }
	@Override
	public void run() {
		ConsumerIterator<byte[], byte[]> it = m_stream.iterator();
        while (it.hasNext()){
        	    list.add(new String(it.next().message()));
        		PageProcessor HtmlContentCrawl=crawlName;
        		String[] urls=new String[list.size()];
        		urls=list.toArray(urls);
        		list.clear();
        		Spider spider=Spider.create(HtmlContentCrawl);
        		spider.addPipeline(Pipeline);
        		spider.addUrl(urls);
        		spider.thread(1);
        		spider.run();
        		while(!spider.isExitWhenComplete()){
        			try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
        	}
	}
}
