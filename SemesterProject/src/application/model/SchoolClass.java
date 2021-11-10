package application.model;

import java.util.ArrayList;
//this represents the class / course
public class SchoolClass{
	
	private String className;
	private String professor;
	private String location;
	//needs metadata for database
	private ArrayList<Task> assignments;//to do list for this course
	private HubEvent meetingTime; //this would be the place for recurring handle on the front end
	
	public SchoolClass(String className, String professor, String location, ArrayList<Task> assignments,
			HubEvent meetingTime) {
		super();
		this.className = className;
		this.professor = professor;
		this.location = location;
		this.assignments = assignments;
		this.meetingTime = meetingTime;
	}

	

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
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
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
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