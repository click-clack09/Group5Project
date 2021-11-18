package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

import application.model.User;
import application.model.Contact;
import application.model.Date;
import application.model.HubEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CalendarController {
	
	@FXML
	private AnchorPane mainPane;

    @FXML
    private Label calendarLabel;
    
    @FXML
    private DatePicker dateMenu;
    
    @FXML
    private Label Sunday1;
    
    @FXML
    private Label Monday1;
    
    @FXML
    private Label Tuesday1;
    
    @FXML
    private Label Wednesday1;
    
    @FXML
    private Label Thursday1;
    
    @FXML
    private Label Friday1;
    
    @FXML
    private Label Saturday1;
    
    @FXML
    private Label Sunday2;

    @FXML
    private Label Monday2;

    @FXML
    private Label Tuesday2;

    @FXML
    private Label Wednesday2;

    @FXML
    private Label Thursday2;

    @FXML
    private Label Friday2;

    @FXML
    private Label Saturday2;

    @FXML
    private Label Sunday3;

    @FXML
    private Label Monday3;

    @FXML
    private Label Tuesday3;

    @FXML
    private Label Wednesday3;

    @FXML
    private Label Thursday3;

    @FXML
    private Label Friday3;

    @FXML
    private Label Saturday3;

    @FXML
    private Label Sunday4;

    @FXML
    private Label Monday4;

    @FXML
    private Label Tuesday4;

    @FXML
    private Label Wednesday4;

    @FXML
    private Label Thursday4;

    @FXML
    private Label Friday4;

    @FXML
    private Label Saturday4;

    @FXML
    private Label Sunday5;

    @FXML
    private Label Monday5;

    @FXML
    private Label Tuesday5;

    @FXML
    private Label Wednesday5;

    @FXML
    private Label Thursday5;

    @FXML
    private Label Friday5;

    @FXML
    private Label Saturday5;	
    
    @FXML
    private Label Sunday6;

    @FXML
    private Label Monday6;

    @FXML
    private Label Tuesday6;

    @FXML
    private Label Wednesday6;

    @FXML
    private Label Thursday6;

    @FXML
    private Label Friday6;

    @FXML
    private Label Saturday6;
    
    private int displayYear;
    
    private int displayMonth;
    
    private int displayDay;
    
    @FXML
    void initialize()
    {
    	LocalDate currentdate = LocalDate.now();
    	
    	updateCalendar(currentdate.getYear(), currentdate.getMonth().getValue());
    	
    	//this will instantiate the LifeHub object based on the DB query
    	//pull all tasks, events and notes associated with the User.getUserID() int
    	//calendarLabel.setText(User.getUserName()+", Monthly Calendar");
    }
    
    void clearCalendar() {
    	Sunday1.setText(""); 
    	Monday1.setText(""); 
    	Tuesday1.setText(""); 
    	Wednesday1.setText(""); 
    	Thursday1.setText(""); 
    	Friday1.setText(""); 
    	Saturday1.setText(""); 

    	Sunday2.setText(""); 
    	Monday2.setText(""); 
    	Tuesday2.setText(""); 
    	Wednesday2.setText(""); 
    	Thursday2.setText(""); 
    	Friday2.setText(""); 
    	Saturday2.setText(""); 

    	Sunday3.setText(""); 
    	Monday3.setText(""); 
    	Tuesday3.setText(""); 
    	Wednesday3.setText(""); 
    	Thursday3.setText(""); 
    	Friday3.setText(""); 
    	Saturday3.setText(""); 

    	Sunday4.setText(""); 
    	Monday4.setText(""); 
    	Tuesday4.setText(""); 
    	Wednesday4.setText(""); 
    	Thursday4.setText(""); 
    	Friday4.setText(""); 
    	Saturday4.setText(""); 

    	Sunday5.setText(""); 
    	Monday5.setText(""); 
    	Tuesday5.setText(""); 
    	Wednesday5.setText(""); 
    	Thursday5.setText(""); 
    	Friday5.setText(""); 
    	Saturday5.setText(""); 
    	
    	Sunday6.setText(""); 
    	Monday6.setText(""); 
    	Tuesday6.setText(""); 
    	Wednesday6.setText(""); 
    	Thursday6.setText(""); 
    	Friday6.setText(""); 
    	Saturday6.setText(""); 
    }
    
    void updateCalendar(int year, int month) {
    	clearCalendar();
    	
    	YearMonth ym = YearMonth.of(year, month);
    	
    	calendarLabel.setText(ym.getMonth().name());

    	int firstDay = ym.atDay(1).getDayOfWeek().getValue();
    	
    	firstDay = firstDay == 7 ? 0 : firstDay;
    	    	
    	int i = 1;    	
    			
    	switch(firstDay) {
    		case 0: Sunday1.setText(getEvent(year, month, i)); i++;
    		case 1: Monday1.setText(getEvent(year, month, i)); i++;
    		case 2: Tuesday1.setText(getEvent(year, month, i)); i++;
    		case 3:	Wednesday1.setText(getEvent(year, month, i)); i++;
    		case 4:	Thursday1.setText(getEvent(year, month, i)); i++;
    		case 5:	Friday1.setText(getEvent(year, month, i)); i++;
    		case 6:	Saturday1.setText(getEvent(year, month, i)); i++;
    	}
    	
    	int lastDay = ym.atEndOfMonth().getDayOfMonth();
    	
    	if(i <= lastDay) Sunday2.setText(getEvent(year, month, i)); i++;
    	if(i <= lastDay) Monday2.setText(getEvent(year, month, i)); i++;
    	if(i <= lastDay) Tuesday2.setText(getEvent(year, month, i)); i++;
    	if(i <= lastDay) Wednesday2.setText(getEvent(year, month, i)); i++;
    	if(i <= lastDay) Thursday2.setText(getEvent(year, month, i)); i++;
    	if(i <= lastDay) Friday2.setText(getEvent(year, month, i)); i++;
    	if(i <= lastDay) Saturday2.setText(getEvent(year, month, i)); i++;

    	if(i <= lastDay) Sunday3.setText(getEvent(year, month, i)); i++;
    	if(i <= lastDay) Monday3.setText(getEvent(year, month, i)); i++;
    	if(i <= lastDay) Tuesday3.setText(getEvent(year, month, i)); i++;
    	if(i <= lastDay) Wednesday3.setText(getEvent(year, month, i)); i++;
    	if(i <= lastDay) Thursday3.setText(getEvent(year, month, i)); i++;
    	if(i <= lastDay) Friday3.setText(getEvent(year, month, i)); i++;
    	if(i <= lastDay) Saturday3.setText(getEvent(year, month, i)); i++;

    	if(i <= lastDay) Sunday4.setText(getEvent(year, month, i)); i++;
    	if(i <= lastDay) Monday4.setText(getEvent(year, month, i)); i++;
    	if(i <= lastDay) Tuesday4.setText(getEvent(year, month, i)); i++;
    	if(i <= lastDay) Wednesday4.setText(getEvent(year, month, i)); i++;
    	if(i <= lastDay) Thursday4.setText(getEvent(year, month, i)); i++;
    	if(i <= lastDay) Friday4.setText(getEvent(year, month, i)); i++;
    	if(i <= lastDay) Saturday4.setText(getEvent(year, month, i)); i++;
    	
    	if(i <= lastDay) Sunday5.setText(getEvent(year, month, i)); i++;
    	if(i <= lastDay) Monday5.setText(getEvent(year, month, i)); i++;
    	if(i <= lastDay) Tuesday5.setText(getEvent(year, month, i)); i++;
    	if(i <= lastDay) Wednesday5.setText(getEvent(year, month, i)); i++;
    	if(i <= lastDay) Thursday5.setText(getEvent(year, month, i)); i++;
    	if(i <= lastDay) Friday5.setText(getEvent(year, month, i)); i++;
    	if(i <= lastDay) Saturday5.setText(getEvent(year, month, i)); i++;

    	if(i <= lastDay) Sunday6.setText(getEvent(year, month, i)); i++;
    	if(i <= lastDay) Monday6.setText(getEvent(year, month, i)); i++;
    	if(i <= lastDay) Tuesday6.setText(getEvent(year, month, i)); i++;
    	if(i <= lastDay) Wednesday6.setText(getEvent(year, month, i)); i++;
    	if(i <= lastDay) Thursday6.setText(getEvent(year, month, i)); i++;
    	if(i <= lastDay) Friday6.setText(getEvent(year, month, i)); i++;
    	if(i <= lastDay) Saturday6.setText(getEvent(year, month, i)); i++;
    }
    
    String getEvent(int year, int month, int day) {
    	String display = " " + day + "\n";
    	
    	ArrayList<HubEvent> events = new ArrayList<HubEvent>();
    	
    	//populate events based on day
    	
    	events.add(new HubEvent("Event " + day));
    	
    	
    	for(int i = 0; i < events.size(); i++) {
    		if(true/*events are in selected hubs*/) {
    			display += events.get(i).toString() + "\n";
    		}
    	}
    	
    	return display;
    }
    
    @FXML
    void changeTheme(ActionEvent event) {
    	System.out.println("testChangeTheme");
    }
    
    @FXML
    void addHub(ActionEvent event) {
    	System.out.println("testAddHub");
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
    void addEvent(ActionEvent event) {
    	/*
    	 * private int eventID = 0;
	private int userID; User.....
	private int eventType; 
	private Date startDate;
	//this will need to be referenced with a LifeHub name, but should that come from the sending method?
	private String hubName;
	private String location;
	private ArrayList<Contact> attendees;
	private String eventName;
	private Date endDate;//ki
    	 * */
    	//Date picker start date
    	//Hub Name (From dropDown)What hub is this event for? Dropdown, hubs This will also give us type
    	//get location
    	//get attendees from contact list (dropdown) loop 
    	//Date picker end date
    	//Get event name
    	//
    	HubEvent tempEvent = new HubEvent();
    	User.addHubEvent(tempEvent);
    }

    @FXML
    void deleteEvent(ActionEvent event) {

    }
    
    @FXML
    void chooseDate(ActionEvent event) {
        LocalDate date = dateMenu.getValue();
        updateCalendar(date.getYear(), date.getMonth().getValue());
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
   		//URL url = new File("src/EducationHome.fxml").toURI().toURL();
    	mainPane = FXMLLoader.load(User.getLastHub());
    	User.setLastHub(new File("src/application/view/Calendar.fxml").toURI().toURL());
    	//mainPane = FXMLLoader.load(getClass().getClassLoader().getResource("Classified.fxml"));// pane you are GOING TO
   		Scene scene = new Scene(mainPane);// pane you are GOING TO show
        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
   		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
	    window.setScene(scene);
	    window.show();
	}

    @FXML
    void goHome(ActionEvent event) throws IOException {
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
