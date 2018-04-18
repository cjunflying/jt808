package com.mortals.iot.framework.cfg;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

/**
 * 
 * Description：Kafka生产者
 * 
 * 依赖： <dependency> <groupId>org.springframework.kafka</groupId>
 * <artifactId>spring-kafka</artifactId>
 * <version>1.0.5.RELEASE</version> </dependency>
 * 
 * 使用案例：
 * 
 * @Resource 
 * private KafkaTemplate kafkaTemplate;
 * 调用方法发送数据： kafkaTemplate.send(topic, msg);
 * 
 */
@Configuration
@EnableKafka
@PropertySource("classpath:kafka.properties")
public class KafkaProducerConfig {

	@Value("${kafka.broker.address}")
	private String brokerAddress;
	private Logger log = LoggerFactory.getLogger(KafkaProducerConfig.class);
	@Bean
	public Map<String, Object> producerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.brokerAddress);
		props.put(ProducerConfig.RETRIES_CONFIG, 0);
		props.put(ProducerConfig.BATCH_SIZE_CONFIG, 4096);
		props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
		props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 40960);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return props;
	}

	/** 获取工厂 */
	@Bean
	public ProducerFactory<String, String> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}

	/** 注册实例 */
	@Bean(name = "kafkaTemplate")
	public KafkaTemplate<String, String> kafkaTemplate() {
		log.info("加载kafka生产者配置");
		return new KafkaTemplate<>(producerFactory());
	}

}