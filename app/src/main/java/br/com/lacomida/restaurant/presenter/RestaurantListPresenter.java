package br.com.lacomida.restaurant.presenter;

import java.util.List;

import br.com.lacomida.api.restaurant.RestaurantRepository;
import br.com.lacomida.base.presenter.BasePresenter;
import br.com.lacomida.home.model.Restaurant;
import br.com.lacomida.restaurant.view.RestaurantListView;
import br.com.lacomida.util.Injector;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RestaurantListPresenter extends BasePresenter<RestaurantListView> {

	private Disposable disposable;

	@Override
	public void onViewAttached() {
		super.onViewAttached();
		loadRestaurants();
	}

	@Override
	public void onViewDetached() {
		super.onViewDetached();
		if (disposable != null) {
			disposable.dispose();
		}
	}

	private void loadRestaurants() {
		RestaurantRepository repository = Injector.get(RestaurantRepository.class);

		Double latitude = 0d;
		Double longitude = 0d;
		disposable = repository.get(latitude, longitude)
				.subscribeOn(Schedulers.newThread())
				.subscribe(this::onNext, this::onError);
	}

	private void onNext(List<Restaurant> restaurants) {
		if (!viewIsAlive()) {
			return;
		}
		if (restaurants == null || restaurants.isEmpty()) {
			return;
		}
		getView().updateResults(restaurants);
	}

	private void onError(Throwable throwable) {
		if (viewIsAlive()) {
			getView().showError();
		}
	}
}
