package application.model;

public abstract class CalendarItem {
	//replace this with event 
	protected int id;
	
	protected Owner owner;
	
	protected String name;
	protected String description;
	
	/*
	 * CONSTRUCTORS
	 */
	public CalendarItem()
	{
		
	}
	public CalendarItem(String name) {
		id = 0;
		
		//owner = User.getUsername();
		
		this.name = name;
		description = "";
	}
		
	/*
	 * GETTERS
	 */
	
	public int getID() {
		return id;
	}
	
	public Owner getOwner() {
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
	
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
