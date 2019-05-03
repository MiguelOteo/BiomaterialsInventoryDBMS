package db.UImenuFX;

import java.io.IOException;

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
import db.pojos.Worker;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;

public class WorkerMenuController implements Initializable {

	// -----> CLASS ATRIBUTES <-----

	private static Worker worker_account;
	private static SQLManager manager_object;
	private static WorkerMenuController worker_controller;


	// -----> FXML ATRIBUTES <-----

	@FXML
    private AnchorPane menu_window;
    @FXML
    private Pane worker_main_panel;
    @FXML
    private Pane worker_main_menu;
	@FXML
	private Pane pane_backup;
	@FXML
	private Pane menu_main_pane;
    @FXML
    private JFXButton addSelection_button;
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
	private static Stage stage_window;
	@FXML
	private JFXTreeTableView<BiomaterialListObject> biomaterials_tree_view;
	@FXML
	private final ObservableList<BiomaterialListObject> biomaterial_objects = FXCollections.observableArrayList();

	

	// -----> ESSENTIAL METHODS <-----

	public WorkerMenuController() {
		// TODO Auto-generated constructor stub
	}

	public static void setValues(SQLManager manager, Worker worker) {
		manager_object = manager;
		worker_account = worker;
	}
	
	public static void setController(WorkerMenuController controller) {
		worker_controller = controller;
	}
	
	public static Worker getValueWorker() {
		return worker_account;
	}
	public static SQLManager getManager() {
		return manager_object;
	}
	

	public void update_worker_account() {
		worker_account = manager_object.Search_worker_by_id(worker_account.getWorker_id());
		setWorkerEmail(worker_account.getEmail());
		setWorkerName(worker_account.getWorker_name());
		setWorkerTelephone(worker_account.getTelephone());
	}

	// --------------> GET AND SET METHODS <-----------------

	public AnchorPane getAnchorPane() {
		return this.menu_window;
	}
	public Pane getWorker_main_panel() {
		return worker_main_panel;
	}
	public void setWorker_main_panel(Pane worker_main_panel) {
		this.worker_main_panel = worker_main_panel;
	}
	
	public Pane getWorker_main_menu() {
		return worker_main_menu;
	}
	
	public void setWorker_main_menu(Pane worker_main_menu) {
		this.worker_main_menu = worker_main_menu;
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
	

	
	//----------> ESSENTIAL METHODS <---------------
	
	@SuppressWarnings("unchecked")
	public void initialize(URL location, ResourceBundle resources) {
		this.pane_backup = menu_main_pane;
		
		myAccount_button.setOnAction((ActionEvent) -> {
			try {
				AccountWorkerController.setValues(manager_object, worker_account);
				FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountWorkerView.fxml"));
				Parent root = (Parent) loader.load();
				AccountWorkerController account_controller = new AccountWorkerController();
				account_controller = loader.getController();
				account_controller.getDoneButton().setOnMouseClicked(new EventHandler<Event>() {
					@Override
					public void handle(Event event) {
						update_worker_account();
						menu_window.setEffect(null);
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
						menu_window.setEffect(new BoxBlur(3,3,3));
						stage_window.initModality(Modality.APPLICATION_MODAL);
					}
				});
				stage_window.setOnHiding(new EventHandler<WindowEvent>() {		
					@Override
					public void handle(WindowEvent event) {
						menu_window.setEffect(null);
					}
				});		
				stage_window.show();
			} catch (IOException worker_account_error) {
				worker_account_error.printStackTrace();
				System.exit(0);
			}
		});
		
		
		// Biomaterials list columns creation

		JFXTreeTableColumn<BiomaterialListObject, String> product_name = new JFXTreeTableColumn<>("Product");
		product_name.setPrefWidth(245);
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
		price.setPrefWidth(195);
		price.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<BiomaterialListObject, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<BiomaterialListObject, String> param) {
						return param.getValue().getValue().price_unit;
					}
				});
		price.setResizable(false);

		JFXTreeTableColumn<BiomaterialListObject, String> exp_date = new JFXTreeTableColumn<>("Expiration date");
		exp_date.setPrefWidth(195);
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
	
	
	// -----> BUTTON METHODS <-----

	@FXML
	private void close_app(MouseEvent event) {
		System.exit(0);
	}
	
	@FXML
	void min_window(MouseEvent event) {
		Stage stage = (Stage) this.menu_window.getScene().getWindow();
		stage.setIconified(true);
	}

	@FXML
	private void log_out(MouseEvent event) {
		LaunchApplication.getStage().show();
		Stage stage = (Stage) logOut_button.getScene().getWindow();
		stage.close();
		manager_object.Close_connection();
	}
	
	@FXML
	public void open_option_panel(MouseEvent event) throws IOException {
		Pane menu_panel = FXMLLoader.load(getClass().getResource("ProductOptionPanel.fxml"));
		ProductOptionController.setValues(worker_controller);
		NewProductController.setManager(manager_object);
		NewProductController.setValues(worker_controller);
		OrderProductController.setValues(manager_object);
		worker_main_panel.getChildren().removeAll();
		worker_main_panel.getChildren().setAll(menu_panel);
	}
		
	@FXML
	public void open_list_inventory_panel(MouseEvent event) throws IOException {
		AnchorPane menu_panel = FXMLLoader.load(getClass().getResource("WorkerMenuView.fxml"));
		ProductOptionController.setValues(worker_controller);
		NewProductController.setManager(manager_object);
		NewProductController.setValues(worker_controller);
		OrderProductController.setValues(manager_object);
		menu_window.getChildren().removeAll();
		menu_window.getChildren().setAll(menu_panel);
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

// -----> BIOMATERIALS LIST CLASS <-----

// To insert columns into the list of biomaterials with all the information
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





