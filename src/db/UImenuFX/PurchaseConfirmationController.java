package db.UImenuFX;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;


import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PurchaseConfirmationController {
	@FXML
	private JFXButton yes_button;
	@FXML
	private JFXButton no_button;
	@FXML
	private AnchorPane confirmation_window;

	// -----> ESSENTIAL METHODS <-----
	
	public PurchaseConfirmationController() {
		// TODO Auto-generated constructor stub
	}

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	// -----> BUTTON METHODS <-----
	@FXML
	public void yes_button() {
		
		
	}
	@FXML
	public void no_button() {
		Stage stage = (Stage) confirmation_window.getScene().getWindow();
		stage.close();
	}
	public JFXButton getYesButton() {
		return this.yes_button;
	}
	public JFXButton getNoButton() {
		return this.no_button;
	}
}
