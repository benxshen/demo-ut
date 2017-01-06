package com.example.demo.powermock;

import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.example.demo.spring.service.EchoServiceImpl;


@RunWith(PowerMockRunner.class)
@PrepareForTest(EchoServiceImpl.class)
public class HelloPowerMockTest {

//	@Rule
//  public PowerMockRule rule = new PowerMockRule();

}
