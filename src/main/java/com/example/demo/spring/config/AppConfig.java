package com.example.demo.spring.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages={"com.example.demo.spring.repository", "com.example.demo.spring.service"},
excludeFilters= {
		@ComponentScan.Filter(type = FilterType.REGEX, pattern = {
				".*Test.*"
		})
})
//@ImportResource({ "classpath:/Spring-Config.xml", "classpath:/data-access-config.xml", "classpath:/Spring-Mail.xml" })

public class AppConfig {

	@Bean
	public Map<String, String> appParams() {
		Map<String, String> params = new HashMap<>();
		params.put("greeting", "hello");
		return Collections.unmodifiableMap(params);
	}
}
