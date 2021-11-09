package application.model;

import java.util.ArrayList;
import javafx.scene.control.CheckBox;
//redundant, kill
public class LifeHubCheckBoxList_KILL extends LifeHub{
	private ArrayList<CheckBox> items = new ArrayList<CheckBox>();

	public LifeHubCheckBoxList_KILL()
	{
		super();
	}
	
	public LifeHubCheckBoxList_KILL(String listName, ArrayList items) {
		super(listName);
		this.items = items;
	}

	public void addItem(CheckBox newItem) {
		items.add(newItem);
	}

	public void addItemAt(CheckBox newItem, int index) {
		items.add(index, newItem);
	}

	/**
	 * @return the items
	 */
	public ArrayList<CheckBox> getItems() {
		return items;
	}

	public CheckBox getItemAt(int index) {
		CheckBox itemSearched = items.get(index);
		return itemSearched;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(ArrayList<CheckBox> items) {
		this.items = items;
	}


}