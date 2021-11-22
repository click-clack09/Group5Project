package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import application.model.*;
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
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Separator;

public class EducationController {
	 @FXML
	 private AnchorPane mainPane;
	 
	 @FXML
    private Label educationLabel;
	 
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
	    
	    private ArrayList<Label> labels = new ArrayList<Label>();
	 
	 @FXML
    private VBox classButtonBox;
	 
     @FXML
     private VBox toDoList;
	 
	 private ObservableList<Button> classButtonList = FXCollections.observableArrayList();
	 private ObservableList<VBox> classesVBoxList = FXCollections.observableArrayList();
	 private ArrayList<String> classes;
	 private HashMap<String, Integer> classHash;
	 private HashMap<String, String> taskHash;
	 //watch VBox. Add class to observable classesList. with each class add, add an observable ToDoList in a VBox
	 //The part below gets dynamically added to the parent classesList
	 //private ObservableList<CheckBox> classToDoList = FXCollections.observableArrayList();
	 
	//This goes with item/task delete  deleteTask(Task task, String className)
	 
	 @FXML
    void initialize()
    {
		 //make String ArrayList of classes
		 initializeDailyLabels();
	    	updateDailyCalendar();
	    	
		 classes = new ArrayList<String>();
		 
		 for (SchoolClass schoolClass : User.getClasses())
		 {
			 classes.add(schoolClass.getClassName());
		 }
		 
		 classHash = new HashMap<String, Integer>();
		 taskHash = new HashMap<String, String>();
		 //Set Label
		 educationLabel.setText(User.getUserName()+", "+User.getCurrentHub().getHubName());
    	//this will instantiate the LifeHub object based on the DB query from the user view
		
		
		//LifeHub currentHub = new LifeHub(User.getCurrentHub().getHubName());
    	
		//populate the Hub (below)
		
		//add buttons and checkboxes using User.getClasses
		Button button;
		for (int i = 0; i < classes.size(); i++)
		{
			//make a class button
			button = new Button();
			button.setMinWidth(190);
			button.setMaxWidth(190);
			button.setText(classes.get(i));
			classButtonList.add(button);
			
			//make task class labels
			Label classLabel = new Label();
            //deal with css
			//classLabel.setStyle("-fx-text-fill:white");
            classLabel.setText(classes.get(i));
            //For every class, add an observable checkList
            ObservableList<CheckBox> checkList = FXCollections.observableArrayList();
            
            //make a separator and a VBox 
            Separator separator = new Separator();
            VBox temp = new VBox();
            temp.setPadding(new Insets(10, 10, 10, 10));
            
            //Add an entry to the classHash tying the class name to the index.
            //We can use this later to remove classes by index.
            classHash.put(classes.get(i), i);
            
            //this will get all tasks in ArrayList for class
            for (int j = 0; j < User.getClasses().get(i).getAssignments().size(); j++)
            {
            	//Make a CheckBox for each task
            	CheckBox cb = new CheckBox(User.getClasses().get(i).getAssignments().get(j).getText());
                //cb.setStyle("-fx-text-fill:white");
                cb.setPadding(new Insets(10, 10, 0, 0));
                Task tempTask = new Task(User.getClasses().get(i).getAssignments().get(j).getText());
                String tempClass = User.getClasses().get(i).getClassName();
                cb.setOnAction(event3 -> {
                	if (cb.isSelected()) 
                    {
                		User.deleteTask(tempTask, tempClass);
                    	System.out.println("CHECKBOX ACTIVATED");
                    	//delete task, use taskHash and classHash as applicable, start thread, if still checked delete?
                    }
                  });
                checkList.add(cb);
                //add HashMap Entry for task text and className (String)
                taskHash.put(cb.getText(), classes.get(i));
            }
            
            //only add tempInner when adding new class, otherwise reference it
            VBox tempInner = new VBox();
            tempInner.getChildren().addAll(checkList);
            temp.getChildren().addAll(classLabel,separator,tempInner);
            toDoList.getChildren().add(temp);
            classesVBoxList.add(temp);
		}
		
		//assign an action to the buttons
    	for (int i =0; i <classButtonList.size(); i++)
    	{
    		
    		classButtonList.get(i).setOnAction(event ->{
    			try
    			{
    				//Where did you come from?
    				setCallingClass(event.getSource().toString());
	    			User.setLastHub(new File("src/application/view/EducationHome.fxml").toURI().toURL());
	    			//Where should you go?
	    			URL url = new File("src/application/view/EdClass.fxml").toURI().toURL();
	    	    	mainPane = FXMLLoader.load(url);
	    	        Scene scene = new Scene(mainPane);// pane you are GOING TO show
	    	        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
	    	        window.setScene(scene);
	    	        window.show();
    			}
    			
    			catch(IOException e)
    			{
    				
    			}
    		});
    	}
    	
    	classButtonBox.getChildren().addAll(classButtonList);
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
	    
	    ///CLASS VALUES NEED TO NOT BE NULL
	    @FXML
	    void addClass(ActionEvent event) {
	    	TextInputDialog textDialog = new TextInputDialog();
	    	String className = "";
	    	String location = "";
	    	String professor = "";
	    	boolean validInput;
	      	//while(!validInput)
	          //{
	      	 
	    	  //this will need input validation, particularly for "New Class"
            do  
            {
            	textDialog.getEditor().clear();
	    		textDialog.setTitle("New Class");
	    		textDialog.setHeaderText("Please enter the new class name");
	    		textDialog.setContentText("Class name:");
	    		textDialog.showAndWait();
	    		className = textDialog.getResult();
	    		validInput = validateInput(className);
            }while(!validInput);
	    		
              //make a new button
              
              Button button = new Button();
              button.setMinWidth(190);
              button.setMaxWidth(190);
              button.setText(className);
              classButtonList.add(button);
              
              //add the class to the User classes ArrayList
              //SchoolClass tempClass =new SchoolClass(className,professor,location, new ArrayList<Task>(),new HubEvent(), new ArrayList<Task>());
              
              //User.getClasses().add(tempClass);
              
              //add the String className to the classes ArrayList and to the classhash with the index
              classes.add(className);
              classHash.put(classes.get(classes.size()-1), classes.size()-1);
              
              //make a new task class CheckList
              Label classLabel = new Label();
              //deal with css
              //classLabel.setStyle("-fx-text-fill:white");
              classLabel.setText(className);
              //add this here, or is this part of parent ObservableList?
              ObservableList<CheckBox> checkList = FXCollections.observableArrayList();
              Separator separator = new Separator();
              VBox temp = new VBox();
              temp.setPadding(new Insets(10, 10, 0, 0));
              temp.getChildren().addAll(classLabel,separator);
              toDoList.getChildren().add(temp);
              classesVBoxList.add(temp);
              
              do
              {
	              textDialog.getEditor().clear();
	              textDialog.setTitle("New Class");
	              textDialog.setHeaderText("Please enter the new class location");
	              textDialog.setContentText("Location:");
	              textDialog.showAndWait();
	              location = textDialog.getResult();
	              validInput = validateInput(location);
              }while(!validInput);
              
              do
              {
	              textDialog.getEditor().clear();
	              textDialog.setTitle("New Class");
	              textDialog.setHeaderText("Please enter the new class instructor");
	              textDialog.setContentText("Instructor:");
	              textDialog.showAndWait();
	              professor = textDialog.getResult();
	              validInput = validateInput(professor);
              }while(!validInput);
              
              ///////////////////////////DEAL WITH THIS??????????///////////////////////////////
              HubEvent meetingTime = new HubEvent(); 
              
              System.out.println(className+" "+location+" "+professor);
              
              //use this class to set DB entry
              SchoolClass newClass = new SchoolClass(className, professor, location, new ArrayList<Task>(), meetingTime, new ArrayList<Note>());
            //SCHOOL_CLASS_ID	USER_ID	SCHOOL_CLASS_NAME	SCHOOL_CLASS_LOCATION	SCHOOL_CLASS_PROFESSOR
              User.addClass(newClass);
              
            //add the class to the User classes ArrayList
              //SchoolClass tempClass =new SchoolClass(className,professor,location, new ArrayList<Task>(),new HubEvent(), new ArrayList<Task>());
              
              User.getClasses().add(newClass);
              User.getCurrentHub();
             
              //get the last button add the setOnAction
              classButtonList.get(classButtonList.size()-1).setOnAction(event1 ->{
	        	  try
	        	  {
	        		  setCallingClass(event1.getSource().toString());
	        		  User.setLastHub(new File("src/application/view/EducationHome.fxml").toURI().toURL());          			
	        		  URL url = new File("src/application/view/EdClass.fxml").toURI().toURL();
	        		  mainPane = FXMLLoader.load(url);
	        		  Scene scene = new Scene(mainPane);// pane you are GOING TO show
	        		  //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	        		  Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
	        		  window.setScene(scene);
	        		  window.show();
	        	  }
	        	  catch(IOException e)
	        	  {
	        		  //deal with this later
	        	  }
              });
              classButtonBox.getChildren().clear();
      		classButtonBox.getChildren().addAll(classButtonList);
//              System.out.println(newClass.getProfessor()+" "+newClass.getLocation()+" "+newClass.getMeetingTime());
              
	    }
	    
	    @FXML
	    void removeClass(ActionEvent event) {
	    	//ask for class from drop-down popup
	    	ArrayList<String> classNameStrings = new ArrayList<String>();
	    	boolean validInput;
	    	
	    	for (int i = 0; i < User.getClasses().size(); i++)
	    		classNameStrings.add(User.getClasses().get(i).getClassName());
	    	ChoiceDialog<String> choicePopup = new ChoiceDialog<String>("Please select:", classNameStrings);
            
            String input = "";
            //choicePopup = new ChoiceDialog("Please select", classNameStrings);
            try
            {
	            do
	            {
		            choicePopup.setTitle("Delete Class");
		            choicePopup.setHeaderText("Please select class to remove");
		            choicePopup.setContentText("Use Dropdown menu:\n");
		            choicePopup.showAndWait();
		            if(choicePopup.getResult()==null)
		            	throw new Exception();
		            input = choicePopup.getResult().toString();
		            validInput = validateInput(input);
	            }while(!validInput);
		    	//match class to correct class object
	            for (int i = 0; i < User.getClasses().size(); i++)
	            {
	            	if (User.getClasses().get(i).compareTo(input) > 0)
	            	{
	            		User.deleteClass(User.getClasses().get(i));
	            		break;
	            	}
	            }
            }
            catch(Exception e)
            {
            	
            }
            
            //remove button, remove 
            	
	    }
	    
	    //Used to determine the source of the event and set the Current class when changing to class view
	    public void setCallingClass(String caller)
	    {
	    	String[] buttonSplit;
	    	buttonSplit=caller.split("\'");
  			//System.out.println(buttonSplit[1]);
	    	User.setCurrentClass(buttonSplit[1]);
	    	System.out.println(User.getCurrentClass());
	    }

	    //This will add a task
	    @FXML
	    void addItem(ActionEvent event) {
	    	
	    	//This is added to allow for new classes to be added on the fly
	    	//classes.add(0,"New Class");	
	    	ChoiceDialog<String> selection = new ChoiceDialog<String>("Select Class",classes);
	    	TextInputDialog textDialog = new TextInputDialog();
	    	String className = "";
	    	String taskString = "";
	    	boolean validInput;
	    	
	    	do
	    	{
		    	selection.setTitle("New Task");
		    	selection.setHeaderText("Which class is the task for?");
		    	selection.setContentText("Class:");
		      	selection.showAndWait();
	            className = selection.getResult();
	            validInput = validateInput(className);
	    	}while(!validInput);
            //remove it here though to not disrupt indexing
	      	//classes.remove("New Class");
	      	//if className.equals("New Class"); <------------deal with this
            
	    	do
	    	{
		      	textDialog.getEditor().clear();
		      	textDialog.setTitle("New Task");
		      	textDialog.setHeaderText("Please enter the To-Do item");
		      	textDialog.setContentText("Task:");
		      	textDialog.showAndWait();
		      	taskString = textDialog.getResult();
		      	validInput = validateInput(taskString);
	    	}while(!validInput);
	      	//add HashMap Entry for task text and className (String)
	      	taskHash.put(taskString, className);
	      	
	      	Task tempTask = new Task(taskString);
	      	//Add task to class
	      	User.getClasses().get((int) classHash.get(className)).getAssignments().add(tempTask);
	      	
	      	//deal with css
			//add this here, or is this part of parent ObservableList?
            CheckBox cb = new CheckBox(taskString);
            //cb.setStyle("-fx-text-fill:white");
            cb.setPadding(new Insets(10, 10, 0, 0));
            String tempClass = User.getClasses().get((int) classHash.get(className)).getClassName();
            cb.setOnAction(event3 -> {
            	if (cb.isSelected()) 
                {
                	User.deleteTask(tempTask, tempClass);
                	System.out.println("CHECKBOX ACTIVATED");
                	//delete task, use taskHash and classHash as applicable, start thread, if still checked delete?
                }
              });
            //This adds it to the appropriate observable VBox
            int VBoxIndex = (int) classHash.get(className);
            classesVBoxList.get(VBoxIndex).getChildren().add(cb);
            
            User.addTask(tempTask, className);
	      	
	    }
	    @FXML
	    void addHub(ActionEvent event) {
	    	System.out.println("testHub");
	    }
	    
//	    @FXML
//	    void goToClass2(ActionEvent event) {
//	    	System.out.println("testClass2");
//	    }
	    	
//	 @FXML
//	    void goToClass1(ActionEvent event) throws IOException {
//		User.setLastHub(new File("src/application/view/EducationHome.fxml").toURI().toURL());
//    	URL url = new File("src/application/view/EdClass.fxml").toURI().toURL();
//    	mainPane = FXMLLoader.load(url);
//        Scene scene = new Scene(mainPane);// pane you are GOING TO show
//        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
//        window.setScene(scene);
//        window.show();
//    }
	 
	 @FXML
	    void goToCalendar(ActionEvent event) throws IOException {
		User.setLastHub(new File("src/application/view/EducationHome.fxml").toURI().toURL());
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
	    void userHome(ActionEvent event) throws IOException {
	    	URL url = new File("src/application/view/UserHome.fxml").toURI().toURL();
	   		mainPane = FXMLLoader.load(url);
	   		//mainPane = FXMLLoader.load(getClass().getClassLoader().getResource("Classified.fxml"));// pane you are GOING TO
	   		Scene scene = new Scene(mainPane);// pane you are GOING TO show
	   		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	   		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
	   		window.setScene(scene);
	   		window.show();
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
	 public boolean validateInput(String input)
	    {
	    	if(input != null)
	    	{
	    		if (!input.equals(""))
	    			return true;
	    	}
	    	return false;
	    }
	 //could move stuff from lambda expression hear for cleaner flow
//	 void goToClass(ActionEvent event)
//	 {
//		 
//	 }
}