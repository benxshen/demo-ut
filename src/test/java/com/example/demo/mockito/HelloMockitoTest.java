/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

/**
 * http://site.mockito.org/
 *
 * @author benx
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class HelloMockitoTest {

    // http://site.mockito.org/mockito/docs/current/org/mockito/Mockito.html#verification

	@Test
    public void testVerify() {
        // mock creation

		List mockedList = mock(List.class);

        // using mock object - it does not throw any "unexpected interaction" exception
        mockedList.add("one");
        mockedList.clear();

        // selective, explicit, highly readable verification
        verify(mockedList).add("one");
        verify(mockedList).clear();

    }

    // http://site.mockito.org/mockito/docs/current/org/mockito/Mockito.html#stubbing
    @Test
    public void testStubMethod() {
        List mockedList = mock(List.class);

        // stubbing appears before the actual execution
        when(mockedList.get(0)).thenReturn("first");

        // the following prints "first"
        System.out.println(mockedList.get(0));
        assertEquals("first", mockedList.get(0));

        // the following prints "null" because get(999) was not stubbed
        System.out.println(mockedList.get(999));
        assertNull(mockedList.get(999));

    }

    // http://site.mockito.org/mockito/docs/current/org/mockito/Mockito.html#argument_matchers
    @Test
    public void testArgumentMatcher() {
        List mockedList = mock(List.class);

        //stubbing using built-in anyInt() argument matcher
        when(mockedList.get(anyInt())).thenReturn("Element");

        //stubbing using custom matcher
        when(mockedList.contains(argThat(arg -> {
            return "Foo".equals(arg);
        }))).thenReturn(true);

        assertEquals("Element", mockedList.get(999));
        assertTrue(mockedList.contains("Foo"));
        assertFalse(mockedList.contains("Bar"));

        verify(mockedList).get(anyInt());
        verify(mockedList, times(2)).contains(any());

        //verify(mock).someMethod(anyInt(), anyString(), eq("third argument"));
        // above is correct - eq() is also an argument matcher
        //verify(mock).someMethod(anyInt(), anyString(), "third argument");
        // above is incorrect - exception will be thrown because third argument is given without an argument matcher.
    }

    // http://site.mockito.org/mockito/docs/current/org/mockito/Mockito.html#exact_verification
    @Test
    public void testCheckNumOfInvocation() {
        List<String> mockedList = mock(List.class);

        mockedList.add("once");

        mockedList.add("twice");
        mockedList.add("twice");

        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        //following two verifications work exactly the same - times(1) is used by default
        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");

        //exact number of invocations verification
        verify(mockedList, times(2)).add("twice");
        verify(mockedList, times(3)).add("three times");

        //verification using never(). never() is an alias to times(0)
        verify(mockedList, never()).add("never happened");

        //verification using atLeast()/atMost()
        verify(mockedList, atLeastOnce()).add("three times");
        verify(mockedList, atLeast(2)).add(any());
        verify(mockedList, atMost(5)).add("three times");
    }

    // http://site.mockito.org/mockito/docs/current/org/mockito/Mockito.html#stubbing_with_exceptions
    // http://site.mockito.org/mockito/docs/current/org/mockito/Mockito.html#do_family_methods_stubs
    @Test
    public void testThrowException() {
        List mockedList = mock(List.class);

        doThrow(new RuntimeException()).when(mockedList).clear();

        try {
            mockedList.clear();
            fail("an exception is expected");
        } catch (Exception e) {
        }
    }

    // http://site.mockito.org/mockito/docs/current/org/mockito/Mockito.html#in_order_verification
    @Test
    public void testVerificationInOrder() {
        // A. Single mock whose methods must be invoked in a particular order
        List<String> singleMock = mock(List.class);
        singleMock.add("was added first");
        singleMock.add("was added second");

        InOrder inOrder = inOrder(singleMock);
        inOrder.verify(singleMock).add("was added first");
        inOrder.verify(singleMock).add("was added second");

        // B. Multiple mocks that must be used in a particular order
        List<String> firstMock = mock(List.class);
        List<String> secondMock = mock(List.class);

        firstMock.add("1");
        secondMock.add("2");

        InOrder inOrder2 = inOrder(firstMock, secondMock);

        inOrder2.verify(firstMock).add("1");
        inOrder2.verify(secondMock).add("2");

    }

    @Test
    public void testVerificationNever() {
        List mockedList = mock(List.class);
        mockedList.add("one");

        verify(mockedList).add("one");
        verify(mockedList, never()).add("two");

        List mock2 = mock(List.class);
        List mock3 = mock(List.class);
        verifyZeroInteractions(mock2, mock3);
    }

    @Test
    public void testVerifyNoMoreInteractions() {
        List mockedList = mock(List.class);
        mockedList.add("one");
        mockedList.add("two");

        verify(mockedList).add("one");

        //following verification will fail
        try {
             verifyNoMoreInteractions(mockedList);
             fail("verifyNoMoreInteractions is expected in this test case.");
        } catch(Throwable e) {
            e.printStackTrace();
        }

        // A word of warning: Some users who did a lot of classic, expect-run-verify mocking tend to use
        // verifyNoMoreInteractions() very often, even in every test method. verifyNoMoreInteractions()
        // is not recommended to use in every test method.

    }

    // http://site.mockito.org/mockito/docs/current/org/mockito/Mockito.html#stubbing_consecutive_calls
    @Test
    public void testStubConsecutiveCalls() {
        List mockedList = mock(List.class);

        when(mockedList.add("once"))
                .thenReturn(true)
                .thenReturn(false)
                .thenThrow(new RuntimeException());

        assertTrue(mockedList.add("once"));
        assertFalse(mockedList.add("once"));

        try {
             mockedList.add("once");
             fail("RuntimeException is expected in this test case.");
        } catch(Throwable e) {
        }

    }

    // http://site.mockito.org/mockito/docs/current/org/mockito/Mockito.html#answer_stubs
    @Test
    public void testStubWithCallbacks() {
        List mockedList = mock(List.class);

        when(mockedList.add(anyString())).thenAnswer(callOnMock -> {
            String str = callOnMock.getArgument(0);
            return str.startsWith("foo");
        });

        assertTrue(mockedList.add("foo1"));
        assertTrue(mockedList.add("foo2"));
        assertFalse(mockedList.add("_foo"));
    }

    // http://site.mockito.org/mockito/docs/current/org/mockito/Mockito.html#spy
    @Test
    public void testSpyOnRealObject() {
        List list = new LinkedList();
        List spy = spy(list);

        when(spy.size()).thenReturn(100);

        //using the spy calls *real* methods
        spy.add("one");
        spy.add("two");

        assertEquals("one", spy.get(0));
        assertEquals("two", spy.get(1));
        assertEquals(100, spy.size());

        verify(spy).add("one");
        verify(spy).add("two");

        // NOTE: Important gotcha on spying real objects!
        // 1.
        //Impossible: real method is called so spy.get(0) throws IndexOutOfBoundsException (the list is yet empty)
        // when(spy.get(0)).thenReturn("foo");

        //You have to use doReturn() for stubbing
        doReturn("foo").when(spy).get(10);
        assertEquals("foo", spy.get(10));

        // 2. Mockito *does not* delegate calls to the passed real instance, instead it actually creates a copy of it.
        assertEquals(0, list.size());

        // 3. Watch out for final methods. Mockito doesn't mock final methods
    }

    // http://site.mockito.org/mockito/docs/current/org/mockito/Mockito.html#defaultreturn
    @Test
    public void testDefaultRetValForUnStubbed() {
//        http://site.mockito.org/mockito/docs/current/org/mockito/Mockito.html#RETURNS_SMART_NULLS
        List mockedList = mock(List.class, Mockito.RETURNS_SMART_NULLS);

        Object ret = mockedList.get(0);

        System.out.println(">>>>>"+ret);

    }
}
