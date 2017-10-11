package br.com.lacomida.base.manager;

import br.com.lacomida.base.MvpView;
import br.com.lacomida.util.Injector;

public class ViewManager {

	@SuppressWarnings("unchecked")
	public <T extends MvpView> T get(Class<T> clazz) {
		return Injector.get(clazz);
	}

	public <T> void mock(Class<T> original, Class<? extends T> replacement) {
		Injector.set(original, replacement);
	}
}
