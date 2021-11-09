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
	private static String currentHub;
		
	public static Boolean validate(String userName, String password)
	{
		Boolean result =true;
		//Donny will do the query return result;
		return result;
	}

	/**
	 * @return the last
	 */
	public static URL getLast() {
		return last;
	}

	/**
	 * @return the username
	 */
	public static String getUsername() {
		return username;
	}

	/**
	 * @return the userID
	 */
	public static int getUserID() {
		return userID;
	}

	/**
	 * @return the currentHub
	 */
	public static String getCurrentHub() {
		return currentHub;
	}

	/**
	 * @param last the last to set
	 */
	public static void setLast(URL last) {
		User.last = last;
	}

	/**
	 * @param username the username to set
	 */
	public static void setUsername(String username) {
		User.username = username;
	}

	/**
	 * @param userID the userID to set
	 */
	public static void setUserID(int userID) {
		User.userID = userID;
	}

	/**
	 * @param currentHub the currentHub to set
	 */
	public static void setCurrentHub(String currentHub) {
		User.currentHub = currentHub;
	}

	
	
	
}
