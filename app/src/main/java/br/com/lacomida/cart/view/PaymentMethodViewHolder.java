package br.com.lacomida.cart.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.lacomida.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentMethodViewHolder extends RecyclerView.ViewHolder {

	@BindView(R.id.payment_method_name)
	TextView name;

	public PaymentMethodViewHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
	}
}
