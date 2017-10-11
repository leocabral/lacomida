package br.com.lacomida.api.checkout;

import br.com.lacomida.api.base.repository.Repository;
import br.com.lacomida.cart.model.CheckoutStatus;
import io.reactivex.Single;

public class CheckoutRepository extends Repository<CheckoutEndpoint> {

	public Single<CheckoutStatus> checkout() {
		return getEndpoint().checkout();
	}

	@Override
	protected Class<CheckoutEndpoint> endpointClass() {
		return CheckoutEndpoint.class;
	}
}
