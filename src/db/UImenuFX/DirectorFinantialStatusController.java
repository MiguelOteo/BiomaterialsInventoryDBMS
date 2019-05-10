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
import db.pojos.Client;
import db.pojos.Transaction;
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

public class DirectorFinantialStatusController implements Initializable{
	
	// -----> CLASS ATRIBUTES <-----
	
	private static SQLManager manager_object;
	@SuppressWarnings("rawtypes")
	private Series series = new XYChart.Series();
	
	// -----> FXML ATRIBUTES <-----
	
	@FXML
	private CategoryAxis number;
	@FXML
	private NumberAxis amount_purchased;
	@FXML
	private LineChart<String, Number> line_chart;
	@FXML
	private Integer client_id_backup = 0; 
	@FXML
	private Pane main_pane;
	@FXML
	private JFXTreeTableView<TransactionListObject> transactions_tree_view;
	@FXML
	private final ObservableList<TransactionListObject> transactions_objects = FXCollections.observableArrayList();
	
	// -----> ESSENTIAL METHODS <-----
	
	public DirectorFinantialStatusController() {
		// TODO Auto-generated constructor stub
	}
	
	public static void setValues(SQLManager manager) {
		manager_object = manager;
	}

	@Override @SuppressWarnings("unchecked")
	public void initialize(URL location, ResourceBundle resources) {
		
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
	
	// -----> CHART MAIN CODE <-----
	
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
}

