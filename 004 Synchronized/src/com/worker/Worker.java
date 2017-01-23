package com.worker;

import com.bankaccount.Bank;

public class Worker implements Runnable {

	private Bank account;

	public Worker(Bank account) {
		this.account = account;
	}

	@Override
	public void run() {
		for(int i=0; i<10; i++){
			int startBalance = account.getBalance();
			account.desposit(10);
			int endBalance = account.getBalance();
			System.out.println( "Balance :" +startBalance+" End Balance : " + endBalance);
		}
	}
	
	
}
