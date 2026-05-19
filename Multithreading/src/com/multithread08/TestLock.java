package com.multithread08;

public class TestLock {

	public static void main(String[] args) throws InterruptedException {
		BankAccount ba = new BankAccount();

		Runnable run = new Runnable() {
			@Override
			public void run() {
				ba.withdraw(50);
			}
		};
		Thread t1 = new Thread(run, "t01");
		Thread t2 = new Thread(run, "t02");
		t1.start();
		t2.start();

		t1.join();
		t2.join();

		System.out.println("Account balance after transaction  :" + ba.getbalance());
	}

}
