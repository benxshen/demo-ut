/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.mockito;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * http://site.mockito.org/mockito/docs/current/org/mockito/junit/MockitoRule.html
 *
 * @author benx
 */
// @RunWith(MockitoJUnitRunner.class) // use @RunWith or use
// MockitoAnnotations.initMocks(this)
@SuppressWarnings({"rawtypes", "unchecked"})
public class SampleMockitoAnnotation2Test {

	/*
	 * The JUnit rule can be used instead of MockitoJUnitRunner.
	 *
	 * Initializes mocks annotated with Mock, so that explicit usage of MockitoAnnotations.initMocks(Object) is not necessary.
	 * Mocks are initialized before each test method.
	 */
	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	// mock creation
	@Mock
	List mockedList;

//	@Before
//	public void initMocks() {
//		MockitoAnnotations.initMocks(this);
//	}

	@Test
	public void testMockito() {

		// using mock object - it does not throw any "unexpected interaction" exception
		mockedList.add("one");
		mockedList.add("two");
		mockedList.clear();

		// selective, explicit, highly readable verification
		verify(mockedList, times(2)).add(anyString());
		verify(mockedList).clear();

	}


}
