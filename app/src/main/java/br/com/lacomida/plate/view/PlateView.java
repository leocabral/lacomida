package br.com.lacomida.plate.view;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import org.parceler.Parcels;

import br.com.lacomida.R;
import br.com.lacomida.base.MvpView;
import br.com.lacomida.cart.manager.CartManager;
import br.com.lacomida.plate.fragment.PlateFragment;
import br.com.lacomida.plate.model.Plate;
import br.com.lacomida.util.Injector;
import butterknife.BindView;
import butterknife.OnClick;

public class PlateView implements MvpView<PlateFragment> {

	public static final String PLATE_BUNDLE_KEY = "plate";

	@BindView(R.id.plate_details_description)
	TextView description;

	@BindView(R.id.plate_details_name)
	TextView name;

	@BindView(R.id.plate_details_price)
	TextView price;

	@BindView(R.id.plate_details_image)
	SimpleDraweeView image;

	private PlateFragment fragment;

	public void populate() {
		Plate plate = getPlate();

		description.setText(plate.getDescription());
		name.setText(plate.getName());
		price.setText(String.valueOf(plate.getPrice()));
		image.setImageURI(plate.getImageUrl());
	}

	public Plate getPlate() {
		Bundle arguments = fragment.getArguments();
		if (arguments == null) {
			return null;
		}
		return Parcels.unwrap(arguments.getParcelable(PLATE_BUNDLE_KEY));
	}

	@OnClick(R.id.plate_details_add_button)
	public void addButtonClicked() {
		CartManager cartManager = Injector.get(CartManager.class);
		cartManager.addPlate(getPlate());

		showFeedbackMessage();
	}

	protected void showFeedbackMessage() {
		Toast.makeText(fragment.getActivity(), R.string.product_added_to_cart, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void attachController(PlateFragment controller) {
		this.fragment = controller;
	}

	@Override
	public int getLayoutResId() {
		return R.layout.fragment_plate;
	}
}
