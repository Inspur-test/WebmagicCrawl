package com.inspur.kafka;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

public class ConsumerDemo {
	private final ConsumerConnector consumer;
    private final String topic;
    private ExecutorService executor;
    private PageProcessor crawlName;
    private Pipeline Pipeline;
    public ConsumerDemo(String a_topic,PageProcessor crawlName,Pipeline Pipeline) {
//        consumer = Consumer.createJavaConsumerConnector(createConsumerConfig(a_zookeeper,a_groupId));
        InputStream is=this.getClass().getResourceAsStream("consumer.properties");
        Properties properties=new Properties();
        try {
			properties.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        consumer = Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));
        this.topic = a_topic;
        this.crawlName=crawlName;
        this.Pipeline=Pipeline;
    }
 
    public void shutdown() {
        if (consumer != null)
            consumer.shutdown();
        if (executor != null)
            executor.shutdown();
    }
 
    public void run(int numThreads) {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, new Integer(numThreads));
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer
                .createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);
 
        // now launch all the threads
        executor = Executors.newFixedThreadPool(numThreads);
 
        // now create an object to consume the messages
        //
        int threadNumber = 0;
        for (final KafkaStream<byte[], byte[]> stream : streams) {
            executor.submit(new ConsumerMsgTask(stream, threadNumber,crawlName,Pipeline));
            threadNumber++;
        }
    }
 
}
