package application.Model;

import java.net.URL;

public class User {
	
	private static URL last;
	private static String userName;
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
	 * @return the userName
	 */
	public static String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public static void setUserName(String userName) {
		User.userName = userName;
	}
	
	
	
}
