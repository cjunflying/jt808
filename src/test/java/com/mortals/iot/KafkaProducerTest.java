package com.mortals.iot;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaProducerTest {
	public static void main(String[] args) {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ProducerConfig.RETRIES_CONFIG, 0);
		props.put(ProducerConfig.BATCH_SIZE_CONFIG, 4096);
		props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
		props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 40960);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

		DefaultKafkaProducerFactory kafkaFctory = new DefaultKafkaProducerFactory<>(props);
		KafkaTemplate kafkaTemplate = new KafkaTemplate<>(kafkaFctory);
		
		String jsonData="{\r\n" + 
				"	\"action\": 33027,\r\n" + 
				"	\"devmark\": \"S60\",\r\n" + 
				"	\"serialNumber\": \"serialNumber\",\r\n" + 
				"	\"data\": {\r\n" + 
				"		\"5Y+C5pWw5oC75pWw\": 1,\r\n" + 
				"		\"5Y+C5pWw6aG55YiX6KGo\": [{\r\n" + 
				"			\"5Y+C5pWwSUQ=\": 1,\r\n" + 
				"			\"5Y+C5pWw6ZW/5bqm\": 4,\r\n" + 
				"			\"5Y+C5pWw5YC8\": 10\r\n" + 
				"		}]\r\n" + 
				"	}\r\n" + 
				"}";
		kafkaTemplate.send("commandreq", jsonData);
	}
}
