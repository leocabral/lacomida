package br.com.lacomida.api.checkout.method;

import java.util.List;

import br.com.lacomida.cart.model.PaymentMethod;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface PaymentMethodEndpoint {

	@GET("payments")
	Single<List<PaymentMethod>> get();
}
