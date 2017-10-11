package br.com.lacomida.restaurant.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.lacomida.R;
import br.com.lacomida.base.MvpView;
import br.com.lacomida.base.manager.ViewManager;
import br.com.lacomida.home.model.Restaurant;
import br.com.lacomida.home.view.HomeView;
import br.com.lacomida.home.view.RestaurantViewHolder;
import br.com.lacomida.restaurant.fragment.RestaurantListFragment;
import br.com.lacomida.ui.adapter.RecyclerViewGenericAdapter;
import br.com.lacomida.util.Injector;
import butterknife.BindView;

public class RestaurantListView implements MvpView<RestaurantListFragment> {

	@BindView(R.id.restaurants_recycler_view)
	RecyclerView recyclerView;

	@BindView(R.id.location_disclaimer_container)
	View locationDisclaimerView;

	@BindView(R.id.unknown_error_container)
	View unknownErrorView;

	protected RestaurantListFragment fragment;
	private List<Restaurant> restaurants;

	public void setupRecyclerView() {
		recyclerView.setLayoutManager(new LinearLayoutManager(fragment.getActivity()));
		recyclerView.setAdapter(new RecyclerViewGenericAdapter<>(this::createViewHolder, this::bindViewHolder)
				.withClickListener(this::restaurantClicked));
	}

	private RestaurantViewHolder createViewHolder(ViewGroup parent, Integer viewType) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View view = inflater.inflate(R.layout.restaurant_cell, parent, false);
		return new RestaurantViewHolder(view);
	}

	private Void bindViewHolder(RestaurantViewHolder holder, Integer position) {
		Restaurant restaurant = restaurants.get(position);

		holder.address.setText(restaurant.getAddress());
		holder.name.setText(restaurant.getName());
		holder.description.setText(restaurant.getDescription());
		holder.image.setImageURI(restaurant.getImageUrl());
		holder.rating.setRating(restaurant.getRating());

		Context context = holder.itemView.getContext();
		holder.deliveryFee.setText(context.getString(R.string.delivery_fee,
				String.valueOf(restaurant.getDeliveryFee())));
		return null;
	}

	public void updateResults(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
		fragment.getActivity().runOnUiThread(() -> {

			RecyclerViewGenericAdapter adapter = (RecyclerViewGenericAdapter) recyclerView.getAdapter();
			adapter.withItems(restaurants);
		});
	}

	public void showError() {
		fragment.getActivity().runOnUiThread(() -> unknownErrorView.setVisibility(View.VISIBLE));
	}

	private void restaurantClicked(int position) {
		Restaurant restaurant = restaurants.get(position);

		ViewManager viewManager = Injector.get(ViewManager.class);
		HomeView homeView = viewManager.get(HomeView.class);

		homeView.showMenuFragment(restaurant.getId(), restaurant.getName());
	}

	@Override
	public void attachController(RestaurantListFragment controller) {
		this.fragment = controller;
	}

	@Override
	public int getLayoutResId() {
		return R.layout.fragment_restaurant_list;
	}
}
