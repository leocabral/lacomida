package br.com.lacomida.persistence;

import android.content.Context;
import android.content.SharedPreferences;

import br.com.lacomida.app.Config;

public class SharedPreferencesPersistenceLayer implements PersistenceLayer {

	private static final String PREFERENCES_TAG = Config.PREFERENCES_TAG;

	protected SharedPreferences preferences;

	public SharedPreferencesPersistenceLayer() {
	}

	@Override
	public void init(Context context) {
		this.preferences = context.getSharedPreferences(PREFERENCES_TAG, Context.MODE_PRIVATE);
	}

	@Override
	public <T> boolean save(String key, T value) {
		checkInitialization();
		return getEditor().putString(key, PreferencesHelper.serialize(value)).commit();
	}

	@Override
	public <T> T load(String key) {
		checkInitialization();
		return PreferencesHelper.parse(preferences.getString(key, null));
	}

	@Override
	public <T> T load(String key, T defaultValue) {
		T result = load(key);
		return result != null ? result : defaultValue;
	}

	@Override
	public boolean contains(String key) {
		checkInitialization();
		return preferences.contains(key);
	}

	@Override
	public boolean delete(String key) {
		checkInitialization();
		return getEditor().remove(key).commit();
	}

	@Override
	public boolean deleteAll() {
		checkInitialization();
		return getEditor().clear().commit();
	}

	@Override
	public void checkInitialization() throws RuntimeException {
		if (preferences == null) {
			throw new RuntimeException("SharedPreferencesPersistenceLayer not initialized");
		}
	}

	protected SharedPreferences.Editor getEditor() {
		return preferences.edit();
	}

}
