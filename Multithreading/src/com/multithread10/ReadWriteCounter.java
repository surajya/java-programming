package com.multithread10;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//ReentrantReadWritelock is a ReadWriteLock implementation that allows for multiple read thread simultaneously when no thread are write.
//Best used when reads are frequent, writes are rare — otherwise a simple synchronized or ReentrantLock is simpler
public class ReadWriteCounter {

	private int count = 0;

//	private final ReadWriteLock lock = new ReentrantReadWriteLock();
//
//	private final Lock readLock = lock.readLock();
//
//	private final Lock writeLock = lock.writeLock();

	private final Lock lock = new ReentrantLock();
	private final Lock readLock = lock;
	private final Lock writeLock = lock;

	public void increment() {
		writeLock.lock();
		try {
			count++;
		} finally {
			writeLock.unlock();
		}
	}

	public int getCount() {
		readLock.lock();
		try {
			return count;
		} finally {
			readLock.unlock();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ReadWriteCounter counter = new ReadWriteCounter();

		Runnable readTask = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					System.out.println(Thread.currentThread().getName() + "  Read count: " + counter.getCount());
				}
			}
		};

		Runnable writeTask = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					counter.increment();
					System.out.println(Thread.currentThread().getName() + "  write count: increment");
				}
			}
		};

		Thread t1 = new Thread(writeTask, "Writer-1");
		Thread t2 = new Thread(readTask, "Reader-1");
		Thread t3 = new Thread(readTask, "Reader-2");

		t1.start();
		t2.start();
		t3.start();

		t1.join();
		t2.join();
		t3.join();
		System.out.println("Final count: " + counter.getCount());
	}

}
