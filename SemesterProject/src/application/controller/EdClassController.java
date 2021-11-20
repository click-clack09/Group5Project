package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.stage.Stage;

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
    private ListView<Hyperlink> archivedClassNotes;
    
    private ObservableList<Hyperlink> archivedNoteList = FXCollections.observableArrayList();
    private ObservableList<CheckBox> toDoVBoxList = FXCollections.observableArrayList();
    private int index;
	
  //This goes with item/task delete  deleteTask(Task task, String className)
    
    @FXML
    void initialize()
    {
    	
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
    
	@FXML
    void addHub(ActionEvent event) {
    	System.out.println("testAddHub");
    }
	
	
	
	@FXML
    void changeTheme(ActionEvent event) {
    	System.out.println("testChangeTheme");
    }
    
	 @FXML
	    void tutorial(ActionEvent event) {
		 System.out.println("testTutorial");
	    }
	 
	 @FXML
	 void addItem(ActionEvent event) {
	 	System.out.println("testAddItem");
	 	TextInputDialog textDialog = new TextInputDialog();
	 	String className = User.getCurrentClass();
		String taskString = "";
		boolean validInput;
		
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
	 
	 
	 
	 @FXML
	    void about(ActionEvent event) {
		 System.out.println("testAbout");
	    }
	 
	    @FXML
	    void logout(ActionEvent event) {
	    	System.out.println("testLogout");
	    }
	    
    @FXML
    void close(ActionEvent event) {
    	System.out.println("testClose");
    }

    @FXML
    void deleteHub(ActionEvent event) {
    	System.out.println("testDelete");
    }
    
    @FXML
    void goBack(ActionEvent event) throws IOException {
   		mainPane = FXMLLoader.load(User.getLastHub());
    	User.setLastHub(new File("src/application/view/EdClass.fxml").toURI().toURL());
    	Scene scene = new Scene(mainPane);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
	    window.setScene(scene);
	    window.show();
	}

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
	    	//Push to DB
	        User.addArchivedNote(tempNote);
    	}
    	classNotes.clear();
    }

    public Alert getAlert(String title, String header, String content)
    {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle(title);
    	alert.setHeaderText(header);
    	alert.setContentText(content);
    	
    	return alert;
    }

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
    
    @FXML
    void deleteNote(ActionEvent event) {
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
	        	User.deleteArchivedNote(User.getCurrentHub().getClasses().get(countClass).getNotes().get(countNote));
        }
    }
    
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
