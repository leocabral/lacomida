package br.com.lacomida.cart.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import br.com.lacomida.R;
import br.com.lacomida.base.MvpView;
import br.com.lacomida.cart.activity.CartActivity;
import br.com.lacomida.cart.fragment.CartListFragment;
import br.com.lacomida.cart.fragment.CheckoutFragment;
import br.com.lacomida.cart.fragment.CheckoutSucceededFragment;
import br.com.lacomida.cart.model.CheckoutStatus;
import br.com.lacomida.ui.FragmentHelper;
import br.com.lacomida.util.Injector;
import butterknife.BindView;
import butterknife.OnClick;

public class CartView implements MvpView<CartActivity> {

	@BindView(R.id.cart_toolbar)
	Toolbar toolbar;

	@BindView(R.id.cart_checkout_container)
	View checkoutView;

	private CartActivity activity;

	public void setupToolbar() {
		activity.setSupportActionBar(toolbar);
		ActionBar actionBar = activity.getSupportActionBar();
		if (actionBar != null) {
			actionBar.setTitle(R.string.my_cart);
		}
	}

	public void showCart() {
		showFragment(CartListFragment.class, null, false);
	}

	private void showCheckoutFragment() {
		showFragment(CheckoutFragment.class, null, true);
		toggleCheckoutViewVisibility(false);
	}

	public void showCheckoutSucceeded(CheckoutStatus status) {
		Bundle extras = new Bundle();
		extras.putString(CheckoutSucceededView.TEXT_BUNDLE_KEY, status.getMessage());

		showFragment(CheckoutSucceededFragment.class, extras, false);
		toggleCheckoutViewVisibility(false);

		clearBackStack();
	}

	protected void showFragment(Class<?> fragmentClass, Bundle extras, boolean addToBackStack) {
		FragmentHelper fragmentHelper = Injector.get(FragmentHelper.class);
		fragmentHelper.add(R.id.cart_fragment_container,
				activity.getSupportFragmentManager(), activity, fragmentClass,
				extras, addToBackStack);
	}

	public void toggleCheckoutViewVisibility(boolean visible) {
		checkoutView.setVisibility(visible ? View.VISIBLE : View.GONE);
	}

	public void onBackPressed() {
		FragmentManager fm = activity.getSupportFragmentManager();
		if (fm.getBackStackEntryCount() == 1) {
			toggleCheckoutViewVisibility(true);
		}
	}

	protected void clearBackStack() {
		Injector.get(FragmentHelper.class).clearBackStack(activity.getSupportFragmentManager());
	}

	@OnClick(R.id.cart_checkout_container)
	public void checkoutClicked() {
		showCheckoutFragment();
	}

	@Override
	public void attachController(CartActivity controller) {
		this.activity = controller;
	}

	@Override
	public int getLayoutResId() {
		return R.layout.activity_cart;
	}
}
