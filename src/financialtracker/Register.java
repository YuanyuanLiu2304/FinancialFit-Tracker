
package src;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;


/**
 * This class represents a registration process for a user.
 * Provides methods to handle user registration and account creation,
 * It interacts with the database to store user information
 * @author yuanyuanliu
 */
public class Register {
    
  /**
   * the database connection
   */
  private static Connection connection;
 

    /**
    * Allows users to register a new account
    */
    public static void register() {
        
            Statement statement = null;           
            ResultSet result = null;      
            String email;
            
            System.out.println("Welcome to the register page");
            email=UserInputController.inputString("Please enter your email: ");
            
        if(!email.matches("\\S+@\\S+\\.\\S+")){
            
                    System.out.println("Email format incorrect, please check it and try again");

            }else{

            //check whether the user already exists        
            try {
                
            connection = DBConnection.getConnection();
            statement = connection.createStatement();         
            // Execute the SQL statement
            result = statement.executeQuery("SELECT * FROM users WHERE email = '" + email + "'");

            // Check if the user already exists
            if (result.next()) {
                System.out.println("User already exists, please back to the menu and log in");

            } else {

                    //validate user name, should contain at least one digit, one letter and no underscores
                    String username=UserInputController.inputString("Please enter your username: ");
                    if(!(username.matches("^(?=.*[0-9])(?=.*[a-zA-Z]).{4,15}$"))) {
                            System.out.println("Username should contain at least one letter, and length between 4 and 15 ");
                            return;
                    }

                    //validate password
                    String password=UserInputController.inputString("Please enter user password: ");
                    if(password == "" || !password.matches("^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z]).{8,}$")) {
                         System.out.println("Passwords should be at least 8 characters long, include numbers, at least one upper letter, one lower letter");
                         return;
                    }

                    String confirmPassword=UserInputController.inputString("Please enter your password again: ");
                    if(password.equals(confirmPassword)) {

                    statement.executeUpdate("INSERT INTO users (username,email,password) VALUES ('" + username + "', '" + email + "', '" + password + "')");
                    System.out.println("User registered successfully!");
                    SignIn.signIn();

                    }else{
                        System.out.println("Password didn't match, please check you password");
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
                  
               }catch ( Exception e ) {                                                          
                  e.printStackTrace();                            
               } // end catch                                             
            } 

        }

    }
}
