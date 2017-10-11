package br.com.lacomida.restaurant.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import br.com.lacomida.R;
import br.com.lacomida.base.fragment.BaseFragment;
import br.com.lacomida.base.manager.ViewManager;
import br.com.lacomida.home.view.HomeView;
import br.com.lacomida.restaurant.presenter.RestaurantListPresenter;
import br.com.lacomida.restaurant.view.RestaurantListView;
import br.com.lacomida.util.Injector;

public class RestaurantListFragment extends BaseFragment<RestaurantListView, RestaurantListPresenter> {

	@Override
	public void onViewCreated(View fragmentView, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(fragmentView, savedInstanceState);
		view.setupRecyclerView();
	}

	@Override
	public void onResume() {
		super.onResume();
		setToolbarTitle();
	}

	private void setToolbarTitle() {
		ViewManager viewManager = Injector.get(ViewManager.class);
		viewManager.get(HomeView.class).setToolbarTitle(R.string.home_title);
	}

	@Override
	public Class<RestaurantListView> getMvpViewClass() {
		return RestaurantListView.class;
	}

	@Override
	public Class<RestaurantListPresenter> presenterClass() {
		return RestaurantListPresenter.class;
	}
}
