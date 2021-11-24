package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;

import application.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**This class controls the EdClass view, which is used to display a single SchoolClass
 * from an Educational Hub.
 * 
 * @author group 5 11-23-21
 *
 */

public class EdClassController {
	
	@FXML
	private AnchorPane mainPane;
	
    @FXML
    private Label classLabel;
    
    @FXML
    private Label toDoClassName;
    
    @FXML
    private VBox classToDo;

    @FXML
    private TextArea classNotes;
    
    @FXML
    private Label text800;

    @FXML
    private Label text830;

    @FXML
    private Label text900;

    @FXML
    private Label text930;

    @FXML
    private Label text1000;

    @FXML
    private Label text1030;

    @FXML
    private Label text1100;

    @FXML
    private Label text1130;

    @FXML
    private Label text1200;

    @FXML
    private Label text1230;

    @FXML
    private Label text1300;

    @FXML
    private Label text1330;

    @FXML
    private Label text1400;

    @FXML
    private Label text1430;

    @FXML
    private Label text1500;

    @FXML
    private Label text1530;

    @FXML
    private Label text1600;

    @FXML
    private Label text1630;

    @FXML
    private Label text1700;

    @FXML
    private Label text1730;

    @FXML
    private Label text1800;

    @FXML
    private Label text1830;

    @FXML
    private Label text1900;

    @FXML
    private Label text1930;

    @FXML
    private Label text2000;

    @FXML
    private Label text2030;

    @FXML
    private Label text2100;

    @FXML
    private Label text2130;

    @FXML
    private Label text2200;

    @FXML
    private Label text2230;

    @FXML
    private Label text2300;
    
    private ArrayList<Label> labels = new ArrayList<Label>();

    @FXML
    private ListView<Hyperlink> archivedClassNotes;
    
    private ObservableList<Hyperlink> archivedNoteList = FXCollections.observableArrayList();
    private ObservableList<CheckBox> toDoVBoxList = FXCollections.observableArrayList();
    private int index;
	
  //This goes with item/task delete  deleteTask(Task task, String className)
    
    /**This method displays the initial values for the HubEvents, Tasks, Notes, and contacts that were read from the database by the UserController,
     * no additional fields or methods.
     * 
     */
    @FXML
    void initialize()
    {
    	initializeDailyLabels();
    	updateDailyCalendar();
    	toDoClassName.setText(User.getCurrentClass());
    	
    	//Get the current class notes, add to ToDoList
    	
    	//Find class in SchoolClass ArrayList. This is faster than a DB query
    	int sentinel = -1;
    	//index of current class in Users SchoolClass ArrayList 
    	index = 0;
    	//loop through user's classes, find a match
    	
    	while(index<User.getClasses().size()&&sentinel<0)
		{
    		sentinel = User.getClasses().get(index++).compareTo(User.getCurrentClass());
		}
    	//decrement the index, always goes one too far
    	if (index > 0)
    		--index;
    	
    	//Set the top label text
    	classLabel.setText(User.getUserName()+", "+User.getCurrentClass()+", "+User.getClasses().get(index).getProfessor()+", "+User.getClasses().get(index).getLocation());
    	
    	//if there are ToDo items
    	if(User.getClasses().get(index).getAssignments()!=null)
    	{
    		
    		for (int i = 0; i < User.getClasses().get(index).getAssignments().size(); i++)
    		{	System.out.println(User.getClasses().get(index).getAssignments().get(i).getText());
    		//Make a CheckBox for each task
        	CheckBox cb = new CheckBox(User.getClasses().get(index).getAssignments().get(i).getText());
        	cb.setPadding(new Insets(10, 10, 0, 0));
        	Task tempTask = new Task(User.getClasses().get(index).getAssignments().get(i).getText());
            String tempClass = User.getClasses().get(i).getClassName();
        	cb.setOnAction(event3 -> {
        		if (cb.isSelected()) 
                {
        			toDoVBoxList.remove(cb);
        			classToDo.getChildren().clear();
        			classToDo.getChildren().addAll(toDoVBoxList);
                	User.deleteTask(tempTask, tempClass);
                	System.out.println("CHECKBOX ACTIVATED");
                	//delete task, use taskHash and classHash as applicable, start thread, if still checked delete?
                }
              });
        	toDoVBoxList.add(cb);
    		}//cb.setStyle("-fx-text-fill:white");
            
            
            classToDo.getChildren().addAll(toDoVBoxList);
    	}
    	
    	//Deal with Archived notes
    	if(User.getClasses().get(index).getNotes() !=null)
    	{
    		
    		for (int i = 0; i < User.getClasses().get(index).getNotes().size(); i++)
    		{	
    			Hyperlink tempLink = new Hyperlink(User.getClasses().get(index).getNotes().get(i).getText());
    			Alert alert = getAlert("Display Note","Note",User.getClasses().get(index).getNotes().get(i).getText());
              	
                tempLink.setOnAction(event2 ->{
              	  alert.showAndWait();
              	  
                });
                archivedNoteList.add(tempLink);
    		}
    		//Add the date to this as well. Wishlist make this a hyperlink with a popup
    		archivedClassNotes.getItems().addAll(archivedNoteList);

    	    //probably should consider how to delete notes
    	}
   } 
    
