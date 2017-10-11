package br.com.lacomida.menu.view;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.lacomida.R;
import br.com.lacomida.base.MvpView;
import br.com.lacomida.base.manager.ViewManager;
import br.com.lacomida.home.view.HomeView;
import br.com.lacomida.menu.fragment.RestaurantMenuFragment;
import br.com.lacomida.plate.model.Plate;
import br.com.lacomida.plate.view.PlateViewHolder;
import br.com.lacomida.ui.adapter.RecyclerViewGenericAdapter;
import br.com.lacomida.util.Injector;
import butterknife.BindView;

public class RestaurantMenuView implements MvpView<RestaurantMenuFragment> {

	public static final String RESTAURANT_ID_BUNDLE_KEY = "restaurantId";
	public static final String RESTAURANT_NAME_BUNDLE_KEY = "restaurantName";

	@BindView(R.id.menu_recycler_view)
	RecyclerView recyclerView;

	@BindView(R.id.unknown_error_container)
	View unknownErrorContainer;

	private RestaurantMenuFragment fragment;
	private List<Plate> plates;

	public void setupRecyclerView() {
		recyclerView.setLayoutManager(new GridLayoutManager(fragment.getActivity(), 2));
		recyclerView.setAdapter(new RecyclerViewGenericAdapter<>(this::createViewHolder, this::bindViewHolder)
				.withClickListener(this::plateSelected));
	}

	private PlateViewHolder createViewHolder(ViewGroup parent, Integer viewType) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View view = inflater.inflate(R.layout.plate_cell, parent, false);
		return new PlateViewHolder(view);
	}

	private Void bindViewHolder(PlateViewHolder viewHolder, Integer position) {
		Plate plate = plates.get(position);

		viewHolder.name.setText(plate.getName());
		viewHolder.description.setText(plate.getDescription());
		viewHolder.price.setText(String.valueOf(plate.getPrice()));
		viewHolder.image.setImageURI(plate.getImageUrl());
		return null;
	}

	public void showError() {
		fragment.getActivity().runOnUiThread(() -> unknownErrorContainer.setVisibility(View.VISIBLE));
	}

	public void showPlates(List<Plate> plates) {
		this.plates = plates;
		fragment.getActivity().runOnUiThread(() -> {
			RecyclerViewGenericAdapter adapter = (RecyclerViewGenericAdapter) recyclerView.getAdapter();
			adapter.withItems(plates);
		});
	}

	private void plateSelected(int position) {
		Plate plate = plates.get(position);

		ViewManager viewManager = Injector.get(ViewManager.class);
		HomeView homeView = viewManager.get(HomeView.class);
		homeView.showPlateFragment(plate);
	}

	public Long getRestaurantId() {
		Bundle arguments = fragment.getArguments();
		if (arguments == null) {
			return null;
		}
		return arguments.getLong(RESTAURANT_ID_BUNDLE_KEY);
	}

	protected String getRestaurantName() {
		Bundle arguments = fragment.getArguments();
		if (arguments == null) {
			return null;
		}
		return arguments.getString(RESTAURANT_NAME_BUNDLE_KEY);
	}

	@Override
	public void attachController(RestaurantMenuFragment controller) {
		this.fragment = controller;
	}

	@Override
	public int getLayoutResId() {
		return R.layout.fragment_restaurant_menu;
	}
}
