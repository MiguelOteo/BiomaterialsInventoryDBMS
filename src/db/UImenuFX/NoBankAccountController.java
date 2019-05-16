package db.UImenuFX;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NoBankAccountController implements Initializable{
	
	// -----> FXML ATRIBUTES <-----
	
	@FXML
	private JFXButton done_button;
	@FXML
	private AnchorPane account_window;

	// -----> ESSENTIAL METHODS <-----
	
	public NoBankAccountController() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	// -----> BUTTON METHODS <-----
	
	@FXML
	public void done_button() {
		Stage stage = (Stage) account_window.getScene().getWindow();
		stage.close();
	}
}