    /**Top menu item. Unused at this time.
     * 
     * @param event- the triggering event
     */
	@FXML
    void addHub(ActionEvent event) {
    	System.out.println("testAddHub");
    }
	
	/**Top menu item. Unused at this time.
     * 
     * @param event- the triggering event
     */
	@FXML
    void changeTheme(ActionEvent event) {
    	System.out.println("testChangeTheme");
    }
    
	/**Top menu item. Unused at this time.
     * 
     * @param event- the triggering event
     */
	 @FXML
	    void tutorial(ActionEvent event) {
		 System.out.println("testTutorial");
	    }
	 
	 /**This method accepts an ActionEvent, displays a TextInputDialog to collect user
	     * input, creates a new Task, adds it to the ObservableList, sets the Action for the
	     * checkbox, pushes the new Task to the database, ad returns nothing.
	     * 
	     * @param event- the button press which triggers this method
	     */
	 @FXML
	 void addItem(ActionEvent event) {
	 	System.out.println("testAddItem");
	 	TextInputDialog textDialog = new TextInputDialog();
	 	String className = User.getCurrentClass();
		String taskString = "";
		boolean validInput;
		
		try
		{
			do
			{
				textDialog.getEditor().clear();
		      	textDialog.setTitle("New Task");
		      	textDialog.setHeaderText("Please enter the To-Do item");
		      	textDialog.setContentText("Task:");
		      	textDialog.showAndWait();
		      	if (textDialog.getResult()==null)
		      		throw new Exception();
		      	taskString = textDialog.getResult();
		      	validInput = validateInput(taskString);
			}while(!validInput);
			
	      	Task tempTask = new Task(taskString);
	      	//Add task to class. Need to pass class index
	      	User.getClasses().get(index).getAssignments().add(tempTask);
	      	
	      	//deal with css
			//add this here, or is this part of parent ObservableList?
	        CheckBox cb = new CheckBox(taskString);
	        //cb.setStyle("-fx-text-fill:white");
	        cb.setPadding(new Insets(10, 10, 0, 0));
	        String tempClass = User.getClasses().get(index).getClassName();
	        cb.setOnAction(event3 -> {
	        	if (cb.isSelected()) 
	            {
	        		toDoVBoxList.remove(cb);
	    			classToDo.getChildren().clear();
	    			classToDo.getChildren().addAll(toDoVBoxList);
	            	User.deleteTask(tempTask, tempClass);
	            	System.out.println("CHECKBOX ACTIVATED");
	            	//delete task, use taskHash and classHash as applicable, start thread, if still checked delete?
	            }
	          });
	        //This adds it to the appropriate observable VBox
	       
	        toDoVBoxList.add(cb);
	        classToDo.getChildren().clear();
	        classToDo.getChildren().addAll(toDoVBoxList);
	        User.addTask(tempTask, User.getCurrentClass());
	    }
		catch(Exception e)
		{
			
		}
	 }
	 
