package db.UImenuFX;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import db.jdbc.SQLManager;
import db.pojos.Biomaterial;
import db.pojos.Client;
import db.pojos.Transaction;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class ClientTransactionController implements Initializable{
	
	// -----> CLASS ATRIBUTES <-----
	
	private static SQLManager manager_object;
	
	// -----> FXML ATRIBUTES <-----
	

	@FXML
	private Pane main_pane;
	@FXML
	private JFXTreeTableView<TransactionListObject> transactions_tree_view;
	@FXML
	private final ObservableList<TransactionListObject> transactions_objects = FXCollections.observableArrayList();
	
	// -----> ESSENTIAL METHODS <-----
	
	public ClientTransactionController() {
		// TODO Auto-generated constructor stub
	}
	
	public static void setValues(SQLManager manager) {
		manager_object = manager;
	}

	@Override @SuppressWarnings("unchecked")
	public void initialize(URL location, ResourceBundle resources) {
		
		// Transaction list columns creation
		JFXTreeTableColumn<TransactionListObject, String> biomat_name = new JFXTreeTableColumn<>("Biomat names");
		biomat_name.setPrefWidth(120);
		biomat_name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TransactionListObject,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<TransactionListObject, String> param) {
				return param.getValue().getValue().biomat_names;
			}
		});
		biomat_name.setResizable(false);
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
			transactions_objects.add(new TransactionListObject(transaction.getTransaction_id().toString(),transaction.getBiomaterial_list(), 
					transaction.getGain().toString(), transaction.getUnits().toString(), transaction.getTransaction_date().toString()));
		}
		TreeItem<TransactionListObject> root = new RecursiveTreeItem<TransactionListObject>(transactions_objects, RecursiveTreeObject::getChildren);
		transactions_tree_view.setPlaceholder(new Label("No transactions found"));
		transactions_tree_view.getColumns().setAll(biomat_name, amount, units, transaction_date);
		transactions_tree_view.setRoot(root);
		transactions_tree_view.setShowRoot(false);
		
		
	}
	
	// -----> REFRESH TRANSACTION LIST VIEW <-----
	
	public void refreshTransactionListView() {
		transactions_objects.clear();
		List<Transaction> transactions_list = manager_object.List_all_transactions();
		for(Transaction transaction: transactions_list) {
			transactions_objects.add(new TransactionListObject(transaction.getTransaction_id().toString(),transaction.getBiomaterial_list(), 
					transaction.getGain().toString(), transaction.getUnits().toString(), transaction.getTransaction_date().toString()));
		}
		TreeItem<TransactionListObject> root = new RecursiveTreeItem<TransactionListObject>(transactions_objects, RecursiveTreeObject::getChildren);
		transactions_tree_view.refresh();
	}
	
	
}

//-----> TRANSACTION LIST CLASS <-----

//To insert columns into the list of transactions with all the information
class TransactionListObject extends RecursiveTreeObject<TransactionListObject> {
	
	StringProperty transaction_id;
	StringProperty biomat_names;
	StringProperty amount;
	StringProperty units;
	StringProperty transaction_date;
	private String biomatname;
	
	public TransactionListObject(String transaction_id, List<Biomaterial> biomat_names, String amount, String units, String transaction_date) {
		this.transaction_id = new SimpleStringProperty(transaction_id);
		for(Biomaterial biomat :biomat_names) {
			biomat.getName_product();
			this.biomatname=biomatname+biomat.getName_product();
		}
		
		this.biomat_names = new SimpleStringProperty(biomatname);
		this.amount = new SimpleStringProperty(amount);
		this.units = new SimpleStringProperty(units);
		this.transaction_date = new SimpleStringProperty(transaction_date);
	}
}


