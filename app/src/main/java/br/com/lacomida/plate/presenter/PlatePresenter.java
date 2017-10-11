package br.com.lacomida.plate.presenter;

import br.com.lacomida.base.presenter.BasePresenter;
import br.com.lacomida.plate.view.PlateView;

public class PlatePresenter extends BasePresenter<PlateView> {

	@Override
	public void onViewAttached() {
		super.onViewAttached();
		getView().populate();
	}
}
