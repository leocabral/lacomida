package br.com.lacomida.cart.presenter;

import java.util.List;

import br.com.lacomida.api.checkout.CheckoutRepository;
import br.com.lacomida.api.checkout.method.PaymentMethodRepository;
import br.com.lacomida.base.presenter.BasePresenter;
import br.com.lacomida.cart.manager.CartManager;
import br.com.lacomida.cart.model.CheckoutStatus;
import br.com.lacomida.cart.model.PaymentMethod;
import br.com.lacomida.cart.view.CheckoutView;
import br.com.lacomida.util.Injector;
import io.reactivex.schedulers.Schedulers;

public class CheckoutPresenter extends BasePresenter<CheckoutView> {

	@Override
	public void onViewAttached() {
		super.onViewAttached();
		loadPaymentMethods();
	}

	private void loadPaymentMethods() {
		PaymentMethodRepository repository = Injector.get(PaymentMethodRepository.class);
		repository.get()
				.toObservable()
				.flatMapIterable(paymentMethods -> paymentMethods)
				.filter(PaymentMethod::getEnabled)
				.toList()
				.subscribeOn(Schedulers.newThread())
				.subscribe(this::paymentsOnNext, this::paymentsOnError);
	}

	private void paymentsOnNext(List<PaymentMethod> paymentMethods) {
		if (!viewIsAlive()) {
			return;
		}
		if (paymentMethods == null || paymentMethods.isEmpty()) {
			getView().showError();
			return;
		}
		getView().updatePaymentMethods(paymentMethods);
	}

	private void paymentsOnError(Throwable throwable) {
		if (viewIsAlive()) {
			getView().showError();
		}
	}

	public void checkout(PaymentMethod method) {
		CheckoutRepository repository = Injector.get(CheckoutRepository.class);
		repository.checkout()
				.subscribeOn(Schedulers.newThread())
				.subscribe(this::checkoutOnNext, this::checkoutOnError);
	}

	private void checkoutOnNext(CheckoutStatus checkoutStatus) {
		if (!viewIsAlive()) {
			return;
		}
		if (!checkoutStatus.getStatus().equals("SUCCESS")) {
			getView().showError();
			return;
		}
		CartManager cartManager = Injector.get(CartManager.class);
		cartManager.clear();

		getView().showSuccess(checkoutStatus);
	}

	private void checkoutOnError(Throwable throwable) {
		if (viewIsAlive()) {
			getView().showError();
		}
	}
}
