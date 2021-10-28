package application.Model;

public class Rsvp extends Attendee
{
	private Boolean willAttend;
	
	public Rsvp()
	{
		super();
		this.willAttend=true;
	}
	
	public Rsvp(String name, Boolean willAttend)
	{
		super(name);
		this.willAttend=willAttend;
	}

	/**
	 * @return the willAttend
	 */
	public Boolean getWillAttend() {
		return willAttend;
	}

	/**
	 * @param willAttend the willAttend to set
	 */
	public void setWillAttend(Boolean willAttend) {
		this.willAttend = willAttend;
	}
	
	

}
