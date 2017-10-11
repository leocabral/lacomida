package br.com.lacomida.welcome.activity;

import br.com.lacomida.base.activity.BaseActivity;
import br.com.lacomida.welcome.presenter.SplashPresenter;
import br.com.lacomida.welcome.view.SplashView;

public class SplashActivity extends BaseActivity<SplashView, SplashPresenter> {

	@Override
	public void onResume() {
		super.onResume();
		presenter.showNextScreenDelayed();
	}

	@Override
	public void onPause() {
		super.onPause();
		presenter.cancelHandlerCallback();
	}

	@Override
	public Class<SplashView> getMvpViewClass() {
		return SplashView.class;
	}

	@Override
	public Class<SplashPresenter> presenterClass() {
		return SplashPresenter.class;
	}
}