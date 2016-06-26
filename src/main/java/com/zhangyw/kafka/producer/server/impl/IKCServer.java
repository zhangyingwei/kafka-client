package com.zhangyw.kafka.producer.server.impl;

import java.util.List;

import com.zhangyw.kafka.producer.client.ConsumerClient;
import com.zhangyw.kafka.producer.exception.KPSException;

public interface IKCServer {
	public void receive(ConsumerClient consumer) throws KPSException;
}
