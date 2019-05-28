package db.UImenuFX;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class ClientTransactionController implements Initializable{
	
	// -----> CLASS ATRIBUTES <-----
	
	private static SQLManager manager_object;
	private static Client client_account;
	
	// -----> FXML ATRIBUTES <-----
	

	@FXML
	private Pane main_pane;
	@FXML
	private JFXTreeTableView<TransactionListObject2> transactions_tree_view;
	@FXML
	private final ObservableList<TransactionListObject2> transactions_objects = FXCollections.observableArrayList();
	
	// -----> ESSENTIAL METHODS <-----
	
	public ClientTransactionController() {
		// TODO Auto-generated constructor stub
	}
	
	public static void setValues(SQLManager manager, Client client) {
		manager_object = manager;
		client_account = client;
	}

	@Override @SuppressWarnings("unchecked")
	public void initialize(URL location, ResourceBundle resources) {
		
		// Transaction list columns creation
		JFXTreeTableColumn<TransactionListObject2, String> biomat_name = new JFXTreeTableColumn<>("Biomat names");
		biomat_name.setPrefWidth(450);
		biomat_name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TransactionListObject2,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<TransactionListObject2, String> param) {
				return param.getValue().getValue().biomat_names;
			}
		});
		biomat_name.setResizable(false);
		JFXTreeTableColumn<TransactionListObject2, String> amount = new JFXTreeTableColumn<>("Payment");
		amount.setPrefWidth(150);
		amount.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TransactionListObject2,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<TransactionListObject2, String> param) {
				return param.getValue().getValue().amount;
			}
		});
		amount.setResizable(false);
		JFXTreeTableColumn<TransactionListObject2, String> units = new JFXTreeTableColumn<>("Units");
		units.setPrefWidth(150);
		units.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TransactionListObject2,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<TransactionListObject2, String> param) {
				return param.getValue().getValue().units;
			}
		});
		units.setResizable(false);
		JFXTreeTableColumn<TransactionListObject2, String> transaction_date = new JFXTreeTableColumn<>("Transaction date");
		transaction_date.setPrefWidth(150);
		transaction_date.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TransactionListObject2,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<TransactionListObject2, String> param) {
				return param.getValue().getValue().transaction_date;
			}
		});
		
		transaction_date.setResizable(false);
		List<Transaction> transactions_list = manager_object.Search_stored_transactions(client_account);
		for(Transaction transaction: transactions_list) {
			String biomat_names = "";
			for(Integer n = 0; n < transaction.getBiomaterial_list().size(); n ++) {
				biomat_names = biomat_names + " " + transaction.getBiomaterial_list().get(n).getName_product();
			}
			transactions_objects.add(new TransactionListObject2(transaction.getTransaction_id().toString(), biomat_names, 
					transaction.getGain().toString(), transaction.getUnits().toString(), transaction.getTransaction_date().toString()));
		}
		TreeItem<TransactionListObject2> root = new RecursiveTreeItem<TransactionListObject2>(transactions_objects, RecursiveTreeObject::getChildren);
		transactions_tree_view.setPlaceholder(new Label("No transactions found"));
		transactions_tree_view.getColumns().setAll(biomat_name, amount, units, transaction_date);
		transactions_tree_view.setRoot(root);
		transactions_tree_view.setShowRoot(false);
		
		
	}
	
	// -----> REFRESH TRANSACTION LIST VIEW <-----
	
	public void refreshTransactionListView() {
		transactions_objects.clear();
		List<Transaction> transactions_list = manager_object.Search_stored_transactions(client_account);
		for(Transaction transaction: transactions_list) {
			String biomat_names = "";
			for(Integer n = 0; n < transaction.getBiomaterial_list().size(); n ++) {
				biomat_names = biomat_names + " " + transaction.getBiomaterial_list().get(n).getName_product();
			}
			transactions_objects.add(new TransactionListObject2(transaction.getTransaction_id().toString(), biomat_names, 
					transaction.getGain().toString(), transaction.getUnits().toString(), transaction.getTransaction_date().toString()));
		}
		TreeItem<TransactionListObject2> root = new RecursiveTreeItem<TransactionListObject2>(transactions_objects, RecursiveTreeObject::getChildren);
		transactions_tree_view.refresh();
	}
}

//-----> TRANSACTION LIST CLASS <-----

//To insert columns into the list of transactions with all the information
class TransactionListObject2 extends RecursiveTreeObject<TransactionListObject2> {
	
	StringProperty transaction_id;
	StringProperty biomat_names;
	StringProperty amount;
	StringProperty units;
	StringProperty transaction_date;
	StringProperty biomatname;
	
	public TransactionListObject2(String transaction_id, String biomat_names, String amount, String units, String transaction_date) {
		this.transaction_id = new SimpleStringProperty(transaction_id);
		this.biomat_names = new SimpleStringProperty(biomat_names);
		this.amount = new SimpleStringProperty(amount);
		this.units = new SimpleStringProperty(units);
		this.transaction_date = new SimpleStringProperty(transaction_date);
	}
}


