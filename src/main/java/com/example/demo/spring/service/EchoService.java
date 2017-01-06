package com.example.demo.spring.service;


public interface EchoService {

	String echo(String message);

	String echoUserName(Long userId);

	String echoAndSendMailToAdmin(String message);
}
