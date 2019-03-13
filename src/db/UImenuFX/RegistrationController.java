package db.UImenuFX;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class RegistrationController {
	
	// -----> CLASS ATRIBUTES <-----
	
	private String user_name;
	private String password;
	
	// -----> FXML ATRIBUTES <-----
	
	@FXML
	private Pane registrationPane;
	@FXML 
	private JFXComboBox<Label> user_type;

    // -----> ESSENTIAL METHODS <-----
	
	public RegistrationController() {

	}
	
	public void initialize(URL location, ResourceBundle resources) {
		user_type.getItems().addAll(new Label("Director"), new Label("Client"), new Label("Worker"));
	}
	
	// -----> BUTTOM METHODS <-----
	
	@FXML // It is triggered when "go back" buttom is pressed
	private void back_to_menu (MouseEvent event) throws IOException {
	    Parent root = FXMLLoader.load(getClass().getResource("LogInView.fxml"));
		LaunchApplication.stage.getScene().setRoot(root);
	}
	
    @FXML // It is triggered when "extitCross.png" is pressed
    private void close_app (MouseEvent event) throws IOException {
    	System.exit(0);
    }
    
    // -----> GET METHODS FOR TEXT JFXFIELDS <-----
    
    public String getUser_name() {
    	return "";
    }
    
    public String getPassword() {
    	return "";
    }
}
