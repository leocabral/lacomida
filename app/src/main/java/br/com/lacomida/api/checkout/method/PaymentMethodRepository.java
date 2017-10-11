package br.com.lacomida.api.checkout.method;

import java.util.List;

import br.com.lacomida.api.base.repository.Repository;
import br.com.lacomida.cart.model.PaymentMethod;
import io.reactivex.Single;

public class PaymentMethodRepository extends Repository<PaymentMethodEndpoint> {

	public Single<List<PaymentMethod>> get() {
		return getEndpoint().get();
	}

	@Override
	protected Class<PaymentMethodEndpoint> endpointClass() {
		return PaymentMethodEndpoint.class;
	}
}
