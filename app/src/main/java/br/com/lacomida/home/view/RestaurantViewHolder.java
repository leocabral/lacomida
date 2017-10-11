package br.com.lacomida.home.view;

import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import br.com.lacomida.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantViewHolder extends RecyclerView.ViewHolder {

	@BindView(R.id.restaurant_delivery_fee)
	public TextView deliveryFee;

	@BindView(R.id.restaurant_address)
	public TextView address;

	@BindView(R.id.restaurant_name)
	public TextView name;

	@BindView(R.id.restaurant_description)
	public TextView description;

	@BindView(R.id.restaurant_image)
	public SimpleDraweeView image;

	@BindView(R.id.restaurant_rating)
	public AppCompatRatingBar rating;

	public RestaurantViewHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
	}
}
