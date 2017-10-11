package br.com.lacomida.restaurant.fragment;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import br.com.lacomida.api.restaurant.RestaurantRepository;
import br.com.lacomida.api.restaurant.RestaurantRepositoryMock;
import br.com.lacomida.base.manager.ViewManager;
import br.com.lacomida.home.model.Restaurant;
import br.com.lacomida.restaurant.view.RestaurantListView;
import br.com.lacomida.util.AbstractTestCase;
import br.com.lacomida.util.Injector;
import br.com.lacomida.util.TestFlow;
import io.reactivex.Single;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RestaurantListFragmentTest extends AbstractTestCase {

	@Before
	public void setup() {
		super.setup();
		Injector.set(RestaurantRepository.class, RestaurantRepositoryMock.class);
	}

	@Test
	public void testLoadRestaurants() {
		ViewManager viewManager = Injector.get(ViewManager.class);
		viewManager.mock(RestaurantListView.class, RestaurantListViewMock.class);

		TestFlow.instantiate(RestaurantListFragment.class);

		RestaurantListViewMock restaurantListView = (RestaurantListViewMock) viewManager.get(RestaurantListView.class);
		assertEquals(1, restaurantListView.restaurants.size());
	}

	@Test
	public void testLoadRestaurantsWithError() {
		ViewManager viewManager = Injector.get(ViewManager.class);
		viewManager.mock(RestaurantListView.class, RestaurantListViewMock.class);

		RestaurantRepositoryMock repository = (RestaurantRepositoryMock) Injector.get(RestaurantRepository.class);
		repository.setResult(Single.error(new RuntimeException("")));

		TestFlow.instantiate(RestaurantListFragment.class);

		RestaurantListViewMock restaurantListView = (RestaurantListViewMock) viewManager.get(RestaurantListView.class);
		assertTrue(restaurantListView.willShowError);
	}

	public static class RestaurantListViewMock extends RestaurantListView {

		private List<Restaurant> restaurants;
		private boolean willShowError;

		@Override
		public void setupRecyclerView() {
		}

		@Override
		public void updateResults(List<Restaurant> restaurants) {
			this.restaurants = restaurants;
		}

		@Override
		public void showError() {
			willShowError = true;
		}
	}

}