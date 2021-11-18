package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import application.model.Contact;
import application.model.HubEvent;
import application.model.LifeHub;
import application.model.SchoolClass;
import application.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController {

	@FXML
	 private AnchorPane mainPane;
	
    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;

    @FXML
    private Label loginError;

    @FXML
    void initialize()
    {
    	//clear user data
    	User.setLastHub(null);
    	User.setUserName(null);
    	User.setUserID(0);
    	User.setCurrentHub(null);
    	User.setUserHubs(null);
    	User.setCurrentClass(null);
    	User.setUserContacts(null);
    	User.setUserEvents(null);
    	User.setClasses(null);
    	
    	
    }
    
    @FXML
    void about(ActionEvent event) {
    	System.out.println("testAbout");
    }

    @FXML
    void addHub(ActionEvent event) {
    	System.out.println("testAddHub");
    	//KILL THIS
    }
    
    @FXML
    void close(ActionEvent event) {
    	//System.out.println("testClose");
    }

    @FXML
    void create(ActionEvent event) {
    	loginError.setText("");
    	boolean wentThrough;
    	User newUser = new User(userName.getText(),password.getText());
    	wentThrough = newUser.addUser();
    	if (!wentThrough)
    	{
    		loginError.setText("Create failed. Please try again");
    	}
    	else
			login(event);
    }

    @FXML
    void login(ActionEvent event) {
    	try
    	{
    		if (User.validate(userName.getText(),password.getText()))
    	
	    	{
	    		User.setLastHub(new File("src/application/view/Login.fxml").toURI().toURL());
	    		User.setUserName(userName.getText());
	        	URL url = new File("src/application/view/UserHome.fxml").toURI().toURL();
	        	mainPane = FXMLLoader.load(url);
	            Scene scene = new Scene(mainPane);// pane you are GOING TO show
	            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
	            window.setScene(scene);
	            window.show();
	    	}
    	}
    	catch (IOException e)
    	{
    		//print to error label
    	}
    	//System.out.println("testLogin");
    	/*Need to verify that user is valid.
    	 * -Pass username and password to validation method which will make a call to the DB
    	 * -if valid:
    	 * 		instantiate user
    	 * 		proceed to next scene*/
    }

    @FXML
    void tutorial(ActionEvent event) {
    	//System.out.println("testTutorial");
    	/*wishlist item, add later if time*/
    }

}
