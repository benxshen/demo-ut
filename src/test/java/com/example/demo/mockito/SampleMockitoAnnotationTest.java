/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.mockito;

import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * http://site.mockito.org/mockito/docs/current/org/mockito/Mockito.html#
 * mock_annotation
 *
 * @author benx
 */
// @RunWith(MockitoJUnitRunner.class) // use @RunWith or use MockitoAnnotations.initMocks(this)
@SuppressWarnings({"rawtypes", "unchecked"})
public class SampleMockitoAnnotationTest {

	// mock creation
	@Mock
	List mockedList;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testMockito() {

		// using mock object - it does not throw any "unexpected interaction"
		// exception
		mockedList.add("one");
		mockedList.clear();

		// selective, explicit, highly readable verification
		verify(mockedList).add("one");
		verify(mockedList).clear();


	}


}
