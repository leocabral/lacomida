package br.com.lacomida.plate.fragment;

import org.junit.Test;

import br.com.lacomida.base.manager.ViewManager;
import br.com.lacomida.plate.model.Plate;
import br.com.lacomida.plate.view.PlateView;
import br.com.lacomida.util.AbstractTestCase;
import br.com.lacomida.util.Injector;
import br.com.lacomida.util.TestFlow;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PlateFragmentTest extends AbstractTestCase {

	@Test
	public void testShowPlate() {
		ViewManager viewManager = Injector.get(ViewManager.class);
		viewManager.mock(PlateView.class, PlateViewMock.class);

		TestFlow.instantiate(PlateFragment.class);

		PlateViewMock plateView = (PlateViewMock) viewManager.get(PlateView.class);
		assertNotNull(plateView.getPlate());
		assertTrue(plateView.willPopulate);
	}

	@Test
	public void testAddToCart() {
		ViewManager viewManager = Injector.get(ViewManager.class);
		viewManager.mock(PlateView.class, PlateViewMock.class);

		TestFlow.instantiate(PlateFragment.class);

		PlateViewMock plateView = (PlateViewMock) viewManager.get(PlateView.class);
		plateView.addButtonClicked();

		assertTrue(plateView.productAddedToCart);
	}

	public static class PlateViewMock extends PlateView {

		private boolean willPopulate;
		private boolean productAddedToCart;

		@Override
		public Plate getPlate() {
			return new Plate().setId(1L)
					.setName("Pizza")
					.setDescription("Pizza de calabresa")
					.setPrice(22.5F);
		}

		@Override
		public void populate() {
			willPopulate = true;
		}

		@Override
		protected void showFeedbackMessage() {
			productAddedToCart = true;
		}
	}
}