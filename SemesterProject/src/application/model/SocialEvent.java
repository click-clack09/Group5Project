package application.model;

import java.util.ArrayList;

public class SocialEvent extends LifeEvent
{
	private String location;
	private Date eventDate;
	private Boolean hasRSVP;
	private Boolean hasAttendees;
	//private ArrayList<Rsvp> rsvpList;
	private ArrayList<Attendee> attendeeList;
	
	public SocialEvent()
	{
		
	}
	
	public SocialEvent(String location, Date eventDate, Boolean hasRSVP, Boolean hasAtendees,
			/*ArrayList<Rsvp> resvpList,*/ ArrayList<Attendee> attenedeeList)
	{
		this.location = location;
		this.eventDate = eventDate;
		this.hasRSVP = hasRSVP;
		this.hasAttendees = hasAttendees;
		//this.rsvpList = rsvpList;
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
//	public ArrayList<Rsvp> getRsvpList() {
//		return rsvpList;
//	}
//
//	/**
//	 * @param rsvpList the rsvpList to set
//	 */
//	public void setRsvpList(ArrayList<Rsvp> rsvpList) {
//		this.rsvpList = rsvpList;
//	}

	/**
	 * @return the attendeeList
	 */
	public ArrayList<Attendee> getAttendeeList() {
		return attendeeList;
	}

	/**
	 * @param attendeeList the attendeeList to set
	 */
	public void setAttendeeList(ArrayList<Attendee> attendeeList) {
		this.attendeeList = attendeeList;
	}
	
	
}
