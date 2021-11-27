package application.model;


/**This Class is used to create Note objects for the application's users
 * The Note object has an imagePath field and a field for the text that represents
 * the note as a String
 * 
 */ 
public class Note implements Comparable {

	private String imagePath;
	private String text;

	/**This a parameterized constructor Note class
	 * 
	 * @param imagePath- String representing the path to an image file
	 * @param text - String representing the details of the user's note
	 * @return none
	 */
	public Note(String imagePath, String text) {
		this.imagePath = imagePath;
		this.text = text;
	}
	
	/**This a parameterized constructor Note class
	 * 
	 * @param imagePath- String representing the path to an image file is set to an empty string
	 * @param text - String representing the details of the user's note
	 * @return none
	 */
	public Note(String text) {
		this.imagePath = "";
		this.text = text;
	}


	/** This a getter that returns the path to an image
	 * 
	 * @return String imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}
	
	/** This a getter that returns the text of the note
	 * 
	 * @return String text
	 */
	public String getText() {
		return text;
	}
	
	/** This a setter that sets the image path
	 * 
	 * @param String imagePath
	 * @return none
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	/** This a setter that sets the note's text
	 * 
	 * @param String text
	 * @return none
	 */
	public void setText(String text) {
		this.text = text;
	}

	/** This method is used to compare text
	 * 
	 * @param String text
	 * @return 1 if the text match, -1 if the text does not math
	 */
	@Override
	public int compareTo(Object o) {
		if (this.getText().equals(o))
			return 1;// TODO Auto-generated method stub
		return -1;
	}
}
