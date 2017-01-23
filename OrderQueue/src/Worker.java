//This class tests the Worker class. It invokes the run() method of a Worker
//* instance.
//
//The Worker class holds an instance of a queue of Order objects.
//The max size of this queue is 5. 10 Orders are generated asynchronously and placed in the queue.
//
//Those 10 Orders are processed concurrently as they are placed in the
//queue. Any time we are waiting for Orders to be FULFILLED, this should be printed to standard output. An Order is simply a class with a state field and an order number (1-10). An Order can be in either NEW or FULFILLED states. When a new Order is created, the default state is NEW. Whenever there is a state change, this is printed to standard output. The test ends when all Orders are FULFILLED.

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Producer implements Runnable {

	private BlockingQueue queue;

	public Producer(BlockingQueue queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				System.out.println("Produced: " + i);
				queue.put(i);
			} catch (InterruptedException ex) {
				ex.getMessage();
			}
		}
	}

}

class Consumer implements Runnable {

	private BlockingQueue queue;

	public Consumer(BlockingQueue queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println("Consumed: " + queue.take());
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}

	}

}

public class Worker {

	public static void main(String[] args) {
		BlockingQueue queue = new LinkedBlockingQueue(5);
		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue);
		
//		Runnable producerTask = () -> {
//			for (int i = 0; i < 10; i++) {
//				try {
//					System.out.println("Produced: " + i);
//					queue.put(i);
//				} catch (InterruptedException ex) {
//					ex.getMessage();
//				}
//			}
//		};
//
//		Runnable consumerTask = () -> {
//			while (true) {
//				try {
//					System.out.println("Consumed: " + queue.take());
//				} catch (InterruptedException ex) {
//					ex.printStackTrace();
//				}
//			}
//		};
//		
		// Creating Producer and Consumer Thread
		Thread prodThread = new Thread( producer);
		Thread consThread = new Thread( consumer);

		// Starting producer and Consumer thread
		prodThread.start();
		consThread.start();
		
		
	}

}
