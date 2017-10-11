package br.com.lacomida.cart.fragment;

import br.com.lacomida.base.fragment.BaseFragment;
import br.com.lacomida.cart.presenter.CheckoutSucceededPresenter;
import br.com.lacomida.cart.view.CheckoutSucceededView;

public class CheckoutSucceededFragment extends BaseFragment<CheckoutSucceededView, CheckoutSucceededPresenter> {

	@Override
	public Class<CheckoutSucceededView> getMvpViewClass() {
		return CheckoutSucceededView.class;
	}

	@Override
	public Class<CheckoutSucceededPresenter> presenterClass() {
		return CheckoutSucceededPresenter.class;
	}
}
