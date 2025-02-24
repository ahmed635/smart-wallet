package service;

import model.*;

public class AccountServiceImpl implements AccountService
{

	private Ewallet ewallet = Ewallet.getInstance();

	@Override
	public boolean createAccount(Account account)
	{
		if (!ewallet.getAccounts().contains(account))
			return ewallet.getAccounts().add(account);
		return false;
	}

	@Override
	public boolean loginAccount(Account account)
	{
		return isAccountExist(account);
	}

	private boolean isAccountExist(Account account)
	{
		return ewallet.getAccounts().stream()
				.anyMatch(acc -> acc.getUserName().equals(account.getUserName()) && acc.getPassword().equals(account.getPassword()));
	}

	@Override
	public boolean deposit(Account account, double amount)
	{
		if (accountExistAndActive(account))
		{
			if (account.getBalance() >= amount)
			{
				account.setBalance(account.getBalance() - amount);
				return true;
			}
			else
			{
				System.out.printf("You have %.2f and try to deposit %.2f %n", account.getBalance(), amount);
				return false;
			}
		}
		return false;
	}

	private boolean accountExistAndActive(Account account)
	{
		if (!isAccountExist(account))
		{
			System.out.println("Account does not exist");
			return false;
		}
		if (!account.getActive())
		{
			System.out.println("Account is not active");
			return false;
		}
		return true;
	}

	@Override
	public boolean withdraw(Account account, double amount)
	{
		if (accountExistAndActive(account))
		{
			account.setBalance(account.getBalance() + amount);
			return true;
		}
		return false;
	}

	public boolean transfer(Account fromAccount, String username, double amount)
	{
		Account toAccount = findAccount(username);
		if (accountExistAndActive(fromAccount) && validDepositAccount(toAccount))
		{
			if (fromAccount.getBalance() >= amount)
			{
				fromAccount.setBalance(fromAccount.getBalance() - amount);
				toAccount.setBalance(toAccount.getBalance() + amount);
				return true;
			}
			else
			{
				System.out.println("You don't have sufficient funds");
				return false;
			}
		}
		return false;
	}

	private boolean validDepositAccount(Account account)
	{
		if (account == null)
		{
			System.out.println("Deposit account does not exist");
			return false;
		}
		else if (!account.getActive())
		{
			System.out.println("Deposit account is not active");
			return false;
		}
		return true;
	}

	private Account findAccount(String to)
	{
		return this.ewallet.getAccounts().stream().filter(acc -> acc.getUserName().equals(to)).findFirst().orElse(null);
	}

	@Override
	public void showBalance(Account account)
	{
		if (isAccountExist(account))
		{
			System.out.printf("Your account balance is %.2f%n", account.getBalance());
		}
	}

	@Override
	public void showDetails(Account account)
	{
		if (isAccountExist(account))
		{
			System.out.println("Account details");
			System.out.println(account);
		}
	}

	@Override
	public Account findAccount(String username, String password)
	{
		return ewallet.getAccounts().stream().filter(account -> account.getUserName().equals(username) && account.getPassword().equals(password))
				.findFirst().orElse(null);
	}
}
