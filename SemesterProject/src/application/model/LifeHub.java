package application.model;
//kill
public class LifeHub {
	private String hubName;

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
	/**
	 * @return the listName
	 */
	public String getListName() {
		return hubName;
	}

	/**
	 * @param listName the listName to set
	 */
	public void setListName(String listName) {
		this.hubName = listName;
	}
}
