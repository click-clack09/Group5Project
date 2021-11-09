package application.model;

import java.util.ArrayList;

public class CheckList<T> extends ArrayList{

	private ArrayList<T> unchecked; //list of tasks
	//Boolean checked/unchecked
	/*Use the checking of the box to delete the item
	 * when the box gets checked, set delay, then delete
	 * this will get handled in the controller
	 */
	
	//collection of tasks, no longer needed, merged with checklist
		/*needs userID - pull from user
		 * EventID - generated from event hub name
		 * EventType (ed, personal, business) - pull from hub
		 * Hub name - pull from hub
		 * task name - pull from tasks
		 * imagePath - pull from tasks
		 * text - pull from tasks*/ 
	
	//Just use an ArrayList of objects from the controller?
	//private ArrayList<T> checked;
	
	//public Checklist<T> () {
	//	unchecked = new ArrayList<T>();
	//	checked = new ArrayList<T>();
	//}
	
	
	
}
