package application.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

import application.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**This class acts as the controller for the Calendar view.
 * 
 * @author group 5 11-23-21
 *
 */
public class CalendarController {
	
	ObservableList<CheckBox> eventCheckBoxes = FXCollections.observableArrayList();
	
    private int displayYear;
    
    private int displayMonth;
    
    private int displayDay;
    
    private ArrayList<Label> labels;
	
	@FXML
	private VBox checkboxDisplay;
    
	@FXML
	private AnchorPane mainPane;

    @FXML
    private Label calendarLabel;
    
    @FXML
    private Label calendarUserLabel;
    
    @FXML
    private DatePicker dateMenu;
    
    @FXML
    private VBox checkBoxContainer;
    
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
    
    /**
     * calls various methods to instantiate lists and display the calendar
     */
    @FXML
    void initialize()
    {
    	LocalDate currentdate = LocalDate.now();
    	calendarUserLabel.setText(User.getUserName()+", Monthly Calendar");
    	
    	displayYear = currentdate.getYear();
    	displayMonth = currentdate.getMonth().getValue();
    	displayDay = currentdate.getDayOfMonth();
    	
    	labels = new ArrayList<Label>();
    	
    	initializeLabels();
    	
    	initializeCheckboxes();
    	
    	updateCalendar();
    }
    
    /**
     * loads checkboxes on the sidebar base on User Hubs
     */
    void initializeCheckboxes() {
    	
    	for (int i = 0; i < User.getUserHubs().size(); i++) {	
    		//Make a CheckBox for each task\
			System.out.println(User.getUserHubs().get(i).getHubName());
        	CheckBox cb = new CheckBox(User.getUserHubs().get(i).getHubName());
        	cb.setPadding(new Insets(10, 10, 0, 0));
        	cb.setStyle("-fx-text-fill: #ffffff");
        	cb.setSelected(true);
        	cb.setOnAction(event3 -> {
                if (!cb.isSelected()) {
                	System.out.println("CHECKBOX Unchecked");
                	updateCalendar();
                	System.out.println(event3.getSource());//parse this, use to get source, make the labels appear or not
                	//delete task, use taskHash and classHash as applicable, start thread, if still checked delete?
                }
                if (cb.isSelected()) {
                	System.out.println("CHECKBOX Checked");
                	updateCalendar();
                	System.out.println(event3.getSource());//parse this, use to get source, make the labels appear or not
                	//delete task, use taskHash and classHash as applicable, start thread, if still checked delete?
                }
              });
        	
        	eventCheckBoxes.add(cb);
		}
    	
    	checkBoxContainer.getChildren().addAll(eventCheckBoxes);
    }
    
    /**
     * adds all labels to their corresponding ArrayLists
     */
    void initializeLabels() {
    	labels.add(Sunday1);
    	labels.add(Monday1);
    	labels.add(Tuesday1);
    	labels.add(Wednesday1);
    	labels.add(Thursday1);
    	labels.add(Friday1);
    	labels.add(Saturday1);
    	
    	labels.add(Sunday2);
    	labels.add(Monday2);
    	labels.add(Tuesday2);
    	labels.add(Wednesday2);
    	labels.add(Thursday2);
    	labels.add(Friday2);
    	labels.add(Saturday2);
    	
    	labels.add(Sunday3);
    	labels.add(Monday3);
    	labels.add(Tuesday3);
    	labels.add(Wednesday3);
    	labels.add(Thursday3);
    	labels.add(Friday3);
    	labels.add(Saturday3);
    	
    	labels.add(Sunday4);
    	labels.add(Monday4);
    	labels.add(Tuesday4);
    	labels.add(Wednesday4);
    	labels.add(Thursday4);
    	labels.add(Friday4);
    	labels.add(Saturday4);
    	
    	labels.add(Sunday5);
    	labels.add(Monday5);
    	labels.add(Tuesday5);
    	labels.add(Wednesday5);
    	labels.add(Thursday5);
    	labels.add(Friday5);
    	labels.add(Saturday5);
    	
    	labels.add(Sunday6);
    	labels.add(Monday6);
    	labels.add(Tuesday6);
    	labels.add(Wednesday6);
    	labels.add(Thursday6);
    	labels.add(Friday6);
    	labels.add(Saturday6);
    }
    
