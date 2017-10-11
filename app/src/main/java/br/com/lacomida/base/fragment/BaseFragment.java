package br.com.lacomida.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.lacomida.base.MvpLifecycleDelegate;
import br.com.lacomida.base.MvpPresenter;
import br.com.lacomida.base.MvpView;
import br.com.lacomida.base.manager.ViewManager;
import br.com.lacomida.util.ActivityLifecycleHandler;
import br.com.lacomida.util.Injector;
import br.com.lacomida.util.ViewBinder;

public abstract class BaseFragment<V extends MvpView, P extends MvpPresenter<V>>
		extends Fragment implements MvpLifecycleDelegate<V, P> {

	protected V view;
	protected P presenter;
	private ViewBinder.Unbinder unbinder;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		if (!ActivityLifecycleHandler.skipRealCalls()) {
			super.onCreate(savedInstanceState);
		}
		if (presenter == null) {
			presenter = createPresenter();
		}
	}

	@Override
	public void onResume() {
		if (!ActivityLifecycleHandler.skipRealCalls()) {
			super.onResume();
		}
	}

	@SuppressWarnings("unchecked")
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {
		ViewManager viewManager = Injector.get(ViewManager.class);
		view = viewManager.get(getMvpViewClass());
		if (view == null) {
			throw new RuntimeException("mvpView == null");
		}

		View contentView = null;
		if (!ActivityLifecycleHandler.skipRealCalls()) {
			int layoutResId = view.getLayoutResId();
			contentView = inflater.inflate(layoutResId, null, false);
			unbinder = Injector.get(ViewBinder.class).bind(view, contentView);
		}
		view.attachController(this);
		return contentView;
	}

	@Override
	public void onViewCreated(View fragmentView, @Nullable Bundle savedInstanceState) {
		if (!ActivityLifecycleHandler.skipRealCalls()) {
			super.onViewCreated(fragmentView, savedInstanceState);
		}
		if (presenter != null) {
			presenter.attachView(view);
		}
	}

	@Override
	public void onDestroyView() {
		if (!ActivityLifecycleHandler.skipRealCalls()) {
			super.onDestroyView();
		}
		unbinder.unbind();
		if (presenter != null) {
			presenter.detachView();
		}
	}

	@Override
	public P createPresenter() {
		return Injector.get(presenterClass());
	}

	public P getPresenter() {
		return presenter;
	}
}
