package br.com.lacomida.home.activity;

import android.support.test.espresso.contrib.RecyclerViewActions;

import br.com.lacomida.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static br.com.lacomida.common.EspressoCustomActions.atPosition;
import static br.com.lacomida.common.RecyclerViewItemCountAssertion.recyclerViewItemCountIs;

class HomeRobot {

	public HomeRobot assertHasOneRestaurant() {
		onView(withId(R.id.restaurants_recycler_view)).check(matches(atPosition(0,
				hasDescendant(withText("Pizzaria")))));
		return this;
	}

	public HomeRobot selectRestaurant() {
		onView(withId(R.id.restaurant_image)).perform(click());
		return this;
	}

	public HomeRobot assertHasTwoPlates() {
		onView(withId(R.id.menu_recycler_view)).check(recyclerViewItemCountIs(2));
		return this;
	}

	public HomeRobot selectPlate() {
		onView(withId(R.id.menu_recycler_view)).perform(
				RecyclerViewActions.actionOnItemAtPosition(0, click()));
		return this;
	}

	public HomeRobot addToCart() {
		onView(withId(R.id.plate_details_add_button)).perform(click());
		return this;
	}

	public HomeRobot openCart() {
		onView(withId(R.id.action_cart)).perform(click());
		return this;
	}

	public HomeRobot checkout() {
		onView(withId(R.id.cart_checkout_container)).perform(click());
		return this;
	}

	public HomeRobot selectPaymentMethod() {
		onView(withId(R.id.payment_method_recycler_view)).perform(
				RecyclerViewActions.actionOnItemAtPosition(0, click()));
		return this;
	}

	public HomeRobot assertCheckoutSucceeded() {
		onView(withId(R.id.checkout_succeeded_text)).check(matches(withText("Seu pedido foi realizado")));
		return this;
	}
}
