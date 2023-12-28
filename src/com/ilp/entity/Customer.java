package com.ilp.entity;

import java.util.ArrayList;

public class Customer {
	private String customerCode;
	private String customerName;
	private ArrayList<Account> accounts = new ArrayList<>();
	public Customer(String customerCode, String customerName) {
		this.customerCode = customerCode;
		this.customerName = customerName;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public ArrayList<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}
}