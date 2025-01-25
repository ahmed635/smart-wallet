package service;

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
		return validPassword(password);
	}

	private boolean validPassword(String password){
		boolean hasNumbers = false;
		boolean hasLowerCaseLetters = false;
		boolean hasUpperCaseLetters = false;
		boolean hasSpecialChars = false;
		for (char c : password.toCharArray())
		{
			if (Character.isDigit(c))
			{
				hasNumbers = true;
			}
			else if (Character.isLowerCase(c))
			{
				hasLowerCaseLetters = true;
			}
			else if (Character.isUpperCase(c))
			{
				hasUpperCaseLetters = true;
			}
			else
			{
				hasSpecialChars = true;
			}
		}
		return hasNumbers && hasLowerCaseLetters && hasUpperCaseLetters && hasSpecialChars;
	}
}
