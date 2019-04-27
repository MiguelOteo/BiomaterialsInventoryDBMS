package db.UImenuFX;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import db.jdbc.SQLManager;
import db.pojos.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RemoveClientController implements Initializable{
	
	// -----> CLASS ATRIBUTES <-----

	private static SQLManager manager_object;
	
	// -----> FXML ATRIBUTES <-----
	
	@FXML
	private AnchorPane account_window;
	@FXML
	private JFXTextField client_id_text_field;
	@FXML
	private JFXButton delete_account_button;
	
	// -----> ESSENTIAL METHODS <-----+
	
	public RemoveClientController() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		delete_account_button.setOnAction((ActionEvent event) -> {
			try {
				Client client = manager_object.Search_client_by_id(Integer.parseInt(client_id_text_field.getText()));
				manager_object.Delete_stored_user(client.getUser().getUserId());
				Stage stage = (Stage) account_window.getScene().getWindow();
				stage.close();
			} catch (NumberFormatException | NullPointerException insertion_error) {
				Stage stage = (Stage) account_window.getScene().getWindow();
				stage.close();
			}
		});
		
	}

	public static void setValues(SQLManager manager) {
		manager_object = manager;
	}
	
	public void close_window() {
		Stage stage = (Stage) account_window.getScene().getWindow();
		stage.close();
	}
	
	public JFXButton getDeleteAccountButton() {
		return delete_account_button;
	}
}
