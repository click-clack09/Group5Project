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
	
	
	
	public SchoolClass(String professor, String location, ArrayList<Task> assignments, HubEvent meetingTime) {
		super();
		this.professor = professor;
		this.location = location;
		this.assignments = assignments;
		this.meetingTime = meetingTime;
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



	/**
	 * @return the professor
	 */
	public String getProfessor() {
		return professor;
	}



	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}



	/**
	 * @return the assignments
	 */
	public ArrayList<Task> getAssignments() {
		return assignments;
	}



	/**
	 * @return the meetingTime
	 */
	public HubEvent getMeetingTime() {
		return meetingTime;
	}



	/**
	 * @param professor the professor to set
	 */
	public void setProfessor(String professor) {
		this.professor = professor;
	}



	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}



	/**
	 * @param assignments the assignments to set
	 */
	public void setAssignments(ArrayList<Task> assignments) {
		this.assignments = assignments;
	}



	/**
	 * @param meetingTime the meetingTime to set
	 */
	public void setMeetingTime(HubEvent meetingTime) {
		this.meetingTime = meetingTime;
	}


}