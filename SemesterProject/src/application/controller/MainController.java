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

/**This class controls the login page for the LifeHub application.
 * 
 * @author Group 5 11-23-21
 *
 */
public class MainController {

	@FXML
	 private AnchorPane mainPane;
	
    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;

    @FXML
    private Label loginError;

    /**Sets all static user variables to null or 0, effectively clearing the User data and 
     * prepping the application for the next user.
     * 
     */
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
    
    /**Top menu item. Unused at this time.
     * 
     * @param event- the triggering event
     */
    @FXML
    void about(ActionEvent event) {
    	System.out.println("testAbout");
    }

    /**Top menu item. Unused at this time.
     * 
     * @param event- the triggering event
     */
    @FXML
    void addHub(ActionEvent event) {
    	System.out.println("testAddHub");
    	//KILL THIS
    }
    
    /**Top menu item. Unused at this time.
     * 
     * @param event- the triggering event
     */
    @FXML
    void close(ActionEvent event) {
    	//System.out.println("testClose");
    }

    /**This method accepts an ActionEvent and attempts to create a new user log-in,
     * succeeding if the username and password are acceptable, failing if not. Sets
     * label text in the event of failure, logs the user in in the case of success.
     * Returns nothing.
     * 
     * @param event- The button click which triggered the event.
     */
    @FXML
    void create(ActionEvent event) {
    	loginError.setText("");
    	boolean wentThrough;
    	String uName =userName.getText();
    	String pw = password.getText();
    	if (!uName.equals("") && !pw.equals(""))
    	{
    		User newUser = new User(uName,pw);
    		wentThrough = newUser.addUser();
    	if (!wentThrough)
    	{
    		loginError.setText("Create failed. Please try again");
    	}
    	else
			login(event);
    	}
    	else
    		loginError.setText("Username cannot be empty. Please try again");
    }

    /**This method accepts an ActionEvent and attempts to log the user in with the 
     * provided credentials, succeeding if the username and password match a found 
     * record, failing if not. Sets label text in the event of failure, logs the 
     * user in in the case of success. Returns nothing.
     * 
     * @param event
     */
    @FXML
    void login(ActionEvent event) {
    	loginError.setText("");
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
    		else
    			loginError.setText("Login failed. Please try again");
    	}
    	catch (IOException e)
    	{
    		//print to error label
    	}
    	/*Need to verify that user is valid.
    	 * -Pass username and password to validation method which will make a call to the DB
    	 * -if valid:
    	 * 		instantiate user
    	 * 		proceed to next scene*/
    }

    /**Top menu item. Unused at this time.
     * 
     * @param event- the triggering event
     */
    @FXML
    void tutorial(ActionEvent event) {
    	//System.out.println("testTutorial");
    	/*wishlist item, add later if time*/
    }

}
