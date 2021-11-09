package application.model;

import java.util.ArrayList;

public class HubEvent {	

	//add database metadata
	private int eventID;
	private int userID;
	private int eventType; //1,2,3 business, personal, education, verify this with Donny prior to use
	private Date startDate;
	//this will need to be referenced with a LifeHub name, but should that come from the sending method?
	private String location;
	private ArrayList<Contact> attendees;
	private String eventName;
	private Date endDate;//kill this later, leave for now in case we end up using it
		
	/*
	 * CONSTRUCTORS
	 */
	public HubEvent()
	{
		this.eventID = -1;
		this.userID = -1;
		this.eventType = -1;
		this.startDate = new Date();
		this.location = "";
		this.attendees = new ArrayList<Contact>();
		this.eventName = "";
		//this.endDate = endDate;
	}
	
	public HubEvent(String name) {
		startDate = new Date();
		endDate = new Date();
		location = "";
		attendees = new ArrayList<Contact>();
	}
	
	public HubEvent(int eventID, int userID, int eventType, Date startDate, String location,
			ArrayList<Contact> attendees, String eventName, Date endDate) {
		this.eventID = eventID;
		this.userID = userID;
		this.eventType = eventType;
		this.startDate = startDate;
		this.location = location;
		this.attendees = attendees;
		this.eventName = eventName;
		//this.endDate = endDate;
	}

	public HubEvent(String name, Date startDate, Date endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
		location = "";
		attendees = new ArrayList<Contact>();
	}
	
	/*
	 * HELPER METHODS
	 */
		
	//helper methods here
		

	/**
	 * @return the eventID
	 */
	public int getEventID() {
		return eventID;
	}

	/**
	 * @return the userID
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * @return the eventType
	 */
	public int getEventType() {
		return eventType;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @return the attendees
	 */
	public ArrayList<Contact> getAttendees() {
		return attendees;
	}

	/**
	 * @return the eventName
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * @param eventID the eventID to set
	 */
	public void setEventID(int eventID) {
		this.eventID = eventID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * @param eventType the eventType to set
	 */
	public void setEventType(int eventType) {
		this.eventType = eventType;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @param attendees the attendees to set
	 */
	public void setAttendees(ArrayList<Contact> attendees) {
		this.attendees = attendees;
	}

	/**
	 * @param eventName the eventName to set
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	//default for now, probably not what will be used
	@Override
	public String toString() {
		return "HubEvent [eventID=" + eventID + ", userID=" + userID + ", eventType=" + eventType + ", startDate="
				+ startDate + ", location=" + location + ", attendees=" + attendees + ", eventName=" + eventName
				+ ", endDate=" + endDate + "]";
	}
	
		
	/*
	 * OVERRIDES
	 */
		
	//overrides for abstract classes here
	
}
