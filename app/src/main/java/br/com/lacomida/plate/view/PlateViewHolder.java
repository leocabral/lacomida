package br.com.lacomida.plate.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import br.com.lacomida.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PlateViewHolder extends RecyclerView.ViewHolder {

	@BindView(R.id.plate_image)
	public SimpleDraweeView image;

	@BindView(R.id.plate_name)
	public TextView name;

	@BindView(R.id.plate_description)
	public TextView description;

	@BindView(R.id.plate_price)
	public TextView price;

	public PlateViewHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
	}
}
