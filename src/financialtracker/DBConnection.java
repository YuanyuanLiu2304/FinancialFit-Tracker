
package src;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This class establishes a connection to the database using the specified properties file
 * It provides a static method to retrieve the database connection.
 * @author yuanyuanliu
 */

public class DBConnection {
    
    private static Properties props = new Properties();
    
    /**
     * prevent instantiation
     */
    private DBConnection(){}
    
    
    /**
     * Loads the database properties from the specified file
     * Called only once during the class initialization.
     */
    static {
        try {
            // Load the properties from a file
            InputStream inputStream = Files.newInputStream(Paths.get("src/Database/database.properties"));
            props.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * Retrieves a connection to the database.
     * @return Connection object representing the database connection
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        
        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");
        return DriverManager.getConnection(url, username, password);
    }
}
