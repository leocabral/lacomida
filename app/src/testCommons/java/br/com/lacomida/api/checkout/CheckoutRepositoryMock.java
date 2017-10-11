package br.com.lacomida.api.checkout;

import br.com.lacomida.cart.model.CheckoutStatus;
import io.reactivex.Single;

public class CheckoutRepositoryMock extends CheckoutRepository {

	private Single result;

	@Override
	public Single<CheckoutStatus> checkout() {
		if (result != null) {
			return result;
		}
		CheckoutStatus checkoutStatus = new CheckoutStatus();
		checkoutStatus.setMessage("Seu pedido foi realizado").setStatus("SUCCESS");
		return Single.just(checkoutStatus);
	}

	public CheckoutRepositoryMock setResult(Single result) {
		this.result = result;
		return this;
	}
}