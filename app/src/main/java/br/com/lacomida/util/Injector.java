package br.com.lacomida.util;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class Injector {

	private static final Object MUTEX = new Object();
	private static Injector instance = new Injector();

	private Map<String, Object> services = new HashMap<>();

	protected Injector() {
	}

	private static Injector getInstance() {
		if (instance == null) {
			synchronized (MUTEX) {
				if (instance == null) {
					instance = new Injector();
				}
			}
		}
		return instance;
	}

	public static <T> T get(Class<T> service) {
		return getInstance().getService(service);
	}

	private <T> T getService(Class<T> service) {
		String serviceName = service.getName();
		T ret = (T) services.get(serviceName);
		if (ret == null || ret instanceof Class) {
			synchronized (MUTEX) {
				ret = (T) services.get(serviceName);
				if (ret == null || ret instanceof Class) {
					ret = instantiate(service, (Class<T>) ret);
					services.put(serviceName, ret);
				}
			}
		}
		return ret;
	}

	private <T> T instantiate(Class<T> original, Class<T> replacement) {
		if (replacement != null) {
			return ReflectionUtil.newInstance(replacement);
		}
		return ReflectionUtil.newInstance(original);
	}

	public static <T> void set(Class<T> service, Class<? extends T> replacement) {
		Injector instance = getInstance();

		String name = service.getName();
		instance.services.remove(name);
		instance.services.put(name, replacement);
	}
}
