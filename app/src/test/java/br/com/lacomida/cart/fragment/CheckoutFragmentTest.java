package br.com.lacomida.cart.fragment;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import br.com.lacomida.api.checkout.CheckoutRepository;
import br.com.lacomida.api.checkout.CheckoutRepositoryMock;
import br.com.lacomida.api.checkout.method.PaymentMethodRepository;
import br.com.lacomida.api.checkout.method.PaymentMethodRepositoryMock;
import br.com.lacomida.base.manager.ViewManager;
import br.com.lacomida.cart.model.CheckoutStatus;
import br.com.lacomida.cart.model.PaymentMethod;
import br.com.lacomida.cart.view.CheckoutView;
import br.com.lacomida.util.AbstractTestCase;
import br.com.lacomida.util.Injector;
import br.com.lacomida.util.TestFlow;
import io.reactivex.Single;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckoutFragmentTest extends AbstractTestCase {

	@Before
	public void setup() {
		super.setup();
		Injector.set(PaymentMethodRepository.class, PaymentMethodRepositoryMock.class);
		Injector.set(CheckoutRepository.class, CheckoutRepositoryMock.class);
	}

	@Test
	public void testShowCheckout() {
		ViewManager viewManager = Injector.get(ViewManager.class);
		viewManager.mock(CheckoutView.class, CheckoutViewMock.class);

		TestFlow.instantiate(CheckoutFragment.class);

		CheckoutViewMock view = (CheckoutViewMock) viewManager.get(CheckoutView.class);
		assertEquals(3, view.getMethods().size());
	}

	@Test
	public void testShowCheckoutWithError() {
		ViewManager viewManager = Injector.get(ViewManager.class);
		viewManager.mock(CheckoutView.class, CheckoutViewMock.class);

		PaymentMethodRepositoryMock repository = (PaymentMethodRepositoryMock) Injector.get(PaymentMethodRepository.class);
		repository.setResult(Single.error(new RuntimeException("")));

		TestFlow.instantiate(CheckoutFragment.class);

		CheckoutViewMock view = (CheckoutViewMock) viewManager.get(CheckoutView.class);
		assertTrue(view.willShowError);
	}

	@Test
	public void testCheckoutWithError() {
		ViewManager viewManager = Injector.get(ViewManager.class);
		viewManager.mock(CheckoutView.class, CheckoutViewMock.class);

		CheckoutRepositoryMock repository = (CheckoutRepositoryMock) Injector.get(CheckoutRepository.class);
		repository.setResult(Single.error(new RuntimeException("")));

		TestFlow.instantiate(CheckoutFragment.class);

		CheckoutViewMock view = (CheckoutViewMock) viewManager.get(CheckoutView.class);
		view.paymentMethodChosen(0);
		assertTrue(view.willShowError);
	}

	@Test
	public void testCheckout() {
		ViewManager viewManager = Injector.get(ViewManager.class);
		viewManager.mock(CheckoutView.class, CheckoutViewMock.class);

		TestFlow.instantiate(CheckoutFragment.class);

		CheckoutViewMock view = (CheckoutViewMock) viewManager.get(CheckoutView.class);
		view.paymentMethodChosen(0);

		assertEquals("SUCCESS", view.status.getStatus());
	}

	public static class CheckoutViewMock extends CheckoutView {

		private boolean willShowError;
		private CheckoutStatus status;

		@Override
		public void setupRecyclerView() {
		}

		@Override
		public void updatePaymentMethods(List<PaymentMethod> paymentMethods) {
			this.methods = paymentMethods;
		}

		@Override
		public void showError() {
			this.willShowError = true;
		}

		@Override
		public void showSuccess(CheckoutStatus status) {
			this.status = status;
		}

		public List<PaymentMethod> getMethods() {
			return methods;
		}
	}
}