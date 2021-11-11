package application.model;

import java.net.URL;
import java.util.ArrayList;

public class User {
	//holds all data
	//userID
	//password
	//list of contacts
	private static URL lastHub;
	private static String userName;
	private static int userID;
	private static LifeHub currentHub;
	private static ArrayList<LifeHub> userHubs;
	
	//This is not SOLID compliant. Use index in ed class instead?
	private static String currentClass;
	
	private static ArrayList<Contact> userContacts;
	private static ArrayList<HubEvent> userEvents;
	private static ArrayList<SchoolClass> classes;
		
	public static Boolean validate(String userName, String password)
	{
		Boolean result =true;
		//Donny will do the query return result;
		return result;
	}

	/**
	 * @return the lastHub
	 */
	public static URL getLastHub() {
		return lastHub;
	}

	/**
	 * @return the userName
	 */
	public static String getUserName() {
		return userName;
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
	public static LifeHub getCurrentHub() {
		return currentHub;
	}

	/**
	 * @return the userHubs
	 */
	public static ArrayList<LifeHub> getUserHubs() {
		return userHubs;
	}

	/**
	 * @return the currentClass
	 */
	public static String getCurrentClass() {
		return currentClass;
	}

	/**
	 * @return the userContacts
	 */
	public static ArrayList<Contact> getUserContacts() {
		return userContacts;
	}

	/**
	 * @return the userEvents
	 */
	public static ArrayList<HubEvent> getUserEvents() {
		return userEvents;
	}

	/**
	 * @return the classes
	 */
	public static ArrayList<SchoolClass> getClasses() {
		return classes;
	}

	/**
	 * @param lastHub the lastHub to set
	 */
	public static void setLastHub(URL lastHub) {
		User.lastHub = lastHub;
	}

	/**
	 * @param userName the userName to set
	 */
	public static void setUserName(String userName) {
		User.userName = userName;
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
	public static void setCurrentHub(LifeHub currentHub) {
		User.currentHub = currentHub;
	}

	/**
	 * @param userHubs the userHubs to set
	 */
	public static void setUserHubs(ArrayList<LifeHub> userHubs) {
		User.userHubs = userHubs;
	}

	/**
	 * @param currentClass the currentClass to set
	 */
	public static void setCurrentClass(String currentClass) {
		User.currentClass = currentClass;
	}

	/**
	 * @param userContacts the userContacts to set
	 */
	public static void setUserContacts(ArrayList<Contact> userContacts) {
		User.userContacts = userContacts;
	}

	/**
	 * @param userEvents the userEvents to set
	 */
	public static void setUserEvents(ArrayList<HubEvent> userEvents) {
		User.userEvents = userEvents;
	}

	/**
	 * @param classes the classes to set
	 */
	public static void setClasses(ArrayList<SchoolClass> classes) {
		User.classes = classes;
	}

	
}
