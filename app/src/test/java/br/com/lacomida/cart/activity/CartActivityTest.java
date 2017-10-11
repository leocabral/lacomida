package br.com.lacomida.cart.activity;

import android.os.Bundle;

import org.junit.Test;

import br.com.lacomida.base.manager.ViewManager;
import br.com.lacomida.cart.fragment.CartListFragment;
import br.com.lacomida.cart.fragment.CheckoutFragment;
import br.com.lacomida.cart.fragment.CheckoutSucceededFragment;
import br.com.lacomida.cart.model.CheckoutStatus;
import br.com.lacomida.cart.view.CartView;
import br.com.lacomida.util.AbstractTestCase;
import br.com.lacomida.util.Injector;
import br.com.lacomida.util.TestFlow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class CartActivityTest extends AbstractTestCase {

	@Test
	public void testShowCart() {
		ViewManager viewManager = Injector.get(ViewManager.class);
		viewManager.mock(CartView.class, CartViewMock.class);

		TestFlow.instantiate(CartActivity.class);

		CartViewMock cartView = (CartViewMock) viewManager.get(CartView.class);
		assertEquals(CartListFragment.class, cartView.fragmentToBeShown);
		assertEquals(null, cartView.extras);
	}

	@Test
	public void testCheckout() {
		ViewManager viewManager = Injector.get(ViewManager.class);
		viewManager.mock(CartView.class, CartViewMock.class);

		TestFlow.instantiate(CartActivity.class);

		CartViewMock cartView = (CartViewMock) viewManager.get(CartView.class);
		cartView.checkoutClicked();
		assertEquals(CheckoutFragment.class, cartView.fragmentToBeShown);
		assertEquals(null, cartView.extras);
		assertFalse(cartView.checkoutIsVisible());
	}

	@Test
	public void testCheckoutSucceeded() {
		ViewManager viewManager = Injector.get(ViewManager.class);
		viewManager.mock(CartView.class, CartViewMock.class);

		TestFlow.instantiate(CartActivity.class);

		CartViewMock cartView = (CartViewMock) viewManager.get(CartView.class);
		cartView.showCheckoutSucceeded(new CheckoutStatus().setMessage("sucesso"));

		assertEquals(CheckoutSucceededFragment.class, cartView.fragmentToBeShown);
		assertNotNull(cartView.extras);
		assertFalse(cartView.checkoutIsVisible());
	}

	public static class CartViewMock extends CartView {

		private Class<?> fragmentToBeShown;
		private Bundle extras;
		private boolean checkoutIsVisible;

		@Override
		public void setupToolbar() {
		}

		@Override
		protected void showFragment(Class<?> fragmentClass, Bundle extras, boolean addToBackStack) {
			this.fragmentToBeShown = fragmentClass;
			this.extras = extras;
		}

		@Override
		public void toggleCheckoutViewVisibility(boolean visible) {
			this.checkoutIsVisible = visible;
		}

		public boolean checkoutIsVisible() {
			return checkoutIsVisible;
		}

		@Override
		protected void clearBackStack() {
		}
	}
}