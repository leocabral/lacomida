package br.com.lacomida.welcome.presenter;

import br.com.lacomida.base.presenter.BasePresenter;
import br.com.lacomida.util.AndroidHandler;
import br.com.lacomida.util.Injector;
import br.com.lacomida.welcome.view.SplashView;

public class SplashPresenter extends BasePresenter<SplashView> {

	private static final int SPLASH_TIMEOUT = 1200;

	private AndroidHandler handler;
	private Runnable runnable;

	public void showNextScreenDelayed() {
		handler = Injector.get(AndroidHandler.class).get();

		runnable = getView()::showHomeScreen;
		handler.postDelayed(runnable, SPLASH_TIMEOUT);
	}

	public void cancelHandlerCallback() {
		handler.removeCallbacks(runnable);
	}
}
