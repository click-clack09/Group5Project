package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import application.model.*;
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
    private ChoiceBox<String> selection;
	
	ArrayList<UserNameRecord> userHubRecords;
	ArrayList<HubEvent> events;
	ArrayList<Contact> contacts;
	
	@FXML
		void initialize()
	{
		
		userHubRecords = new ArrayList<UserNameRecord>();
		
		//How will data come back from query? Each record read should be added to userHubNames
		
		//DBQuery for all HubEvents and Contacts
		events = new ArrayList<HubEvent>();
		//Add ALL events
		User.setUserEvents(events);

		contacts = new ArrayList<Contact>();
		//Add ALL contacts
		User.setUserContacts(contacts);
		
		/*constructor for HubEvent(int eventID, int userID, int eventType, Date startDate, String hubName, String location,
		ArrayList<Contact> attendees, String eventName, Date endDate)*/
		
		//Dummy event
		events.add(new HubEvent(-1, 1, 1, new Date(),"Fall2021", "Fall2021Event1Location",
				new ArrayList<Contact>(), "Fall2021Event1Name", new Date()));
		
		//Hard coded hubs for testing
		userHubRecords.add(new UserNameRecord("Fall 2021",1));
		userHubRecords.add(new UserNameRecord("My Business Hub",2));
		userHubRecords.add(new UserNameRecord("Personal Hub",3));
		
		//make a list of the names
		ArrayList<String> hubNames = new ArrayList<String>();
		for (UserNameRecord record:userHubRecords)
		{
			hubNames.add(record.getUserName());
		}
		
		//This will print all of the hubs
		selection.getItems().addAll(hubNames);
		
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
    	String userChoice = selection.getSelectionModel().getSelectedItem().toString();
		
		//Logic below will determine the FXML doc to call
    	//Initialize hubType to sentinel value
    	int hubType = -1;
		int count = userHubRecords.size();
		Boolean find = true;
		while (find)
		{
			if (count > 0)
			{
				//descending search for hubEntry 
				hubType=userHubRecords.get(--count).compareTo(userChoice);
				//if valid hubType is returned, search is complete
				if (hubType > 0)
					find = false;
	
				System.out.println(userHubRecords.get(count).getUserName()+" "+hubType);
			}
			//Ends search when all values have been checked
			else
				find = false;
		}
		
		String hubView = "";
		
		ArrayList<Task> tasks = new ArrayList<Task>();
		ArrayList<Note> notes = new ArrayList<Note>();
		
		//this switch will get select the appropriate FXML doc (1 school, 3 personal, or 2 business)
		switch (hubType)
		{
			case 1:
				hubView="EducationHome";
				ArrayList<SchoolClass> classes = new ArrayList<SchoolClass>();
				//get classes from DB
				/*Constructor for SchoolClass(String className, String professor, String location, ArrayList<Task> assignments,
					HubEvent meetingTime)*/
				classes.add(new SchoolClass("Application Programming","Rathore","NPB",new ArrayList<Task>(), new HubEvent()));
				classes.add(new SchoolClass("Systems Programming","Sylvestro","SEB",new ArrayList<Task>(), new HubEvent()));
				User.setClasses(classes);
				//tasks and notes belong to classes for Education
				break;
			case 2:
				hubView="BusinessHome";
				//perform a DB query to based on userChoice (Hub name). Return tasks and notes.
				
				//Add hub tasks
				User.getCurrentHub().setTasks(tasks);
				
				//Add hub notes
				User.getCurrentHub().setNotes(notes);
				break;
			case 3:
				hubView="PersonalHome";
				//perform a DB query to based on userChoice (Hub name). Return tasks and notes.
				
				//Add hub tasks
				User.getCurrentHub().setTasks(tasks);
				
				//Add hub notes
				User.getCurrentHub().setNotes(notes);
				break;
			default:
				break;
		}
		
		//Create current Hub with data from on DB query
		User.setCurrentHub(new LifeHub("Fall 2021",1, events, tasks,notes));
	
    	try
    	{
    		
    		URL url = new File("src/application/view/"+hubView+".fxml").toURI().toURL();
    		User.setLastHub(new File("src/application/view/UserHome.fxml").toURI().toURL());
    		//this will instantiate the LifeHub object based on the DB query
        	//pull all tasks, events and notes associated with the User.getCurrentHub() String
    		//Make sure this makes sense. 
        	LifeHub currentHub = new LifeHub(userChoice);
        	
    		User.setCurrentHub(currentHub);
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
        		User.setLastHub(new File("src/application/view/UserHome.fxml").toURI().toURL());        		
 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////           	
        		//User.setCurrentHub(hubName);
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
       		User.setLastHub(new File("src/application/view/UserHome.fxml").toURI().toURL());
       	 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////      		
       		//User.setCurrentHub(hubName);
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
       		User.setLastHub(new File("src/application/view/UserHome.fxml").toURI().toURL());
       		//Make a New Hub, add it to the users arrayList, send it to DB
       	 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////  		
       		
       		//User.setCurrentHub(hubName);
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
