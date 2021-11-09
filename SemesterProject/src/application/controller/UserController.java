package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import application.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextInputDialog;
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
		//will return the event type use this to navigate to the correct hub view
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
    		//temporary solution, will pull from DB to populate list. this will pass hub name
    		String hubView = userChoice;
    		//this will be a switch, get the int Hub type for the Hub
    		//Hub (1 school, 3 personal, or 2 business)
    		int hubType = 0;
    		switch (hubType)
    		{
    			case 1:
    				hubView="EducationHome";
    				break;
    			case 2:
    				hubView="BusinessHome";
    				break;
    			case 3:
    				hubView="PersonalHome";
    				break;
    			default:
    				break;
    		}
    		URL url = new File("src/application/view/"+hubView+".fxml").toURI().toURL();
    		User.setLast(new File("src/application/view/UserHome.fxml").toURI().toURL());
    		User.setCurrentHub(userChoice);
        	mainPane = FXMLLoader.load(url);
        	Scene scene = new Scene(mainPane);// pane you are GOING TO show
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
    	 TextInputDialog textDialog = new TextInputDialog();
    	 String hubName = "";
    	//while(!validInput)
        //{
            textDialog.getEditor().clear();
            textDialog.setTitle("New Business Hub");
            textDialog.setHeaderText("Please enter the new hub name");
            textDialog.setContentText("Hub name:");
            textDialog.showAndWait();
            hubName = textDialog.getResult();
            //pull next index from database
            //index will be used to populate all events, lists, etc on the page
            //any calendar visible on the hub page will only be from this hub
            //when switching to calendar view, ALL of the users events be added to the calendar
            try
        	{
        		URL url = new File("src/application/view/BusinessHome.fxml").toURI().toURL();
        		User.setLast(new File("src/application/view/UserHome.fxml").toURI().toURL());        		
            	User.setCurrentHub(hubName);
        		mainPane = FXMLLoader.load(url);
            	//use FXMLloader to pass all user data to next controller
            	Scene scene = new Scene(mainPane);// pane you are GOING TO show
                Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
                window.setScene(scene);
                window.show();
        	}
        	catch (IOException e)
        	{
        		//popup error window
        	}   
            //validInput = textInputChecker(tempUser,0);
        //}
    }

    @FXML
    void newEducation(ActionEvent event) {
    	TextInputDialog textDialog = new TextInputDialog();
   	 String hubName = "";
   	//while(!validInput)
       //{
		   //pull next index from database
	       //index will be used to populate all events, lists, etc on the page
           //any calendar visible on the hub page will only be from this hub
	       //when switching to calendar view, ALL of the users events be added to the calendar
		     
           textDialog.getEditor().clear();
           textDialog.setTitle("New Education Hub");
           textDialog.setHeaderText("Please enter the new hub name");
           textDialog.setContentText("Hub name:");
           textDialog.showAndWait();
           hubName = textDialog.getResult();
           try
       	{
       		URL url = new File("src/application/view/EducationHome.fxml").toURI().toURL();
       		User.setLast(new File("src/application/view/UserHome.fxml").toURI().toURL());
       		User.setCurrentHub(hubName);
       		//use FXMLloader to pass all user data to next controller
           	mainPane = FXMLLoader.load(url);
           	   Scene scene = new Scene(mainPane);// pane you are GOING TO show
               Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
               window.setScene(scene);
               window.show();
       	}
       	catch (IOException e)
       	{
       		//popup error window
       	} 
           //validInput = textInputChecker(tempUser,0);
       //}
    }

    @FXML
    void newPersonal(ActionEvent event) {
    	TextInputDialog textDialog = new TextInputDialog();
   	 String hubName = "";
   	//while(!validInput)
       //{
   	 		//pull next index from database
   	 		//index will be used to populate all events, lists, etc on the page
   	 		//any calendar visible on the hub page will only be from this hub
   	 		//when switching to calendar view, ALL of the users events be added to the calendar
     
           textDialog.getEditor().clear();
           textDialog.setTitle("New Personal Hub");
           textDialog.setHeaderText("Please enter the new hub name");
           textDialog.setContentText("Hub name:");
           textDialog.showAndWait();
           hubName = textDialog.getResult();
           try
       	{
       		URL url = new File("src/application/view/PersonalHome.fxml").toURI().toURL();
       		User.setLast(new File("src/application/view/UserHome.fxml").toURI().toURL());
       		User.setCurrentHub(hubName);
       	//use FXMLloader to pass all user data to next controller
           	mainPane = FXMLLoader.load(url);
           	Scene scene = new Scene(mainPane);// pane you are GOING TO show
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
            window.setScene(scene);
            window.show();
       	}
       	catch (IOException e)
       	{
       		//popup error window
       	} 
           //validInput = textInputChecker(tempUser,0);
       //}
    }

    

}
