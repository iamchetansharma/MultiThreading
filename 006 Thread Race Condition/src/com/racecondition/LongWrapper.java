package com.racecondition;

public class LongWrapper {

	private int number;
	private Object Key = new Object();
	LongWrapper(int number) {
		this.number = number;
	}
	
	public int getNumber(){
		return number;
	}
	
	public void increment(){
		synchronized (Key) {
			number += 1;
		}
		
	}
}
