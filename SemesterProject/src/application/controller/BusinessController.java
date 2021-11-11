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
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BusinessController {
	
	@FXML
	 private AnchorPane mainPane;

    @FXML
    private Label businessHomeLabel;
    
    @FXML
    private VBox toDoList;

    @FXML
    private ListView<String> contactList;

    @FXML
    private TextArea notes;

    @FXML
    private ListView<String> archivedNotes;
    
    @FXML
    void initialize()
    {
    	
    	businessHomeLabel.setText(User.getUserName()+", "+User.getCurrentHub().getHubName());
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
    void addContact(ActionEvent event) {

    }

    @FXML
    void addItem(ActionEvent event) {

    }
    
     @FXML
     void goToCalendar(ActionEvent event) throws IOException {
    	User.setLastHub(new File("src/application/view/BusinessHome.fxml").toURI().toURL());
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
    void removeContact(ActionEvent event) {
    	
    }

    @FXML
    void saveNote(ActionEvent event) {

    }

    

    @FXML
    void userHome(ActionEvent event) throws IOException {
    	User.setLastHub(new File("src/application/view/BusinessHome.fxml").toURI().toURL());
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
