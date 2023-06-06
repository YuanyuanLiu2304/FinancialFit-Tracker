
package src;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The SignIn class handles user authentication and sign-in functionality.
 * This class interacts with the user database to retrieve user information and verify user credentials.
 * @author yuanyuanliu
 */
public class SignIn{
    
    /**
     * the database connection
     */
    private static Connection connection;
    
    /**
     * the statement used for executing SQL queries
     */
    private static Statement statement;
    
    /**
     * current user ID
     */
    private static int currentUserId;
 
    
    

   /**
   * @return the current user ID
   */
    public static int getCurrentUserId() {
        return currentUserId;
    }
    
    
    /**
     * Performs the sign-in operation
     */
    public static void signIn() {
        
            ResultSet result = null;
            
            System.out.println("Welcome to the sign in page");

            try{
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                String emailAddress=UserInputController.inputString("Please enter your email address: ");
                result = statement.executeQuery("SELECT * FROM users WHERE email = '" + emailAddress + "'");

                //check whether the user already exists 
                if(!result.next()) { 
                        System.out.println("User doesn't exist, please check your email address");		
                }else{

                    //check user password whether match with the account
                    String password=UserInputController.inputString("Please enter password: ");

                    String pwd = result.getString("password");

                    if(!password.equals(pwd)) {
                                System.out.println("password doesn't match, please check your password and try agin");	
                    }

                    else {
                     int id = result.getInt("id");
                    currentUserId = id;
                        //go to main menu after sign in
                        TransactionManager.menu();
                    }


                }  

            
            }catch(SQLException e){
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

}
