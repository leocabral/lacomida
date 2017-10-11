package br.com.lacomida.persistence;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class SharedPreferencesPersistenceLayerTest {

	private SharedPreferencesPersistenceLayer preferences;

	@Before
	public void setup() {
		preferences = new MockedSharedPreferencesPersistenceLayer();
		preferences.init(null);
	}

	@Test(expected = RuntimeException.class)
	public void testNotInitialized() {
		preferences = new MockedSharedPreferencesPersistenceLayer();
		preferences.deleteAll();
	}

	@Test
	public void testSave() {
		TestModel value = new TestModel();
		value.value = "value";

		assertTrue(preferences.save("test", value));

		TestModel saved = preferences.load("test");

		assertEquals("value", saved.value);
	}

	@Test
	public void testLoad() {
		String result = preferences.load("test", "default");
		assertEquals("default", result);
	}

	@Test
	public void testSaveString() {
		assertTrue(preferences.save("test", "value"));

		String saved = preferences.load("test");

		assertEquals("value", saved);
	}

	@Test
	public void testContains() {
		TestModel value = new TestModel();
		value.value = "value";

		preferences.save("test", value);

		assertTrue(preferences.contains("test"));
	}

	@Test
	public void testDelete() {
		TestModel value = new TestModel();
		value.value = "value";

		preferences.save("test", value);

		assertTrue(preferences.contains("test"));
		assertTrue(preferences.delete("test"));

		assertNull(preferences.load("test"));
	}

	@Test
	public void testDeleteAll() {
		TestModel value = new TestModel();
		value.value = "value";
		preferences.save("test", value);

		TestModel value2 = new TestModel();
		value.value = "value";
		preferences.save("test2", value2);

		assertTrue(preferences.deleteAll());

		assertNull(preferences.load("test"));
		assertNull(preferences.load("test2"));
	}

	private class TestModel {

		String value;
	}
}