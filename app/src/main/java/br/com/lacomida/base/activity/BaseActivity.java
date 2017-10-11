package br.com.lacomida.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import br.com.lacomida.base.MvpLifecycleDelegate;
import br.com.lacomida.base.MvpView;
import br.com.lacomida.base.manager.ViewManager;
import br.com.lacomida.base.presenter.BasePresenter;
import br.com.lacomida.util.ActivityLifecycleHandler;
import br.com.lacomida.util.Injector;
import br.com.lacomida.util.ViewBinder;

public abstract class BaseActivity<V extends MvpView, P extends BasePresenter<V>>
		extends AppCompatActivity implements MvpLifecycleDelegate<V, P> {

	protected V view;
	protected P presenter;

	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		if (!ActivityLifecycleHandler.skipRealCalls()) {
			super.onCreate(savedInstanceState);
		}

		ViewManager viewManager = Injector.get(ViewManager.class);
		view = viewManager.get(getMvpViewClass());
		if (view == null) {
			throw new RuntimeException("mvpView == null");
		}

		createView(view);
		view.attachController(this);
		instantiatePresenter(view);
	}

	@Override
	public void onResume() {
		if (!ActivityLifecycleHandler.skipRealCalls()) {
			super.onResume();
		}
	}

	@Override
	public void onPause() {
		if (!ActivityLifecycleHandler.skipRealCalls()) {
			super.onPause();
		}
	}

	@Override
	public void onStop() {
		if (!ActivityLifecycleHandler.skipRealCalls()) {
			super.onStop();
		}
	}

	@Override
	public void onDestroy() {
		if (!ActivityLifecycleHandler.skipRealCalls()) {
			super.onDestroy();
		}
		if (presenter != null) {
			presenter.detachView();
		}
	}

	@Override
	public P createPresenter() {
		return Injector.get(presenterClass());
	}

	private void createView(V view) {
		if (ActivityLifecycleHandler.skipRealCalls()) {
			return;
		}
		int layoutResId = view.getLayoutResId();
		if (layoutResId != 0) {
			setContentView(layoutResId);
		}
		Injector.get(ViewBinder.class).bind(view, this);
	}

	private void instantiatePresenter(V view) {
		presenter = createPresenter();
		if (presenter != null) {
			presenter.attachView(view);
		}
	}

	public P getPresenter() {
		return presenter;
	}
}
