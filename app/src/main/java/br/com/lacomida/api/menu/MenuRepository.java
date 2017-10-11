package br.com.lacomida.api.menu;

import java.util.List;

import br.com.lacomida.api.base.repository.Repository;
import br.com.lacomida.plate.model.Plate;
import io.reactivex.Single;

public class MenuRepository extends Repository<MenuEndpoint> {

	public Single<List<Plate>> get(Long restaurantId) {
		return getEndpoint().get(restaurantId);
	}

	@Override
	protected Class<MenuEndpoint> endpointClass() {
		return MenuEndpoint.class;
	}
}