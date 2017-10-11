package br.com.lacomida.persistence;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MockedSharedPreferences implements android.content.SharedPreferences {

	private Map<String, String> values = new HashMap<>();

	@Override
	public Map<String, ?> getAll() {
		return values;
	}

	@Override
	public String getString(String s, String s1) {
		String value = values.get(s);
		return value == null ? s1 : value;
	}

	@Override
	public Set<String> getStringSet(String s, Set<String> set) {
		return null;
	}

	@Override
	public int getInt(String s, int i) {
		String value = values.get(s);
		if (value != null) {
			return Integer.valueOf(value);
		}
		return -1;
	}

	@Override
	public long getLong(String s, long l) {
		return 0;
	}

	@Override
	public float getFloat(String s, float v) {
		return 0;
	}

	@Override
	public boolean getBoolean(String s, boolean b) {
		return false;
	}

	@Override
	public boolean contains(String s) {
		return values.containsKey(s);
	}

	@Override
	public Editor edit() {
		return new MockedEditor();
	}

	@Override
	public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
	}

	@Override
	public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
	}

	private class MockedEditor implements Editor {

		@Override
		public Editor putString(String s, String s1) {
			values.put(s, s1);
			return this;
		}

		@Override
		public Editor putStringSet(String s, Set<String> set) {
			return null;
		}

		@Override
		public Editor putInt(String s, int i) {
			values.put(s, String.valueOf(i));
			return this;
		}

		@Override
		public Editor putLong(String s, long l) {
			return null;
		}

		@Override
		public Editor putFloat(String s, float v) {
			return null;
		}

		@Override
		public Editor putBoolean(String s, boolean b) {
			return null;
		}

		@Override
		public Editor remove(String s) {
			values.remove(s);
			return this;
		}

		@Override
		public Editor clear() {
			values.clear();
			return this;
		}

		@Override
		public boolean commit() {
			return true;
		}

		@Override
		public void apply() {
		}
	}
}
