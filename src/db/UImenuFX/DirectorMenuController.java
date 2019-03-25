package db.UImenuFX;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import db.jdbc.SQLManager;
import db.pojos.Director;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DirectorMenuController implements Initializable {

	// -----> CLASS ATRIBUTES <-----

	@FXML 
	private Director director_account;
	@FXML 
	private SQLManager manager_object;

	// -----> FXML ATRIBUTES <-----

	@FXML
	private AnchorPane menu_window;
	@FXML
	private Pane menu_main_pane;
	@FXML
	private JFXButton logOut_buttom;
	@FXML
	private JFXButton myAccount_buttom;
	@FXML
	private JFXButton listAllClients_buttom;
	@FXML
	private JFXButton removeClient_buttom;
	@FXML
	private JFXButton listAllWorkers_buttom;
	@FXML
	private JFXButton addWorker_buttom;
	@FXML
	private JFXButton removeWorker_buttom;
	@FXML
	private JFXButton listAllTransactions_button;
	@FXML
	private JFXButton finantialStatus_buttom;
	@FXML
	private ImageView minButtom;
	@FXML
	private ImageView exitButtom;
	@FXML
	private Label current_pane_option_label;
	@FXML
	private Label director_name;
	@FXML
	private Label email;
	@FXML
	private Label telephone;
	@FXML
	private StackPane my_account_pane;

	// -----> ESSENTIAL METHODS <-----

	public DirectorMenuController() {
		// TODO Auto-generated constructor stub
	}

	public DirectorMenuController(SQLManager manager, Director director) {
		this.director_account = director;
		this.manager_object = manager;
		System.out.println(director_account);
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
	private void min_window(MouseEvent event) {
		Stage stage = (Stage) menu_main_pane.getScene().getWindow();
		stage.setIconified(true);
	}

	@FXML
	private void my_account(MouseEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountDirectorView.fxml"));
			Parent root = (Parent) loader.load();
			System.out.println(this.director_account);
			AccountDirectorController account_controller = new AccountDirectorController(this.manager_object, this.director_account);
			account_controller = loader.getController();
			Stage stage = new Stage();
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException director_account_error) {
			director_account_error.printStackTrace();
			System.exit(0);
		}
	}

	// -----> SET AND GET METHODS <-----

	public void setDirectorName(String name) {
		this.director_name.setText("Director's name: " + name);
	}

	public void setDirectorEmail(String email) {
		if (email != null) {
			this.email.setText("Email: " + email);
		} else {
			this.email.setText("Email: No email associated");
		}
	}

	public void setDirectorTelephone(Integer telephone) {
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

	public AnchorPane getAnchorPane() {
		return this.menu_window;
	}
}
