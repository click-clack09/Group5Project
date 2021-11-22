package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.scene.paint.Color;

import application.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
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
    private ListView<Hyperlink> contactList;

    @FXML
    private TextArea notes;

    @FXML
    private ListView<Hyperlink> archivedNotes;
    
    
private ArrayList<Label> labels = new ArrayList<Label>();
    
@FXML
private Label Sunday;

@FXML
private Label Monday;

@FXML
private Label Tuesday;

@FXML
private Label Wednesday;

@FXML
private Label Thursday;

@FXML
private Label Friday;

@FXML
private Label Saturday;

    @FXML
    private Label text800;

    @FXML
    private Label text830;

    @FXML
    private Label text900;

    @FXML
    private Label text930;

    @FXML
    private Label text1000;

    @FXML
    private Label text1030;

    @FXML
    private Label text1100;

    @FXML
    private Label text1130;

    @FXML
    private Label text1200;

    @FXML
    private Label text1230;

    @FXML
    private Label text1300;

    @FXML
    private Label text1330;

    @FXML
    private Label text1400;

    @FXML
    private Label text1430;

    @FXML
    private Label text1500;

    @FXML
    private Label text1530;

    @FXML
    private Label text1600;

    @FXML
    private Label text1630;

    @FXML
    private Label text1700;

    @FXML
    private Label text1730;

    @FXML
    private Label text1800;

    @FXML
    private Label text1830;

    @FXML
    private Label text1900;

    @FXML
    private Label text1930;

    @FXML
    private Label text2000;

    @FXML
    private Label text2030;

    @FXML
    private Label text2100;

    @FXML
    private Label text2130;

    @FXML
    private Label text2200;

    @FXML
    private Label text2230;

    @FXML
    private Label text2300;
    @FXML
    private VBox topBox;
    private ObservableList<CheckBox> toDoVBoxList = FXCollections.observableArrayList();
    private ObservableList<Hyperlink> archivedNoteList = FXCollections.observableArrayList();
    private ObservableList<Hyperlink> userContactList = FXCollections.observableArrayList();
    int index;
    
    //This goes with item/task delete  deleteTask(Task task, String className)
    
    @FXML
    void initialize()
    {
    	int r = 255;
    	int g = 1;
    	int b = 255;
    	//coordinate logo to match color scheme
    	//topBox.setBackground(new Background(new BackgroundFill(Color.rgb(r,g,b), null, null)));
    	//topBox.setStyle(null);
    	//Add Hub tasks
    	businessHomeLabel.setText(User.getUserName()+", "+User.getCurrentHub().getHubName());
    	
    	initializeDailyLabels();
    	updateDailyCalendar();
    	updateWeeklyCalendar();
    	
    	if(User.getCurrentHub().getTasks()!=null)
    	{
    		System.out.println(User.getCurrentHub().getTasks().size());
    		for (int i = 0; i < User.getCurrentHub().getTasks().size(); i++)
    		{	
	    		//Make a CheckBox for each task\
    			System.out.println(User.getCurrentHub().getTasks().get(i).getText());
	        	CheckBox cb = new CheckBox(User.getCurrentHub().getTasks().get(i).getText());
	        	cb.setPadding(new Insets(10, 10, 0, 0));
	        	Task tempTask = User.getCurrentHub().getTasks().get(i);
//	        	Thread t1 = new Thread(new Runnable() {
//	        	      @Override
//	        	      public void run() {
//	        	        try {
//	        	            Thread.sleep(200);
//	        	            if (cb.isSelected()) 
//		                    {
//		                    	User.deleteTask(tempTask, User.getCurrentClass());
//		                    	toDoVBoxList.remove(cb);
//		                    	toDoList.getChildren().clear();
//		                    	toDoList.getChildren().addAll(toDoVBoxList);
//		                    }
//	        	          } catch (InterruptedException e) {
//	        	            e.printStackTrace();
//	        	          }
//
//	        	      }
//	        	    });
	        	
	        	cb.setOnAction(event3 -> {
                    if (cb.isSelected()) 
                    {
                    	//t1.start();
                    	toDoVBoxList.remove(cb);
                    	toDoList.getChildren().clear();
                    	toDoList.getChildren().addAll(toDoVBoxList);
                    	User.deleteTask(tempTask, User.getCurrentClass());
						System.out.println("CHECKBOX ACTIVATED");
                    	//delete task, use taskHash and classHash as applicable, start thread, if still checked delete?
						//call method that makes a new thread, thread sleeps, checks again, deletes or terminates
                    }
                  });
	        	
	        	
	        	toDoVBoxList.add(cb);
    		}
    		
    		
    		//cb.setStyle("-fx-text-fill:white");
            
            toDoList.getChildren().addAll(toDoVBoxList);
            
          //Deal with Archived notes
        	if(User.getCurrentHub().getNotes() !=null)
        	{
        		System.out.println(User.getCurrentHub().getNotes().size());
        		for (int i = 0; i < User.getCurrentHub().getNotes().size(); i++)
        		{	
        			Hyperlink tempLink = new Hyperlink(User.getCurrentHub().getNotes().get(i).getText());
        			System.out.println(User.getCurrentHub().getNotes().get(i).getText()+"<______________________________");
        			 
        			Alert alert = getAlert("Display Note","Note",User.getCurrentHub().getNotes().get(i).getText());
                  	
                    tempLink.setOnAction(event2 ->{
                  	  alert.showAndWait();
                  	  
                    });
                    archivedNoteList.add(tempLink);
        		}
        		//Add the date to this as well. Wishlist make this a hyperlink with a popup
        		archivedNotes.getItems().addAll(archivedNoteList);
        		//probably should consider how to delete notes
        	}
        	
    	}
    	if (User.getUserContacts()!=null)
    	{
    	TextInputDialog textDialog = new TextInputDialog();
    	

    	  //Add contact hyperlink
//        String contactHeader = "";
//        String contactContent = "";
//        String contactHeader = "";
    	//ArrayList<Alert> popups = new ArrayList<Alert>();
		for (int i = 0; i < User.getUserContacts().size(); i++)
          {
          //User.getUserContacts().add(User.getUserContacts().get(i));
          System.out.println("");
          
          Hyperlink tempLink = new Hyperlink(User.getUserContacts().get(i).getName());
          userContactList.add(tempLink);
          
        
          
          Alert alert = getAlert("Display Contact",User.getUserContacts().get(i).getName(),User.getUserContacts().get(i).toString());
        	
          tempLink.setOnAction(event2 ->{
        	  alert.showAndWait();
        	  
          });
          
          //contactList.getItems().add(tempLink); 
          }
		contactList.getItems().addAll(userContactList);
    	}
    	
    }
    
    public Alert getAlert(String title, String header, String content)
    {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle(title);
    	alert.setHeaderText(header);
    	alert.setContentText(content);
    	
    	return alert;
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
    	TextInputDialog textDialog = new TextInputDialog();
    	ChoiceDialog choice = new ChoiceDialog();
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Display Contact");
    	alert.setHeaderText("Contact Name");
    	alert.setContentText("Contact toString");
    	boolean validInput;
    	ArrayList<String> phoneTypes = new ArrayList<String>();
    	ArrayList<String> emailTypes = new ArrayList<String>();
    	phoneTypes.add("Home");
    	phoneTypes.add("Cell");
    	phoneTypes.add("Work");
    	phoneTypes.add("Fax");
    	phoneTypes.add("Other");
    	
    	emailTypes.add("Personal");
    	emailTypes.add("Business");
    	emailTypes.add("School");
    	emailTypes.add("Other");

    	String contactName = "";
    	String email = "";
    	String phone = "";
    	String etype = "";
    	String ptype = "";
      	
    	try
    	{
	    	do
	        {
	    		textDialog.getEditor().clear();
	    		textDialog.setTitle("New Contact");
		     	textDialog.setHeaderText("Please enter the new contact name");
		     	textDialog.setContentText("Name:");
		     	textDialog.showAndWait();
		     	if(textDialog.getResult()==null)
		     		throw new Exception();
		     	contactName = textDialog.getResult();
		     	validInput = validateInput(contactName);
	        }while(!validInput);
	    	
	    	do
	        {
	    		textDialog.getEditor().clear();
	    		textDialog.setTitle("New Contact");
	    		textDialog.setHeaderText("Please enter a phone number");
	    		textDialog.setContentText("Number:");
	    		textDialog.showAndWait();
	    		if(textDialog.getResult()==null)
		     		throw new Exception();
	    		phone = textDialog.getResult();
	    		validInput = validateInput(phone);
	        }while(!validInput);
			
	    	do
	    	{
		    	choice = new ChoiceDialog("Please select", phoneTypes);
		    	choice.setTitle("New Contact");
		    	choice.setHeaderText("Please enter a phone type");
		    	choice.setContentText("Type:");
				choice.showAndWait();
				ptype = choice.getSelectedItem().toString();
				validInput = validateInput(ptype);
		    }while(!validInput);
	    	
			//either add logic to loop this or make a multifield popup
	    	
	    	do
	        {
		    	textDialog.getEditor().clear();
				textDialog.setTitle("New Contact");
				textDialog.setHeaderText("Please enter an email address");
				textDialog.setContentText("Email:");
				textDialog.showAndWait();
				if(textDialog.getResult()==null)
		     		throw new Exception();
				email = textDialog.getResult();
				validInput = validateInput(email);
		    }while(!validInput);
	    	
	    	//change this to dropdown selection
	    	do
	        {
	    		choice = new ChoiceDialog("Please select", emailTypes);
			choice.setTitle("New Contact");
			choice.setHeaderText("Please enter an email type");
			choice.setContentText("Type:");
			choice.showAndWait();
			etype = choice.getSelectedItem().toString();
			validInput = validateInput(etype);
		    }while(!validInput);
	    	
			Contact temp = new Contact(contactName, phone, ptype, email, etype);
			//Add contact hyperlink
			  
			User.getUserContacts().add(temp);
			
			//Push to DB
			if (User.addContact(temp))
			{
				Hyperlink tempLink = new Hyperlink(temp.getName());
				userContactList.add(tempLink);
				tempLink.setOnAction(event2 ->{
				alert.setHeaderText(temp.getName());
				alert.setContentText(temp.toString());
				alert.showAndWait();
				});
			}
			//Add the date to this as well. Wishlist make this a hyperlink with a popup
			//deal with the output
			contactList.getItems().clear();
			contactList.getItems().addAll(userContactList);
    	}
    	catch(Exception e)
    	{
    		
    	}
		          
    }
    
    void initializeDailyLabels() {
    	labels.add(text800);
    	labels.add(text830);
    	labels.add(text900);
    	labels.add(text930);
    	labels.add(text1000);
    	labels.add(text1030);
    	labels.add(text1100);
    	labels.add(text1130);
    	labels.add(text1200);
    	labels.add(text1230);
    	labels.add(text1300);
    	labels.add(text1330);
    	labels.add(text1400);
    	labels.add(text1430);
    	labels.add(text1500);
    	labels.add(text1530);
    	labels.add(text1600);
    	labels.add(text1630);
    	labels.add(text1700);
    	labels.add(text1730);
    	labels.add(text1800);
    	labels.add(text1830);
    	labels.add(text1900);
    	labels.add(text1930);
    	labels.add(text2000);
    	labels.add(text2030);
    	labels.add(text2100);
    	labels.add(text2130);
    	labels.add(text2200);
    	labels.add(text2230);
    	labels.add(text2300);    	
    }
    
    void clearDailyCalendar() {
    	for(Label label : labels) {
    		label.setText(" ");
        	label.setTextFill(Color.web("#000000"));
        	//label.setStyle("-fx-background-color: #909090");
    	}
    }
    
    void updateDailyCalendar() {
    	clearDailyCalendar();
    	
    	ArrayList<HubEvent> events = User.getUserEvents();
    	
    	LocalDate currentdate = LocalDate.now();
    	
    	int year = currentdate.getYear();
    	int month = currentdate.getMonth().getValue();
    	int day = currentdate.getDayOfMonth();
    	
    	for(HubEvent e : events) {
    		
    		Date d = e.getStartDate();
    		
    		if(year == d.getYear() && month == d.getMonth() && day == d.getDay()) {
    			displayDailyEvent(e);
    		}
    	}
    }
    
