package com.example.demo.spring.service;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.spring.config.AppConfig;

@RunWith(SpringRunner.class)
@ContextHierarchy({
	@ContextConfiguration,
	//@ContextConfiguration(classes= {AppConfig.class})
})
public class EchoServiceImpl2Test {

	static Map<String, String> appParamsForTest = new HashMap<>();
	static {
		appParamsForTest.put("benx", "shen");
	};

	@Configuration
	@Import(value= {AppConfig.class})
	static class Config {
		@Bean
		@Primary
		public Map<String, String> appParams() {
			return appParamsForTest;
		}
	}

	@Resource
	@Qualifier("appParams")
	Map<String, String> appParams;

	@Resource
	EchoService echoService;

	@Test
	public void testModifyAppParams() throws Exception {
		assertTrue(appParams == appParamsForTest);

		assertEquals("shen", appParams.get("benx"));

		appParams.put("foo", "bar");
		assertEquals("bar", appParamsForTest.get("foo"));
	}

	@Test
	public void testEcho() throws Exception {
		assertEquals("world", echoService.echo("world"));
	}


}
