package model;

import java.util.*;

public class Ewallet
{
	private static Ewallet instance;

	private String name = "EraaSoft Cash";

	private List<Account> accounts = new ArrayList<>();

	private Ewallet()
	{
	}

	public static Ewallet getInstance()
	{
		if (instance == null)
			instance = new Ewallet();
		return instance;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<Account> getAccounts()
	{
		return accounts;
	}

	public void setAccounts(List<Account> accounts)
	{
		this.accounts = accounts;
	}
}
