package com.multithread03;

public class MyHellowRunnable implements Runnable {
	@Override
	public void run() {
		for (;;)
			System.out.println("world*****");
	}
}
