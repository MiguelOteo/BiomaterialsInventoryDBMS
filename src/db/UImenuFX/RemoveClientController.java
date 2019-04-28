package db.UImenuFX;

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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class RemoveClientController implements Initializable{
	
	// -----> CLASS ATRIBUTES <-----

	private static SQLManager manager_object;
	
	// -----> FXML ATRIBUTES <-----
	
	@FXML
	private AnchorPane account_window;
	@FXML
	private JFXButton delete_account_button;
	@FXML
	private JFXTreeTableView<ClientListObject> clients_tree_view;
	@FXML
	private final ObservableList<ClientListObject> clients_objects = FXCollections.observableArrayList();
	
	// -----> ESSENTIAL METHODS <-----+
	
	public RemoveClientController() {
		// TODO Auto-generated constructor stub
	}
	 
	@SuppressWarnings("unchecked")
	public void initialize(URL location, ResourceBundle resources) {
		
		JFXTreeTableColumn<ClientListObject, String> user_name = new JFXTreeTableColumn<>("User name");
		user_name.setPrefWidth(100);
		user_name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ClientListObject,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<ClientListObject, String> param) {
				return param.getValue().getValue().user_name;
			}
		});
		user_name.setResizable(false);
		JFXTreeTableColumn<ClientListObject, String> client_id = new JFXTreeTableColumn<>("Client ID");
		client_id.setPrefWidth(75);
		client_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ClientListObject,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<ClientListObject, String> param) {
				return param.getValue().getValue().client_id;
			}
		});
		client_id.setResizable(false);
		JFXTreeTableColumn<ClientListObject, String> user_id = new JFXTreeTableColumn<>("User ID");
		user_id.setPrefWidth(75);
		user_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ClientListObject,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<ClientListObject, String> param) {
				return param.getValue().getValue().user_id;
			}
		});
		user_id.setResizable(false);

		
		List<Client> clients_list = manager_object.List_all_clients();
		for(Client client: clients_list) {
			clients_objects.add(new ClientListObject(client.getUser().getUserName(), client.getClient_id().toString(),client.getUser().getUserId().toString()));
		}
		TreeItem<ClientListObject> root = new RecursiveTreeItem<ClientListObject>(clients_objects, RecursiveTreeObject::getChildren);
		clients_tree_view.getColumns().setAll(user_name, client_id, user_id);
		clients_tree_view.setRoot(root);
		clients_tree_view.setShowRoot(false);
		
		delete_account_button.setOnAction((ActionEvent event) -> {
			TreeItem<ClientListObject> client_object = clients_tree_view.getSelectionModel().getSelectedItem();
			if(client_object != null) {
				manager_object.Delete_stored_user(Integer.parseInt(client_object.getValue().user_id.getValue().toString()));
				Stage stage = (Stage) account_window.getScene().getWindow();
				stage.close();
			} else {
				Stage stage = (Stage) account_window.getScene().getWindow();
				stage.close();
			}
		});
	}

	public static void setValues(SQLManager manager) {
		manager_object = manager;
	}
	
	public void close_window() {
		Stage stage = (Stage) account_window.getScene().getWindow();
		stage.close();
	}
	
	public JFXButton getDeleteAccountButton() {
		return delete_account_button;
	}
}

//-----> TRANSACTION LIST CLASS <-----

//To insert columns into the list of clients with all the information
class ClientListObject extends RecursiveTreeObject<ClientListObject> {
	
	StringProperty user_name;
	StringProperty user_id;
	StringProperty client_id;
	
	public ClientListObject(String user_name, String user_id, String client_id) {
		this.user_name = new SimpleStringProperty(user_name);
		this.user_id = new SimpleStringProperty(user_id);
		this.client_id = new SimpleStringProperty(client_id);
	}
}
