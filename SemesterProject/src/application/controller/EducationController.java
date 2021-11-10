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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Separator;

public class EducationController {
	 @FXML
	 private AnchorPane mainPane;
	 
	 @FXML
    private Label educationLabel;
	 
	 @FXML
    private VBox classButtonBox;
	 
     @FXML
     private VBox toDoList;
	 
	 //private ArrayList<Button> classButtonList;
	 private ObservableList<Button> classButtonList = FXCollections.observableArrayList();
	 private ObservableList<VBox> classesList = FXCollections.observableArrayList();
	 //watch VBox. Add class to observable classesList. with each class add, add an observable ToDoList in a VBox
	 //The part below gets dynamically added to the parent classesList
	 //private ObservableList<CheckBox> classToDoList = FXCollections.observableArrayList();
	 
	 @FXML
    void initialize()
    {
		 educationLabel.setText(User.getUserName()+", "+User.getCurrentHub());
    	//this will instantiate the LifeHub object based on the DB query
    	//pull all tasks, events, notes and classes associated with the User.getCurrentHub() String
		//use the listed to set the ObservableList<Button>

//		 classButtonBox.getChildren().clear();

		//get the SchoolClasses fromDB, make a array list of them. This will let us pull their names, tasks, etc 
		classButtonBox.getChildren().addAll(classButtonList);
    	LifeHub currentHub = new LifeHub(User.getCurrentHub());
    	
    	for (int i =0; i <classButtonList.size(); i++)
    	{
    		
    		classButtonList.get(i).setOnAction(event ->{
    			try
    			{
    			User.setLastHub(new File("src/application/view/EducationHome.fxml").toURI().toURL());
    			URL url = new File("src/application/view/EdClass.fxml").toURI().toURL();
    	    	mainPane = FXMLLoader.load(url);
    	        Scene scene = new Scene(mainPane);// pane you are GOING TO show
    	        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    	        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
    	        window.setScene(scene);
    	        window.show();
    			}
    			catch(IOException e)
    			{
    				
    			}
    		});
    	}
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
	    void addClass(ActionEvent event) {
	    	TextInputDialog textDialog = new TextInputDialog();
	    	String className = "";
	    	String location = "";
	    	String professor = "";
	      	//while(!validInput)
	          //{
	      	 		
              textDialog.getEditor().clear();
              textDialog.setTitle("New Class");
              textDialog.setHeaderText("Please enter the new class name");
              textDialog.setContentText("Class name:");
              textDialog.showAndWait();
              
              //make a new button
              className = textDialog.getResult();
              Button button = new Button();
              button.setText(className);
              classButtonList.add(button);
              
              //make a new task class CheckList
              Label classLabel = new Label();
              //deal with css
              classLabel.setText(className);
              //add this here, or is this part of parent ObservableList?
              ObservableList<CheckBox> checkList = FXCollections.observableArrayList();
              Separator separator = new Separator();
              VBox temp = new VBox();
              temp.getChildren().addAll(classLabel,separator);
              toDoList.getChildren().add(temp);
              classesList.add(temp);
              
              textDialog.getEditor().clear();
              textDialog.setTitle("New Class");
              textDialog.setHeaderText("Please enter the new class location");
              textDialog.setContentText("Location:");
              textDialog.showAndWait();
              location = textDialog.getResult();
              
              textDialog.getEditor().clear();
              textDialog.setTitle("New Class");
              textDialog.setHeaderText("Please enter the new class instructor");
              textDialog.setContentText("Instructor:");
              textDialog.showAndWait();
              
              HubEvent meetingTime = new HubEvent(); 
              professor = textDialog.getResult();
              System.out.println(className+" "+location+" "+professor);
              
              //use this class to set DB entry
              SchoolClass newClass = new SchoolClass(className, professor, location, new ArrayList<Task>(), meetingTime);
            //SCHOOL_CLASS_ID	USER_ID	SCHOOL_CLASS_NAME	SCHOOL_CLASS_LOCATION	SCHOOL_CLASS_PROFESSOR
             
              classButtonList.get(classButtonList.size()-1).setOnAction(event1 ->{
	        	  try
	        	  {
	        		  setCallingClass(event1.getSource().toString());
	        		  User.setLastHub(new File("src/application/view/EducationHome.fxml").toURI().toURL());          			
	        		  URL url = new File("src/application/view/EdClass.fxml").toURI().toURL();
	        		  mainPane = FXMLLoader.load(url);
	        		  Scene scene = new Scene(mainPane);// pane you are GOING TO show
	        		  //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	        		  Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
	        		  window.setScene(scene);
	        		  window.show();
	        	  }
	        	  catch(IOException e)
	        	  {
	        		  //deal with this later
	        	  }
              });
              classButtonBox.getChildren().clear();
      		classButtonBox.getChildren().addAll(classButtonList);
//              System.out.println(newClass.getProfessor()+" "+newClass.getLocation()+" "+newClass.getMeetingTime());
              
	    }
	    
	    public void setCallingClass(String caller)
	    {
	    	String[] buttonSplit;
	    	buttonSplit=caller.split("\'");
  			System.out.println(buttonSplit[1]);
	    	User.setCurrentClass(buttonSplit[1]);
	    }

	    //This will add a task
	    @FXML
	    void addItem(ActionEvent event) {
	    	TextInputDialog textDialog = new TextInputDialog();
	    	String className = "";
	    	String location = "";
	    	String professor = "";
	      	textDialog.getEditor().clear();
	      	textDialog.setTitle("New Task");
	      	textDialog.setHeaderText("Which class is the task for?");
	      	textDialog.setContentText("Class name:");
	      	textDialog.showAndWait();
              
	      	className = textDialog.getResult();
	      	for (int i = 0; i <classesList.size(); i++)
	      	{
	      		if (classesList.get(i).getChildren().contains(className))
	      		{
	      			System.out.println(className+" found in vbox "+i);
	      		}
	      	}
	      	
	    }
	    @FXML
	    void addHub(ActionEvent event) {
	    	System.out.println("testHub");
	    }
	    
	    @FXML
	    void goToClass2(ActionEvent event) {
	    	System.out.println("testClass2");
	    }
	    	
	 @FXML
	    void goToClass1(ActionEvent event) throws IOException {
		 System.out.println("testClass1");
		User.setLastHub(new File("src/application/view/EducationHome.fxml").toURI().toURL());
    	URL url = new File("src/application/view/EdClass.fxml").toURI().toURL();
    	mainPane = FXMLLoader.load(url);
        Scene scene = new Scene(mainPane);// pane you are GOING TO show
        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
        window.setScene(scene);
        window.show();
    }
	 
	 @FXML
	    void goToCalendar(ActionEvent event) throws IOException {
		User.setLastHub(new File("src/application/view/EducationHome.fxml").toURI().toURL());
    	URL url = new File("src/application/view/Calendar.fxml").toURI().toURL();
    	mainPane = FXMLLoader.load(url);
    	//mainPane = FXMLLoader.load(getClass().getClassLoader().getResource("Classified.fxml"));// pane you are GOING TO
        Scene scene = new Scene(mainPane);// pane you are GOING TO show
        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
        window.setScene(scene);
        window.show();
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
	 
	 //could move stuff from lambda expression hear for cleaner flow
//	 void goToClass(ActionEvent event)
//	 {
//		 
//	 }
}