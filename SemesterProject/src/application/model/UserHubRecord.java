package application.model;

/**This class is used for to store the user's Hubs and the Hub's type
 * 
 * @author rlk538
 *
 */

public class UserHubRecord implements Comparable{

	private String hubName;
	private int hubType;
	
	
	public UserHubRecord(String hubName, int hubType) {
		this.hubName = hubName;
		this.hubType = hubType;
	}


	/**
	 * @return the hubName
	 */
	public String getHubName() {
		return hubName;
	}


	/**
	 * @return the hubType
	 */
	public int getHubType() {
		return hubType;
	}


	/**
	 * @param hubName the userName to set
	 */
	public void setHubName(String userName) {
		this.hubName = userName;
	}


	/**
	 * @param hubType the hubType to set
	 */
	public void setHubType(int hubType) {
		this.hubType = hubType;
	}


	@Override
	public int compareTo(Object arg0) {
		if (arg0.equals(hubName))
			return hubType;
		return -1;
	}

	
}
