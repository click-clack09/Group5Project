package application;
	
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import application.model.LifeHub;
import application.model.User;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

/**This class launches the initial view for LifeHub.
 * 
 * @author group 5 11-23-21
 *
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			URL url = new File("src/application/view/Login.fxml").toURI().toURL();
	    	AnchorPane root = FXMLLoader.load(url);
			//AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root,800,650);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("LifeHub");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
