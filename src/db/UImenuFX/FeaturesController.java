package db.UImenuFX;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import db.jdbc.SQLManager;
import db.pojos.Maintenance;
import db.pojos.Utility;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class FeaturesController implements Initializable {

	//----------------> CLASS ATTRIBUTES <-----------------
	
	private static SQLManager manager_object;
	private static TreeItem<UtilityListObject> utility_object;
	private static TreeItem<MaintenanceListObject> maintenance_object;
	
	
	//----------------> FXML ATTRIBUTES <------------------
	@FXML
	private AnchorPane account_window;
	@FXML
	private JFXButton done_button;
	@FXML
	private ImageView close_button;
	@FXML
    private Pane main_bar;
    @FXML
    private JFXTreeTableView<UtilityListObject> utility_tree_view;
    @FXML
    private JFXTreeTableView<MaintenanceListObject> maintenance_tree_view;
    @FXML
    private final ObservableList<UtilityListObject> utility_objects = FXCollections.observableArrayList();
    @FXML
    private final ObservableList<MaintenanceListObject> maintenance_objects = FXCollections.observableArrayList();

    
    //--------------------> GETTERS AND SETTERS <-----------------
    
    public AnchorPane getAccount_window() {
		return account_window;
	}

	public void setAccount_window(AnchorPane account_window) {
		this.account_window = account_window;
	}
    
	public JFXButton getDone_button() {
		return done_button;
	}
	
	public Pane getMain_bar() {
		return main_bar;
	}

	public void setMain_bar(Pane main_bar) {
		this.main_bar = main_bar;
	}
	
    //--------------------> MAIN FUNCTIONS <-----------------
    

	public static void setValue(SQLManager manager) {
    	manager_object = manager;
    }
    
    @Override @SuppressWarnings("unchecked")
	public void initialize(URL location, ResourceBundle resources) {
		
		// -----> UTILITY TABLE <-----
		
		JFXTreeTableColumn<UtilityListObject, String> heat_cold = new JFXTreeTableColumn<>("Heat/Cold");
		heat_cold.setPrefWidth(170);
		heat_cold.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<UtilityListObject, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<UtilityListObject, String> param) {
						return param.getValue().getValue().heat_cold;
					}
				});
		heat_cold.setResizable(false);
		
		JFXTreeTableColumn<UtilityListObject, String> flexibility = new JFXTreeTableColumn<>("Flexibility");
		flexibility.setPrefWidth(175);
		flexibility.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<UtilityListObject, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<UtilityListObject, String> param) {
						return param.getValue().getValue().flexibility;
					}
				});
		flexibility.setResizable(false);
		
		JFXTreeTableColumn<UtilityListObject, String> strength = new JFXTreeTableColumn<>("Strength");
		strength.setPrefWidth(170);
		strength.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<UtilityListObject, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<UtilityListObject, String> param) {
						return param.getValue().getValue().strength;
					}
				});
		strength.setResizable(false);
		
		JFXTreeTableColumn<UtilityListObject, String> resistance = new JFXTreeTableColumn<>("Resistance");
		resistance.setPrefWidth(175);
		resistance.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<UtilityListObject, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<UtilityListObject, String> param) {
						return param.getValue().getValue().resistance;
					}
				});
		resistance.setResizable(false);
		
		JFXTreeTableColumn<UtilityListObject, String> pressure = new JFXTreeTableColumn<>("Pressure");
		pressure.setPrefWidth(170);
		pressure.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<UtilityListObject, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<UtilityListObject, String> param) {
						return param.getValue().getValue().pressure;
					}
				});
		pressure.setResizable(false);
		
		JFXTreeTableColumn<UtilityListObject, String> util_id = new JFXTreeTableColumn<>("id");
		util_id.setPrefWidth(50);
		util_id.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<UtilityListObject, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<UtilityListObject, String> param) {
						return param.getValue().getValue().utility_id;
					}
				});
		util_id.setResizable(false);
		
		
		List<Utility> utility_list = manager_object.List_all_utilities();
		for(Utility utility: utility_list) {
			utility_objects.add(new UtilityListObject(utility.getHeat_cold().toString(), utility.getFlexibility(), utility.getResistance(), utility.getPressure().toString(), utility.getStrength().toString(), utility.getUtility_id().toString()));
		}
		
		TreeItem<UtilityListObject> root = new RecursiveTreeItem<UtilityListObject>(utility_objects, RecursiveTreeObject::getChildren);
		utility_tree_view.getColumns().setAll(util_id, heat_cold, flexibility, resistance, pressure, strength);
		utility_tree_view.setPlaceholder(new Label("No utilities found"));
		utility_tree_view.setRoot(root);
		utility_tree_view.setShowRoot(false);
		
		// -----> MAINTENANCE TABLE <-----
		
		JFXTreeTableColumn<MaintenanceListObject, String> compatibility = new JFXTreeTableColumn<>("Compatibility");
		compatibility.setPrefWidth(120);
		compatibility.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<MaintenanceListObject, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<MaintenanceListObject, String> param) {
						return param.getValue().getValue().compatibility;
					}
				});
		compatibility.setResizable(false);
		
		JFXTreeTableColumn<MaintenanceListObject, String> humidity = new JFXTreeTableColumn<>("Humidity");
		humidity.setPrefWidth(120);
		humidity.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<MaintenanceListObject, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<MaintenanceListObject, String> param) {
						return param.getValue().getValue().humidity;
					}
				});
		humidity.setResizable(false);
		
		JFXTreeTableColumn<MaintenanceListObject, String> light = new JFXTreeTableColumn<>("Light");
		light.setPrefWidth(100);
		light.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<MaintenanceListObject, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<MaintenanceListObject, String> param) {
						return param.getValue().getValue().light;
					}
				});
		light.setResizable(false);
		
		JFXTreeTableColumn<MaintenanceListObject, String> o2_supply = new JFXTreeTableColumn<>("Oxygen sypply");
		o2_supply.setPrefWidth(150);
		o2_supply.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<MaintenanceListObject, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<MaintenanceListObject, String> param) {
						return param.getValue().getValue().o2_supply;
					}
				});
		o2_supply.setResizable(false);
		
		JFXTreeTableColumn<MaintenanceListObject, String> temperature = new JFXTreeTableColumn<>("Temperature");
		temperature.setPrefWidth(110);
		temperature.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<MaintenanceListObject, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<MaintenanceListObject, String> param) {
						return param.getValue().getValue().temperature;
					}
				});
		temperature.setResizable(false);
		
		JFXTreeTableColumn<MaintenanceListObject, String> pressureM = new JFXTreeTableColumn<>("Pressure");
		pressureM.setPrefWidth(120);
		pressureM.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<MaintenanceListObject, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<MaintenanceListObject, String> param) {
						return param.getValue().getValue().pressure;
					}
				});
		pressureM.setResizable(false);
		
		JFXTreeTableColumn<MaintenanceListObject, String> others = new JFXTreeTableColumn<>("Others");
		others.setPrefWidth(135);
		others.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<MaintenanceListObject, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<MaintenanceListObject, String> param) {
						return param.getValue().getValue().others;
					}
				});
		others.setResizable(false);
		
		JFXTreeTableColumn<MaintenanceListObject, String> maint_id = new JFXTreeTableColumn<>("id");
		maint_id.setPrefWidth(50);
		maint_id.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<MaintenanceListObject, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<MaintenanceListObject, String> param) {
						return param.getValue().getValue().maintenance_id;
					}
				});
		maint_id.setResizable(false);
		
		
		List<Maintenance> maintenance_list = manager_object.List_all_maintenances();
		
		for(Maintenance maintenance: maintenance_list) {
			maintenance_objects.add(new MaintenanceListObject(maintenance.getCompatibility(), maintenance.getHumidity().toString(), maintenance.getLight()
					, maintenance.getO2_supply(), maintenance.getTemperature().toString(), maintenance.getPressure().toString(), maintenance.getOthers(), maintenance.getManteinance_id().toString()));
		}
		
		final TreeItem<MaintenanceListObject> root_maint = new RecursiveTreeItem<MaintenanceListObject>(maintenance_objects, RecursiveTreeObject::getChildren);
		maintenance_tree_view.getColumns().setAll(maint_id, compatibility, humidity, light, o2_supply, temperature, pressureM, others);
		maintenance_tree_view.setRoot(root_maint);
		maintenance_tree_view.setShowRoot(false);
		
		done_button.setOnAction((ActionEvent) ->{
			utility_object = utility_tree_view.getSelectionModel().getSelectedItem();
			maintenance_object = maintenance_tree_view.getSelectionModel().getSelectedItem();	
			Stage stage = (Stage) account_window.getScene().getWindow();
			stage.close();	
		});
	}
    
    //-----> BUTTON FUNCTIONS <-----
    
    @FXML
    public void close_window(MouseEvent event) {
    	Stage stage = (Stage) this.account_window.getScene().getWindow();
    	stage.close();
    }

    // -----> GET METHODS <-----
    
    public static TreeItem<UtilityListObject> getUtility_object() {
    	return utility_object;
    }
    
    public static TreeItem<MaintenanceListObject> getMaintenance_object() {
    	return maintenance_object;
    }
}

