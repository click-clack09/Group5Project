package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import application.model.LifeHub;
import application.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CalendarController {
	
	@FXML
	 private AnchorPane mainPane;

    @FXML
    private Label calendarLabel;
    
    @FXML
    void initialize()
    {
    	//this will instantiate the LifeHub object based on the DB query
    	//pull all tasks, events and notes associated with the User.getUserID() int
    	calendarLabel.setText(User.getUsername()+", Monthly Calendar");
    }
    
    @FXML
    void changeTheme(ActionEvent event) {
    	System.out.println("testChangeTheme");
    }
    
    @FXML
    void addHub(ActionEvent event) {
    	System.out.println("testAddHub");
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
    void addEvent(ActionEvent event) {

    }

    @FXML
    void deleteEvent(ActionEvent event) {

    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
   		//URL url = new File("src/EducationHome.fxml").toURI().toURL();
    	mainPane = FXMLLoader.load(User.getLast());
    	User.setLast(new File("src/application/view/Calendar.fxml").toURI().toURL());
    	//mainPane = FXMLLoader.load(getClass().getClassLoader().getResource("Classified.fxml"));// pane you are GOING TO
   		Scene scene = new Scene(mainPane);// pane you are GOING TO show
        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
   		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
	    window.setScene(scene);
	    window.show();
	}

    @FXML
    void goHome(ActionEvent event) throws IOException {
   		URL url = new File("src/application/view/UserHome.fxml").toURI().toURL();
   		mainPane = FXMLLoader.load(url);
   		//mainPane = FXMLLoader.load(getClass().getClassLoader().getResource("Classified.fxml"));// pane you are GOING TO
   		Scene scene = new Scene(mainPane);// pane you are GOING TO show
        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
   		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
	    window.setScene(scene);
	    window.show();
	}

}
