package service;

import model.Account;

import java.util.Scanner;

public class ApplicationServiceImpl implements ApplicationService
{
	@Override
	public void run()
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome Sir");

		boolean quit = false;
		int tails = 4;
		while (!quit && tails > 0)
		{
			System.out.println("Please Enter your choose");
			System.out.println("a.login     b.signup   c.exit");
			char choose = scanner.next().toLowerCase().charAt(0);
			switch (choose)
			{
			case 'a':
				login();
				break;
			case 'b':
				signup();
				break;
			case 'c':
				quit = true;
				System.out.println("you are welcome.");
				break;
			default:
				tails--;
				System.out.println("Invalid Choose");
			}
			if (tails == 0 || quit)
				break;
		}
	}

	private void signup()
	{
		Scanner scanner = new Scanner(System.in);

		System.out.println("Please Enter User name");
		String name = scanner.nextLine();

		System.out.println("Please Enter password");
		String password = scanner.nextLine();

		ValidationService validationService = new ValidationServiceImpl();

		if (!validationService.validateUserName(name))
		{ // "eslam"
			System.out.println("Invalid UserName");
			return;
		}

		if (!validationService.validatePassword(password))
		{
			System.out.println("Invalid Password");
			return;
		}

		AccountService accountService = AccountServiceImpl.getInstance();
		Account account = new Account(name, password);
		boolean isAccountCreated = accountService.createAccount(account);
		if (isAccountCreated)
		{
			System.out.println("Account Created");
		}
		else
		{
			System.out.println("Account not Created Because There exist account with same user name");
		}

	}

	private void login()
	{
		Scanner scanner = new Scanner(System.in);

		System.out.println("Please Enter User name");
		String name = scanner.nextLine();

		System.out.println("Please Enter password");
		String password = scanner.nextLine();

		ValidationService validationService = new ValidationServiceImpl();

		if (!validationService.validateUserName(name))
		{
			System.out.println("Invalid UserName");
			return;
		}

		if (!validationService.validatePassword(password))
		{
			System.out.println("Invalid Password");
			return;
		}

		AccountService accountService = AccountServiceImpl.getInstance();
		Account account = new Account(name, password);
		if (accountService.loginAccount(account))
		{
			System.out.println("Login Success");
			services(account);
		}
		else
		{
			System.out.println("Account not Exist");
		}
	}

	private void services(Account account)
	{

		// TODO create switch case such as on run function
		// TODO every case on switch call function  don't forget (Invalid choose)
		Scanner scanner = new Scanner(System.in);
		boolean quit = false;
		while (!quit)
		{
			System.out.println("1.Deposit   2.Withdraw    3.show details    4.Transfer    5. show balance   6.exit  7.logout");
			System.out.println("Enter your choice");
			int choice = scanner.nextInt();
			switch (choice)
			{
			case 1:
				deposit(account);
				break;
			case 2:
				withdraw(account);
				break;
			case 3:
				showDetails(account);
				break;
			case 4:
				transfer(account);
				break;
			case 5:
				showBalance(account);
				break;
			case 6:
				quit = true;
				System.out.println("you are welcome.");
				break;
			case 7:
				System.out.println("You Logged out.");
				break;
			default:
				System.out.println("Invalid Choice");
			}
			if (quit)
			{
				break;
			}
		}
	}

	// TODO create deposit function
	void deposit(Account a)
	{
		Scanner scanner = new Scanner(System.in);
		double amount = scanner.nextDouble();
		ValidationService validationService = new ValidationServiceImpl();
		AccountService accountService = AccountServiceImpl.getInstance();
		if (validationService.validateDeposit(amount))
		{
			if (!accountService.deposit(a, amount))
				System.out.println("Deposit Failed");
			else
				System.out.println("Deposit Success");
		}
		else
		{
			System.out.println("Not Valid Amount");
		}
	}

	// TODO create Withdraw function
	void withdraw(Account a)
	{
		// input int money
		// TODO pls validate money >= 100 and <= 8000
	}

	void showDetails(Account a)
	{
		System.out.println("Account details");
		System.out.println(a);
	}

	void transfer(Account withdrawAccount)
	{
		// TODO USER MUST give me user name of account that will transfer
		// TODO input Account depositAccount
		// TODO input int money
	}

	void showBalance(Account a)
	{
		System.out.println("Your Balance = " + a.getBalance());
	}
}
