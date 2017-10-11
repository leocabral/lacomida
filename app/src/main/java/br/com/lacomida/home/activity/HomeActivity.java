package br.com.lacomida.home.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import br.com.lacomida.R;
import br.com.lacomida.base.activity.BaseActivity;
import br.com.lacomida.home.presenter.HomePresenter;
import br.com.lacomida.home.view.HomeView;

public class HomeActivity extends BaseActivity<HomeView, HomePresenter> {

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view.setupToolbar();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_cart) {
			view.showCart();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public Class<HomeView> getMvpViewClass() {
		return HomeView.class;
	}

	@Override
	public Class<HomePresenter> presenterClass() {
		return HomePresenter.class;
	}
}