    /**
     * clears the calendar
     */
    void clearCalendar() {
    	for(Label label : labels) {
    		label.setText("");
        	label.setTextFill(Color.web("#000000"));
        	label.setStyle("-fx-background-color: #909090");
    	}
    }
    
    /**
     * fills the calendar based on the current/selected day and User HubEvents
     */
    void updateCalendar() {
    	clearCalendar();
    	
    	YearMonth ym = YearMonth.of(displayYear, displayMonth);
    	
    	calendarLabel.setText(ym.getMonth().name());

    	int firstDay = ym.atDay(1).getDayOfWeek().getValue();
    	
    	firstDay = firstDay == 7 ? 0 : firstDay;
    	    	
    	int i = 1;    	
    			
    	switch(firstDay) {
			case 0: fillDay(Sunday1, i); i++;
			case 1: fillDay(Monday1, i); i++;
			case 2: fillDay(Tuesday1, i); i++;
			case 3:	fillDay(Wednesday1, i); i++;
			case 4:	fillDay(Thursday1, i); i++;
			case 5:	fillDay(Friday1, i); i++;
			case 6:	fillDay(Saturday1, i); i++;
    	}
	
    	for(int x = 7; x < labels.size(); x++) {
    		fillDay(labels.get(x), i); i++;
    	}
	}

    /**
     * @param label : the label to fill
     * @param i : the day of the month to display
     */
    void fillDay(Label label, int i) {
    	YearMonth ym = YearMonth.of(displayYear, displayMonth);
    	
		int lastDay = ym.atEndOfMonth().getDayOfMonth();

		if(i <= lastDay) {
			label.setText(getEvent(displayYear, displayMonth, i));
	    	
			label.setStyle("-fx-background-color: #FFFFFF");
			
			if(i == displayDay) {
				label.setTextFill(Color.web("#c21919")); 
				label.setStyle("-fx-background-color: #fffebf");
			} 
			
		}
    }
    
    /**
     * @param year : the year to find events for
     * @param month : the month to find events for
     * @param day : the day to find events for
     * @return a String containing events with the given year/month/day
     */
    String getEvent(int year, int month, int day) {
    	String display = " " + day + "\n";
    	
    	ArrayList<HubEvent> events = User.getUserEvents();
    	
    	for(HubEvent e : events) {
    		Date d = e.getStartDate();
    		if(d.getYear() == year && d.getMonth() == month && d.getDay() == day) {
    			if(inHub(e)) {
        			display += e.getEventName() + "\n ";
        		}
    		}
    	}
    	
    	return display;
    }
    
    /**
     * @param e : HubEvent being checked
     * @return whether HubEvent e is a child of one of the checked Hubs
     */
    boolean inHub(HubEvent e) {
    	String name = e.getHubName();
    	
    	for(CheckBox c: eventCheckBoxes) {
    		if(c.isSelected()) { 
    			if(name.equals(c.getText())) {
    				return true;
    			}
    		}
    	}
    	
    	return false;
    }
    
    @FXML
    void changeTheme(ActionEvent event) {
    	System.out.println("testChangeTheme");
    }
    
    /**Top menu item. Unused at this time.
	 * 
     * @param event : the triggering event
     */    
    @FXML
    void addHub(ActionEvent event) {
    	System.out.println("testAddHub");
    }
    
    /**Top menu item. Unused at this time.
	 * 
     * @param event : the triggering event
     */    
	 @FXML
	    void tutorial(ActionEvent event) {
		 System.out.println("testTutorial");
	    }
	 
	 /**Top menu item. Unused at this time.
		 * 
	     * @param event : the triggering event
	     */    
	 @FXML
	    void about(ActionEvent event) {
		 System.out.println("testAbout");
	    }
	 
	 /**Top menu item. Unused at this time.
		 * 
	     * @param event : the triggering event
	     */    
	    @FXML
	    void logout(ActionEvent event) {
	    	System.out.println("testLogout");
	    }
	 
