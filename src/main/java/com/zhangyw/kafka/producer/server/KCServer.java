package com.zhangyw.kafka.producer.server;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.producer.KeyedMessage;

import com.zhangyw.kafka.producer.client.ConsumerClient;
import com.zhangyw.kafka.producer.client.ProducerExecuter;
import com.zhangyw.kafka.producer.client.ProducerPool;
import com.zhangyw.kafka.producer.exception.KPSException;
import com.zhangyw.kafka.producer.server.impl.IKCServer;
import com.zhangyw.kafka.producer.server.impl.IKPServer;

public class KCServer extends Thread implements IKCServer{
	
	Logger logger = Logger.getLogger(KCServer.class);
	private ConsumerConnector consumer;
	private Properties properties;
	public KCServer(Properties properties){
		this.properties = properties;
		initConsumer();
	}
	private void initConsumer(){
		this.consumer = Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));
	}
	public void receive(ConsumerClient consumerClient) throws KPSException {
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(consumerClient.getTopic(), 1);
		Map<String, List<KafkaStream<byte[], byte[]>>> messageStreams = consumer.createMessageStreams(topicCountMap);
		KafkaStream<byte[], byte[]> stream = messageStreams.get(consumerClient.getTopic()).get(0);
		ConsumerIterator<byte[], byte[]> iterator = stream.iterator();
		while (iterator.hasNext()) {
			String message;
			try {
				message = new String(iterator.next().message(),"utf-8");
				consumerClient.msg(message);
			} catch (UnsupportedEncodingException e) {
				logger.info(e);
			}
		}
	}
}
