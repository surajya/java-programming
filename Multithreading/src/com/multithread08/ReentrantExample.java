package com.multithread08;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantExample {

	private final Lock lock = new ReentrantLock();

	public void outerMethod() {
		lock.lock();
		try {
			System.out.println("Outer method is executing.");
			innerMethod();
		} finally {
			lock.unlock();
		}
	}

	private void innerMethod() {
		lock.lock();
		try {
			System.out.println("Inner method is executing.");
			// outerMethod(); // This will not cause a deadlock because the same thread can
			// acquire the lock
			// multiple times.
			System.out.println(
					Thread.currentThread().getName() + ": lock hold count: " + ((ReentrantLock) lock).getHoldCount());
		} finally {
			lock.unlock();
		}

	}

	public static void main(String[] args) {
		ReentrantExample example = new ReentrantExample();
		example.outerMethod();
	}

}
