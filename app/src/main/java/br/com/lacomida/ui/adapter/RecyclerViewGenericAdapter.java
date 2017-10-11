package br.com.lacomida.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.lacomida.util.view.ItemClickListener;
import io.reactivex.functions.BiFunction;

public class RecyclerViewGenericAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {

	private BiFunction<T, Integer, Void> onBindViewHolder;
	private BiFunction<ViewGroup, Integer, T> onCreateViewHolder;
	private List<Object> items = new ArrayList<>();
	private ItemClickListener clickListener;

	public RecyclerViewGenericAdapter(BiFunction<ViewGroup, Integer, T> onCreateViewHolder,
	                                  BiFunction<T, Integer, Void> onBindViewHolder) {
		this.onBindViewHolder = onBindViewHolder;
		this.onCreateViewHolder = onCreateViewHolder;
	}

	@Override
	public T onCreateViewHolder(ViewGroup parent, int viewType) {
		try {
			T holder = onCreateViewHolder.apply(parent, viewType);
			if (clickListener != null) {
				holder.itemView.setOnClickListener(v -> clickListener.onClick(holder.getAdapterPosition()));
			}
			return holder;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onBindViewHolder(T holder, int position) {
		try {
			onBindViewHolder.apply(holder, position);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public int getItemCount() {
		return items == null ? 0 : items.size();
	}

	public RecyclerViewGenericAdapter withClickListener(ItemClickListener clickListener) {
		this.clickListener = clickListener;
		return this;
	}

	public RecyclerViewGenericAdapter withItems(List<?> items) {
		return withItems(items, true);
	}

	@SuppressWarnings("unchecked")
	public RecyclerViewGenericAdapter withItems(List<?> items, boolean notifiyDataSetChanged) {
		this.items = (List<Object>) items;
		if (notifiyDataSetChanged) {
			notifyDataSetChanged();
		}
		return this;
	}
}
