package br.com.lacomida.home.activity;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Test;

import br.com.lacomida.api.checkout.CheckoutRepository;
import br.com.lacomida.api.checkout.CheckoutRepositoryMock;
import br.com.lacomida.api.checkout.method.PaymentMethodRepository;
import br.com.lacomida.api.checkout.method.PaymentMethodRepositoryMock;
import br.com.lacomida.api.menu.MenuRepository;
import br.com.lacomida.api.menu.MenuRepositoryMock;
import br.com.lacomida.api.restaurant.RestaurantRepository;
import br.com.lacomida.api.restaurant.RestaurantRepositoryMock;
import br.com.lacomida.common.AbstractEspressoTestCase;
import br.com.lacomida.util.Injector;

public class HomeActivityTest extends AbstractEspressoTestCase<ActivityTestRule<HomeActivity>> {

	@Before
	public void setup() {
		super.setup();

		Injector.set(RestaurantRepository.class, RestaurantRepositoryMock.class);
		Injector.set(MenuRepository.class, MenuRepositoryMock.class);
		Injector.set(PaymentMethodRepository.class, PaymentMethodRepositoryMock.class);
		Injector.set(CheckoutRepository.class, CheckoutRepositoryMock.class);
	}

	@Test
	public void testHappyFlow() {
		Intent intent = new Intent();
		testRule.launchActivity(intent);

		HomeRobot robot = new HomeRobot();
		robot.assertHasOneRestaurant()
				.selectRestaurant()
				.assertHasTwoPlates()
				.selectPlate()
				.addToCart()
				.openCart()
				.checkout()
				.selectPaymentMethod()
				.assertCheckoutSucceeded();
	}

	@Override
	protected ActivityTestRule<HomeActivity> buildTestRule() {
		return new ActivityTestRule<>(HomeActivity.class, false, false);
	}
}