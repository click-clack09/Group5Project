package application.Model;

public abstract class CalendarItem {
	
	protected String name;
	protected String description;
	
	/*
	 * CONSTRUCTORS
	 */
	
	public CalendarItem(String name) {
		this.name = name;
		description = "";
	}
	
	public CalendarItem(String name, String description) {
		this.name = name;
		this.description = description;
	}	
		
	/*
	 * GETTERS
	 */
		
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	/*
	 * SETTERS
	 */
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}