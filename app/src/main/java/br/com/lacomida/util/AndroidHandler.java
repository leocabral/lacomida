package br.com.lacomida.util;

import android.os.Handler;

public class AndroidHandler {

	private Handler handler;

	public AndroidHandler() {
	}

	public AndroidHandler(Handler handler) {
		this.handler = handler;
	}

	public AndroidHandler get() {
		return new AndroidHandler(new Handler());
	}

	public boolean postDelayed(Runnable r, int delay) {
		return handler.postDelayed(r, delay);
	}

	public void removeCallbacks(Runnable r) {
		handler.removeCallbacks(r);
	}
}
