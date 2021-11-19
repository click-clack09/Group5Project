package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

import application.model.User;
import application.model.Date;
import application.model.HubEvent;
import application.model.LifeHub;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CalendarController {
	
	ObservableList<CheckBox> eventCheckBoxes = FXCollections.observableArrayList();
	
	@FXML
	private VBox checkboxDisplay;
    
	@FXML
	private AnchorPane mainPane;

    @FXML
    private Label calendarLabel;
    
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
    
    private int displayYear;
    
    private int displayMonth;
    
    private int displayDay;
    
    private ArrayList<Label> labels;
    
    @FXML
    void initialize()
    {
    	LocalDate currentdate = LocalDate.now();
    	
    	for (int i = 0; i < User.getUserHubs().size(); i++)
		{	
    		//Make a CheckBox for each task\
			System.out.println(User.getUserHubs().get(i).getHubName());
        	CheckBox cb = new CheckBox(User.getUserHubs().get(i).getHubName());
        	cb.setPadding(new Insets(10, 10, 0, 0));
        	cb.setStyle("-fx-text-fill: #ffffff");
        	cb.setSelected(true);
        	cb.setOnAction(event3 -> {
                if (!cb.isSelected()) 
                {
                	System.out.println("CHECKBOX Unchecked");
                	System.out.println(event3.getSource());//parse this, use to get source, make the labels appear or not
                	//delete task, use taskHash and classHash as applicable, start thread, if still checked delete?
                }
              });
        	eventCheckBoxes.add(cb);
		}
    	checkBoxContainer.getChildren().addAll(eventCheckBoxes);
    	
    	displayYear = currentdate.getYear();
    	displayMonth = currentdate.getMonth().getValue();
    	displayDay = currentdate.getDayOfMonth();
    	
    	labels = new ArrayList<Label>();
    	initializeLabels();
    	
    	updateCalendar();
    	
    	//displayHubCheckboxes();
    }
    
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
    
    void clearCalendar() {
    	for(Label label : labels) {
    		label.setText("");
        	label.setTextFill(Color.web("#000000"));
        	label.setStyle("-fx-background-color: #909090");
    	}
    }
    
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


    void fillDay(Label label, int i) {
    	YearMonth ym = YearMonth.of(displayYear, displayMonth);
    	
		int lastDay = ym.atEndOfMonth().getDayOfMonth();

		if(i <= lastDay) {
			label.setText(getEvent(displayYear, displayMonth, i));
	    	
			if(i == displayDay) {
				label.setTextFill(Color.web("#ff0000")); 
			} 
			
			label.setStyle("-fx-background-color: #FFFFFF");
		}
    }
    
    String getEvent(int year, int month, int day) {
    	String display = " " + day + "\n";
    	
    	ArrayList<HubEvent> events = User.getUserEvents();
    	
    	for(HubEvent e : events) {
    		Date d = e.getStartDate();
    		if(d.getYear() == year && d.getMonth() == month && d.getDay() == day) {
    			if(true/*events are in selected hubs*/) {
        			display += e.getEventName() + "\n ";
        		}
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

    }

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
    
//    void displayHubCheckboxes()
//    {
//    	//for (int i = 0; i < User.getUserHubs().size(); i++)
//		//{
//			//For every class, add an observable checkList
//            
//            //make a separator and a VBox 
//            
//    		//THIS ONE
//    		//Separator separator = new Separator();
//            VBox temp = new VBox();
//            //temp.setPadding(new Insets(10, 10, 10, 10));
//            
//            //this will get all hubs in ArrayList for user
//            for (int j = 0; j < User.getUserHubs().size(); j++)
//            {
//            	//System.out.println("HELLO");
//            	//Make a CheckBox for each task
//            	LifeHub hub = (LifeHub)User.getUserHubs().get(j);
//            	CheckBox cb = new CheckBox("HELLO");//hub.getHubName());
//                //cb.setStyle("-fx-text-fill:white");
//                cb.setPadding(new Insets(10, 10, 0, 0));
//                eventCheckBoxes.add(cb);
//
//            }
//            
//            System.out.println(eventCheckBoxes.toString());
//            for(CheckBox c: eventCheckBoxes)
//            	System.out.println(c.getText());
//            
//    		//THIS ONE
//            checkboxDisplay.getChildren().clear();
//            checkboxDisplay.getChildren().addAll(eventCheckBoxes);
//            
//
//            
//		//}
//    }

}
