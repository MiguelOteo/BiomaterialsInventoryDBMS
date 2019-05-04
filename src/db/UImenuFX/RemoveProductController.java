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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class RemoveProductController implements Initializable {

	
	// -----> CLASS ATRIBUTES <-----

	private static SQLManager manager_object;
	
	// -----> FXML ATRIBUTES <-----
	
    @FXML
    private AnchorPane account_window;
    @FXML
    private JFXButton delete_button;
    @FXML
    private JFXTreeTableView<DeleteItemsListObject> biomaterials_tree_view;
    @FXML
	private final ObservableList<DeleteItemsListObject> product_objects = FXCollections.observableArrayList();
	
    // -----> ESSENTIAL METHODS <-----+
    
    public RemoveProductController() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    public static void setValues(SQLManager manager) {
		manager_object = manager;
	}
    
    public JFXButton getDelete_button() {
		return delete_button;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	
    
	JFXTreeTableColumn<DeleteItemsListObject, String> product_name = new JFXTreeTableColumn<>("Product");
	product_name.setPrefWidth(150);
	product_name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<DeleteItemsListObject,String>, ObservableValue<String>>() {
		@Override
		public ObservableValue<String> call(CellDataFeatures<DeleteItemsListObject, String> param) {
			return param.getValue().getValue().product_name;
		}
	});
	product_name.setResizable(false);
	
	JFXTreeTableColumn<DeleteItemsListObject, String> biomaterial_id = new JFXTreeTableColumn<>("ID");
	biomaterial_id.setPrefWidth(100);
	biomaterial_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<DeleteItemsListObject,String>, ObservableValue<String>>() {
		@Override
		public ObservableValue<String> call(CellDataFeatures<DeleteItemsListObject, String> param) {
			return param.getValue().getValue().biomaterial_id;
		}
	});
	biomaterial_id.setResizable(false);
	
	List<Biomaterial> biomaterial_list = manager_object.List_all_biomaterials();
	for(Biomaterial biomaterial: biomaterial_list) {
		product_objects.add(new DeleteItemsListObject(biomaterial.getBiomaterial_id().toString(), biomaterial.getName_product()));
	}
	TreeItem<DeleteItemsListObject> root = new RecursiveTreeItem<DeleteItemsListObject>(product_objects, RecursiveTreeObject::getChildren);
	biomaterials_tree_view.setPlaceholder(new Label("No items found"));
	biomaterials_tree_view.getColumns().setAll(biomaterial_id, product_name);
	biomaterials_tree_view.setRoot(root);
	biomaterials_tree_view.setShowRoot(false);
	
	
	
	delete_button.setOnAction((ActionEvent event) -> {
		
		TreeItem<DeleteItemsListObject> biomat_object = biomaterials_tree_view.getSelectionModel().getSelectedItem();
		
		if(biomat_object != null) {
			manager_object.Delete_biomaterial_by_id(Integer.parseInt(biomat_object.getValue().biomaterial_id.getValue().toString()));
			Stage stage = (Stage) account_window.getScene().getWindow();
			stage.close();
			
		} else {
			Stage stage = (Stage) account_window.getScene().getWindow();
			stage.close();
		}
	});
	
	
}
    
    // -----> BUTTON METHODS <-----+
    

	@FXML
    void close_window(MouseEvent event) {
		Stage stage = (Stage) account_window.getScene().getWindow();
		stage.close();
    }

}


//-----> biomaterial LIST CLASS <-----

//To insert columns into the list of items with all the information
class DeleteItemsListObject extends RecursiveTreeObject<DeleteItemsListObject> {
	
	StringProperty product_name;
	StringProperty biomaterial_id;
	
	public DeleteItemsListObject(String biomaterial_id, String product_name) {
		this.product_name = new SimpleStringProperty(product_name);
		this.biomaterial_id = new SimpleStringProperty(biomaterial_id);
	}
}