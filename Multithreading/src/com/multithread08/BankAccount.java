package com.multithread08;

//time stamp : 00: 12:00
//6.Lock: this is interface
//6.1.InTrinsic:these are built into every object in java. when using synchronized keyword, we are using these automatic locks.
//6.2.Explicit: advance lock control by you 
public class BankAccount {
	private int balance = 100;

	public int getbalance() {
		return balance;
	}

	public synchronized void withdraw(int amount) {
		System.out.println(Thread.currentThread().getName() + ": attempting to withdraw: " + amount);
		if (balance >= amount) {
			System.out.println(Thread.currentThread().getName() + ": proceeding with withdrawl");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {

			}
			balance -= amount;
		} else {
			System.out.println(Thread.currentThread().getName() + ": insufficient balance");
		}
	}
}
