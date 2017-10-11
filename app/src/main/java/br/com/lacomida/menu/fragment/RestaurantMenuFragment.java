package br.com.lacomida.menu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import br.com.lacomida.base.fragment.BaseFragment;
import br.com.lacomida.menu.presenter.RestaurantMenuPresenter;
import br.com.lacomida.menu.view.RestaurantMenuView;

public class RestaurantMenuFragment extends BaseFragment<RestaurantMenuView, RestaurantMenuPresenter> {

	@Override
	public void onViewCreated(View fragmentView, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(fragmentView, savedInstanceState);
		view.setupRecyclerView();
	}

	@Override
	public Class<RestaurantMenuView> getMvpViewClass() {
		return RestaurantMenuView.class;
	}

	@Override
	public Class<RestaurantMenuPresenter> presenterClass() {
		return RestaurantMenuPresenter.class;
	}
}
