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
import db.pojos.Director;
import db.pojos.Transaction;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

public class DirectorMenuController implements Initializable {

	// -----> CLASS ATRIBUTES <-----

	private static Director director_account;
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
	private JFXButton listAllClients_button;
	@FXML
	private JFXButton removeClient_button;
	@FXML
	private JFXButton listAllWorkers_button;
	@FXML
	private JFXButton addWorker_button;
	@FXML
	private JFXButton removeWorker_button;
	@FXML
	private JFXButton addPromotion_button;
	@FXML
	private JFXButton finantialStatus_button;
	@FXML
	private ImageView minButton;
	@FXML
	private ImageView exitButton;
	@FXML
	private Label current_pane_option_label;
	@FXML
	private Label director_name;
	@FXML
	private Label email;
	@FXML
	private Label telephone;
	@FXML
	private static Stage stage_window;
	@FXML
	private JFXTreeTableView<TransactionListObject> transactions_tree_view;
	@FXML
	private final ObservableList<TransactionListObject> transactions_objects = FXCollections.observableArrayList();

	// -----> ESSENTIAL METHODS <-----

	public DirectorMenuController() {
		// TODO Auto-generated constructor stub
	}
	
	public static void setValues(SQLManager manager, Director director) {
		manager_object = manager;
		director_account = director;
	}

