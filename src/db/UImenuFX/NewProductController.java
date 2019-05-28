package db.UImenuFX;

import java.io.IOException;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import db.jdbc.SQLManager;
import db.pojos.Biomaterial;
import db.pojos.Maintenance;
import db.pojos.Utility;
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.converter.IntegerStringConverter;

public class NewProductController implements Initializable {
	
	// -----> CLASS ATTRIBUTES <-----
	
		private static SQLManager manager_object;
		private TreeItem<UtilityListObject> utility_object;
		private TreeItem<MaintenanceListObject> maintenance_object;
		
	// -----> FXML ATTRIBUTES <-----
		
		@FXML
		private Label maintenance_selected;
		@FXML
		private Label utility_selected;
		@FXML
	    private Pane new_product_pane;
	    @FXML
	    private JFXButton conclude_button;
	    @FXML
	    private JFXComboBox<Integer> units_button;
	    @FXML
	    private JFXComboBox<String> price_button;
	    @FXML
	    private JFXTextField name_field;
	    @FXML
	    private JFXDatePicker date_picker;
	    @FXML
	    private JFXButton features_button;
	    @FXML
	    private JFXTextArea information_field;
	    @FXML
	    private static Stage stage_window;
	    
	
 // -----> ESSENTIAL METHODS <-----
    

	public NewProductController() {
		super();
	}
	
	public static void setValues(SQLManager manager) {
		manager_object = manager;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		units_button.getItems().addAll(50, 100, 250, 500, 1000);
		price_button.getItems().addAll("10", "20","50", "80", "100");
   		units_button.setConverter(new IntegerStringConverter());
       		
   		
   		features_button.setOnAction((ActionEvent) -> {
   			try {
   	    		FeaturesController.setValue(manager_object);
   				FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductFeaturesView.fxml"));
   				Parent root = (Parent) loader.load();
   				FeaturesController controller = new FeaturesController();
   				controller = loader.getController();
   				controller.getDone_button().setOnMouseClicked(new EventHandler<Event>() {
   					@Override
   					public void handle(Event event) {
   						//worker_controller.getAnchorPane().setEffect(null);
   						stage_window.close();
   					}
   				});
   				stage_window = new Stage();
   				stage_window.initStyle(StageStyle.UNDECORATED);
   				stage_window.setScene(new Scene(root));
   				stage_window.setAlwaysOnTop(true);				
   				stage_window.setOnShowing(new EventHandler<WindowEvent>() {
   					@Override
   					public void handle(WindowEvent arg0) {
   						//worker_controller.getAnchorPane().setEffect(new BoxBlur(3,3,3));
   						stage_window.initModality(Modality.APPLICATION_MODAL);
   					}
   				});
   				stage_window.setOnHiding(new EventHandler<WindowEvent>() {		
   					@Override
   					public void handle(WindowEvent event) {
   						//worker_controller.getAnchorPane().setEffect(null);
   						utility_object = FeaturesController.getUtility_object();
   						if(utility_object != null) {
   							utility_selected.setText("Utility has been selected");
   						}
   						maintenance_object = FeaturesController.getMaintenance_object();
   						if(maintenance_object != null) {
   							maintenance_selected.setText("Maintenance has been selected");
   						}
   					}
   				});		
   				stage_window.show();
   			} catch (IOException features_error) {
   				features_error.printStackTrace();
   				System.exit(0);
   			}
   		});
   		
    }
    
	@FXML
	public void conclude_creation(MouseEvent event) {
		String product_name = name_field.getText();
		LocalDate exp_date = date_picker.getValue();
		Integer units = units_button.getValue();
		Float price = Float.parseFloat(price_button.getSelectionModel().getSelectedItem());
		
		name_field.setText("");
		date_picker.setValue(null);
		units_button.setValue(null);
		price_button.setValue(null);
		utility_selected.setText("No utility selected");
		maintenance_selected.setText("No maintenance selected");
		
		if (!product_name.equals("") || exp_date != null || units != null || price != null) {
			Biomaterial biomaterial = new Biomaterial();
			biomaterial.setName_product(product_name);
			biomaterial.setAvailable_units(units.intValue());
			biomaterial.setPrice_unit(price.floatValue());
			biomaterial.setExpiration_date(Date.valueOf(exp_date));
			if(utility_object != null) {
				Utility utility = manager_object.Search_utility_by_id(Integer.parseInt(utility_object.getValue().utility_id.getValue()));
				biomaterial.setUtility(utility);
			}
			if(maintenance_object != null) {
				Maintenance maintenance = manager_object.Search_maintenance_by_id(Integer.parseInt(maintenance_object.getValue().maintenance_id.getValue().toString()));
				biomaterial.setMaintenance(maintenance);
			}
			System.out.println(biomaterial);
			manager_object.Insert_new_biomaterial(biomaterial);
		}
	}	
}

