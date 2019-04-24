package db.UImenuFX;

import java.net.URL;

import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import db.jdbc.SQLManager;
import db.pojos.Worker;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class WorkerMenuController implements Initializable {
	
    // -----> CLASS ATRIBUTES <-----

	@SuppressWarnings("unused")
	private Worker worker_account;
	@SuppressWarnings("unused")
	private SQLManager manager;

	// -----> FXML ATRIBUTES <-----

	@FXML
	private AnchorPane menu_window;
	@FXML
	private Pane menu_main_pane;
	@FXML
	private JFXButton logOut_buttom;
	@FXML
    private JFXButton logOut_button1;
    @FXML
    private JFXButton myAccount_buttom;
    @FXML
    private JFXButton myAccount_button1;
    @FXML
    private JFXButton myAccount_button2;
    @FXML
    private JFXButton myAccount_button3;
    @FXML
    private JFXButton myAccount_button4;
    @FXML
    private JFXButton myAccount_button5;
    @FXML
    private Label current_pane_option_label;
    @FXML
    private ImageView exitButtom;
    @FXML
    private ImageView minButtom;
    @FXML
    private Label worker_name;
    @FXML
    private Label email;
    @FXML
    private Label telephone;
    @FXML
    private Label current_pane_option_label1;
	
	// -----> ESSENTIAL METHODS <-----
	
	public WorkerMenuController() {
		// TODO Auto-generated constructor stub
	}
	
	public WorkerMenuController(SQLManager manager, Worker worker) {
		this.worker_account = worker;
		this.manager = manager;
	}

    

    @FXML
    void add_product_to_inventory(ActionEvent event) {
    }
    @FXML
    void clients_list(ActionEvent event) {
    }
    @FXML
    void delete_from_inventory(ActionEvent event) {
    }

    @FXML
    void list_inventory(ActionEvent event) {

    }

    @FXML
    void min_window(MouseEvent event) {

    }

    @FXML
    void transaction_records(ActionEvent event) {

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
			LaunchApplication.getStage().show();
			Stage stage = (Stage) logOut_buttom.getScene().getWindow();
			stage.close();
	}
	
	public AnchorPane getAnchorPane() {
		return this.menu_window;
	}
}
