package application.model;
//kill
public class Meeting extends HubEvent{
	
	protected String note;
	protected Time time;
	protected Boolean recurring;
	
	//constructor, non-parameterized
	public Meeting() {
		
	}
	
	//parameterized constructor
	public Meeting(String name, int id, String eventName, Owner owner, Date date, String note, String location,
			Time time, boolean recurring) {
		super(name, date, date);
		//this.id = id;
		//this.eventName = eventName;
		//this.owner = owner;
		//this.date = date;
		this.note = note;
		this.location = location;
		this.time = time;
		this.recurring = recurring;
	}

//	public String getEventName() {
//		return name;
//	}
//
//	public void setEventName(String eventName) {
//		this.name = eventName;
//	}
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public Owner getOwner() {
//		return owner;
//	}
//
//	public void setOwner(Owner owner) {
//		this.owner = owner;
//	}

//	public Date getDate() {
//		return this.getDate();
//	}
//
//	public void setDate(Date date) {
//		this.setDate(date);
//	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public boolean isRecurring() {
		return recurring;
	}

	public void setRecurring(boolean recurring) {
		this.recurring = recurring;
	}
			
}