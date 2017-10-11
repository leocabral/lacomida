package br.com.lacomida.base;

public interface MvpPresenter<V extends MvpView> {

	void attachView(V view);

	void detachView();

	V getView();

	boolean viewIsAlive();

	void onViewAttached();

	void onViewDetached();
}
