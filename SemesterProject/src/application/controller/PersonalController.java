package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import application.model.Contact;
import application.model.Date;
import application.model.HubEvent;
import application.model.Note;
import application.model.SchoolClass;
import application.model.Task;
import application.model.User;
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
import javafx.scene.control.MenuBar;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**This class acts as the controller for the PersonalHome view.
 * 
 * @author group 5 11-23-21
 *
 */
public class PersonalController 
{

	@FXML
	 private AnchorPane mainPane;
	
	@FXML
    private VBox userLists;

    @FXML
    private TextArea note;

    @FXML
    private ListView<Hyperlink> archivedNotes;
    
    @FXML
    private Label personalLabel;
    
    private ObservableList<Hyperlink> archivedNoteList = FXCollections.observableArrayList();
    private ObservableList<VBox> listsVBoxList = FXCollections.observableArrayList();
    private ArrayList<String> toDoLists;
    private HashMap<String, Integer> toDoHash;
	private HashMap<String, String> taskHash;
    
	/**This method take no parameters, uses the User class static fields to display the Tasks,
	 * and Notes belonging the this LifeHub, returns nothing. 
	 * 
	 */
    @FXML 
    void initialize() {
    	
    	toDoLists = new ArrayList<String>();
		
    	personalLabel.setText(User.getUserName()+", "+User.getCurrentHub().getHubName());
    	/*If the user has notes associated with this LifeHub, create a Hyperlink for each one and 
    	  display it under the archived notes list.*/
    	if(User.getCurrentHub().getNotes() !=null)
    	{
    		for (int i = 0; i < User.getCurrentHub().getNotes().size(); i++)
    		{	
    			Hyperlink tempLink = new Hyperlink(User.getCurrentHub().getNotes().get(i).getText());
    			
    			 
    			Alert alert = getAlert("Display Note","Note",User.getCurrentHub().getNotes().get(i).getText());
              	
                tempLink.setOnAction(event2 ->{
              	  alert.showAndWait();
              	  
                });
                archivedNoteList.add(tempLink);
    		}
    		archivedNotes.getItems().addAll(archivedNoteList);
    		
    		for (SchoolClass schoolClass : User.getClasses())
    			toDoLists.add(schoolClass.getClassName());
   		 
   		 	toDoHash = new HashMap<String, Integer>();
   		 	taskHash = new HashMap<String, String>();
    		
    		if (User.getCurrentHub().getClasses()!=null)
    		{	for (int i = 0; i < toDoLists.size(); i++)
    			{
    				//make task class labels
    				Label toDoListLabel = new Label();
    	            //deal with css
    				//classLabel.setStyle("-fx-text-fill:white");
    				toDoListLabel.setText(toDoLists.get(i));
    	            //For every class, add an observable checkList
    	            ObservableList<CheckBox> checkList = FXCollections.observableArrayList();
    	            
    	            //make a separator and a VBox 
    	            Separator separator = new Separator();
    	            VBox temp = new VBox();
    	            temp.setPadding(new Insets(10, 10, 10, 10));
    	            
    	            //Add an entry to the classHash tying the class name to the index.
    	            //We can use this later to remove classes by index.
    	            toDoHash.put(toDoLists.get(i), i);
    	            
    	            //this will get all tasks in ArrayList for class
    	            for (int j = 0; j < User.getClasses().get(i).getAssignments().size(); j++)
    	            {
    	            	//Make a CheckBox for each task
    	            	CheckBox cb = new CheckBox(User.getClasses().get(i).getAssignments().get(j).getText());
    	                //cb.setStyle("-fx-text-fill:white");
    	                cb.setPadding(new Insets(10, 10, 0, 0));
    	                checkList.add(cb);
    	                Task tempTask = new Task(User.getClasses().get(i).getAssignments().get(j).getText());
    	                String tempClass = User.getClasses().get(i).getClassName();
    	                cb.setOnAction(event3 -> {
    	                	if (cb.isSelected()) 
    	                    {
    	                    	User.deleteTask(tempTask, tempClass);
    	                    	System.out.println("CHECKBOX ACTIVATED");
    	                    	//delete task, use taskHash and classHash as applicable, start thread, if still checked delete?
    	                    }
    	                  });
    	                //add HashMap Entry for task text and className (String)
    	                taskHash.put(cb.getText(), toDoLists.get(i));
    	            }
    	            
    	            //only add tempInner when adding new class, otherwise reference it
    	            VBox tempInner = new VBox();
    	            tempInner.getChildren().addAll(checkList);
    	            temp.getChildren().addAll(toDoListLabel,separator,tempInner);
    	            userLists.getChildren().add(temp);
    	            listsVBoxList.add(temp);
    			}
    		}
    	}
    }
    
