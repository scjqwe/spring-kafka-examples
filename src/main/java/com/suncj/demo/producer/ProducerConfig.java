package com.suncj.demo.producer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
@EnableKafka
public class ProducerConfig {

	@Value("${kafka.producer.servers}")
	private String servers;

	@Value("${kafka.producer.retries}")
	private int retries;

	@Value("${kafka.producer.batch.size}")
	private int batchSize;

	@Value("${kafka.producer.linger}")
	private int linger;

	@Value("${kafka.producer.buffer.memory}")
	private int bufferMemory;

	public Map<String, Object> producerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(Config.BOOTSTRAP_SERVERS_CONFIG, servers);
		props.put(Config.RETRIES_CONFIG, retries);
		props.put(Config.BATCH_SIZE_CONFIG, batchSize);
		props.put(Config.LINGER_MS_CONFIG, linger);
		props.put(Config.BUFFER_MEMORY_CONFIG, bufferMemory);
		props.put(Config.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(Config.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return props;
	}

	public ProducerFactory<String, String> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}

	@Bean
	public KafkaTemplate<String, String> kafkaTemplate() {
		return new KafkaTemplate<String, String>(producerFactory());
	}
}
