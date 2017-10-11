package br.com.lacomida.cart.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import br.com.lacomida.base.activity.BaseActivity;
import br.com.lacomida.cart.presenter.CartPresenter;
import br.com.lacomida.cart.view.CartView;

public class CartActivity extends BaseActivity<CartView, CartPresenter> {

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view.setupToolbar();
	}

	@Override
	public void onBackPressed() {
		view.onBackPressed();
		super.onBackPressed();
	}

	@Override
	public Class<CartView> getMvpViewClass() {
		return CartView.class;
	}

	@Override
	public Class<CartPresenter> presenterClass() {
		return CartPresenter.class;
	}
}
