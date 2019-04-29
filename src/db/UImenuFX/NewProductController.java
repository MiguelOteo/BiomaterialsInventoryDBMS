package db.UImenuFX;

import java.io.IOException;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import db.jdbc.SQLManager;
import db.pojos.Biomaterial;
import javafx.event.ActionEvent;
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
import javafx.stage.WindowEvent;

public class NewProductController implements Initializable {
	
	// -----> CLASS ATTRIBUTESS <-----
		private static SQLManager manager_object;
		
	// -----> FXML ATTRIBUTES <-----
	
		@FXML
	    private Pane new_product_pane;

	    @FXML
	    private JFXButton conclude_button;

	    @FXML
	    private JFXTextField name_field;

	    @FXML
	    private Spinner<Float> price_button;

	    @FXML
	    private Spinner<Integer> units_button;

	    @FXML
	    private JFXDatePicker date_picker;

	    @FXML
	    private Pane features_pane;

	    @FXML
	    private JFXCheckBox utility_button;

	    @FXML
	    private JFXCheckBox maintenance_button;
    
    
    
    
 // -----> GETTERS AND SETTERS <-----
    
    
	public Pane getFeatures_pane() {
		return features_pane;
	}

	public void setFeatures_pane(Pane features_pane) {
		this.features_pane = features_pane;
	}
	
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

	public Spinner<Float> getPrice_button() {
		return price_button;
	}

	public void setPrice_button(Spinner<Float> price_button) {
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
    
    public Pane getNew_product_pane() {
		return new_product_pane;
	}

	public void setNew_product_pane(Pane new_product_pane) {
		this.new_product_pane = new_product_pane;
	}
    
 // -----> METHODS <-----
    
	

	public NewProductController() {
		super();
		this.price_button = new Spinner(0, 100000, 0);
		this.units_button = new Spinner(0, 100000, 0);
	}
    
    public static void setValues(SQLManager manager) {
		manager_object = manager;
	}
	
	
	
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		conclude_button.setOnAction((ActionEvent event) -> {
			try {
				String product_name = name_field.getText();
				Integer units = units_button.getValue();
				float price = price_button.getValue();
				//Toma fecha actual, no la introducida. hay que mirar como se hace
				LocalDate exp_date = date_picker.getValue().now();
				
				//Faltan los optativos checkBox para maintenance y utility
				
				Biomaterial biomaterial = manager_object.Search_biomaterial_by_id(null);
				biomaterial.setName_product(product_name);
				biomaterial.setAvailable_units(units);
				biomaterial.setPrice_unit(price);
				biomaterial.setExpiration_date(Date.valueOf(exp_date));
				//biomaterial.setMaintenance();
				//biomaterial.setUtility();
				
				
			if (!(product_name.equals(""))) {
				manager_object.Insert_new_biomaterial(biomaterial);
				System.out.println("Biomaterial saved into database");
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("NewProductView.fxml"));
				Parent root = (Parent) loader.load();
				WorkerMenuController worker_controller = new WorkerMenuController();
				worker_controller = loader.getController();
				worker_controller.getAnchorPane().setEffect(null);
					Stage stage = new Stage();
					stage.initStyle(StageStyle.UNDECORATED);
					stage.setAlwaysOnTop(true);
					stage.setScene(new Scene(root));
					stage.show();
			
			
			
				this.name_field.clear();
			
			}
			} catch (IOException conclude_error) {
				conclude_error.printStackTrace();
			}
		});
		
		
    }

    
    @FXML
	public void open_newProduct_panel(WindowEvent event_handler) throws IOException {
		Pane menu_panel = FXMLLoader.load(getClass().getResource("OrderProductView.fxml"));
		this.features_pane.getChildren().removeAll();
		this.features_pane.getChildren().setAll(menu_panel);
	}
    
}

