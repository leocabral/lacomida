package br.com.lacomida.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ReflectionUtilTest {

	@Test
	public void testNewInstance() {
		TestClass testClass = ReflectionUtil.newInstance(TestClass.class);

		assertNotNull(testClass);
		assertEquals("zzz", testClass.getValue());
	}

	public static class TestClass {

		String getValue() {
			return "zzz";
		}
	}
}