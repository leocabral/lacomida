package br.com.lacomida.util;

import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

public class RxJavaTestScheduler {

	public static void init() {
		init(scheduler -> Schedulers.trampoline());
	}

	public static void init(Function<? super Scheduler, ? extends Scheduler> scheduler) {
		RxJavaPlugins.reset();
		RxJavaPlugins.setIoSchedulerHandler(scheduler);
		RxJavaPlugins.setNewThreadSchedulerHandler(scheduler);
	}
}