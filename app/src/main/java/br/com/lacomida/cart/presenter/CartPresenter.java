package br.com.lacomida.cart.presenter;

import br.com.lacomida.base.presenter.BasePresenter;
import br.com.lacomida.cart.manager.CartManager;
import br.com.lacomida.cart.view.CartView;
import br.com.lacomida.util.Injector;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CartPresenter extends BasePresenter<CartView> {

	private Disposable disposable;

	@Override
	public void onViewAttached() {
		super.onViewAttached();
		getView().showCart();

		attachCartObserver();
	}

	@Override
	public void onViewDetached() {
		super.onViewDetached();
		if (disposable != null) {
			disposable.dispose();
		}
	}

	private void attachCartObserver() {
		CartManager cartManager = Injector.get(CartManager.class);
		Observable<Integer> observable = cartManager.cartChangedObserver();
		disposable = observable.subscribeOn(Schedulers.newThread()).subscribe(this::cartSizeChanged);
	}

	private void cartSizeChanged(Integer cartSize) {
		if (cartSize == 0) {
			getView().toggleCheckoutViewVisibility(false);
			return;
		}
		getView().toggleCheckoutViewVisibility(true);
	}
}