package br.com.lacomida.plate.fragment;

import br.com.lacomida.base.fragment.BaseFragment;
import br.com.lacomida.plate.presenter.PlatePresenter;
import br.com.lacomida.plate.view.PlateView;

public class PlateFragment extends BaseFragment<PlateView, PlatePresenter> {

	@Override
	public Class<PlateView> getMvpViewClass() {
		return PlateView.class;
	}

	@Override
	public Class<PlatePresenter> presenterClass() {
		return PlatePresenter.class;
	}
}
