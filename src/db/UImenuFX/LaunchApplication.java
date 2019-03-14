package db.UImenuFX;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LaunchApplication extends Application{
	
	public static Stage stage;
	
	@Override @SuppressWarnings("static-access")
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("LogInView.fxml"));
			primaryStage.setTitle("Log in page");
			Scene scene = new Scene(root);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setScene(scene);
			this.stage = primaryStage;
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
