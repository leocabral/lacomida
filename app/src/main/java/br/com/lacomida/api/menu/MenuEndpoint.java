package br.com.lacomida.api.menu;

import java.util.List;

import br.com.lacomida.plate.model.Plate;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MenuEndpoint {

	@GET("menu/{restaurantId}")
	Single<List<Plate>> get(@Path("restaurantId") Long restaurantId);
}