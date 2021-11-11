package application.model;

public class UserNameRecord implements Comparable{

	private String userName;
	private int hubType;
	
	
	public UserNameRecord(String userName, int hubType) {
		this.userName = userName;
		this.hubType = hubType;
	}


	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}


	/**
	 * @return the hubType
	 */
	public int getHubType() {
		return hubType;
	}


	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}


	/**
	 * @param hubType the hubType to set
	 */
	public void setHubType(int hubType) {
		this.hubType = hubType;
	}


	@Override
	public int compareTo(Object arg0) {
		if (arg0.equals(userName))
			return hubType;
		return -1;
	}

	
}
