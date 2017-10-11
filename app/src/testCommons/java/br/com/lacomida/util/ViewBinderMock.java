package br.com.lacomida.util;

import android.app.Activity;
import android.view.View;

public class ViewBinderMock extends ViewBinder {

	@Override
	public Unbinder bind(Object object, View view) {
		return new Unbinder(null);
	}

	@Override
	public Unbinder bind(Object object, Activity activity) {
		return new Unbinder(null);
	}
}
