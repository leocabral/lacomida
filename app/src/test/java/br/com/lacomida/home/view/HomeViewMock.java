package br.com.lacomida.home.view;

import android.os.Bundle;

public class HomeViewMock extends HomeView {

	public Class<?> fragmentToBeShown;
	public Bundle currentExtras;

	@Override
	public void setupToolbar() {
	}

	@Override
	public void setToolbarTitle(String title) {
	}

	@Override
	public void setToolbarTitle(int stringRes) {
	}

	@Override
	public void showFragment(Class<?> fragmentClass, Bundle extras, boolean addToBackStack) {
		fragmentToBeShown = fragmentClass;
		currentExtras = extras;
	}
}
