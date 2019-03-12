package db.UImenuFX;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LaunchApplication extends Application{
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("LogInView.fxml"));
			primaryStage.setTitle("Log in page");
			primaryStage.setScene(new Scene(root, 800, 600));
			primaryStage.show();
		} catch (IOException fatal_error) {
			fatal_error.printStackTrace();
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
