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

public class ListAllClientsController implements Initializable{

	// -----> CLASS ATRIBUTES <-----

	private static SQLManager manager_object;
	
	// -----> FXML ATRIBUTES <-----
	
	@FXML
	private Pane main_pane;
	@FXML
	private JFXTreeTableView<ClientListObject2> client_tree_view;
	@FXML
	private final ObservableList<ClientListObject2> clients_objects = FXCollections.observableArrayList();
	
	public ListAllClientsController() {
		// TODO Auto-generated constructor stub
	}
	
	public static void setValues(SQLManager manager) {
		manager_object = manager;
	}
	
	@Override @SuppressWarnings("unchecked")
	public void initialize(URL location, ResourceBundle resources) {
		
		// Client list columns creation
		
		JFXTreeTableColumn<ClientListObject2, String> client_name = new JFXTreeTableColumn<>("Client name");
		client_name.setPrefWidth(180);
		client_name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ClientListObject2,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<ClientListObject2, String> param) {
				return param.getValue().getValue().client_name;
			}
		});
		client_name.setResizable(false);
		
		JFXTreeTableColumn<ClientListObject2, String> user_name = new JFXTreeTableColumn<>("User name");
		user_name.setPrefWidth(180);
		user_name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ClientListObject2,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<ClientListObject2, String> param) {
				return param.getValue().getValue().user_name;
			}
		});
		user_name.setResizable(false);
		
		JFXTreeTableColumn<ClientListObject2, String> client_telephone = new JFXTreeTableColumn<>("Telephone");
		client_telephone.setPrefWidth(180);
		client_telephone.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ClientListObject2,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<ClientListObject2, String> param) {
				return param.getValue().getValue().client_telephone;
			}
		});
		client_telephone.setResizable(false);
		
		JFXTreeTableColumn<ClientListObject2, String> client_email = new JFXTreeTableColumn<>("Email");
		client_email.setPrefWidth(180);
		client_email.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ClientListObject2,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<ClientListObject2, String> param) {
				return param.getValue().getValue().client_email;
			}
		});
		client_email.setResizable(false);
		
	    JFXTreeTableColumn<ClientListObject2, String> client_category = new JFXTreeTableColumn<>("Category");
	    client_category.setPrefWidth(180);
	    client_category.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ClientListObject2,String>, ObservableValue<String>>() {
	    	@Override
	    	public ObservableValue<String> call(CellDataFeatures<ClientListObject2, String> param) {
	    		return param.getValue().getValue().client_category;
	    	}
	    });
	    client_category.setResizable(false);
			
		List<Client> clients_list = manager_object.List_all_clients();
		for (Client client: clients_list) {
			clients_objects.add(new ClientListObject2(client.getEmail(), "None (TODO - Repair)" /*client.getCategory().getCategory_name()*/, client.getUser().getUserName()
					, client.getName(), client.getTelephone().toString()));
		}
		TreeItem<ClientListObject2> root = new RecursiveTreeItem<ClientListObject2>(clients_objects, RecursiveTreeObject::getChildren);
		client_tree_view.setPlaceholder(new Label("No clients found"));
		client_tree_view.getColumns().setAll(user_name, client_name, client_category, client_email, client_telephone);
		client_tree_view.setRoot(root);
		client_tree_view.setShowRoot(false);
	}

// -----> REFRESH CLIENTS LIST VIEW <-----

	public void refreshClientListView() {
		clients_objects.clear();
		List<Client> clients_list = manager_object.List_all_clients();
		for (Client client: clients_list) {
			clients_objects.add(new ClientListObject2(client.getEmail(), "None (TODO - Repair)" /*client.getCategory().getCategory_name()*/, client.getUser().getUserName()
					, client.getName(), client.getTelephone().toString()));
		}
		TreeItem<ClientListObject2> root = new RecursiveTreeItem<ClientListObject2>(clients_objects, RecursiveTreeObject::getChildren);
		client_tree_view.refresh();
	}
}

//To insert columns into the list of clients with all the information
class ClientListObject2 extends RecursiveTreeObject<ClientListObject2> {
	
	StringProperty user_name;
	StringProperty client_name;
	StringProperty client_telephone;
	StringProperty client_email;
	StringProperty client_category;
	
	public ClientListObject2(String client_email, String client_category, String user_name, String client_name, String client_telephone) {
		this.client_name = new SimpleStringProperty(client_name);
		this.user_name = new SimpleStringProperty(user_name);
		this.client_telephone = new SimpleStringProperty(client_telephone);
		this.client_email = new SimpleStringProperty(client_email);
		this.client_category = new SimpleStringProperty(client_category);
	}
}