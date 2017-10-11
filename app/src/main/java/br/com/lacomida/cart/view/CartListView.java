package br.com.lacomida.cart.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.lacomida.R;
import br.com.lacomida.base.MvpView;
import br.com.lacomida.cart.fragment.CartListFragment;
import br.com.lacomida.cart.manager.CartManager;
import br.com.lacomida.plate.model.Plate;
import br.com.lacomida.ui.adapter.RecyclerViewGenericAdapter;
import br.com.lacomida.util.Injector;
import butterknife.BindView;

public class CartListView implements MvpView<CartListFragment> {

	@BindView(R.id.cart_list_recycler_view)
	RecyclerView recyclerView;

	@BindView(R.id.cart_list_empty_view)
	View emptyView;

	private CartListFragment fragment;
	protected List<Plate> plates;

	private CartPlateViewHolder createViewHolder(ViewGroup parent, Integer viewType) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View view = inflater.inflate(R.layout.cart_plate_cell, parent, false);

		CartPlateViewHolder viewHolder = new CartPlateViewHolder(view);
		viewHolder.removeButton.setOnClickListener(v -> removePlateClicked(viewHolder.getAdapterPosition()));
		return viewHolder;
	}

	private Void bindViewHolder(CartPlateViewHolder holder, Integer position) {
		Plate plate = plates.get(position);

		holder.name.setText(plate.getName());
		holder.image.setImageURI(plate.getImageUrl());

		Context context = holder.itemView.getContext();
		holder.quantity.setText(context.getString(R.string.plate_quantity, 1));
		holder.totalPrice.setText(context.getString(R.string.total_price,
				String.valueOf(plate.getPrice())));
		return null;
	}

	public void showCart(List<Plate> plates) {
		this.plates = plates;

		RecyclerViewGenericAdapter<CartPlateViewHolder> adapter =
				new RecyclerViewGenericAdapter<>(this::createViewHolder, this::bindViewHolder);
		adapter.withItems(plates);
		recyclerView.setAdapter(adapter);

		recyclerView.setLayoutManager(new LinearLayoutManager(fragment.getActivity()));
	}

	public void showEmptyView() {
		emptyView.setVisibility(View.VISIBLE);
	}

	public void removePlateClicked(int position) {
		Plate plate = plates.remove(position);
		CartManager cartManager = Injector.get(CartManager.class);
		cartManager.removePlate(plate);

		if (plates.isEmpty()) {
			showEmptyView();
		}
		removeFromRecyclerView(position);
	}

	protected void removeFromRecyclerView(int position) {
		RecyclerViewGenericAdapter adapter = (RecyclerViewGenericAdapter) recyclerView.getAdapter();
		adapter.withItems(plates, false);
		adapter.notifyItemRemoved(position);
		adapter.notifyItemRangeChanged(position, plates.size() - position);
	}

	@Override
	public void attachController(CartListFragment controller) {
		this.fragment = controller;
	}

	@Override
	public int getLayoutResId() {
		return R.layout.fragment_cart_list;
	}
}
