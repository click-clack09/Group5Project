package application.model;

public class EmailAddress
{
	private int userID;
	private String address;
	private String type;
	
	public EmailAddress(String address)
	{
		this.address = address;
		type = "default";
	}
	
	public EmailAddress(String address, String type)
	{
		this.address = address;
		this.type = type;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		type = type;
	}

}
