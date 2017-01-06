package com.example.demo.spring.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class AppConfigTest {

	AppConfig appConfig;

	@Before
	public void init() {
		appConfig = new AppConfig();
	}

	@Test
	public void testAppParams() throws Exception {
		Map<String, String> appParams = appConfig.appParams();

		assertNotNull(appParams);
		// assertTrue(appParams.containsKey("greeting"));
		assertEquals("hello", appParams.get("greeting"));
	}

	@Test
	public void testAppParams_modify() {
		Map<String, String> appParams = appConfig.appParams();
		UnsupportedOperationException exp = null;
		try {
			appParams.put("foo", "bar");
			fail("appParam shall be unmodifiable map");
		} catch(UnsupportedOperationException e) {
			exp = e;
		}
		assertNotNull("UnsupportedOperationException should be thrown when modifying the appParams", exp);

	}

	@Test(expected=UnsupportedOperationException.class)
	public void testAppParams_modify2() {
		Map<String, String> appParams = appConfig.appParams();
		appParams.put("foo", "bar");

		fail("never happened");
	}

}
