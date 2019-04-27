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
	private static Stage my_account;
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
	void min_window(MouseEvent event) {
		Stage stage = (Stage) menu_main_pane.getScene().getWindow();
		stage.setIconified(true);
	}

	@FXML
	void transaction_records(ActionEvent event) {

	}

	public void initialize(URL location, ResourceBundle resources) {
		myAccount_button.setOnAction((ActionEvent) -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountDirectorView.fxml"));
				Parent root = (Parent) loader.load();
				AccountDirectorController account_controller = new AccountDirectorController();
				account_controller = loader.getController();
				account_controller.done_button.setOnMouseClicked(new EventHandler<Event>() {
					@Override
					public void handle(Event event) {
						update_worker_account();
						menu_window.setEffect(null);
						my_account.close();
					}
				});
				my_account = new Stage();
				my_account.initStyle(StageStyle.UNDECORATED);
				my_account.setScene(new Scene(root));
				my_account.setAlwaysOnTop(true);
				my_account.setOnShowing(new EventHandler<WindowEvent>() {
					@Override
					public void handle(WindowEvent arg0) {
						menu_window.setEffect(new BoxBlur(3, 3, 3));
						myAccount_button.setDisable(true);
						listInventory_button.setDisable(true);
						addProduct_button.setDisable(true);
						removeProduct_button.setDisable(true);
						listTransactions_button.setDisable(true);
						listClients_button.setDisable(true);
						logOut_button.setDisable(true);
						minButton.setDisable(true);
						exitButton.setDisable(true);
					}
				});
				my_account.setOnHiding(new EventHandler<WindowEvent>() {
					@Override
					public void handle(WindowEvent event) {
						myAccount_button.setDisable(false);
						listInventory_button.setDisable(true);
						addProduct_button.setDisable(true);
						removeProduct_button.setDisable(true);
						listTransactions_button.setDisable(true);
						listClients_button.setDisable(true);
						logOut_button.setDisable(true);
						minButton.setDisable(true);
						exitButton.setDisable(true);
						menu_window.setEffect(null);
					}
				});
				my_account.show();
			} catch (IOException worker_account_error) {
				worker_account_error.printStackTrace();
				System.exit(0);
			}
		});

		// Biomaterials list columns creation

		/*JFXTreeTableColumn<BiomaterialListObject, String> product_name = new JFXTreeTableColumn<>("Product");
		product_name.setPrefWidth(120);
		product_name.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<BiomaterialListObject, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<BiomaterialListObject, String> param) {
						return param.getValue().getValue().product_name;
					}
				});
		product_name.setResizable(false);

		JFXTreeTableColumn<BiomaterialListObject, String> available_units = new JFXTreeTableColumn<>("Available units");
		available_units.setPrefWidth(120);
		available_units.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<BiomaterialListObject, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<BiomaterialListObject, String> param) {
						return param.getValue().getValue().available_units;
					}
				});
		available_units.setResizable(false);

		JFXTreeTableColumn<BiomaterialListObject, String> price = new JFXTreeTableColumn<>("Price / unit ($)");
		price.setPrefWidth(120);
		price.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<BiomaterialListObject, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<BiomaterialListObject, String> param) {
						return param.getValue().getValue().price_unit;
					}
				});
		price.setResizable(false);

		JFXTreeTableColumn<BiomaterialListObject, String> exp_date = new JFXTreeTableColumn<>("Expiration date");
		exp_date.setPrefWidth(120);
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

		for (Biomaterial biomaterial : biomaterial_list) {
			biomaterial_objects.add(new BiomaterialListObject(biomaterial.getName_product(),
					biomaterial.getAvailable_units().toString(), biomaterial.getPrice_unit().toString(),
					biomaterial.getExpiration_date().toString()));
		}
		final TreeItem<BiomaterialListObject> root = new RecursiveTreeItem<BiomaterialListObject>(biomaterial_objects,
				RecursiveTreeObject::getChildren);
		biomaterials_tree_view.getColumns().setAll(product_name, available_units, price, exp_date);
		biomaterials_tree_view.setRoot(root);
		biomaterials_tree_view.setShowRoot(false);*/

	}

	// -----> BUTTOM METHODS <-----

	@FXML
	private void close_app(MouseEvent event) {
		System.exit(0);
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
}

// -----> TRANSACTION LIST CLASS <-----

// To insert columns into the list of transactions with all the information
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
