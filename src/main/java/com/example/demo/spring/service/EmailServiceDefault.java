package com.example.demo.spring.service;

import org.springframework.stereotype.Service;

@Service
public class EmailServiceDefault implements EmailService{

	@Override
	public void sendMailToAdmin(String subject, String content) {
		// TODO Auto-generated method stub
		throw new RuntimeException("not implement!!!");
	}

}
