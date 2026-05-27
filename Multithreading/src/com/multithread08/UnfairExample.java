package com.multithread08;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//6.2.5 Unfair lock: this is a lock that does not guarantee that the longest waiting thread will acquire the lock next. 
//it may allow a thread that has just requested the lock to acquire it before a thread that has been waiting for a long time. 
//this can lead to thread starvation, where a thread is unable to acquire the lock for an extended period of time 
//because other threads are continuously acquiring the lock before it.

public class UnfairExample {

	private final Lock unfairLoc = new ReentrantLock(true); // false for unfair lock

	public void accessResource() {
		unfairLoc.lock();
		try {
			System.out.println(Thread.currentThread().getName() + ": has acquired the lock.");
			// Simulate some work with the resource
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		} finally {
			System.out.println(Thread.currentThread().getName() + ": has released the lock.");
			unfairLoc.unlock();
		}
	}

	public static void main(String[] args) {
		UnfairExample example = new UnfairExample();

		Runnable task = new Runnable() {
			@Override
			public void run() {
				example.accessResource();
			}
		};

		Thread t1 = new Thread(task, "Thread-1");
		Thread t2 = new Thread(task, "Thread-2");
		Thread t3 = new Thread(task, "Thread-3");
		Thread t4 = new Thread(task, "Thread-4");
		Thread t5 = new Thread(task, "Thread-5");
		Thread t6 = new Thread(task, "Thread-6");

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();

	}

}
