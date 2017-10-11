package br.com.lacomida.home.presenter;

import br.com.lacomida.base.presenter.BasePresenter;
import br.com.lacomida.home.view.HomeView;

public class HomePresenter extends BasePresenter<HomeView> {

	@Override
	public void onViewAttached() {
		super.onViewAttached();
		getView().showRestaurantFragment();
	}
}