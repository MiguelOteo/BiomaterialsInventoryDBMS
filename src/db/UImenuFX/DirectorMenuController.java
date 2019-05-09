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
import db.pojos.Client;
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
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

public class DirectorMenuController implements Initializable {

	// -----> CLASS ATRIBUTES <-----

	private static Director director_account;
	private static SQLManager manager_object;
	@SuppressWarnings("rawtypes")
	private Series series = new XYChart.Series();

	// -----> FXML ATRIBUTES <-----

	@FXML
	private AnchorPane menu_window;
	@FXML
	private Pane menu_main_pane;
	@FXML
	private Pane pane_backup;
	@FXML
	private Pane main_pane;
	@FXML
	private CategoryAxis number;
	@FXML
	private NumberAxis amount_purchased;
	@FXML
	private LineChart<String, Number> line_chart;
	@FXML
	private Integer client_id_backup = 0; 
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
	private static Stage stage_main;
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
		this.pane_backup = menu_main_pane;
		finantialStatus_button.setDisable(true);
		
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
						stage_window.initModality(Modality.APPLICATION_MODAL);
					}
				});
				stage_window.setOnHiding(new EventHandler<WindowEvent>() {		
					@Override
					public void handle(WindowEvent event) {	
							refreshTransactionListView();
							menu_window.setEffect(null);
					}
				});		
				stage_window.show();
			} catch(IOException delete_client_error) {
				delete_client_error.printStackTrace();
				System.exit(0);
			}
		});
		
		removeWorker_button.setOnAction((ActionEvent) -> {
			try {
				RemoveWorkerController.setValues(manager_object);
				FXMLLoader loader = new FXMLLoader(getClass().getResource("RemoveWorkerView.fxml"));
				Parent root = (Parent) loader.load();
				RemoveWorkerController worker_controller = new RemoveWorkerController();
				worker_controller = loader.getController();
				worker_controller.getDeleteAccountButton().setOnMouseClicked(new EventHandler<Event>() {
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
		transaction_date.setResizable(false);
		List<Transaction> transactions_list = manager_object.List_all_transactions();
		for(Transaction transaction: transactions_list) {
			transactions_objects.add(new TransactionListObject(transaction.getTransaction_id().toString(),transaction.getClient().getUser().getUserName()
					, transaction.getUnits().toString(), transaction.getGain().toString(), transaction.getTransaction_date().toString()));
		}
		TreeItem<TransactionListObject> root = new RecursiveTreeItem<TransactionListObject>(transactions_objects, RecursiveTreeObject::getChildren);
		transactions_tree_view.setPlaceholder(new Label("No transactions found"));
		transactions_tree_view.getColumns().setAll(client_name, amount, units, transaction_date);
		transactions_tree_view.setRoot(root);
		transactions_tree_view.setShowRoot(false);
		
		// Since there is a bug with the line chart, the auto size of x axis does not work correctly so i add it manually
		number.setCategories(FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8")); 
	}
	
	// -----> REFRESH TRANSACTION LIST VIEW <-----
	
	public void refreshTransactionListView() {
		transactions_objects.clear();
		List<Transaction> transactions_list = manager_object.List_all_transactions();
		for(Transaction transaction: transactions_list) {
			transactions_objects.add(new TransactionListObject(transaction.getTransaction_id().toString(), transaction.getClient().getUser().getUserName()
					, transaction.getUnits().toString(), transaction.getGain().toString(), transaction.getTransaction_date().toString()));
		}
		TreeItem<TransactionListObject> root = new RecursiveTreeItem<TransactionListObject>(transactions_objects, RecursiveTreeObject::getChildren);
		transactions_tree_view.refresh();
	}

	// -----> BUTTOM METHODS <-----
	
	@FXML @SuppressWarnings({ "unchecked", "rawtypes" })
    private void selected_item_list_view(MouseEvent event) throws IOException {
    	TreeItem<TransactionListObject> transaction_object = transactions_tree_view.getSelectionModel().getSelectedItem();
    	if(transaction_object != null) {
    		Integer transaction_id = Integer.parseInt(transaction_object.getValue().transaction_id.getValue());
    		Client client = manager_object.Search_transaction_by_id(transaction_id).getClient();
    		if(client.getClient_id() != client_id_backup) {
    			List<Transaction> transactions_list = manager_object.Search_stored_transactions(client);
    			series = new XYChart.Series();
            	series.getData().clear();
            	for(int count = 1; count <= 8 && count < transactions_list.size(); count++) {
           			series.getData().add(new XYChart.Data(String.valueOf(transactions_list.size() - count) , transactions_list.get(transactions_list.size() - count).getGain()));
           		}
           		series.setName("Payment $");
           		line_chart.setTitle(client.getName() + "'s transactions record");
           		line_chart.getData().clear();
           		line_chart.getData().add(series);
           		series.getNode().setStyle("-fx-stroke: #4f90a5;"); 
           		client_id_backup = client.getClient_id();
    		}
    	}
	}
	
	@FXML
	private void list_all_clients_button(MouseEvent event) throws IOException {
		current_pane_option_label.setText("List all clients");
		setAllButtonsOn();
		listAllClients_button.setDisable(true);
		ListAllClientsController.setValues(manager_object);
		Pane list_all_clients_pane = FXMLLoader.load(getClass().getResource("ListAllClientsView.fxml"));
		main_pane.getChildren().removeAll();
		main_pane.getChildren().setAll(list_all_clients_pane);
	}
	
	@FXML
	private void list_all_workers_button(MouseEvent event) throws IOException {
		current_pane_option_label.setText("List all workers");
		setAllButtonsOn();
		listAllWorkers_button.setDisable(true);
		ListAllWorkersController.setValues(manager_object);
		Pane list_all_workers_pane = FXMLLoader.load(getClass().getResource("ListAllWorkersView.fxml"));
		main_pane.getChildren().removeAll();
		main_pane.getChildren().setAll(list_all_workers_pane);
	}
	
	@FXML
	private void finantial_status_button(MouseEvent event) throws IOException { 
		setAllButtonsOn();
		finantialStatus_button.setDisable(true);
		Parent root = FXMLLoader.load(getClass().getResource("DirectorMenuView.fxml"));
		stage_main.getScene().setRoot(root);
		// TODO - Labels
	}

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

	public static void setStage(Stage stage) {
		stage_main = stage;
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
	
	StringProperty transaction_id;
	StringProperty client_name;
	StringProperty amount;
	StringProperty units;
	StringProperty transaction_date;
	
	public TransactionListObject(String transaction_id, String client_name, String amount, String units, String transaction_date) {
		this.transaction_id = new SimpleStringProperty(transaction_id);
		this.client_name = new SimpleStringProperty(client_name);
		this.amount = new SimpleStringProperty(amount);
		this.units = new SimpleStringProperty(units);
		this.transaction_date = new SimpleStringProperty(transaction_date);
	}
}



















