package br.com.lacomida.api.base.endpoint;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class EndpointService {

	private static final Object MUTEX = new Object();
	private static final String BASE_URL = "https://demo1531977.mockable.io/";

	private Retrofit retrofit;
	private Map<String, Object> endpoints = new HashMap<>();

	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> endpointClass) {
		if (endpointClass == null) {
			return null;
		}
		String name = endpointClass.getName();
		T impl = (T) endpoints.get(name);
		if (impl == null) {
			synchronized (MUTEX) {
				impl = (T) endpoints.get(name);
				if (impl == null) {
					impl = getRetrofit().create(endpointClass);
					endpoints.put(name, impl);
				}
			}
		}
		return impl;
	}

	private Retrofit getRetrofit() {
		if (retrofit == null) {
			synchronized (MUTEX) {
				if (retrofit == null) {
					retrofit = new Retrofit.Builder()
							.baseUrl(BASE_URL)
							.addConverterFactory(GsonConverterFactory.create())
							.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
							.build();
				}
			}
		}
		return retrofit;
	}
}
