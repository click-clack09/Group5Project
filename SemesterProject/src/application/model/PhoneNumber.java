package application.model;

/**The PhoneNumber Class is used to create phone numbers for the user's contacts
 * 
 * This class has a number field represented as a String
 * This class has a type (of phone number) represented as a String
 */
public class PhoneNumber
{
	private String number;
	private String type;

	
	/**This a parameterized constructor for the PhoneNumber class
	 * 
	 * @param number -String that represents the email adress
	 * @param type - String that represents the email type is set to default
	 * @return none
	 */
	public PhoneNumber(String number)
	{
		this.number = number;
		type = "default";
	}
	
	/**This a parameterized constructor for the PhoneNumber class
	 * 
	 * @param number - String that represents the phone number
	 * @param type - String to be used as the phone number type
	 * @return none
	 */
	public PhoneNumber(String number, String type)
	{
		this.number = number;
		this.type = type;
	}

	/** This is a getter that retreives phone number as a string
	 * 
	 * @return number, String
	 */
	public String getNumber() {
		return number;
	}
	
	/** This is a getter that retreives the email address type as a string
	 * 
	 * @return type, String
	 */
	public String getType() {
		return type;
	}
	

	/** This a setter that sets the phone number
	 * 
	 * @param String number
	 * @return none
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/** This a setter that sets phone number type
	 * 
	 * @param String type
	 * @return none
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
}
