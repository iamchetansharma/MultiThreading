package com.deadlock;

public class App {

	public static void main(String[] args) throws InterruptedException {
		
		Dead dead = new Dead();
		Runnable runnable1 = () -> {dead.a();};
		Runnable runnable2 = () -> {dead.b();};
		
		Thread thread1 = new Thread(runnable1);
		thread1.start();
		
		Thread thread2 = new Thread(runnable2);
		thread2.start();
		
		thread1.join();
		thread2.join();

	}

}
