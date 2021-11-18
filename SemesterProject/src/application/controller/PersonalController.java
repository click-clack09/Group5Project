package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import application.model.Note;
import application.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PersonalController {

	@FXML
	 private AnchorPane mainPane;
	
	@FXML
    private VBox userLists;

    @FXML
    private TextArea note;

    @FXML
    private ListView<Hyperlink> archivedNotes;
    
    @FXML
    private Label personalLabel;
    
    private ObservableList<Hyperlink> archivedNoteList = FXCollections.observableArrayList();
	
    
    @FXML 
    void initialize() {
    	
    	personalLabel.setText(User.getUserName()+", "+User.getCurrentHub().getHubName());
    	if(User.getCurrentHub().getNotes() !=null)
    	{
    		
    		for (int i = 0; i < User.getCurrentHub().getNotes().size(); i++)
    		{	
    			Hyperlink tempLink = new Hyperlink(User.getCurrentHub().getNotes().get(i).getText());
    			
    			 
    			Alert alert = getAlert("Display Note","Note",User.getCurrentHub().getNotes().get(i).getText());
              	
                tempLink.setOnAction(event2 ->{
              	  alert.showAndWait();
              	  
                });
                archivedNoteList.add(tempLink);
    		}
    		//Add the date to this as well. Wishlist make this a hyperlink with a popup
    		archivedNotes.getItems().addAll(archivedNoteList);

    		///////////
    		
            
            
    		
    		    
            //probably should consider how to delete notes
    	}
    }
    @FXML
    void about(ActionEvent event) {

    }

    @FXML
    void addItem(ActionEvent event) {

    }
    
    @FXML
    void addHub(ActionEvent event) {
    	System.out.println("testAddHub");
    }

    @FXML
    void addList(ActionEvent event) {

    }

    @FXML
    void changeTheme(ActionEvent event) {

    }

    @FXML
    void close(ActionEvent event) {

    }

    @FXML
    void deleteHub(ActionEvent event) {

    }

    @FXML
    void goBack(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) {

    }

    @FXML
    void saveNote(ActionEvent event) {
    	//archivedNoteList.add(User.getClasses().get(index).getNotes().get(i).getText());
    	//Add a note to the class notes
    	User.getCurrentHub().getNotes().add(new Note(note.getText()));
    	//Add the date to this as well. Wishlist make this a hyperlink with a popup
    	Alert alert = getAlert("Display Note","Note",note.getText());
    	Hyperlink tempLink = new Hyperlink(note.getText());
    	tempLink.setOnAction(event2 ->{
        	  alert.showAndWait();
        	  
          });
        archivedNotes.getItems().add(tempLink);
    	//Push to DB
    	note.clear();
    }

    @FXML
    void selectList(ActionEvent event) {

    }

    @FXML
    void tutorial(ActionEvent event) {

    }

    @FXML
    void userHome(ActionEvent event) throws IOException {
    	URL url = new File("src/application/view/UserHome.fxml").toURI().toURL();
   		mainPane = FXMLLoader.load(url);
   		//mainPane = FXMLLoader.load(getClass().getClassLoader().getResource("Classified.fxml"));// pane you are GOING TO
   		Scene scene = new Scene(mainPane);// pane you are GOING TO show
   		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
   		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
   		System.out.println("5");
   		window.setScene(scene);
   		System.out.println("6");
	    window.show();
	}
    
    @FXML
    void goToCalendar(ActionEvent event) throws IOException {
   	User.setLastHub(new File("src/application/view/PersonalHome.fxml").toURI().toURL());
    	URL url = new File("src/application/view/Calendar.fxml").toURI().toURL();
    	mainPane = FXMLLoader.load(url);
    	//mainPane = FXMLLoader.load(getClass().getClassLoader().getResource("Classified.fxml"));// pane you are GOING TO
        Scene scene = new Scene(mainPane);// pane you are GOING TO show
        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
        window.setScene(scene);
        window.show();
	    }
    
    public Alert getAlert(String title, String header, String content)
    {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle(title);
    	alert.setHeaderText(header);
    	alert.setContentText(content);
    	
    	return alert;
    }

}
