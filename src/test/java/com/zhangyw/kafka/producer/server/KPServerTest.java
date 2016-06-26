package com.zhangyw.kafka.producer.server;

import java.util.Properties;
import java.util.UUID;

import com.zhangyw.kafka.producer.exception.KPSException;

public class KPServerTest {
	public static void main(String[] args) throws KPSException{
//		final KPServer server = new KPServer("D:/hermes/kafka-producer/src/main/resources/conf.properties");
		Properties properties = new Properties();
		properties.put("producer.zookeeper.connect", "192.168.1.108:2181");
		properties.put("producer.metadata.broker.list", "192.168.1.108:9092");
		properties.put("producer.serializer.class", "kafka.serializer.StringEncoder");
		properties.put("producer.key.serializer.class", "kafka.serializer.StringEncoder");
		final KPServer server = new KPServer(properties);
		server.send("test", UUID.randomUUID().toString());
//		new Thread(new Runnable() {
//			public void run() {
//				while(true){
//					try {
//						server.send("test", UUID.randomUUID().toString());
//						Thread.sleep(1000);
//					} catch (KPSException e) {
//						e.printStackTrace();
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}).start();;
	}
}
