package br.com.lacomida.persistence;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class PreferencesHelperTest {

	@Test
	public void testSerializeMap() {
		TestModel value = new TestModel();
		value.intValue = 123;
		value.stringValue = "test";

		Map<String, TestModel> map = new HashMap<>();

		map.put("a", value);
		map.put("b", value);

		String jsonString = PreferencesHelper.serialize(map);
		assertEquals("java.lang.String#br.com.lacomida.persistence" +
				".PreferencesHelperTest$TestModel#M#" +
				"{\"a\":{\"stringValue\":\"test\",\"intValue\":123}," +
				"\"b\":{\"stringValue\":\"test\",\"intValue\":123}}", jsonString);
	}

	@Test
	public void testParseMap() {
		String jsonString = "java.lang.String#br.com.lacomida.persistence" +
				".PreferencesHelperTest$TestModel#M#" +
				"{\"a\":{\"stringValue\":\"testA\",\"intValue\":123}," +
				"\"b\":{\"stringValue\":\"testB\",\"intValue\":456}}";
		Map<String, TestModel> map = PreferencesHelper.parse(jsonString);

		assertEquals(2, map.size());
		assertEquals(123, map.get("a").intValue, 0);
		assertEquals("testA", map.get("a").stringValue);
		assertEquals(456, map.get("b").intValue, 0);
		assertEquals("testB", map.get("b").stringValue);
	}

	@Test
	public void testSerializeList() {
		TestModel value = new TestModel();
		value.intValue = 123;
		value.stringValue = "test";

		TestModel value2 = new TestModel();
		value2.intValue = 456;
		value2.stringValue = "test2";

		List<TestModel> list = new ArrayList<>();
		list.add(value);
		list.add(value2);

		String jsonString = PreferencesHelper.serialize(list);
		assertEquals("br.com.lacomida.persistence" +
				".PreferencesHelperTest$TestModel##L#" +
				"[{\"stringValue\":\"test\",\"intValue\":123}," +
				"{\"stringValue\":\"test2\",\"intValue\":456}]", jsonString);
	}

	@Test
	public void testParseList() {
		String jsonString = "br.com.lacomida.persistence" +
				".PreferencesHelperTest$TestModel##L#" +
				"[{\"stringValue\":\"test\",\"intValue\":123}," +
				"{\"stringValue\":\"test2\",\"intValue\":456}]";

		List<TestModel> list = PreferencesHelper.parse(jsonString);

		assertEquals(2, list.size());
		assertEquals(123, list.get(0).intValue, 0);
		assertEquals("test", list.get(0).stringValue);

		assertEquals(456, list.get(1).intValue, 0);
		assertEquals("test2", list.get(1).stringValue);
	}

	@Test
	public void testSerializeSet() {
		TestModel value = new TestModel();
		value.intValue = 123;
		value.stringValue = "test";

		TestModel value2 = new TestModel();
		value2.intValue = 456;
		value2.stringValue = "test2";

		Set<TestModel> set = new HashSet<>();
		set.add(value);
		set.add(value2);

		String jsonString = PreferencesHelper.serialize(set);
		assertEquals("br.com.lacomida.persistence" +
				".PreferencesHelperTest$TestModel##S#" +
				"[{\"stringValue\":\"test2\",\"intValue\":456}," +
				"{\"stringValue\":\"test\",\"intValue\":123}]", jsonString);
	}

	@Test
	public void testParseSet() {
		String jsonString = "br.com.lacomida.persistence" +
				".PreferencesHelperTest$TestModel##S#" +
				"[{\"stringValue\":\"test2\",\"intValue\":456}," +
				"{\"stringValue\":\"test\",\"intValue\":123}]";

		Set<TestModel> set = PreferencesHelper.parse(jsonString);

		assertEquals(2, set.size());

		Iterator<TestModel> iterator = set.iterator();

		TestModel value2 = iterator.next();
		assertEquals(456, value2.intValue, 0);
		assertEquals("test2", value2.stringValue);

		TestModel value = iterator.next();
		assertEquals(123, value.intValue, 0);
		assertEquals("test", value.stringValue);
	}

	@Test
	public void testSerializeObject() {
		TestModel value = new TestModel();
		value.intValue = 123;
		value.stringValue = "test";

		String jsonString = PreferencesHelper.serialize(value);

		assertEquals("br.com.lacomida.persistence" +
				".PreferencesHelperTest$TestModel##O#" +
				"{\"stringValue\":\"test\",\"intValue\":123}", jsonString);
	}

	@Test
	public void testParseObject() {
		String jsonString = "br.com.lacomida.persistence" +
				".PreferencesHelperTest$TestModel##O#" +
				"{\"stringValue\":\"test\",\"intValue\":123}";

		TestModel value = PreferencesHelper.parse(jsonString);

		assertEquals(123, value.intValue, 0);
		assertEquals("test", value.stringValue);
	}

	private class TestModel {

		String stringValue;
		Integer intValue;

		@Override
		public int hashCode() {
			return intValue;
		}
	}
}
