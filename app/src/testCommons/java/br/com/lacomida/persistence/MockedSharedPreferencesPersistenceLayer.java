package br.com.lacomida.persistence;

import android.content.Context;

public class MockedSharedPreferencesPersistenceLayer extends SharedPreferencesPersistenceLayer {

	@Override
	public void init(Context context) {
		preferences = new MockedSharedPreferences();
	}
}