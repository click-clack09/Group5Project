package application.model;

import java.util.ArrayList;
//this represents the class / course
public class SchoolClass{
	
	private String professor;
	private String location;
	//needs metadata for database
	private ArrayList<Task> assignments;//to do list for this course
	private HubEvent meetingTime; //this would be the place for recurring handle on the front end
	
	//constructor, non-parameterized
	public SchoolClass() {
		super();
	}
	
	public SchoolClass(int id, String eventName, Owner_KILL owner_KILL, Date date, String note, String location,
			Time time, String professor, boolean recurring) {
		//super(eventName);
		//this.id = id;
		////this.eventName = eventName;
		//this.owner = owner;
		//this.date = date;
		//this.note = note;
		//this.location = location;
		//this.time = time;
		this.professor = professor;
		//this.recurring = recurring;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

//	public String getEventName() {
//		return eventName;
//	}
//
//	public void setEventName(String eventName) {
//		this.eventName = eventName;
//	}

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
//		return date;
//	}
//
//	public void setDate(Date date) {
//		this.date = date;
//	}
//
//	public String getNote() {
//		return note;
//	}
//
//	public void setNote(String note) {
//		this.note = note;
//	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

//	public Time getTime() {
//		return time;
//	}
//
//	public void setTime(Time time) {
//		this.time = time;
//	}
//
//	public boolean isRecurring() {
//		return recurring;
//	}
//
//	public void setRecurring(boolean recurring) {
//		this.recurring = recurring;
//	}

}