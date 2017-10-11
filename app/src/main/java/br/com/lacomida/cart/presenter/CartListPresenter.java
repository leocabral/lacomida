package br.com.lacomida.cart.presenter;

import java.util.List;

import br.com.lacomida.base.presenter.BasePresenter;
import br.com.lacomida.cart.manager.CartManager;
import br.com.lacomida.cart.view.CartListView;
import br.com.lacomida.plate.model.Plate;
import br.com.lacomida.util.Injector;

public class CartListPresenter extends BasePresenter<CartListView> {

	@Override
	public void onViewAttached() {
		super.onViewAttached();
		loadCart();
	}

	private void loadCart() {
		CartManager cartManager = Injector.get(CartManager.class);
		List<Plate> cart = cartManager.load();
		if (cart == null || cart.isEmpty()) {
			getView().showEmptyView();
			return;
		}
		getView().showCart(cart);
	}
}
