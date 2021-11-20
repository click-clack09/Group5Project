package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

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
    
    private ObservableList<CheckBox> toDoVBoxList = FXCollections.observableArrayList();
    private ObservableList<Hyperlink> archivedNoteList = FXCollections.observableArrayList();
    private ObservableList<Hyperlink> userContactList = FXCollections.observableArrayList();
    int index;
    
    @FXML
    void initialize()
    {
    	//Add Hub tasks
    	businessHomeLabel.setText(User.getUserName()+", "+User.getCurrentHub().getHubName());
    	
    	
    	
    	if(User.getCurrentHub().getTasks()!=null)
    	{
    		System.out.println(User.getCurrentHub().getTasks().size());
    		for (int i = 0; i < User.getCurrentHub().getTasks().size(); i++)
    		{	
	    		//Make a CheckBox for each task\
    			System.out.println(User.getCurrentHub().getTasks().get(i).getText());
	        	CheckBox cb = new CheckBox(User.getCurrentHub().getTasks().get(i).getText());
	        	cb.setPadding(new Insets(10, 10, 0, 0));
	        	cb.setOnAction(event3 -> {
                    if (cb.isSelected()) 
                    {
                    	System.out.println("CHECKBOX ACTIVATED");
                    	//delete task, use taskHash and classHash as applicable, start thread, if still checked delete?
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
      	
    	do
        {
    		textDialog.getEditor().clear();
    		textDialog.setTitle("New Contact");
	     	textDialog.setHeaderText("Please enter the new contact name");
	     	textDialog.setContentText("Name:");
	     	textDialog.showAndWait();
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
			email = textDialog.getResult();
			validInput = validateInput(email);
	    }while(!validInput);
    	
    	//change this to dropdown selection
    	do
        {
    		choice = new ChoiceDialog("Please select", phoneTypes);
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

    @FXML
    void addItem(ActionEvent event) {
    	TextInputDialog textDialog = new TextInputDialog();
	 	String className = User.getCurrentClass();
		String taskString = "";
		Boolean validInput;
		
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
    void deleteNote(ActionEvent event) {

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
