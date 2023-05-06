package SmallChange;
import java.time.LocalDate;

/**
 * The MainMenu class represents a simple personal finance management system
 * that allows users to record their income and expenditure, and view their account balance.
 * The class contains a main menu method that provides users with options for viewing
 * their account balance, recording an income or expenditure, and exiting the system.
 * @author yuanyuanliu
 *
 */
public class MainMenu {

	/**
	 * transactionDate is the date when the transaction occurs
	 */
	private static String transactionDate = LocalDate.now().getMonth().toString().substring(0, 3) +
			" " + LocalDate.now().getDayOfMonth() + ", " + LocalDate.now().getYear();
	
	/**
	 * details is to describe details of all the transactions
	 */
	private static String details = "------------------- Account Statement  ----------------------  " + "\n" +
			"Transaction Date" + "\t" + "Description" + "\t\t" + "Amount" + "\t\t" + "Account Balance" + "\n" ;
	
	/**
	 * accountBalance is to record account balance
	 */
	private static double accountBalance;
	
	/**
	 * amount is the amount of each transaction
	 */
	private static double amount;
	
	    
	    
	
       /**
        * private constructor prevent instantiation.
        */
       private MainMenu() {}
    	   
       
     
       /**
        * This method is to show Small Change Menu to user
        */
       public static void menu() {

     	 final int ACCOUNT_BALANCE = 1;
  		 final int INCOME = 2;
  		 final int EXPENDITURE = 3;
  		 final int EXIT = 4;
  		 boolean loop = true;
  		 String shouldExit;
  		 int choice;
  		 
  		 do {
  			   System.out.println("\n==================== Small Change Menu =====================");
  			   System.out.printf("\t%d Account Balance\n", ACCOUNT_BALANCE);
  			   System.out.printf("\t%d Record an income\n", INCOME);
  			   System.out.printf("\t%d Record an expenditure\n", EXPENDITURE);
  			   System.out.printf("\t%d Exit\n", EXIT);
  			 
  			  
  			   choice = User.inputInteger("Please choose a option (1-4): ");
  			   
  			   switch(choice) {
  			       
  			      case ACCOUNT_BALANCE:
  			    	  showBalance();
  			    	  break;
  			      case INCOME:
  			    	  incomeRecord();
  			    	  break;
  			      case EXPENDITURE:
  			    	expenditureRecord();
  			    	  break;
  			      case EXIT:
  			    	  shouldExit = exit();
  			    	  if(shouldExit.equalsIgnoreCase("y")) {
  			    		  loop = false;
  			    	  }
  			    	  break;
  			      default: 
  			    	  System.out.println("No this option, please choose again");
  			    	  break;
  			   }
  			 
  			 
  		    }while(loop);
  		 
                   
  		      System.out.println("\n" + "Done! You're now exited." + "\n"
  		      + "Thanks for using Small Change. See you next time! ");
  	
       }	
       
             
       
       
       /**
        * show account balance
        */
       public static void showBalance() {
    	   
    	   System.out.println(details);
       }
       
       
       /**
        * This method is to record income and its description
        */
       public static void incomeRecord() {
    	   
    	   String description;
    	   amount = User.inputDouble("Please enter income amount: ");
    	   if(amount<=0) {
    		   System.out.println("Income amount must be greater than 0");
    		   return;
    	   }
    	   accountBalance += amount;
    	   description = User.inputString("Please enter description for the transaction: ");
    	   details += "\n" + transactionDate + "\t\t" + description + "\t\t\t" + "+" + amount + "\t\t" + accountBalance ;
    	   System.out.println("The transaction has been added successfully");
    	   
       }
	
       /**
        * This method is to record expenditure and its description
        */
       public static void expenditureRecord() {
    	   
    	   String description;
    	   amount = User.inputDouble("Please enter expenditure amount: ");
    	   if(amount<=0 || amount > accountBalance) {
    		   System.out.println("expenditure amount must between 0～" + accountBalance);
   		   return;
    	   }
 	       accountBalance -= amount;
    	   description = User.inputString("Please enter the decription for the tranaction: ");
    	   details += "\n" + transactionDate + "\t\t" + description + "\t\t\t" + "-" + amount + "\t\t" + accountBalance ;
    	   System.out.println("The transaction has been added successfully");
             
       }
      
       /**
        * This method is to ask user if they want to exit, accept y/n(non-case-sensitive) only, otherwise keep going asking user
        * @return a string to check if user want to exit
        */
       public static String exit() {

    	   String choice;
    	   
    	   while(true) {
 
    		 choice = User.inputString("Are you sure you want to exit (y/n): ");
    		   if(choice.equalsIgnoreCase("y")|| choice.equalsIgnoreCase("n")) { break;}
    		   
    	   }
    	   
    	   return choice;
       }
}
