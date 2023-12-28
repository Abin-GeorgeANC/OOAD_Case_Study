package com.ilp.services;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import com.ilp.entity.Account;
import com.ilp.entity.CurrentAccount;
import com.ilp.entity.Customer;
import com.ilp.entity.LoanAccount;
import com.ilp.entity.Product;
import com.ilp.entity.SavingsMaxAccount;
import com.ilp.entity.Services;

public class BankServices {
	// Function to createServices
	public static Services createService() {
		Services service = null;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Servic Code");
		String serviceCode = scanner.nextLine();
		System.out.println("Enter Service Name");
		String serviceName = scanner.nextLine();
		System.out.println("Enter Service Rate");
		double serviceRate = scanner.nextDouble();
		service = new Services(serviceCode, serviceName, serviceRate);
		return service;
	}

	// Function to create products
	public static Product createProduct(ArrayList<Services> serviceList) {
		Product product = null;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Product Code");
		String productCode = scanner.nextLine();
		System.out.println("Enter Prouduct Name");
		String productName = scanner.nextLine();
		// Check product
		if (productName.equalsIgnoreCase("SavingsMaxAccount")) {
			SavingsMaxAccount savingsMax = new SavingsMaxAccount(productCode, productName, 1000);
			product = addServices(savingsMax, serviceList);
		} else if (productName.equalsIgnoreCase("CurrentAccount")) {
			CurrentAccount currentAccount = new CurrentAccount(productCode, productName);
			product = addServices(currentAccount, serviceList);
		} else if (productName.equalsIgnoreCase("LoanAccount")) {
			LoanAccount loanAccount = new LoanAccount(productCode, productName, .03);
			product = addServices(loanAccount, serviceList);
		} else {
			System.out.println("Invalid Product");
		}
		return product;
	}

	// Function to add services to the 3 products
	private static Product addServices(Product product, ArrayList<Services> serviceList) {
		ArrayList<Services> productServiceList = new ArrayList<>();
		int continueChoice = 1;
		int serviceChoice;
		int serviceCount = 1;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Select services");
		for (Services service : serviceList) {
			System.out.println(serviceCount + "\n");
			System.out.println(service.getServiceCode());
			System.out.println(service.getServiceName());
			System.out.println(service.getServiceRate());
			serviceCount++;
			System.out.println();
		}
		do {
			System.out.println("Enter service No:");
			serviceChoice = scanner.nextInt();
			productServiceList.add(serviceList.get(serviceChoice - 1));
			product.setProductServices(productServiceList);
			System.out.println("Enter 0 to stop adding services");
			continueChoice = scanner.nextInt();
		} while (continueChoice == 1);
		return product;
	}

	// Function to create Customer AND add accounts
	public static Customer createAccount(Customer customer, ArrayList<Product> productList) {
		Account account = null;
		Scanner scanner = new Scanner(System.in);
		String accountChoice;
		ArrayList<Account> accountList = new ArrayList<Account>();
		System.out.println("Enter the customer Id");
		String customerId = scanner.nextLine();
		if (customer != null && customer.getCustomerCode().equalsIgnoreCase(customerId)) {
			System.out.println("Customer Found");
			accountList = customer.getAccounts();
		} else {
			System.out.println("Customer Id Not Available Creating..");
			System.out.println("Enter Customer name");
			String customerName = scanner.nextLine();
			customer = new Customer(customerId, customerName);
			System.out.println("Customer Created");
		}
		System.out.println("*****Accounts Available*****");
		Random random = new Random();
		int accountNumber = random.nextInt(100000);
		for (Product productItter : productList) {
			System.out.print(productItter.getProductName() + ",");
		}
		System.out.println();
		System.out.println("Enter your Choice");
		accountChoice = scanner.nextLine();
		System.out.println("Enter Account balance");
		double accountBalance = scanner.nextDouble();
		if (accountChoice.equalsIgnoreCase("savingsmaxaccount") && accountBalance < 1000) {
			do {
				System.out.println("Savings max need atleast 1000 minumumbalance");
				System.out.println("Enter Account balance");
				accountBalance = scanner.nextDouble();
			} while (accountBalance < 1000);
		}
		int indexOfProduct = 0;
		// find product from list
		for (Product product : productList) {

			if (product.getProductName().equalsIgnoreCase(accountChoice)) {
				indexOfProduct = productList.indexOf(product);
			}
		}
		// add product and account details to account
		switch (accountChoice) {
		case "savingsmaxaccount":
			account = new Account(accountNumber, "savings max account", accountBalance,
					productList.get(indexOfProduct));
			System.out.println("Savings Max Account Created for " + customer.getCustomerName());
			accountList.add(account);
			break;
		case "currentaccount":
			account = new Account(accountNumber, "current account", accountBalance, productList.get(indexOfProduct));
			System.out.println("Current Account Created for " + customer.getCustomerName());
			accountList.add(account);
			break;
		case "loanaccount":
			account = new Account(accountNumber, "loan account", accountBalance, productList.get(indexOfProduct));
			System.out.println("Loan Account Created for " + customer.getCustomerName());
			accountList.add(account);
			break;
		default:
			System.out.println("Invalid");
		}
		customer.setAccounts(accountList);
		System.out.println("Account is Active!!!!");
		System.out.print("Services Available: ");
		for (Services service : account.getProduct().getProductServices()) {
			System.out.print(service.getServiceName() + ",");
		}
		System.out.println();
		return customer;
	}

