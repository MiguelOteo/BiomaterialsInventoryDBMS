package db.UImenuFX;

import java.io.IOException;
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
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;


public class WorkerMenuController implements Initializable {
	
    // -----> CLASS ATRIBUTES <-----

	@SuppressWarnings("unused")
	private static Worker worker_account;
	@SuppressWarnings("unused")
	private static SQLManager manager;

	// -----> FXML ATRIBUTES <-----

	 	@FXML
	    private AnchorPane menu_window;
	    @FXML
	    private Pane menu_main_pane;
	    @FXML
	    private JFXButton logOut_button1;
	    @FXML
	    private JFXButton myAccount_button;
	    @FXML
	    private JFXButton listInventory_button;
	    @FXML
	    private JFXButton addProduct_button;
	    @FXML
	    private JFXButton removeProduct_button;
	    @FXML
	    private JFXButton listTransactions_button;
	    @FXML
	    private JFXButton listClients_button;
	    @FXML
	    private Label current_pane_option_label;
	    @FXML
	    private ImageView exitButton;
	    @FXML
	    private ImageView minButton;
	    @FXML
	    private Label worker_name;
	    @FXML
	    private Label email;
	    @FXML
	    private Label telephone;
	    @FXML
	    private Label current_pane_option_label1;
	    @FXML
	    private static Stage my_account;

	
	// -----> ESSENTIAL METHODS <-----
	
	public WorkerMenuController() {
		// TODO Auto-generated constructor stub
	}
	
	public WorkerMenuController(SQLManager manager, Worker worker) {
		this.worker_account = worker;
		this.manager = manager;
	}

	public void update_worker_account() {
    	worker_account = manager.Search_worker_by_id(worker_account.getWorker_id());
    	setWorkerEmail(worker_account.getEmail());
    	setWorkerName(worker_account.getWorker_name());
		setWorkerTelephone(worker_account.getTelephone());
	}
	
	public void setWorkerName(String name) {
		this.worker_name.setText("Worker's name: " + name);
	}

	public void setWorkerEmail(String email) {
		if (email != null) {
			this.email.setText("Email: " + email);
		} else {
			this.email.setText("Email: No email associated");
		}
	}

	public void setWorkerTelephone(Integer telephone) {
		if (telephone == null) {
			this.telephone.setText("Telephone: No telephone associated");
		} else {
			if (telephone != 0) {
				this.telephone.setText("Telephone: " + telephone);
			} else {
				this.telephone.setText("Telephone: No telephone associated");
			}
		}
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
    	Stage stage = (Stage) menu_main_pane.getScene().getWindow();
		stage.setIconified(true);
    }

    @FXML
    void transaction_records(ActionEvent event) {

    }

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		myAccount_button.setOnAction((ActionEvent) -> {
			try {
				AccountDirectorController.setValuesWorker(manager, worker_account);
				FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountDirectorView.fxml"));
				Parent root = (Parent) loader.load();
				AccountDirectorController account_controller = new AccountDirectorController();
				account_controller = loader.getController();
				account_controller.done_button.setOnMouseClicked(new EventHandler<Event>() {
					@Override
					public void handle(Event event) {
						update_worker_account();
						menu_window.setEffect(null);
						my_account.close();
					}
				});	
				my_account = new Stage();
				my_account.initStyle(StageStyle.UNDECORATED);
				my_account.setScene(new Scene(root));
				my_account.setAlwaysOnTop(true);				
				my_account.setOnShowing(new EventHandler<WindowEvent>() {
					@Override
					public void handle(WindowEvent arg0) {
						menu_window.setEffect(new BoxBlur(3,3,3));
					    myAccount_button.setDisable(true);
					    listInventory_button.setDisable(true);
					    addProduct_button.setDisable(true);
					    removeProduct_button.setDisable(true);
					    listTransactions_button.setDisable(true);
					    listClients_button.setDisable(true);
					    logOut_button1.setDisable(true);
					    minButton.setDisable(true);
					    exitButton.setDisable(true);
					}
				});
				my_account.setOnHiding(new EventHandler<WindowEvent>() {		
					@Override
					public void handle(WindowEvent event) {
						myAccount_button.setDisable(false);
						listInventory_button.setDisable(true);
						addProduct_button.setDisable(true);
						removeProduct_button.setDisable(true);
						listTransactions_button.setDisable(true);
						listClients_button.setDisable(true);
					    logOut_button1.setDisable(true);
					    minButton.setDisable(true);
					    exitButton.setDisable(true);
						menu_window.setEffect(null);
					}
				});		
				my_account.show();
			} catch (IOException director_account_error) {
				director_account_error.printStackTrace();
				System.exit(0);
			}
		});
			
			// Biomaterials list columns creation
			
		
		

		
	}
	
	// -----> BUTTOM METHODS <-----

	@FXML
	private void close_app(MouseEvent event) {
			System.exit(0);
    }

	@FXML
	private void log_out(MouseEvent event) {
			LaunchApplication.getStage().show();
			Stage stage = (Stage) logOut_button1.getScene().getWindow();
			stage.close();
	}
	
	public AnchorPane getAnchorPane() {
		return this.menu_window;
	}
}
