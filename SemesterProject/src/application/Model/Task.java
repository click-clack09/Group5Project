package application.model;

public class Task extends CalendarItem implements Comparable<Task> {
	
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
	 * HELPER METHODS
	 */
	
	public void addSubtask(Task subtask) {
		//MUST FINISH
	}
	
	public void checkSubtask(Task subtask) {
		//MUST FINISH
	}
	
	public void removeSubtask(Task subtask) {
		//MUST FINISH
	}
	
	/*
	 * GETTERS
	 */
	
	public Date getDueDate() {
		return dueDate;
	}
	
	public CheckList<Task> getSubtasks() {
		return subtasks;
	}
	
	/*
	 * SETTERS
	 */
	
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	public void setSubtasks(CheckList<Task> subtasks) {
		this.subtasks = subtasks;
	}
	
	/*
	 * OVERRIDES
	 */
	
	@Override
	public int compareTo(Task task) {
		return this.dueDate.compareTo(task.getDueDate());
	}
	
	@Override
	public String toString() {
		return name + " " + dueDate + "\nSubtasks: " + subtasks.size() + "\n" + description;
	}
}
