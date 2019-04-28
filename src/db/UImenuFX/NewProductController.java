package db.UImenuFX;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import db.jdbc.SQLManager;
import db.pojos.Biomaterial;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class NewProductController implements Initializable {
	
	// -----> CLASS ATTRIBUTESS <-----
		private static SQLManager manager_object;
		private ChargeIconController charging_controller;
		
	// -----> FXML ATTRIBUTES <-----
	
    @FXML
    private AnchorPane menu_window;
    @FXML
    private Pane menu_main_pane;
    @FXML
    private JFXButton conclude_button;
    @FXML
    private JFXTextField name_field;
    @FXML
    private Spinner<Integer> price_button;
    @FXML
    private Spinner<Integer> units_button;
    @FXML
    private JFXDatePicker date_picker;
    @FXML
    private JFXCheckBox utility_button;
    @FXML
    private JFXCheckBox maintenance_button;
    @FXML
    private JFXButton logOut_button;
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
    private Label current_option_label;
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

    
    public NewProductController() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    public static void setValues(SQLManager manager) {
		manager_object = manager;
	}
    
    
 // -----> GETTERS AND SETTERS <-----
    
    public JFXButton getConclude_button() {
		return conclude_button;
	}

	public void setConclude_button(JFXButton conclude_button) {
		this.conclude_button = conclude_button;
	}

	public JFXTextField getName_field() {
		return name_field;
	}

	public void setName_field(JFXTextField name_field) {
		this.name_field = name_field;
	}

	public Spinner<Integer> getPrice_button() {
		return price_button;
	}

	public void setPrice_button(Spinner<Integer> price_button) {
		this.price_button = price_button;
	}

	public Spinner<Integer> getUnits_button() {
		return units_button;
	}

	public void setUnits_button(Spinner<Integer> units_button) {
		this.units_button = units_button;
	}

	public JFXDatePicker getDate_picker() {
		return date_picker;
	}

	public void setDate_picker(JFXDatePicker date_picker) {
		this.date_picker = date_picker;
	}

	public JFXCheckBox getUtility_button() {
		return utility_button;
	}

	public void setUtility_button(JFXCheckBox utility_button) {
		this.utility_button = utility_button;
	}

	public JFXCheckBox getMaintenance_button() {
		return maintenance_button;
	}

	public void setMaintenance_button(JFXCheckBox maintenance_button) {
		this.maintenance_button = maintenance_button;
	}
    
    
    
 // -----> METHODS <-----
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			/*String product_name = name_field.getText();
			Integer units = units_button.getValue();
			Integer price = price_button.getValue();
			String exp_date = date_picker.getDayCellFactory().toString();
			
			//Faltan los optativos checkBox para maintenance y utility
			
			Biomaterial biomaterial = manager_object.Search_biomaterial_by_id(null);
			manager_object.Insert_new_biomaterial(biomaterial);
			
			
			*/
			
		} catch (Exception insertion_error) {
			insertion_error.printStackTrace();
		}
		
		conclude_button.setOnAction((ActionEvent event) -> {
			try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ChargeIconView.fxml"));
			Parent root = (Parent) loader.load();
			this.charging_controller = new ChargeIconController();
			this.charging_controller = loader.getController();
			Stage stage = new Stage();
			//stage.setOnShowing((event_handler) -> this.charging_controller.searching_create_account(user_name, password, user_type_string));
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setAlwaysOnTop(true);
			stage.setScene(new Scene(root));
			stage.show();
			
			//Al acabar muestra icono de cargando para simularnos que hemos acabado
			PauseTransition wait = new PauseTransition(Duration.seconds(2));
			wait.setOnFinished(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					if(charging_controller.getWorker_controller() != null) {
						charging_controller.removeBlur();
					}
					stage.close();
				}
	        });
			wait.play();
			
			root = FXMLLoader.load(getClass().getResource("WorkerMenuView.fxml"));
			LaunchApplication.getStage().getScene().setRoot(root);
			
			} catch (IOException conclude_error) {
				conclude_error.printStackTrace();
			}
		});
		
		
    
}
    
 // -----> BUTTON METHODS <-----

	@FXML
    void close_app(MouseEvent event) {
    	System.exit(0);
    }

    @FXML
    void log_out(MouseEvent event) {
    	LaunchApplication.getStage().show();
		Stage stage = (Stage) logOut_button.getScene().getWindow();
		stage.close();
    }

    @FXML
    void min_window(MouseEvent event) {
    	Stage stage = (Stage) menu_main_pane.getScene().getWindow();
		stage.setIconified(true);
    }


	

}

