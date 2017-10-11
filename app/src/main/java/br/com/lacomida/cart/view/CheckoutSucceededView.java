package br.com.lacomida.cart.view;

import android.os.Bundle;
import android.widget.TextView;

import br.com.lacomida.R;
import br.com.lacomida.base.MvpView;
import br.com.lacomida.cart.fragment.CheckoutSucceededFragment;
import butterknife.BindView;

public class CheckoutSucceededView implements MvpView<CheckoutSucceededFragment> {

	public static final String TEXT_BUNDLE_KEY = "text";

	@BindView(R.id.checkout_succeeded_text)
	TextView successTextView;

	private CheckoutSucceededFragment fragment;

	public void showSuccessMessage() {
		String message = getMessage();
		successTextView.setText(message);
	}

	protected String getMessage() {
		Bundle arguments = fragment.getArguments();
		if (arguments == null) {
			return null;
		}
		return arguments.getString(TEXT_BUNDLE_KEY);
	}

	@Override
	public void attachController(CheckoutSucceededFragment controller) {
		this.fragment = controller;
	}

	@Override
	public int getLayoutResId() {
		return R.layout.fragment_checkout_succeeded;
	}
}
