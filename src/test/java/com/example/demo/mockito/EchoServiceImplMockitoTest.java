package com.example.demo.mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.example.demo.entity.User;
import com.example.demo.spring.repository.UserRepository;
import com.example.demo.spring.service.EchoServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class EchoServiceImplMockitoTest {

	@Mock
	Map<String, String> appParams;

	@Mock
	UserRepository userRepository;

	@InjectMocks
	EchoServiceImpl echoServiceImpl;

	@Test
	public void testEcho() throws Exception {

		assertEquals("mockito", echoServiceImpl.echo("mockito"));

		verify(appParams, times(1)).get("greeting");

		////////////////////
		reset(appParams);
		////////////////////

		doReturn("hello").when(appParams).get("greeting");
		assertEquals("hello, mockito", echoServiceImpl.echo("mockito"));

		verify(appParams, times(2)).get("greeting");
	}

	@Test
	public void testEchoUserName() throws Exception {
		User user = new User();
		user.setName("benx");
		when(userRepository.findById(1L)).thenReturn(user);

		assertEquals("benx", echoServiceImpl.echoUserName(1L));
	}

	@Test
	public void testEchoUserName2() throws Exception {
		User user = mock(User.class);
		when(userRepository.findById(1L)).thenReturn(user);

		when(user.getName()).thenReturn("BENX");

		assertEquals("BENX", echoServiceImpl.echoUserName(1L));

		verify(user, times(0)).getId();
		verify(user, times(0)).getEmail();

	}

	@Test
	@Ignore
	public void testEchoAndSendMailToAdmin() throws Exception {
		// TODO: write the test.
		fail("TODO: write the test");
	}

}
