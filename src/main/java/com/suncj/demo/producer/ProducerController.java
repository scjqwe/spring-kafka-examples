package com.suncj.demo.producer;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka/producer")
public class ProducerController {
	private static Logger logger = LoggerFactory.getLogger(ProducerController.class);

	@Value("${topic.name}")
	private String topicName;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@RequestMapping("/send")
	public Object sendKafka(String message) {
		try {
			logger.info("send kafka message: {}", message);
			kafkaTemplate.send(topicName, UUID.randomUUID().toString(), message);
			return "success";
		} catch (Exception e) {
			logger.error("发送kafka失败", e);
			return "fail";
		}
	}
}
