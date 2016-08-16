package com.zzb.testing;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Created by ZZB on 2016/8/16.
 */
public class MockitoTest {
    @Mock
    MockTest mTest;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);//初始化mockito 注解
    }
    @Test
    public void testOrigin() {//fail
        //这是它的精髓之一，可以通过注解@Mock(语法糖)或者Mockito.mock(Class<T> clazz)模拟出一个实例，
        // 传入的不论是Interface, Abstract class还是普通Class，统统都会mock出一个继承原类、填满hook的新类。注意这个生成的新类是一个空壳，
        // 如果需要使用必须指定行为（Stub）。对于未Stub的方法，通常返回null
        Assert.assertEquals("str", mTest.getStr());
    }

    @Test
    public void testMock(){//success
        //成功，因为我们把这个方法Stub住了，让它返回了”test1”
        Mockito.when(mTest.getStr()).thenReturn("test1");
        Assert.assertEquals("test1", mTest.getStr());
    }
    class MockTest {
        public String getStr() {
            return "str";
        }
    }
}
