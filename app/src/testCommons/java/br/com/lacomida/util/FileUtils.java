package br.com.lacomida.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;

import io.reactivex.annotations.NonNull;

public class FileUtils {

	private static Gson gson;
	private static JsonParser parser;

	@NonNull
	public static <T> T readJson(String path, Type type) {
		String jsonString = readJson(path);

		JsonElement element = getJsonParser().parse(jsonString);
		Gson gson = getGson();
		return gson.fromJson(element, type);
	}

	private static String readJson(String path) {
		URL url = FileUtils.class.getClassLoader().getResource(path);
		if (url == null) {
			throw new RuntimeException("file not found: " + path);
		}

		InputStream inputStream = null;
		try {
			inputStream = url.openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			StringBuilder file = new StringBuilder();
			while (reader.ready()) {
				file.append(reader.readLine());
			}
			return file.toString();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static JsonParser getJsonParser() {
		if (parser == null) {
			parser = new JsonParser();
		}
		return parser;
	}

	private static Gson getGson() {
		if (gson == null) {
			gson = new Gson();
		}
		return gson;
	}
}
