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

	@FXML 
	private static Director director_account;
	@FXML 
	private static SQLManager manager_object;

	// -----> FXML ATRIBUTES <-----

	@FXML
	private AnchorPane menu_window;
	@FXML
	private Pane menu_main_pane;
	@FXML
	private JFXButton logOut_buttom;
	@FXML
	private JFXButton myAccount_buttom;
	@FXML
	private JFXButton listAllClients_buttom;
	@FXML
	private JFXButton removeClient_buttom;
	@FXML
	private JFXButton listAllWorkers_buttom;
	@FXML
	private JFXButton addWorker_buttom;
	@FXML
	private JFXButton removeWorker_buttom;
	@FXML
	private JFXButton listAllTransactions_button;
	@FXML
	private JFXButton finantialStatus_buttom;
	@FXML
	private ImageView minButtom;
	@FXML
	private ImageView exitButtom;
	@FXML
	private Label current_pane_option_label;
	@FXML
	private Label director_name;
	@FXML
	private Label email;
	@FXML
	private Label telephone;
	@FXML
	public static Stage my_account;
	@FXML
	private JFXTreeTableView<TransactionListObject> transactions_tree_view;

	// -----> ESSENTIAL METHODS <-----

	public DirectorMenuController() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		myAccount_buttom.setOnAction((ActionEvent) -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountDirectorView.fxml"));
				Parent root = (Parent) loader.load();
				AccountDirectorController account_controller = new AccountDirectorController(manager_object, director_account);
				account_controller = loader.getController();
				account_controller.done_button.setOnMouseClicked(new EventHandler<Event>() {
					@Override
					public void handle(Event event) {
						update_director_account();
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
						menu_window.setEffect(new BoxBlur(3,3,3));
					    myAccount_buttom.setDisable(true);
					}
				});
				my_account.setOnHiding(new EventHandler<WindowEvent>() {		
					@Override
					public void handle(WindowEvent event) {
						myAccount_buttom.setDisable(false);
						menu_window.setEffect(null);
					}
				});		
				my_account.show();
			} catch (IOException director_account_error) {
				director_account_error.printStackTrace();
				System.exit(0);
			
			}
		});
		
		// Transaction list columns creation
		
		JFXTreeTableColumn<TransactionListObject, String> client_name = new JFXTreeTableColumn<>("Client name");
		client_name.setPrefWidth(150);
		client_name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TransactionListObject,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<TransactionListObject, String> param) {
				return param.getValue().getValue().client_name;
			}
		});
		JFXTreeTableColumn<TransactionListObject, String> amount = new JFXTreeTableColumn<>("Amount");
		amount.setPrefWidth(70);
		amount.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TransactionListObject,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<TransactionListObject, String> param) {
				return param.getValue().getValue().amount;
			}
		});
		JFXTreeTableColumn<TransactionListObject, String> units = new JFXTreeTableColumn<>("Units");
		units.setPrefWidth(70);
		units.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TransactionListObject,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<TransactionListObject, String> param) {
				return param.getValue().getValue().units;
			}
		});
		JFXTreeTableColumn<TransactionListObject, String> transaction_date = new JFXTreeTableColumn<>("Transaction date");
		transaction_date.setPrefWidth(170);
		transaction_date.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TransactionListObject,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<TransactionListObject, String> param) {
				return param.getValue().getValue().transaction_date;
			}
		});
		
		ObservableList<TransactionListObject> transactions_objects = FXCollections.observableArrayList();
		List<Transaction> transactions_list = manager_object.List_all_transactions();
		
		for(Transaction transaction: transactions_list) {
			transactions_objects.add(new TransactionListObject(transaction.getClient().getUser().getUserName(), transaction.getUnits().toString(), transaction.getGain().toString()
					, transaction.getTransaction_date().toString()));
		}
		final TreeItem<TransactionListObject> root = new RecursiveTreeItem<TransactionListObject>(transactions_objects, RecursiveTreeObject::getChildren);
		transactions_tree_view.getColumns().setAll(client_name, amount, units, transaction_date);
		transactions_tree_view.setRoot(root);
		transactions_tree_view.setShowRoot(false);
	}

	// -----> BUTTOM METHODS <-----

	@FXML
	private void close_app(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	private void log_out(MouseEvent event) {
		manager_object.Close_connection();
		LaunchApplication.stage.show();
		Stage stage = (Stage) logOut_buttom.getScene().getWindow();
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
	 
	public static void setValues(SQLManager manager, Director director) {
		manager_object = manager;
		director_account = director;
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



















