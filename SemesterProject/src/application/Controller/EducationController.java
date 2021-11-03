package application.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import application.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EducationController {
	 @FXML
	 private AnchorPane mainPane;
	 
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
	    	System.out.println("testAddClass");
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
		User.setLast(new File("src/EducationHome.fxml").toURI().toURL());
    	URL url = new File("src/EdClass.fxml").toURI().toURL();
    	mainPane = FXMLLoader.load(url);
        Scene scene = new Scene(mainPane);// pane you are GOING TO show
        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
        window.setScene(scene);
        window.show();
    }
	 
	 @FXML
	    void goToCalendar(ActionEvent event) throws IOException {
		User.setLast(new File("src/EducationHome.fxml").toURI().toURL());
    	URL url = new File("src/Calendar.fxml").toURI().toURL();
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
	    	URL url = new File("src/UserHome.fxml").toURI().toURL();
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