void displayDailyEvent(HubEvent e) {
    	
    	Date d = e.getStartDate();
    	
    	if(d.getHour() < 8) {
    		String text = text800.getText();
    		text += e.getEventName() + " \n";
    		text800.setText(text);
    	}
    	if(d.getHour() == 8) {
    		if(d.getMinute() >= 30) {
    			String text = text830.getText();
        		text += e.getEventName() + " \n";
        		text830.setText(text);
    		}
    		else {
    			String text = text800.getText();
        		text += e.getEventName() + " \n";
        		text800.setText(text);
    		}
    	}
    	if(d.getHour() == 9) {
    		if(d.getMinute() >= 30) {
    			String text = text930.getText();
        		text += e.getEventName() + " \n";
        		text930.setText(text);
    		}
    		else {
    			String text = text900.getText();
        		text += e.getEventName() + " \n";
        		text900.setText(text);
    		}
    	}
    	if(d.getHour() == 10) {
    		if(d.getMinute() >= 30) {
    			String text = text1030.getText();
        		text += e.getEventName() + " \n";
        		text1030.setText(text);
    		}
    		else {
    			String text = text1000.getText();
        		text += e.getEventName() + " \n";
        		text1000.setText(text);
    		}
    	}
    	if(d.getHour() == 11) {
    		if(d.getMinute() >= 30) {
    			String text = text1130.getText();
        		text += e.getEventName() + " \n";
        		text1130.setText(text);
    		}
    		else {
    			String text = text1100.getText();
        		text += e.getEventName() + " \n";
        		text1100.setText(text);
    		}
    	}
    	if(d.getHour() == 12) {
    		if(d.getMinute() >= 30) {
    			String text = text1230.getText();
        		text += e.getEventName() + " \n";
        		text1230.setText(text);
    		}
    		else {
    			String text = text1200.getText();
        		text += e.getEventName() + " \n";
        		text1200.setText(text);
    		}
    	}
    	if(d.getHour() == 13) {
    		if(d.getMinute() >= 30) {
    			String text = text1330.getText();
        		text += e.getEventName() + " \n";
        		text1330.setText(text);
    		}
    		else {
    			String text = text1300.getText();
        		text += e.getEventName() + " \n";
        		text1300.setText(text);
    		}
    	}
    	if(d.getHour() == 14) {
    		if(d.getMinute() >= 30) {
    			String text = text1430.getText();
        		text += e.getEventName() + " \n";
        		text1430.setText(text);
    		}
    		else {
    			String text = text1400.getText();
        		text += e.getEventName() + " \n";
        		text1400.setText(text);
    		}
    	}
    	if(d.getHour() == 15) {
    		if(d.getMinute() >= 30) {
    			String text = text1530.getText();
        		text += e.getEventName() + " \n";
        		text1530.setText(text);
    		}
    		else {
    			String text = text1500.getText();
        		text += e.getEventName() + " \n";
        		text1500.setText(text);
    		}
    	}
    	if(d.getHour() == 16) {
    		if(d.getMinute() >= 30) {
    			String text = text1630.getText();
        		text += e.getEventName() + " \n";
        		text1630.setText(text);
    		}
    		else {
    			String text = text1600.getText();
        		text += e.getEventName() + " \n";
        		text1600.setText(text);
    		}
    	}
    	if(d.getHour() == 17) {
    		if(d.getMinute() >= 30) {
    			String text = text1730.getText();
        		text += e.getEventName() + " \n";
        		text1730.setText(text);
    		}
    		else {
    			String text = text1700.getText();
        		text += e.getEventName() + " \n";
        		text1700.setText(text);
    		}
    	}
    	if(d.getHour() == 18) {
    		if(d.getMinute() >= 30) {
    			String text = text1830.getText();
        		text += e.getEventName() + " \n";
        		text1830.setText(text);
    		}
    		else {
    			String text = text1800.getText();
        		text += e.getEventName() + " \n";
        		text1800.setText(text);
    		}
    	}
    	if(d.getHour() == 19) {
    		if(d.getMinute() >= 30) {
    			String text = text1930.getText();
        		text += e.getEventName() + " \n";
        		text1930.setText(text);
    		}
    		else {
    			String text = text1900.getText();
        		text += e.getEventName() + " \n";
        		text1900.setText(text);
    		}
    	}
    	if(d.getHour() == 20) {
    		if(d.getMinute() >= 30) {
    			String text = text2030.getText();
        		text += e.getEventName() + " \n";
        		text2030.setText(text);
    		}
    		else {
    			String text = text2000.getText();
        		text += e.getEventName() + " \n";
        		text2000.setText(text);
    		}
    	}
    	if(d.getHour() == 21) {
    		if(d.getMinute() >= 30) {
    			String text = text2130.getText();
        		text += e.getEventName() + " \n";
        		text2130.setText(text);
    		}
    		else {
    			String text = text2100.getText();
        		text += e.getEventName() + " \n";
        		text2100.setText(text);
    		}
    	}
    	if(d.getHour() == 20) {
    		if(d.getMinute() >= 30) {
    			String text = text2130.getText();
        		text += e.getEventName() + " \n";
        		text2130.setText(text);
    		}
    		else {
    			String text = text2100.getText();
        		text += e.getEventName() + " \n";
        		text2100.setText(text);
    		}
    	}
    	if(d.getHour() == 22) {
    		if(d.getMinute() >= 30) {
    			String text = text2230.getText();
        		text += e.getEventName() + " \n";
        		text2230.setText(text);
    		}
    		else {
    			String text = text2200.getText();
        		text += e.getEventName() + " \n";
        		text2200.setText(text);
    		}
    	}
    	
    	if(d.getHour() >= 23) {
    		String text = text2300.getText();
    		text += e.getEventName() + " \n";
    		text2300.setText(text);
    	}
    }

    @FXML
    void addItem(ActionEvent event) {
    	TextInputDialog textDialog = new TextInputDialog();
	 	String className = User.getCurrentClass();
		String taskString = "";
		Boolean validInput;
		try
		{
			do
	        {
				textDialog.getEditor().clear();
		      	textDialog.setTitle("New Task");
		      	textDialog.setHeaderText("Please enter the To-Do item");
		      	textDialog.setContentText("Task:");
		      	textDialog.showAndWait();
		      	if(textDialog.getResult()==null)
		      		throw new Exception();
		      	taskString = textDialog.getResult();
		      	validInput = validateInput(taskString);
	        }while(!validInput);
	      	
	      	Task tempTask = new Task(taskString);
	      	//Add task to class. Need to pass class index
	      	User.getCurrentHub().getTasks().add(tempTask);
	      	//This might need to be passed to the User Hub ArrayList instead
	      	
	      	//deal with css
			//add this here, or is this part of parent ObservableList?
	        CheckBox cb = new CheckBox(taskString);
	        
	        //cb.setStyle("-fx-text-fill:white");
	        cb.setPadding(new Insets(10, 10, 0, 0));
	        cb.setOnAction(event3 -> {
	        	if (cb.isSelected()) 
	            {
	        		toDoVBoxList.remove(cb);
	            	toDoList.getChildren().clear();
	            	toDoList.getChildren().addAll(toDoVBoxList);
	            	User.deleteTask(tempTask, User.getCurrentClass());
	            	System.out.println("CHECKBOX ACTIVATED");
	            	//delete task, use taskHash and classHash as applicable, start thread, if still checked delete?
	            }
	          });
	        
	        //This adds it to the appropriate observable VBox
	       
	        toDoVBoxList.add(cb);
	        //toDoVBoxList.add(cb);
		 	//classToDo.getChildren().addAll(toDoVBoxList);
	        toDoList.getChildren().clear();
	        toDoList.getChildren().addAll(toDoVBoxList);
	        User.addTask(tempTask,"");
		}
		catch(Exception e)
		{
			
		}
    }
    
    void clearWeeklyCalendar() {
    	Sunday.setText(" ");
    	Monday.setText(" ");
    	Tuesday.setText(" ");
    	Wednesday.setText(" ");
    	Thursday.setText(" ");
    	Friday.setText(" ");
    	Saturday.setText(" ");
    	
    	Sunday.setTextFill(Color.web("#000000"));
    	Monday.setTextFill(Color.web("#000000"));
    	Tuesday.setTextFill(Color.web("#000000"));
    	Wednesday.setTextFill(Color.web("#000000"));
    	Thursday.setTextFill(Color.web("#000000"));
    	Friday.setTextFill(Color.web("#000000"));
    	Saturday.setTextFill(Color.web("#000000"));
    }
    
    void updateWeeklyCalendar() {
    	clearWeeklyCalendar();
    	
    	LocalDate currentdate = LocalDate.now();
    	System.out.println("glerdsdgf " + currentdate.getDayOfWeek().getValue());
    	switch(currentdate.getDayOfWeek().getValue()) {
    		case 1: Monday.setStyle("-fx-background-color: #fffebf"); break;
    		case 2: Tuesday.setStyle("-fx-background-color: #fffebf"); break;
    		case 3: Wednesday.setStyle("-fx-background-color: #fffebf"); break;
    		case 4: Thursday.setStyle("-fx-background-color: #fffebf"); break;
    		case 5: Friday.setStyle("-fx-background-color: #fffebf"); break;
    		case 6: Saturday.setStyle("-fx-background-color: #fffebf"); break;
    		case 7: Sunday.setStyle("-fx-background-color: #fffebf"); break;
    	}
    	
    	while(currentdate.getDayOfWeek().getValue() != 7) {
    		System.out.println(currentdate.getDayOfWeek());
    		currentdate = currentdate.minusDays(1);
    	}
    	
    	Sunday.setText(getEvent(currentdate));
    	currentdate = currentdate.plusDays(1);
    	
    	Monday.setText(getEvent(currentdate));
    	currentdate = currentdate.plusDays(1);
    	
    	Tuesday.setText(getEvent(currentdate));
    	currentdate = currentdate.plusDays(1);
    	
    	Wednesday.setText(getEvent(currentdate));
    	currentdate = currentdate.plusDays(1);
    	
    	Thursday.setText(getEvent(currentdate));
    	currentdate = currentdate.plusDays(1);
    	
    	Friday.setText(getEvent(currentdate));
    	currentdate = currentdate.plusDays(1);
    	
    	Saturday.setText(getEvent(currentdate));
    }	
    
    //What if we passed a HubEvent instead?
    String getEvent(LocalDate date) {
    	int year = date.getYear();
    	int month = date.getMonth().getValue();
    	int day = date.getDayOfMonth();
    	
    	String display = " ";
    	
    	ArrayList<HubEvent> events = User.getUserEvents();
    	
    	for(HubEvent e : events) {
    		Date d = e.getStartDate();
    		if(d.getYear() == year && d.getMonth() == month && d.getDay() == day) {
    			display += e.getEventName() + "\n ";
    		}
    	}
    	
    	return display;
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
    	boolean validInput;
    	boolean find = true;
    	int contactFound = -1;
    	ArrayList<String> contactStrings = new ArrayList<String>();
    	for (int i = 0; i < User.getUserContacts().size(); i++)
    		contactStrings.add(User.getUserContacts().get(i).getName());
    	ChoiceDialog<String> choicePopup = new ChoiceDialog<String>("Please select:", contactStrings);
        
        String input = "";
        //choicePopup = new ChoiceDialog("Please select", classNameStrings);

        try 
        {
	        do
	        {
	            choicePopup.setTitle("Delete Contact");
	            choicePopup.setHeaderText("Please select contact to remove");
	            choicePopup.setContentText("Use Dropdown menu:\n");
	            choicePopup.showAndWait();
	            if (choicePopup.getResult()==null)
	            {
	            	throw new Exception();
	            }
	            input = choicePopup.getResult().toString();
	        	validInput = validateInput(input);
	        }while(!validInput);
	        
	        int count = User.getUserContacts().size();
	        while (find)
			{
				if (count > 0)
				{
					
					//descending search for hubEntry 
					contactFound=User.getUserContacts().get(--count).compareTo(input);
					//System.out.println(count+" ");
					//if valid hubType is returned, search is complete
					if (contactFound > 0)
						find = false;
				}
				//Ends search when all values have been checked
				else
					find = false;
	
			}
	        if (contactFound > 0)
	        {
	        	userContactList.remove(choicePopup.getItems().indexOf(input));
	        	contactList.getItems().clear();
	        	contactList.getItems().addAll(userContactList);
	        	User.deleteContact(User.getUserContacts().get(count));
	        	User.getUserContacts().remove(count);
	        }
        }
        catch(Exception e)
        {
        	
        }
    }
    

    @FXML
    void saveNote(ActionEvent event) {
    	//archivedNoteList.add(User.getClasses().get(index).getNotes().get(i).getText());
    	//Add a note to the class notes
    	if (!notes.getText().equals(""))
    	{
    		Note tempNote = new Note(notes.getText());
    		User.getCurrentHub().getNotes().add(tempNote);
    		//Add the date to this as well. Wishlist make this a hyperlink with a popup
    		Alert alert = getAlert("Display Note","Note",notes.getText());
    		Hyperlink tempLink = new Hyperlink(notes.getText());
    		tempLink.setOnAction(event2 ->{
    			alert.showAndWait();
			});
    		
    		archivedNoteList.add(tempLink);
    		archivedNotes.getItems().add(tempLink);
    		
    		//Push to DB
    		User.addArchivedNote(tempNote);
    	}
    	notes.clear();
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
    
    @FXML
    void removeNote(ActionEvent event) {
    	if (archivedNoteList.size()!=0)
    	{
	    	boolean validInput;
	    	boolean find = true;
	    	int noteFound = -1;
	    	ArrayList<String> noteStrings = new ArrayList<String>();
	    	for (int i = 0; i < User.getCurrentHub().getNotes().size(); i++)
	    		noteStrings.add(User.getCurrentHub().getNotes().get(i).getText());
	    	ChoiceDialog<String> choicePopup = new ChoiceDialog<String>("Please select:", noteStrings);
	        
	        String input = "";
	        //choicePopup = new ChoiceDialog("Please select", classNameStrings);
	        try
	        {
		        do
		        {
		            choicePopup.setTitle("Delete note");
		            choicePopup.setHeaderText("Please select note to remove");
		            choicePopup.setContentText("Use Dropdown menu:\n");
		            choicePopup.showAndWait();
		            if (choicePopup.getResult()==null)
		            	throw new Exception();
		            input = choicePopup.getResult().toString();
		            validInput = validateInput(input);
		        }while(!validInput);
		        System.out.println(input);
		        int count = User.getCurrentHub().getNotes().size();
		        while (find)
				{
					if (count > 0)
					{
						noteFound=User.getCurrentHub().getNotes().get(--count).compareTo(input);
						//if valid hubType is returned, search is complete
						if (noteFound > 0)
							find = false;
					}
					//Ends search when all values have been checked
					else
						find = false;
		
				}
		        if (noteFound > 0)
		        {
		        	archivedNoteList.remove(choicePopup.getItems().indexOf(input));
		        	archivedNotes.getItems().clear();
		        	archivedNotes.getItems().addAll(archivedNoteList);
		        	User.deleteArchivedNote(User.getCurrentHub().getNotes().get(count));
		        	User.getCurrentHub().getNotes().remove(count);
		        }
	        }
	        catch(Exception e)
	        {
	        	
	        }
    	}
    }
    
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
