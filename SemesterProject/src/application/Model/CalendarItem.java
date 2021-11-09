package application.Model;

public abstract class CalendarItem {
	
	protected int id;
	
	protected User owner;
	
	protected String name;
	protected String description;
	
	/*
	 * CONSTRUCTORS
	 */
	
	public CalendarItem(String name) {
		id = 0;
		
		owner = User.getUsername();
		
		this.name = name;
		description = "";
	}
		
	/*
	 * GETTERS
	 */
	
	public int getID() {
		return id;
	}
	
	public User getOwner() {
		return owner;
	}
		
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	/*
	 * SETTERS
	 */
	
	public void setID(int id) {
		this.id = id;
	}
	
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
