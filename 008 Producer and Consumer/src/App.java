
public class App {

	private static int[] buffer;
	private static int count;
	private static Object lock = new Object();

	static class Producer {

		void producer() {
			synchronized (lock) {
				if(isFull(buffer)) {
					try{
						lock.wait();
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
				buffer[count++] = 1;
				lock.notify();
			}

		}
	}

	static class Consumer {

		void consumer() {
			synchronized (lock) {
				if(isEmpty(buffer)) {
					try{
						lock.wait();
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
				buffer[--count] = 0;
				lock.notify();
			}

		}

	}

	static boolean isFull(int[] buffer) {
		return count == buffer.length;
	}

	static boolean isEmpty(int[] buffer) {
		return count == 0;
	}

	public static void main(String[] args) throws InterruptedException {

		buffer = new int[10];
		count = 0;

		Producer producer = new Producer();
		Consumer consumer = new Consumer();

		Runnable producerTask = () -> {
			for (int i = 0; i < 50; i++) {
				producer.producer();
			}
			System.out.println("Done Producing");
		};

		Runnable consumerTask = () -> {
			for (int i = 0; i < 45; i++) {
				consumer.consumer();
			}
			System.out.println("Done consuming");
		};

		Thread cons = new Thread(consumerTask);
		Thread prod = new Thread(producerTask);

		cons.start();
		prod.start();

		cons.join();
		prod.join();

		System.out.println("Data is buffer :" + count);
	}

}
