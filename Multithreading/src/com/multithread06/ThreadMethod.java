package com.multithread06;

//There are multiple method

//1.start
//2.run
//3.sleep
//4.setPriority
//5.join
//6.interrupt
//7.yield

//public class ThreadMethod extends Thread {
//
//	public ThreadMethod(String name) {
//		super(name);
//	}
//
//	@Override
//	public void run() {
//		for (int i = 0; i < 10; i++) {
//			System.out.println(Thread.currentThread().getName() + " - priority: " + Thread.currentThread().getPriority()
//					+ " - count: " + i);
//			Thread.yield();
////			try {
////				Thread.sleep(1000);
////			} catch (InterruptedException e) {
////				System.out.println(e);
////			}
//		}
//	}
//
//	public static void main(String[] args) throws InterruptedException {
//		ThreadMethod low = new ThreadMethod("low-Priority");
//		ThreadMethod normal = new ThreadMethod("nor-Priority");
//		ThreadMethod high = new ThreadMethod("Hig-Priority");
//		low.setPriority(Thread.MIN_PRIORITY);
//		normal.setPriority(Thread.NORM_PRIORITY);
//		high.setPriority(Thread.MAX_PRIORITY);
//		low.start();
//		normal.start();
//		// normal.join();
//		high.start();
////		high.interrupt();
////		high.interrupt();
//
//		System.out.println("Main method running..........");
//	}
//}

//8.setDeamon : JVM will wait for userThread, but not for deamon thread(It is background thread
public class ThreadMethod extends Thread {

	public ThreadMethod(String name) {
		super(name);
	}

	@Override
	public void run() {
		while (true) {
			System.out.println("hellow word!!!");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ThreadMethod tm = new ThreadMethod("suraj");
		tm.setDaemon(true);
		tm.start();
		System.out.println("Main thread............");
	}
}
