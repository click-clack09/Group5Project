package application.model;

import java.util.ArrayList;

public class Event extends CalendarItem {	

	protected Date startDate;
	protected Date endDate;
	protected String location;
	
	protected ArrayList<Contact> attendees;
		
	/*
	 * CONSTRUCTORS
	 */
	public Event()
	{
		super();
	}
	
	public Event(String name) {
		super(name);
		
		startDate = new Date();
		
		endDate = new Date();
		location = "";
		
		attendees = new ArrayList<Contact>();
	}
	
	public Event(String name, Date startDate, Date endDate) {
		super(name);
		
		this.startDate = startDate;
		
		this.endDate = endDate;
		location = "";
		
		attendees = new ArrayList<Contact>();
	}
	
	/*
	 * HELPER METHODS
	 */
		
	//helper methods here
		
	/*
	 * GETTERS
	 */
		
	//getters here
		
	/*
	 * SETTERS
	 */
		
	//setters here
		
	/*
	 * OVERRIDES
	 */
		
	//overrides for abstract classes here
}