	 @FXML
	    void about(ActionEvent event) {
		 System.out.println("testAbout");
	    }
	
	 /**Top menu item. Unused at this time.
	     * 
	     * @param event- the triggering event
	     */
	 @FXML
	 void logout(ActionEvent event) {
		 System.out.println("testLogout");
	 }
	   
	 /**Top menu item. Unused at this time.
	  * 
	  * @param event- the triggering event
	  */    
	 @FXML
	 void close(ActionEvent event) {
		 System.out.println("testClose");
	 }

	 /**Top menu item. Unused at this time.
	  * 
	  * @param event- the triggering event
	  */
	 @FXML
	 void deleteHub(ActionEvent event) {
		 System.out.println("testDelete");
	 }
    
	 /**This method accepts an ActionEvent, takes the user back to the main education hub,
	  * and returns nothing.
	  * 
	  * @param event- the ActionEvent that triggers this method
	  * @throws IOException- handles issues with the fxml documents.
	  */
	 @FXML
	 void goBack(ActionEvent event) throws IOException {
		 mainPane = FXMLLoader.load(User.getLastHub());
		 User.setLastHub(new File("src/application/view/EdClass.fxml").toURI().toURL());
		 Scene scene = new Scene(mainPane);
		 Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		 window.setScene(scene);
		 window.show();
	 }

    /**This method accepts an ActionEvent, gets the text from the "notes" TextField,
     * creates a Hyperlink from the text, adds the Hyperlink to an ObservableList,
     * and returns nothing.
     * 
     * @param event- the ActionEvent that triggers this method
     */
    @FXML
    void saveNote(ActionEvent event) {
    	//archivedNoteList.add(User.getClasses().get(index).getNotes().get(i).getText());
    	//Add a note to the class notes
    	if (!classNotes.getText().equals(""))
    	{
	    	Note tempNote = new Note(classNotes.getText());
	    	User.getClasses().get(index).getNotes().add(tempNote);
	    	//Add the date to this as well.
	    	Alert alert = getAlert("Display Note","Note",classNotes.getText());
	    	Hyperlink tempLink = new Hyperlink(classNotes.getText());
	    	tempLink.setOnAction(event2 ->{
	    		alert.showAndWait();
	        });
	        archivedClassNotes.getItems().add(tempLink);
	        archivedNoteList.add(tempLink);
	    	//Push to DB
	        User.addArchivedNote(tempNote);
    	}
    	classNotes.clear();
    }

    /**This method accepts an ActionEvent, changes the scene to the UserHome,
     * and returns nothing.
     * 
     * @param event- the ActionEvent that triggers this method
     * @throws IOException- handles issues with the fxml documents.
     */
    public Alert getAlert(String title, String header, String content)
    {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle(title);
    	alert.setHeaderText(header);
    	alert.setContentText(content);
    	
    	return alert;
    }

    /**This method accepts an ActionEvent, changes the scene to the UserHome,
     * and returns nothing.
     * 
     * @param event- the ActionEvent that triggers this method
     * @throws IOException- handles issues with the fxml documents.
     */
    @FXML
    void userHome(ActionEvent event) throws IOException {
    	URL url = new File("src/application/view/UserHome.fxml").toURI().toURL();
   		mainPane = FXMLLoader.load(url);
   		Scene scene = new Scene(mainPane);
   		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
   		System.out.println("5");
   		window.setScene(scene);
   		System.out.println("6");
	    window.show();
	}
    
