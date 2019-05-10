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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class ListAllBiomaterialsController implements Initializable {

	
	// -----> CLASS ATRIBUTES <-----
	
	private static SQLManager manager_object;

	// -----> FXML ATRIBUTES <-----
	
    @FXML
    private JFXButton addSelection_button;
    @FXML
    private Pane worker_main_menu;
	@FXML
	private JFXTreeTableView<BiomaterialListObject> biomaterials_tree_view;
	@FXML
	private final ObservableList<BiomaterialListObject> biomaterial_objects = FXCollections.observableArrayList();
	
	
	
	// -----> ESSENTIAL METHODS <-----
	
	public ListAllBiomaterialsController() {
		// TODO Auto-generated constructor stub
	}
	
	public Pane getWorker_main_menu() {
		return worker_main_menu;
	}
	
	public void setWorker_main_menu(Pane worker_main_menu) {
		this.worker_main_menu = worker_main_menu;
	}
	
	public static void setValues(SQLManager manager) {
		manager_object = manager;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	
		
		refreshBiomaterialListView();
		
	// Biomaterials list columns creation

			JFXTreeTableColumn<BiomaterialListObject, String> product_name = new JFXTreeTableColumn<>("Product");
			product_name.setPrefWidth(240);
			product_name.setCellValueFactory(
					new Callback<TreeTableColumn.CellDataFeatures<BiomaterialListObject, String>, ObservableValue<String>>() {
						@Override
						public ObservableValue<String> call(CellDataFeatures<BiomaterialListObject, String> param) {
							return param.getValue().getValue().product_name;
						}
					});
			product_name.setResizable(false);

			JFXTreeTableColumn<BiomaterialListObject, String> available_units = new JFXTreeTableColumn<>("Available units");
			available_units.setPrefWidth(220);
			available_units.setCellValueFactory(
					new Callback<TreeTableColumn.CellDataFeatures<BiomaterialListObject, String>, ObservableValue<String>>() {
						@Override
						public ObservableValue<String> call(CellDataFeatures<BiomaterialListObject, String> param) {
							return param.getValue().getValue().available_units;
						}
					});
			available_units.setResizable(false);

			JFXTreeTableColumn<BiomaterialListObject, String> price = new JFXTreeTableColumn<>("Price / unit ($)");
			price.setPrefWidth(190);
			price.setCellValueFactory(
					new Callback<TreeTableColumn.CellDataFeatures<BiomaterialListObject, String>, ObservableValue<String>>() {
						@Override
						public ObservableValue<String> call(CellDataFeatures<BiomaterialListObject, String> param) {
							return param.getValue().getValue().price_unit;
						}
					});
			price.setResizable(false);

			JFXTreeTableColumn<BiomaterialListObject, String> exp_date = new JFXTreeTableColumn<>("Expiration date");
			exp_date.setPrefWidth(190);
			exp_date.setCellValueFactory(
					new Callback<TreeTableColumn.CellDataFeatures<BiomaterialListObject, String>, ObservableValue<String>>() {
						@Override
						public ObservableValue<String> call(CellDataFeatures<BiomaterialListObject, String> param) {
							return param.getValue().getValue().expiration_date;
						}
					});
			exp_date.setResizable(false);

			JFXTreeTableColumn<BiomaterialListObject, String> id = new JFXTreeTableColumn<>("id");
			id.setPrefWidth(40);
			id.setCellValueFactory(
					new Callback<TreeTableColumn.CellDataFeatures<BiomaterialListObject, String>, ObservableValue<String>>() {
						@Override
						public ObservableValue<String> call(CellDataFeatures<BiomaterialListObject, String> param) {
							return param.getValue().getValue().bio_id;
						}
					});
			id.setResizable(false);
			
			List<Biomaterial> biomaterial_list = manager_object.List_all_biomaterials();
			
			for(Biomaterial biomaterial: biomaterial_list) {
				biomaterial_objects.add(new BiomaterialListObject(biomaterial.getBiomaterial_id().toString(), biomaterial.getName_product(), biomaterial.getAvailable_units().toString()
						, biomaterial.getPrice_unit().toString(), biomaterial.getExpiration_date().toString()));
			}
			
			TreeItem<BiomaterialListObject> root = new RecursiveTreeItem<BiomaterialListObject>(biomaterial_objects, RecursiveTreeObject::getChildren);
			biomaterials_tree_view.getColumns().setAll(id, product_name, available_units, price, exp_date);
			biomaterials_tree_view.setRoot(root);
			biomaterials_tree_view.setShowRoot(false);

			
			//Ables the selection of several biomaterials of treeTable
			//next step: associate selection's id to a variable being read by Order product controller
			biomaterials_tree_view.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);	
		}
	
	// -----> REFRESH BIOMATERIAL LIST VIEW <-----
	
	public void refreshBiomaterialListView() {
		biomaterial_objects.clear();
		List<Biomaterial> biomaterial_list = manager_object.List_all_biomaterials();
		for(Biomaterial biomaterial: biomaterial_list) {
			biomaterial_objects.add(new BiomaterialListObject(biomaterial.getBiomaterial_id().toString(), biomaterial.getName_product(), biomaterial.getAvailable_units().toString()
					, biomaterial.getPrice_unit().toString(), biomaterial.getExpiration_date().toString()));
		}
		TreeItem<BiomaterialListObject> root = new RecursiveTreeItem<BiomaterialListObject>(biomaterial_objects, RecursiveTreeObject::getChildren);
		biomaterials_tree_view.refresh();
	}
		
	
	
	@FXML
	public void add_selection(MouseEvent event) {
		TreeItem<BiomaterialListObject> selection = biomaterials_tree_view.getSelectionModel().getSelectedItem();
		
		//PROXIMAMENTE HAY QUE PASARLE UNA LISTA EN VEZ DE UN BIOMATERIAL SOLO
		
		Biomaterial biomaterial = manager_object.Search_biomaterial_by_id(Integer.parseInt(selection.getValue().bio_id.getValue().toString()));
		 
		System.out.println(biomaterial);
		OrderProductController.setValues(manager_object);
		OrderProductController.setItems(biomaterial);
		
	}
	
	
}

//-----> BIOMATERIALS LIST CLASS <-----

//To insert columns into the list of biomaterials with all the information
class BiomaterialListObject extends RecursiveTreeObject<BiomaterialListObject> {
	StringProperty product_name;
	StringProperty available_units;
	StringProperty price_unit;
	StringProperty expiration_date;
	StringProperty bio_id;

	public BiomaterialListObject(String id, String product_name, String available_units, String price_unit,
			String expiration_date) {
		this.product_name = new SimpleStringProperty(product_name);
		this.available_units = new SimpleStringProperty(available_units);
		this.price_unit = new SimpleStringProperty(price_unit);
		this.expiration_date = new SimpleStringProperty(expiration_date);
		this.bio_id = new SimpleStringProperty(id);
	}
}