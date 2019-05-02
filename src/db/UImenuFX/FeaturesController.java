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
import db.pojos.Biomaterial;
import db.pojos.Maintenance;
import db.pojos.Utility;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class FeaturesController implements Initializable {

	//----------------> CLASS ATTRIBUTES <-----------------
	
	private static SQLManager manager_object;
	private static NewProductController product;
	private static Biomaterial biomaterial;
	
	
	//----------------> FXML ATTRIBUTES <------------------
	@FXML
	private AnchorPane account_window;
	@FXML
	private JFXButton done_button;
	@FXML
	private ImageView close_button;
    @FXML
    private JFXTreeTableView<UtilityListObject> utility_tree_view;
    @FXML
    private JFXTreeTableView<MaintenanceListObject> maintenance_tree_view;

    
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

	public void setDone_button(JFXButton done_button) {
		this.done_button = done_button;
	}
	
    //--------------------> MAIN FUNCTIONS <-----------------
    
    

	public static void setValues(SQLManager manager/*, WorkerMenuController worker_controller*/) {
    	manager_object = manager;
    	//controller = worker_controller;
    }
    
    public static void setBiomaterial(Biomaterial biomat) {
    	biomaterial = biomat;
    }
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
		//Table generators
		JFXTreeTableColumn<UtilityListObject, String> utilities = new JFXTreeTableColumn<>("Utilities");
		utilities.setPrefWidth(100);
		utilities.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<UtilityListObject, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<UtilityListObject, String> param) {
						return param.getValue().getValue().utility;
					}
				});
		utilities.setResizable(false);
		
		ObservableList<UtilityListObject> utility_objects = FXCollections.observableArrayList();
		List<Utility> utility_list = manager_object.List_all_utilities();
		
		for(Utility utility: utility_list) {
			utility_objects.add(new UtilityListObject(utility.getUtility_id().toString()));
		}
		
		final TreeItem<UtilityListObject> root = new RecursiveTreeItem<UtilityListObject>(utility_objects, RecursiveTreeObject::getChildren);
		utility_tree_view.getColumns().setAll(utilities);
		utility_tree_view.setRoot(root);
		utility_tree_view.setShowRoot(false);

		/*
		
		JFXTreeTableColumn<MaintenanceListObject, String> maintenances = new JFXTreeTableColumn<>("Maintenance");
		maintenances.setPrefWidth(100);
		maintenances.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<MaintenanceListObject, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<MaintenanceListObject, String> param) {
						return param.getValue().getValue().maintenance;
					}
				});
		maintenances.setResizable(false);
		
		ObservableList<MaintenanceListObject> maintenance_objects = FXCollections.observableArrayList();
		List<Maintenance> maintenance_list = manager_object.List_all_maintenances();
		
		for(Maintenance maintenance: maintenance_list) {
			maintenance_objects.add(new MaintenanceListObject(maintenance.getManteinance_id().toString()));
		}
		
		final TreeItem<MaintenanceListObject> root_maint = new RecursiveTreeItem<MaintenanceListObject>(maintenance_objects, RecursiveTreeObject::getChildren);
		maintenance_tree_view.getColumns().setAll(maintenances);
		maintenance_tree_view.setRoot(root_maint);
		maintenance_tree_view.setShowRoot(false);
		*/
		
		
		done_button.setOnAction((ActionEvent event) -> {
			
			TreeItem<UtilityListObject> utility_object = utility_tree_view.getSelectionModel().getSelectedItem();
			TreeItem<MaintenanceListObject> maintenance_object = maintenance_tree_view.getSelectionModel().getSelectedItem();
		
			if (utility_object != null | maintenance_object != null) {
				biomaterial = manager_object.Search_biomaterial_by_id(product.getBiomaterial().getBiomaterial_id());
				
				System.out.println("biomaterial: " + biomaterial);
				
				Integer utility_id = Integer.parseInt(utility_object.getValue().utility.getValue().toString());
				Integer maintenance_id = Integer.parseInt(maintenance_object.getValue().maintenance.getValue().toString());
					Utility utility = manager_object.Search_utility_by_id(utility_id);
					Maintenance maintenance = manager_object.Search_maintenance_by_id(maintenance_id);
					biomaterial.setUtility(utility);
					biomaterial.setMaintenance(maintenance);
					
				if(manager_object.Update_biomaterial_features(biomaterial) == true) {
					System.out.println("Updated sucessfully");
				} else System.out.println("Update error");
				
			}
			
			
			Stage stage = (Stage) account_window.getScene().getWindow();
			stage.close();
			
		});
			
		
	}
    
    //-------------------> BUTTON FUNCITONS <------------------
    
    @FXML
    void close_window(MouseEvent event) {
    	Stage stage = (Stage) this.account_window.getScene().getWindow();
    	stage.close();
    }




}

class UtilityListObject extends RecursiveTreeObject<UtilityListObject> {
	StringProperty utility;
	//StringProperty 
    public UtilityListObject(String utility) {
    	this.utility = new SimpleStringProperty(utility);
    }
}


class MaintenanceListObject extends RecursiveTreeObject<MaintenanceListObject> {
	StringProperty maintenance;

    public MaintenanceListObject(String maintenance) {
    	this.maintenance = new SimpleStringProperty(maintenance);
    }
}
