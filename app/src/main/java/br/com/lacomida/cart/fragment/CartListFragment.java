package br.com.lacomida.cart.fragment;

import br.com.lacomida.base.fragment.BaseFragment;
import br.com.lacomida.cart.presenter.CartListPresenter;
import br.com.lacomida.cart.view.CartListView;

public class CartListFragment extends BaseFragment<CartListView, CartListPresenter> {

	@Override
	public Class<CartListView> getMvpViewClass() {
		return CartListView.class;
	}

	@Override
	public Class<CartListPresenter> presenterClass() {
		return CartListPresenter.class;
	}
}
