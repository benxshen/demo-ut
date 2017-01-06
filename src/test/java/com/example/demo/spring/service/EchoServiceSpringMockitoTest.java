package com.example.demo.spring.service;

import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
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
	@ContextConfiguration
})
public class EchoServiceSpringMockitoTest {

	static EmailService mockEmailService;

	@Configuration
	@Import(value= {AppConfig.class})
	static class Config {

		@Bean
		@Primary
		public EmailService emailService() {
			mockEmailService = mock(EmailService.class);
			return mockEmailService;
		}
	}

	@After
	public void teardown() {
		reset(mockEmailService);
	}

	@Resource
	EchoService echoService;

	@Test
	public void testEchoAndSendMailToAdmin() throws Exception {
		String message = "Sping with Mockito Intergation Test";
		echoService.echoAndSendMailToAdmin(message);

//		verify(mockEmailService).sendMailToAdmin(eq("echo"), contains(message));
		verify(mockEmailService).sendMailToAdmin(eq("echo"), argThat(arg -> {
			return ((String) arg).contains(message);
		}));

	}
}
