package db.UImenuFX;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXSpinner;

import db.jdbc.SQLManager;
import db.pojos.Worker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ChargeIconController implements Initializable{

	private static SQLManager manager_object;
	private WorkerMenuController worker_controller;
	
    @FXML
    private JFXSpinner cycle;
    @FXML
    private Stage cycle_stage;

    
    
    public ChargeIconController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void setValues(SQLManager manager) {
		manager_object = manager;
	}
    
	public WorkerMenuController getWorker_controller() {
		return worker_controller;
	}


	public void setWorker_controller(WorkerMenuController worker_controller) {
		this.worker_controller = worker_controller;
	}






	public JFXSpinner getCycle() {
		return cycle;
	}






	public void setCycle(JFXSpinner cycle) {
		this.cycle = cycle;
	}






	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void charge_worker_main_menu(Worker worker) {
		try {
			WorkerMenuController.setValues(this.manager_object, worker);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("WorkerMenuView.fxml"));
			Parent root = (Parent) loader.load();
			this.worker_controller = loader.getController();
			cycle_stage = new Stage();
			cycle_stage.initStyle(StageStyle.UNDECORATED);
			cycle_stage.setScene(new Scene(root));
			cycle_stage.show();
		} catch (IOException worker_menu_error) {
			worker_menu_error.printStackTrace();
			System.exit(0);
		}
	}
	
	public void removeBlur() {
		this.worker_controller.getAnchorPane().setEffect(null);
	}

}
