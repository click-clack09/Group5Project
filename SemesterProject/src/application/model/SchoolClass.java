package application.model;

import java.util.ArrayList;
import java.util.concurrent.Callable;

/**The School Class is used school class objects
 * 
 * This class is used to represent events related to education and schools
 * 
 */
public class SchoolClass implements Comparable{
	
	private String className;
	private String professor;
	private String location;
	private ArrayList<Task> assignments;
	private HubEvent meetingTime; 
	private ArrayList<Note> notes;
	
	
	/**This a parameterized constructor for the SchoolClass
	 * 
	 * @param className - String representing the class name
	 * @param professor - String representing the name of the professor teaching the class
	 * @param location - String representing the location of the class
	 * @param assignments - ArrayList of Task objects representing the class's assignments
	 * @param meetingTime - String representing the HubEvent
	 * @param notes - ArrayList of Note objects representing the class's notes

	 * @return none
	 */
	public SchoolClass(String className, String professor, String location, ArrayList<Task> assignments,
			HubEvent meetingTime, ArrayList<Note> notes) {
		this.className = className;
		this.professor = professor;
		this.location = location;
		this.assignments = assignments;
		this.meetingTime = meetingTime;
		this.notes = notes;
	}

	

	/** This is a getter that retreives a SchoolClass name
	 * 
	 * @return className, String
	 */
	public String getClassName() {
		return className;
	}

	/** This is a getter that retreives the professor's name
	 * 
	 * @return professor, String
	 */
	public String getProfessor() {
		return professor;
	}

	/** This is a getter that retreives a class location
	 * 
	 * @return location, String
	 */
	public String getLocation() {
		return location;
	}

	/** This is a getter that retreives an ArrayList of Tasks
	 * 
	 * @return ArrayList of Tasks
	 */
	public ArrayList<Task> getAssignments() {
		return assignments;
	}

	/** This is a getter that retreives the meeting time via the HubEvent
	 * 
	 * @return HubEvent
	 */
	public HubEvent getMeetingTime() {
		return meetingTime;
	}

	/** This is a getter that retreives an ArrayList of Note objects
	 * 
	 * @return ArrayList Note objects
	 */
	public ArrayList<Note> getNotes() {
		return notes;
	}

	/** This a setter that set class name
	 * 
	 * @param String className
	 * @return none
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/** This a setter that sets the professor's name
	 * 
	 * @param String professor
	 * @return none
	 */
	public void setProfessor(String professor) {
		this.professor = professor;
	}

	/** This a setter that sets the location
	 * 
	 * @param String location
	 * @return none
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/** This a setter that sets an ArrayList of Task objects as assignments
	 * 
	 * @param ArrayList<Task> assignments
	 * @return none
	 */
	public void setAssignments(ArrayList<Task> assignments) {
		this.assignments = assignments;
	}

	/** This a setter that sets the meeting time
	 * 
	 * @param HubEvent meetingTime
	 * @return none
	 */
	public void setMeetingTime(HubEvent meetingTime) {
		this.meetingTime = meetingTime;
	}

	/** This a setter that sets an ArrayList of Note objects
	 * 
	 * @param ArrayList<Note> notes
	 * @return none
	 */
	public void setNotes(ArrayList<Note> notes) {
		this.notes = notes;
	}


	/** This method is used to compare class name
	 * 
	 * @param ArrayList<Note> notes
	 * @return 1 for a match, -1 for a non-match
	 */
	@Override
	public int compareTo(Object arg0) {
		if (arg0.equals(this.className))
			return 1;
		return -1;
	}

	
}