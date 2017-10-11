package br.com.lacomida.cart.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import br.com.lacomida.base.fragment.BaseFragment;
import br.com.lacomida.cart.presenter.CheckoutPresenter;
import br.com.lacomida.cart.view.CheckoutView;

public class CheckoutFragment extends BaseFragment<CheckoutView, CheckoutPresenter> {

	@Override
	public void onViewCreated(View fragmentView, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(fragmentView, savedInstanceState);
		view.setupRecyclerView();
	}

	@Override
	public Class<CheckoutView> getMvpViewClass() {
		return CheckoutView.class;
	}

	@Override
	public Class<CheckoutPresenter> presenterClass() {
		return CheckoutPresenter.class;
	}
}
