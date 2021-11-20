package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.stage.Stage;
import javafx.scene.control.Separator;

public class EducationController {
	 @FXML
	 private AnchorPane mainPane;
	 
	 @FXML
    private Label educationLabel;
	 
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
	 
	 @FXML
    void initialize()
    {
		 //make String ArrayList of classes
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
                cb.setOnAction(event3 -> {
                    if (cb.isSelected()) 
                    {
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

            do
            {
	            choicePopup.setTitle("Delete Class");
	            choicePopup.setHeaderText("Please select class to remove");
	            choicePopup.setContentText("Use Dropdown menu:\n");
	            choicePopup.showAndWait();
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
            cb.setOnAction(event3 -> {
                if (cb.isSelected()) 
                {
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