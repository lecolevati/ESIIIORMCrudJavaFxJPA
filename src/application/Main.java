package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		try {
			AnchorPane anchorPane =  FXMLLoader.load(this.getClass().getResource("Main.fxml"));
			Scene scene = new Scene(anchorPane);

			primaryStage.setScene(scene);
			primaryStage.setTitle("CRUD JPA Hibernate");
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

	public static void main(String[] args) {
		launch(args);
	}
}
