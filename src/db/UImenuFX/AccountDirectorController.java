package db.UImenuFX;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import db.jdbc.SQLManager;
import db.pojos.Director;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

public class AccountDirectorController implements Initializable {

	// -----> CLASS ATRIBUTES <-----
	
	private Director director_account;
	private SQLManager manager_object;
	
	// -----> FXML ATRIBUTES <-----
	
	@FXML
	private JFXButton update_button;
	@FXML
	private JFXButton change_buttom;
	@FXML
	private JFXButton done_button;
    @FXML
    private JFXButton delete_account_button;
    @FXML
    private JFXTextField name_field;
    @FXML
    private JFXTextField email_field;
    @FXML
    private JFXTextField telephone_field;
    @FXML
    private JFXPasswordField password_field;
    @FXML
    private JFXPasswordField repeat_password_field;
    @FXML
    private JFXPasswordField new_password_field;
    
    // -----> ESSENTIAL METHODS <-----+
	
    public AccountDirectorController() {
		// TODO Auto-generated constructor stub
	}
    
    public AccountDirectorController(SQLManager manager, Director director) {
    	this.director_account = director;
    	this.manager_object = manager;
	}
    
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
    // -----> BUTTON METHODS <-----
	
	@FXML
	private void close_app(MouseEvent event) {
		System.exit(0);
	}
	
	@FXML
	private void update_information() {
		if(this.name_field.getText() != null) {
			this.director_account.setDirector_name(this.name_field.getText());
		}
		if(this.email_field.getText() != null) {
			this.director_account.setEmail(this.email_field.getText());
		} 
		if(this.telephone_field.getText() != null) {
			this.director_account.setTelephone(Integer.parseInt(this.telephone_field.getText()));
		}
		this.manager_object.Update_director_info(this.director_account);
	}
}


























