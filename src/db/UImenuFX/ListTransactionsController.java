package db.UImenuFX;

import java.net.*;

import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import db.jdbc.SQLManager;
import db.pojos.Client;
import db.pojos.Transaction;
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
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

public class ListTransactionsController implements Initializable{
	
	
	// -----> CLASS ATRIBUTES <-----

	private static SQLManager manager_object;
	
	// -----> FXML ATRIBUTES <-----
	
    @FXML
    private AnchorPane account_window;
    @FXML
    private JFXButton back_button;
    @FXML
    private JFXTreeTableView<TransactionsListObject> transactions_tree_view;
    @FXML
	private final ObservableList<TransactionsListObject> transaction_objects = FXCollections.observableArrayList();
	
    // -----> ESSENTIAL METHODS <-----+
    
    public ListTransactionsController() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    public static void setValues(SQLManager manager) {
		manager_object = manager;
	}
    
    @SuppressWarnings("unchecked")
	@Override 
	public void initialize(URL location, ResourceBundle resources) {
			
		// Transaction list columns creation

				JFXTreeTableColumn<TransactionsListObject, String> trans_id = new JFXTreeTableColumn<>("Transaction");
				trans_id.setPrefWidth(240);
				trans_id.setCellValueFactory(
						new Callback<TreeTableColumn.CellDataFeatures<TransactionsListObject, String>, ObservableValue<String>>() {
							@Override
							public ObservableValue<String> call(CellDataFeatures<TransactionsListObject, String> param) {
								return param.getValue().getValue().transaction_id;
							}
						});
				trans_id.setResizable(false);

				JFXTreeTableColumn<TransactionsListObject, String> gain = new JFXTreeTableColumn<>("Gain units");
				gain.setPrefWidth(220);
				gain.setCellValueFactory(
						new Callback<TreeTableColumn.CellDataFeatures<TransactionsListObject, String>, ObservableValue<String>>() {
							@Override
							public ObservableValue<String> call(CellDataFeatures<TransactionsListObject, String> param) {
								return param.getValue().getValue().gain;
							}
						});
				gain.setResizable(false);

				JFXTreeTableColumn<TransactionsListObject, String> units = new JFXTreeTableColumn<>("Units");
				units.setPrefWidth(190);
				units.setCellValueFactory(
						new Callback<TreeTableColumn.CellDataFeatures<TransactionsListObject, String>, ObservableValue<String>>() {
							@Override
							public ObservableValue<String> call(CellDataFeatures<TransactionsListObject, String> param) {
								return param.getValue().getValue().units;
							}
						});
				units.setResizable(false);

				JFXTreeTableColumn<TransactionsListObject, String> trans_date = new JFXTreeTableColumn<>("Transaction date");
				trans_date.setPrefWidth(190);
				trans_date.setCellValueFactory(
						new Callback<TreeTableColumn.CellDataFeatures<TransactionsListObject, String>, ObservableValue<String>>() {
							@Override
							public ObservableValue<String> call(CellDataFeatures<TransactionsListObject, String> param) {
								return param.getValue().getValue().transaction_date;
							}
						});
				trans_date.setResizable(false);

				JFXTreeTableColumn<TransactionsListObject, String> biomat = new JFXTreeTableColumn<>("Biomaterial");
				biomat.setPrefWidth(40);
				biomat.setCellValueFactory(
						new Callback<TreeTableColumn.CellDataFeatures<TransactionsListObject, String>, ObservableValue<String>>() {
							@Override
							public ObservableValue<String> call(CellDataFeatures<TransactionsListObject, String> param) {
								return param.getValue().getValue().biomaterial;
							}
						});
				biomat.setResizable(false);
				
				JFXTreeTableColumn<TransactionsListObject, String> client = new JFXTreeTableColumn<>("Client");
				client.setPrefWidth(40);
				client.setCellValueFactory(
						new Callback<TreeTableColumn.CellDataFeatures<TransactionsListObject, String>, ObservableValue<String>>() {
							@Override
							public ObservableValue<String> call(CellDataFeatures<TransactionsListObject, String> param) {
								return param.getValue().getValue().client;
							}
						});
				client.setResizable(false);
				
				List<Transaction> transaction_list = manager_object.List_all_transactions();
				System.out.println(transaction_list);
				for(Transaction transaction: transaction_list) {
					transaction_objects.add(new TransactionsListObject(transaction.getTransaction_id().toString(), transaction.getGain(), transaction.getUnits().toString(), 
						transaction.getTransaction_date().toString(), transaction.getBiomaterial().toString(), transaction.getClient() ));
				}
				
				TreeItem<TransactionsListObject> root = new RecursiveTreeItem<TransactionsListObject>(transaction_objects, RecursiveTreeObject::getChildren);
				transactions_tree_view.getColumns().setAll(trans_id, gain, units, trans_date, biomat, client);
				transactions_tree_view.setRoot(root);
				transactions_tree_view.setShowRoot(false);

				
				//Ables the selection of several transactions of treeTable
				//next step: associate selection's id to a variable being read by Order product controller
				transactions_tree_view.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);	
				System.out.println("h");
	
}
    

// To insert columns into the list of transactions with all the information
class TransactionsListObject extends RecursiveTreeObject<TransactionsListObject> {
	StringProperty transaction_id;
	StringProperty gain;
	StringProperty units;
	StringProperty transaction_date;
	StringProperty biomaterial;
	StringProperty client;

	public TransactionsListObject(String id, String transaction_id, String gain, String units, String transaction_date, String biomaterial, String client) {
		this.transaction_id = new SimpleStringProperty(transaction_id);
		this.gain = new SimpleStringProperty(gain);
		this.units = new SimpleStringProperty(units);
		this.transaction_date = new SimpleStringProperty(transaction_date);
		this.biomaterial = new SimpleStringProperty(biomaterial);
		this.client = new SimpleStringProperty(client);
	}

	public TransactionsListObject(String string, Float gain2, String string2, String string3, String string4,
			Client client2) {
		// TODO Auto-generated constructor stub
	}
}

}

