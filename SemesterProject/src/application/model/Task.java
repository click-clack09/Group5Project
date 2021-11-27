package application.model;

/**The Task Class is used to create tasks for the user
 * 
 * This class has a textfield represented as a String
 */
public class Task implements Comparable<Task> {
	
	private String text;
	
	/**This a parameterized constructor for the Task class
	 * 
	 * @param task - String that represents the details of the user's task
	 * @return none
	 */
	public Task(String text) {
		this.text =  text;
	}
	

	public void addSubtask(Task subtask) {
		//MUST FINISH
	}
	
	public void checkSubtask(Task subtask) {
		//MUST FINISH
	}
	
	public void removeSubtask(Task subtask) {
		//MUST FINISH
	}
	
	
	@Override
	public int compareTo(Task task) {
		return 0;
	}


	/** This is a getter that retreives the text of the task
	 * 
	 * @return String text
	 */
	public String getText() {
		return text;
	}


	/** This a setter that sets the text of the Task
	 * 
	 * @param String text
	 * @return none
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	
}
