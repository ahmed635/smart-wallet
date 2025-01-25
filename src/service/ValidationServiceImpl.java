package service;

import java.util.regex.*;

public class ValidationServiceImpl implements ValidationService
{

	@Override
	public boolean validateUserName(String userName)
	{
		if (userName.length() < 3)
			return false;
		char firstLetter = userName.charAt(0);
		if (!Character.isUpperCase(firstLetter))
			return false;
		return true;
	}

	@Override
	public boolean validatePassword(String password)
	{
		if (password.length() < 6)
			return false;
		String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}

	@Override
	public boolean validateDeposit(double amount)
	{
		// TODO pls validate money >= 100 and <= 20000
		if (amount >= 100 && amount <= 2000)
			return true;
		return false;
	}

}
