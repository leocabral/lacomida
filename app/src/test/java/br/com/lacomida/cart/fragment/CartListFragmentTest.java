package br.com.lacomida.cart.fragment;

import org.junit.Test;

import java.util.List;

import br.com.lacomida.base.manager.ViewManager;
import br.com.lacomida.cart.manager.CartManager;
import br.com.lacomida.cart.view.CartListView;
import br.com.lacomida.plate.model.Plate;
import br.com.lacomida.util.AbstractTestCase;
import br.com.lacomida.util.Injector;
import br.com.lacomida.util.TestFlow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class CartListFragmentTest extends AbstractTestCase {

	@Test
	public void testShowEmptyCart() {
		ViewManager viewManager = Injector.get(ViewManager.class);
		viewManager.mock(CartListView.class, CartListViewMock.class);

		TestFlow.instantiate(CartListFragment.class);

		CartListViewMock cartListView = (CartListViewMock) viewManager.get(CartListView.class);
		assertNull(cartListView.getPlates());
		assertTrue(cartListView.willShowEmptyView);
	}

	@Test
	public void testShowCart() {
		CartManager cartManager = Injector.get(CartManager.class);
		cartManager.addPlate(new Plate().setId(1L));
		cartManager.addPlate(new Plate().setId(2L));

		ViewManager viewManager = Injector.get(ViewManager.class);
		viewManager.mock(CartListView.class, CartListViewMock.class);

		TestFlow.instantiate(CartListFragment.class);

		CartListViewMock cartListView = (CartListViewMock) viewManager.get(CartListView.class);
		assertNotNull(cartListView.getPlates());
		assertEquals(2, cartListView.getPlates().size());
	}

	@Test
	public void testRemoveFromCart() {
		CartManager cartManager = Injector.get(CartManager.class);
		cartManager.addPlate(new Plate().setId(1L));
		cartManager.addPlate(new Plate().setId(3L));

		ViewManager viewManager = Injector.get(ViewManager.class);
		viewManager.mock(CartListView.class, CartListViewMock.class);

		TestFlow.instantiate(CartListFragment.class);

		CartListViewMock cartListView = (CartListViewMock) viewManager.get(CartListView.class);
		cartListView.removePlateClicked(0);

		assertEquals(0, cartListView.willRemoveAt);
		assertEquals(1, cartManager.load().size());

		cartListView.removePlateClicked(0);
		assertTrue(cartListView.willShowEmptyView);
	}

	public static class CartListViewMock extends CartListView {

		private boolean willShowEmptyView;
		private int willRemoveAt;

		@Override
		public void showCart(List<Plate> plates) {
			this.plates = plates;
		}

		@Override
		public void showEmptyView() {
			willShowEmptyView = true;
		}

		@Override
		protected void removeFromRecyclerView(int position) {
			willRemoveAt = position;
		}

		public List<Plate> getPlates() {
			return plates;
		}
	}
}