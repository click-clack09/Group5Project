package application.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import application.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserController {
	@FXML
	 private AnchorPane mainPane;
	
	@FXML
    private ChoiceBox<?> selection;
	
	
	@FXML
		void initialize()
	{
		ArrayList userHubs = new ArrayList();
		//for testing only. Will read from user data
		userHubs.add("EducationHome");
		userHubs.add("BusinessHome");
		userHubs.add("PersonalHome");
		selection.getItems().addAll(userHubs);
	}
	
	@FXML
    void addHub(ActionEvent event) {
    	System.out.println("testHub");
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
    void goTo(ActionEvent event) {
    	try
    	{
    		String userChoice = selection.getSelectionModel().getSelectedItem().toString();
    		URL url = new File("src/"+userChoice+".fxml").toURI().toURL();
    		User.setLast(new File("src/UserHome.fxml").toURI().toURL());
        	mainPane = FXMLLoader.load(url);
        	//mainPane = FXMLLoader.load(getClass().getClassLoader().getResource("Classified.fxml"));// pane you are GOING TO
            Scene scene = new Scene(mainPane);// pane you are GOING TO show
            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
            window.setScene(scene);
            window.show();
    	}
    	catch (IOException e)
    	{
    		//popup error window
    	}
    }

    @FXML
    void newBusiness(ActionEvent event) {

    }

    @FXML
    void newEducation(ActionEvent event) {

    }

    @FXML
    void newPersonal(ActionEvent event) {

    }

    

}
