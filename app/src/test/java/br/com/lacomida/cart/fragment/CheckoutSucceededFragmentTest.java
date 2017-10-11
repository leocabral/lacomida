package br.com.lacomida.cart.fragment;

import org.junit.Test;

import br.com.lacomida.base.manager.ViewManager;
import br.com.lacomida.cart.view.CheckoutSucceededView;
import br.com.lacomida.util.AbstractTestCase;
import br.com.lacomida.util.Injector;
import br.com.lacomida.util.TestFlow;

import static org.junit.Assert.assertTrue;

public class CheckoutSucceededFragmentTest extends AbstractTestCase {

	@Test
	public void testShowFragment() {
		ViewManager viewManager = Injector.get(ViewManager.class);
		viewManager.mock(CheckoutSucceededView.class, CheckoutSucceededViewMock.class);

		TestFlow.instantiate(CheckoutSucceededFragment.class);

		CheckoutSucceededViewMock view = (CheckoutSucceededViewMock) viewManager.get(CheckoutSucceededView.class);
		assertTrue(view.willShowMessage);
	}

	public static class CheckoutSucceededViewMock extends CheckoutSucceededView {

		private boolean willShowMessage;

		@Override
		public void showSuccessMessage() {
			this.willShowMessage = true;
		}

		@Override
		protected String getMessage() {
			return "success";
		}
	}
}