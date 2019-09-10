package com.suncj.demo.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.MessageListener;

/**
 * 通用kafka监听器 <br>
 * 作者：suncj<br>
 * 版本：1.0<br>
 * 创建日期：2018年3月21日<br>
 */
public class CustomMessageListener implements MessageListener<String, String> {
	private static Logger logger = LoggerFactory.getLogger(CustomMessageListener.class);

	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		logger.info("received key: {}, value: {}", data.key(), data.value());
	}
}
