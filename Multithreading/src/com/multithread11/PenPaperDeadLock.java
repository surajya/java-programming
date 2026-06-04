package com.multithread11;

// Deadlock is a situation where two or more threads are blocked forever, waiting for each other to release resources.
//write two classes Pen and Paper. Both classes have a method to use the resource. The Pen class has a method to use the pen, 
//and the Paper class has a method to use the paper. Create two threads, one for each class, and make them try to use the resources in a way that causes a deadlock.

class Pen {
	public synchronized void writeWithPenAndPaper(Paper paper) {
		System.out.println(Thread.currentThread().getName() + " is using the pen and trying to use the paper...");
		paper.finishWriting();
	}
	
	public synchronized  void finishWriting() {
		System.out.println(Thread.currentThread().getName() + " has finished writing with the pen.");
	}
}

class Paper {
	public synchronized void writeWithPenAndPaper(Pen pen) {
		System.out.println(Thread.currentThread().getName() + " is using the paper and trying to use the pen...");
		pen.finishWriting();
	}
	
	public synchronized void finishWriting() {
		System.out.println(Thread.currentThread().getName() + " has finished writing with the paper.");
	}
}

class PenPaperThread1 implements Runnable {
	private Pen pen;
	private Paper paper;

	public PenPaperThread1(Pen pen, Paper paper) {
		this.pen = pen;
		this.paper = paper;
	}

	@Override
	public void run() {
			pen.writeWithPenAndPaper(paper);
	}
}

class PenPaperThread2 implements Runnable {
	private Pen pen;
	private Paper paper;

	public PenPaperThread2(Pen pen, Paper paper) {
		this.pen = pen;
		this.paper = paper;
	}

	@Override
	public void run() {
		synchronized (pen) {
			paper.writeWithPenAndPaper(pen);
		}
	}
}

public class PenPaperDeadLock {
	public static void main(String[] args) throws InterruptedException {
		Pen pen = new Pen();
		Paper paper = new Paper();

		Thread thread1 = new Thread(new PenPaperThread1(pen, paper), "Thread-1");
		Thread thread2 = new Thread(new PenPaperThread2(pen, paper), "Thread-2");

		thread1.start();
		thread2.start();
	}
}