    /**Top menu item. Unused at this time.
     * 
     * @param event- the triggering event
     */
    @FXML
    void about(ActionEvent event) {

    }

    /**This method accepts an ActionEvent, displays a TextInputDialog to collect user
     * input, creates a new Task, adds it to the ObservableList, sets the Action for the
     * checkbox, pushes the new Task to the database, ad returns nothing.
     * 
     * @param event- the button press which triggers this method
     */
    @FXML
    void addItem(ActionEvent event) {
    	
    	//This is added to allow for new classes to be added on the fly
    	//classes.add(0,"New Class");
    	if(User.getCurrentHub().getClasses().size()!=0)
    	{ChoiceDialog<String> selection = new ChoiceDialog<String>("Select List",toDoLists);
    	TextInputDialog textDialog = new TextInputDialog();
    	String listName = "";
    	String taskString = "";
    	boolean validInput;
    	
    	do
    	{
	    	selection.setTitle("New Task");
	    	selection.setHeaderText("Which list is the task for?");
	    	selection.setContentText("List:");
	      	selection.showAndWait();
	        listName = selection.getResult();
	        validInput = validateInput(listName);
    	}while(!validInput);
	        //remove it here though to not disrupt indexing
      	//classes.remove("New Class");
      	//if className.equals("New Class"); <------------deal with this
        
    	do
    	{
	      	textDialog.getEditor().clear();
	      	textDialog.setTitle("New Task");
	      	textDialog.setHeaderText("Please enter the To-Do item");
	      	textDialog.setContentText("Task:");
	      	textDialog.showAndWait();
	      	taskString = textDialog.getResult();
	      	validInput = validateInput(taskString);
    	}while(!validInput);
      	//add HashMap Entry for task text and className (String)
      	taskHash.put(taskString, listName);
      	
      	Task tempTask = new Task(taskString);
      	//Add task to class
      	User.getClasses().get((int) toDoHash.get(listName)).getAssignments().add(tempTask);
      	String tempSchool = User.getClasses().get((int) toDoHash.get(listName)).getClassName();
      	//deal with css
		//add this here, or is this part of parent ObservableList?
        CheckBox cb = new CheckBox(taskString);
        //cb.setStyle("-fx-text-fill:white");
        cb.setPadding(new Insets(10, 10, 0, 0));
        cb.setOnAction(event3 -> {
        	if (cb.isSelected()) 
            {
            	User.deleteTask(tempTask, tempSchool);
            	System.out.println("CHECKBOX ACTIVATED");
            	//delete task, use taskHash and classHash as applicable, start thread, if still checked delete?
            }
          });
        //This adds it to the appropriate observable VBox
        int VBoxIndex = (int) toDoHash.get(listName);
        listsVBoxList.get(VBoxIndex).getChildren().add(cb);
        
        User.addTask(tempTask, listName);
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

    /**This method accepts an ActionEvent, displays a TextInputDialog to collect
     * the name of the new list, creates the list, adds the list to the ObservableList
     * and the database, and returns nothing.
     * 
     * @param event-the ActionEvent which triggers this method
     */
    @FXML
    void addList(ActionEvent event) {
    	TextInputDialog textDialog = new TextInputDialog();
    	String listName = "";
    	boolean validInput;
       
    	do
        {
        	textDialog.getEditor().clear();
        	textDialog.setTitle("New List");
        	textDialog.setHeaderText("Please enter the new list name");
        	textDialog.setContentText("List name:");
        	textDialog.showAndWait();
        	listName = textDialog.getResult();
        	validInput = validateInput(listName);
    	}while(!validInput);
          //make a new button
          
          //add the String className to the classes ArrayList and to the classhash with the index
          toDoLists.add(listName);
          toDoHash.put(toDoLists.get(toDoLists.size()-1), toDoLists.size()-1);
          
          //make a new task class CheckList
          Label classLabel = new Label();
          //deal with css
          //classLabel.setStyle("-fx-text-fill:white");
          classLabel.setText(listName);
          //add this here, or is this part of parent ObservableList?
          ObservableList<CheckBox> checkList = FXCollections.observableArrayList();
          Separator separator = new Separator();
          VBox temp = new VBox();
          temp.setPadding(new Insets(10, 10, 0, 0));
          temp.getChildren().addAll(classLabel,separator);
          userLists.getChildren().add(temp);
          listsVBoxList.add(temp);
          
          HubEvent dummyEvent = new HubEvent(0, User.getUserID(), 3, new Date(1900,1,1,1,1) , User.getCurrentHub().getHubName(), "N/A", 
        		  new ArrayList<Contact>(), "N/A", new Date(1900,1,1,1,1));
          //use this class to set DB entry
          SchoolClass newClass = new SchoolClass(listName, "N/A", "N/A", new ArrayList<Task>(),dummyEvent , new ArrayList<Note>());
          User.addClass(newClass);
          
          //add the class to the User classes ArrayList
          
          User.getClasses().add(newClass);
          User.getCurrentHub();
    }
    
    /**Top menu item. Unused at this time.
     * 
     * @param event- the triggering event
     */
    @FXML
    void changeTheme(ActionEvent event) {

    }
    
    /**Top menu item. Unused at this time.
     * 
     * @param event- the triggering event
     */
    @FXML
    void close(ActionEvent event) {

    }

    /**Top menu item. Unused at this time.
     * 
     * @param event- the triggering event
     */
    @FXML
    void deleteHub(ActionEvent event) {

    }

    /**Top menu item. Unused at this time.
     * 
     * @param event- the triggering event
     */
    @FXML
    void goBack(ActionEvent event) {

    }

    /**Top menu item. Unused at this time.
     * 
     * @param event- the triggering event
     */
    @FXML
    void logout(ActionEvent event) {

    }
    
    /**This method accepts an ActionEvent, displays a ChoiceDialog of the LifeHub's Task lists,
     * then based on the user's selection, removes the list from the ObservableList
     * and the database, and returns nothing.
     * 
     * @param event-the ActionEvent which triggers this method
     */
    @FXML
    void deleteList(ActionEvent event) {
    	//Make a dropdown of which to delete, steal this from Ed controller
    	//User.getClasses().get((int) toDoHash.get(listName));
    	ArrayList<String> listNameStrings = new ArrayList<String>();
    	boolean validInput;
    	
    	for (int i = 0; i < User.getClasses().size(); i++)
    		listNameStrings.add(User.getClasses().get(i).getClassName());
    	ChoiceDialog<String> choicePopup = new ChoiceDialog<String>("Please select:", listNameStrings);
        
        String input = "";
        //choicePopup = new ChoiceDialog("Please select", classNameStrings);

        do
        {
            choicePopup.setTitle("Delete List");
            choicePopup.setHeaderText("Please select list to remove");
            choicePopup.setContentText("Use Dropdown menu:\n");
            choicePopup.showAndWait();
            input = choicePopup.getResult().toString();
            validInput = validateInput(input);
        }while(!validInput);
    	//match class to correct class object
        for (int i = 0; i < User.getClasses().size(); i++)
        {
        	if (User.getClasses().get(i).compareTo(input) > 0)
        	{
        		User.deleteClass(User.getClasses().get(i));
        		break;
        	}
        }
    }
    
    /**This method accepts an ActionEvent, displays a ChoiceDialog of the LifeHub's notes,
     * removes the note from the ArchivedNotes and the database based on the users selection,
     * and returns nothing.
     * 
     * @param event- the ActionEvent that triggers this method.
     */
    @FXML
    void deleteNote(ActionEvent event) {
    	boolean validInput;
    	boolean find = true;
    	int noteFound = -1;
    	ArrayList<String> noteStrings = new ArrayList<String>();
    	for (int i = 0; i < User.getCurrentHub().getNotes().size(); i++)
    		noteStrings.add(User.getCurrentHub().getNotes().get(i).getText());
    	ChoiceDialog<String> choicePopup = new ChoiceDialog<String>("Please select:", noteStrings);
        
        String input = "";
        //choicePopup = new ChoiceDialog("Please select", classNameStrings);

        do
        {
            choicePopup.setTitle("Delete note");
            choicePopup.setHeaderText("Please select note to remove");
            choicePopup.setContentText("Use Dropdown menu:\n");
            choicePopup.showAndWait();
            input = choicePopup.getResult().toString();
            validInput = validateInput(input);
        }while(!validInput);
        System.out.println(input);
        int count = User.getCurrentHub().getNotes().size();
        while (find)
		{
			if (count > 0)
			{
				noteFound=User.getCurrentHub().getNotes().get(--count).compareTo(input);
				//if valid hubType is returned, search is complete
				if (noteFound > 0)
					find = false;
			}
			//Ends search when all values have been checked
			else
				find = false;

		}
        if (noteFound > 0)
        	User.deleteArchivedNote(User.getCurrentHub().getNotes().get(count));
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
    	if (!note.getText().equals(""))
    	{
    		Note tempNote = new Note(note.getText());
	    	User.getCurrentHub().getNotes().add(tempNote);
	    	//Add the date to this as well. Wishlist make this a hyperlink with a popup
	    	Alert alert = getAlert("Display Note","Note",note.getText());
	    	Hyperlink tempLink = new Hyperlink(note.getText());
	    	tempLink.setOnAction(event2 ->{
	        	  alert.showAndWait();
	        	  
	          });
	        archivedNotes.getItems().add(tempLink);
	    	//Push to DB
	        User.addArchivedNote(tempNote);
    	}
    	note.clear();
    }

    /**Top menu item. Unused at this time.
     * 
     * @param event- the triggering event
     */
    @FXML
    void tutorial(ActionEvent event) {

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
   		//mainPane = FXMLLoader.load(getClass().getClassLoader().getResource("Classified.fxml"));// pane you are GOING TO
   		Scene scene = new Scene(mainPane);// pane you are GOING TO show
   		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
   		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
   		window.setScene(scene);
   		window.show();
	}
    
    /**This method accepts an ActionEvent, changes the view to the monthly Calendar, and 
     * returns nothing.
     * 
     * @param event- the button click that triggers this method call
     * @throws IOException- handles issues with the fxml documents.
     */
    @FXML
    void goToCalendar(ActionEvent event) throws IOException {
   	User.setLastHub(new File("src/application/view/PersonalHome.fxml").toURI().toURL());
    	URL url = new File("src/application/view/Calendar.fxml").toURI().toURL();
    	mainPane = FXMLLoader.load(url);
    	//mainPane = FXMLLoader.load(getClass().getClassLoader().getResource("Classified.fxml"));// pane you are GOING TO
        Scene scene = new Scene(mainPane);// pane you are GOING TO show
        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
        window.setScene(scene);
        window.show();
	    }
    
    /**This method accepts a String title, header, and content used to create an Alert pop-up
     * and returns the crafted Alert pop-up.
     * 
     * @param title -String to be use as the title
     * @param header -String to be use as the header
     * @param content -String to be use as the content
     * @return the Alert containing the title, header, and content
     */
    public Alert getAlert(String title, String header, String content)
    {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle(title);
    	alert.setHeaderText(header);
    	alert.setContentText(content);
    	
    	return alert;
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
