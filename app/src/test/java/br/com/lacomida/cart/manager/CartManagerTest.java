package br.com.lacomida.cart.manager;

import org.junit.Test;

import java.util.List;

import br.com.lacomida.plate.model.Plate;
import br.com.lacomida.util.AbstractTestCase;
import br.com.lacomida.util.Injector;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CartManagerTest extends AbstractTestCase {

	@Test
	public void testCart() {
		CartManager cartManager = Injector.get(CartManager.class);

		List<Plate> cart = cartManager.load();
		assertNotNull(cart);
		assertEquals(0, cart.size());

		Plate pizza = new Plate().setId(1L);
		cartManager.addPlate(pizza);

		cart = cartManager.load();
		assertEquals(1, cart.size());

		boolean removed = cartManager.removePlate(pizza);
		assertTrue(removed);

		cart = cartManager.load();
		assertEquals(0, cart.size());

		Plate bread = new Plate().setId(2L);

		removed = cartManager.removePlate(bread);
		assertFalse(removed);
		assertEquals(0, cart.size());
	}

	@Test
	public void testCartEmitter() {
		CartManager cartManager = Injector.get(CartManager.class);

		Observable<Integer> observable = cartManager.cartChangedObserver();
		TestObserver<Integer> test = observable.test();

		Plate pizza = new Plate().setId(1L);

		cartManager.addPlate(pizza);
		cartManager.addPlate(new Plate());
		cartManager.removePlate(pizza);

		test.assertSubscribed()
				.assertValueAt(0, integer -> integer.equals(1))
				.assertValueAt(1, integer -> integer.equals(2))
				.assertValueAt(2, integer -> integer.equals(1));
	}
}