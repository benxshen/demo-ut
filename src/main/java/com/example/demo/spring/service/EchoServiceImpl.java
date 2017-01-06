package com.example.demo.spring.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.spring.repository.UserRepository;

@Service
public class EchoServiceImpl implements EchoService {

	@Resource
	@Qualifier(value="appParams")
	private Map<String, String> appParams;

	@Resource
	private UserRepository userRepository;

	@Resource
	private EmailService emailService;

	@Override
	public String echo(String message) {
		if(appParams.get("greeting") != null) {
			return String.format("%s, %s", appParams.get("greeting"), message);
		}

		return message;
	}

	@Override
	public String echoUserName(Long userId) {
		return userRepository.findById(userId).getName();
	}

	@Override
	public String echoAndSendMailToAdmin(String message) {
		String result = echo(message);
		emailService.sendMailToAdmin("echo", result);
		return result;
	}

	public String echo() {
		return defaultEcho();
	}

	private String defaultEcho() {
		return "ECHO";
	}
}