	@Override @SuppressWarnings("unchecked")
	public void initialize(URL location, ResourceBundle resources) {
		myAccount_button.setOnAction((ActionEvent) -> {
			try {
				AccountDirectorController.setValues(manager_object, director_account);
				FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountDirectorView.fxml"));
				Parent root = (Parent) loader.load();
				AccountDirectorController account_controller = new AccountDirectorController();
				account_controller = loader.getController();
				account_controller.getDoneButton().setOnMouseClicked(new EventHandler<Event>() {
					@Override
					public void handle(Event event) {
						update_director_account();
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
			} catch (IOException director_account_error) {
				director_account_error.printStackTrace();
				System.exit(0);
			
			}
		});
		
		removeClient_button.setOnAction((ActionEvent) -> {
			try {
				RemoveClientController.setValues(manager_object);
				FXMLLoader loader = new FXMLLoader(getClass().getResource("RemoveClientView.fxml"));
				Parent root = (Parent) loader.load();
				RemoveClientController client_controller = new RemoveClientController();
				client_controller = loader.getController();
				client_controller.getDeleteAccountButton().setOnMouseClicked(new EventHandler<Event>() {
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
						refreshtransactionListView();
						menu_window.setEffect(null);
					}
				});		
				stage_window.show();
			} catch(IOException delete_client_error) {
				delete_client_error.printStackTrace();
				System.exit(0);
			}
		});
		
		// Transaction list columns creation
		
		JFXTreeTableColumn<TransactionListObject, String> client_name = new JFXTreeTableColumn<>("Client name");
		client_name.setPrefWidth(120);
		client_name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TransactionListObject,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<TransactionListObject, String> param) {
				return param.getValue().getValue().client_name;
			}
		});
		client_name.setResizable(false);
		JFXTreeTableColumn<TransactionListObject, String> amount = new JFXTreeTableColumn<>("Payment");
		amount.setPrefWidth(95);
		amount.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TransactionListObject,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<TransactionListObject, String> param) {
				return param.getValue().getValue().amount;
			}
		});
		amount.setResizable(false);
		JFXTreeTableColumn<TransactionListObject, String> units = new JFXTreeTableColumn<>("Units");
		units.setPrefWidth(70);
		units.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TransactionListObject,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<TransactionListObject, String> param) {
				return param.getValue().getValue().units;
			}
		});
		units.setResizable(false);
		JFXTreeTableColumn<TransactionListObject, String> transaction_date = new JFXTreeTableColumn<>("Transaction date");
		transaction_date.setPrefWidth(170);
		transaction_date.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TransactionListObject,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<TransactionListObject, String> param) {
				return param.getValue().getValue().transaction_date;
			}
		});
		transactions_tree_view.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		transaction_date.setResizable(false);
		List<Transaction> transactions_list = manager_object.List_all_transactions();
		for(Transaction transaction: transactions_list) {
			transactions_objects.add(new TransactionListObject(transaction.getClient().getUser().getUserName(), transaction.getUnits().toString(), transaction.getGain().toString()
					, transaction.getTransaction_date().toString()));
		}
		TreeItem<TransactionListObject> root = new RecursiveTreeItem<TransactionListObject>(transactions_objects, RecursiveTreeObject::getChildren);
		transactions_tree_view.getColumns().setAll(client_name, amount, units, transaction_date);
		transactions_tree_view.setRoot(root);
		transactions_tree_view.setShowRoot(false);
	}
	
	// -----> REFRESH TRANSACTION LIST VIEW <-----
	
	public void refreshtransactionListView() {
		transactions_objects.clear();
		List<Transaction> transactions_list = manager_object.List_all_transactions();
		for(Transaction transaction: transactions_list) {
			transactions_objects.add(new TransactionListObject(transaction.getClient().getUser().getUserName(), transaction.getUnits().toString(), transaction.getGain().toString()
					, transaction.getTransaction_date().toString()));
		}
		TreeItem<TransactionListObject> root = new RecursiveTreeItem<TransactionListObject>(transactions_objects, RecursiveTreeObject::getChildren);
		transactions_tree_view.refresh();
	}

	// -----> BUTTOM METHODS <-----

	@FXML
	private void close_app(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	private void log_out(MouseEvent event) {
		manager_object.Close_connection();
		LaunchApplication.getStage().show();
		Stage stage = (Stage) logOut_button.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void min_window(MouseEvent event) {
		Stage stage = (Stage) menu_main_pane.getScene().getWindow();
		stage.setIconified(true);
	}
	
	public void update_director_account() {
	    	director_account = manager_object.Search_director_by_id(director_account.getDirector_id());
	    	setDirectorEmail(director_account.getEmail());
	    	setDirectorName(director_account.getDirector_name());
    		setDirectorTelephone(director_account.getTelephone());
	}

	// -----> SET AND GET METHODS <-----
	
	public void setDirectorName(String name) {
		this.director_name.setText("Director's name: " + name);
	}

	public void setDirectorEmail(String email) {
		if (email != null) {
			this.email.setText("Email: " + email);
		} else {
			this.email.setText("Email: No email associated");
		}
	}

	public void setDirectorTelephone(Integer telephone) {
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

	public AnchorPane getAnchorPane() {
		return this.menu_window;
	}

	// -----> ANABLE/DISABLE BUTTONS <-----
	
	public void setAllButtonsOff() {
	    myAccount_button.setDisable(true);
	    listAllClients_button.setDisable(true);
	    addPromotion_button.setDisable(true);
	    listAllWorkers_button.setDisable(true);
	    removeClient_button.setDisable(true);
	    removeWorker_button.setDisable(true);
	    addWorker_button.setDisable(true);
	    finantialStatus_button.setDisable(true);
	    logOut_button.setDisable(true);
	    minButton.setDisable(true);
	    exitButton.setDisable(true);
	}
	
	public void setAllButtonsOn() {
		myAccount_button.setDisable(false);
	    listAllClients_button.setDisable(false);
	    addPromotion_button.setDisable(false);
	    listAllWorkers_button.setDisable(false);
	    removeClient_button.setDisable(false);
	    removeWorker_button.setDisable(false);
	    addWorker_button.setDisable(false);
	    finantialStatus_button.setDisable(false);
	    logOut_button.setDisable(false);
	    minButton.setDisable(false);
	    exitButton.setDisable(false);
	}
}

// -----> TRANSACTION LIST CLASS <-----

// To insert columns into the list of transactions with all the information
class TransactionListObject extends RecursiveTreeObject<TransactionListObject> {
	
	StringProperty client_name;
	StringProperty amount;
	StringProperty units;
	StringProperty transaction_date;
	
	public TransactionListObject(String client_name, String amount, String units, String transaction_date) {
		this.client_name = new SimpleStringProperty(client_name);
		this.amount = new SimpleStringProperty(amount);
		this.units = new SimpleStringProperty(units);
		this.transaction_date = new SimpleStringProperty(transaction_date);
	}
}



















