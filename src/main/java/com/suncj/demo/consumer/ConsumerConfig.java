package com.suncj.demo.consumer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.listener.config.ContainerProperties;

/**
 * kafka消费端配置 <br>
 * 版权：Copyright (c) 2015-2016<br>
 * 公司：北京活力天汇<br>
 * 作者：孙常军<br>
 * 版本：1.0<br>
 * 创建日期：2018年3月21日<br>
 */

@Configuration
@EnableKafka
public class ConsumerConfig {

	@Value("${kafka.consumer.servers}")
	private String servers;

	@Value("${kafka.consumer.enable.auto.commit}")
	private boolean enableAutoCommit;

	@Value("${kafka.consumer.session.timeout}")
	private String sessionTimeout;

	@Value("${kafka.consumer.auto.commit.interval}")
	private String autoCommitInterval;

	@Value("${kafka.consumer.group.id}")
	private String groupId;

	@Value("${kafka.consumer.topic}")
	private String topic;

	@Value("${kafka.consumer.auto.offset.reset}")
	private String autoOffsetReset;

	@Value("${kafka.consumer.concurrency}")
	private int concurrency;

	/**
	 * KafkaMessageListenerContainer： 从单个线程上的所有主题/分区接收所有消息
	
	@Bean(initMethod = "doStart")
	public KafkaMessageListenerContainer<String, String> kafkaMessageListenerContainer() {
		KafkaMessageListenerContainer<String, String> container = new KafkaMessageListenerContainer<>(consumerFactory(), containerProperties());
		return container;
	}
	
	*/

	/**
	 * ConcurrentMessageListenerContainer：
	 * 委托给1个或多个KafkaMessageListenerContainer以提供多线程消费。
	 * 通过container.setConcurrency(3)，来设置多个线程
	 */
	@Bean(initMethod = "doStart")
	public ConcurrentMessageListenerContainer<String, String> concurrentMessageListenerContainer() {
		ConcurrentMessageListenerContainer<String, String> container = new ConcurrentMessageListenerContainer<>(consumerFactory(), containerProperties());
		container.setConcurrency(concurrency);
		return container;

	}

	public ConsumerFactory<String, String> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs());
	}

	public ContainerProperties containerProperties() {
		ContainerProperties containerProperties = new ContainerProperties(topic);
		containerProperties.setMessageListener(messageListener());
		return containerProperties;
	}

	public Map<String, Object> consumerConfigs() {
		Map<String, Object> propsMap = new HashMap<>();
		propsMap.put(Config.BOOTSTRAP_SERVERS_CONFIG, servers);
		propsMap.put(Config.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
		propsMap.put(Config.AUTO_COMMIT_INTERVAL_MS_CONFIG, autoCommitInterval);
		propsMap.put(Config.SESSION_TIMEOUT_MS_CONFIG, sessionTimeout);
		propsMap.put(Config.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		propsMap.put(Config.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		propsMap.put(Config.GROUP_ID_CONFIG, groupId);
		propsMap.put(Config.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
		return propsMap;
	}

	public MessageListener<String, String> messageListener() {
		return new CustomMessageListener();
	}

}
