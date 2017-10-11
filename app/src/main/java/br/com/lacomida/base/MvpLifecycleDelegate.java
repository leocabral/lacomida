package br.com.lacomida.base;

public interface MvpLifecycleDelegate<V extends MvpView, P extends MvpPresenter<V>> {

	P createPresenter();

	Class<V> getMvpViewClass();

	Class<P> presenterClass();
}
