package com.racecondition;


public class App {

	public static void main(String[] args) throws InterruptedException {
		
		LongWrapper longWrapper = new LongWrapper(0);

		Runnable runnable = () -> {
			
			for(int i = 0 ; i < 1_000; i++){
				longWrapper.increment();
			}
		};
		
		Thread[] threads = new Thread[1_000];
		for(int i = 0 ; i < threads.length; i++){
			threads[i] = new Thread(runnable);
			threads[i].start();
		}
		
		for(int i = 0 ; i < threads.length ; i++){
			threads[i].join();
		}
		System.out.println("Number is :" +longWrapper.getNumber());
	}

}
