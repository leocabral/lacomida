package br.com.lacomida.api.checkout.method;

import com.google.gson.reflect.TypeToken;

import java.util.List;

import br.com.lacomida.cart.model.PaymentMethod;
import br.com.lacomida.util.FileUtils;
import io.reactivex.Observable;
import io.reactivex.Single;

public class PaymentMethodRepositoryMock extends PaymentMethodRepository {

	private Single result;

	@Override
	public Single<List<PaymentMethod>> get() {
		if (result != null) {
			return result;
		}
		List<PaymentMethod> paymentMethods = FileUtils.readJson("payment/payment.json", new TypeToken<List<PaymentMethod>>() {
		}.getType());
		return Observable.fromArray(paymentMethods.toArray(new PaymentMethod[]{}))
				.toList();
	}

	public PaymentMethodRepositoryMock setResult(Single<Object> result) {
		this.result = result;
		return this;
	}
}
