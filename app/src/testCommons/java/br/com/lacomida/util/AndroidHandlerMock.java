package br.com.lacomida.util;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AndroidHandlerMock extends AndroidHandler {

	private AtomicInteger messagesCount = new AtomicInteger(0);
	private List<Runnable> messageQueue = new LinkedList<>();

	@Override
	public AndroidHandler get() {
		return new AndroidHandler() {

			@Override
			public boolean postDelayed(Runnable runnable, int delay) {
				messagesCount.incrementAndGet();
				messageQueue.add(runnable);
				return true;
			}

			@Override
			public void removeCallbacks(Runnable r) {
				messagesCount.decrementAndGet();
				messageQueue.remove(0);
			}
		};
	}

	public void executeFirstPendingTransaction() {
		if (messageQueue.isEmpty()) {
			return;
		}
		messageQueue.remove(0).run();
		messagesCount.decrementAndGet();
	}

	public int getMessagesCount() {
		return messagesCount.get();
	}

	public void reset() {
		messageQueue.clear();
		messagesCount.set(0);
	}
}