    /**This method accepts an ActionEvent, displays a ChoiceDialog of the SchoolClasses's notes,
     * removes the note from the ArchivedNotes and the database based on the users selection,
     * and returns nothing.
     * 
     * @param event- the ActionEvent that triggers this method.
     */
    @FXML
    void deleteNote(ActionEvent event) {
    	if (archivedNoteList.size()>0)
    	{
    		boolean validInput;
    	
	    	boolean find = true;
	    	int classFound = -1;
	    	int noteFound = -1;
	    	ArrayList<String> noteStrings = new ArrayList<String>();
	    	//which class are we on now?
	    	int countClass = User.getCurrentHub().getClasses().size();
	    	int countNote = -1;
	    	while (find)
			{
				if (countClass > 0)
				{
					classFound=User.getCurrentHub().getClasses().get(--countClass).compareTo(User.getCurrentClass());
					//if valid hubType is returned, search is complete
					if (classFound > 0)
						find = false;
				}
				//Ends search when all values have been checked
				else
					find = false;
	
			}
	        if (classFound > 0)
	        {
		    	for (int i = 0; i < User.getCurrentHub().getClasses().get(countClass).getNotes().size(); i++)
		    		noteStrings.add(User.getCurrentHub().getClasses().get(countClass).getNotes().get(i).getText());
		    	ChoiceDialog<String> choicePopup = new ChoiceDialog<String>("Please select:", noteStrings);
		        
		        String input = "";
		        //choicePopup = new ChoiceDialog("Please select", classNameStrings);
		        try
		        {
			        do
			        {
			            choicePopup.setTitle("Delete note");
			            choicePopup.setHeaderText("Please select note to remove");
			            choicePopup.setContentText("Use Dropdown menu:\n");
			            choicePopup.showAndWait();
			            if(choicePopup.getResult()==null)
			            	throw new Exception();
			            input = choicePopup.getResult().toString();
			            validInput = validateInput(input);
			        }while(!validInput);
			        System.out.println(input);
			        countNote = User.getCurrentHub().getClasses().get(countClass).getNotes().size();
			        find = true;
			        while (find)
					{
						if (countNote > 0)
						{
							noteFound=User.getCurrentHub().getClasses().get(countClass).getNotes().get(--countNote).compareTo(input);
							//if valid hubType is returned, search is complete
							if (noteFound > 0)
								find = false;
						}
						//Ends search when all values have been checked
						else
							find = false;
			
					}
			        if (noteFound > 0)
			        {
			        	archivedNoteList.remove(choicePopup.getItems().indexOf(input));
			        	archivedClassNotes.getItems().clear();
			        	archivedClassNotes.getItems().addAll(archivedNoteList);
			        	
			        	User.deleteArchivedNote(User.getCurrentHub().getClasses().get(countClass).getNotes().get(countNote));
			        	
			        	User.getCurrentHub().getClasses().get(countClass).getNotes().remove(countNote);
			        }
		        }
		        catch(Exception e)
		        {
		        	
		        }
	        }
        }
    }
    
    /**This method accepts no arguments, sets the text for all of the daily calendar labels,
     * and returns nothing.
     * 
     */
    void initializeDailyLabels() {
    	labels.add(text800);
    	labels.add(text830);
    	labels.add(text900);
    	labels.add(text930);
    	labels.add(text1000);
    	labels.add(text1030);
    	labels.add(text1100);
    	labels.add(text1130);
    	labels.add(text1200);
    	labels.add(text1230);
    	labels.add(text1300);
    	labels.add(text1330);
    	labels.add(text1400);
    	labels.add(text1430);
    	labels.add(text1500);
    	labels.add(text1530);
    	labels.add(text1600);
    	labels.add(text1630);
    	labels.add(text1700);
    	labels.add(text1730);
    	labels.add(text1800);
    	labels.add(text1830);
    	labels.add(text1900);
    	labels.add(text1930);
    	labels.add(text2000);
    	labels.add(text2030);
    	labels.add(text2100);
    	labels.add(text2130);
    	labels.add(text2200);
    	labels.add(text2230);
    	labels.add(text2300);    	
    }
    
