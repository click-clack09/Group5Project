package application.model;
//kill
public class LifeHubNumberedList_KILL extends LifeHub{
	private String data;
	private int numOnList;
	public LifeHubNumberedList_KILL(String listName, String data, int numOnList) {
		super(listName);
		//this.listName = listName;
		this.data = data;
		this.numOnList = numOnList;
	}
	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}
	/**
	 * @return the numOnList
	 */
	public int getNumOnList() {
		return numOnList;
	}
	/**
	 * @param numOnList the numOnList to set
	 */
	public void setNumOnList(int numOnList) {
		this.numOnList = numOnList;
	}



}
