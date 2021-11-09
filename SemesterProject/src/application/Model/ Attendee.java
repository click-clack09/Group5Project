package application.model;

public class Attendee {
	
	private Contact contact;
	private boolean RSVP;
	
	/*
	 * CONSTRUCTORS
	 */
	
	public Attendee(String name) {
		contact = new Contact(name);
		RSVP = false;
	}
	
	public Attendee(Contact contact) {
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