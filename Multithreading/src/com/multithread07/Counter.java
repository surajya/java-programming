package com.multithread07;

public class Counter {
	private int counter = 0;

	public synchronized void increment() {
//		synchronized(this) {
//			counter++;
//		}
		counter++;
	}

	public int getCount() {
		return counter;
	}
}
