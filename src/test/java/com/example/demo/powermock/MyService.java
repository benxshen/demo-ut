package com.example.demo.powermock;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;

import com.example.demo.spring.service.EmailService;

import lombok.Getter;

@Getter
public class MyService {
	private String value;
	@Resource
	private EmailService emailService;
	@Resource
	private MessageSource messageSource;

	private String privateFoo() {
		return "foo";
	}

	private void privateFoo2() {
	}

	private void privateFoo3() {
	}

	public String publicFoo() {
		privateFoo2();
		return privateFoo();
	}
}
