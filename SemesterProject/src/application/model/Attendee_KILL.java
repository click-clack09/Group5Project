package application.model;

public class Attendee_KILL {
	//replace with arrayLIst of contacts, delete
	protected int id;
	protected UserNameRecord userNameRecord;
	private Contact contact;
	//trim if need be
	private boolean RSVP;
	
	/*
	 * CONSTRUCTORS
	 */
	
	public Attendee_KILL(String name) {
		contact = new Contact(name);
		RSVP = false;
	}
	
	public Attendee_KILL(Contact contact) {
		this.contact = contact;
		RSVP = false;
	}
		
	/*
	 * GETTERS
	 */
		
	public Contact getContact() {
		return contact;
	}
	
	public boolean getRSVP() {
		return RSVP;
	}
		
	/*
	 * SETTERS
	 */
	
	public void setContact(Contact contact)
	{
		this.contact = contact;
	}
	
	public void setRSVP(boolean RSVP) {
		this.RSVP = RSVP;
	}
		
	/*
	 * OVERRIDES
	 */
		
	@Override
	public String toString() {
		return contact.toString();
	}
}
