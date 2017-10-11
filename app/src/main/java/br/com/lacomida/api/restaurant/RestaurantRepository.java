package br.com.lacomida.api.restaurant;

import java.util.List;

import br.com.lacomida.api.base.repository.Repository;
import br.com.lacomida.home.model.Restaurant;
import io.reactivex.Single;

public class RestaurantRepository extends Repository<RestaurantEndpoint> {

	public Single<List<Restaurant>> get(Double latitude, Double longitude) {
		return getEndpoint().get(latitude, longitude);
	}

	@Override
	protected Class<RestaurantEndpoint> endpointClass() {
		return RestaurantEndpoint.class;
	}
}
