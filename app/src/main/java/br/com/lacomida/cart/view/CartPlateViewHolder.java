package br.com.lacomida.cart.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import br.com.lacomida.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CartPlateViewHolder extends RecyclerView.ViewHolder {

	@BindView(R.id.cart_plate_remove_button)
	View removeButton;

	@BindView(R.id.cart_plate_image)
	SimpleDraweeView image;

	@BindView(R.id.cart_plate_name)
	TextView name;

	@BindView(R.id.cart_plate_price)
	TextView totalPrice;

	@BindView(R.id.cart_plate_quantity)
	TextView quantity;

	public CartPlateViewHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
	}
}
