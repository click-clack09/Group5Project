package application.model;


import java.sql.Connection;
import java.sql.DriverManager;

/**This Class is used as a generic database connection to the MySQL
 * Community Server
 * 
 * This class contains a URL field, username, password, and database field.
 * These fields serve as the credentials for setting up the 
 * application's database connection.
 */
public class DatabaseConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "yanula20";
	private static final String DATABASE = "lifehub";

	/**This Class is non-parameterizd constructor used to instantiate the 
	 * Database Connection class
	 * 
	 */
	public DatabaseConnection() {

	}
	
	/**This is a static method is used to create the connection to the application's
	 * database
	 * 
	 * @return a Connection object
	 */
	public static Connection getConnection() {
    	Connection connection;
    	try {
    		connection = DriverManager.getConnection(URL+DATABASE,USERNAME,PASSWORD);
    		return connection;
    	}catch(Exception e){
    		e.printStackTrace();
    		System.out.println("Error: "+e.getMessage());
    		return null;
    	}    	
    }
}
