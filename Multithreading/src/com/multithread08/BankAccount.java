package com.multithread08;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//time stamp : 00: 21:00

//6.Lock: this is interface
//6.1.InTrinsic:these are built into every object in java. when using synchronized keyword, we are using these automatic locks.
//6.2.Explicit: advance lock control by you 
//6.2.1 lock.lock() and unlock() and tryLock() method
//6.2.2 ReentrantLock: this is a class that implements the Lock interface. it is a reentrant lock, which means that the same thread can acquire the lock multiple times without causing a deadlock.
//6.2.3 ReentrantReadWriteLock: this is a class that implements the Lock interface. it is a read-write lock, which means that it allows multiple threads to read the shared resource simultaneously, but only one thread can write to the shared resource at a time.
//6.2.4 lock will be unlocked in finally block to ensure that the lock is released even if an exception occurs. this is important to prevent deadlocks and ensure that other threads can acquire the lock when it is released.

public class BankAccount {
	private int balance = 100;

	private final Lock lock = new ReentrantLock();

	public int getbalance() {
		return balance;
	}

	public synchronized void withdraw(int amount) {
		System.out.println(Thread.currentThread().getName() + ": attempting to withdraw: " + amount);

		try {
			if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
				System.out.println(Thread.currentThread().getName() + ": lock acquired");
				if (balance >= amount) {
					System.out.println(Thread.currentThread().getName() + ": proceeding with withdrawl");
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						System.out.println(Thread.currentThread().getName() + ": interrupted during sleep");
						Thread.currentThread().interrupt();
						return;
					}
					balance -= amount;
				} else {
					System.out.println(Thread.currentThread().getName() + ": insufficient balance");
				}
			} else {
				System.out.println(Thread.currentThread().getName() + ": could not acquire lock");
				return;
			}
		} catch (Exception e) {
			System.out.println(Thread.currentThread().getName() + ": exception occurred: " + e.getMessage());
			Thread.currentThread().interrupt();
		} finally {
			if (lock.tryLock()) {
				lock.unlock();
				System.out.println(Thread.currentThread().getName() + ": lock released");
			}
		}

		if (Thread.currentThread().isInterrupted()) {
			System.out.println(Thread.currentThread().getName() + ": thread was interrupted");
		}

	}
}
