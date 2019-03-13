package db.UImenuFX;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class LogInController implements Initializable {
	
	@FXML
	private AnchorPane startUpMenu;
	@FXML
	private Pane logInPane;

	public LogInController() {
		// TODO Auto-generated constructor stub
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		
	}

    @FXML
	private void open_registration () throws IOException {
		Pane registration_pane_fxml = FXMLLoader.load(getClass().getResource("RegistrationView.fxml"));
		logInPane.getChildren().removeAll(); 
		logInPane.getChildren().setAll(registration_pane_fxml);
	}
}
