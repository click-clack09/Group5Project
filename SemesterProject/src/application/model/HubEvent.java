package application.model;

import java.util.ArrayList;

/**This Class is used to create the application's Hub Events
 * This class has the following fields:
 * eventID - int, a 1-up serialization field that is assigned when Hub Events are created
 * userID - int, represents the owner of the Hub Event
 * evetyType - int representing school, personal, or business categories
 * startDate - Date object representing the start date of the Hub Event
 * hubName - String representing the HubName
 * location - String representing the Hub Event location
 * attendees - ArrayList of Contact objects representing the attendees of the event
 * eventName - String representing the event name
 * endDate - Date object representing the end date of the Hub Event
 */
public class HubEvent {	

	private int eventID;
	private int userID;
	private int eventType; 
	private Date startDate;
	private String hubName;
	private String location;
	private ArrayList<Contact> attendees;
	private String eventName;
	private Date endDate;
		
	/**This a non-parameterized constructor for testing the HubEvent
	 * 
	 */
	public HubEvent()
	{
		this.eventID = -1;
		this.userID = -1;
		this.eventType = -1;
		this.startDate = new Date();
		this.hubName = "";
		this.location = "";
		this.attendees = new ArrayList<Contact>();
		this.eventName = "";
	    this.endDate = endDate;
	}
	
	/**This a non-parameterized constructor for testing the HubEvent
	 * 
	 */
	public HubEvent(String name) {
		this.eventID = -1;
		this.userID = -1;
		this.eventType = -1;
		this.startDate = new Date();
		this.hubName = "";
		this.location = "";
		this.attendees = new ArrayList<Contact>();
		this.eventName = name;
	}
	
	/**This a parameterized constructor for the HubEvent class
	 * 
	 * @param eventID - int, a 1-up serialization field that is assigned when Hub Events are created
	 * @param userID - int, represents the owner of the Hub Event
	 * @param  evetyType - int representing school, personal, or business categories
	 * @param startDate - Date object representing the start date of the Hub Event
	 * @param hubName - String representing the HubName
	 * @param location - String representing the Hub Event location
	 * @param attendees - ArrayList of Contact objects representing the attendees of the event
	 * @param eventName - String representing the event name
	 * @param endDate - Date object representing the end date of the Hub Event
	 * @return none
	 */
	public HubEvent(int eventID, int userID, int eventType, Date startDate, String hubName, String location,
			ArrayList<Contact> attendees, String eventName, Date endDate) {

		this.eventID = eventID;
		this.userID = userID;
		this.eventType = eventType;
		this.startDate = startDate;
		this.hubName = hubName;
		this.location = location;
		this.attendees = attendees;
		this.eventName = eventName;
		this.endDate = endDate;
	}

	/**This a getter that returns the eventID
	 * 
	 * @return int, eventID
	 */
	public int getEventID() {
		return eventID;
	}

	/**This a getter that returns the userID of the person who owns the event
	 * 
	 * @return int userID
	 */
	public int getUserID() {
		return userID;
	}

	/**This a getter that returns the event type (school, personal, or business)
	 * 
	 * @return int eventType
	 */
	public int getEventType() {
		return eventType;
	}

	/** This a getter that returns a Date object representing the start of the event
	 * 
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/** This a getter that returns the Hub Name
	 * 
	 * @return the hubName
	 */
	public String getHubName() {
		return hubName;
	}

	/** This a getter that returns the location of the event as a String
	 * 
	 * @return String, location
	 */
	public String getLocation() {
		return location;
	}

	/** This a getter that returns an ArrayList of Contact objects as attendees
	 * 
	 * @return ArrayList of Contact objects
	 */
	public ArrayList<Contact> getAttendees() {
		return attendees;
	}

	/** This a getter that returns the event name
	 * 
	 * @return String eventName
	 */
	public String getEventName() {
		return eventName;
	}

	/** This a getter that returns a Date object represent the end of the event
	 * 
	 * @return Date endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/** This a setter that sets the eventID 
	 * 
	 * @param int eventID
	 * @return none
	 */
	public void setEventID(int eventID) {
		this.eventID = eventID;
	}

	/** This a setter that sets the userID 
	 * 
	 * @param int userID
	 * @return none
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/** This a setter that sets the userID 
	 * 
	 * @param int eventType
	 * @return none
	 */
	public void setEventType(int eventType) {
		this.eventType = eventType;
	}

	/** This a setter that sets the startDate
	 * 
	 * 
	 * @param Date startDate 
	 * @return none
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/** This a setter that sets the Hub Name
	 * 
	 * 
	 * @param String hubName
	 * @return none
	 */
	public void setHubName(String hubName) {
		this.hubName = hubName;
	}

	/** This a setter that sets the location
	 *
	 *
	 * @param String location
	 * @return none
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/** This a setter that sets attendees
	 * 
	 * 
	 * @param ArrayList of Contact objects
	 * @return none
	 */
	public void setAttendees(ArrayList<Contact> attendees) {
		this.attendees = attendees;
	}

	/** This a setter that sets the Event Name
	 * 
	 * 
	 * @param String eventName
	 * @return none
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	/** This a setter that sets the endDate
	 * 
	 * 
	 * @param Date endDate
	 * @return none
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/** This method prints the HubEvent details as a string
	 * 
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return "HubEvent [eventID=" + eventID + ", userID=" + userID + ", eventType=" + eventType + ", startDate="
				+ startDate + ", hubName=" + hubName + ", location=" + location + ", attendees=" + attendees
				+ ", eventName=" + eventName + ", endDate=" + endDate + "]";
	}
	
}
