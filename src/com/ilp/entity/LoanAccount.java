package com.ilp.entity;

public class LoanAccount extends Product {

	private double checkDeposit;
	public LoanAccount(String productCode, String productName, double checkDeposit) {
		super(productCode, productName);
		this.checkDeposit = checkDeposit;
	}
}