package br.com.lacomida.util;

public class ActivityLifecycleHandler {

	private static boolean skipRealCalls;

	public static void setSkipRealCalls(boolean value) {
		skipRealCalls = value;
	}

	public static boolean skipRealCalls() {
		return skipRealCalls;
	}
}
