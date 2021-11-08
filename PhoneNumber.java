package application.model;

public class PhoneNumber
{
	private String number;
	private String type;
	
	/*
	 * CONSTRUCTORS
	 */
	
	public PhoneNumber(String number)
	{
		this.number = number;
		type = "default";
	}
	
	public PhoneNumber(String number, String type)
	{
		this.number = number;
		this.type = type;
	}

	/*
	 * GETTERS
	 */
	
	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/*
	 * SETTERS
	 */

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
}
