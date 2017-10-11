package br.com.lacomida.api.menu;

import com.google.gson.reflect.TypeToken;

import java.util.List;

import br.com.lacomida.plate.model.Plate;
import br.com.lacomida.util.FileUtils;
import io.reactivex.Observable;
import io.reactivex.Single;

public class MenuRepositoryMock extends MenuRepository {

	private Single result;

	@Override
	public Single<List<Plate>> get(Long restaurantId) {
		if (result != null) {
			return result;
		}

		List<Plate> plates = FileUtils.readJson("menu/menu.json", new TypeToken<List<Plate>>() {
		}.getType());
		return Observable.fromArray(plates.toArray(new Plate[]{}))
				.map(plate -> plate.setImageUrl("localimg"))
				.toList();
	}

	public MenuRepositoryMock setResult(Single<Object> result) {
		this.result = result;
		return this;
	}
}