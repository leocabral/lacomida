package br.com.lacomida.util;

import org.junit.Before;
import org.junit.BeforeClass;

import br.com.lacomida.cart.manager.CartManager;
import br.com.lacomida.persistence.MockedSharedPreferencesPersistenceLayer;
import br.com.lacomida.persistence.PersistenceLayer;

public class AbstractTestCase {

	@BeforeClass
	public static void setupClass() {
		ActivityLifecycleHandler.setSkipRealCalls(true);

		Injector.set(AndroidHandler.class, AndroidHandlerMock.class);
		Injector.set(ViewBinder.class, ViewBinderMock.class);

		Injector.set(PersistenceLayer.class, MockedSharedPreferencesPersistenceLayer.class);
		Injector.get(PersistenceLayer.class).init(null);

		RxJavaTestScheduler.init();
	}

	@Before
	public void setup() {
		AndroidHandlerMock handler = (AndroidHandlerMock) Injector.get(AndroidHandler.class);
		handler.reset();

		Injector.get(PersistenceLayer.class).deleteAll();

		Injector.get(CartManager.class).clear();
	}
}
