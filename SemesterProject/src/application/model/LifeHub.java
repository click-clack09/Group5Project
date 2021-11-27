package application.model;

import java.net.URL;
import java.util.ArrayList;

/**This Class is used to create LifeHub's for the logged-in user
 * 
 * 
 * LifeHub's have a name, event type, and ArrayLists that hold
 * LifeHub events, tasks, notes, or classes
 *
 */
public class LifeHub implements Comparable {
	private String hubName;
	private int eventType;
	private ArrayList<HubEvent> events; 
	private ArrayList<Task> tasks;
	private ArrayList<Note> notes;
	private ArrayList<SchoolClass> classes;
	

	/**This a non-parameterized constructor used for testing the LifeHub class
	 * 
	 * @param listName - String to be used as the contact's name
	 * 
	 *  This constructor assigns a name to the LifeHub and initializes 3 ArrayLists
	 *  that will hold tasks, notes, and classes.
	 */
	public LifeHub()
	{
		hubName ="";
	}
	
	/**This a parameterized constructor for the LifeHub Class
	 * 
	 * @param listName - String to be used as the contact's name
	 * 
	 *  This constructor assigns a name to the LifeHub and initializes 3 ArrayLists
	 *  that will hold tasks, notes, and classes.
	 */
	public LifeHub(String listName)
	{
		this.hubName = listName;
		tasks= new ArrayList<Task>();
		notes= new ArrayList<Note>();
		classes= new ArrayList<SchoolClass>();
	}

	
	/**This a parameterized constructor for the contact class
	 * 
	 * @param hubName - String represents the Hub Name
	 * @param eventType - int represents a type of event
	 * @param events - represents an ArrayList of HubEvent objects
	 * @param tasks - represents an ArrayList of Task objects
	 * @param notes - represents an ArrayList of Note objects
	 * @param classes - represents an ArrayList SchoolClass objects
	 */
	public LifeHub(String hubName, int eventType, ArrayList<HubEvent> events, ArrayList<Task> tasks,
			ArrayList<Note> notes) {
		super();
		this.hubName = hubName;
		this.eventType = eventType;
		this.events = events;
		this.tasks = tasks;
		this.notes = notes;
		this.classes = new ArrayList<SchoolClass>();
		
	}

	/**This a getter that retrieves the LifeHub name
	 * 
	 * @return String
	 */
	public String getHubName() {
		return hubName;
	}

	/**This a getter that retrieves the LifeHub event type
	 * 
	 * @return int
	 */
	public int getEventType() {
		return eventType;
	}

	/**This a getter that retrieves an array list of Hub Events
	 * 
	 * @return ArrayList
	 */
	public ArrayList<HubEvent> getEvents() {
		return events;
	}

	/**This a getter that retrieves an array list of Task objects
	 * 
	 * @return ArrayList
	 */
	public ArrayList<Task> getTasks() {
		return tasks;
	}

	/**This a getter that retrieves an array list of Note objects
	 * 
	 * @return ArrayList
	 */
	public ArrayList<Note> getNotes() {
		return notes;
	}

	/**This a setter that sets an array Life Hub name
	 * 
	 * @return none
	 */
	public void setHubName(String hubName) {
		this.hubName = hubName;
	}

	/**This a setter that sets an Life Hub event type
	 * 
	 * @return none
	 */
	public void setEventType(int eventType) {
		this.eventType = eventType;
	}

	/**This a setter that sets an Life Hub's ArrayList of events
	 * 
	 * @return none
	 */
	public void setEvents(ArrayList<HubEvent> events) {
		this.events = events;
	}

	/**This a setter that sets an Life Hub's ArrayList of tasks
	 * 
	 * @return none
	 */
	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}

	/**This a setter that sets an ArrayList of notes
	 * 
	 * @return none
	 */
	public void setNotes(ArrayList<Note> notes) {
		this.notes = notes;
	}

	/**This method is used to compare LifeHub objects
	 * 
	 * @return 1 for match, - 1 for non-match
	 */
	@Override
	public int compareTo(Object o) {
		
		if (this.equals(o))
			return 1;
		return -1;
	}
	
	/**This method is used to compare Hub Name strings
	 * 
	 * @return the event type for match, - 1 for non-match
	 */
	public int compareTo(String str) {
		
		if (this.hubName.equals(str))
			return this.eventType;
		return -1;
	}
	
	/**This a getter that retrieves an array list of SchoolClass objects
	 * 
	 * @return ArrayList
	 */
	public ArrayList<SchoolClass> getClasses() {
		return classes;
	}

	/**This a getter that retrieves an array list of SchoolClass objects
	 * 
	 * @return ArrayList
	 */
	public void setClasses(ArrayList<SchoolClass> classes) {
		this.classes = classes;
	}

	/**This method is used to compare Life Hub objects
	 * 
	 * @return 1 for a match, -1 for a non-match
	 */
	public int equals(LifeHub hub)
	{
		System.out.println("Equals for lifehub");
		if (hub.getHubName().equals(this.getHubName()))
			return 1;
		return -1;
	}
}
