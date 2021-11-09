package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import application.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
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
    void forgot(ActionEvent event) {
    	//System.out.println("testForgot");
    	/*Need to verify that user is valid.
    	 * -Pass username to forgot method which will make a call to the DB
    	 * -if valid:
    	 * 		instantiate user
    	 * 		proceed to next scene*/
    }

    @FXML
    void login(ActionEvent event) {
    	try
    	{
    		if (User.validate(userName.getText(),password.getText()))
    	
	    	{
	    		User.setLast(new File("src/application/view/Login.fxml").toURI().toURL());
	    		User.setUsername(userName.getText());
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
