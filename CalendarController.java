package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;

import application.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
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
    void initialize() {
    	System.out.println("Hello");
    	
    	LocalDate currentdate = LocalDate.now();
    	
    	YearMonth ym = YearMonth.of(currentdate.getYear(), currentdate.getMonth());

    	int firstDay = ym.atDay(1).getDayOfWeek().getValue();
    	
    	switch(firstDay) {
    	case 0: Sunday1.setText(); //get text and do rest
    	case 1:
    	case 2:
    	case 3:
    	case 4:
    	case 5:
    	case 6:
    	case 7:
    	}
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
    void goBack(ActionEvent event) throws IOException {
   		//URL url = new File("src/EducationHome.fxml").toURI().toURL();
    	mainPane = FXMLLoader.load(User.getLast());
    	User.setLast(new File("src/application/view/Calendar.fxml").toURI().toURL());
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
