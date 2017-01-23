package com.bankaccount;

public class Bank {
	private int balance;

	public Bank(int balance) {
		this.balance = balance;
	}

	public synchronized int getBalance() {
		return balance;
	}

	public synchronized void desposit(int amount) {
		balance += amount;
		//System.out.println("Balance is :" + balance);
	}

	@Override
	public String toString() {
		return "Balance :" + balance;
	}
}
