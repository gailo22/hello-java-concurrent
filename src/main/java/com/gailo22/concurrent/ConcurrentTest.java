package com.gailo22.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ConcurrentTest {

	public static void main(final String[] args) throws Exception {

		final ExecutorService executor = Executors.newFixedThreadPool(10);
		final List<Future<String>> futureList = new ArrayList<Future<String>>();

		for (int i = 0; i < 20000; i++) {
			final Future<String> future = executor.submit(new ThreadA());
			futureList.add(future);
		}

		executor.shutdown();

		while (!executor.isTerminated()) {

			for (final Future<String> future : futureList) {
				try {
					System.out.println("future: " + future.get());
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static String doInThread() {
		return "";
	}

	private static class ThreadA implements Callable<String> {

		@Override
		public String call() throws Exception {

			System.out.println("Current Thread: " + Thread.currentThread());

			return doInThread();
		}
	}

}
