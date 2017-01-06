package com.example.demo.powermock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.times;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.test.util.ReflectionTestUtils;

import com.example.demo.spring.service.EmailService;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MyService.class)
public class PowerMockTest {

//	@Rule
//    public PowerMockRule rule = new PowerMockRule();

	@Mock
	private EmailService emailService;

	@Spy
	private MessageSource messageSource = new ResourceBundleMessageSource();

	@InjectMocks
	@Spy
	private MyService myService = new MyService();

	@Before
	public void setUp() throws Exception {
		ReflectionTestUtils.setField(myService, "value", "hello world");
	}

	@Test
	public void testPowerMock() throws Exception {
		// resource/autowire field js injected by @Mock
		assertNotNull(myService.getEmailService());
		assertSame(emailService, myService.getEmailService());

		assertNotNull(myService.getMessageSource());
		assertSame(messageSource, myService.getMessageSource());

		// private field is injected by ReflectionTestUtils.setField
		assertEquals("hello world", myService.getValue());


		PowerMockito.doReturn("FOO").when(myService, "privateFoo");	// spy/mock return value for a private method
		assertEquals("FOO", myService.publicFoo());


		PowerMockito.verifyPrivate(myService).invoke("privateFoo2");

		// PowerMockito.verifyPrivate(myService).invoke("privateFoo3"); // will fail since no invoke privateFoo3 in the publicFoo method.
		PowerMockito.verifyPrivate(myService, times(0)).invoke("privateFoo3");
	}



}
