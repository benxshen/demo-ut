package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyFirstUnitTest {
	final String NON_EXISTED_KEY = "non_existed_key";

	HashMap<String, String> tested;

	@Before
	public void setup() {
		tested = new HashMap<>();
		tested.put("test", "junit");
		tested.put("com", "pollex");
	}

	@After
	public void teardown() {
		tested = null;
	}

	@Test
	public void testHashMap() throws Exception {

		assertEquals(2, tested.size());
		assertTrue(!tested.isEmpty());
		assertEquals("junit", tested.get("test"));
		assertEquals("pollex", tested.get("com"));

		assertNull(tested.get(NON_EXISTED_KEY));
		assertFalse(tested.containsKey(NON_EXISTED_KEY));

		tested.remove("test");
		assertNull(tested.get("test"));
		assertEquals(1, tested.size());
	}

	@Test
	public void testHashMap_computeIfPresent() throws Exception {
		assertEquals(2, tested.size());

		assertEquals("JUNIT", tested.computeIfPresent("test", (key, value) -> {
			return value.toUpperCase();
		}));

		assertNull(tested.computeIfPresent(NON_EXISTED_KEY, (key, value) -> {
			return value.toUpperCase();
		}));
	}

}
