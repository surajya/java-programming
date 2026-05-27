package com.multithread10;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteCounter {

	private int count = 0;

	private final ReadWriteLock lock = new ReentrantReadWriteLock();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
