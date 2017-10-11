package br.com.lacomida.persistence;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("unchecked")
public class PreferencesHelper {

	private static final String CLASS_TAG = "PreferencesHelper";

	private static Gson gson;

	protected static Gson getGson() {
		if (gson == null) {
			gson = new Gson();
		}
		return gson;
	}

	protected static <T> String serialize(T value) {
		if (value == null) {
			return null;
		}
		String keyClassName = "";
		String valueClassName = "";
		char dataType;

		Class<?> valueClass = value.getClass();
		if (List.class.isAssignableFrom(valueClass)) {
			List<?> list = (List<?>) value;
			if (!list.isEmpty()) {
				keyClassName = list.get(0).getClass().getName();
			}
			dataType = DataType.LIST;
		} else if (Map.class.isAssignableFrom(valueClass)) {
			Map<?, ?> map = (Map<?, ?>) value;
			if (!map.isEmpty()) {
				Map.Entry<?, ?> entry = map.entrySet().iterator().next();
				keyClassName = entry.getKey().getClass().getName();
				valueClassName = entry.getValue().getClass().getName();
			}
			dataType = DataType.MAP;
		} else if (Set.class.isAssignableFrom(valueClass)) {
			Set<?> set = (Set<?>) value;
			if (!set.isEmpty()) {
				keyClassName = set.iterator().next().getClass().getName();
			}
			dataType = DataType.SET;
		} else {
			dataType = DataType.OBJECT;
			keyClassName = valueClass.getName();
		}

		String jsonString = getGson().toJson(value);
		return keyClassName + DataType.DELIMITER +
				valueClassName + DataType.DELIMITER +
				dataType + DataType.DELIMITER +
				jsonString;
	}

	protected static <T> T parse(String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}

		String[] parts = value.split(DataType.DELIMITER + "");

		String keyClassName = parts[0];
		String valueClassName = parts[1];
		String jsonString = parts[3];

		Class<?> keyClass = getClassFor(keyClassName);
		Class<?> valueClass = getClassFor(valueClassName);

		char dataType = parts[2].charAt(0);
		if (dataType == DataType.MAP) {
			return parseMap(jsonString, keyClass, valueClass);
		}
		if (dataType == DataType.LIST) {
			return parseList(jsonString, keyClass);
		}
		if (dataType == DataType.SET) {
			return parseSet(jsonString, keyClass);
		}
		if (dataType == DataType.OBJECT) {
			return parseObject(jsonString, keyClass);
		}
		return null;
	}

	private static <T, K, V> T parseMap(String json, Class<K> keyClass, Class<V> valueClass) {
		Map<K, V> resultMap = new HashMap<>();
		if (keyClass == null || valueClass == null) {
			return (T) resultMap;
		}
		Gson gson = getGson();
		Map<K, V> map = gson.fromJson(json, new TypeToken<Map<K, V>>() {
		}.getType());

		for (Map.Entry<K, V> entry : map.entrySet()) {
			String keyJson = gson.toJson(entry.getKey());
			K k = gson.fromJson(keyJson, keyClass);

			String valueJson = gson.toJson(entry.getValue());
			V v = gson.fromJson(valueJson, valueClass);
			resultMap.put(k, v);
		}
		return (T) resultMap;
	}

	private static <T, K> T parseList(String json, Class<K> keyClass) {
		List<K> resultList = new ArrayList<>();
		if (keyClass == null) {
			return (T) resultList;
		}

		Gson gson = getGson();
		List<K> list = gson.fromJson(json, new TypeToken<List<K>>() {
		}.getType());

		for (K value : list) {
			String valueJson = gson.toJson(value);
			resultList.add(gson.fromJson(valueJson, keyClass));
		}
		return (T) resultList;
	}

	private static <T, K> T parseSet(String json, Class<K> keyClass) {
		Set<K> resultSet = new HashSet<>();
		if (keyClass == null) {
			return (T) resultSet;
		}

		Gson gson = getGson();
		Set<K> set = gson.fromJson(json, new TypeToken<Set<K>>() {
		}.getType());

		for (K value : set) {
			String valueJson = gson.toJson(value);
			resultSet.add(gson.fromJson(valueJson, keyClass));
		}
		return (T) resultSet;
	}

	private static <T> T parseObject(String json, Class<?> keyClass) {
		return (T) getGson().fromJson(json, keyClass);
	}

	private static Class<?> getClassFor(String className) {
		if (className != null && !className.isEmpty()) {
			try {
				return Class.forName(className);
			} catch (ClassNotFoundException e) {
				Log.e(CLASS_TAG, e.getMessage());
			}
		}
		return null;
	}
}