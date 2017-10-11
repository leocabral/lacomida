package br.com.lacomida.home.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import org.parceler.Parcels;

import br.com.lacomida.R;
import br.com.lacomida.base.MvpView;
import br.com.lacomida.cart.activity.CartActivity;
import br.com.lacomida.home.activity.HomeActivity;
import br.com.lacomida.menu.fragment.RestaurantMenuFragment;
import br.com.lacomida.menu.view.RestaurantMenuView;
import br.com.lacomida.plate.fragment.PlateFragment;
import br.com.lacomida.plate.model.Plate;
import br.com.lacomida.plate.view.PlateView;
import br.com.lacomida.restaurant.fragment.RestaurantListFragment;
import br.com.lacomida.ui.FragmentHelper;
import br.com.lacomida.util.Injector;
import butterknife.BindView;

public class HomeView implements MvpView<HomeActivity> {

	@BindView(R.id.home_toolbar)
	Toolbar toolbar;

	protected HomeActivity activity;

	public void setupToolbar() {
		activity.setSupportActionBar(toolbar);
	}

	public void showRestaurantFragment() {
		String title = activity.getString(R.string.home_title);

		showFragment(RestaurantListFragment.class, null, false);
		setToolbarTitle(title);
	}

	public void showMenuFragment(Long restaurantId, String restaurantName) {
		Bundle extras = new Bundle();
		extras.putLong(RestaurantMenuView.RESTAURANT_ID_BUNDLE_KEY, restaurantId);
		extras.putString(RestaurantMenuView.RESTAURANT_NAME_BUNDLE_KEY, restaurantName);

		showFragment(RestaurantMenuFragment.class, extras, true);
	}

	public void showPlateFragment(Plate plate) {
		Bundle extras = new Bundle();
		extras.putParcelable(PlateView.PLATE_BUNDLE_KEY, Parcels.wrap(plate));

		showFragment(PlateFragment.class, extras, true);
	}

	protected void showFragment(Class<?> fragmentClass, Bundle extras, boolean addToBackStack) {
		FragmentHelper fragmentHelper = Injector.get(FragmentHelper.class);
		fragmentHelper.add(R.id.home_fragment_container, activity.getSupportFragmentManager(),
				activity, fragmentClass, extras, addToBackStack);
	}

	public void showCart() {
		activity.startActivity(new Intent(activity, CartActivity.class));
	}

	public void setToolbarTitle(int stringRes) {
		setToolbarTitle(activity.getString(stringRes));
	}

	public void setToolbarTitle(String title) {
		ActionBar actionBar = activity.getSupportActionBar();
		if (actionBar != null) {
			actionBar.setTitle(title);
		}
	}

	@Override
	public void attachController(HomeActivity controller) {
		this.activity = controller;
	}

	@Override
	public int getLayoutResId() {
		return R.layout.activity_home;
	}
}
