package application.model;

public abstract class CalendarItem_KILL {
	//replace this with event, delete
	protected int id;
	
	protected Owner_KILL owner_KILL;
	
	protected String name;
	protected String description;
	
	/*
	 * CONSTRUCTORS
	 */
	public CalendarItem_KILL()
	{
		
	}
	public CalendarItem_KILL(String name) {
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
	
	public Owner_KILL getOwner() {
		return owner_KILL;
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
	
	public void setOwner(Owner_KILL owner_KILL) {
		this.owner_KILL = owner_KILL;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
