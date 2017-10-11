package br.com.lacomida.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class InjectorTest {

	@Test
	public void testGet() {
		TestClass testClass = Injector.get(TestClass.class);
		assertNotNull(testClass);

		testClass.setValue("123");
		assertEquals("123", Injector.get(TestClass.class).getValue());
	}

	@Test
	public void testSet() {
		Injector.set(TestClass.class, TestClassExt.class);

		TestClassExt testClassExt = (TestClassExt) Injector.get(TestClass.class);
		assertNotNull(testClassExt);

		assertEquals("zzz", testClassExt.getValue());
	}

	public static class TestClass {

		private String value;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	public static class TestClassExt extends TestClass {

		@Override
		public String getValue() {
			return "zzz";
		}
	}
}