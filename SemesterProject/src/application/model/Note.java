package application.model;

public class Note implements Comparable {

	private String imagePath;
	private String text;
	//
	
	/*
	 * CONSTRUCTORS
	 */
	
	public Note(String imagePath, String text) {
		this.imagePath = imagePath;
		this.text = text;
	}
	
	public Note(String text) {
		this.imagePath = "";
		this.text = text;
	}

	/*
	 * GETTERS
	 */
	
	public String getImagePath() {
		return imagePath;
	}
	
	public String getText() {
		return text;
	}
	
	/*
	 * SETTERS
	 */
	
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public int compareTo(Object o) {
		if (this.getText().equals(o))
			return 1;// TODO Auto-generated method stub
		return -1;
	}
}
