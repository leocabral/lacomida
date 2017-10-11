package br.com.lacomida.cart.view;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import br.com.lacomida.R;
import br.com.lacomida.base.MvpView;
import br.com.lacomida.base.manager.ViewManager;
import br.com.lacomida.cart.fragment.CheckoutFragment;
import br.com.lacomida.cart.model.CheckoutStatus;
import br.com.lacomida.cart.model.PaymentMethod;
import br.com.lacomida.ui.adapter.RecyclerViewGenericAdapter;
import br.com.lacomida.util.Injector;
import butterknife.BindView;

public class CheckoutView implements MvpView<CheckoutFragment> {

	@BindView(R.id.payment_method_recycler_view)
	RecyclerView recyclerView;

	private CheckoutFragment fragment;
	protected List<PaymentMethod> methods;

	public void setupRecyclerView() {
		RecyclerViewGenericAdapter<PaymentMethodViewHolder> adapter = new RecyclerViewGenericAdapter<>(
				this::createViewHolder, this::bindViewHolder);
		adapter.withClickListener(this::paymentMethodChosen);
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(new GridLayoutManager(fragment.getActivity(), 2));
	}

	private PaymentMethodViewHolder createViewHolder(ViewGroup parent, Integer viewType) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View view = inflater.inflate(R.layout.payment_method_cell, parent, false);
		return new PaymentMethodViewHolder(view);
	}

	private Void bindViewHolder(PaymentMethodViewHolder holder, Integer position) {
		PaymentMethod method = methods.get(position);
		holder.name.setText(method.getName());
		return null;
	}

	public void paymentMethodChosen(int position) {
		PaymentMethod method = methods.get(position);
		fragment.getPresenter().checkout(method);
	}

	public void updatePaymentMethods(List<PaymentMethod> paymentMethods) {
		fragment.getActivity().runOnUiThread(() -> {
			RecyclerViewGenericAdapter adapter = (RecyclerViewGenericAdapter) recyclerView.getAdapter();
			adapter.withItems(paymentMethods);
		});
		this.methods = paymentMethods;
	}

	public void showError() {
		Activity activity = fragment.getActivity();
		activity.runOnUiThread(() -> Toast.makeText(activity, R.string.unknown_error, Toast.LENGTH_SHORT).show());
	}

	public void showSuccess(CheckoutStatus status) {
		fragment.getActivity().runOnUiThread(() -> {
			ViewManager viewManager = Injector.get(ViewManager.class);
			CartView cartView = viewManager.get(CartView.class);
			cartView.showCheckoutSucceeded(status);
		});
	}

	@Override
	public void attachController(CheckoutFragment controller) {
		this.fragment = controller;
	}

	@Override
	public int getLayoutResId() {
		return R.layout.fragment_checkout;
	}
}
