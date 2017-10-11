package br.com.lacomida.menu.fragment;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import br.com.lacomida.api.menu.MenuRepository;
import br.com.lacomida.api.menu.MenuRepositoryMock;
import br.com.lacomida.base.manager.ViewManager;
import br.com.lacomida.menu.view.RestaurantMenuView;
import br.com.lacomida.plate.model.Plate;
import br.com.lacomida.util.AbstractTestCase;
import br.com.lacomida.util.Injector;
import br.com.lacomida.util.TestFlow;
import io.reactivex.Single;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RestaurantMenuFragmentTest extends AbstractTestCase {

	@Before
	public void setup() {
		super.setup();
		Injector.set(MenuRepository.class, MenuRepositoryMock.class);
	}

	@Test
	public void testShowMenu() {
		ViewManager viewManager = Injector.get(ViewManager.class);
		viewManager.mock(RestaurantMenuView.class, RestaurantMenuViewMock.class);

		TestFlow.instantiate(RestaurantMenuFragment.class);

		RestaurantMenuViewMock menuView = (RestaurantMenuViewMock) viewManager.get(RestaurantMenuView.class);

		assertNotNull(menuView.plates);
		assertEquals(2, menuView.plates.size());
	}

	@Test
	public void testShowMenuWithError() {
		ViewManager viewManager = Injector.get(ViewManager.class);
		viewManager.mock(RestaurantMenuView.class, RestaurantMenuViewMock.class);

		MenuRepositoryMock repository = (MenuRepositoryMock) Injector.get(MenuRepository.class);
		repository.setResult(Single.error(new RuntimeException("")));

		TestFlow.instantiate(RestaurantMenuFragment.class);

		RestaurantMenuViewMock menuView = (RestaurantMenuViewMock) viewManager.get(RestaurantMenuView.class);

		assertTrue(menuView.willShowError);
	}

	public static class RestaurantMenuViewMock extends RestaurantMenuView {

		private boolean willShowError;
		private List<Plate> plates;

		@Override
		public void setupRecyclerView() {
		}

		@Override
		public Long getRestaurantId() {
			return 1L;
		}

		@Override
		protected String getRestaurantName() {
			return "Rest";
		}

		@Override
		public void showError() {
			willShowError = true;
		}

		@Override
		public void showPlates(List<Plate> plates) {
			this.plates = plates;
		}
	}
}