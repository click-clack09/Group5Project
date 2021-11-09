package application.model;
//kill
public abstract class LifeHub {
	
	private int userID;
	private int eventType; 
	private String name;

	//this will be a Hub (1 school, 3 personal, or 2 business)
	//it should a user, category/event Type, a name, an ArrayList of events, tasks, and notes.
	//for the calendar to show all of the users events, query for all of the users events.
	//This is independent of the hub currently instantiated.
	LifeHub()
	{
		listName ="";
	}
	
	LifeHub(String listName)
	{
		this.listName = listName;
	}
	/**
	 * @return the listName
	 */
	public String getListName() {
		return listName;
	}

	/**
	 * @param listName the listName to set
	 */
	public void setListName(String listName) {
		this.listName = listName;
	}
}
