package com.ilp.entity;

import java.util.ArrayList;

public abstract class Product {
	private String productCode;
	private String productName;
	private ArrayList<Services> productServices = new ArrayList<>();
	public Product(String productCode, String productName) {
		this.productCode = productCode;
		this.productName = productName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public ArrayList<Services> getProductServices() {
		return productServices;
	}
	public void setProductServices(ArrayList<Services> productServices) {
		this.productServices = productServices;
	}
}
