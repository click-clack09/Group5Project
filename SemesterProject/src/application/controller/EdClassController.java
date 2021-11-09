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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EdClassController {
	
	@FXML
	private AnchorPane mainPane;
	
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
    	mainPane = FXMLLoader.load(User.getLast());
    	User.setLast(new File("src/application/view/EdClass.fxml").toURI().toURL());
    	//mainPane = FXMLLoader.load(getClass().getClassLoader().getResource("Classified.fxml"));// pane you are GOING TO
   		Scene scene = new Scene(mainPane);// pane you are GOING TO show
        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
   		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
	    window.setScene(scene);
	    window.show();
	}

    

    @FXML
    void saveNote(ActionEvent event) {

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
