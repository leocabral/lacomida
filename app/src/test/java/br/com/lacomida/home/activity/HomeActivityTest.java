package br.com.lacomida.home.activity;

import org.junit.Test;

import br.com.lacomida.base.manager.ViewManager;
import br.com.lacomida.home.view.HomeView;
import br.com.lacomida.home.view.HomeViewMock;
import br.com.lacomida.menu.fragment.RestaurantMenuFragment;
import br.com.lacomida.plate.fragment.PlateFragment;
import br.com.lacomida.plate.model.Plate;
import br.com.lacomida.restaurant.fragment.RestaurantListFragment;
import br.com.lacomida.util.AbstractTestCase;
import br.com.lacomida.util.Injector;
import br.com.lacomida.util.TestFlow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HomeActivityTest extends AbstractTestCase {

	@Test
	public void testShowRestaurantsList() {
		ViewManager viewManager = Injector.get(ViewManager.class);
		viewManager.mock(HomeView.class, HomeViewMock.class);

		TestFlow.instantiate(HomeActivity.class);

		HomeViewMock homeView = (HomeViewMock) viewManager.get(HomeView.class);
		assertEquals(RestaurantListFragment.class, homeView.fragmentToBeShown);
		assertEquals(null, homeView.currentExtras);
	}

	@Test
	public void testShowRestaurantMenu() {
		ViewManager viewManager = Injector.get(ViewManager.class);
		viewManager.mock(HomeView.class, HomeViewMock.class);

		TestFlow.instantiate(HomeActivity.class);

		HomeViewMock homeView = (HomeViewMock) viewManager.get(HomeView.class);
		homeView.showMenuFragment(1L, "Pizzaria");

		assertEquals(RestaurantMenuFragment.class, homeView.fragmentToBeShown);
		assertNotNull(homeView.currentExtras);
	}

	@Test
	public void testShowPlate() {
		ViewManager viewManager = Injector.get(ViewManager.class);
		viewManager.mock(HomeView.class, HomeViewMock.class);

		TestFlow.instantiate(HomeActivity.class);

		HomeViewMock homeView = (HomeViewMock) viewManager.get(HomeView.class);
		homeView.showPlateFragment(new Plate().setName("Pizza"));

		assertEquals(PlateFragment.class, homeView.fragmentToBeShown);
		assertNotNull(homeView.currentExtras);
	}
}