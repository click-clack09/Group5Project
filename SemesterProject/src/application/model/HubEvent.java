package application.model;

import java.util.ArrayList;

public class HubEvent {	

	//add database metadata
	protected Date startDate;
	protected Date endDate;//kill this
	protected String location;
	
	protected ArrayList<Contact> attendees;
		
	/*
	 * CONSTRUCTORS
	 */
	public HubEvent()
	{
		super();
	}
	
	public HubEvent(String name) {
		//super(name);
		
		startDate = new Date();
		
		endDate = new Date();
		location = "";
		
		attendees = new ArrayList<Contact>();
	}
	
	public HubEvent(String name, Date startDate, Date endDate) {
		//super(name);
		
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