class UtilityListObject extends RecursiveTreeObject<UtilityListObject> {
	
	StringProperty heat_cold;
	StringProperty flexibility;
	StringProperty resistance;
	StringProperty pressure;
	StringProperty strength;
	StringProperty utility_id;
	
    public UtilityListObject(String heat_cold, String flexibility, String resistance, String pressure, String strength, String utility_id) {
    	this.heat_cold = new SimpleStringProperty(heat_cold);
    	this.flexibility = new SimpleStringProperty(flexibility);
    	this.resistance = new SimpleStringProperty(resistance);
    	this.pressure = new SimpleStringProperty(pressure);
    	this.strength = new SimpleStringProperty(strength);
    	this.utility_id = new SimpleStringProperty(utility_id);
    }
}

class MaintenanceListObject extends RecursiveTreeObject<MaintenanceListObject> {
	
	StringProperty humidity;
	StringProperty o2_supply;
	StringProperty light;
	StringProperty pressure;
	StringProperty temperature;
	StringProperty compatibility;
	StringProperty others;
	StringProperty maintenance_id;

    public MaintenanceListObject(String pressure, String humidity, String o2_supply, String light, String temperature, String compatibility, String others, String maintenance_id) {
    	this.humidity = new SimpleStringProperty(humidity);
    	this.o2_supply = new SimpleStringProperty(o2_supply);
    	this.light = new SimpleStringProperty(light);
    	this.temperature = new SimpleStringProperty(temperature);
    	this.pressure = new SimpleStringProperty(pressure);
    	this.compatibility = new SimpleStringProperty(compatibility);
    	this.others = new SimpleStringProperty(others);
    	this.maintenance_id = new SimpleStringProperty(maintenance_id);
    }
}
