package br.com.lacomida.common;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import br.com.lacomida.cart.manager.CartManager;
import br.com.lacomida.persistence.MockedSharedPreferencesPersistenceLayer;
import br.com.lacomida.persistence.PersistenceLayer;
import br.com.lacomida.util.Injector;
import br.com.lacomida.util.RxJavaTestScheduler;
import io.reactivex.schedulers.Schedulers;

@RunWith(AndroidJUnit4.class)
public abstract class AbstractEspressoTestCase<T extends TestRule> {

	protected T testRule;

	@BeforeClass
	public static void setupClass() {
		Injector.set(PersistenceLayer.class, MockedSharedPreferencesPersistenceLayer.class);
		Injector.get(PersistenceLayer.class).init(null);

		setupRxScheduler();
	}

	private static void setupRxScheduler() {
		CountingIdlingResource idlingResource = new CountingIdlingResource("rxJava");
		IdlingRegistry.getInstance().register(idlingResource);

		RxJavaTestScheduler.init(s -> Schedulers.from(runnable -> {
			idlingResource.increment();
			new Thread(() -> {
				runnable.run();
				idlingResource.decrement();
			}).start();
		}));
	}

	@Before
	public void setup() {
		Injector.get(PersistenceLayer.class).deleteAll();

		Injector.get(CartManager.class).clear();
	}

	@Rule
	public final T applyTestRule() {
		testRule = buildTestRule();
		return testRule;
	}

	protected abstract T buildTestRule();
}
