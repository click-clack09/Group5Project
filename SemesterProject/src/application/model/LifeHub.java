package application.model;

import java.net.URL;
import java.util.ArrayList;

//kill
public class LifeHub implements Comparable {
	private String hubName;
	private int eventType;
	private ArrayList<HubEvent> events;
	private ArrayList<Task> tasks;
	private ArrayList<Note> notes;
	
	
	//user class has the following:
	//private static URL last;
//	private static String username;
//	private static int userID;
//	private static String currentHub;
		

	//this will be a Hub (1 school, 3 personal, or 2 business)
	//it should a user, category/event Type, a name, an ArrayList of events, tasks, and notes.
	//for the calendar to show all of the users events, query for all of the users events.
	//This is independent of the hub currently instantiated.
	public LifeHub()
	{
		hubName ="";
	}
	
	public LifeHub(String listName)
	{
		this.hubName = listName;
	}

	public LifeHub(String hubName, int eventType, ArrayList<HubEvent> events, ArrayList<Task> tasks,
			ArrayList<Note> notes) {
		super();
		this.hubName = hubName;
		this.eventType = eventType;
		this.events = events;
		this.tasks = tasks;
		this.notes = notes;
	}

	/**
	 * @return the hubName
	 */
	public String getHubName() {
		return hubName;
	}

	/**
	 * @return the eventType
	 */
	public int getEventType() {
		return eventType;
	}

	/**
	 * @return the events
	 */
	public ArrayList<HubEvent> getEvents() {
		return events;
	}

	/**
	 * @return the tasks
	 */
	public ArrayList<Task> getTasks() {
		return tasks;
	}

	/**
	 * @return the notes
	 */
	public ArrayList<Note> getNotes() {
		return notes;
	}

	/**
	 * @param hubName the hubName to set
	 */
	public void setHubName(String hubName) {
		this.hubName = hubName;
	}

	/**
	 * @param eventType the eventType to set
	 */
	public void setEventType(int eventType) {
		this.eventType = eventType;
	}

	/**
	 * @param events the events to set
	 */
	public void setEvents(ArrayList<HubEvent> events) {
		this.events = events;
	}

	/**
	 * @param tasks the tasks to set
	 */
	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(ArrayList<Note> notes) {
		this.notes = notes;
	}

	@Override
	public int compareTo(Object o) {
		
		if (this.equals(o))
			return 1;// TODO Auto-generated method stub
		return -1;
	}
	
	public int equals(LifeHub hub)
	{
		System.out.println("Equals for lifehub");
		if (hub.getHubName().equals(this.getHubName()))
			return 1;
		return -1;
	}
	
	
	
}
