package br.com.lacomida.welcome.view;

import android.content.Intent;

import br.com.lacomida.base.MvpView;
import br.com.lacomida.home.activity.HomeActivity;
import br.com.lacomida.welcome.activity.SplashActivity;

public class SplashView implements MvpView<SplashActivity> {

	private SplashActivity activity;

	@Override
	public int getLayoutResId() {
		return 0;
	}

	@Override
	public void attachController(SplashActivity controller) {
		this.activity = controller;
	}

	public void showHomeScreen() {
		activity.startActivity(new Intent(activity, HomeActivity.class));
		activity.finish();
	}
}
