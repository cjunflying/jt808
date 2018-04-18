package com.mortals.iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@ComponentScan(value = { "com.mortals.iot.framework.cfg", "com.mortals.iot.handler", "com.mortals.iot.server" })
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ImportResource(locations = { "classpath:config/spring-config.xml" })
public class IotHanderReceiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(IotHanderReceiveApplication.class, args);
	}
}
