package application.model;

public class RecurringEvent extends Event {
	
	private int frequency;
	private Date recurringStart;
	private Date recurringEnd;
	
	/*
	 * CONSTRUCTORS
	 */
		
	public RecurringEvent(String name) {
		super(name);
		
		frequency = 0;
		
		recurringStart = new Date();
		recurringEnd = new Date();
	}
		
	/*
	 * HELPER METHODS
	 */
		
	//helper methods here
		
	/*
	 * GETTERS
	 */
		
	//getters here
		
	/*
	 * SETTERS
	 */
		
	//setters here
		
	/*
	 * OVERRIDES
	 */
		
	//overrides for abstract classes here
}
