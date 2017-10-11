package br.com.lacomida.persistence;

import android.content.Context;

public interface PersistenceLayer {

	void init(Context context);

	<T> boolean save(String key, T value);

	<T> T load(String key);

	<T> T load(String key, T defaultValue);

	boolean contains(String key);

	boolean delete(String key);

	boolean deleteAll();

	void checkInitialization() throws RuntimeException;
}
