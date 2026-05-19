package com.multithread04;

public class ThreadLifeCycle extends Thread {

	@Override
	public void run() {
		System.out.println("RUNNING");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ThreadLifeCycle tlc = new ThreadLifeCycle();
		System.out.println(tlc.getState());
		tlc.start();
		System.out.println(tlc.getState());
		Thread.sleep(100);
		System.out.println(tlc.getState());
		tlc.join();
		System.out.println(tlc.getState());
	}
}
