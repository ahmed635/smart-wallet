package service;

import model.*;

public class AccountServiceImpl implements AccountService
{

	private Ewallet ewallet = new Ewallet();
	private static AccountServiceImpl accountService;

	private AccountServiceImpl()
	{
	}

	public static AccountServiceImpl getInstance()
	{
		if (accountService == null)
		{
			accountService = new AccountServiceImpl();
		}
		return accountService;
	}

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
		// TODO create function with name deposit that return
		// TODO true if deposit success
		// TODO false if deposit fail
		// TODO check if account exist on wallet or not if not print account not exist
		// TODO check if account is active or not  if not print account not active
		// TODO make deposit
		if (accountExistAndActive(account))
		{
			// TODO:: do deposit
			if (account.getBalance() >= amount)
			{
				account.setBalance(account.getBalance() - amount);
				return true;
			}
			else
			{
				System.out.println("Not enough balance");
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

	// TODO without duplication
	// TODO make withdraw
	// TODO create function with name withdraw that return
	// TODO true if withdraw success
	// TODO false if withdraw fail
	// TODO check if account exist on wallet or not if not print account not exist
	// TODO check if account is active or not  if not print account not active
	// TODO check if account balance is greater than  money if not print can't deposit because ....
	// TODO make without

	// Transfer Account depositAccount, Account withdrawAccount, int money
	// TODO without duplication
	// TODO make Transfer
	// TODO create function with name transfer that return
	// TODO true if transfer success
	// TODO false if transfer fail
	// TODO check if depositAccount and withdrawAccount exist on wallet or not if not print account not exist
	// TODO check if depositAccount and withdrawAccount is active or not  if not print account not active
	// TODO check if withdrawAccount balance is greater than money if not print can't deposit because ....

	// TODO SHOW Account Details

	// TODO SHOW show Balance
}
