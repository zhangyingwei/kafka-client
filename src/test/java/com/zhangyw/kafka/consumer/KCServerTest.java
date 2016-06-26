package com.zhangyw.kafka.consumer;

import java.util.Properties;

import com.zhangyw.kafka.producer.exception.KPSException;
import com.zhangyw.kafka.producer.server.KCServer;

public class KCServerTest {
	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put("zookeeper.connect", "192.168.1.108:2181");
		properties.put("metadata.broker.list", "192.168.1.108:9092");
		properties.put("group.id", "haha2");
		KCServer server = new KCServer(properties);
		try {
			server.receive(new KConsumer().setTopic("hermes-server"));
		} catch (KPSException e) {
			System.out.println(e.getMessage());
		}
	}
}
