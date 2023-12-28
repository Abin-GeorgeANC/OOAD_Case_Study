package com.ilp.entity;

public class SavingsMaxAccount extends Product {

	private int minimumBalance;

	public SavingsMaxAccount(String productCode, String productName, int minimumBalance) {
		super(productCode, productName);
		this.minimumBalance = minimumBalance;
	}
	public int getMinimumBalance() {
		return minimumBalance;
	}
	public void setMinimumBalance(int minimumBalance) {
		this.minimumBalance = minimumBalance;
	}
}