	// function to withdraw deposit and display balance
	public static void manageAccount(Customer customer) {
		Account account = null;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Customer ID");
		String customerId = scanner.nextLine();
		System.out.println(customer.getCustomerName() + " has the following accounts");
		for (Account accountitter : customer.getAccounts()) {
			System.out.print(accountitter.getAccountType() + ",");
		}
		System.out.println("Enter your choice:");
		String accountChoice = scanner.nextLine();
		for (Account accountitter : customer.getAccounts()) {
			if (accountitter.getAccountType().equalsIgnoreCase(accountChoice)) {
				account = accountitter;
			}
		}
		char operationContinue = 'y';
		do {
			System.out.println("1.Deposit\t" + "2.Withdraw\t" + "3.Display Balance");
			int operationChoice = scanner.nextInt();
			double amount;
			double balance;
			if (operationChoice == 1) {
				System.out.println("Enter amount to be deposited:");
				amount = scanner.nextDouble();
				balance = account.getAccountBalance();
				if ((account.getAccountType().equalsIgnoreCase("savings max account"))
						|| (account.getAccountType().equalsIgnoreCase("current account"))) {
					account.setAccountBalance(balance + amount);
					System.out.println("New balance is: " + account.getAccountBalance());

				} else if (account.getAccountType().equalsIgnoreCase("loan account"))

				{
					System.out.println("1.CashDeposit 2. Cheque Deposit");
					int loanDepositType = scanner.nextInt();
					if (loanDepositType == 1) {
						account.setAccountBalance(balance + amount);
						System.out.println("New balance is: " + account.getAccountBalance());
					} else if (loanDepositType == 2) {
						account.setAccountBalance(balance + (amount - (amount * .3)));
						System.out.println("New balance is: " + account.getAccountBalance());
					} else {
						System.out.println("Invalid");
					}
				}

			} else if (operationChoice == 2) {
				System.out.println("Enter amount to be withdrawn");
				amount = scanner.nextDouble();
				if (account.getAccountType().equalsIgnoreCase("loan account")) {
					System.out.println("Cant withdraw from loan account");
				} else if (account.getAccountType().equalsIgnoreCase("savings max account")) {
					if ((account.getAccountBalance() - amount) < 1000) {
						System.out.println("Savings max account needs atleast 1000rs balance");
					} else {
						account.setAccountBalance(account.getAccountBalance() - amount);
						System.out.println("New balance is: " + account.getAccountBalance());
					}
				} else if (account.getAccountType().equalsIgnoreCase("current account")) {
					if ((account.getAccountBalance() - amount) < 0) {
						System.out.println("The amount more than your balance");
					} else {
						account.setAccountBalance(account.getAccountBalance() - amount);
						System.out.println("New balance is: " + account.getAccountBalance());
					}
				}

			} else if (operationChoice == 3) {
				display(account);

			} else {

				System.out.println("Invalid operation");
			}
			System.out.println("Do you want to continue (y/n)");
			operationContinue = scanner.next().charAt(0);

		} while (operationContinue == 'y');
	}

	private static void display(Account account) {
		System.out.println("Account Balance is: " + account.getAccountBalance());

	}

	// Function to Dispaly account
	public static void display(Customer customer) {
		System.out.println("********************" + "Customer-Account-Details" + "****************************");
		System.out.println("CustomerId" + "  " + "Customer Name" + "   " + "Account Type" + "       " + "Balance");
		System.out.println("**********************************************************************************");
		for (Account account : customer.getAccounts()) {
			System.out.print(customer.getCustomerCode() + "        " + customer.getCustomerName() + "          "
					+ account.getAccountType() + "         " + account.getAccountBalance());
			System.out.println();
			System.out.print("Services: ");
			for (Services service : account.getProduct().getProductServices()) {
				System.out.print(service.getServiceName() + ",");
			}

			System.out.println();

		}
		System.out.println();

	}

}
