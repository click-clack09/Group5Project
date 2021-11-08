package application.model;

import java.util.ArrayList;

public class CheckList<T>  {

	private ArrayList<T> unchecked;
	private ArrayList<T> checked;
	
	public Checklist<T> () {
		unchecked = new ArrayList<T>();
		checked = new ArrayList<T>();
	}
	
	
	
}
