package application.Model;

public class Attendee {
	private String name;
	
	public Attendee()
	{
		this.name="";
	}
	
	public Attendee(String name)
	{
		this.name=name;
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
	
}
