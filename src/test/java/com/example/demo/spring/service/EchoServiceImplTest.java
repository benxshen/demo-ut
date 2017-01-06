package com.example.demo.spring.service;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.spring.config.AppConfig;
import com.example.demo.spring.service.EchoService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= {AppConfig.class})
public class EchoServiceImplTest {

	@Resource
	@Qualifier("appParams")
	Map<String, String> appParams;

	@Resource
	EchoService echoService;

	@Test(expected=UnsupportedOperationException.class)
	public void testModifyAppParams() throws Exception {
		appParams.put("foo", "bar");
	}

	@Test
	public void testEcho() throws Exception {
		assertEquals("hello, world", echoService.echo("world"));
	}

	@Test(expected=RuntimeException.class)
	public void testEchoUserName() throws Exception {
		echoService.echoUserName(100L);
	}

}
