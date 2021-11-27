package application.model;

import java.util.ArrayList;

/**This Class is used to create contacts for the application's users
 * 
 * The Contact class contains the following members:
 * name - String to be use as the title
 * phoneList - String to be use as the header
 * emailsList - String to be use as the content
 * 
 */
public class Contact implements Comparable
{
	private String name;
	private ArrayList<PhoneNumber> phoneList;
	private ArrayList<EmailAddress> emailsList;
	
	/**This a parameterized constructor for the contact class
	 * 
	 * @param name - String to be used as the contact's name
	 * @param phoneNumber -String to be used as the contact's phoneNumber
	 * @param pType -String to be used as the contact's phone type
	 * @param email -String to be used as the contact's email
	 * @param eType -String to be used as the email type
	 * @return none
	 */
	public Contact(String name, String phoneNumber, String pType, String email, String eType)
	{
		this.name = name;
		phoneList=new ArrayList<PhoneNumber>();
		emailsList=new ArrayList<EmailAddress>();
		this.phoneList.add(new PhoneNumber(phoneNumber,pType));
		this.emailsList.add(new EmailAddress(email,eType));
	}
	
	/**This a parameterized constructor for the contact class
	 * 
	 * @param name - String to be used as the contact's name
	 * @param phoneList - represents an ArrayList of the user's phone contacts
	 * @param emailsList - represents an ArrayList of the user's email contacts
	 */
	public Contact(String name, ArrayList<PhoneNumber> phoneList, ArrayList<EmailAddress> emailsList)
	{
		this.name = name;
		this.phoneList = phoneList;
		this.emailsList = emailsList;
	}

	/** This is a getter that retreives the contact's name
	 * 
	 * @return name, String
	 */
	public String getName() {
		return name;
	}

	/** This a setter that sets the name
	 * 
	 * 
	 * @param String name
	 * @return none
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** This a getter that returns an ArrayList of PhoneNumber objects
	 * 
	 * @return the phoneList
	 */
	public ArrayList<PhoneNumber> getPhoneList() {
		return phoneList;
	}

	/** This a setter that sets an ArrayList of PhoneNumber objects
	 * 
	 * @param phoneList the phoneList to set
	 * @return none
	 */
	public void setPhoneList(ArrayList<PhoneNumber> phoneList) {
		this.phoneList = phoneList;
	}

	
	/** This a getter that returns an ArrayList of EmailAddress objects
	 * 
	 * 
	 * @return the emailsList
	 */
	public ArrayList<EmailAddress> getEmailsList() {
		return emailsList;
	}

	/** This a setter that sets an ArrayList of EmailAddress objects
	 * 
	 * @param ArrayList of EmailAddress objects
	 * @return none
	 */
	public void setEmailsList(ArrayList<EmailAddress> emailsList) {
		this.emailsList = emailsList;
	}
	
	/** This method returns a string of detailed information about the Contact object
	 * 
	 * @return this method returns a concatenated string of the emails and phone numbers
	 * as well as their respective types
	 */
	@Override
	public String toString() {
		String phoneString = "";
		String emailString = "";
		for (int i = 0; i < phoneList.size(); i++)
			phoneString += phoneList.get(i).getNumber()+" : "+phoneList.get(i).getType()+"\n";
		for (int i = 0; i < emailsList.size(); i++)
			emailString += emailsList.get(i).getAddress()+" : "+emailsList.get(i).getType()+"\n";
		return phoneString +"\n"+ emailString;
	}
	
	/** This method compares Contacts 
	 * 
	 * @return this method returns a 1 for a match or -1 for a non-match
	 * of Contacts
	 */
	@Override
	public int compareTo(Object o) {
		if (this.getName().equals(o))
			return 1;
		return -1;
	}
	
	
}
