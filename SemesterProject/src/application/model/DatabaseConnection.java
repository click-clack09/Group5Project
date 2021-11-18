package application.model;


import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "31415!Steel180";
	private static final String DATABASE = "lifehub";

	public DatabaseConnection() {

	}
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
