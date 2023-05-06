package SmallChange;
import java.util.ArrayList;
import java.util.Random;

/**
 * The SmallChange class provides the functionalities of a small change application, 
 * which allows users to register, sign in, and reset their passwords.
 * @author yuanyuanliu
 */
public class SmallChange {

/**
 * Displays the main menu of the Small Change application and handles
 * user input to navigate to the corresponding functionalities.
 */
public static void menu() {
	
	final int REGISTER = 1;
	final int SIGN_IN = 2;
	final int FORGET_PASSWORD = 3;
	int option;
	ArrayList<Account> accounts = new ArrayList<>();

	System.out.println("\t\t\t  Welcome to the Small Change Application  ");
	System.out.printf("%d Register  %n", REGISTER);
	System.out.printf("%d Sign In  %n",SIGN_IN);
	System.out.printf("%d Forget password  %n", FORGET_PASSWORD);

	option = User.inputInteger("Please select option:");

	switch (option) {
	case REGISTER:
		register(accounts);
		break;
	case SIGN_IN:
		signIn(accounts);
		break;
	case FORGET_PASSWORD:
		resetPassword(accounts);
		break;
	default:
		System.out.println("Invalid select, please select again");
		break;
	}

}	

/**
 * Allows users to sign in to their accounts
 * @param accounts the list of registered accounts
 */
public static void signIn(ArrayList<Account> accounts) {
	System.out.println("Welcome to the sign in page");
	String name=User.inputString("Please enter user name: ");
	//check whether the user already exists 
	if(!contain(accounts,name)) { 
		System.out.println("User doesn't exist, please register first");
		return;}
	Account account=accounts.get(getAccountIndex(accounts,name));
	String password=User.inputString("Please enter password: ");
     //check user password whether match with the account
	if(!password.equals(account.getPassword())) {
		System.out.println("password doesn't match, please check your password and try agin");
		return;
	}
	//go to main menu after sign in
	MainMenu.menu();
	
}

/**
 * Allows users to register a new account
 * @param accounts the list of registered accounts
 */
public static void register(ArrayList<Account> accounts) {
	
	System.out.println("Welcome to the register page");
	
	while(true) {
		String name=User.inputString("Please enter user name: ");
		
	//check whether the user already exists
	if(contain(accounts,name)) { 
		System.out.println("user already exist, please back to the menu and log in");
		continue;}
	
	//validate user name, should contain at least one digit, one letter and no underscores
	if(!(name.matches("^(?=\\w*[0-9])(?=\\w*[a-zA-Z])\\w{3,15}$"))) {
		System.out.println("user name should contain at least one digit, one letter, and length between 4 and 15 ");
		continue;
	}
		

	      //validate password
			String password=User.inputString("Please enter user password: ");
			if(password == "" || !password.matches("^(?=\\w*[0-9])(?=\\w*[a-zA-Z])\\w{8,}$")) {
				System.out.println("Password should be at least 8 characters and numbers");
				continue;
			}
			String confirmPassword=User.inputString("Please enter your password again: ");
			if(!(password.equals(confirmPassword))) {
				System.out.println("Password didn't match, please check you password");
				continue;
			}
			
		//validate email address
       String email = User.inputString("Please enter your email: ");
		if(email.matches("\\S+@\\S+\\.\\S+")) {
			System.out.println("Congratulate, you registed sucessfully");
			accounts.add(new Account(name,password,email));
			signIn(accounts);
			break;
		}else {
			System.out.println("Email format incorrect, please check it and try again");
			continue;
		}
			
	}
	
}
	


/**
 *  Allows users to reset their password
 * @param accounts the list of registered accounts
 */
public static void resetPassword(ArrayList<Account> accounts) {
	
	System.out.println("Welcome to the reset password page");
	
	String name=User.inputString("Please enter user name: ");
	if(!contain(accounts,name)) {
		System.out.println("We could not find an account associated with the username");
		return;
	}
	
	Account account=accounts.get(getAccountIndex(accounts,name));
	String email=User.inputString("Please enter user email: ");
	
	if(!email.equalsIgnoreCase(account.getEmail())) {
		System.out.println("We could not find an account associated with the email you provided");
		return;
	}
	int verificationCode = new Random().nextInt(900000) + 100000;
	int input = User.inputInteger("Please enter the six-digit verification code sent to your email: ");
	if(verificationCode!=input) {
		System.out.println("A verification code incorrect");
	}else {
		
		String password=User.inputString("Please enter user password: ");
		if(!password.equals(account.getPassword())) {
			System.out.println("Password doesn't match, please check your password and try agin");
			return;
		}
		String newPassword=User.inputString("Please enter the new password: ");
		account.setPassword(newPassword);
		System.out.println("The password has been changed successfully");
		signIn(accounts);}
	
}
	
/**
 * Returns the index of the Account object that matches the given user name 
 * If the user name is not found in the ArrayList, returns -1
 * @param accounts the list of registered accounts
 * @param name the user name of user account
 * @return the index of the Account object that matches the user name
 */
public static int getAccountIndex(ArrayList<Account> accounts, String name) {
	
	for(int i=0;i<accounts.size();i++) {
		String accountName =accounts.get(i).getName();
		if(name.equalsIgnoreCase(accountName)) { return i;} }
	
	return -1;
}

/**
 * Checks if an account with the given name exists in the ArrayList of accounts.
 * @param accounts the list of registered accounts
 * @param name the name of the account to search for
 * @return  true if an account with the given name exists in the ArrayList, false otherwise
 */
public static boolean contain(ArrayList<Account> accounts, String name) {
	int index = getAccountIndex(accounts,name);
	return index>=0;
	}



}
