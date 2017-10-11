package br.com.lacomida.base;

public interface MvpView<T> {

	int getLayoutResId();

	void attachController(T controller);
}
