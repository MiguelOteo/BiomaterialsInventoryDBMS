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
	private static NewProductController controller;
	
	
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
    
    //--------------------> MAIN FUNCTIONS <-----------------
    
    public static void setValues(SQLManager manager) {
    	manager_object = manager;
    }
    
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
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
		
		TreeItem<UtilityListObject> root = new RecursiveTreeItem<UtilityListObject>(utility_objects, RecursiveTreeObject::getChildren);
		utility_tree_view.getColumns().setAll(utilities);
		utility_tree_view.setRoot(root);
		utility_tree_view.setShowRoot(false);

		
		
		
		done_button.setOnAction((ActionEvent event) -> {
			TreeItem<UtilityListObject> utility_object = utility_tree_view.getSelectionModel().getSelectedItem();
		
			if (utility_object != null) {
				Biomaterial biomaterial = manager_object.Search_stored_biomaterial(controller.add_new_biomaterial(event));
				
			}
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
