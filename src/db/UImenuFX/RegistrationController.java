package db.UImenuFX;


import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class RegistrationController {
	
	@FXML
	private Pane registrationPane;
	
	@FXML 
	private JFXComboBox<Label> user_type;


	public RegistrationController() {

	}
	
	public void initialize(URL location, ResourceBundle resources) {
		user_type.getItems().addAll(new Label("Director"), new Label("Client"), new Label("Worker"));
	}
}
