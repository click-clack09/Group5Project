package application.model;

public class Task implements Comparable<Task> extends CalendarItem {
	
	private Date dueDate;
	private CheckList<Task> subtasks;

	
	/*
	 * CONSTRUCTORS
	 */
	
	public Task(String name) {
		super(name);
		
		dueDate = new Date();
		subtasks = new CheckList<Task>();
	}
	
	public Task(String name, Date dueDate) {
		super(name);
		
		this.dueDate = dueDate;
		subtasks = new CheckList<Task>();
	}
	
	public Task(String name, String description) {
		super(name, description);
		
		dueDate = new Date();
		subtasks = new CheckList<Task>();
	}
	public Task(String name, Date dueDate, String description) {
		super(name, description);
		
		this.dueDate = dueDate;
		subtasks = new CheckList<Task>();
	}
	/*
	 * + addSubtask(subtask: Task)
	 * + checkSubtask(subtask: Task)
	 * + removeSubtask(subtask: Task)
	 * + toString(): String
	 */
	
	/*
	 * HELPER METHODS
	 */
	
	//helper methods here
	
	/*
	 * GETTERS
	 */
	
	public Date getDate() {
		return date;
	}
	
	public CheckList<Task> getSubtasks() {
		return subtasks;
	}
	
	/*
	 * SETTERS
	 */
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setSubtasks(CheckList<Task> subtasks) {
		this.subtasks = subtasks;
	}
	
	/*
	 * OVERRIDES
	 */
	
	@Override
	public int compareTo(Task task) {
		return this.date.compareTo(task.getDate());
	}
	
	@Override
	public String toString() {
		return name + " " + date + "\nSubtasks: " + subtasks.size() + "\n" + description;
	}
}
