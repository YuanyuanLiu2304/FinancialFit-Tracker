
package src;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;


/**
 * The ResetPassword class provides functionality for resetting user passwords.
 * It includes methods for verifying user identity, generating and sending password reset verification code, and updating the user's password.
 * This class interacts with the user database to retrieve and update user information.
 * @author yuanyuanliu
 */
public class ResetPassword {
    
       /**
         * the database connection
         */
    private static Connection connection;
   
    
 
/**
 *  Allows users to reset their password
 * @param accounts the list of registered accounts
 */
public static void resetPassword() {
    
	    Statement statement = null;
            ResultSet result = null;
            
            System.out.println("Welcome to the reset password page");
            
        try {
           
             connection = DBConnection.getConnection();
             statement = connection.createStatement();
                
                String emailAddress=UserInputController.inputString("Please enter email: ");
                result = statement.executeQuery("SELECT * FROM users WHERE email = '" + emailAddress + "'");
                if (!result.next()) {
                    System.out.println("We could not find an account associated with this email address");
                } else {
                    int verificationCode = new Random().nextInt(900000) + 100000;
                    System.out.println("verificationCode " + verificationCode);
                    int inputCode = UserInputController.inputInteger("Please enter the six-digit verification code sent to your email: ");
                    
                    if(verificationCode!=inputCode) {
                        System.out.println("Incorrect verification code");
                    }else {       
                        
                        String newPwd=UserInputController.inputString("Please enter the new password: ");
                        String pwd = result.getString("password");
                        if(newPwd.matches(pwd)|| !newPwd.matches("^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z]).{8,}$")) {
                            System.out.println("Passwords should be at least 8 characters long, include numbers, one upper letter, one lower letter, and should not be the same as any previously used passwords");
                        }else{
                            
                            statement.executeUpdate("UPDATE users SET password = '" + newPwd + "' WHERE email = '" + emailAddress + "'");
                            
                            System.out.println("The password has been changed successfully");
                            SignIn.signIn();
                            
                        }
                                                                       
                    }                                
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
	
}