package br.com.lacomida.welcome.activity;

import org.junit.Test;

import br.com.lacomida.base.manager.ViewManager;
import br.com.lacomida.util.AbstractTestCase;
import br.com.lacomida.util.TestFlow;
import br.com.lacomida.util.AndroidHandler;
import br.com.lacomida.util.AndroidHandlerMock;
import br.com.lacomida.util.Injector;
import br.com.lacomida.welcome.view.SplashView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SplashActivityTest extends AbstractTestCase {

	@Test
	public void testSplashHappyFlow() throws InterruptedException {
		ViewManager viewManager = Injector.get(ViewManager.class);
		viewManager.mock(SplashView.class, SplashViewMock.class);

		SplashActivity flow = TestFlow.instantiate(SplashActivity.class);
		flow.onResume();

		AndroidHandlerMock handler = (AndroidHandlerMock) Injector.get(AndroidHandler.class);
		assertEquals(1, handler.getMessagesCount());

		handler.executeFirstPendingTransaction();

		SplashViewMock splashView = (SplashViewMock) viewManager.get(SplashView.class);
		assertTrue(splashView.willOpenHomeScreen());
	}

	@Test
	public void testSplashWithUserLeavingTheApp() {
		ViewManager viewManager = Injector.get(ViewManager.class);
		viewManager.mock(SplashView.class, SplashViewMock.class);

		SplashActivity flow = TestFlow.instantiate(SplashActivity.class);
		flow.onResume();

		flow.onPause();

		AndroidHandlerMock handler = (AndroidHandlerMock) Injector.get(AndroidHandler.class);
		assertEquals(0, handler.getMessagesCount());

		SplashViewMock splashView = (SplashViewMock) viewManager.get(SplashView.class);
		assertFalse(splashView.willOpenHomeScreen());
	}

	@Test
	public void testSplashWithUserLeavingTheAppAndResumingAgain() {
		ViewManager viewManager = Injector.get(ViewManager.class);
		viewManager.mock(SplashView.class, SplashViewMock.class);

		SplashActivity flow = TestFlow.instantiate(SplashActivity.class);
		flow.onResume();

		flow.onPause();

		flow.onResume();

		AndroidHandlerMock handler = (AndroidHandlerMock) Injector.get(AndroidHandler.class);
		assertEquals(1, handler.getMessagesCount());

		handler.executeFirstPendingTransaction();

		SplashViewMock splashView = (SplashViewMock) viewManager.get(SplashView.class);
		assertTrue(splashView.willOpenHomeScreen());
	}

	public static class SplashViewMock extends SplashView {

		private boolean willOpenHomeScreen;

		public boolean willOpenHomeScreen() {
			return willOpenHomeScreen;
		}

		@Override
		public void showHomeScreen() {
			this.willOpenHomeScreen = true;
		}
	}
}