
package src;


/**
 * The FinancialTrackerMenu class represents the main menu of the FinancialFit Tracker Application.
 * It provides options for user registration, sign-in, and password reset.
 * @author yuanyuanliu
 */
public class FinancialTrackerMenu {
 
    
    /**
     * prevent instantiation
     */
    private FinancialTrackerMenu(){}
    
    
    /**
     * Displays the main menu options and handles user selection.
     */
    public static void menu() {
	
	final int REGISTER = 1;
	final int SIGN_IN = 2;
	final int RESET_PASSWORD = 3;
	int option;

	System.out.println("\t\t\t  Welcome to the FinancialFit Tracker Application  ");
	System.out.printf("%d Register  %n", REGISTER);
	System.out.printf("%d Sign In  %n",SIGN_IN);
	System.out.printf("%d Reset password  %n", RESET_PASSWORD);

	option = UserInputController.inputInteger("Please select option:");

	switch (option) {
	case REGISTER -> Register.register();
	case SIGN_IN -> SignIn.signIn();
	case RESET_PASSWORD -> ResetPassword.resetPassword();
	default -> System.out.println("Invalid select, please select again");
	}

}	


}
