package src;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;


/**
 * The TransactionManager class provides functionality for managing financial transactions.
 * It includes methods for viewing balance, recording income and expenses, and exiting the program.
 * This class interacts with the underlying data source to retrieve and update transaction data.
 * 
 * @author yuanyuanliu
 */
public class TransactionManager {
        
        /**
         * the database connection
         */
        private static Connection connection;
        
        /**
         * the statement used for executing SQL queries
         */
        private static Statement statement;
	                    
	/**
	 * record account balance
	 */
	private static double balance;
	
	/**
	 * the amount of each transaction
	 */
	private static double amount;
	
	    
	    
    static{
       
            balance = getBalance();         
    }

       /**
        * private constructor prevent instantiation.
        */
       private TransactionManager() {}
    	   
       
     
       /**
        * Displays the menu options for the user account and handles user input.
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
  			   System.out.println("\n==================== Menu =====================");
  			   System.out.printf("\t%d View Balance\n", ACCOUNT_BALANCE);
  			   System.out.printf("\t%d Record Income\n", INCOME);
  			   System.out.printf("\t%d Record Expense\n", EXPENDITURE);
  			   System.out.printf("\t%d Exit\n", EXIT);
  			 
  			  
  			   choice = UserInputController.inputInteger("Please choose a option (1-4): ");
  			   
  			   switch(choice) {
  			       
  			      case ACCOUNT_BALANCE -> viewHistory();
  			      case INCOME -> recordIncome();
  			      case EXPENDITURE -> recordExpense();
  			      case EXIT -> {
                                  shouldExit = exit();
                                  if(shouldExit.equalsIgnoreCase("y")) {
                                      loop = false;
                                  }
                      }
  			      default -> System.out.println("Invalid option, please choose again");
  			   }
  			 
  			 
  		    }while(loop);
  		 
                   
  		      System.out.println("""
                                         
                                         Done! You're now exited.
                                         Thanks for using FinancialFit Tracker. See you next time! """);
  	
       }	
       
             
       
       
       /**
        * View account transaction history
        */
       public static void viewHistory() {
    	  
                ResultSet result = null;
                
            try {
               
               connection = DBConnection.getConnection();
               statement = connection.createStatement();
               result = statement.executeQuery("SELECT date, description, amount, balance FROM transactions "
                        + "WHERE user_id = '" + SignIn.getCurrentUserId() + "'");
                System.out.println( "------------------- Transaction History  ----------------------  " + "\n" +
                        "Date" + "\t\t\t" + "Amount" + "\t\t\t" + "Balance" + "\t\t\t" + "Description" + "\n" );
                
                
               while(result.next()){
                    
                    System.out.println(result.getDate("date") + "\t\t" + result.getFloat("amount") + "\t\t\t" + result.getFloat("balance") + "\t\t\t" + result.getString("description") + "\n");
                }
                                
            } catch (SQLException e) {
               e.printStackTrace();
            }finally 
            {                                                             
               try                                                        
               {  
                  result.close();                                                       
                  statement.close();
                  connection.close();

               } // end try                                               
               catch ( Exception e )                              
               {                                                          
                  e.printStackTrace();                            
               } // end catch                                             
            } 
    	   
       }
       
       
       /**
        * Record income and its description
        */
       public static void recordIncome() {
           
            
            try {
                
                String description;
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                amount = UserInputController.inputDouble("Please enter income amount: ");
                if(amount<=0) {
                    System.out.println("Income amount must be greater than 0");
                    return;
                }
               
                description = UserInputController.inputString("Please enter description for the transaction: ");
                balance = balance + amount;
                int record = statement.executeUpdate("INSERT INTO transactions (date, description, amount, balance, user_id) "
                + "VALUES ('" + LocalDate.now() + "', '" + description + "', " + amount + ", " + balance + ", '" + SignIn.getCurrentUserId() + "')");
                System.out.println("The transaction has been added successfully");
                                
            } catch (SQLException e) {
               e.printStackTrace();
            }finally 
            {                                                             
               try                                                        
               {  
                  
                  statement.close();
                  connection.close();

               } // end try                                               
               catch ( Exception e )                              
               {                                                          
                  e.printStackTrace();                            
               } // end catch                                             
            } 
    	   
       }
	
       /**
        * Record expenditure and its description
        */
       public static void recordExpense() {
    	   
     
            try {
                String description;
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                amount = UserInputController.inputDouble("Please enter the expense amount: ");
                if(amount<=0 || amount > balance) {
                    System.out.println("expense amount must between 0～" + balance);
                    return;
                }
                
                description = UserInputController.inputString("Please enter a decription for the transaction: ");
                balance = balance - amount;
                amount = - amount;
                int record = statement.executeUpdate("INSERT INTO transactions (date, description, amount, balance, user_id) "
                        + "VALUES ('" + LocalDate.now() + "', '" + description + "', " + amount + ", " + balance + ", '" + SignIn.getCurrentUserId() + "')");
                System.out.println("The transaction has been added successfully");
            } catch (SQLException e) {
                e.printStackTrace();
            }finally 
            {                                                             
               try                                                        
               {                                                          
                  statement.close();
                  connection.close();

               } // end try                                               
               catch ( Exception e )                              
               {                                                          
                  e.printStackTrace();                            
               } // end catch                                             
            } 
             
       }
      
       /**
        * Ask user if they want to exit, accept y/n(non-case-sensitive) only
        * @return a string to check if user want to exit
        */
       public static String exit() {

    	   String choice;
    	   
    	   while(true) {
 
    		 choice = UserInputController.inputString("Are you sure you want to exit (y/n): ");
    		   if(choice.equalsIgnoreCase("y")|| choice.equalsIgnoreCase("n")) { break;}
    		   
    	   }
    	   
    	   return choice;
       }
       
       /**
        * Retrieves the current balance for the user.
        * @return The current balance as a double value
        * @throws SQLException If an SQL exception occurs while querying the database.
        */
       public static double getBalance(){
       
            ResultSet result = null;
            
            try {
                
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                result = statement.executeQuery("SELECT balance FROM transactions WHERE user_id = '" + SignIn.getCurrentUserId() + "' ORDER BY date DESC, id DESC LIMIT 1");

                if(result.next()){
                    
                    balance = result.getFloat("balance");
                }                           
                
            } catch (SQLException e) {
               e.printStackTrace();
            }finally 
            {                                                             
               try                                                        
               {  
                  result.close();
                  statement.close();  
                  connection.close();
               } // end try                                               
               catch ( Exception e )                              
               {                                                          
                  e.printStackTrace();                            
               } // end catch                                             
            } 
            
         return balance;
       }
}

