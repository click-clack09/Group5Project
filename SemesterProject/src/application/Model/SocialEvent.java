package application.Model;

public class SocialEvent extends Event
{
	private String location;
	private Date eventDate;
	private Boolean hasRSVP;
	private Boolean hasAttendees;
	private ArrayList<RSVP> rsvpList;
	private ArrayList<Attendess> attendeeList;
	
	public SocialEvent()
	{
		
	}
	
	public SocialEvent(String location, Date eventDate, Boolean hasRSVP, Boolean hasAtendees,
			ArrayList<RSVP> resvpList, ArrayList<Attendees> attenedeeList)
	{
		this.location = location;
		this.eventDate = eventDate;
		this.hasRSVP = hasRSVP;
		this.hasAttendees = hasAttendees;
		this.rsvpList = rsvpList;
		this.attendeeList = attenedeeList;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the eventDate
	 */
	public Date getEventDate() {
		return eventDate;
	}

	/**
	 * @param eventDate the eventDate to set
	 */
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	/**
	 * @return the hasRSVP
	 */
	public Boolean getHasRSVP() {
		return hasRSVP;
	}

	/**
	 * @param hasRSVP the hasRSVP to set
	 */
	public void setHasRSVP(Boolean hasRSVP) {
		this.hasRSVP = hasRSVP;
	}

	/**
	 * @return the hasAttendees
	 */
	public Boolean getHasAttendees() {
		return hasAttendees;
	}

	/**
	 * @param hasAttendees the hasAttendees to set
	 */
	public void setHasAttendees(Boolean hasAttendees) {
		this.hasAttendees = hasAttendees;
	}

	/**
	 * @return the rsvpList
	 */
	public ArrayList<RSVP> getRsvpList() {
		return rsvpList;
	}

	/**
	 * @param rsvpList the rsvpList to set
	 */
	public void setRsvpList(ArrayList<RSVP> rsvpList) {
		this.rsvpList = rsvpList;
	}

	/**
	 * @return the attendeeList
	 */
	public ArrayList<Attendess> getAttendeeList() {
		return attendeeList;
	}

	/**
	 * @param attendeeList the attendeeList to set
	 */
	public void setAttendeeList(ArrayList<Attendess> attendeeList) {
		this.attendeeList = attendeeList;
	}
	
	
}
