package com.ilp.utility;
import java.util.ArrayList;
import java.util.Scanner;
import com.ilp.entity.Customer;
import com.ilp.entity.Product;
import com.ilp.entity.Services;
import com.ilp.services.BankServices;

public class BankUtility {

	public static void main(String[] args) {

		int choice;
		int exitChoice = 1;
		Customer customer = null;
		ArrayList<Services> serviceList = new ArrayList<>();
		ArrayList<Product> productList = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println(
					"1.Create Service\n2.Create Prouduct\n3.Create Account\n4.Manage Accounts\n5.Display Accounts\n6.exit");
			choice = scanner.nextInt();
			switch (choice) {
			case 1:
				serviceList.add(BankServices.createService());
				break;
			case 2:
				if (serviceList.size() == 0) {
					System.out.println("No services available");
				} else {
					productList.add(BankServices.createProduct(serviceList));
				}
				break;
			case 3:
				if (productList.size() == 0) {
					System.out.println("No products available");
				} else {
					customer = BankServices.createAccount(customer, productList);
				}
				break;
			case 4:
				if (customer == null) {
					System.out.println("Create Customer first");
				} else {
					BankServices.manageAccount(customer);
				}
				break;
			case 5:
				if (customer == null) {
					System.out.println("Create Customer first");
				} else {
					BankServices.display(customer);
				}
				break;
			case 6:
				exitChoice = 0;
				break;
			default:
				System.out.println("INVALID");
			}
		} while (exitChoice == 1);

	}

}
