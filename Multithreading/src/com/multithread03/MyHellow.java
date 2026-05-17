package com.multithread03;

public class MyHellow extends Thread {
	@Override
	public void run() {
		for (;;)
			System.out.println("world*****");
	}
}
