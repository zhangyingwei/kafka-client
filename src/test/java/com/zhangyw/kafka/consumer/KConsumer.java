package com.zhangyw.kafka.consumer;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

import com.zhangyw.kafka.producer.client.ConsumerClient;

public class KConsumer extends ConsumerClient{
	Logger logger = Logger.getLogger(KConsumer.class);
	@Override
	public void msg(String jsonStr) {
		JSONObject json = null;
		try {
			json = JSONObject.fromObject(jsonStr);
		} catch (Exception e) {
			logger.info(e);
		}
	}
}
