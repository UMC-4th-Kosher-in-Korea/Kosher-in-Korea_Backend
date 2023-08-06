package com.kusher.kusher_in_korea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextInstanceDataAutoConfiguration;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(exclude = {ContextStackAutoConfiguration.class, ContextInstanceDataAutoConfiguration.class})
@EnableWebMvc
public class KusherInKoreaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KusherInKoreaApplication.class, args);
	}

}
