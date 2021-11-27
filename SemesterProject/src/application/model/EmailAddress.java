package application.model;

/**The EmailAddress Class is used to create email addresses for the user's contacts
 * 
 * This class has an email address field represented as a String
 * This class has a type (of email) represented as a String
 */
public class EmailAddress
{
	private String address;
	private String type;
	
	
	/**This a parameterized constructor for the EmailAddress class
	 * 
	 * @param address -String that represents the email adress
	 * @param type - String that represents the email type is set to default
	 * @return none
	 */
	public EmailAddress(String address)
	{
		this.address = address;
		type = "default";
	}
	
	/**This a parameterized constructor for the EmailAddress class
	 * 
	 * @param address -String that represents the email adress
	 * @param type -String to be used as the email address type
	 * @return none
	 */
	public EmailAddress(String address, String type)
	{
		this.address = address;
		this.type = type;
	}

	/** This is a getter that retreives the email address as a string
	 * 
	 * @return address, String
	 */
	public String getAddress() {
		return address;
	}

	/** This a setter that sets the address
	 * 
	 * @param String address
	 * @return none
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/** This is a getter that retreives the EmailAddress type
	 * 
	 * @return type, String
	 */
	public String getType() {
		return type;
	}

	/** This a setter that sets the EmailAddress type
	 * 
	 * @param String type
	 * @return none
	 */
	public void setType(String type) {
		type = type;
	}

}
