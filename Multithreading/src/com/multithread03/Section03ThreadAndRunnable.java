package com.multithread03;

public class Section03ThreadAndRunnable {

	public static void main(String[] args) {

		MyHellow myHellow = new MyHellow();
		// myHellow.start(); help of Thread class

		// Runnable
		Thread thread = new Thread(myHellow);
		thread.start();
		for (;;)
			System.out.println("Hellow&&&&&&&&&&&");
	}

}
