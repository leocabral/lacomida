package br.com.lacomida.cart.presenter;

import br.com.lacomida.base.presenter.BasePresenter;
import br.com.lacomida.cart.view.CheckoutSucceededView;

public class CheckoutSucceededPresenter extends BasePresenter<CheckoutSucceededView> {

	@Override
	public void onViewAttached() {
		super.onViewAttached();
		getView().showSuccessMessage();
	}
}
