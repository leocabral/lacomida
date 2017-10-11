package br.com.lacomida.util;

import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FileUtilsTest {

	@Test
	public void testReadJson() {
		TestModel testModel = FileUtils.readJson("test/test.json", TestModel.class);

		assertNotNull(testModel);
		assertEquals("test", testModel.value);
	}

	@Test
	public void testReadJsonList() {
		List<TestModel> testModel = FileUtils.readJson("test/testList.json", new TypeToken<List<TestModel>>() {
		}.getType());

		assertNotNull(testModel);
		assertEquals(2, testModel.size());
		assertEquals("test", testModel.get(0).value);
		assertEquals("test1", testModel.get(1).value);
	}

	public static class TestModel {

		String value;
	}
}