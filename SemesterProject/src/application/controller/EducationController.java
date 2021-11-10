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
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EducationController {
	 @FXML
	 private AnchorPane mainPane;
	 
	 @FXML
    private Label educationLabel;
	 
	 @FXML
    private VBox classButtonBox;
	 
	 //private ArrayList<Button> classButtonList;
	 private ObservableList<Button> classButtonList = FXCollections.observableArrayList();
	 
	 @FXML
    void initialize()
    {
    	//this will instantiate the LifeHub object based on the DB query
    	//pull all tasks, events, notes and classes associated with the User.getCurrentHub() String
		//use the listed to set the ObservableList<Button>
		classButtonBox.getChildren().clear();
		//get the SchoolClasses, make a array list of them. This will let us pull their names, tasks, etc 
		
		classButtonBox.getChildren().addAll(classButtonList);
    	LifeHub currentHub = new LifeHub(User.getCurrentHub());
    	educationLabel.setText(User.getUsername()+", "+User.getCurrentHub());
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
              
              className = textDialog.getResult();
              textDialog.getEditor().clear();
              textDialog.setTitle("New Class");
              textDialog.setHeaderText("Please enter the new class location");
              textDialog.setContentText("Location:");
              textDialog.showAndWait();
              location = textDialog.getResult();
              
              className = textDialog.getResult();
              textDialog.getEditor().clear();
              textDialog.setTitle("New Class");
              textDialog.setHeaderText("Please enter the new class instructor");
              textDialog.setContentText("Instructor:");
              textDialog.showAndWait();
              
              HubEvent meetingTime = new HubEvent(); 
              professor = textDialog.getResult();
              SchoolClass newClass = new SchoolClass(professor, location, new ArrayList<Task>(), meetingTime);
            //SCHOOL_CLASS_ID	USER_ID	SCHOOL_CLASS_NAME	SCHOOL_CLASS_LOCATION	SCHOOL_CLASS_PROFESSOR
             Button button = new Button();
              button.setText(className);
              classButtonList.add(button);
              classButtonBox.getChildren().clear();
      		classButtonBox.getChildren().addAll(classButtonList);
//              System.out.println(newClass.getProfessor()+" "+newClass.getLocation()+" "+newClass.getMeetingTime());
              
	    }

	    @FXML
	    void addItem(ActionEvent event) {
	    	System.out.println("testAddItem");
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
		User.setLast(new File("src/application/view/EducationHome.fxml").toURI().toURL());
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
		User.setLast(new File("src/application/view/EducationHome.fxml").toURI().toURL());
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
}