package com.zhangyw.kafka.producer.client;

import net.sf.json.JSONObject;

public abstract class ConsumerClient {
	private String topic;
	public abstract void msg(String jsonStr);
	public ConsumerClient setTopic(String topic) {
		this.topic = topic;
		return this;
	}
	public String getTopic() {
		return topic;
	}
}
