package br.com.lacomida.api.checkout;

import br.com.lacomida.cart.model.CheckoutStatus;
import io.reactivex.Single;
import retrofit2.http.POST;

public interface CheckoutEndpoint {

	@POST("checkout")
	Single<CheckoutStatus> checkout();
}
