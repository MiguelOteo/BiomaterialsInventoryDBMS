package db.UImenuFX;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import db.jdbc.SQLManager;
import db.pojos.Client;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ClientMenuController implements Initializable {

	// -----> CLASS ATRIBUTES <-----

	@SuppressWarnings("unused")
	private ObjectProperty<Client> client_account;
	@SuppressWarnings("unused")
	private ObjectProperty<SQLManager> manager;

	// -----> FXML ATRIBUTES <-----

	@FXML
	private AnchorPane menu_window;
	@FXML
	private Pane menu_main_pane;
	@FXML
	private JFXButton logOut_buttom;

	// -----> ESSENTIAL METHODS <-----

	public ClientMenuController() {
		// TODO Auto-generated constructor stub
	}

	public ClientMenuController(ObjectProperty<SQLManager> manager, Client client) {
		this.client_account = new SimpleObjectProperty<>(client);
		this.manager = manager;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}

	// -----> BUTTOM METHODS <-----

	@FXML
	private void close_app(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	private void log_out(MouseEvent event) {
			LaunchApplication.stage.show();
			Stage stage = (Stage) logOut_buttom.getScene().getWindow();
			stage.close();
	}

	@FXML
	private void open_marketplace(MouseEvent event) {
		// try {

		// } catch (IOException marketplace_charge_error) {
      
		// }
	}
	
	public AnchorPane getAnchorPane() {
		return this.menu_window;
	}
}
