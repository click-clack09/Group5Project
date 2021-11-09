package application.model;

import java.util.ArrayList;

public class GroceryList extends LifeHubCheckBoxList {
	
	private ArrayList<String> imageRefs;
	
	/*
	 * CONSTRUCTORS
	 */
	
	public GroceryList() {
		super(); //NEED TO ADD PARAMETERS
		
		imageRefs = new ArrayList<String>();
	}
	
	/*
	 * HELPER METHODS
	 */
	
	public void addImage(String imageRef) {
		imageRefs.add(imageRef);
	}
	
	public void addImage(String imageRef, int index) {
		imageRefs.add(index, imageRef);
	}
	
	public void removeImage(int index) {
		imageRefs.remove(index);
	}
	
	/*
	 * GETTERS
	 */
	
	public ArrayList<String> getImageRefs() {
		return imageRefs;
	}
	
	/*
	 * SETTERS
	 */
	
	public void setImageRefs(ArrayList<String> imageRefs) {
		this.imageRefs = imageRefs;
	}
	
	/*
	 * OVERRIDES
	 */
	
	@Override
	public String toString() {
		return super.toString();
	}
	
}
