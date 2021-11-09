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
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PersonalController {

	@FXML
	 private AnchorPane mainPane;
	
    @FXML
    void about(ActionEvent event) {

    }

    @FXML
    void addItem(ActionEvent event) {

    }
    
    @FXML
    void addHub(ActionEvent event) {
    	System.out.println("testAddHub");
    }

    @FXML
    void addList(ActionEvent event) {

    }

    @FXML
    void changeTheme(ActionEvent event) {

    }

    @FXML
    void close(ActionEvent event) {

    }

    @FXML
    void deleteHub(ActionEvent event) {

    }

    @FXML
    void goBack(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) {

    }

    @FXML
    void saveNote(ActionEvent event) {

    }

    @FXML
    void selectList(ActionEvent event) {

    }

    @FXML
    void tutorial(ActionEvent event) {

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
    
    @FXML
    void goToCalendar(ActionEvent event) throws IOException {
   	User.setLast(new File("src/application/view/PersonalHome.fxml").toURI().toURL());
    	URL url = new File("src/application/view/Calendar.fxml").toURI().toURL();
    	mainPane = FXMLLoader.load(url);
    	//mainPane = FXMLLoader.load(getClass().getClassLoader().getResource("Classified.fxml"));// pane you are GOING TO
        Scene scene = new Scene(mainPane);// pane you are GOING TO show
        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
        window.setScene(scene);
        window.show();
	    }

}
