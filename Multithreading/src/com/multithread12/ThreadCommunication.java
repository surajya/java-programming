package com.multithread12;


// This example demonstrates inter-thread communication using wait() and notify() methods.
// The Producer thread produces data and the Consumer thread consumes it. They coordinate using a shared resource.
// The SharedResource class has synchronized methods for producing and consuming data. The producer waits if the data is already produced, and the consumer waits if there is no data to consume.
// The main method initializes the shared resource and starts both the producer and consumer threads.
// Note: The output may vary due to the nature of thread scheduling.
// Output will show produced and consumed values in an interleaved manner.
//NotifyAll can be used instead of notify if there are multiple waiting threads, but in this example, we have only one producer and one consumer, so notify is sufficient.
class SharedResource{
	int data;
	boolean hasData = false;
	
	public synchronized void produce(int value) {
		while(hasData) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		this.data = value;
		System.out.println("Produced: " + value);
		hasData = true;
		notify();
	}
	
	public synchronized int consume() {
		 while(!hasData) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		hasData = false;
		notify();
		System.out.println("Consumed: " + data);
		return data;
	}
}

class Producer implements Runnable{
	SharedResource resource;
	
	public Producer(SharedResource resource) {
		this.resource = resource;
	}
	
	@Override
	public void run() {
		for(int i=0; i<9; i++) {
			resource.produce(i);
		}
	}
}

class Consumer implements Runnable{
	SharedResource resource;
	
	public Consumer(SharedResource resource) {
		this.resource = resource;
	}
	
	@Override
	public void run() {
		for(int i=0; i<9; i++) {
			resource.consume();
		}
	}
}

public class ThreadCommunication {

	public static void main(String[] args) {
		SharedResource resource = new SharedResource();
		
		Thread producerThread = new Thread(new Producer(resource));
		Thread consumerThread = new Thread(new Consumer(resource));
		
		producerThread.start();
		consumerThread.start();

	}

}
