package service;

public class ValidationServiceImpl implements ValidationService
{

	@Override
	public boolean validateUserName(String userName)
	{
		if (userName.length() < 3)
			return false;
		char firstLetter = userName.charAt(0);
		return Character.isUpperCase(firstLetter);
	}

	@Override
	public boolean validatePassword(String password)
	{
		if (password.length() < 6)
			return false;
		boolean hasDigit = false;
		boolean hasUpper = false;
		boolean hasLower = false;
		boolean hasSpecial = false;

		int i = 0;
		while (i < password.length())
		{
			if (Character.isDigit(password.charAt(i)))
			{
				hasDigit = true;
			}
			else if (Character.isUpperCase(password.charAt(i)))
			{
				hasUpper = true;
			}
			else if (Character.isLowerCase(password.charAt(i)))
			{
				hasLower = true;
			}
			else
			{
				hasSpecial = true;
			}
			i++;
			if (hasDigit && hasUpper && hasLower && hasSpecial)
				return true;
		}
		return false;
	}

	@Override
	public boolean validateDeposit(double amount)
	{
		return amount >= 100 && amount <= 2000;
	}

	@Override
	public boolean validateWithdraw(double amount)
	{
		return amount >= 100 && amount <= 8000;
	}

	@Override
	public boolean validateTransfer(double amount)
	{
		return amount > 0;
	}

}
