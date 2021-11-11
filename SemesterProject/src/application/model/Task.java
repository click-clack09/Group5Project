package application.model;

public class Task implements Comparable<Task> {
	
	//Add Class or extend to SchoolTask so I can add a class? How to deal with that?
	private String text;//this is unnecessary
	//all details to instantiate object
	//Name, description, all database metadata
	/*
	 * CONSTRUCTORS
	 */
	
	public Task(String text) {
		this.text =  text;
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
	
	
	
	/*
	 * OVERRIDES
	 */
	
	@Override
	public int compareTo(Task task) {
		return 0;
	}


	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}


	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	
}
