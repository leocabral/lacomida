package br.com.lacomida.menu.presenter;

import java.util.List;

import br.com.lacomida.api.menu.MenuRepository;
import br.com.lacomida.base.presenter.BasePresenter;
import br.com.lacomida.menu.view.RestaurantMenuView;
import br.com.lacomida.plate.model.Plate;
import br.com.lacomida.util.Injector;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RestaurantMenuPresenter extends BasePresenter<RestaurantMenuView> {

	private Disposable disposable;

	@Override
	public void onViewAttached() {
		super.onViewAttached();
		loadMenu(getView().getRestaurantId());
	}

	@Override
	public void onViewDetached() {
		super.onViewDetached();
		if (disposable != null) {
			disposable.dispose();
		}
	}

	private void loadMenu(Long restaurantId) {
		if (restaurantId == null) {
			getView().showError();
			return;
		}

		MenuRepository repository = Injector.get(MenuRepository.class);
		disposable = repository.get(restaurantId)
				.subscribeOn(Schedulers.newThread())
				.subscribe(this::menuLoaded, this::onError);
	}

	private void menuLoaded(List<Plate> plates) {
		if (!viewIsAlive()) {
			return;
		}
		if (plates == null || plates.isEmpty()) {
			return;
		}
		getView().showPlates(plates);
	}

	private void onError(Throwable throwable) {
		if (viewIsAlive()) {
			getView().showError();
		}
	}
}
