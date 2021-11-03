package application.Model;

public abstract class LifeHubList {
	private String listName;

	LifeHubList()
	{
		listName ="";
	}
	
	LifeHubList(String listName)
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