	/**Top menu item. Unused at this time.
	 * 
     * @param event : the triggering event
     */    
    @FXML
    void close(ActionEvent event) {
    	System.out.println("testClose");
    }

    /**Top menu item. Unused at this time.
	 * 
     * @param event : the triggering event
     */    
    @FXML
    void deleteHub(ActionEvent event) {
    	System.out.println("testDelete");
    }

    @FXML
    void addEvent(ActionEvent event) {
    	//eventID=0,userID get, eventType get,
    	//Popup 1 get date. Add to Alert? craft? do multiple asks? Is there a date picker popup?
    	//Popup 2 hubName ask
    	//popup 3 location
    	//
    	//do all popups, create event
    	//addHubEvent(hubEvent);
    	Dialog<Object> dialog = new Dialog<Object>();
    	DatePicker startDate = new DatePicker();
    	DatePicker endDate = new DatePicker();
    	GridPane dialogGridPane= new GridPane();
    	dialogGridPane.setAlignment(Pos.CENTER);
    	HBox startHBox = new HBox();
    	HBox endHBox = new HBox();
    	String eventName = "";
    	String location = "";
    	String hubName ="";
    	int hubType = 0;
    	Date start;
    	Date end;
    	ButtonType buttonTypeOk = new ButtonType("OK");
        
        ChoiceBox hubPicker = new ChoiceBox();
        Label hubLabel = new Label("Event Hub:");
    	Label eventLabel = new Label("Event Name:");
        TextField eventText = new TextField();
        Label locationLabel = new Label("Location:");
        Label startLabel = new Label("Start Date:");
        Label endLabel = new Label("End Date:");
        Label startTime = new Label("Start Time:");
        TextField startHr = new TextField();
        TextField startMin = new TextField();
        Label endTime = new Label("End Time:");
        Label colon1 = new Label(" : ");
        Label colon2 = new Label(" : ");
        TextField endHr = new TextField();
        TextField endMin = new TextField();
        TextField locationText = new TextField();
        eventText.setMinWidth(200);
        locationText.setMinWidth(200);
        startDate.setMinWidth(200);
        endDate.setMinWidth(200);
        dialogGridPane.setMinWidth(240);
        hubPicker.setMinWidth(200);
        startHr.setMinWidth(20);
        startMin.setMinWidth(20);
        endHr.setMinWidth(20);
        endMin.setMinWidth(20);
        startHr.setMaxWidth(40);
        startMin.setMaxWidth(40);
        endHr.setMaxWidth(40);
        endMin.setMaxWidth(40);
        startHBox.getChildren().addAll(startHr,colon1,startMin);
        endHBox.getChildren().addAll(endHr,colon2,endMin);
        ArrayList<UserHubRecord> hubRecords = new ArrayList<UserHubRecord>();
        ObservableList<String> hubChoices = FXCollections.observableArrayList();
        for (LifeHub hub : User.getUserHubs())
        {
        	hubChoices.add(hub.getHubName());
        	hubRecords.add(new UserHubRecord(hub.getHubName(),hub.getEventType()));
        }
        hubPicker.setItems(hubChoices);
        //dialogVBox.setAlignment(Pos.BOTTOM_CENTER);
        dialogGridPane.setHgap(10);
        dialogGridPane.setVgap(12);
        dialogGridPane.setPadding(new Insets(20, 15, 15, 20));
        dialogGridPane.add(hubLabel,1,1);
        dialogGridPane.add(hubPicker,1,2);
        dialogGridPane.add(eventLabel,1,3);
        dialogGridPane.add(eventText,1,4);
        dialogGridPane.add(startLabel,1,5);
        dialogGridPane.add(startDate,1,6);
        dialogGridPane.add(startTime,1,7);
        dialogGridPane.add(startHBox,1,8);
        dialogGridPane.add(locationLabel,1,9);
        dialogGridPane.add(locationText,1,10);
        dialogGridPane.add(endLabel,1,11);
        dialogGridPane.add(endDate,1,12);
        dialogGridPane.add(endTime,1,13);
        dialogGridPane.add(endHBox,1,14);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
        int index;
        int sentinal;
        int startHourInt = -1;
        int startMinInt = -1; 
        int endHourInt = -1;
        int endMinInt = -1;
        LocalDate date1;
        LocalDate date2;
        boolean validInput;
        do
        {
        	validInput = true;
        	eventText.clear();
            locationText.clear();
            startHr.clear();
            startMin.clear();
            endHr.clear();
            endMin.clear();
            dialog.getDialogPane().setContent(dialogGridPane);
        	dialog.showAndWait();
        	eventName = eventText.getText();
        	if(!validateInput(eventName))
        	{
        		validInput = false;
        		continue;
        	}
        	location = locationText.getText();
        	if(!validateInput(location))
        	{
        		validInput = false;
        		continue;
        	}
        	try
        	{
        		hubName=hubPicker.getSelectionModel().getSelectedItem().toString();
        	}
        	catch (Exception e)
        	{
        		validInput = false;
        		continue;
        	}
        	if(!validateInput(hubName))
        	{
        		validInput = false;
        		continue;
        	}
        	try
        	{
        		if(!validateInput(startHr.getText()))
            	{
            		validInput = false;
            		continue;
            	}
        		startHourInt = Integer.parseInt(startHr.getText());
        		
        		if(!validateInput(startMin.getText()))
            	{
            		validInput = false;
            		continue;
            	}
        		startMinInt = Integer.parseInt(startMin.getText());
        		
        		if(!validateInput(endHr.getText()))
            	{
            		validInput = false;
            		continue;
            	}
        		endHourInt = Integer.parseInt(endHr.getText());
        		
        		if(!validateInput(endMin.getText()))
            	{
            		validInput = false;
            		continue;
            	}
        		endMinInt = Integer.parseInt(endMin.getText());
        	}
        	catch(NumberFormatException e)
        	{
        		validInput = false;
        	}
        	catch(Exception e)
        	{
        		validInput = false;
        	}
        	
        }while(!validInput);
        date1 = startDate.getValue();
    	date2 = endDate.getValue();
        index = 0;
    	sentinal = -1;
    	do {
    			sentinal = hubRecords.get(index++).compareTo(hubName);
    		}while(index <hubRecords.size() && sentinal < 0);
    	if (sentinal > 0)
    		hubType = sentinal;
    	
    	start = new Date(date1.getYear(), date1.getMonth().getValue(), date1.getDayOfMonth(), startHourInt,startMinInt);
    	end = new Date(date2.getYear(), date2.getMonth().getValue(), date2.getDayOfMonth(), endHourInt,endMinInt);
    	
    	//System.out.println("Event Name:"+eventName+"\nlocation:"+location+"\n"+start.toString()+"\n"+end.toString()+"\nHubName"+hubName+"\nHubType "+sentinal);
    	HubEvent temp = new HubEvent(0, User.getUserID(), sentinal, start, hubName, location, new ArrayList<Contact>(), eventName, end);
    	User.getUserEvents().add(temp);
    	User.addHubEvent(temp);
    	updateCalendar();
    }

    /**Sidebar item. Unused at this time.
     * 
     * @param event : the triggering event
     */
    @FXML
    void deleteEvent(ActionEvent event) {

    }
    
    @FXML
    void chooseDate(ActionEvent event) {
        LocalDate date = dateMenu.getValue();
        
        displayYear = date.getYear();
        displayMonth = date.getMonth().getValue();
        displayDay = date.getDayOfMonth();
        
        updateCalendar();
    }

    /**This method accepts an ActionEvent, changes the scene to the last scene,
     * and returns nothing.
     * 
     * @param event- the ActionEvent that triggers this method
     * @throws IOException- handles issues with the fxml documents.
     */
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

    /**This method accepts an ActionEvent, changes the scene to the UserHome,
     * and returns nothing.
     * 
     * @param event- the ActionEvent that triggers this method
     * @throws IOException- handles issues with the fxml documents.
     */
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
    
    
    /**This method accepts a String, returns true if the String is not empty
     * and not null, otherwise returns false.
     * 
     * @param input : The String being validated.
     * @return : the Boolean value determined by the String being null or empty.
     */
    public boolean validateInput(String input)
    {
    	if(input != null)
    	{
    		if (!input.equals(""))
    			return true;
    	}
    	return false;
    }

}
