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
import db.pojos.Worker;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class RemoveWorkerController implements Initializable{
	// -----> CLASS ATRIBUTES <-----

		private static SQLManager manager_object;
		
		// -----> FXML ATRIBUTES <-----
		
		@FXML
		private AnchorPane account_window;
		@FXML
		private JFXButton delete_account_button;
		@FXML
		private JFXTreeTableView<WorkerListObject> worker_tree_view;
		@FXML
		private final ObservableList<WorkerListObject> worker_objects = FXCollections.observableArrayList();
		
		// -----> ESSENTIAL METHODS <-----
		
		public RemoveWorkerController() {
			// TODO Auto-generated constructor stub
		}
		 
		@SuppressWarnings("unchecked")
		public void initialize(URL location, ResourceBundle resources) {
			
			JFXTreeTableColumn<WorkerListObject, String> user_name = new JFXTreeTableColumn<>("User name");
			user_name.setPrefWidth(100);
			user_name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<WorkerListObject,String>, ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(CellDataFeatures<WorkerListObject, String> param) {
					return param.getValue().getValue().user_name;
				}
			});
			user_name.setResizable(false);
			JFXTreeTableColumn<WorkerListObject, String> worker_id = new JFXTreeTableColumn<>("Worker ID");
			worker_id.setPrefWidth(75);
			worker_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<WorkerListObject,String>, ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(CellDataFeatures<WorkerListObject, String> param) {
					return param.getValue().getValue().worker_id;
				}
			});
			worker_id.setResizable(false);
			JFXTreeTableColumn<WorkerListObject, String> user_id = new JFXTreeTableColumn<>("User ID");
			user_id.setPrefWidth(75);
			user_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<WorkerListObject,String>, ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(CellDataFeatures<WorkerListObject, String> param) {
					return param.getValue().getValue().user_id;
				}
			});
			user_id.setResizable(false);

			
			List<Worker> worker_list = manager_object.List_all_workers();
			for(Worker worker: worker_list) {
				worker_objects.add(new WorkerListObject(worker.getUser().getUserName(), worker.getUser().getUserId().toString(), worker.getWorker_id().toString()));
			}
			TreeItem<WorkerListObject> root = new RecursiveTreeItem<WorkerListObject>(worker_objects, RecursiveTreeObject::getChildren);
			worker_tree_view.setPlaceholder(new Label("No workers found"));
			worker_tree_view.getColumns().setAll(user_name, worker_id, user_id);
			worker_tree_view.setRoot(root);
			worker_tree_view.setShowRoot(false);
			
			delete_account_button.setOnAction((ActionEvent event) -> {
				TreeItem<WorkerListObject> worker_object = worker_tree_view.getSelectionModel().getSelectedItem();
				if(worker_object != null) {
					manager_object.Delete_stored_user(Integer.parseInt(worker_object.getValue().user_id.getValue().toString()));
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
	class WorkerListObject extends RecursiveTreeObject<WorkerListObject> {
		
		StringProperty user_name;
		StringProperty user_id;
		StringProperty worker_id;
		
		public WorkerListObject(String user_name, String user_id, String worker_id) {
			this.user_name = new SimpleStringProperty(user_name);
			this.user_id = new SimpleStringProperty(user_id);
			this.worker_id = new SimpleStringProperty(worker_id);
		}
	}

