package application.Model;

public class Task implements Comparable<Task> {
	
	private String name;
	private Date date;
	private String description;
	private ToDoList subtasks;

	
	/*
	 * CONSTRUCTORS
	 */
	
	public Task(String name) {
		this.name = name;
		date = new Date();
		description = "";
		subtasks = new ToDoList();
	}
	
	public Task(String name, Date date) {
		this.name = name;
		this.date = date;
		description = "";
		subtasks = new ToDoList();
	}
	
	public Task(String name, String description) {
		this.name = name;
		date = new Date();
		this.description = description;
		subtasks = new ToDoList();
	}
	public Task(String name, Date date, String description) {
		this.name = name;
		this.date = date;
		this.description = description;
		subtasks = new ToDoList();
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
	
	public String getName() {
		return name;
	}
	
	public Date getDate() {
		return date;
	}
	
	public String getDescription() {
		return description;
	}
	
	public ToDoList getSubtasks() {
		return subtasks;
	}
	
	/*
	 * SETTERS
	 */
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setSubtasks(ToDoList subtasks) {
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
