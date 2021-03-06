package com.zhangyw.kafka.producer.server;

import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import kafka.producer.KeyedMessage;

import com.zhangyw.kafka.producer.client.ProducerExecuter;
import com.zhangyw.kafka.producer.client.ProducerPool;
import com.zhangyw.kafka.producer.exception.KPSException;
import com.zhangyw.kafka.producer.server.impl.IKPServer;

public class KPServer extends Thread implements IKPServer{
	
	Logger logger = Logger.getLogger(KPServer.class);
	
	private ProducerExecuter executer = null;
	private String defaultTopic;
	
	public String getDefaultTopic() {
		return defaultTopic;
	}
	public void setDefaultTopic(String defaultTopic) {
		this.defaultTopic = defaultTopic;
	}
	public KPServer(){
		this.executer = new ProducerExecuter(ProducerPool.getIS());
	}
	public KPServer(String configPath){
		ProducerPool pool = ProducerPool.getIS();
		pool.initConf(configPath);
		this.executer = new ProducerExecuter(pool);
	}
	public KPServer(Properties properties){
		ProducerPool pool = ProducerPool.getIS();
		pool.initConf(properties);
		this.executer = new ProducerExecuter(pool);
	}

	public void send(String topic, String message) throws KPSException {
		this.executer.send(new KeyedMessage(topic, message));
		logger.info("topic:"+topic+"-message:"+message);
	}

	public void send(List<String> topics, String message) throws KPSException {
		for(String topic:topics){
			this.executer.send(new KeyedMessage(topic, message));
		}
	}

	public void send(String topic, List<String> messages) throws KPSException {
		for(String message:messages){
			this.executer.send(new KeyedMessage(topic, message));
		}
	}
	public void sendByDefultTopic(String message) throws KPSException {
		if(this.defaultTopic!=null){
			this.executer.send(new KeyedMessage(this.defaultTopic, message));
		}else{
			throw new KPSException("defaultTopic is null");
		}
	}
}
