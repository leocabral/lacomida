package br.com.lacomida.api.restaurant;

import com.google.gson.reflect.TypeToken;

import java.util.List;

import br.com.lacomida.home.model.Restaurant;
import br.com.lacomida.util.FileUtils;
import io.reactivex.Observable;
import io.reactivex.Single;

public class RestaurantRepositoryMock extends RestaurantRepository {

	private Single result;

	@Override
	public Single<List<Restaurant>> get(Double latitude, Double longitude) {
		if (result != null) {
			return result;
		}

		List<Restaurant> restaurants = FileUtils.readJson("restaurant/restaurants.json", new TypeToken<List<Restaurant>>() {
		}.getType());
		return Observable.fromArray(restaurants.toArray(new Restaurant[]{}))
				.map(restaurant -> restaurant.setImageUrl(""))
				.toList();
	}

	public RestaurantRepositoryMock setResult(Single result) {
		this.result = result;
		return this;
	}
}