    /**This method accepts no arguments, sets the daily calendar labels to a single space character,
     * and returns nothing.
     */
    void clearDailyCalendar() {
    	for(Label label : labels) {
    		label.setText(" ");
        	label.setTextFill(Color.web("#000000"));
        	//label.setStyle("-fx-background-color: #909090");
    	}
    }
    
    /**This method accepts no arguments, gets the current date, checks for user HubEvents from the current date,
     * calls the displayDailyEvent method if the HubEvent date matches the current date, and returns nothing.
     * 
     */
    void updateDailyCalendar() {
    	clearDailyCalendar();
    	
    	ArrayList<HubEvent> events = User.getUserEvents();
    	
    	LocalDate currentdate = LocalDate.now();
    	
    	int year = currentdate.getYear();
    	int month = currentdate.getMonth().getValue();
    	int day = currentdate.getDayOfMonth();
    	
    	for(HubEvent e : events) {
    		
    		Date d = e.getStartDate();
    		
    		if(year == d.getYear() && month == d.getMonth() && day == d.getDay()) {
    			displayDailyEvent(e);
    		}
    	}
    }
    
    /**This method accepts an instance of the HubEvent Class, sets the text in the
     * label corresponding to the correct hour, and returns nothing.
     * 
     * @param e-the HubEvent to be displayed.
     */
    	void displayDailyEvent(HubEvent e) {
    	
    	Date d = e.getStartDate();
    	
    	if(d.getHour() < 8) {
    		String text = text800.getText();
    		text += e.getEventName() + " \n";
    		text800.setText(text);
    	}
    	if(d.getHour() == 8) {
    		if(d.getMinute() >= 30) {
    			String text = text830.getText();
        		text += e.getEventName() + " \n";
        		text830.setText(text);
    		}
    		else {
    			String text = text800.getText();
        		text += e.getEventName() + " \n";
        		text800.setText(text);
    		}
    	}
    	if(d.getHour() == 9) {
    		if(d.getMinute() >= 30) {
    			String text = text930.getText();
        		text += e.getEventName() + " \n";
        		text930.setText(text);
    		}
    		else {
    			String text = text900.getText();
        		text += e.getEventName() + " \n";
        		text900.setText(text);
    		}
    	}
    	if(d.getHour() == 10) {
    		if(d.getMinute() >= 30) {
    			String text = text1030.getText();
        		text += e.getEventName() + " \n";
        		text1030.setText(text);
    		}
    		else {
    			String text = text1000.getText();
        		text += e.getEventName() + " \n";
        		text1000.setText(text);
    		}
    	}
    	if(d.getHour() == 11) {
    		if(d.getMinute() >= 30) {
    			String text = text1130.getText();
        		text += e.getEventName() + " \n";
        		text1130.setText(text);
    		}
    		else {
    			String text = text1100.getText();
        		text += e.getEventName() + " \n";
        		text1100.setText(text);
    		}
    	}
    	if(d.getHour() == 12) {
    		if(d.getMinute() >= 30) {
    			String text = text1230.getText();
        		text += e.getEventName() + " \n";
        		text1230.setText(text);
    		}
    		else {
    			String text = text1200.getText();
        		text += e.getEventName() + " \n";
        		text1200.setText(text);
    		}
    	}
    	if(d.getHour() == 13) {
    		if(d.getMinute() >= 30) {
    			String text = text1330.getText();
        		text += e.getEventName() + " \n";
        		text1330.setText(text);
    		}
    		else {
    			String text = text1300.getText();
        		text += e.getEventName() + " \n";
        		text1300.setText(text);
    		}
    	}
    	if(d.getHour() == 14) {
    		if(d.getMinute() >= 30) {
    			String text = text1430.getText();
        		text += e.getEventName() + " \n";
        		text1430.setText(text);
    		}
    		else {
    			String text = text1400.getText();
        		text += e.getEventName() + " \n";
        		text1400.setText(text);
    		}
    	}
    	if(d.getHour() == 15) {
    		if(d.getMinute() >= 30) {
    			String text = text1530.getText();
        		text += e.getEventName() + " \n";
        		text1530.setText(text);
    		}
    		else {
    			String text = text1500.getText();
        		text += e.getEventName() + " \n";
        		text1500.setText(text);
    		}
    	}
    	if(d.getHour() == 16) {
    		if(d.getMinute() >= 30) {
    			String text = text1630.getText();
        		text += e.getEventName() + " \n";
        		text1630.setText(text);
    		}
    		else {
    			String text = text1600.getText();
        		text += e.getEventName() + " \n";
        		text1600.setText(text);
    		}
    	}
    	if(d.getHour() == 17) {
    		if(d.getMinute() >= 30) {
    			String text = text1730.getText();
        		text += e.getEventName() + " \n";
        		text1730.setText(text);
    		}
    		else {
    			String text = text1700.getText();
        		text += e.getEventName() + " \n";
        		text1700.setText(text);
    		}
    	}
    	if(d.getHour() == 18) {
    		if(d.getMinute() >= 30) {
    			String text = text1830.getText();
        		text += e.getEventName() + " \n";
        		text1830.setText(text);
    		}
    		else {
    			String text = text1800.getText();
        		text += e.getEventName() + " \n";
        		text1800.setText(text);
    		}
    	}
    	if(d.getHour() == 19) {
    		if(d.getMinute() >= 30) {
    			String text = text1930.getText();
        		text += e.getEventName() + " \n";
        		text1930.setText(text);
    		}
    		else {
    			String text = text1900.getText();
        		text += e.getEventName() + " \n";
        		text1900.setText(text);
    		}
    	}
    	if(d.getHour() == 20) {
    		if(d.getMinute() >= 30) {
    			String text = text2030.getText();
        		text += e.getEventName() + " \n";
        		text2030.setText(text);
    		}
    		else {
    			String text = text2000.getText();
        		text += e.getEventName() + " \n";
        		text2000.setText(text);
    		}
    	}
    	if(d.getHour() == 21) {
    		if(d.getMinute() >= 30) {
    			String text = text2130.getText();
        		text += e.getEventName() + " \n";
        		text2130.setText(text);
    		}
    		else {
    			String text = text2100.getText();
        		text += e.getEventName() + " \n";
        		text2100.setText(text);
    		}
    	}
    	if(d.getHour() == 20) {
    		if(d.getMinute() >= 30) {
    			String text = text2130.getText();
        		text += e.getEventName() + " \n";
        		text2130.setText(text);
    		}
    		else {
    			String text = text2100.getText();
        		text += e.getEventName() + " \n";
        		text2100.setText(text);
    		}
    	}
    	if(d.getHour() == 22) {
    		if(d.getMinute() >= 30) {
    			String text = text2230.getText();
        		text += e.getEventName() + " \n";
        		text2230.setText(text);
    		}
    		else {
    			String text = text2200.getText();
        		text += e.getEventName() + " \n";
        		text2200.setText(text);
    		}
    	}
    	
    	if(d.getHour() >= 23) {
    		String text = text2300.getText();
    		text += e.getEventName() + " \n";
    		text2300.setText(text);
    	}
    }
    
/**This method accepts a String, returns true if the String is not empty
 * and not null, otherwise returns false.
 * 
 * @param input- The String being validated.
 * @return- the Boolean value determined by the String being null or empty.
 */
    public boolean validateInput(String input)
    {
    	if(input != null)
    	{
    		if (!input.equals(""))
    			return true;
    	}
    	return false;
    }
    
}
