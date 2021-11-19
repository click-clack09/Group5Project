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

public class PersonalController {

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
    
    @FXML 
    void initialize() {
    	
    	toDoLists = new ArrayList<String>();
		
    	personalLabel.setText(User.getUserName()+", "+User.getCurrentHub().getHubName());
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
    		//Add the date to this as well. Wishlist make this a hyperlink with a popup
    		archivedNotes.getItems().addAll(archivedNoteList);
    		
    		//probably should consider how to delete notes
    		
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
    	                //add HashMap Entry for task text and className (String)
    	                taskHash.put(cb.getText(), toDoLists.get(i));
    	            }
    	            
    	            //only add tempInner when adding new class, otherwise reference it
    	            VBox tempInner = new VBox();
    	            tempInner.getChildren().addAll(checkList);
    	            temp.getChildren().addAll(toDoListLabel,tempInner,separator);
    	            userLists.getChildren().add(temp);
    	            listsVBoxList.add(temp);
    			}
    		}
    	}
    }
    @FXML
    void about(ActionEvent event) {

    }

    @FXML
    void addItem(ActionEvent event) {
    	
    	//This is added to allow for new classes to be added on the fly
    	//classes.add(0,"New Class");	
    	ChoiceDialog<String> selection = new ChoiceDialog<String>("Select List",toDoLists);
    	TextInputDialog textDialog = new TextInputDialog();
    	String listName = "";
    	String taskString = "";
    	
    	selection.setTitle("New Task");
    	selection.setHeaderText("Which list is the task for?");
    	selection.setContentText("List:");
      	selection.showAndWait();
        listName = selection.getResult();
        //remove it here though to not disrupt indexing
      	//classes.remove("New Class");
      	//if className.equals("New Class"); <------------deal with this
        
      	textDialog.getEditor().clear();
      	textDialog.setTitle("New Task");
      	textDialog.setHeaderText("Please enter the To-Do item");
      	textDialog.setContentText("Task:");
      	textDialog.showAndWait();
      	taskString = textDialog.getResult();
      	
      	//add HashMap Entry for task text and className (String)
      	taskHash.put(taskString, listName);
      	
      	Task tempTask = new Task(taskString);
      	//Add task to class
      	User.getClasses().get((int) toDoHash.get(listName)).getAssignments().add(tempTask);
      	
      	//deal with css
		//add this here, or is this part of parent ObservableList?
        CheckBox cb = new CheckBox(taskString);
        //cb.setStyle("-fx-text-fill:white");
        cb.setPadding(new Insets(10, 10, 0, 0));
        
        //This adds it to the appropriate observable VBox
        int VBoxIndex = (int) toDoHash.get(listName);
        listsVBoxList.get(VBoxIndex).getChildren().add(cb);
        
        User.addTask(tempTask, listName);
      	
    }
    
    @FXML
    void addHub(ActionEvent event) {
    	System.out.println("testAddHub");
    }

    @FXML
    void addList(ActionEvent event) {
//////////////////
    	TextInputDialog textDialog = new TextInputDialog();
    	String listName = "";
    	//while(!validInput)
          //{
      	 
    	  //this will need input validation, particularly for "New Class"
          textDialog.getEditor().clear();
          textDialog.setTitle("New List");
          textDialog.setHeaderText("Please enter the new list name");
          textDialog.setContentText("List name:");
          textDialog.showAndWait();
          
          //make a new button
          listName = textDialog.getResult();
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
        //SCHOOL_CLASS_ID	USER_ID	SCHOOL_CLASS_NAME	SCHOOL_CLASS_LOCATION	SCHOOL_CLASS_PROFESSOR
          User.addClass(newClass);
          
        //add the class to the User classes ArrayList
          //SchoolClass tempClass =new SchoolClass(className,professor,location, new ArrayList<Task>(),new HubEvent(), new ArrayList<Task>());
          
          User.getClasses().add(newClass);
          User.getCurrentHub();
         
          
//          System.out.println(newClass.getProfessor()+" "+newClass.getLocation()+" "+newClass.getMeetingTime());
          
    
    	//////////
    }

    @FXML
    void changeTheme(ActionEvent event) {

    }

    @FXML
    void close(ActionEvent event) {

    }

    @FXML
    void deleteHub(ActionEvent event) {

    }

    @FXML
    void goBack(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) {

    }

    @FXML
    void saveNote(ActionEvent event) {
    	//archivedNoteList.add(User.getClasses().get(index).getNotes().get(i).getText());
    	//Add a note to the class notes
    	User.getCurrentHub().getNotes().add(new Note(note.getText()));
    	//Add the date to this as well. Wishlist make this a hyperlink with a popup
    	Alert alert = getAlert("Display Note","Note",note.getText());
    	Hyperlink tempLink = new Hyperlink(note.getText());
    	tempLink.setOnAction(event2 ->{
        	  alert.showAndWait();
        	  
          });
        archivedNotes.getItems().add(tempLink);
    	//Push to DB
    	note.clear();
    }

    @FXML
    void selectList(ActionEvent event) {

    }

    @FXML
    void tutorial(ActionEvent event) {

    }

    @FXML
    void userHome(ActionEvent event) throws IOException {
    	URL url = new File("src/application/view/UserHome.fxml").toURI().toURL();
   		mainPane = FXMLLoader.load(url);
   		//mainPane = FXMLLoader.load(getClass().getClassLoader().getResource("Classified.fxml"));// pane you are GOING TO
   		Scene scene = new Scene(mainPane);// pane you are GOING TO show
   		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
   		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
   		System.out.println("5");
   		window.setScene(scene);
   		System.out.println("6");
	    window.show();
	}
    
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
    
    public Alert getAlert(String title, String header, String content)
    {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle(title);
    	alert.setHeaderText(header);
    	alert.setContentText(content);
    	
    	return alert;
    }

}
