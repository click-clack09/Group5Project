package application.model;

import java.util.ArrayList;

public class Contact
{
	private int userID;
	private String name;
	private ArrayList<PhoneNumber> phoneList;
	private ArrayList<EmailAddress> emailsList;
	
	public Contact(String name)
	{
		this.name = name;
	}
	
	public Contact(String name, ArrayList<PhoneNumber> phoneList, ArrayList<EmailAddress> emailsList)
	{
		this.name = name;
		this.phoneList = phoneList;
		this.emailsList = emailsList;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the phoneList
	 */
	public ArrayList<PhoneNumber> getPhoneList() {
		return phoneList;
	}

	/**
	 * @param phoneList the phoneList to set
	 */
	public void setPhoneList(ArrayList<PhoneNumber> phoneList) {
		this.phoneList = phoneList;
	}

	/**
	 * @return the emailsList
	 */
	public ArrayList<EmailAddress> getEmailsList() {
		return emailsList;
	}

	/**
	 * @param emailsList the emailsList to set
	 */
	public void setEmailsList(ArrayList<EmailAddress> emailsList) {
		this.emailsList = emailsList;
	}
}
