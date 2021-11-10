package application;
	
import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Calendar extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//for example: the user adds 3 events, the system will add them in this retrospect.
		ArrayList<CalendarEvents> sampleList = new ArrayList<>();
		CalendarEvents sample1 = new CalendarEvents(3,5,2021);
		CalendarEvents sample2 = new CalendarEvents(3,6,2021);
		CalendarEvents sample3 = new CalendarEvents(11,7,2021);
		sampleList.add(sample3);
		sampleList.add(sample2);
		sampleList.add(sample1);
		//now populate the calendar..
		CalendarInitialize init = new CalendarInitialize(0, 0, 0);
		init.populateCalendar(sampleList);
		launch(args);
	}
}
