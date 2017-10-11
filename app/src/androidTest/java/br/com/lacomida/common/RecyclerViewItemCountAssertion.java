package br.com.lacomida.common;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import static org.junit.Assert.assertEquals;

public class RecyclerViewItemCountAssertion implements ViewAssertion {

	private final int expected;

	RecyclerViewItemCountAssertion(int expected) {
		this.expected = expected;
	}

	@Override
	public void check(View view, NoMatchingViewException noMatchingViewException) {
		if (noMatchingViewException != null) {
			throw noMatchingViewException;
		}
		RecyclerView recyclerView = (RecyclerView) view;
		RecyclerView.Adapter adapter = recyclerView.getAdapter();
		assertEquals(expected, adapter.getItemCount());
	}

	public static RecyclerViewItemCountAssertion recyclerViewItemCountIs(int expected) {
		return new RecyclerViewItemCountAssertion(expected);
	}
}