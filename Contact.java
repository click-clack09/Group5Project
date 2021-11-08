package application.model;

import java.util.ArrayList;

public class Contact
{
	private String name;
	private ArrayList<Phone> phoneList;
	private ArrayList<Email> emailsList;
	
	public Contact(String name)
	{
		this.name = name;
	}
	
	public Contact(String name, ArrayList<Phone> phoneList, ArrayList<Email> emailsList)
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
	public ArrayList<Phone> getPhoneList() {
		return phoneList;
	}

	/**
	 * @param phoneList the phoneList to set
	 */
	public void setPhoneList(ArrayList<Phone> phoneList) {
		this.phoneList = phoneList;
	}

	/**
	 * @return the emailsList
	 */
	public ArrayList<Email> getEmailsList() {
		return emailsList;
	}

	/**
	 * @param emailsList the emailsList to set
	 */
	public void setEmailsList(ArrayList<Email> emailsList) {
		this.emailsList = emailsList;
	}
}