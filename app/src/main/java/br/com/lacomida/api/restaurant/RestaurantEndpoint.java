package br.com.lacomida.api.restaurant;

import java.util.List;

import br.com.lacomida.home.model.Restaurant;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface RestaurantEndpoint {

	@GET("restaurants")
	Single<List<Restaurant>> get(@Query("lat") Double latitude, @Query("lng") Double longitude);
}
