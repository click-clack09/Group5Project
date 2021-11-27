package application.model;

/**This class is used for to store the user's Hubs and the Hub's type
 * This class has two fields: hubName and hubType
 */
public class UserHubRecord implements Comparable{

	private String hubName;
	private int hubType;
	
	/**This a parameterized constructor for the Task class
	 * 
	 * @param hubName - String that represents the details of the user's task
	 * @return hubType - int that represents school, business, or personal
	 */
	public UserHubRecord(String hubName, int hubType) {
		this.hubName = hubName;
		this.hubType = hubType;
	}


	/** This is a getter that Hub Name
	 * 
	 * @return hubName, String
	 */
	public String getHubName() {
		return hubName;
	}


	/** This is a getter that Hub Type
	 * 
	 * @return hubType, int
	 */
	public int getHubType() {
		return hubType;
	}


	/** This a setter that sets the Hub Name
	 * 
	 * @param String userName
	 * @return none
	 */
	public void setHubName(String userName) {
		this.hubName = userName;
	}


	/** This a setter that sets the Hub type
	 * 
	 * @param int hubType
	 * @return none
	 */
	public void setHubType(int hubType) {
		this.hubType = hubType;
	}

	
	/** This method is used to compare HubNames
	 * 
	 * @param int hubType
	 * @return -1 when HubNames do not match, return the hubType when HubNames match
	 */
	@Override
	public int compareTo(Object arg0) {
		if (arg0.equals(hubName))
			return hubType;
		return -1;
	}

	
}
