package br.com.lacomida.cart.manager;

import java.util.ArrayList;
import java.util.List;

import br.com.lacomida.persistence.PersistenceLayer;
import br.com.lacomida.plate.model.Plate;
import br.com.lacomida.util.Injector;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class CartManager {

	private static final String CART_STORAGE_KEY = "cartData";

	private List<Plate> cart = new ArrayList<>();
	private ObservableEmitter<Integer> emitter;

	public List<Plate> load() {
		PersistenceLayer persistenceLayer = Injector.get(PersistenceLayer.class);
		List<Plate> cart = persistenceLayer.load(CART_STORAGE_KEY);
		if (cart != null) {
			this.cart = cart;
		}
		List<Plate> ret = new ArrayList<>();
		ret.addAll(this.cart);

		emitSizeChanged();
		return ret;
	}

	public void addPlate(Plate plate) {
		cart.add(plate);
		emitSizeChanged();
		storeCart();
	}

	public boolean removePlate(Plate plate) {
		boolean removed = cart.remove(plate);
		if (removed) {
			emitSizeChanged();
			storeCart();
		}
		return removed;
	}

	private void storeCart() {
		PersistenceLayer persistenceLayer = Injector.get(PersistenceLayer.class);
		persistenceLayer.save(CART_STORAGE_KEY, cart);
	}

	public void clear() {
		if (cart != null) {
			cart.clear();
			emitSizeChanged();
			storeCart();
		}
	}

	private void emitSizeChanged() {
		if (emitter != null && !emitter.isDisposed()) {
			emitter.onNext(cart.size());
		}
	}

	public Observable<Integer> cartChangedObserver() {
		return Observable.create(onSubscribe());
	}

	private ObservableOnSubscribe<Integer> onSubscribe() {
		return e -> this.emitter = e;
	}
}
