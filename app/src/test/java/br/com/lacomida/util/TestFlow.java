package br.com.lacomida.util;

import br.com.lacomida.base.activity.BaseActivity;
import br.com.lacomida.base.fragment.BaseFragment;

@SuppressWarnings("unchecked")
public class TestFlow {

	public static <T> T instantiate(Class<?> activityClass) {
		Object controller = ReflectionUtil.newInstance(activityClass);
		if (controller instanceof BaseActivity) {
			runActivityLifecycle((BaseActivity) controller);
		} else if (controller instanceof BaseFragment) {
			runFragmentLifecycle((BaseFragment) controller);
		}
		return (T) controller;
	}

	private static void runActivityLifecycle(BaseActivity controller) {
		controller.onCreate(null);
	}

	private static void runFragmentLifecycle(BaseFragment controller) {
		controller.onCreate(null);
		controller.onCreateView(null, null, null);
		controller.onViewCreated(null, null);
	}
}
