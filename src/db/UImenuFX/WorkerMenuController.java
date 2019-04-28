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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

	// -----> FXML ATRIBUTES <-----

	@FXML
	private AnchorPane menu_window;
	@FXML
	private Pane menu_main_pane;
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
	private static Stage stage_window;
	@FXML
	private JFXTreeTableView<BiomaterialListObject> biomaterials_tree_view;
	

	// -----> ESSENTIAL METHODS <-----

	public WorkerMenuController() {
		// TODO Auto-generated constructor stub
	}

	public static void setValues(SQLManager manager, Worker worker) {
		manager_object = manager;
		worker_account = worker;
	}

	public void update_worker_account() {
		worker_account = manager_object.Search_worker_by_id(worker_account.getWorker_id());
		setWorkerEmail(worker_account.getEmail());
		setWorkerName(worker_account.getWorker_name());
		setWorkerTelephone(worker_account.getTelephone());
	}

	// --------------> GET AND SET METHODS <-----------------

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

	@FXML
	void add_product_to_inventory(ActionEvent event) {
	}

	@FXML
	void clients_list(ActionEvent event) {
	}

	@FXML
	void delete_from_inventory(ActionEvent event) {
	}

	@FXML
	void list_inventory(ActionEvent event) {

	}

	@FXML
	void transaction_records(ActionEvent event) {

	}
	
	

	@SuppressWarnings("unchecked")
	public void initialize(URL location, ResourceBundle resources) {
		myAccount_button.setOnAction((ActionEvent) -> {
			try {
				// TODO - "AccountWorkerView.fxml" and its controller
				FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountDirectorView.fxml"));
				Parent root = (Parent) loader.load();
				AccountDirectorController account_controller = new AccountDirectorController();
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
						menu_window.setEffect(new BoxBlur(3, 3, 3));
						setAllButtonsOff();
					}
				});
				stage_window.setOnHiding(new EventHandler<WindowEvent>() {
					@Override
					public void handle(WindowEvent event) {
						setAllButtonsOn();
					}
				});
				stage_window.show();
			} catch (IOException worker_account_error) {
				worker_account_error.printStackTrace();
				System.exit(0);
			}
		});
		
		
		addProduct_button.setOnAction((ActionEvent) -> {
			try {
				ProductOptionController.setValues(manager_object);
				FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductOptionPanel.fxml"));
				Parent root = (Parent) loader.load();
				ProductOptionController option_controller = new ProductOptionController();
				option_controller = loader.getController();
				option_controller.getOrderProduct_button().setOnMouseClicked(new EventHandler<Event>() {
					@Override
					public void handle(Event event) {
						menu_window.setEffect(null);
						stage_window.close();
					}
				});
				option_controller.getNewProduct_button().setOnMouseClicked(new EventHandler<Event>() {
					@Override
					public void handle(Event event) {
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
					    setAllButtonsOff();
					}
				});
				
				stage_window.setOnHiding(new EventHandler<WindowEvent>() {
					@Override
					public void handle(WindowEvent event) {
						setAllButtonsOn();
						menu_window.setEffect(null);
					}
				});
				stage_window.show();
				
				//Stage stage = (Stage) menu_window.getScene().getWindow();
				//stage.close();
				
			} catch (IOException panel_access_error) {
				panel_access_error.printStackTrace();
				System.exit(0);
			}
		}); 
		
		
		
		

		// Biomaterials list columns creation

		JFXTreeTableColumn<BiomaterialListObject, String> product_name = new JFXTreeTableColumn<>("Product");
		product_name.setPrefWidth(250);
		product_name.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<BiomaterialListObject, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<BiomaterialListObject, String> param) {
						return param.getValue().getValue().product_name;
					}
				});
		product_name.setResizable(false);

		JFXTreeTableColumn<BiomaterialListObject, String> available_units = new JFXTreeTableColumn<>("Available units");
		available_units.setPrefWidth(225);
		available_units.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<BiomaterialListObject, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<BiomaterialListObject, String> param) {
						return param.getValue().getValue().available_units;
					}
				});
		available_units.setResizable(false);

		JFXTreeTableColumn<BiomaterialListObject, String> price = new JFXTreeTableColumn<>("Price / unit ($)");
		price.setPrefWidth(200);
		price.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<BiomaterialListObject, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<BiomaterialListObject, String> param) {
						return param.getValue().getValue().price_unit;
					}
				});
		price.setResizable(false);

		JFXTreeTableColumn<BiomaterialListObject, String> exp_date = new JFXTreeTableColumn<>("Expiration date");
		exp_date.setPrefWidth(200);
		exp_date.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<BiomaterialListObject, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<BiomaterialListObject, String> param) {
						return param.getValue().getValue().expiration_date;
					}
				});
		exp_date.setResizable(false);

		
		ObservableList<BiomaterialListObject> biomaterial_objects = FXCollections.observableArrayList();
		List<Biomaterial> biomaterial_list = manager_object.List_all_biomaterials();
		
		for(Biomaterial biomaterial: biomaterial_list) {
			biomaterial_objects.add(new BiomaterialListObject(biomaterial.getName_product(), biomaterial.getAvailable_units().toString()
					, biomaterial.getPrice_unit().toString(), biomaterial.getExpiration_date().toString()));
		}
		
		final TreeItem<BiomaterialListObject> root = new RecursiveTreeItem<BiomaterialListObject>(biomaterial_objects, RecursiveTreeObject::getChildren);
		biomaterials_tree_view.getColumns().setAll(product_name, available_units, price, exp_date);
		biomaterials_tree_view.setRoot(root);
		biomaterials_tree_view.setShowRoot(false);

		biomaterials_tree_view.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
	}
	

	
	
	
	// -----> BUTTON METHODS <-----

	@FXML
	private void close_app(MouseEvent event) {
		System.exit(0);
	}
	
	@FXML
	void min_window(MouseEvent event) {
		Stage stage = (Stage) menu_main_pane.getScene().getWindow();
		stage.setIconified(true);
	}

	@FXML
	private void log_out(MouseEvent event) {
		LaunchApplication.getStage().show();
		Stage stage = (Stage) logOut_button.getScene().getWindow();
		stage.close();
	}

	public AnchorPane getAnchorPane() {
		return this.menu_window;
	}
	
	
	// -----> ANABLE/DISABLE BUTTONS <-----
	
		public void setAllButtonsOff() {
		    myAccount_button.setDisable(true);
			listInventory_button.setDisable(true);
			addProduct_button.setDisable(true);
			removeProduct_button.setDisable(true);
			listTransactions_button.setDisable(true);
			listClients_button.setDisable(true);
			logOut_button.setDisable(true);
			minButton.setDisable(true);
			exitButton.setDisable(true);
			addSelection_button.setDisable(true);
		}
		
		public void setAllButtonsOn() {
			myAccount_button.setDisable(false);
			listInventory_button.setDisable(false);
			addProduct_button.setDisable(false);
			removeProduct_button.setDisable(false);
			listTransactions_button.setDisable(false);
			listClients_button.setDisable(false);
			logOut_button.setDisable(false);
			minButton.setDisable(false);
			exitButton.setDisable(false);
			addSelection_button.setDisable(false);
		    
		}

	
	
	
}

// -----> BIOMATERIALS LIST CLASS <-----

// To insert columns into the list of biomaterials with all the information
class BiomaterialListObject extends RecursiveTreeObject<BiomaterialListObject> {
	StringProperty product_name;
	StringProperty available_units;
	StringProperty price_unit;
	StringProperty expiration_date;

	public BiomaterialListObject(String product_name, String available_units, String price_unit,
			String expiration_date) {
		this.product_name = new SimpleStringProperty(product_name);
		this.available_units = new SimpleStringProperty(available_units);
		this.price_unit = new SimpleStringProperty(price_unit);
		this.expiration_date = new SimpleStringProperty(expiration_date);
	}
}





