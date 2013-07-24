package com.gailo22.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {

	public static void main(final String args[]) {
		final ExecutorService service = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 100; i++) {
			service.submit(new Task(i));
		}
	}

}

final class Task implements Runnable {
	private final int taskId;

	public Task(final int id) {
		this.taskId = id;
	}

	@Override
	public void run() {
		System.out.println("Task ID : " + this.taskId + " performed by " + Thread.currentThread().getName());
	}

}
