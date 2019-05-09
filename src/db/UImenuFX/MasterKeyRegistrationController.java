package db.UImenuFX;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class MasterKeyRegistrationController implements Initializable{

	// -----> FXML ATRIBUTES <----- 
	
	@FXML
	private JFXPasswordField password;
	@FXML
	private JFXButton done_button;
	
	public MasterKeyRegistrationController() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	
	// -----> GET METHODS <-----
	
	public String getPassword() {
			return password.getText();
	}
	
	public JFXButton getButton() {
		return done_button;
	}
}
 