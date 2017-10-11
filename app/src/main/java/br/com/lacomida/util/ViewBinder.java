package br.com.lacomida.util;

import android.app.Activity;
import android.view.View;

import butterknife.ButterKnife;

public class ViewBinder {

	public Unbinder bind(Object object, Activity activity) {
		butterknife.Unbinder unbinder = ButterKnife.bind(object, activity);
		return new Unbinder(unbinder);
	}

	public Unbinder bind(Object object, View view) {
		butterknife.Unbinder unbinder = ButterKnife.bind(object, view);
		return new Unbinder(unbinder);
	}

	public static class Unbinder {

		private butterknife.Unbinder unbinder;

		public Unbinder(butterknife.Unbinder unbinder) {
			this.unbinder = unbinder;
		}

		public void unbind() {
			if (unbinder != null) {
				unbinder.unbind();
			}
		}
	}
}
