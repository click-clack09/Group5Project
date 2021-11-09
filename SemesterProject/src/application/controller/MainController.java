package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MainController {

    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;

    @FXML
    void about(ActionEvent event) {
    	System.out.println("testAbout");
    }

    @FXML
    void addHub(ActionEvent event) {
    	System.out.println("testAddHub");
    }
    
    @FXML
    void close(ActionEvent event) {
    	System.out.println("testClose");
    }

    @FXML
    void forgot(ActionEvent event) {
    	System.out.println("testForgot");
    }

    @FXML
    void login(ActionEvent event) {
    	System.out.println("testLogin");
    }

    @FXML
    void tutorial(ActionEvent event) {
    	System.out.println("testTutorial");
    }

}
