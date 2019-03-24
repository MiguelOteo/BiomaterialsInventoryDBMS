package db.UImenuFX;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import db.jdbc.SQLManager;
import db.pojos.Worker;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WorkerMenuController implements Initializable {
	
    // -----> CLASS ATRIBUTES <-----

	@SuppressWarnings("unused")
	private ObjectProperty<Worker> worker_account;
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
	
	public WorkerMenuController() {
		// TODO Auto-generated constructor stub
	}
	
	public WorkerMenuController(ObjectProperty<SQLManager> manager, Worker worker) {
		this.worker_account = new SimpleObjectProperty<>(worker);
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
}
