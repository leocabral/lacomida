package br.com.lacomida.base.presenter;

import java.lang.ref.WeakReference;

import br.com.lacomida.base.MvpPresenter;
import br.com.lacomida.base.MvpView;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

	protected WeakReference<V> viewRef;

	@Override
	public void attachView(V view) {
		viewRef = new WeakReference<>(view);
		onViewAttached();
	}

	@Override
	public void detachView() {
		if (viewRef != null) {
			viewRef.clear();
			viewRef = null;
		}
		onViewDetached();
	}

	@Override
	public boolean viewIsAlive() {
		return viewRef != null && viewRef.get() != null;
	}

	@Override
	public V getView() {
		return viewRef == null ? null : viewRef.get();
	}

	@Override
	public void onViewDetached() {
	}

	@Override
	public void onViewAttached() {
	}
}
