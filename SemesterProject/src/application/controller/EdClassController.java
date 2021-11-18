package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;

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
		
		/////////
		textDialog.getEditor().clear();
      	textDialog.setTitle("New Task");
      	textDialog.setHeaderText("Please enter the To-Do item");
      	textDialog.setContentText("Task:");
      	textDialog.showAndWait();
      	taskString = textDialog.getResult();
      	
      	//Add task to class. Need to pass class index
      	User.getClasses().get(index).getAssignments().add(new Task(taskString));
      	
      	//deal with css
		//add this here, or is this part of parent ObservableList?
        CheckBox cb = new CheckBox(taskString);
        //cb.setStyle("-fx-text-fill:white");
        cb.setPadding(new Insets(10, 10, 0, 0));
        
        //This adds it to the appropriate observable VBox
       
        toDoVBoxList.add(cb);
        //toDoVBoxList.add(cb);
	 	//classToDo.getChildren().addAll(toDoVBoxList);
        classToDo.getChildren().clear();
        classToDo.getChildren().addAll(toDoVBoxList);
		
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
   		//URL url = new File("src/application/view/EducationHome.fxml").toURI().toURL();
   		//mainPane = FXMLLoader.load(url);
    	mainPane = FXMLLoader.load(User.getLastHub());
    	User.setLastHub(new File("src/application/view/EdClass.fxml").toURI().toURL());
    	//mainPane = FXMLLoader.load(getClass().getClassLoader().getResource("Classified.fxml"));// pane you are GOING TO
   		Scene scene = new Scene(mainPane);// pane you are GOING TO show
        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
   		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
	    window.setScene(scene);
	    window.show();
	}

    

    @FXML
    void saveNote(ActionEvent event) {
    	//archivedNoteList.add(User.getClasses().get(index).getNotes().get(i).getText());
    	//Add a note to the class notes
    	User.getClasses().get(index).getNotes().add(new Note(classNotes.getText()));
    	//Add the date to this as well. Wishlist make this a hyperlink with a popup
    	Alert alert = getAlert("Display Note","Note",classNotes.getText());
    	Hyperlink tempLink = new Hyperlink(classNotes.getText());
    	tempLink.setOnAction(event2 ->{
        	  alert.showAndWait();
        	  
          });
        archivedClassNotes.getItems().add(tempLink);
    	//Push to DB
    	classNotes.clear();
    }
    
    
//    void saveNote(ActionEvent event) {
//    	
//    	//archivedNoteList.add(User.getClasses().get(index).getNotes().get(i).getText());
//    	//Add a note to the class notes
//    	User.getClasses().get(index).getNotes().add(new Note(classNotes.getText()));
//    	//Add the date to this as well. Wishlist make this a hyperlink with a popup
//    	archivedClassNotes.getItems().add(classNotes.getText());
//    	//archivedClassNotes.getItems().addAll(archivedNoteList);
//    	classNotes.clear();
//
//    }

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
   		//mainPane = FXMLLoader.load(getClass().getClassLoader().getResource("Classified.fxml"));// pane you are GOING TO
   		Scene scene = new Scene(mainPane);// pane you are GOING TO show
   		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
   		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
   		System.out.println("5");
   		window.setScene(scene);
   		System.out.println("6");
	    window.show();
	}
    
}
