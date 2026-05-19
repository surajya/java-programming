package com.multithread07;

//1.synchronized keyword

//2.synchronized(this){} block
//3.Critical Section
//4.Race Condition
//5.Mutual Exclusion

public class SynchronizationAndSychronizedKeyword {

	public static void main(String[] args) {
		Counter counter = new Counter();
		MyThread t1 = new MyThread(counter);
		MyThread t2 = new MyThread(counter);
		MyThread t3 = new MyThread(counter);
		MyThread t4 = new MyThread(counter);

		t1.start();
		t2.start();
		t3.start();
		t4.start();

		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		} catch (Exception e) {

		}
		System.out.println(counter.getCount());
	}
}
