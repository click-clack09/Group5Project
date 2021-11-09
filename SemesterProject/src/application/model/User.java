package application.model;

import java.net.URL;

public class User {
	//holds all data
	//userID
	//password
	//list of contacts
	private static URL last;
	private static String username;
	private static int userID;
	/**
	 * @return the last
	 */
	public static URL getLast() {
		return last;
	}
	/**
	 * @param last the last to set
	 */
	public static void setLast(URL last) {
		User.last = last;
	}
	/**
	 * @return the username
	 */
	public static String getUsername() {
		return username;
	}
	
	/**
	 * @param username the username to set
	 */
	public static void setUserName(String username) {
		User.username = username;
	}
	
	public static Boolean validate(String userName, String password)
	{
		Boolean result =true;
		//Donny will do the query return result;
		return result;
	}
}
